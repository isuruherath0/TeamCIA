package com.example.weddingrides;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class feedadpter extends ArrayAdapter<feed> {

    Context context;
    int resource;
    List<feed> feeds;

    feedadpter(Context context, int resource, List<feed> feeds){
        super(context,resource,feeds);
        this.context = context;
        this.resource = resource;
        this.feeds = feeds;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView gtafeed = row.findViewById(R.id.feedback);
        //TextView gnumbv = row.findViewById(R.id.numbv);
        //TextView description = row.findViewById(R.id.description);



        // todos [obj1,obj2,obj3]
        feed feds = feeds.get(position);
        gtafeed.setText(feds.getFeedback());




        return row;
    }
}

