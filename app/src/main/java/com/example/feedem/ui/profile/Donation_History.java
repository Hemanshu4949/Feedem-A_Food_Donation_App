package com.example.feedem.ui.profile;

import android.location.Address;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.feedem.ModelClasses.Donation_Data;
import com.example.feedem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class Donation_History extends Fragment {

RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_donation__history, container, false);
        ArrayList<String> timestamp = new ArrayList<>();
        ArrayList<String> status = new ArrayList<>();
        ArrayList<String> contact = new ArrayList<>();
        ArrayList<String> Food_Categories = new ArrayList<>();
        ArrayList<Long> Food_Hours = new ArrayList<>();
        ArrayList<GeoPoint> Locaiton = new ArrayList<>();
        ArrayList<Long> Servings = new ArrayList<>();
        ArrayList<String> Type_Of_Food = new ArrayList<>();
        ArrayList<String> address = new ArrayList<>();


        recyclerView = view.findViewById(R.id.donationhistory);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        FirebaseFirestore database;
        database = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = database.collection("Donation_Data");
        Task<QuerySnapshot> query = collectionReference.orderBy("UID").whereEqualTo("UID" , Donation_Data.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult())
                    {
                        Log.d("errordataretrival" , "by");
                        timestamp.add((String) documentSnapshot.get("timestamp"));
                        status.add((String) documentSnapshot.get("Status"));
                        contact.add((String) documentSnapshot.get("Contact"));
                        address.add((String) documentSnapshot.get("Address"));
                        Locaiton.add((GeoPoint) documentSnapshot.get("Location"));
                        Food_Hours.add((Long) documentSnapshot.get("Food_Hours"));
                        Servings.add((Long) documentSnapshot.get("Servings"));
                        Type_Of_Food.add((String) documentSnapshot.get("Type_Of_Food"));
                        Food_Categories.add((String) documentSnapshot.get("Food_Categorie"));
                        Log.d("retriveddonationdata" , String.valueOf(Locaiton.get(0)));

                    }
                }

                recyclerView.setAdapter(new Recycle_view_adapter(timestamp , status , contact , Food_Categories , Food_Hours , Locaiton , Servings , Type_Of_Food , address));
            }
        });
        return view;
    }

}