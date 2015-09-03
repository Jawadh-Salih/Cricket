package com.example.jawadh.cricket.Other.Model;

/**
 * Created by Jawadh on 8/8/2015.
 */
public class Club {

    private int club_id;
    private String clubname;
    private int totalMatches;
    private int totalWins;
    private int totalLoses;
    private int majorCup;

    public int getClub_id() {
        return club_id;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
    }

    public String getClubname() {
        return clubname;
    }

    public void setClubname(String clubname) {
        this.clubname = clubname;
    }

    public int getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(int totalMatches) {
        this.totalMatches = totalMatches;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

    public int getTotalLoses() {
        return totalLoses;
    }

    public void setTotalLoses(int totalLoses) {
        this.totalLoses = totalLoses;
    }

    public int getMajorCup() {
        return majorCup;
    }

    public void setMajorCup(int majorCup) {
        this.majorCup = majorCup;
    }
}
