package com.example.feedem.ui.Credentials_chacking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.feedem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class credential_removal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {


            SharedPreferences credentials;
            credentials = getSharedPreferences("crendentials", MODE_PRIVATE);
            String credential = credentials.getString("Userid", null);
            SharedPreferences.Editor logout = credentials.edit();
            logout.remove("Userid").apply();
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = mAuth.getCurrentUser();

            if (user != null) {
                // User is signed in
                // Access user information (limited based on sign-in method)
                String uid = user.getUid();
                String email = user.getEmail();
                Log.d("signoutuser" , uid + email);
            } else {
                // User is signed out
                // Handle sign-out or redirect to login
                Log.d("signedoutuser" , "false");
            }

            Intent intent = new Intent(getApplicationContext(), login.class);
            startActivity(intent);
        }
        catch (Exception e)
        {
         System.out.println(e.toString());
        }
    }
}