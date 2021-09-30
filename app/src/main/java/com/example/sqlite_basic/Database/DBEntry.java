package com.example.sqlite_basic.Database;

import android.provider.BaseColumns;

public class DBEntry {

    public DBEntry() {
    }

    public static final class GroceryEntry implements BaseColumns {
        public static final String TABLE_NAME = "User_Details";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_IMAGE = "image";
    }
}
