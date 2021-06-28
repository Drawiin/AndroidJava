package com.fabi.forca;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Objects;

public class InserirPalavras extends AppCompatActivity {
    private SQLiteOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_words);
        findViewById(R.id.btn_save).setOnClickListener(view -> addWord());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Nova palavra");
        dbHelper = new BaseDeDados(this, BaseDeDados.DATABASE_NAME, 1);
        loadWords();
    }

    private void loadWords() {
        final SQLiteDatabase db = dbHelper.getReadableDatabase();
        final List<String> result = Palavras.getWords(db);
        final TextView wordsContainer = findViewById(R.id.text_words);
        final String text = result.stream().reduce("", (acc, current) -> acc + "\n" + current);
        wordsContainer.setText("Palavras: \n" + text);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void addWord() {
        final EditText input = findViewById(R.id.input_palavra);
        final String text = input.getText().toString();
        if (!text.isEmpty()) {
            final SQLiteDatabase db = dbHelper.getWritableDatabase();
            Palavras.insert(db, text);
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
        final Intent intent = new Intent(context, InserirPalavras.class);
        context.startActivity(intent);
    }
}