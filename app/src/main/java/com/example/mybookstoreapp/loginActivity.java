package com.example.mybookstoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mybookstoreapp.Admin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginActivity extends AppCompatActivity {
    EditText edUname,edPassword;
    Button btnLogin,btnClear,btnNewUser;
    String userName,userPassword;


    FirebaseDatabase fb;
    DatabaseReference mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUname=findViewById(R.id.editTextTextUserNme);
        edPassword=findViewById(R.id.editTextTextUPassword);
        btnLogin=findViewById(R.id.buttonLogin);
        btnClear=findViewById(R.id.buttonClear);
        btnNewUser=findViewById(R.id.buttonNewUser);
        fb=FirebaseDatabase.getInstance();
        mydb=fb.getReference("Users");

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUname.setText("");
                edPassword.setText("");
            }
        });

        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(loginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = edUname.getText().toString();
                userPassword = edPassword.getText().toString();

                if (userName.equals(""))
                    edUname.setError("Please Enter User Name");
                else if (userPassword.equals(""))
                    edPassword.setError("Please Enter Password");
                else {
                    mydb.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int flag = 0;
                            for (DataSnapshot ds : snapshot.getChildren())
                            {
                                MyUser u1 = ds.getValue(MyUser.class);
                                if (userName.equals(u1.username) && userPassword.equals(u1.password))
                                {
                                    Global.myUserName = userName;
                                    Toast.makeText(loginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(loginActivity.this,ProductActivity.class);
                                    startActivity(i);
                                    flag = 1;
                                    break;
                                }
                                else if (userName.equals("admin") && userPassword.equals("admin")){
                                    Intent i = new Intent(loginActivity.this, Admin.class);
                                    startActivity(i);
                                    flag = 1;
                                    break;
                                }
                            }
                            if (flag==0)
                            {
                                Toast.makeText(loginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });


    }
}
