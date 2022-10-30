package com.example.weddingrides;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addreview extends AppCompatActivity {

    private EditText review;
    private Button add;
    private RBhandler rbhandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addreview);

        review = findViewById(R.id.plainReview);
        add = findViewById(R.id.btnreview);

        context = this;
        rbhandler = new RBhandler(context);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userev =  review.getText().toString();

                review ree = new review(userev);
                rbhandler.addReview(ree);

                startActivity(new Intent(context,MainActivity.class));
                Toast toast = Toast.makeText(getApplicationContext(),"Your Review added.",Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}