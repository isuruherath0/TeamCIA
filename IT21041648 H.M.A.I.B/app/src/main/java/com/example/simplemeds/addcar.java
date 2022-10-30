package com.example.simplemeds;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addcar extends AppCompatActivity {

    EditText type,brand,model,location,price,description;
    Button addcarbtn, back;
    DatabaseReference dbRef;
    users user;
    car car;
    long cID = 0;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcar);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Cars");
        addcarbtn = findViewById(R.id.addcarbtn);
        back = findViewById(R.id.back);
        type = findViewById(R.id.type);
        brand = findViewById(R.id.brand);
        model = findViewById(R.id.model);
        price = findViewById(R.id.price);
        description = findViewById(R.id.Description);
        location = findViewById(R.id.location);

        user = new users();
        car = new car();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    cID = (snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        addcarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( TextUtils.isEmpty(type.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter car type", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(brand.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter car brand", Toast.LENGTH_LONG).show();
                }
                else if( TextUtils.isEmpty(model.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter car model", Toast.LENGTH_LONG).show();
                }
                else if( TextUtils.isEmpty(location.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter price", Toast.LENGTH_LONG).show();
                }
                else if( TextUtils.isEmpty(price.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter Description", Toast.LENGTH_LONG).show();
                }
                else{

                    Intent userSession = getIntent();
                    String fn = userSession.getStringExtra("fn");




                    car.setType(type.getText().toString().trim());
                    car.setBrand(brand.getText().toString().trim());
                    car.setModel(model.getText().toString().trim());
                    car.setLocation(location.getText().toString().trim());
                    car.setPrice(price.getText().toString().trim());
                    car.setCurl(description.getText().toString().trim());
                    car.setUser(fn);
                    car.setcID(cID+1) ;
                    dbRef.child(location.getText().toString().trim()).setValue(car);
                    Intent intent = new Intent (addcar.this, managecarhome.class);
                    startActivity(intent);

                }

            }
        });
    }
}
