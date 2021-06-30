package com.drawiin.forca.ui.game;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.drawiin.forca.R;
import com.drawiin.forca.database.DatabaseHelper;
import com.drawiin.forca.database.WordsDao;
import com.drawiin.forca.model.GameLetter;
import com.drawiin.forca.model.KeyboardLetter;
import com.drawiin.forca.model.KeyboardLetterState;
import com.drawiin.forca.utils.Constants;
import com.drawiin.forca.utils.ResourceLocator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class GameViewModel extends ViewModel {
    private final DatabaseHelper dbHelper;
    private final ResourceLocator resourceLocator;

    @Inject
    GameViewModel(DatabaseHelper dbHelper, ResourceLocator resourceLocator) {
        this.dbHelper = dbHelper;
        this.resourceLocator = resourceLocator;
        init();
    }

    private Timer timer;
    private String currentWord;

    private final MutableLiveData<Long> _timing = new MutableLiveData<>();
    final LiveData<String> timing = Transformations.map(_timing, Object::toString);

    private final MutableLiveData<String> _error = new MutableLiveData<>();
    final LiveData<String> error = _error;

    private final MutableLiveData<String> _loseGameDialog = new MutableLiveData<>();
    final LiveData<String> loseGameDialog = _loseGameDialog;

    private final MutableLiveData<String> _winGameDialog = new MutableLiveData<>();
    final LiveData<String> winGameDialog = _winGameDialog;

    private final MutableLiveData<List<KeyboardLetter>> _keyBoardLetter = new MutableLiveData<>();
    final LiveData<List<KeyboardLetter>> keyBoardLetter = _keyBoardLetter;

    private final MutableLiveData<List<GameLetter>> _gameLetter = new MutableLiveData<>();
    final LiveData<List<GameLetter>> gameLetter = _gameLetter;

    private final MutableLiveData<Integer> _hangedCount = new MutableLiveData<>(0);
    final LiveData<Integer> hangedCount = Transformations.map(_hangedCount, input -> Constants.STICKERS[Math.min(input, Constants.STICKERS.length - 1)]);

    private void init() {
        loadKeyBoard();
        currentWord = getRandomWord();
        Log.d("CURRENT_WORD", currentWord);
        final List<GameLetter> letters = Arrays.stream(currentWord.split(""))
                .map(w -> new GameLetter(false, w))
                .collect(Collectors.toList());
        _gameLetter.setValue(letters);
        startTimer();
    }

    private void loadKeyBoard() {
        final List<KeyboardLetter> keyboard = Arrays.stream(Constants.GAME_LETTERS)
                .map(w -> new KeyboardLetter(w, KeyboardLetterState.IDLE))
                .collect(Collectors.toList());
        _keyBoardLetter.setValue(keyboard);
    }

    private String getRandomWord() {
        try {
            final SQLiteDatabase db = dbHelper.getWritableDatabase();
            final List<String> result = WordsDao.getWords(db);
            if (!result.isEmpty()) {
                final Random rand = new Random();
                return result.get(rand.nextInt(result.size()));
            }

        } catch (Error e) {
            _error.setValue(e.getMessage());
        }
        return "";
    }

    public void onLetterClicked(String letter) {
        final boolean hasPlayedLetter = _keyBoardLetter
                .getValue()
                .stream()
                .anyMatch(l -> l.getLetter().equals(letter)
                        && l.getState() != KeyboardLetterState.IDLE);

        if (hasPlayedLetter) {
            _error.setValue(resourceLocator.getString(R.string.error_letter_played));
        } else if (currentWord.contains(letter)) {
            onKeyboardLetterRight(letter);
            onGameLetterRight(letter);
        } else {
            onKeyboardLetterWrong(letter);
            onGameLetterWrong();
        }
    }

    private void onGameLetterRight(String letter) {
        final List<GameLetter> newLetters = _gameLetter.getValue()
                .stream()
                .map(gl -> gl.getLetter().equals(letter) ? gl.copyWithDiscovered(true) : gl)
                .collect(Collectors.toList());
        _gameLetter.setValue(newLetters);
        verifyGameWinner();
    }

    private void onGameLetterWrong() {
        final Integer hangedCount = _hangedCount.getValue();
        _hangedCount.setValue(hangedCount != null ? hangedCount + 1 : 0);
        verifyGameLose();
    }

    private void onKeyboardLetterRight(String letter) {
        final List<KeyboardLetter> newLetters = _keyBoardLetter.getValue()
                .stream()
                .map(k -> k.getLetter().equals(letter) ? k.copyWithState(KeyboardLetterState.RIGHT) : k)
                .collect(Collectors.toList());
        _keyBoardLetter.setValue(newLetters);

    }

    private void onKeyboardLetterWrong(String letter) {
        final List<KeyboardLetter> newLetters = _keyBoardLetter.getValue()
                .stream()
                .map(k -> k.getLetter().equals(letter) ? k.copyWithState(KeyboardLetterState.WRONG) : k)
                .collect(Collectors.toList());
        _keyBoardLetter.setValue(newLetters);
    }

    private void verifyGameWinner() {
        if (_gameLetter.getValue().stream().allMatch(GameLetter::isDiscovered)) {
            _winGameDialog.setValue(resourceLocator.getString(R.string.dialog_message_win_game));
        }
    }

    private void verifyGameLose() {
        final int hangedCount = _hangedCount.getValue();
        if (hangedCount >= Constants.STICKERS.length - 1) {
            _loseGameDialog.setValue(resourceLocator.getString(R.string.dialog_message_lose_game_hanged));
        }
    }

    private void gameLoseTimeout() {
        _loseGameDialog.postValue(resourceLocator.getString(R.string.dialog_message_lose_game_time_end));
    }

    private void startTimer() {
        if (timer != null) timer.cancel();
        timer = new Timer();
        new Thread(() -> timer.scheduleAtFixedRate(new TimerTask() {
            long secondsUntilFinished = 60 * 3;

            public void run() {
                if (secondsUntilFinished <= 1) {
                    timer.cancel();
                    gameLoseTimeout();
                } else {
                    secondsUntilFinished--;
                }
                _timing.postValue(secondsUntilFinished);
            }
        }, 0, 1000)).start();
    }
}
