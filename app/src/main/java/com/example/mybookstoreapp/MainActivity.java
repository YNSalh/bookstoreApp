package com.example.mybookstoreapp;
import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText uname,password,conpassword,phone;
    Button btnDOB,btnReg;
    RadioButton rbMale,rbFemale;
    CheckBox cbCond;
    String strUname,strPwd,strCPwd,strPhone,strDOB,strGender;
    Calendar calendar;
    int selYear;
    int flag=0;

    FirebaseDatabase fb;
    DatabaseReference mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname=findViewById(R.id.editTextTextUsername);
        password=findViewById(R.id.editTextTextPassword);
        conpassword=findViewById(R.id.editTextTextconfirmPassword);
        phone=findViewById(R.id.editTextTextPhoneNumber);
        btnDOB=findViewById(R.id.button);
        btnReg=findViewById(R.id.button2);
        rbFemale=findViewById(R.id.radioButton);
        rbMale=findViewById(R.id.radioButton2);
        cbCond=findViewById(R.id.checkBox);
        calendar=Calendar.getInstance();
        fb = FirebaseDatabase.getInstance();
        mydb = fb.getReference("Users");



        btnDOB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog dp = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        btnDOB.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        selYear=year;
                    }
                }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(calendar.DAY_OF_MONTH));
                dp.show();
            }
        });




        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strUname = uname.getText().toString();
                strPwd = password.getText().toString();
                strCPwd = conpassword.getText().toString();
                strDOB = btnDOB.getText().toString();
                strPhone = phone.getText().toString();

                if (rbMale.isChecked())
                    strGender="Male";
                else if (rbFemale.isChecked())
                    strGender="Female";

                if (strUname.equals(""))
                    uname.setError("Please Enter User Name");
                else if (strPwd.equals(""))
                    password.setError("Please Enter Password");
                else if (strCPwd.equals(""))
                    conpassword.setError("Please Enter Confirm Password");
                else if (!strPwd.equals(strCPwd))
                    conpassword.setError("Please Password and Confirm Password should be Same");
                else if (!rbMale.isChecked() && !rbFemale.isChecked())
                    Toast.makeText(MainActivity.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
                else if (strDOB.equalsIgnoreCase("select"))
                    Toast.makeText(MainActivity.this, "Please Select Date of Birth", Toast.LENGTH_SHORT).show();
                else if ((calendar.get(Calendar.YEAR)-selYear)<18)
                    Toast.makeText(MainActivity.this, "Sorry, You are under age", Toast.LENGTH_SHORT).show();
                else if (strPhone.equals(""))
                    phone.setError("Please Enter Phone Number");
                else if (strPhone.length()!=8)
                    phone.setError("Phone Number should be 8 digits");
                else if (!strPhone.startsWith("7")&& !strPhone.startsWith("9"))
                    phone.setError("Please Phone Number should Start with 7 or 9");
                else if (!cbCond.isChecked())
                    Toast.makeText(MainActivity.this, "Please Accept Terms and Conditions", Toast.LENGTH_SHORT).show();
                else {
                    mydb.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            flag=0;
                            for (DataSnapshot ds: snapshot.getChildren())
                            {
                                MyUser u1 = ds.getValue(MyUser.class);
                                if (u1.username.equalsIgnoreCase(uname.getText().toString()))
                                {
                                    flag=1;
                                    break;
                                }
                            }
                            if (flag==1)
                                Toast.makeText(MainActivity.this, "User Already Exists !!!", Toast.LENGTH_SHORT).show();

                            else if (flag==0)
                            {
                                MyUser u2 = new MyUser(strUname,strPwd,strGender,strDOB,strPhone);
                                String key = mydb.push().getKey();
                                mydb.child(key).setValue(u2);
                                Toast.makeText(MainActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(MainActivity.this,loginActivity.class);
                                startActivity(i);
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
