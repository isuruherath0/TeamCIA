package com.example.madapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class editCart extends AppCompatActivity {

    private EditText cNumber,cName,cCVC,cExp;
    private Button editButton;
    private cardDbhandler db;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cart);

        context = this;
        db = new cardDbhandler(context);

        cNumber = findViewById(R.id.editEditTextCardNum);
        cName = findViewById(R.id.editEditTextName);
        cCVC = findViewById(R.id.editEditTextCVC);
        cExp = findViewById(R.id.editEditTextDate);
        editButton = findViewById(R.id.buttonEdit);

        final String id = getIntent().getStringExtra("id");
        cardModel cm = db.getSingleCard(Integer.parseInt(id));

        cNumber.setText(cm.getcNumber());
        cName.setText(cm.getcName());
        cExp.setText(cm.getcExp());
        cCVC.setText(cm.getcCVC());
        System.out.println(id);
        editButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String number = cNumber.getText().toString();
                String name = cName.getText().toString();
                String cvc = cCVC.getText().toString();
                String exp = cExp.getText().toString();

                cardModel cm = new cardModel(Integer.parseInt(id),name,number,cvc,exp);
                int state = db.updatesinglecard(cm);
                startActivity(new Intent(context,MainCard.class));
            }
        });


    }
}