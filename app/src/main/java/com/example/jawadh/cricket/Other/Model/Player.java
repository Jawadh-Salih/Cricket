package com.example.jawadh.cricket.Other.Model;

import android.widget.TextView;

/**
 * Created by Jawadh on 8/8/2015.
 */
public class Player extends User {

    private String player_id;
    private String name;
    private String password;
    private String type = "player";
    private int age;
    private int totalScore;
    private int sixes;
    private int fours;
    private double sRate;
    private double  average;
    private boolean onStrike;
    private TextView playerView;
    private int run;

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType(){
        return type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public double getsRate() {
        return sRate;
    }

    public void setsRate(double sRate) {
        this.sRate = sRate;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public boolean isOnStrike() {
        return onStrike;
    }

    public void setOnStrike(boolean onStrike) {
        this.onStrike = onStrike;
    }

    public TextView getPlayerView() {
        return playerView;
    }

    public void setPlayerView(TextView playerView) {
        this.playerView = playerView;
    }

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }
}

