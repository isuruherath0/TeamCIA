package com.example.weddingrides;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class myfeedback extends AppCompatActivity {

    private EditText feedbac;
    private Button add;
    private DBhandler dbhandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfeedback);

        feedbac = findViewById(R.id.painfeed);
        add = findViewById(R.id.addfeed);

        context = this;
        dbhandler = new DBhandler(context);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usefeeedbac =  feedbac.getText().toString();

                feed fee = new feed(usefeeedbac);
                dbhandler.myfeedback(fee);

                startActivity(new Intent(context,feedhome.class));
                Toast toast = Toast.makeText(getApplicationContext(),"Your Feedback added.",Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}