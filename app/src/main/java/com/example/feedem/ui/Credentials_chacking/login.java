package com.example.feedem.ui.Credentials_chacking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.feedem.MainActivity;
import com.example.feedem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

SharedPreferences credential;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        EditText editTextEmail , editTextPassword ;

        Button signIn , signUp ;

        credential = getSharedPreferences("crendentials" , MODE_PRIVATE);
        // editable instace of shared preferance
        SharedPreferences.Editor editor = credential.edit();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.edittext_email);
        editTextPassword = findViewById(R.id.Password);

        signIn = findViewById(R.id.login);
        signUp = findViewById(R.id.registration_page);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(login.this , registration.class);
                startActivity(intent);
                finish();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                String email , password ;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(login.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete (@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    editor.putString("Userid" , email);
                                    editor.commit();
                                    Log.d("login" , email);
                                    Intent intent = new Intent(login.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(login.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}