package com.example.mybookstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminDelete extends AppCompatActivity {
    EditText pid;
    Button btnDelete, btnAdmin;
    FirebaseDatabase fb;
    DatabaseReference myfb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete);
        pid = findViewById(R.id.editTextTextProductId);
        btnDelete = findViewById(R.id.button3);
        btnAdmin = findViewById(R.id.button5);
        fb = FirebaseDatabase.getInstance();
        myfb = fb.getReference("product");

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pid.getText().toString().isEmpty())
                    pid.setError("Enter Product ID");
                else {
                    myfb.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int flag = 0;
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                MyProduct p = ds.getValue(MyProduct.class);
                                if (pid.getText().toString().equals(p.pid)) {
                                    ds.getRef().removeValue();
                                    Toast.makeText(AdminDelete.this, "Product Deleted...", Toast.LENGTH_SHORT).show();
                                    flag = 1;
                                    break;
                                }
                            }
                            if (flag == 0) {
                                Toast.makeText(AdminDelete.this, "Product ID NOT Found", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminDelete.this, Admin.class);
                startActivity(i);
            }
        });
    }
}