package com.example.jawadh.cricket;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.jawadh.cricket.Other.Controller.PlayerController;
import com.example.jawadh.cricket.Other.Model.Player;
import com.example.jawadh.cricket.Other.Model.User;
import com.example.jawadh.cricket.Other.Supports.PlayerListAdapter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class MatchScore extends ActionBarActivity {

    PlayerController playerController = PlayerController.getInstance();
    User user = CricManagerApp.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_score);
        Bundle extras = getIntent().getExtras();
        try {
            getPlayerListScore(CricManagerApp.getCurrentMatch().getVerses());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_match_score, menu);
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
    public void getPlayerListScore(String verses) throws IOException,URISyntaxException {
        ArrayList<Player> players = new ArrayList<>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        players = playerController.matchplayerList(user.getClub(),verses);
        Log.d(user.getUserNname(), user.getClub());
        ListAdapter playerListAdapter = new PlayerListAdapter(this,players);
        ListView playerlistView = (ListView) findViewById(R.id.playerlist);
        playerlistView.setAdapter(playerListAdapter);
    }
}
