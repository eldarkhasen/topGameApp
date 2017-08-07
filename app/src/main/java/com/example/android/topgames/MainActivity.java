package com.example.android.topgames;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.topgames.Config.Config;
import com.example.android.topgames.Models.Game;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private TextView shortDesc;
    private Game mainGame;
    private Button nextButton;
    private ProgressBar simpleProgressBar;
    private TextView more;
    private View moreView;
    public int c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        more.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                    // TODO Auto-generated method stub
                Intent mIntent = new Intent(MainActivity.this,GameDetailActivity.class);
                mIntent.putExtra("Game", (Serializable) mainGame);
                startActivity(mIntent);
                    return false;
            }
        });


        nextButton = (Button) findViewById(R.id.nextGame);
        getRandom(nextButton);

    }


    public void getRandom(View view){
        Random rand = new Random();
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
                        setImage(MainActivity.this,game.getGameImage());
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
                System.out.println("The read failed: " + firebaseError.getMessage());
                simpleProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void setImage(Context context, String image){
        ImageView gameImg = (ImageView) findViewById(R.id.gameImage);
        Glide.with(context).load(image).into(gameImg);
    }


}
