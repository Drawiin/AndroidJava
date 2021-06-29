package com.drawiin.forca;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SQLiteOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new BaseDeDados(this, BaseDeDados.DATABASE_NAME, 1);
        loadWords();
        setupUi();
    }

    private void setupUi() {
//        findViewById(R.id.btn_jogar).setOnClickListener(view -> {
//            final String nickname = ((EditText) findViewById(R.id.input_nome)).getText().toString();
//            if (!nickname.isEmpty()) {
//                JogoActivity.navigate(this, nickname);
//            } else {
//                Toast.makeText(this, "Insira um nome", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void loadWords() {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final List<String> result = Palavras.getWords(db);
        if (result.isEmpty()) {
            seedDb();
        }
    }

    private void seedDb() {
        final List<String> defaultWords =
                Arrays.asList(
                        "Banana",
                        "Mamao",
                        "Jaca",
                        "Azul",
                        "Carro",
                        "Aviao",
                        "Porta",
                        "Cachorro",
                        "Gato",
                        "Passaro"
                );
        addWords(defaultWords);
    }

    private void addWords(List<String> words) {
        words.stream().forEach(word -> {
            if (!word.isEmpty()) {
                final SQLiteDatabase db = dbHelper.getWritableDatabase();
                Palavras.insert(db, word);
            }
        });
    }
}