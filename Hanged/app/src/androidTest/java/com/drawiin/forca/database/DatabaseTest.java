package com.drawiin.forca.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.test.platform.app.InstrumentationRegistry;

import com.drawiin.forca.database.entity.WordEntity;
import com.google.common.truth.Truth;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class DatabaseTest {
    private SQLiteOpenHelper dbHelper;

    @Before
    public void createDB() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        dbHelper = new HangedDatabaseBuilder(appContext, null, 1);
    }

    @After
    public void closeDb() {
        dbHelper.close();
    }

    @Test
    public void insertWords_getWordsBack() {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final List<String> expected = Arrays.asList("Word", "Word2");

        WordEntity.insert(db, "Word");
        WordEntity.insert(db, "Word2");
        final List<String> result = WordEntity.getWords(db);

        Truth.assertThat(result).containsExactlyElementsIn(expected);
    }

    @Test
    public void emptyDb_getsEmptyListOfWords() {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();


        final List<String> result = WordEntity.getWords(db);
        Truth.assertThat(result).hasSize(0);
    }
}
