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
import android.widget.EditText;
import android.widget.TextView;

import com.example.jawadh.cricket.Other.Controller.UserController;
import com.example.jawadh.cricket.Other.Model.User;
import com.example.jawadh.cricket.Other.Services.CricService;

import java.io.IOException;
import java.net.URISyntaxException;


public class MainActivity extends ActionBarActivity {

    private UserController userController;
    private EditText textUsername;
    private EditText textPassword;
    private TextView lblAlert;
    private SharedPreferences preferences;
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
        //rememberMe = (CheckBox)findViewById(R.id.chkRememberMe); do this if I have time


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
            // Making the valdation process under a thread
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
                        message = "Connection Error IO EXCEPTION"+e.getMessage(); // remove the final before submission
                        Log.d("",message);
                        return message;
                    } catch (URISyntaxException e) {
                        message = "Internal Error";
                        Log.d("",message);
                        return message;
                    }

                    return "System Error"; // this will give a not null object
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
                        editor.putString("club",user.getClub());
                        CricManagerApp.setCurrentUser(user);
                        Log.d(user.getUserNname(),user.getType());
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
}
