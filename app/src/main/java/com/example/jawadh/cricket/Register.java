package com.example.jawadh.cricket;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jawadh.cricket.Other.Controller.ClubController;
import com.example.jawadh.cricket.Other.Controller.UserController;
import com.example.jawadh.cricket.Other.Model.User;
import com.example.jawadh.cricket.Other.Services.CricService;

import java.io.IOException;
import java.net.URISyntaxException;


public class Register extends ActionBarActivity {

    private ProgressDialog progressDialog;
    private User user;
    UserController userController;
    ClubController clubController;
    EditText tName,tuName,tpWord,tage,tClub;
    Spinner dropdown;
    String item = "";
    TextView lblAlert ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        startService(new Intent(getBaseContext(), CricService.class));

//        myApp = (CricManagerApp)getApplication();

        userController = UserController.getInstance();
        clubController = ClubController.getInstance();
        tName = (EditText) findViewById(R.id.name);
        tuName = (EditText) findViewById(R.id.userRname);
        tpWord = (EditText) findViewById(R.id.Rpassword);
        tage = (EditText) findViewById(R.id.age);// make s spinner (combo box for this)
        tClub = (EditText) findViewById(R.id.club);
        lblAlert = (TextView) findViewById(R.id.tAlertReg);
        dropdown = (Spinner) findViewById(R.id.type);
        final String[] items = new String[]{"player", "manager"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                item = items[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
            Log.d("Settings"," Clicked");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void registerUser(final View v) throws IOException, URISyntaxException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        final String username = tuName.getText().toString();
        final String password = tpWord.getText().toString();
        final String type = item;
        final String name = tName.getText().toString();
        final int age = Integer.parseInt(tage.getText().toString());
        final String club = tClub.getText().toString();
        if(userController.usernameAvailability(username).equals("FALSE")) {
            if (clubController.checkClub(club).equals("FALSE") && item == "player") {
                lblAlert.setText(" Please enter your valid Club name");
            } else if (clubController.checkClub(club).equals("TRUE") && item == "manager") {
                lblAlert.setText(" Club name already Exists");
            }
            else
            gotoLogin(v);

        }
        else if(userController.usernameAvailability(username).equals("TRUE")){
            lblAlert.setText(" Entered username is already in use ");
        }
        // make a widget form to load the club details.
        if(username == null || username.length() <= 0) {
            lblAlert.setText("Invalid Username");
            lblAlert.requestFocus();
            return;
        }
        if(password == null || password.length() <= 0) {
            lblAlert.setText("Invalid Password");
            lblAlert.requestFocus();
            return;
        }
        try{

        }
        catch (Exception e){
           // lblAlert.setText(e.toString());
        }
       // startActivity(new Intent(this,MainActivity.class));

    }

    public void gotoLogin(View view){
        startActivity(new Intent(this,MainActivity.class));
    }
    public void alert(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

        builder.setMessage(message);

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void ThreadActivities(){
        // get all the usernames as a string array and return.

    }

}
/*
 AsyncTask asyncTask = new AsyncTask() {
                @Override
                protected Object doInBackground(Object[] params) {
                    Boolean isExist;
                    String message ="";
                    try {
                        isExist = userController.usernameAvailability(username);

                    } catch (IOException e) {
                        message = "Connection Error";
                        return message;
                    } catch (URISyntaxException e) {
                        message = "Internal Error";
                        return message;
                    }
                    if(isExist) { //username error
                        message = "Try another username, User name already exists";
                        lblAlert.setText(message);
                        return message;
                    }
                    else
                        return null;
                }
                protected void onPostExecute(Object o) {
                    super.onPostExecute(o);
                    if (o == null) {

                        final User user = new User();
                       // final User player = new Player();
                        user.setUserNname(username);
                        user.setPassword(password);
                        user.setType(type);
                        user.setName(name);
                        user.setAge(age);
                        user.setClub(club);
                        gotoLogin(v);
                        try {
                            if (user.getType().equals("manager")) {
                                userController.addUser(user);

                            }
                            if (user.getType().equals("player")) {
                                userController.addUser(user);
                            }
                            //if(!user.getType().equals("manager") || !user.getType().equals("player"))
                                //alert("Please enter a valid User (player or Manager)");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            };

            asyncTask.execute();

 */