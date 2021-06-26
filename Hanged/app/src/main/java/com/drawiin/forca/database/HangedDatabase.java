package com.drawiin.forca.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class HangedDatabase {
    private final HangedDatabaseBuilder dbHelper;

    private HangedDatabase(HangedDatabaseBuilder dbHelper) {
        this.dbHelper = dbHelper;
    }

    private static HangedDatabase INSTANCE = null;

    public static HangedDatabase getInstance(HangedDatabaseBuilder db) {
        if (INSTANCE == null)
            INSTANCE = new HangedDatabase(db);
        return INSTANCE;
    }

    public SQLiteDatabase getWritableDatabase(){
        return dbHelper.getWritableDatabase();
    }

    public SQLiteDatabase getReadableDatabase(){
        return dbHelper.getReadableDatabase();
    }
}
