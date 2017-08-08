package com.example.android.topgames;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.topgames.LocalDB.DBHelper;
import com.example.android.topgames.LocalDB.GameContract;
import com.example.android.topgames.Models.Game;

public class GameDetailActivity extends AppCompatActivity {
    private TextView title;
    private TextView playText;
    private TextView prepareText;
    private TextView restrictionText;
    private Game game;
    private Menu menu;
    private SQLiteDatabase mDb;
    private Cursor mCursor;
    private boolean check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        DBHelper dbHelper = new DBHelper(this);
        mDb = dbHelper.getWritableDatabase();
        game = getGame();
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(game.getGameName());
        }
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "Fonts/Oswald-Medium.ttf");
        title = (TextView) findViewById(R.id.gameName);
        title.setTypeface(tf);
        Typeface desc = Typeface.createFromAsset(getAssets(),
                "Fonts/OpenSans-Light.ttf");
        playText = (TextView) findViewById(R.id.playText);
        playText.setTypeface(desc);
        prepareText = (TextView) findViewById(R.id.prepareText);
        prepareText.setTypeface(desc);
        restrictionText = (TextView) findViewById(R.id.restrictionText);
        restrictionText.setTypeface(desc);
        title.setText(game.getGameName());
        playText.setText(game.getPlay());
        prepareText.setText(game.getPreparation());
        restrictionText.setText(game.getRestriction());
    }

    public Game getGame(){
        Intent intent = getIntent();
        Game game = (Game) intent.getSerializableExtra("Game");
        return game;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_game_menu, menu);
        this.menu = menu;
        MenuItem item = (MenuItem) menu.findItem(R.id.action_add);
        item.setEnabled(false);
        check = isFavorite(game.getGameID());
        if(check==true){
            item.setIcon(getResources().getDrawable(R.drawable.star_fill));
            item.setEnabled(true);
            Log.e("This is in DB",game.getGameName());
        }else{
            item.setIcon(getResources().getDrawable(R.drawable.star));
            item.setEnabled(true);
            Log.e("This is not in DB",game.getGameName());
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(check == false){
            if(id==R.id.action_add){
                item.setIcon(getResources().getDrawable(R.drawable.star_fill));
                check = true;
            }
        }else{
            if(id==R.id.action_add){
                item.setIcon(getResources().getDrawable(R.drawable.star));
                check = false;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean isFavorite(Long id){
        String query = "SELECT * FROM "+ GameContract.GameEntity.TABLE_NAME+ " WHERE "+ GameContract.GameEntity._ID+" = "+id+" ";
        mCursor = mDb.rawQuery(query,null);
        if(mCursor.moveToFirst()){
            return true;
        }else{
            return false;
        }

    }

}
