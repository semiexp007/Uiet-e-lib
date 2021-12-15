package com.akmadheshiya.uietelib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
   FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        final int splashTimeOut = 500;

        Thread splashThread = new Thread(){
            int wait = 0;
            @Override
            public void run() {
                try {
                    super.run();
                    while(wait < splashTimeOut){
                        sleep(200);
                        wait += 10;
                    }
                } catch (Exception e) {
                }finally{

                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                    Intent intent;
                    if(auth.getCurrentUser().equals(null))
                    intent=new Intent(MainActivity.this,Login.class);
                    else
                        intent=new Intent(MainActivity.this,Home2.class);

                    startActivity(intent);
                    finish();
                }
            }
        };
        splashThread.start();
    }
}