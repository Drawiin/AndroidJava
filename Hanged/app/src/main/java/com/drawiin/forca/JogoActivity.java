package com.drawiin.forca;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.drawiin.forca.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class JogoActivity extends AppCompatActivity {
    private Timer timer;
    private String nickname;
    private SQLiteOpenHelper dbHelper;
    private String currentWord;
    private List<String> gameWords;
    private List<String> playedWords;
    private int errors = 0;
    private @DrawableRes
    final
    int[] stickers = {
            R.drawable.sticker_1,
            R.drawable.sticker_2,
            R.drawable.sticker_3,
            R.drawable.sticker_4,
            R.drawable.sticker_5,
            R.drawable.sticker_6,
            R.drawable.sticker_7
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        dbHelper = new BaseDeDados(this, BaseDeDados.DATABASE_NAME, 1);
        nickname = getIntent().getStringExtra(NICKNAME);
        setupUi();
        startGame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    private void startGame() {
        currentWord = getRandomWord().toUpperCase();
        Log.d("GAME_WORD", currentWord);
        gameWords = Arrays.stream(currentWord.split("")).map(w -> "_").collect(Collectors.toList());
        playedWords = new ArrayList<>(Collections.emptyList());
        startTimer();
        renderWord();
        renderPlayedWords();
        updateSticker();
        setupControl();
    }



    private void updateSticker() {
        final ImageView image = findViewById(R.id.img_sticker);
        final Drawable img = ContextCompat.getDrawable(this, stickers[errors]);
        image.setImageDrawable(img);
    }

    private void renderWord() {
//        final String wordToShow = gameWords.stream().reduce("", (acc, curr) -> acc + " " + (curr.equals("_") ? "__" : curr));
//        ((TextView) findViewById(R.id.tv_word)).setText(wordToShow);
    }

    private void renderPlayedWords() {
//        final String wordToShow = playedWords.stream().reduce("", (acc, curr) -> acc + curr + ", ");
//        ((TextView) findViewById(R.id.tv_used_words)).setText("Letras jogadas : \n" + wordToShow);
    }

    private void onNewWord() {
//        final EditText editText = findViewById(R.id.input_word);
//        final String newWord = editText.getText().toString().toUpperCase();
//        if (newWord.isEmpty()) {
//            Toast.makeText(this, "Insira uma letra", Toast.LENGTH_SHORT).show();
//        } else if (playedWords.stream().anyMatch(w -> w.equals(newWord))) {
//            Toast.makeText(this, "Você já jogou essa palavra", Toast.LENGTH_SHORT).show();
//        } else {
//            playedWords.add(newWord);
//            if (Arrays.asList(currentWord.split("")).contains(newWord)) {
//                gameWords = Arrays.stream(currentWord.split("")).map(w -> w.equals(newWord) || playedWords.contains(w) ? w : "_").collect(Collectors.toList());
//            } else {
//                errors += 1;
//            }
//        }
//
//        renderWord();
//        renderPlayedWords();
//        updateSticker();
//        verifyWinOrLose();
//        editText.setText("");
    }

    private void setupControl() {
//        final Button button = findViewById(R.id.btn_confirm);
//        button.setOnClickListener(view -> onNewWord());
    }

    private String getRandomWord() {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final List<String> result = Palavras.getWords(db);
        if (!result.isEmpty()) {
            final Random rand = new Random();
            return result.get(rand.nextInt(result.size()));
        }
        return "";
    }

    private void verifyWinOrLose() {
        if (!gameWords.contains("_")) {
            winGame();
        } else if (errors >= stickers.length - 1) {
            loseGame();
        }
    }

    private void winGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Parabés você ganhou a palavra era: " + currentWord)
                .setTitle("Vitória")
                .setPositiveButton("OK", (d, id) -> finish())
                .create()
                .show();
    }

    private void loseGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Não doi dessa vez a palavra era: " + currentWord)
                .setTitle("Perdeu")
                .setPositiveButton("OK", (d, id) -> finish())
                .create()
                .show();

    }

    private void startTimer() {
//        if (timer != null) timer.cancel();
//        final TextView textView = findViewById(R.id.tv_time);
//        timer = new Timer();
//        new Thread(() -> timer.scheduleAtFixedRate(new TimerTask() {
//            long secondsUntilFinished = 60 * 3;
//
//            public void run() {
//                if (secondsUntilFinished <= 1) {
//                    timer.cancel();
//                    ContextCompat.getMainExecutor(getBaseContext()).execute(() -> {
//                        losesGameTimeOut();
//                    });
//
//                } else {
//                    secondsUntilFinished--;
//                }
//                final String minutes = String.valueOf(secondsUntilFinished / 60);
//                final String seconds = String.valueOf(secondsUntilFinished % 60);
//                ContextCompat.getMainExecutor(getBaseContext()).execute(() -> {
//                    textView.setText(minutes + ":" + seconds);
//                });
//            }
//        }, 0, 1000)).start();
    }

    private void losesGameTimeOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Opa o tempo acabou e você perdeu")
                .setTitle("Perdeu")
                .setPositiveButton("OK", (d, id) -> finish())
                .create()
                .show();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit_game:
                exitGame();
                return true;
            case R.id.restart_game:
                restartGame();
                return true;
            case R.id.start_new_game:
                startNewGame();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void exitGame() {
        finish();
    }

    private void restartGame() {
        Log.d("GAME_WORD", currentWord);
        gameWords = Arrays.stream(currentWord.split("")).map(w -> "_").collect(Collectors.toList());
        playedWords = new ArrayList<>(Collections.emptyList());
        startTimer();
        renderWord();
        renderPlayedWords();
        updateSticker();
        setupControl();
    }

    private void startNewGame() {
        startGame();
    }

    private void setupUi() {
        final ActionBar toolbar = Objects.requireNonNull(getSupportActionBar());
        toolbar.setTitle("Vamos jogar: " + nickname);
    }


    private static final String NICKNAME = "NICKNAME";

    public static void navigate(Context context, String nickname) {
        final Intent intent = new Intent(context, JogoActivity.class);
        intent.putExtra(NICKNAME, nickname);
        context.startActivity(intent);
    }
}