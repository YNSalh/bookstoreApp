package com.example.mybookstoreapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Key;

public class OrderActivity extends AppCompatActivity {
    TextView txtpid,txtname,txtprice,txtavaQty,txttotal;
    EditText edQty;
    Button btnOrder;
    int reqQty;
    double Total;
    FirebaseDatabase fb;
    DatabaseReference mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        txtpid=findViewById(R.id.textView18);
        txtname=findViewById(R.id.textView22);
        txtprice=findViewById(R.id.textView24);
        txtavaQty=findViewById(R.id.textView26);
        edQty=findViewById(R.id.editTextText6);
        btnOrder=findViewById(R.id.button7);
        fb=FirebaseDatabase.getInstance();
        mydb=fb.getReference("order");

        Bundle b = getIntent().getExtras();
        txtpid.setText(b.getString("productID"));
        txtname.setText(b.getString("productname"));
        txtprice.setText(b.getString("productprice"));
        txtavaQty.setText(b.getString("productavailable"));

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reqQty = Integer.parseInt(edQty.getText().toString());
                if (!(reqQty>0 && reqQty<=Integer.parseInt(txtavaQty.getText().toString())))
                {
                    edQty.setError("The Required Quantity should be more than or equal to the Available Quantity");
                }
                else {
                    Total = reqQty * Double.parseDouble(txtprice.getText().toString());

                    AlertDialog.Builder adb = new AlertDialog.Builder(OrderActivity.this);
                    adb.setTitle("Customer Order");
                    adb.setMessage("Do you want to Process This Order ? "+"      Total Coast = "+Total+" OMR");


                    adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Total = reqQty * Double.parseDouble(txtprice.getText().toString());
                            MyOrder order = new MyOrder(txtpid.getText().toString(),edQty.getText().toString(),String.valueOf(Total),Global.myUserName);
                            String key = mydb.push().getKey();
                            mydb.child(key).setValue(order);
                            // Toast.makeText(OrderActivity.this, "Total C", Toast.LENGTH_SHORT).show();

                            NotificationCompat.Builder nb = new NotificationCompat.Builder(OrderActivity.this,"101");
                            nb.setContentTitle("Customer Order");
                            nb.setContentText("Dear Customer "+Global.myUserName+" ,Your Order is Processing");
                            nb.setSmallIcon(R.drawable.baseline_shopping_cart_24);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                            {
                                NotificationChannel nc = new NotificationChannel("101","mychannel", NotificationManager.IMPORTANCE_DEFAULT);
                                NotificationManager nm = getSystemService(NotificationManager.class);
                                nm.createNotificationChannel(nc);
                                nm.notify(10002,nb.build());
                            }
                            else {
                                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                nm.notify(10002,nb.build());
                            }

                        }
                    });

                    adb.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    adb.show();
                }

            }
        });
    }
}