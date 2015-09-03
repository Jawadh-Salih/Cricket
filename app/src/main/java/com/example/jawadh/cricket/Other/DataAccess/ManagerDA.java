package com.example.jawadh.cricket.Other.DataAccess;

/**
 * Created by Jawadh on 8/8/2015.
 */
public class ManagerDA {

    private ManagerDA managerDA;

    private ManagerDA(){}

    public ManagerDA getInstance(){
        if(managerDA == null)
            managerDA = new ManagerDA();
        return managerDA;
    }
}
