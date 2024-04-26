package com.example.feedem.ui.Credentials_chacking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.feedem.MainActivity;
import com.example.feedem.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class registration extends AppCompatActivity {
    SharedPreferences credential1;



    EditText editTextEmail , editTextPassword ;

    Button signIn , signUp ;

    MaterialButton googleAuth ;
    FirebaseAuth auth ;
    FirebaseDatabase database;
    GoogleSignInClient googleSignInClient ;
    GoogleSignInClient mGoogleSignInClient ;
    int RC_SIGN_IN = 20;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        // editable instace of shared preferance

        googleAuth = findViewById(R.id.googleConnect);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        googleAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                googleSignIn();
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());

                if (account != null)
                {
                    String email1 = account.getEmail();
//                            editor.putString("Userid" , email1.toString());
//                            editor.commit();
//                            credential = getSharedPreferences("crendentials" , MODE_PRIVATE);
//                            Log.d("emailgot" , credential.getString("Userid" , null));
//                            Log.d("newmethod",user1.getUid().toString() );
                }

            }
        });


        editTextEmail = findViewById(R.id.email_address);
        editTextPassword = findViewById(R.id.password);

        signIn = findViewById(R.id.login_btn);
        signUp = findViewById(R.id.btnsignup);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(registration.this , login.class);
                startActivity(intent);
                finish();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                String email , password ;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(registration.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(registration.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email , password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete (@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(registration.this, "Registration Successfully", Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor editor = credential1.edit();
                                    editor.putString("Userid" , email.toString());
                                    editor.commit();
                                    Intent intent = new Intent(registration.this , MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(registration.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }
        });
    }

    private void googleSignIn () {

        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {

                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuth(account.getIdToken());

            }
            catch (Exception e ){

                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void firebaseAuth (String idToken) {

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete (@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            FirebaseUser user = auth.getCurrentUser();

                            HashMap<String,Object> map= new HashMap<>();
                            map.put("id", user.getUid());
                            map.put("name", user.getDisplayName());
                            map.put("profile", user.getPhotoUrl().toString());

                            database.getReference().child("users").child(user.getUid()).setValue(map);
                            credential1 = getSharedPreferences("crendentials" , MODE_PRIVATE);
                            SharedPreferences.Editor editor = credential1.edit();
                            editor.putString("Userid" , user.getEmail().toString());
                            editor.commit();
                            credential1 = getSharedPreferences("crendentials" , MODE_PRIVATE);
                            Log.d("emailgot" , credential1.getString("Userid" , null));
                            Intent intent = new Intent(registration.this, MainActivity.class);
                            startActivity(intent);

                        }
                        else {

                            Toast.makeText(registration.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                        }


                    }
                });

    }

}