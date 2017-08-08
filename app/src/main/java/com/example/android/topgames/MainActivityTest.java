package com.example.android.topgames;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.android.topgames.Config.Config;
import com.example.android.topgames.Models.Game;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.Serializable;
import java.util.Random;

public class MainActivityTest extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView title;
    private TextView shortDesc;
    private Game mainGame;
    private Button nextButton;
    private ProgressBar simpleProgressBar;
    private TextView more;
    private View moreView;
    public int c;
    public CoordinatorLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);

        if(isNetworkConnected()==false){
            Intent mIntent = new Intent(MainActivityTest.this,FavoGamesActivity.class);
            startActivity(mIntent);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Typeface tf = Typeface.createFromAsset(getAssets(),
                "Fonts/Oswald-Medium.ttf");
        title = (TextView) findViewById(R.id.gameName);
        title.setTypeface(tf);
        shortDesc = (TextView) findViewById(R.id.shortDesc);
        simpleProgressBar = (ProgressBar) findViewById(R.id.determinateBar);
        more = (TextView) findViewById(R.id.more);
        moreView = (View) findViewById(R.id.more_view);
        more.setVisibility(View.INVISIBLE);
        moreView.setVisibility(View.INVISIBLE);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(MainActivityTest.this,GameDetailActivity.class);
                mIntent.putExtra("Game", (Serializable) mainGame);
                startActivity(mIntent);
            }
        });



        getRandom();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRandom();
            }
        });

    }

    public void getRandom(){
        Firebase.setAndroidContext(this);
        Firebase ref = new Firebase(Config.FIREBASE_URL);
        simpleProgressBar.setVisibility(View.VISIBLE);
        ref.child("Game").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                Random random = new Random();
                int index = random.nextInt((int) snapshot.getChildrenCount());
                int count = 0;
                for (DataSnapshot snapshotT : snapshot.getChildren()) {
                    if (count == index) {
                        c=count;
                        Game game = snapshotT.getValue(Game.class);
                        Log.e("snapshot", game.getGameName());
                        mainGame = game;
                        title.setText(game.getGameName());
                        shortDesc.setText(game.getShortDesc());
                        setImage(MainActivityTest.this,game.getGameImage());
                        Log.e("IMAGE URL",game.getGameImage());
                        simpleProgressBar.setVisibility(View.INVISIBLE);
                        more.setVisibility(View.VISIBLE);
                        moreView.setVisibility(View.VISIBLE);
                        return;
                    }
                    count++;
                }

            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {
                linearLayout = (CoordinatorLayout) findViewById(R.id.main);
                System.out.println("The read failed: " + firebaseError.getMessage());
                simpleProgressBar.setVisibility(View.INVISIBLE);
                Snackbar snackbar = Snackbar
                        .make(linearLayout, "Нет интернет соединения", Snackbar.LENGTH_LONG);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.RED);
                snackbar.show();
            }
        });
    }

    public void setImage(Context context, String image){
        ImageView gameImg = (ImageView) findViewById(R.id.gameImage);
        Glide.with(context)
                .load(image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.placeh)
                .into(gameImg);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_test, menu);
        return true;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_favorite) {
            Intent mIntent = new Intent(MainActivityTest.this,FavoGamesActivity.class);
            startActivity(mIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
