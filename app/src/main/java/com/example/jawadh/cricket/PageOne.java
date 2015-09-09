package com.example.jawadh.cricket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jawadh.cricket.Other.Model.User;


public class PageOne extends ActionBarActivity {

    User user = CricManagerApp.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_one);
       // Log.d("Current User is a ",user.getType());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_page_one, menu);
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
    public void getScoreCard(View view){
        Log.d("jawadh", "Inside the view");
        Intent intent = new Intent(this,BattingScoreCard.class);
        startActivity(intent);

    }
    // this method switch to the playerlist in the database. Currently there are no any players. So first
    // implement addplayer method below
    public void getPlayerList(View view){
        Log.d("Player", "Get the Club Player List");
        Intent intent = new Intent (this,PlayerList.class);
        startActivity(intent);
    }
    // this method swithches to the add player UI and then manager can add  a player to the database.

}
