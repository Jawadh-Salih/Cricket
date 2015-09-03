package com.example.jawadh.cricket.Other.Model;

import java.util.Date;

/**
 * Created by Jawadh on 8/8/2015.
 */
public class Match {
    private int matchid;
    private Date matchDate;
    private String verses;

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
}
