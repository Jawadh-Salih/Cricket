package com.example.jawadh.cricket;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.jawadh.cricket.Other.Controller.PlayerController;
import com.example.jawadh.cricket.Other.Model.Player;
import com.example.jawadh.cricket.Other.Model.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class PlayerList extends ActionBarActivity {

    PlayerController playerController = PlayerController.getInstance();
    //PlayerHandler playerHandler = PlayerHandler.getInstance();
    User user = CricManagerApp.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);
if(user==null){
    user=CricManagerApp.getCurrentUser();
}

        Log.d("Current User is a ",user.getUserNname());
        try {
            init();
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
    public void init() throws IOException, URISyntaxException {
      //  TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);
        TextView playerName = new TextView(this);
        playerName.setText(" Player ");
        playerName.setTextColor(Color.WHITE);
        tbrow0.addView(playerName);
        TextView Age = new TextView(this);
        Age.setText(" Age ");
        Age.setTextColor(Color.WHITE);
        tbrow0.addView(Age);
        TextView Runs = new TextView(this);
        Runs.setText(" Total Runs  ");
        Runs.setTextColor(Color.WHITE);
        tbrow0.addView(Runs);
        ArrayList<Player> players = new ArrayList<>();

        if(user.getType() != "manager") {
            Log.d(user.getName(),"is not a manger. Something wrong with the current user");
        }
        else
            players = playerController.getPlayerDetails(user.getClub());// get manager ID.
        for (int i = 0; i < players.size(); i++) {
            TableRow tbrow = new TableRow(this);
            final TextView playername = new TextView(this);
            playername.setText(players.get(i).getName());
            playername.setTextColor(Color.WHITE);
            playername.setGravity(Gravity.CENTER);
            tbrow.addView(playername);
            TextView age = new TextView(this);
            age .setText(players.get(i).getAge() + "");
            age .setTextColor(Color.WHITE);
            age .setGravity(Gravity.CENTER);
            tbrow.addView(age);
            TextView runs = new TextView(this);
            runs.setText(players.get(i).getTotalScore());
            runs.setTextColor(Color.WHITE);
            runs.setGravity(Gravity.CENTER);
            tbrow.addView(runs);
            Button playerButton = new Button(this);
            playerButton.setText("OK");
            tbrow.addView(playerButton);
            playerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    play(v,playername.getText().toString());
                }
            });
        }
    }

    public void play(View view,String playername){

        Intent intent= new Intent(this,ScoreCard.class);
        intent.putExtra("player_name",playername);
        startActivity(intent);
    }
}
