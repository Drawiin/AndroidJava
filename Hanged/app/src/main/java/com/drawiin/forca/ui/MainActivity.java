package com.drawiin.forca.ui;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.drawiin.forca.R;
import com.drawiin.forca.database.HangedDatabaseBuilder;
import com.drawiin.forca.database.entity.WordEntity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SQLiteOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_add_words).setOnClickListener(view -> InsertWordsActivity.navigate(this));
        dbHelper = new HangedDatabaseBuilder(this, HangedDatabaseBuilder.DATABASE_NAME, 1);
        loadWords();
    }

    private void loadWords() {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final List<String> result = WordEntity.getWords(db);
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
                WordEntity.insert(db, word);
            }
        });
    }
}