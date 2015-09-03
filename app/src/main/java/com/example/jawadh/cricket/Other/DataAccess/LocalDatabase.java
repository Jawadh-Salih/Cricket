package com.example.jawadh.cricket.Other.DataAccess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class LocalDatabase extends SQLiteOpenHelper {
    public static final String DATABASENAME = "cric.db";
    public static final int DATABASEVERSION = 3;
    public static final String USER_TABLENAME = "manager";
    public static final String USER_ID = "id";
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";
    private static LocalDatabase localDB;

    private LocalDatabase(Context context){
        super(context,DATABASENAME,null,DATABASEVERSION);

    }
    public static LocalDatabase getInstance(Context context){
        if(localDB == null){
            localDB = new LocalDatabase(context);
        }
        return  localDB;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DATABASE", "  Creating database tables");
        //create the tables
        db.execSQL(managerTableQuery());
        db.execSQL(playerTableQuery());
        db.execSQL(clubTableQuery());
        db.execSQL(matchTableQuery());
        db.execSQL(clubPlayTableQuery());
        db.execSQL(playerPlayTableQuery());
        Log.d("DATABASE", "Tables created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("Drop These", "Table and Check for method usage");
        db.execSQL("DROP TABLE IF EXISTS manager");
        db.execSQL("DROP TABLE IF EXISTS player");
        db.execSQL("DROP TABLE IF EXISTS club");
        db.execSQL("DROP TABLE IF EXISTS game");
        db.execSQL("DROP TABLE IF EXISTS clubplay");
        db.execSQL("DROP TABLE IF EXISTS playerplay");

        onCreate(db);
    }

    public String managerTableQuery() {
        String manager;
        manager=" CREATE TABLE IF NOT EXISTS`manager`( `id` TEXT NOT NULL,\n" +
                " `name` TEXT NOT NULL,\n `password` TEXT NOT NULL" +
                "  `club_id` INTEGER NOT NULL" +
                "  CONSTRAINT `reminder_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`),\n" +
                "PRIMARY KEY (`id`)\n" +
                ")";
        return manager;
    }
    public String playerTableQuery(){
        String player;
        player= " CREATE TABLE IF NOT EXISTS `player` (\n" +
                "  `player_id` INTEGER  NOT NULL,\n" +
                "  `name ` TEXT  NOT NULL,\n" +
                "  `age` INTEGER,\n" +
                "  `total_score ` INTEGER  DEFAULT '0',\n" +
                "  `sixes ` INTEGER  DEFAULT NULL,\n" +
                "  `fours ` INTEGER  DEFAULT NULL,\n" +
                "  `s_rate ` DOUBLE  DEFAULT NULL,\n" +
                "  `average` DOUBLE DEFAULT NULL,\n" +
                "  PRIMARY KEY (`player_id`)\n" +
                ")";
        return player;
    }
    public String matchTableQuery(){
        String match;
        match = "CREATE TABLE IF NOT EXISTS `game` (\n" +
                "  `match_id` INTEGER NOT NULL,\n" +
                "  `matchdate` DATE,\n" +
                "  `verses` TEXT,\n" +
                "  PRIMARY KEY (`match_id`)\n" +
                ") ";
        return match;
    }
    public String clubTableQuery(){
        String club;
        club = "CREATE TABLE IF NOT EXISTS `club` (\n" +
                "  `club_id` INTEGER NOT NULL,\n" +
                "  `name` TEXT NOT NULL,\n" +
                "   `total_matches` INTEGER, " +
                "   `total_wins` INTEGER, " +
                "   `total_loses` INTEGER," +
                "   `major_cup` TEXT, "+
                "   PRIMARY KEY (`club_id`),\n" +
                ")";
        return club;
    }
    public String clubPlayTableQuery(){
        String clubPlay;
        clubPlay="CREATE TABLE IF NOT EXISTS `club_play` (\n" +
                "  `club_id` INTEGER NOT NULL,\n" +
                "  `match_id` INTEGER NOT NULL,\n" +
                "  `play_id` INTEGER NOT NULL,\n" +
                "  `match_score` INTEGER DEFAULT NULL,\n" +
                "   `status` INTEGER ," +
                "   `extras` INTEGER ," +
                "  PRIMARY KEY (`play_id`),\n" +
                "  CONSTRAINT `reminder_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`),\n" +
                "  CONSTRAINT `reminder_ibfk_2` FOREIGN KEY (`match_id`) REFERENCES `match` (`match_id`)\n" +
                ")";
        return clubPlay;
    }
    public String playerPlayTableQuery(){
        String playerPlay;
        playerPlay="CREATE TABLE IF NOT EXISTS `player_play` (\n" +
                "  `id` INTEGER NOT NULL,\n" +
                "  `match_id` INTEGER NOT NULL,\n" +
                "  `player_id` INTEGER NOT NULL," +
                "  `player_score` INTEGER DEFAULT NULL,\n" +
                "   `status` INTEGER ," +
                "   `extras` INTEGER ," +
                "  PRIMARY KEY (`id`),\n" +
                "  CONSTRAINT `reminder_ibfk_1` FOREIGN KEY (`match_id`) REFERENCES `club` (`match_id`)\n" +
                "  CONSTRAINT `reminder_ibfk_2` FOREIGN KEY (`player_id`) REFERENCES `player` (`player_id`)\n" +
                ")";
        return playerPlay;
    }
}
