package com.example.android.topgames.Models;

import java.io.Serializable;

/**
 * Created by админ on 03.08.2017.
 */

public class Game implements Serializable {
    public String GameName;
    public String Play;
    public String Preparation;
    public String Restriction;
    public String ShortDesc;
    public String GameImage;

    public String getGameImage() {
        return GameImage;
    }

    public void setGameImage(String gameImage) {
        GameImage = gameImage;
    }

    public Game() {
    }

    /*public Game(String gameName, String shortDesc, String play, String preparation, String restriction) {
        GameName = gameName;
        ShortDesc = shortDesc;
        Play = play;
        Preparation = preparation;
        Restriction = restriction;
    }*/

    public String getGameName() {
        return GameName;
    }

    public void setGameName(String gameName) {
        GameName = gameName;
    }

    public String getPlay() {
        return Play;
    }

    public void setPlay(String play) {
        Play = play;
    }

    public String getPreparation() {
        return Preparation;
    }

    public void setPreparation(String preparation) {
        Preparation = preparation;
    }

    public String getRestriction() {
        return Restriction;
    }

    public void setRestriction(String restriction) {
        Restriction = restriction;
    }

    public String getShortDesc() {
        return ShortDesc;
    }

    public void setShortDesc(String shortDesc) {
        ShortDesc = shortDesc;
    }
}
