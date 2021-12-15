package com.akmadheshiya.uietelib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Hashtable;
import java.util.Map;

public class SignUp extends AppCompatActivity {
   String cat;
   EditText mname,memail,mpassword,mroll;
   Button mcreate;
   FirebaseAuth  mauth;
   FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        memail=findViewById(R.id.cremail);
        mname=findViewById(R.id.crname);
        mpassword=findViewById(R.id.crpassword);
        mroll=findViewById(R.id.crroll);
        mcreate=findViewById(R.id.crcreate);
        mauth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        mcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=memail.getText().toString().trim();
                String password=mpassword.getText().toString().trim();
                if(password.length()<6 && !email.equals(null))
                {
                    Toast.makeText(SignUp.this, "Please enter a valid details and password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                  mauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                          if (task.isSuccessful())
                          { DatabaseReference reference=database.getReference().child("Users").child(cat);
                              String name=mname.getText().toString();
                              String roll=mroll.getText().toString();
                              Map<String,Object> mp= new Hashtable<>();
                              mp.put("Name",name);
                              mp.put("Roll",roll);
                              mp.put("ProfilePic","Default");
                              reference.child(mauth.getCurrentUser().getUid()).setValue(mp);
                              Toast.makeText(SignUp.this, "Account created", Toast.LENGTH_SHORT).show();
                              mname.setText("");
                              memail.setText("");
                              mpassword.setText("");
                              mroll.setText("");
                             
                          }

                      }
                  });
                }
            }
        });

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.MCA:
                if (checked)
                   cat="MCA";
                    // Pirates are the best
                    break;
            case R.id.BCA:
                if (checked)
                    cat="BCA";  // Ninjas rule
                    break;
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}