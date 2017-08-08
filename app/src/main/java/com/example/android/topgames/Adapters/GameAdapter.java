package com.example.android.topgames.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.topgames.LocalDB.GameContract;
import com.example.android.topgames.Models.Game;
import com.example.android.topgames.R;

import java.util.ArrayList;

/**
 * Created by админ on 08.08.2017.
 */

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder>{
    private Context mContext;
    private Cursor mCursor;
    //private Game game;
    ArrayList <Game> gameArray;
    public GameAdapter(Context context, Cursor cursor){
        this.mContext = context;
        mCursor = cursor;
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.game_list, parent, false);

        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
       // if(!mCursor.moveToPosition(position)) {
         //   return;
        //}
//        Long id =mCursor.getLong(mCursor.getColumnIndex(GameContract.GameEntity._ID));
//        final String gameName = mCursor.getString(mCursor.getColumnIndex(GameContract.GameEntity.COLUMN_GAME_NAME));
//        String shortDesc = mCursor.getString(mCursor.getColumnIndex(GameContract.GameEntity.COLUMN_SHORT_DESC));
//
//        game = new Game();
//        game.setGameID(mCursor.getLong(mCursor.getColumnIndex(GameContract.GameEntity._ID)));
//        game.setGameName(mCursor.getString(mCursor.getColumnIndex(GameContract.GameEntity.COLUMN_GAME_NAME)));
//        game.setShortDesc(mCursor.getString(mCursor.getColumnIndex(GameContract.GameEntity.COLUMN_SHORT_DESC)));
//        game.setPlay(mCursor.getString(mCursor.getColumnIndex(GameContract.GameEntity.COLUMN_GAME_PLAY)));
//        game.setPreparation(mCursor.getString(mCursor.getColumnIndex(GameContract.GameEntity.COLUMN_GAME_PREPARATION)));
//        game.setRestriction(mCursor.getString(mCursor.getColumnIndex(GameContract.GameEntity.COLUMN_GAME_RESRICTION)));
//        game.setGameImage(mCursor.getString(mCursor.getColumnIndex(GameContract.GameEntity.COLUMN_GAME_IMAGE)));

        final Game game = gameArray.get(position);
        Long id=game.getGameID();
        String gameName=game.getGameName();
        String shortDesc=game.getShortDesc();

        holder.gameName.setText(gameName);
        holder.shortDesc.setText(shortDesc);
      //  holder.itemView.setTag(id);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(game.getGameName());

            }
        });
        
    }

    @Override
    public int getItemCount() {
        return gameArray.size();
    }

    public void updateData(ArrayList<Game> gameArrayList) {
        gameArray=gameArrayList;
    }


    class GameViewHolder extends RecyclerView.ViewHolder {

        TextView gameName;
        TextView shortDesc;
        public GameViewHolder(View itemView) {
            super(itemView);
            gameName = (TextView) itemView.findViewById(R.id.game_name);
            shortDesc = (TextView) itemView.findViewById(R.id.short_desc);
        }
    }
}
