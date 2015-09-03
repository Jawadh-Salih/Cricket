package com.example.jawadh.cricket.Other.Model;

/**
 * Created by Jawadh on 8/22/2015.
 */
public class User {

    private String Name;
    private int Age;
    private int userid;
    private String userNname;
    private String password;
    private String type;
    private String club;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUserNname() {
        return userNname;
    }

    public void setUserNname(String userNname) {
        this.userNname = userNname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }
}
