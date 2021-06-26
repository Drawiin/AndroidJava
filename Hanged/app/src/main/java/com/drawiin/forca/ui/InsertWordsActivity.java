package com.drawiin.forca.ui;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.drawiin.forca.R;
import com.drawiin.forca.database.HangedDatabaseBuilder;
import com.drawiin.forca.database.entity.WordEntity;

import java.util.List;

public class InsertWordsActivity extends AppCompatActivity {
    private SQLiteOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_words);
        findViewById(R.id.btn_save).setOnClickListener(view -> addWord());
        dbHelper = new HangedDatabaseBuilder(this, HangedDatabaseBuilder.DATABASE_NAME, 1);
        loadWords();
    }

    private void loadWords() {
        final SQLiteDatabase db = dbHelper.getReadableDatabase();
        final List<String> result = WordEntity.getWords(db);
        final TextView wordsContainer = findViewById(R.id.text_words);
        final String text = result.stream().reduce("", (acc, current) -> acc + "\n" + current);
        wordsContainer.setText("Palavras: \n" + text);
    }

    private void addWord() {
        final EditText input = findViewById(R.id.input_palavra);
        final String text = input.getText().toString();
        if (!text.isEmpty()) {
            final SQLiteDatabase db = dbHelper.getWritableDatabase();
            WordEntity.insert(db, text);
            input.setText("");
            loadWords();
        }
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }

    public static void navigate(Context context) {
        final Intent intent = new Intent(context, InsertWordsActivity.class);
        context.startActivity(intent);
    }
}