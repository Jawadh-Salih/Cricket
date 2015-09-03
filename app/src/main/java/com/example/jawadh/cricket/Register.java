package com.example.jawadh.cricket;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
    EditText tName,tuName,tpWord,tType,tage,tClub;
    TextView lblAlert ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        startService(new Intent(getBaseContext(), CricService.class));

//        myApp = (CricManagerApp)getApplication();

        userController = UserController.getInstance();
        clubController = ClubController.getInstance();
        tName = (EditText)findViewById(R.id.name);
        tuName = (EditText)findViewById(R.id.userRname);
        tpWord = (EditText)findViewById(R.id.Rpassword);
        tage = (EditText) findViewById(R.id.age);// make s spinner (combo box for this)
        tType = (EditText)findViewById(R.id.type);
        tClub = (EditText)findViewById(R.id.club);
        lblAlert = (TextView)findViewById(R.id.tAlert);
        //rememberMe = (CheckBox)findViewById(R.id.chkRememberMe);


//
//        preferences = getSharedPreferences(myApp.PREFS_CODE,0);
//        //restore username password if user has asked to remember user.
//        if(preferences.getBoolean("remember",false)){
//            textUsername.setText(preferences.getString("username",""));
//            textPassword.setText(preferences.getString("password",""));
//        }
//        Log.d("CricDebug", preferences.contains("remember") + "");
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
    public void registerUser(View v){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        final String username = tuName.getText().toString();
        final String password = tpWord.getText().toString();
        final String type = tType.getText().toString();
        final String name = tName.getText().toString();
        final int age = Integer.parseInt(tage.getText().toString());
        final String club = tClub.getText().toString();


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

                        if(type.toLowerCase() != "player" || type.toLowerCase() != "manager") {
                            message = "please write a correct type";
                            lblAlert.setText(message);
                            return message;
                        }
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

                        try {
                            if (user.getType().equals("manager")) {
                                Log.d("",user.getClub());
                                clubController.addClub(user);
                                userController.addUser(user);
                                // login as usual.
                            }
                            if (user.getType().equals("player")) {
                                userController.addUser(user);
                                Log.d("",user.getType());
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

        }
        catch (Exception e){
            lblAlert.setText(e.toString());
        }
        startActivity(new Intent(this,MainActivity.class));

    }
    public void setAlert(String alert) {
        lblAlert.setText(alert);
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

}
//                                final EditText input = new EditText(Register.this);
// new AlertDialog.Builder(Register.this)
//        .setTitle("Your Club Name")
//        .setMessage("Club")
//        .setView(input)
//        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//public void onClick(DialogInterface dialog, int whichButton) {
//        // deal with the editable
//        user.setClub(input.getText().toString());
//
//        try {
//        clubController.addClub(user);
//        userController.addUser(user);
//        gotoLogin();
//        } catch (IOException e) {
//        e.printStackTrace();
//        } catch (URISyntaxException e) {
//        e.printStackTrace();
//        }
//        }
//        })
//        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//public void onClick(DialogInterface dialog, int whichButton) {
//        // Do nothing.
//        }
//        }).show();