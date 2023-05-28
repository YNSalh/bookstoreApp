package com.example.mybookstoreapp;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {
    ProgressBar pb;
    TextView percent;
    CountDownTimer ct;
    int progressStateuse=0;
    Handler handler =new Handler();
    @Override
    protected void onCreate(Bundle saveedInstanceState){
        super.onCreate(saveedInstanceState);
        setContentView(R.layout.activity_welcome);
        pb=findViewById(R.id.progressBar);
        percent=findViewById(R.id.textView29);

        pb.setProgress(100);
        new  Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStateuse<100){
                    progressStateuse+=5;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            pb.setProgress(progressStateuse);
                            percent.setText(progressStateuse+"%");


                        }

                    });
                    if ((progressStateuse>=100)){

                        Intent i=new Intent(Welcome.this,loginActivity.class);
                        startActivity(i);

                    }
                    try {
                        Thread.sleep(500);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

}
