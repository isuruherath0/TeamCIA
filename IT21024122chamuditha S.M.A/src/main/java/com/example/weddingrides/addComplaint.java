package com.example.weddingrides;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addComplaint extends AppCompatActivity {

    private EditText compla;
    private Button add;
    private cBhandler cbhandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_complaint);

        compla = findViewById(R.id.plincomp);
        add = findViewById(R.id.btncomp);

        context = this;
        cbhandler = new cBhandler(context);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usecomp =  compla.getText().toString();

                complain cee = new complain(usecomp);
                cbhandler.addComplaint(cee);

                startActivity(new Intent(context,MainActivity.class));
                Toast toast = Toast.makeText(getApplicationContext(),"Your complaint added.",Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}