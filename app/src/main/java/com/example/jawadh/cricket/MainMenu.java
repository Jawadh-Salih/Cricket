package com.example.jawadh.cricket;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jawadh.cricket.Other.Controller.ClubController;
import com.example.jawadh.cricket.Other.Model.Club;
import com.example.jawadh.cricket.Other.Model.Match;
import com.example.jawadh.cricket.Other.Model.User;

import java.io.IOException;
import java.net.URISyntaxException;


public class MainMenu extends ActionBarActivity {

    private User user = CricManagerApp.getCurrentUser();
    private ClubController clubController = ClubController.getInstance();
    private Club club;
    private Match match;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        if(club == null) {
            club = new Club();
            club.setClubname(user.getClub());
        }
        club.setClubname(user.getClub());

       }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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
    public void gotoPageOne(View view){
        // using this method has to go to the pageone. Score card and List.
        // only manager granted access to the page.
        // new match
       // if(CricManagerApp.getCurrentUser().getType().equals("manager")) {
            startActivity(new Intent(this, PageOne.class)); // only manager can access
        //}
       // alert("Please Login as a Manager :)");
       // Toast.makeText(getApplicationContext(),"Login as a manager",Toast.LENGTH_SHORT);
         // or toast a widget that is saying user is a player who is restricted.
    }
    public void getPlayerList(View view){
        // anyone can go to this page who is in the club
        startActivity(new Intent(this,PlayerList.class));
    }
    public void newMatch(final View view){
        if(user.getType().equals("manager")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("New Match");
            final EditText input = new EditText(MainMenu.this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            input.setHint(" Match Vs ");

            LinearLayout lay = new LinearLayout(this);
            lay.setOrientation(LinearLayout.VERTICAL);
            lay.addView(input);
            builder.setView(lay);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // deal with the editable
                    if (match == null)
                        match = new Match();
                    match.setVerses(input.getText().toString());
                    try {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                        clubController.addMatch(match);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    gotoPlayerList(view, input.getText().toString());
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // Do nothing.
                }
            });
            builder.show();
        }
        else {
            Toast.makeText(getApplicationContext(),"You can't create " +
                    "a match as a Player",Toast.LENGTH_SHORT).show();
        }
    }
    public void gotoPlayerList(View view,String verses){
        Intent intent = new Intent(this, PlayerList.class);
        intent.putExtra("verses",verses);
        startActivity(intent);
    }
}
