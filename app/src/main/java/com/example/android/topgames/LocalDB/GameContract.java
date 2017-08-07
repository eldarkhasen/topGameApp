package com.example.android.topgames.LocalDB;

import android.provider.BaseColumns;

/**
 * Created by админ on 07.08.2017.
 */

public class GameContract {
    public static class GameEntity implements BaseColumns{
        public static final String TABLE_NAME = "games";
        public static final String COLUMN_GAME_NAME = "gameTitle";
        public static final String COLUMN_GAME_PREPARATION = "preparation";
        public static final String COLUMN_GAME_RESRICTION = "restriction";
        public static final String COLUMN_GAME_PLAY = "gamePlay";
        public static final String COLUMN_SHORT_DESC = "desc";
        public static final String COLUMN_GAME_IMAGE = "image";
    }
}
