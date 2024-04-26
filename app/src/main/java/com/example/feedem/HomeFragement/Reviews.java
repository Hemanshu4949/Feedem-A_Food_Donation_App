package com.example.feedem.HomeFragement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.feedem.ModelClasses.Reviews_Data;
import com.example.feedem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Reviews extends Fragment {

    FirebaseFirestore database;
    DocumentSnapshot Reviews ;

    public static Reviews newInstance() {
        Reviews fragment = new Reviews();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        ListView reviews;
        view = inflater.inflate(R.layout.fragment_reviews, container, false);
        reviews = view.findViewById(R.id.reviews);
        ArrayList<String> reviewlist = new ArrayList<>();
        ArrayList<Float> ratinglist = new ArrayList<>();


//        database = FirebaseFirestore.getInstance();
//        CollectionReference docref = database.collection("Reviews");
//        Log.d("errordataretrival" , "hello");
//        docref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
//        {
//
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                Log.d("errordataretrival" , "hello");
//                if (task.isSuccessful())
//                {
//                    Log.d("errordataretrival" , "by");
//                    for (QueryDocumentSnapshot reviews : task.getResult())
//                    {
//                        if (reviews.exists())
//                        {
//                            Object value = reviews.get("review");
//                            Object rating = reviews.getDouble("rating");
//                            reviewlist.add(value.toString());
//                            ratinglist.add(Float.parseFloat((String) rating));
//                            Log.d("databasevalues", value.toString());
//                        }
//                        else
//                        {
//                            Log.d("errordataretrival" , task.getException().toString());
//
//                        }
//
//                    }
//
//                }
//                else {
//                    Log.d("errordataretrival" , task.getException().toString());
//                }
//            }
//        });
        ReviewAdapter reviewAdapter = new ReviewAdapter(getContext() , Reviews_Data.getReview(), Reviews_Data.getStars());

        reviews.setAdapter(reviewAdapter);
        reviews.setClickable(false);
        return view;
    }
}