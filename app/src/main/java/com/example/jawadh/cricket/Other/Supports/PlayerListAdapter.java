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
 * Created by Jawadh on 9/7/2015.
 */
public class PlayerListAdapter extends ArrayAdapter<Player> {

    public PlayerListAdapter(Context context, ArrayList<Player> players) {
        super(context, R.layout.player_row, players);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row,parent,false);

        Player singlePlayer = getItem(position);
        TextView tName = (TextView) customView.findViewById(R.id.nameplayer);
        TextView tScore = (TextView) customView.findViewById(R.id.scoreplayer);
        TextView tBalls = (TextView) customView.findViewById(R.id.ballsfaced);
        TextView tsRate = (TextView) customView.findViewById(R.id.strikerate);

        tName.setText(singlePlayer.getName());
        tScore.setText(singlePlayer.getAge()+"");
        tBalls.setText(singlePlayer.getTotalScore()+"");
        tsRate.setText(singlePlayer.getPlayer_id()+"");

        return customView;

    }
}
