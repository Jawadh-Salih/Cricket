package com.example.jawadh.cricket.Other.Model;

/**
 * Created by Jawadh on 8/8/2015.
 */
public class Manager extends User{


    private String type = "manager";
    private int club_id;

    public String getType(){
        return type;
    }

    public int getClub_id() {
        return club_id;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
    }
}
