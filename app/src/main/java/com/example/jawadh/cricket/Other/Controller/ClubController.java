package com.example.jawadh.cricket.Other.Controller;


import android.util.Log;

import com.example.jawadh.cricket.Other.Model.Match;
import com.example.jawadh.cricket.Other.Model.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;


public class ClubController {

    private static ClubController clubController;
    private Connection con;
    private ClubController(){}

    public static  ClubController getInstance(){
        if(clubController == null){
            clubController = new ClubController();
        }
        return clubController;
    }
    public void addClub(User user)throws IOException,URISyntaxException {
        HashMap<String,String> addClub = new HashMap<>();
        con = Connection.getInstance();
        Log.d(user.getUserNname()," "+user.getClub()+"   "+user.getType());
        addClub.put("username",user.getUserNname()); // only update is user is a manager
        addClub.put("clubname",user.getClub());

        con.post("add_club.php",addClub);
    }

    public void addMatch(Match match) throws IOException,URISyntaxException{
        HashMap<String,String> addMatch = new HashMap<>();
        con = Connection.getInstance();

        addMatch.put("verses",match.getVerses());

        con.get("add_match.php",addMatch);

    }

}
