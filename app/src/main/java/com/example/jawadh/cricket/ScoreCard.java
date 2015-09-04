package com.example.jawadh.cricket;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jawadh.cricket.Other.Model.Player;

public class ScoreCard extends ActionBarActivity {


    private TextView textR1,textB1,text41,text61,textSR1,textR2,textB2,text42,text62,textSR2,textTotR,textWkts,textOvrs,textExtra ;
    private TextView player1,player2;
    private int runs1=0,balls1=0,fours1 = 0,sixes1=0,runs2=0,balls2=0,fours2 = 0,sixes2=0,
            totalRuns=0,wickets=0,extras,balls=0,fours=0,sixes = 0;
    private double sr = 0.0,sr1 = 0.0,sr2 = 0.0;
    private String run1 = 0+"",ball1 = 0+"",four1=0+"",six1=0+"",SR1=0.0+"",run2 = 0+"",ball2 = 0+"",four2=0+"",
            six2=0+"",SR2=0.0+"",totalRun=0+"",wicket=0+"",ovr=0+"",extra=0+"",ball=0+"";
    private Button b0,b1,b2,b3,b4,b6,bWd,bNb,bLb,bB,bW,bEnd ; // here I have declared the score buttons
    private String playerName="";
    private Player[] player = new Player[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);
        player[0] = new Player();
        player[1] = new Player();
        player[0].setPlayerView((TextView)findViewById(R.id.Player1));
        player[1].setPlayerView((TextView)findViewById(R.id.Player2));
        player1 = player[0].getPlayerView();
        player2 = player[1].getPlayerView();
        player[0].setOnStrike(true);
        player[1].setOnStrike(false);
        Bundle extras=getIntent().getExtras();
        if(player1.getText().toString() == "" || player1.getText().toString()==null) {
            player1.setText(playerName);
            Toast.makeText(getApplicationContext(),"Select a suitable Player",Toast.LENGTH_SHORT);
            startActivity(new Intent(this,PlayerList.class));
        }
        else if(player2.getText().toString() == "" || player2.getText().toString()==null)
            player2.setText(playerName);
        Toast.makeText(getApplicationContext(),"Select a suitable Player",Toast.LENGTH_SHORT);
            startActivity(new Intent(this,PlayerList.class));
        playerName=extras.getString("player_name");

        // if(player1.getText().toString() == null)
       // player1.setText(playerGet().getName());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_score_card, menu);
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
    public void updateText(View view){
        textR1 = (TextView)findViewById(R.id.runsP1);
        textB1 = (TextView)findViewById(R.id.ballsP1);
        text41 = (TextView)findViewById(R.id.foursP1);
        text61 = (TextView)findViewById(R.id.sixesP1);
        textSR1 = (TextView)findViewById(R.id.SRP1);
        textR2 = (TextView)findViewById(R.id.runsP2);
        textB2 = (TextView)findViewById(R.id.ballsP2);
        text42 = (TextView)findViewById(R.id.foursP2);
        text62 = (TextView)findViewById(R.id.sixesP2);
        textSR2 = (TextView)findViewById(R.id.SRP2);
        textTotR= (TextView)findViewById(R.id.tscore);
        textWkts = (TextView) findViewById(R.id.twkts);
        textOvrs = (TextView)findViewById(R.id.tovrs);
        textExtra = (TextView)findViewById(R.id.textra);
        b0 = (Button)findViewById(R.id.button0);
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);
        b6 = (Button)findViewById(R.id.button6);
        bWd = (Button)findViewById(R.id.wide);
        bNb = (Button)findViewById(R.id.noball);
        bLb = (Button)findViewById(R.id.legbye);
        bB = (Button)findViewById(R.id.bye);
        bW = (Button)findViewById(R.id.out);
        bEnd = (Button)findViewById(R.id.endplayer);
        if(view.getId() == b0.getId()){
            player[0].setOnStrike(true);
            player[1].setOnStrike(false);
                runs1 += 0;
                runs2 += 0;
                totalRuns+=0;
        }
        else if(view.getId() == b1.getId()){
            if(player[0].isOnStrike()) {
                runs1 += 1;
                player[1].setOnStrike(true);
                player[0].setOnStrike(false);
            }
            if(player[1].isOnStrike()) {
                runs2 += 1;
                player[0].setOnStrike(true);
                player[1].setOnStrike(false);
            }

            totalRuns+=1;

        }
        else if(view.getId() == b2.getId()){
            if(player[0].isOnStrike()) {
                runs1 += 2;
                player[1].setOnStrike(false);
            }
            if(player[1].isOnStrike()){
                runs2 += 2;
                player[0].setOnStrike(false);
            }
            totalRuns+=2;
        }
        else if(view.getId() == b3.getId()){
            if(player[0].isOnStrike()) {
                runs1 += 3;
                player[1].setOnStrike(true);
                player[0].setOnStrike(false);
            }
            if(player[1].isOnStrike()){
                runs2 += 3;
                player[0].setOnStrike(true);
                player[1].setOnStrike(false);
            }
            totalRuns+=3;
        }
        else if(view.getId() == b4.getId()){
            if(player[0].isOnStrike()) {
                runs1 += 4;
                player[1].setOnStrike(false);
            }
            if(player[1].isOnStrike()){
                runs2 += 4;
                player[0].setOnStrike(false);
            }
            totalRuns+=4;
        }
        else if(view.getId() == b6.getId()){
            if(player[0].isOnStrike()) {
                runs1 += 6;
                player[1].setOnStrike(false);
            }
            if(player[1].isOnStrike()){
                runs2 += 6;
                player[0].setOnStrike(false);
            }
            totalRuns+=6;
        }
        else if(view.getId() == bWd.getId()){
            totalRuns +=1;
            extras +=1;
            balls --;
        }
        else if(view.getId() == bNb.getId()){
            totalRuns += 1;
            extras +=1;
            balls --;
        }
        else if(view.getId() == bLb.getId()){
            totalRuns += 1;
            extras +=1;
        }
        else if(view.getId() == bB.getId()){
            totalRuns +=1;
            extras +=1;
        }
        else if(view.getId() == bW.getId()){
            Intent intent = new Intent(this,PlayerList.class);
            startActivity(intent);
            // change the player and going to the list and choosing a player from the
            // list. and write a method
            wickets +=1;
        }
        balls++;
        fours = 0; sixes = 0;
        Log.d(balls + "", "\n");
        if(balls !=0) {
            // write a testcase for the actual outcome.
            if(player[0].isOnStrike()) {
                sr1 = (runs1 * 100 / balls1);
            }
            if(player[1].isOnStrike()) {
                sr2 = (runs2 * 100 / balls1);
            }
                ovr = (balls / 6) + "." + (balls % 6);
        }
        else {
            sr = 0.0;
            ovr = 0.0+"";
        }
        run1 = runs1+"";
        run2 = runs2+"";
        totalRun = totalRuns+"";
        wicket = wickets+"";
        ball = balls+"";
        ball1 = balls1+"";
        ball2 = balls2+"";
        extra = extras+"";
        four1 = fours1 + "";
        four2 = fours2 + "";
        six1 = sixes1+"";
        six2 = sixes2+"";
        SR1 = sr1+"";
        SR2 = sr2+"";


        textR1.setText(run1);
        textR2.setText(run2);
        textB1.setText(ball1);
        textB2.setText(ball2);
        text41.setText(four1);
        text42.setText(four2);
        text61.setText(six1);
        text62.setText(six2);
        textSR1.setText(SR1);
        textSR2.setText(SR2);
        textTotR.setText(totalRun);
        textWkts.setText(wicket);
        textOvrs.setText(ovr);
        textExtra.setText(extra);
    }
    public void setPlayerData(View view){
        // get all the values in the text field. and

    }
    public void getPlayer1(View v){
        startActivity(new Intent(this,PlayerList.class));
    }
    public void getPlayer2(View v){
        startActivity(new Intent(this,PlayerList.class));

    }

//    public Player playerGet(){
//            return PlayerHandler.getInstance().getPlayer();
//    }
public void alert(String message){
    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

    builder.setMessage(message);

    AlertDialog alert = builder.create();
    alert.show();
}

}