package com.example.feedem.HomeFragement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.feedem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class About extends Fragment {
View view;
    FirebaseFirestore database;
    DocumentSnapshot events ;
    TextView about;



    public static About newInstance() {
        About fragment = new About();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_about, container, false);
        about = view.findViewById(R.id.about);

//
        database = FirebaseFirestore.getInstance();
        CollectionReference docref = database.collection("About");
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
                            Object value = reviews.get("detail");
                            about.setText(value.toString());
                            Log.d("databasevalues", value.toString());
                        }
                        else
                        {
                            Log.d("errordataretrival" , task.getException().toString());

                        }

                    }

                }
                else {
                    Log.d("errordataretrival" , task.getException().toString());
                }
            }
        });
        return view;
    }
}