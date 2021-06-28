package com.fabi.forca;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Palavras {
    public static String TABLE_NAME = "words";
    public static String ID = "_id";
    public static String WORD = "word";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    + WORD + " TEXT NOT NULL"
                    + ")";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static ContentValues getContentValues(String word) {
        final ContentValues values = new ContentValues();
        values.put(WORD, word);
        return values;
    }

    public static void insert(SQLiteDatabase db, String newWord) {
        final ContentValues word = getContentValues(newWord);
        final long result = db.insert(TABLE_NAME, null, word);
    }

    public static List<String> getWords(SQLiteDatabase db) {
        List<String> words = new ArrayList<String>();
        final String[] fields = {ID, WORD};
        final Cursor result = db.query(
                TABLE_NAME, fields,
                null,
                null,
                null,
                null,
                null,
                null
        );
        while (result.moveToNext()) {
            final String word = result.getString(result.getColumnIndexOrThrow(WORD));
            words.add(word);
        }

        result.close();

        return words;
    }
}
