package com.example.feedem.ModelClasses;

import android.graphics.Bitmap;

public class Ngo_Details {

    static String name , reviews , feeds , campaign , volunteers ;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Ngo_Details.name = name;
    }

    public static String getReviews() {
        return reviews;
    }

    public static void setReviews(String reviews) {
        Ngo_Details.reviews = reviews;
    }

    public static String getFeeds() {
        return feeds;
    }

    public static void setFeeds(String feeds) {
        Ngo_Details.feeds = feeds;
    }

    public static String getCampaign() {
        return campaign;
    }

    public static void setCampaign(String campaign) {
        Ngo_Details.campaign = campaign;
    }

    public static String getVolunteers() {
        return volunteers;
    }

    public static void setVolunteers(String volunteers) {
        Ngo_Details.volunteers = volunteers;
    }

    public static Bitmap getImage() {
        return image;
    }

    public static void setImage(Bitmap image) {
        Ngo_Details.image = image;
    }

    static Bitmap image;

    public static boolean imagecheck()
    {
        if (image == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public static boolean namecheck()
    {
        if (name == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}
