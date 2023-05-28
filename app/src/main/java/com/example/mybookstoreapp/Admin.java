package com.example.mybookstoreapp;
import androidx.appcompat.app.AppCompatActivity;

import  android.content.Intent;
import  android.os.Bundle;
import  android.view.View;
import  android.widget.Button;


public class Admin extends AppCompatActivity {
    Button btnUplode ,btnSearch,btnDelete,btnLogout;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_admin);
        btnUplode=findViewById(R.id.buttonUPLODE);
        btnSearch=findViewById(R.id.SERCH);
        btnDelete=findViewById(R.id.buttonDELETE);
        btnLogout=findViewById(R.id.button8);

        btnUplode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Admin.this,AdminUplode.class);
                startActivity(i);

            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Admin.this,AdminSearch.class);
                startActivity(i);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Admin.this,AdminDelete.class);
                startActivity(i);

            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Admin.this, loginActivity.class);
                startActivity(i);

            }
        });

    }
}
