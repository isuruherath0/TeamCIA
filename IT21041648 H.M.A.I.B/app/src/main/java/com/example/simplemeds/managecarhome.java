package com.example.simplemeds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class managecarhome extends AppCompatActivity {

    Button  addbtn, managebtn;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.managecarhome);
        addbtn= findViewById(R.id.addcar);
        managebtn = findViewById(R.id.managecar);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent userSession = getIntent();
                String fn = userSession.getStringExtra("fn");

                Intent intent = new Intent(managecarhome.this, addcar.class);
                intent.putExtra("fn", fn);
                startActivity(intent);




            }


        });

        managebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent userSession = getIntent();
                String fn = userSession.getStringExtra("fn");

                Intent intent = new Intent(managecarhome.this, managecar.class);
                intent.putExtra("fn", fn);
                startActivity(intent);


            }


        });
    }



}
