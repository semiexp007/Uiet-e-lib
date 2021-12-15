package com.akmadheshiya.uietelib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
 Button mlogin;
 TextView forget,mcreate;
 EditText meamil,mpassword;
 FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mcreate=findViewById(R.id.create);
        forget=findViewById(R.id.ftgpassword);
        meamil=findViewById(R.id.email);
        mpassword=findViewById(R.id.password);
        mlogin=findViewById(R.id.login);

        auth=FirebaseAuth.getInstance();
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,forgotPassword.class));
            }
        });
        mcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,SignUp.class));

            }
        });
        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signInWithEmailAndPassword(meamil.getText().toString().trim(),mpassword.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                           startActivity(new Intent(Login.this,Home2.class));
                           finish();
                        }

                    }
                });
            }
        });

    }
}