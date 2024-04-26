package com.example.feedem.ModelClasses;

import java.util.ArrayList;

public class Reviews_Data {
    static String Value;



    static ArrayList<String> review = new ArrayList<String>();

    static ArrayList<Float> Stars = new ArrayList<Float>();


    public static ArrayList<String> getReview() {
        return review;
    }
    public static ArrayList<Float> getStars() {
        return Stars;
    }

    public static void addreview(String value , Float star)
    {
        review.add(value);
        Stars.add(star);

    }

    public static void emptyreview()
    {
        review.removeAll(review);
        Stars.removeAll(Stars);
    }

}
