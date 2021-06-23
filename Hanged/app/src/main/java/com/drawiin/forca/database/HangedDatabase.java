package com.drawiin.forca.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class HangedDatabase {
    private final HangedDbHelper dbHelper;

    private HangedDatabase(HangedDbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    private static HangedDatabase INSTANCE = null;

    public static HangedDatabase getInstance(HangedDbHelper db) {
        if (INSTANCE == null)
            INSTANCE = new HangedDatabase(db);
        return INSTANCE;
    }

    public long insert(String name, ContentValues values) {
        SQLiteDatabase db = INSTANCE.dbHelper.getWritableDatabase();
        return db.insert(name, null, values);
    }
}
