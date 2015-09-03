package com.example.jawadh.cricket.Other.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
/**
 * Created by Jawadh on 8/22/2015.
 */
public class CricService extends Service{
    private static String UPDATE = "com.example.jawadh.cricket.UPDATE";
    CricReciever piasReceiver = new CricReciever();

    @Override
    public int onStartCommand(Intent intent, int flag, int startId){
        piasReceiver.SetAlarm(this);
        return  START_STICKY;
    }

    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
