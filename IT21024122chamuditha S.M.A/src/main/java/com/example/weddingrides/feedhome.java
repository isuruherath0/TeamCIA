package com.example.weddingrides;

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

public class feedhome extends AppCompatActivity {

    Button add,cdd;
    ListView listView;
    TextView count;
    Context context;
    DBhandler dBhandler;
    List<feed> feeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedhome);
        context = this;

        dBhandler = new DBhandler(context);
        add=findViewById(R.id.btnAdd);
        //new
        cdd=findViewById(R.id.can);
        listView = findViewById(R.id.feedList);
        count = findViewById(R.id.feedCount);
        feeds = new ArrayList<>();
        feeds = dBhandler.getAllfeed();


        feedadpter adapter = new feedadpter(context,R.layout.singlefeed,feeds);
        listView.setAdapter(adapter);

        //get feedback count
        int countfeed = dBhandler.countfeed();
        count.setText("you have "+countfeed+" feedbacks");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,myfeedback.class));
            }
        });
        //new
        cdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,MainActivity.class));
            }
        });

        //alert box
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                feed feedc = feeds.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                //builder.setTitle(feedc.getFeedback());
                builder.setTitle("Feedback "+position++);
                //builder.setMessage("message");

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //////////
                        dBhandler.deletefeedback(feedc.getId());
                        startActivity(new Intent(context,feedhome.class));

                    }
                });

                builder.setNeutralButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(context,editfeedback.class);
                        intent.putExtra("id",String.valueOf(feedc.getId()));
                        startActivity(intent);

                        //startActivity(new Intent(context,editfeedback.class));

                    }
                });
                builder.show();

            }
        });



    }
}