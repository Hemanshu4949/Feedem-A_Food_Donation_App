package com.example.feedem.HomeFragement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.feedem.R;

import java.util.ArrayList;

public class ReviewAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> reviews = new ArrayList<String>();
    ArrayList<Float> ratings = new ArrayList<Float>();

    public ReviewAdapter(@NonNull Context context, ArrayList<String> reviews , ArrayList<Float>ratings) {
        this.context = context;
        this.reviews = reviews;
        this.ratings = ratings;
    }

    @Override
    public int getCount() {
        return reviews.size();
    }

    @Override
    public Object getItem(int position) {
        return reviews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position , View view , ViewGroup parent)
    {
        LayoutInflater inflater  =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view1 = inflater.inflate(R.layout.customreview , null);

        TextView review = view1.findViewById(R.id.review);
        RatingBar rating = view1.findViewById(R.id.rating);

        review.setText(reviews.get(position));
        rating.setRating((Float) ratings.get(position));
        return view1;
    }
}
