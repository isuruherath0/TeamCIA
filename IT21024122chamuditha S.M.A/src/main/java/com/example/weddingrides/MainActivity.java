package com.example.weddingrides;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button conbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conbtn = findViewById(R.id.btncon);
        conbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,contactUs.class);
                startActivity(intent);
            }
        });

        conbtn=findViewById(R.id.btncom);
        conbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent comten = new Intent(MainActivity.this,addComplaint.class);
                startActivity(comten);
            }
        });

        conbtn=findViewById(R.id.btnrev);
        conbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rew = new Intent(MainActivity.this,addreview.class);
                startActivity(rew);
            }
        });

        conbtn=findViewById(R.id.btnfeed);
        conbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent feed = new Intent(MainActivity.this,feedhome.class);
                startActivity(feed);
            }
        });

    }
}