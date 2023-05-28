package com.example.mybookstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminUplode extends AppCompatActivity {
    EditText pid,pname,price,pquantity;
    Button btnUplode,btnAdmin;
    FirebaseDatabase fb;
    DatabaseReference mydp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_uplode);
        pid=findViewById(R.id.editTextTextPId);
        pname=findViewById(R.id.editTextTextPName);
        price=findViewById(R.id.editTextTextPrice);
        pquantity=findViewById(R.id.editTextTextPQuantity);
        btnUplode=findViewById(R.id.buttonUplode);
        btnAdmin=findViewById(R.id.buttonAdmin);

        fb=FirebaseDatabase.getInstance();
        mydp=fb.getReference("product");

        btnUplode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pid.equals(""))
                    pid.setError("Plese Enter Proudect ID");
                else if(pname.equals(""))
                    pname.setError("Plese Enter Proudect Name");
                else if (price.equals(""))
                    price.setError("Plese Enter Proudect Price");
                else if (pquantity.equals(""))
                    pquantity.setError("Plese Enter Proudect Quantity");
                else {
                    MyProduct p1=new MyProduct(pid.getText().toString(),pname.getText().toString(),pquantity.getText().toString(),price.getText().toString());
                    String key=mydp.push().getKey();
                    mydp.child(key).setValue(p1);
                    Toast.makeText(AdminUplode.this,"Proudect Uploded...",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminUplode.this,Admin.class);
                startActivity(i);


            }
        });
    }
}
