package com.example.feedem.Service;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.feedem.ModelClasses.Donation_Data;
import com.example.feedem.ModelClasses.Profile_Data;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Profile_retrival extends Service {
    public Profile_retrival() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        profile_data_retrival();
        retrieveImage("profile_images/" + Donation_Data.getUid()+".jpg");



    }
    public void profile_data_retrival()
    {

        FirebaseFirestore database;
        database = FirebaseFirestore.getInstance();
        DocumentReference docref = database.collection("Profile").document(Donation_Data.getUid());
        docref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot profile_data = task.getResult();
                if (profile_data.exists())
                {
                    Profile_Data.setName(profile_data.getString("name"));
                    Profile_Data.setEmail(profile_data.getString("email"));
                    Profile_Data.setContact_no(profile_data.getString(  "Contact_no"));
                    Profile_Data.setAge(profile_data.getString("age"));
                }
            }
        });
        Log.d("errordataretrival" , "hello");
    }

    private void retrieveImage(String imagePath) {
        StorageReference storageRef ;
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        StorageReference imageRef = storageRef.child(imagePath);
        final long ONE_MEGABYTE = 1024 * 1024*5;
        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(bytes -> {
            // Successfully downloaded image data
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            Profile_Data.setImage(bitmap);
        }).addOnFailureListener(exception -> {
            Log.w("Image Retrieval", "Error downloading image", exception);
        });
    }
}