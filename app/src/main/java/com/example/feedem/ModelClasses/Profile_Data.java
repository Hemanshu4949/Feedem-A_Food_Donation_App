package com.example.feedem.ModelClasses;

import android.graphics.Bitmap;

public class Profile_Data {


    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Profile_Data.name = name;
    }

    public static String getContact_no() {
        return Contact_no;
    }

    public static void setContact_no(String contact_no) {
        Contact_no = contact_no;
    }

    public static String getEmail() {
        return Email;
    }

    public static void setEmail(String email) {
        Email = email;
    }


    static String name = null;
    static String Contact_no = null;
    static String Email = null;

    static Bitmap image = null;

    public static Bitmap getImage() {
        return image;
    }

    public static void setImage(Bitmap image) {
        Profile_Data.image = image;
    }

    public static String getAge() {
        return age;
    }

    public static void setAge(String age) {
        Profile_Data.age = age;
    }

    static String age = null;


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
    public static boolean agecheck()
    {
        if (age == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public static boolean emailcheck()
    {
        if (Email == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public static boolean contactcheck()
    {
        if (Contact_no == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
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
}
