package com.example.weddingrides;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editfeedback extends AppCompatActivity {

    private EditText feedtex;
    private Button edit;
    DBhandler dBhandler;
    Context context;
    //Long updateDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editfeedback);

        context = this;
        dBhandler = new DBhandler(context);

        feedtex = findViewById(R.id.txtedtfeed);
        edit = findViewById(R.id.btnfeededt);

        final String id = getIntent().getStringExtra("id");
        feed FEEd =  dBhandler.getSingleFeedback(Integer.parseInt(id));

        feedtex.setText(FEEd.getFeedback());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedfTitle = feedtex.getText().toString();

                feed Feed = new feed(Integer.parseInt(id),feedfTitle);
                int state = dBhandler.updateSingleFeed(Feed);

                startActivity(new Intent(context,feedhome.class));
                Toast toast = Toast.makeText(getApplicationContext(),"Your Feedback edited.",Toast.LENGTH_LONG);
                toast.show();

            }
        });
    }
}