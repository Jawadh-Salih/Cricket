package com.example.jawadh.cricket;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.jawadh.cricket.Other.Controller.PlayerController;
import com.example.jawadh.cricket.Other.Model.Player;
import com.example.jawadh.cricket.Other.Model.User;
import com.example.jawadh.cricket.Other.Supports.CustomAdapter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class PlayerList extends ActionBarActivity {

    private PlayerController playerController = PlayerController.getInstance();
    //PlayerHandler playerHandler = PlayerHandler.getInstance();
    private User user = CricManagerApp.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);
        if(user==null){
             user=CricManagerApp.getCurrentUser();
        }

        Log.d("Current User is a ",user.getType());
        try {
            getPlayerList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
 // think about drawable to insert an Image
    public void getPlayerList() throws IOException,URISyntaxException{

        ArrayList<Player> players = new ArrayList<>();
        if(user.getType().trim().equals("manager")) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            players = playerController.getPlayerDetails(user.getClub());
            ListAdapter playerListAdapter = new CustomAdapter(this,players);
            ListView playerlistView = (ListView) findViewById(R.id.playerlist);
            playerlistView.setAdapter(playerListAdapter);

            final ArrayList<Player> finalPlayers = players;
            playerlistView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                play(view,finalPlayers,position);
                        }
                    }
            );
        }
        else{
            Log.d(user.getName(),"is not a manager. Something wrong with the current user"+ user.getType());
        }
    }


    public void play(View view,ArrayList<Player> players,int position){

        final ArrayList<Player> finalPlayers = players;
        Intent intent= new Intent(this,ScoreCard.class);
        intent.putExtra("player_name", finalPlayers.get(position).getName());
        startActivity(intent);
    }
}
