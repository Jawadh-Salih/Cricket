package com.example.jawadh.cricket.Other.Model;

import java.util.Date;

/**
 * Created by Jawadh on 8/8/2015.
 */
public class Match {
    private int matchid;
    private Date matchDate;
    private String verses;
    private int Score;
    private int wickets;
    private String overs;
    private int extras;
    private Player batsMan1;
    private Player batsMan2;
    private int maxOvers;

    public int getMatchid() {
        return matchid;
    }

    public void setMatchid(int matchid) {
        this.matchid = matchid;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public String getVerses() {
        return verses;
    }

    public void setVerses(String verses) {
        this.verses = verses;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public String getOvers() {
        return overs;
    }

    public void setOvers(String overs) {
        this.overs = overs;
    }

    public int getExtras() {
        return extras;
    }

    public void setExtras(int extras) {
        this.extras = extras;
    }

    public Player getBatsMan1() {
        return batsMan1;
    }

    public void setBatsMan1(Player batsMan1) {
        this.batsMan1 = batsMan1;
    }

    public Player getBatsMan2() {
        return batsMan2;
    }

    public void setBatsMan2(Player batsMan2) {
        this.batsMan2 = batsMan2;
    }

    public int getMaxOvers() {
        return maxOvers;
    }

    public void setMaxOvers(int maxOvers) {
        this.maxOvers = maxOvers;
    }
}

