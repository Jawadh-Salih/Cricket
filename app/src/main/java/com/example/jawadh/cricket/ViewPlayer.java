package com.example.jawadh.cricket;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class ViewPlayer extends ActionBarActivity {

    private String playeruserName;
    private String playerName;
    private int playerAge;
    private int playerTotals,playerSixes,playerFours;
    private EditText eplayername;

    private TextView tplayeruname,tplayerage,tplayerscore,tplayersixes,tplayerfours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_player);

        Bundle extrasView = getIntent().getExtras();
        playerName = extrasView.getString("player_name_selected");
        playeruserName = extrasView.getString("player_username_selected");
        playerAge = extrasView.getInt("player_age_selected");
        playerTotals =extrasView.getInt("player_score_selected");
        playerSixes = extrasView.getInt("player_sixes_selected");
        playerFours = extrasView.getInt("player_fours_selected");


        eplayername = (EditText) findViewById(R.id.playerName);
        tplayeruname = (TextView) findViewById(R.id.playerUsername);
        tplayerage = (TextView) findViewById(R.id.playerAge);
        tplayerscore = (TextView) findViewById(R.id.scoreValue);
        tplayersixes = (TextView) findViewById(R.id.sixCount);
        tplayerfours = (TextView)findViewById(R.id.fourCount);

        eplayername.setText(playerName);
        tplayeruname.setText(playeruserName);
        tplayerage.setText(playerAge+"");
        tplayerscore.setText(playerTotals+"");
        tplayersixes.setText(playerSixes+"");
        tplayerfours.setText(playerFours+"");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_player, menu);
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

    public void updatePlayerEditions(View view){
        // player controllelr update player.
    }
}
