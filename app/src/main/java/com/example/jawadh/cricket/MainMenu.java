package com.example.jawadh.cricket;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainMenu extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
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
    public void alert(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

        builder.setMessage(message);
        AlertDialog alert = builder.create();
        alert.show();
    }
}
