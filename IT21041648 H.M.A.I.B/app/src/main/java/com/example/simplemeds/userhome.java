package com.example.simplemeds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class userhome extends AppCompatActivity {

    Button shop, prof, logoutbtn, managebtn;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userhome);

        shop = findViewById(R.id.addcar);
        prof = findViewById(R.id.prof);
        logoutbtn = findViewById(R.id.logoutbtn2);
        managebtn = findViewById(R.id.managecar);
        dbRef = FirebaseDatabase.getInstance().getReference().child("Users");

        managebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent userSession = getIntent();
                String fn = userSession.getStringExtra("fn");

                Intent intent1 = new Intent(userhome.this, managecarhome.class);
                intent1.putExtra("fn", fn);
                startActivity(intent1);
            }
        });
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userhome.this, home.class);
                startActivity(intent);
            }
        });

        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent userSession = getIntent();
                String fn = userSession.getStringExtra("fn");

                Intent intent1 = new Intent(userhome.this, profile.class);
                intent1.putExtra("fn", fn);
                startActivity(intent1);

            }
        });

    }
}
