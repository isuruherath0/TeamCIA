package com.example.madapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class add_card extends AppCompatActivity {

    private EditText number,name,cvc,exp;
    private Button add;
    private cardDbhandler carddbhandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        number = findViewById(R.id.editTextCardNum);
        name = findViewById(R.id.editTextName);
        cvc = findViewById(R.id.editTextCVC);
        exp = findViewById(R.id.editTextDate);

        add = findViewById(R.id.buttonAdd);
        context = this;
        carddbhandler = new cardDbhandler(context);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cNumber = number.getText().toString();
                String cName = name.getText().toString();
                String cCvc = cvc.getText().toString();
                String cExp = exp.getText().toString();

                cardModel cardmodel = new cardModel(cName,cNumber,cCvc,cExp);
                carddbhandler.addCard(cardmodel);

                startActivity((new Intent(context,MainCard.class)));


            }
        });
    }
}