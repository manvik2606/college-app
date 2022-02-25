package com.example.collegeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText lg_Username1,lg_Password1;
    Button login_btn;
    TextView sign_up;

    FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mauth = FirebaseAuth.getInstance();

        lg_Username1 = findViewById(R.id.login_username);
        lg_Password1 = findViewById(R.id.inputPassword1);

        login_btn = findViewById(R.id.login_btn);
        sign_up = findViewById(R.id.sign_up);



        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = lg_Username1.getText().toString().trim();
                String lo_password = lg_Password1.getText().toString().trim();


                if(!email.endsWith("charusat.edu.in")) {
                    lg_Username1.setError("please enter proper email");
                    lg_Username1.requestFocus();
                    return;
                }


                if (lo_password.isEmpty()) {
                    lg_Password1.setError("Password is required!");
                    lg_Password1.requestFocus();
                    return;
                }

                if (lo_password.length() < 6) {
                    lg_Password1.setError("Min password length should be 6 characters!");
                    lg_Password1.requestFocus();
                    return;
                }


                mauth.signInWithEmailAndPassword(email,lo_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(Login.this,MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Login.this,"Failed to login! Please check your credentials",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

}