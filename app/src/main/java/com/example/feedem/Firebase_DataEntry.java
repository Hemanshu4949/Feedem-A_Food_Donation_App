package com.example.feedem;// Import necessary libraries
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.feedem.ModelClasses.Donation_Data;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Firebase_DataEntry {
    Context context ;
    public Firebase_DataEntry() {

        Log.d("inside database", "yes");
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Month is 0-based
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

// You can format the date and time manually or use SimpleDateFormat as before

        String formattedDateTime = year + "/" + month + "/" + day + " " + hour + ":" + minute + ":" + second;
        Log.d("Current Date & Time", formattedDateTime);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String collectionName = "users";
        Log.d("inside database", "yes");
        GeoPoint location = new GeoPoint(Donation_Data.getLatitude() , Donation_Data.getLongitude());
        Map<String, Object> data = new HashMap<>();
        data.put("UID" , Donation_Data.getUid());
        data.put("Food_Categorie" , Donation_Data.getFood_Categorie());
        data.put("Type_Of_Food", Donation_Data.getType_OF_Food());
        data.put("Servings", Donation_Data.getServings());
        data.put("Food_Hours", Donation_Data.getFoodhours());
        data.put("Location", location);
        data.put("Address", Donation_Data.getAddress());
        data.put("Contact", Donation_Data.getContact());
        data.put("timestamp" ,formattedDateTime );
        data.put("Status" , "Pending");


        db.collection("Donation_Data").document()
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error writing document", e);
                    }
                });

    }
}