package com.example.jawadh.cricket;

import android.app.Application;
import android.util.Log;

import com.example.jawadh.cricket.Other.Controller.UserController;
import com.example.jawadh.cricket.Other.DataAccess.LocalDatabase;
import com.example.jawadh.cricket.Other.Model.User;

public class CricManagerApp extends Application {
    public UserController userController;
    //public LocationController locationController;
    public final String PREFS_CODE = "CricPrefs";// use of this?
    private static LocalDatabase localDatabase;
    private static User currentUser;

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
}
