package com.example.android.topgames.LocalDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by админ on 07.08.2017.
 */

public class DBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "toDoList.db";

    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_WAITLIST_TABLE = "CREATE TABLE " + GameContract.GameEntity.TABLE_NAME + " (" +
                GameContract.GameEntity._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GameContract.GameEntity.COLUMN_GAME_NAME + " TEXT NOT NULL, " +
                GameContract.GameEntity.COLUMN_GAME_PLAY + " TEXT NOT NULL, " +
                GameContract.GameEntity.COLUMN_SHORT_DESC + " TEXT NOT NULL, " +
                GameContract.GameEntity.COLUMN_GAME_PREPARATION + " TEXT NOT NULL, " +
                GameContract.GameEntity.COLUMN_GAME_RESRICTION + " TEXT NOT NULL, " +
                GameContract.GameEntity.COLUMN_GAME_IMAGE + " TEXT NOT NULL " + " ); ";
        db.execSQL(SQL_CREATE_WAITLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + GameContract.GameEntity.TABLE_NAME);
        onCreate(db);
    }
}
