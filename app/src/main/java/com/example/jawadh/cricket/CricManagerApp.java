package com.example.jawadh.cricket;

import android.app.Application;
import android.util.Log;

import com.example.jawadh.cricket.Other.Controller.UserController;
import com.example.jawadh.cricket.Other.DataAccess.LocalDatabase;
import com.example.jawadh.cricket.Other.Model.Match;
import com.example.jawadh.cricket.Other.Model.Player;
import com.example.jawadh.cricket.Other.Model.User;

public class CricManagerApp extends Application {
    public UserController userController;
    //public LocationController locationController;
    public static Boolean matchClicked = false;
    public final String PREFS_CODE = "CricPrefs";// use of this?
    private static LocalDatabase localDatabase;
    private static User currentUser;
    private static Match currentMatch;
    private static Player setCurrentBatsMan1;
    private static Player setCurrentBatsMan2;

    public CricManagerApp() {
        super();
        localDatabase =  LocalDatabase.getInstance(this);

        Log.d("APP" , "Application started");
    }
    public static LocalDatabase getLocalDatabase() {
        return localDatabase;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Log.d("User",currentUser.getUserNname());
        CricManagerApp.currentUser = currentUser;
    }

    public static void setCurrentMatch(Match currentMatch){
        CricManagerApp.currentMatch = currentMatch;
    }
    public static Match getCurrentMatch(){
        return currentMatch;
    }

    public static Player getCurrentBatsMan1() {
        return setCurrentBatsMan1;
    }

    public static void setCurrentBatsMan1(Player setCurrentBatsMan1) {
        CricManagerApp.setCurrentBatsMan1 = setCurrentBatsMan1;
    }

    public static Player getCurrentBatsMan2() {
        return setCurrentBatsMan2;
    }

    public static void setCurrentBatsMan2(Player setCurrentBatsMan2) {
        CricManagerApp.setCurrentBatsMan2 = setCurrentBatsMan2;
    }
}
