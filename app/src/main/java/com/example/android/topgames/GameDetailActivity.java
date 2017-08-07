package com.example.android.topgames;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.topgames.Models.Game;

public class GameDetailActivity extends AppCompatActivity {
    private TextView title;
    private Game game;
    private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "Fonts/Oswald-Medium.ttf");
        title = (TextView) findViewById(R.id.gameName);
        title.setTypeface(tf);
        game = getGame();
        title.setText(game.getGameName());
        Log.e("Game is here",game.getGameName());
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.action_add){
            item.setIcon(getResources().getDrawable(R.drawable.star_fill));
        }
        return super.onOptionsItemSelected(item);
    }
}
