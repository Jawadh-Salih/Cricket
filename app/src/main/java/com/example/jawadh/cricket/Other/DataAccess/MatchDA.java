package com.example.jawadh.cricket.Other.DataAccess;

/**
 * Created by Jawadh on 8/8/2015.
 */
public class MatchDA {

    private MatchDA matchDA;

    private MatchDA(){}

    public MatchDA getInstance(){
        if(matchDA == null)
            matchDA = new MatchDA();
        return matchDA;
    }
}
