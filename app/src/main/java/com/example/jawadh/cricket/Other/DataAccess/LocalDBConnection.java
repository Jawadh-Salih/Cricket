package com.example.jawadh.cricket.Other.DataAccess;

/**
 * Created by Jawadh on 8/8/2015.
 */
public class LocalDBConnection {

    private static LocalDBConnection localDBConnection;

    private LocalDBConnection(){
    }

    public static LocalDBConnection getInstance(){
        if(localDBConnection == null)
            localDBConnection = new LocalDBConnection();
        return localDBConnection;
    }

}
