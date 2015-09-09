package com.example.jawadh.cricket.Other.Controller;

import com.example.jawadh.cricket.Other.Model.Player;
import com.example.jawadh.cricket.Other.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jawadh on 8/22/2015.
 */
public class UserController {

    private static UserController userController;
    private Connection con;
    private UserController(){}

    public static final int  USERNAME_WRONG = 100;
    public static final int PASSWORD_WRONG = 101;
    public static final int SUCCESS = 102;
    public static UserController getInstance(){
        if(userController == null){
            userController = new UserController();
        }
        return userController;
    }
    User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int login(String userName, String password) throws IOException, URISyntaxException {
        HashMap<String,String> data = new HashMap<>();
        con = Connection.getInstance();
        data.put("username",userName);
        data.put("password", password);

        String results = con.post("login_user.php",data);
        return Integer.parseInt(results);
    }
    public String usernameAvailability(String username) throws IOException,URISyntaxException{
        HashMap<String,String> data = new HashMap<>();
        con = Connection.getInstance();
        data.put("username",username);

        String results = con.post("check_user.php",data);

        return (results);
    }
    public void addUser(User user)throws IOException,URISyntaxException{
        HashMap<String,String> data = new HashMap<>();
        con = Connection.getInstance();

        data.put("name",user.getName());
        data.put("age",user.getAge()+"");
        data.put("username",user.getUserNname());
        data.put("password",user.getPassword());
        data.put("type",user.getType());
        //if(user.getType().equals("player"))
        data.put("club",user.getClub());// only get the variable if user is a player
//        data.put("username","Nifras");
//        data.put("password","nifras");
//        data.put("type","player");

        con.post("add_user.php",data);


    }
    public User getUserdetail(String username,String password) throws IOException, URISyntaxException {
        ArrayList<Player> players = new ArrayList<>();
        HashMap<String,String> data = new HashMap<>();
        User user = new User();
        data.put("username",username);
        data.put("password",password);

        String results =con.get("get_user.php",data);
        try{

            JSONObject temp = new JSONObject(results);

                user.setUserNname(username);
                user.setPassword(password);
                user.setUserid(temp.getInt("user_id"));
                user.setType(temp.getString("type"));
                user.setClub(temp.getString("name"));

        }catch (JSONException e){
            e.getCause();
            e.printStackTrace();
        }

        return  user;
    }
}
