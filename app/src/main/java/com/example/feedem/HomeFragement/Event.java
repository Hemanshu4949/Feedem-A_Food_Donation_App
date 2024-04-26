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

import com.example.feedem.ModelClasses.Events_Data;
import com.example.feedem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;


public class Event extends Fragment {
    FirebaseFirestore database;
    DocumentSnapshot events ;

    public static Event newInstance() {
        Event fragment = new Event();
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        ArrayList<String> reviewlist = new ArrayList<>();
        ListView Events ;
// use this code in service and create a method for it then return arraylist and use arraylist in this event fragment
        //fetching data from databse
//        database = FirebaseFirestore.getInstance();
//        CollectionReference docref = database.collection("Event");
//        Log.d("errordataretrival" , "hello");
//        docref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
//                                           {
//
//                                               @Override
//                                               public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                                   Log.d("errordataretrival" , "hello");
//                                                   if (task.isSuccessful())
//                                                   {
//                                                       Log.d("errordataretrival" , "by");
//                                                       for (QueryDocumentSnapshot events : task.getResult())
//                                                       {
//                                                           if (events.exists())
//                                                           {
//                                                               Object value = events.get("event");
//                                                               reviewlist.add(value.toString());
//                                                               Log.d("databasevalues", value.toString());
//                                                           }
//                                                           else {
//                                                               Log.d("errordataretrival" , task.getException().toString());
//
//                                                           }
//
//                                                       }
//
//                                                   }
//                                                   else {
//                                                       Log.d("errordataretrival" , task.getException().toString());
//                                                   }
//                                               }
//                                           });


//        HashMap<String,Object> map= new HashMap<>();
//
//        map.put("id", user.getUid());
//        map.put("name", user.getDisplayName());
//        map.put("profile", user.getPhotoUrl().toString());


        ArrayAdapter listadapter  = new ArrayAdapter(getContext() , R.layout.listviewlayout , Events_Data.getEvents());
        Events = view.findViewById(R.id.Event);
        Events.setAdapter(listadapter);
        Events.setClickable(false);
        return view;
    }
}