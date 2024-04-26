package com.example.feedem.Service;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.feedem.ModelClasses.Donation_Data;
import com.example.feedem.ModelClasses.Events_Data;
import com.example.feedem.ModelClasses.Ngo_Details;
import com.example.feedem.ModelClasses.Profile_Data;
import com.example.feedem.ModelClasses.Reviews_Data;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Ngo_Data_Retrival();
        NgoretrieveImage("Ngopicture.jpg");
        event_data_retrival();
        review_data_retrival();




    }

    public void event_data_retrival()
    {
        FirebaseFirestore database;
        database = FirebaseFirestore.getInstance();
        CollectionReference docref = database.collection("Event");
        Log.d("errordataretrival" , "hello");
        docref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.d("errordataretrival" , "hello");
                if (task.isSuccessful())
                {
                    Log.d("errordataretrival" , "by");
                    for (QueryDocumentSnapshot events : task.getResult())
                    {
                        if (events.exists())
                        {
                            Object value = events.get("event");
                            Events_Data.addevent((value.toString()));
                            Log.d("databasevalues", value.toString());
                        }
                        else {
                            Log.d("errordataretrival" , task.getException().toString());

                        }
                    }
                }
                else {
                    Log.d("errordataretrival" , task.getException().toString());
                }
            }
        });

    }
    public void review_data_retrival()
    {

        FirebaseFirestore database;
        database = FirebaseFirestore.getInstance();
        CollectionReference docref = database.collection("Review");
        Log.d("errordataretrival" , "hello");
        docref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.d("errordataretrival" , "hello");
                if (task.isSuccessful())
                {
                    Log.d("errordataretrival" , "by");
                    for (QueryDocumentSnapshot reviews : task.getResult())
                    {
                        if (reviews.exists())
                        {
                            Object value = reviews.get("review");
                            Object rating = reviews.getDouble("stars");
                            Reviews_Data.addreview(value.toString() , Float.parseFloat(rating.toString()) );
                            Log.d("databasevalues", value.toString());
                        }
                        else
                        {
                            Log.d("errordataretrivalofreview" , task.getException().toString());

                        }
                    }
                }
                else {
                    Log.d("errordataretrivalofreview" , task.getException().toString());
                }
            }
        });
    }




    private void NgoretrieveImage(String imagePath) {
        StorageReference storageRef ;
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        StorageReference imageRef = storageRef.child(imagePath);
        final long ONE_MEGABYTE = 1024 * 1024*5;
        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(bytes -> {
            // Successfully downloaded image data
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            Ngo_Details.setImage(bitmap);
        }).addOnFailureListener(exception -> {
            Log.w("Image Retrieval", "Error downloading image", exception);
        });
    }

    public void Ngo_Data_Retrival()
    {

        FirebaseFirestore database;
        database = FirebaseFirestore.getInstance();
        CollectionReference docref = database.collection("Ngo_Data");
        Log.d("errordataretrival" , "hello");
        docref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot data : task.getResult()) {

                    Ngo_Details.setName(data.get("Name").toString());
                    Ngo_Details.setFeeds(data.get("Feeds").toString());
                    Ngo_Details.setCampaign(data.get("Campaign").toString());
                    Ngo_Details.setReviews(data.get("Reviews").toString());
                    Ngo_Details.setVolunteers(data.get("Volunteers").toString());


                }
            }
        });



    }
}