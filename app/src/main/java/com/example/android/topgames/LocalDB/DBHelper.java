package com.example.android.topgames.LocalDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by админ on 07.08.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    // The database name
    private static final String DATABASE_NAME = "toDoList.db";
    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
