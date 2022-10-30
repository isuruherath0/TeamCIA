package com.example.madapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CardAdapter extends ArrayAdapter<cardModel> {
    private Context context;
    private int resource;
    List<cardModel> cards;
    CardAdapter(Context context, int resource, List<cardModel> cards){
        super(context,resource,cards);
        this.context = context;
        this.resource = resource;
        this.cards = cards;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView number =  row.findViewById(R.id.cardNum);
        TextView name =  row.findViewById(R.id.cardName);
        TextView exp =  row.findViewById(R.id.exp);
        TextView cvc =  row.findViewById(R.id.cvc);

        cardModel cmodel = cards.get(position);
        number.setText(cmodel.getcNumber());
        name.setText(cmodel.getcName());
        cvc.setText(cmodel.getcCVC());
        exp.setText(cmodel.getcExp());

        return row;

    }
}




























