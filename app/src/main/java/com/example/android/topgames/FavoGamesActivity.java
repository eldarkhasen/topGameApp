package com.example.android.topgames;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.topgames.Adapters.GameAdapter;
import com.example.android.topgames.LocalDB.DBHelper;
import com.example.android.topgames.LocalDB.GameContract;
import com.example.android.topgames.Models.Game;

import java.util.ArrayList;
import java.util.List;

public class FavoGamesActivity extends AppCompatActivity {

    public LinearLayout linearLayout;
    private RecyclerView mRecyclerView;
    public ArrayList<Game> gameArrayList = new ArrayList<>();
    private static SQLiteDatabase mDb;
    private GameAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favo_games);
        linearLayout = (LinearLayout) findViewById(R.id
                .linear);
        ActionBar actionBar = getSupportActionBar();

        if(isNetworkConnected()==false){
            Snackbar snackbar = Snackbar
                    .make(linearLayout, "Нет интернет соединения", Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.RED);
            snackbar.show();
            if(actionBar != null){
                actionBar.setDisplayHomeAsUpEnabled(false);
                actionBar.setTitle("Избранные игры");
            }
        }else{
            if(actionBar != null){
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setTitle("Избранные игры");
            }
        }


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DBHelper dbHelper = new DBHelper(this);
        mDb = dbHelper.getWritableDatabase();
        //insertFake(mDb);
        Cursor mCursor = getAllGames();

        gameArrayList = setAllGames(mCursor);
        mAdapter = new GameAdapter(FavoGamesActivity.this,null);
        mAdapter.updateData(gameArrayList);
        mRecyclerView.setAdapter(mAdapter);



    }

    public void insertFake(SQLiteDatabase db){
        if(db==null){
            return;
        }
        List<ContentValues> list = new ArrayList<ContentValues>();
        ContentValues cv = new ContentValues();
        cv.put(GameContract.GameEntity.COLUMN_GAME_NAME,"The hat");
        cv.put(GameContract.GameEntity.COLUMN_SHORT_DESC,"The hat");
        cv.put(GameContract.GameEntity.COLUMN_GAME_PLAY,"The hat");
        cv.put(GameContract.GameEntity.COLUMN_GAME_PREPARATION,"The hat");
        cv.put(GameContract.GameEntity.COLUMN_GAME_RESRICTION,"The hat");
        cv.put(GameContract.GameEntity.COLUMN_GAME_IMAGE,"The hat");
        list.add(cv);
        cv = new ContentValues();
        cv.put(GameContract.GameEntity.COLUMN_GAME_NAME,"LOOOOL");
        cv.put(GameContract.GameEntity.COLUMN_SHORT_DESC,"The hat");
        cv.put(GameContract.GameEntity.COLUMN_GAME_PLAY,"The hat");
        cv.put(GameContract.GameEntity.COLUMN_GAME_PREPARATION,"The hat");
        cv.put(GameContract.GameEntity.COLUMN_GAME_RESRICTION,"The hat");
        cv.put(GameContract.GameEntity.COLUMN_GAME_IMAGE,"The hat");
        list.add(cv);
        try{
            db.beginTransaction();
            db.delete (GameContract.GameEntity.TABLE_NAME,null,null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(GameContract.GameEntity.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
          //  Log.e("List Size", String.valueOf(list.size()));
        }catch (SQLException e){

        }finally{
            db.endTransaction();
        }
    }

    public ArrayList<Game> setAllGames(Cursor cursor){
        ArrayList<Game> gameArrayList = new ArrayList<>();


        if(cursor!=null&&cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Game game = new Game();
                game.setGameID(cursor.getLong(cursor.getColumnIndex(GameContract.GameEntity._ID)));
                game.setGameName(cursor.getString(cursor.getColumnIndex(GameContract.GameEntity.COLUMN_GAME_NAME)));
                game.setShortDesc(cursor.getString(cursor.getColumnIndex(GameContract.GameEntity.COLUMN_SHORT_DESC)));
                game.setPlay(cursor.getString(cursor.getColumnIndex(GameContract.GameEntity.COLUMN_GAME_PLAY)));
                game.setPreparation(cursor.getString(cursor.getColumnIndex(GameContract.GameEntity.COLUMN_GAME_PREPARATION)));
                game.setRestriction(cursor.getString(cursor.getColumnIndex(GameContract.GameEntity.COLUMN_GAME_RESRICTION)));
                game.setGameImage(cursor.getString(cursor.getColumnIndex(GameContract.GameEntity.COLUMN_GAME_IMAGE)));
                gameArrayList.add(game);
            }while(cursor.moveToNext());
            return gameArrayList;
        }else{
            return gameArrayList;
        }


    }


    public Cursor getAllGames(){
        return mDb.query(
                GameContract.GameEntity.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
