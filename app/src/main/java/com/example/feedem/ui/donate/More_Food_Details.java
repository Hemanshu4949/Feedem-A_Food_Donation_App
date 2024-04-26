package com.example.feedem.ui.donate;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;


import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.feedem.ModelClasses.Donation_Data;
import com.example.feedem.Firebase_DataEntry;
import com.example.feedem.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


public class More_Food_Details extends Fragment {

    View view;
    ImageButton location;
    TextInputEditText donationdetail , Address = null, Contact_no = null;

    Donation_Data adddata;
    MaterialButton Post ;


    public static More_Food_Details newInstance(String param1, String param2) {
        More_Food_Details fragment = new More_Food_Details();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_more__food__details, container, false);
        location = view.findViewById(R.id.location);
        Post = view.findViewById(R.id.Post);
        donationdetail = view.findViewById(R.id.DonationDetail);
        Address = view.findViewById(R.id.Address);
        Contact_no = view.findViewById(R.id.Contact_no);
        donationdetail.setText("Type Of Food : "+adddata.getType_OF_Food() +"\n"+"Pre Cooked Time : "+ + adddata.getFoodhours() +"\n"+"Servings : "+ + adddata.getServings());

        //location permissions
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissionLauncher.launch(ACCESS_FINE_LOCATION);
                requestPermissionLauncher.launch(ACCESS_COARSE_LOCATION);
            }
        });

        // data entry in database
        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Address.getText().toString() != null && Contact_no.getText().toString() != null )
                {
                    Donation_Data.setAddress(String.valueOf(Address.getText()));
                    Donation_Data.setContact(String.valueOf(Contact_no.getText()));
                    try {
                        Firebase_DataEntry dataEntry1 = new Firebase_DataEntry();
                        Toast.makeText(getContext() , "Entry Done Successfully" , Toast.LENGTH_SHORT).show();
                        NavController navController = Navigation.findNavController(view);
                        navController.navigate(R.id.navigation_home);

                    } catch (Exception e) {
                        Log.d("DataEntry Error", e.toString());
                        SharedPreferences credentials;
                        credentials = getContext().getSharedPreferences("crendentials" , getContext().MODE_PRIVATE);
                        String credential = credentials.getString("Userid" , null);
                        Log.d("mainactivity" , credential+"hi");
                    }
                }
                else {
                    Toast.makeText(getContext() , "Fill All Fields" , Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;


    }
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    if (ContextCompat.checkSelfPermission(getContext() , ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getContext() , ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED )
                    {
                        NavController navController = Navigation.findNavController(view);
                        MapsFragment.destroyindicater = false ;// Assuming cookedfood is a View within the fragment
                        navController.navigate(R.id.map);
                    }

                } else {
                    Toast.makeText(requireContext(), "Permission is required for usage of your current location", Toast.LENGTH_SHORT).show();
                }
            });

}