package com.drawiin.forca.database.entity;

public class UserEntity {
    private UserEntity() {
    }

    public static class UserEntityNames {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PHOTO = "photo";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserEntityNames.TABLE_NAME + " (" +
                    UserEntityNames.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    UserEntityNames.COLUMN_NAME_NAME + " TEXT NOT NULL," +
                    UserEntityNames.COLUMN_NAME_PHOTO + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserEntityNames.TABLE_NAME;
}
