package com.example.jawadh.cricket.Other.Supports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jawadh.cricket.Other.Model.Player;
import com.example.jawadh.cricket.R;

import java.util.ArrayList;

/**
 * Created by Jawadh on 9/4/2015.
 */
public class CustomAdapter extends ArrayAdapter<Player>{

    public CustomAdapter(Context context, ArrayList<Player> players) {
        super(context, R.layout.custom_row, players);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row,parent,false);

        Player singlePlayer = getItem(position);
        TextView tAge = (TextView) customView.findViewById(R.id.age);
        TextView tName = (TextView) customView.findViewById(R.id.playername);
        TextView tRuns = (TextView) customView.findViewById(R.id.runs);
        TextView tId = (TextView) customView.findViewById(R.id.playerId);

        tName.setText(singlePlayer.getName());
        tAge.setText(singlePlayer.getAge()+" yrs");
        tRuns.setText(singlePlayer.getTotalScore()+"");
        tId.setText(singlePlayer.getPlayer_id()+"");

        return customView;

    }
}
