package com.example.mybookstoreapp;

import android.content.Intent;
import android.os.Bundle;
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

public class AdminSearch extends AppCompatActivity {
    EditText pid,pname,price,pquantity;
    Button btnSearch,btnAdmin;
    FirebaseDatabase fb;
    DatabaseReference mydb;


    @Override
    protected  void  onCreate(Bundle saveInstancesState){
        super.onCreate(saveInstancesState);
        setContentView(R.layout.activit_admin_search);
        pid=findViewById(R.id.editTextText);
        pname=findViewById(R.id.editTextText3);
        price=findViewById(R.id.editTextText4);
        pquantity=findViewById(R.id.editTextText5);
        btnSearch=findViewById(R.id.button4);
        btnAdmin=findViewById(R.id.button6);
        fb=FirebaseDatabase.getInstance();
        mydb=fb.getReference("product");

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pid.getText().toString().isEmpty())
                    pid.setError("Enter Product ID");
                else {
                    mydb.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int flag=0;
                            for (DataSnapshot ds:snapshot.getChildren()){
                                MyProduct p=ds.getValue(MyProduct.class);
                                if ((pid.getText().toString().equals(p.pid))){
                                    pname.setText(p.pname);
                                    price.setText(p.price);
                                    pquantity.setText(p.pquantity);
                                    flag=1;
                                }

                            }
                            if (flag==0)
                                Toast.makeText(AdminSearch.this, "The Product ID Not Found...", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(AdminSearch.this, "The Product ID Founded...", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        btnAdmin.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void  onClick(View v){
                Intent i=new Intent(AdminSearch.this,Admin.class);
                startActivity(i);
                
            }
        });

    }


}
