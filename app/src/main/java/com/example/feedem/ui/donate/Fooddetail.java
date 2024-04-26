package com.example.feedem.ui.donate;

import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.example.feedem.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.Slider;


public class Fooddetail extends Fragment {
    ImageButton breakfast , lunch , dinner ;

    Slider  servings , hours ;

    MaterialButton moredetails;
    static int  ServingValue  = 0 , foodhourvalue = 0 ;

   static String  Type_of_food = "Any" ;

    Donation_Data adddata;


    public static Fooddetail newInstance(String param1, String param2) {
        Fooddetail fragment = new Fooddetail();
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
        final Boolean[] breakfastflag = {false};
        final Boolean[] lunchflag = {false};
        final Boolean[] dinnerflag = {false};

        view =  inflater.inflate(R.layout.fragment_food_detail, container, false);
        breakfast = view.findViewById(R.id.breakfastbutton);
        lunch = view.findViewById(R.id.LunchButton);
        dinner = view.findViewById(R.id.DinnerButton);
        servings = view.findViewById(R.id.servings);
        hours = view.findViewById(R.id.foodhours);
        moredetails = view.findViewById(R.id.moredetailpage);



        breakfast.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                breakfastflag[0] = true;
                breakfastcolor(breakfastflag[0]);
                lunchflag[0] = false;
                dinnerflag[0] = false;
                lunchcolor(lunchflag[0]);
                dinnercolor(dinnerflag[0]);
                Type_of_food = "BreakFast";
                Log.d("type of food" , Type_of_food);

            }
        });

        lunch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                lunchflag[0] = true;
                lunchcolor(lunchflag[0]);
                breakfastflag[0] = false;
                dinnerflag[0] = false;
                breakfastcolor(breakfastflag[0]);
                dinnercolor(dinnerflag[0]);
                Type_of_food = "Lunch";
                Log.d("type of food" , Type_of_food);

            }
        });

        dinner.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dinnerflag[0] = true;
                dinnercolor(dinnerflag[0]);
                lunchflag[0] = false;
                breakfastflag[0] = false;
                lunchcolor(lunchflag[0]);
                breakfastcolor(breakfastflag[0]);
                Type_of_food = "Dinner";
                Log.d("type of food" , Type_of_food);
            }
        });
        servings.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }
            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                ServingValue = (int) servings.getValue();
                Log.d("value" , String.valueOf(ServingValue));
            }
        });
        hours.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {
            }
            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                foodhourvalue = (int) hours.getValue();
                Log.d("value" , String.valueOf(foodhourvalue));
            }
        });
//        servings.setOnCapturedPointerListener(new View.OnCapturedPointerListener() {
//            @Override
//            public boolean onCapturedPointer(View view, MotionEvent event) {
//                ServingValue = (int) servings.getValue();
//                Log.d("value" , String.valueOf(ServingValue));
//                return true;
//            }
//
//        });
//        hours.setOnCapturedPointerListener(new View.OnCapturedPointerListener() {
//            @Override
//            public boolean onCapturedPointer(View view, MotionEvent event) {
//                foodhourvalue = (int) hours.getValue();
//                Log.d("value" , String.valueOf(ServingValue));
//                return true;
//            }
//
//        });



        moredetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("daata", "onClick: "+ServingValue + foodhourvalue);
                if (Type_of_food != "Any" && ServingValue != 0 && foodhourvalue != 0)
                {
                    adddata.setType_Of_Food(Type_of_food);
                    adddata.setServings(ServingValue);
                    adddata.setFoodhours(foodhourvalue);
                    Log.d("daata", "onClick: "+adddata.getServings() + adddata.getFoodhours());
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.Moredetails);
                }
                else
                {
                    Toast.makeText(getContext() , "Fill All The Fields" , Toast.LENGTH_LONG).show();
                }

            }
        });





        return view;
    }

    public void breakfastcolor(Boolean flag)
    {
        if (flag == true) {
            breakfast.setImageResource(R.drawable.darkbreakfast);
        }
        else
        {
            breakfast.setImageResource(R.drawable.breakfast);

        }
    }
    public void lunchcolor(Boolean flag)
    {
        if (flag == true) {
            lunch.setImageResource(R.drawable.darklunch);
        }
        else
        {
            lunch.setImageResource(R.drawable.lunch);

        }
    }
    public void dinnercolor(Boolean flag)
    {
        if (flag == true) {
            dinner.setImageResource(R.drawable.darkdinner);
        }
        else
        {
            dinner.setImageResource(R.drawable.dinner);

        }
    }
}