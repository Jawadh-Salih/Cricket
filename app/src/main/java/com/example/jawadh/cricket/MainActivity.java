package com.example.jawadh.cricket;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jawadh.cricket.Other.Controller.UserController;
import com.example.jawadh.cricket.Other.Model.User;
import com.example.jawadh.cricket.Other.Services.CricService;

import java.io.IOException;
import java.net.URISyntaxException;


public class MainActivity extends ActionBarActivity {

    UserController userController;
    EditText textUsername;
    EditText textPassword;
    TextView lblAlert;
    SharedPreferences preferences; // why we use shared preferences.
    CheckBox rememberMe;
    public static CricManagerApp myApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(getBaseContext(), CricService.class));

        myApp = (CricManagerApp)getApplication();

        userController = UserController.getInstance();
        textUsername = (EditText)findViewById(R.id.username);
        textPassword = (EditText)findViewById(R.id.password);
        lblAlert = (TextView)findViewById(R.id.tAlert);
        //rememberMe = (CheckBox)findViewById(R.id.chkRememberMe);


        preferences = getSharedPreferences(myApp.PREFS_CODE,0);
        //restore username password if user has asked to remember user.
        if(preferences.getBoolean("remember",false)){
            textUsername.setText(preferences.getString("username",""));
            textPassword.setText(preferences.getString("password",""));
        }
        Log.d("CricDebug", preferences.contains("remember") + "");
        //rememberMe.setChecked(preferences.getBoolean("remember", true));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void register(View v){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }
    public void login(View v){
         //before we have to check for the validity of the user that must be done using controller class
//        Intent intent = new Intent(this,PageOne.class);
//        startActivity(intent);
        final String username = textUsername.getText().toString();
        final String password = textPassword.getText().toString();

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
        lblAlert.setText("Logging in...");

        try {
            // what is usage of this?
            AsyncTask at = new AsyncTask() {
                User user = null;

                @Override
                protected Object doInBackground(Object[] params) {
                    String message = null;
                    int r;
                    try {
                        r = userController.login(username, password);
                        user = userController.getUserdetail(username,password);

                        if(r == UserController.USERNAME_WRONG) { //username error
                            message = "User Doesn't exist";
                            return message;
                        }
                        if(r == UserController.PASSWORD_WRONG) {
                            message = "Wrong password";
                            return message;
                        }
                        if(r == UserController.SUCCESS) {
                            return null;
                        }

                    } catch (IOException e) {
                        message = "Connection Error";
                        Log.d("",message);
                        return message;
                    } catch (URISyntaxException e) {
                        message = "Internal Error";
                        Log.d("",message);
                        return message;
                    }

                    return "System Error";
                }
                @Override
                protected void onPostExecute(Object o) {
                    super.onPostExecute(o);
                    if(o == null) {
                        SharedPreferences.Editor editor = preferences.edit();
                        Log.d("",user.getType());
                        editor.putString("username", user.getUserNname());
                        editor.putString("password", user.getPassword());
                        editor.putString("type",user.getType());
                        editor.putInt("user_id", user.getUserid());
                        CricManagerApp.setCurrentUser(user);
                        Log.d(user.getUserNname(),user.getType());
//                        if(user.getType().equals("player")){
//                            // restrict the user from updating the scoercard
//                        }
//                        else if(user.getType().equals("manager")){
//                            // login as usual.
//                        }
                        editor.apply();
                        editor.commit();
                        Log.d("cricdebug", preferences.getString("username", "none")
                                + ", " + preferences.getString("password", "none"));
                        loginNow();
                    }
                    else
                        lblAlert.setText(o.toString());
                }
            };

            at.execute();
        }
        catch (Exception e)
        {
            lblAlert.setText(e.toString());
            return;
        }
        finally {
            return;
        }
    }
    public void loginNow(){
        lblAlert.setText("Login Successful");
        startActivity(new Intent(this,MainMenu.class));
    }
    public void setAlert(String alert) {
        lblAlert.setText(alert);
    }

    public void testScore(View v){
        startActivity(new Intent(this,PlayerList.class));
    }
}
