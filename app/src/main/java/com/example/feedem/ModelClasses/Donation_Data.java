package com.example.feedem.ModelClasses;

import java.util.Date;

public class Donation_Data {
    public static int getServings() {
        return Servings;
    }

    public static void setServings(int servings) {
        Servings = servings;
    }

    public static int getFoodhours() {
        return Foodhours;
    }

    public static void setFoodhours(int foodhours) {
        Foodhours = foodhours;
    }
    public static String getFood_Categorie() {
        return Food_Categorie;
    }

    public static void setFood_Categorie(String food_Categorie) {
        Food_Categorie = food_Categorie;
    }



    public  static String getType_OF_Food()
    {
        return Type_Of_Food;
    }

    public static void setType_Of_Food(String type_OF_Food)
    {
        Type_Of_Food = type_OF_Food;
    }

    public static String getAddress()
    {
        return address;
    }

    public static void setAddress(String address)
    {
        Donation_Data.address = address;
    }

    public static String getContact()
    {
        return contact;
    }

    public static void setContact(String contact)
    {
        Donation_Data.contact = contact;
    }

    static int Servings = 0;
    static int Foodhours = 0;
    static double Longitude = 0;
    static double Latitude = 0;
    static String address = null ;
    static String contact = "0000000000" ;
    static String Type_Of_Food = "Any" ;
    static String Food_Categorie = "Any" ;



    static String uid  = null;



    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        Donation_Data.status = status;
    }

    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        Donation_Data.date = date;
    }

    static String date = null;
    static String status = "pending";





    public static double getLongitude() {
        return Longitude;
    }

    public static void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public static double getLatitude() {
        return Latitude;
    }

    public static void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public static String getUid() {
        return uid;
    }

    public static void setUid(String uid) {
        Donation_Data.uid = uid;
    }


}
