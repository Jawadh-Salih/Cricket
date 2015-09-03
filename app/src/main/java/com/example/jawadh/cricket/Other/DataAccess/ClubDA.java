package com.example.jawadh.cricket.Other.DataAccess;

/**
 * Created by Jawadh on 8/8/2015.
 */
public class ClubDA {

    private  ClubDA clubDA;

    private ClubDA(){}

    public ClubDA getInstance(){
        if(clubDA == null)
            clubDA = new ClubDA();
        return clubDA;
    }
}
