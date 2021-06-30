package com.drawiin.forca.ui.game;

import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
    private final int errors = 0;

    private final MutableLiveData<String> _error = new MutableLiveData<>();
    final LiveData<String> error = _error;

    private final MutableLiveData<List<KeyboardLetter>> _keyBoardLetter = new MutableLiveData<>();
    final LiveData<List<KeyboardLetter>> keyBoardLetter = _keyBoardLetter;

    private final MutableLiveData<List<GameLetter>> _gameLetter = new MutableLiveData<>();
    final LiveData<List<GameLetter>> gameLetter = _gameLetter;

    private void init() {
        loadKeyBoard();
        currentWord = getRandomWord();
        final List<GameLetter> letters = Arrays.stream(currentWord.split(""))
                .map(w -> new GameLetter(false, w))
                .collect(Collectors.toList());
        _gameLetter.setValue(letters);
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

    }
}
