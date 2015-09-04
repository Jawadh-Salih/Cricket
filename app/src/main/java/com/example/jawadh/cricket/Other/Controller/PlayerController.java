package com.example.jawadh.cricket.Other.Controller;

import android.util.Log;

import com.example.jawadh.cricket.Other.Model.Player;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jawadh on 8/19/2015.
 */
public class PlayerController {

    Connection con = Connection.getInstance();
    private static PlayerController playerController;

    private PlayerController(){}

    public static PlayerController getInstance(){
        if(playerController == null){
            playerController = new PlayerController();
        }
        return playerController;
    }
    //Player player = new Player();


    public ArrayList<Player> getPlayerDetails(String clubName) throws IOException,URISyntaxException{
        ArrayList<Player> players = new ArrayList<>();
        HashMap<String,String> data = new HashMap<>();
        Player player;

        data.put("club",clubName);

        String results =con.get("get_player.php",data);
        Log.d("",results);
        try{
            JSONArray array = new JSONArray(results);
            JSONObject temp;

            for(int i=0;i<array.length();i++){
                temp =array.getJSONObject(i);
                player = new Player();

                player.setName(temp.getString("name"));
                player.setAge(temp.getInt("age"));
                player.setTotalScore(temp.getInt("total_score"));

                players.add(player);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        return  players;
    }
    public ArrayList<Player> getPlayerAll(String clubName) throws IOException,URISyntaxException{
        ArrayList<Player> players = new ArrayList<>();
        HashMap<String,String> data = new HashMap<>();
        Player player = null;
        data.put("club",clubName);

        String results =con.get("get_player.php",data);

        try{
            JSONArray array = new JSONArray(results);
            JSONObject temp;
            for(int i=0;i<array.length();i++){
                temp =array.getJSONObject(i);
                player = new Player();

                player.setName(temp.getString("name"));
                player.setAge(temp.getInt("age"));
                player.setTotalScore(temp.getInt("total_score"));
                player.setSixes(temp.getInt("sixes"));
                player.setFours(temp.getInt("fours"));

                players.add(player);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return  players;
    }
}