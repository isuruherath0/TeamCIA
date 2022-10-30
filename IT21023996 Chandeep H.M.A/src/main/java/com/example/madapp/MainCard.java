package com.example.madapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainCard extends AppCompatActivity {

    private Button add;
    private ListView cardList;
    private TextView cardCount;
    Context context;
    private cardDbhandler carddbhandler;
    private List<cardModel> cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_card);
        context = this;
        carddbhandler = new cardDbhandler(context);

        add = findViewById(R.id.add);
        cardList = findViewById(R.id.cardList);
        cardCount = findViewById(R.id.cardCount);

        cards = new ArrayList<>();
        cards =  carddbhandler.getAllcards();

        CardAdapter adapter = new CardAdapter(context,R.layout.add_card,cards);
        cardList.setAdapter(adapter);



        int countCd = carddbhandler.countCards();
        cardCount.setText("You have "+countCd+" cards");



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, add_card.class));
            }
        });
        cardList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final cardModel cmodel = cards.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Card Number : "+cmodel.getcNumber());
                builder.setMessage("Cardholder Name : "+cmodel.getcName());

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(context,MainCard.class));

                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        carddbhandler.deletecard(cmodel.getId());
                        startActivity(new Intent(context,MainCard.class));
                    }
                });
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(context,editCart.class);
                        intent.putExtra("id",String.valueOf(cmodel.getId()));
                        startActivity(intent);

                    }
                });
                builder.show();

            }
        });
    }
}