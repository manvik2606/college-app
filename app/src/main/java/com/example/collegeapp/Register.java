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

import com.example.collegeapp.Model.Model_Registration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText rg_Username,rg_Email,rg_Password,rg_ConformPassword;
    Button register_btn;
    TextView sign_in;

    FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mauth =  FirebaseAuth.getInstance();

        rg_Username = findViewById(R.id.inputUsername);
        rg_Email = findViewById(R.id.inputEmail);
        rg_Password = findViewById(R.id.inputPassword);
        rg_ConformPassword = findViewById(R.id.inputConformPassword);
        register_btn = findViewById(R.id.register_btn);
        sign_in = findViewById(R.id.sign_in);


        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String u_name = rg_Username.getText().toString().trim();
                String user_email = rg_Email.getText().toString().trim();
                String user_password = rg_Password.getText().toString().trim();


                if(u_name.isEmpty()){
                    rg_Username.setError("Please enter your name");
                    rg_Username.requestFocus();
                    return;
                }

                if(!user_email.endsWith("charusat.edu.in")) {
                    rg_Email.setError("please enter proper email");
                    rg_Email.requestFocus();
                    return;
                }

                if (user_password.length()>6 || user_password.isEmpty()){
                 rg_Password.setError("Password must be at least 6 characters");
                 rg_Password.requestFocus();
                 return;
                }

                if (!rg_Password.getText().toString().equals(rg_ConformPassword.getText().toString())){
                    rg_ConformPassword.setError("password does not match");
                    rg_ConformPassword.requestFocus();
                    return;
                }

                mauth.createUserWithEmailAndPassword(user_email, user_password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Model_Registration model_registration = new Model_Registration(u_name,user_email);

                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(model_registration).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(Register.this, "User has been registered succesfully", Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(Register.this,Login.class);
                                                startActivity(intent);
                                            } else {
                                                Toast.makeText(Register.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();

                                            }
                                        }
                                    });

                                }else{

                                    Toast.makeText(Register.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();

                                }
                            }
                        });

            }
        });

    }
}