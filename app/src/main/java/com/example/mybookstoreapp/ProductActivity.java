package com.example.mybookstoreapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import  java.util.ArrayList;


public class ProductActivity extends AppCompatActivity {
    RecyclerView rview;
    ArrayList<MyProduct> alist;
    ItemArrayAdapter  iadapter;
    FirebaseDatabase fb;
    DatabaseReference mydp;
    @Override
    protected  void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        rview=findViewById(R.id.recyclerView3);
        alist=new ArrayList<MyProduct>();
        fb=FirebaseDatabase.getInstance();
        mydp=fb.getReference("product");

        mydp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    MyProduct p1=ds.getValue(MyProduct.class);
                    alist.add(new MyProduct(p1.pid, p1.pname, p1.pquantity, p1.price));

                } iadapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        LinearLayoutManager lm= new LinearLayoutManager(this);
        rview.setLayoutManager(lm);
        iadapter=new  ItemArrayAdapter(alist);
        rview.setAdapter(iadapter);



    }
}
