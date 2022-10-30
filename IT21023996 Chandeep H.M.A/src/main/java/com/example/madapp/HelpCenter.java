package com.example.madapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HelpCenter extends AppCompatActivity {

    private EditText msg;
    private Button btnHelp;
    private HelpmsgDbhandler helpmsgDbhandler;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
        msg = findViewById(R.id.msg);
        btnHelp = findViewById(R.id.btnHelp);

        context = this;
        helpmsgDbhandler = new HelpmsgDbhandler(context);

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hmsg = msg.getText().toString();
                MsgModel msgModel = new MsgModel(hmsg);
                helpmsgDbhandler.addMsg(msgModel);

                startActivity((new Intent(context,MainCard.class)));


            }
        });
    }
}