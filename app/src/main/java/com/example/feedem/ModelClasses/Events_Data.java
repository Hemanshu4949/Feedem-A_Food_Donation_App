package com.example.feedem.ModelClasses;

import java.util.ArrayList;

public class Events_Data {



    static ArrayList<String> events = new ArrayList<String>();

    public static ArrayList<String> getEvents() {
        return events;
    }

    public static void addevent(String event)
    {
        events.add(event);
    }

    public static void emptyevent()
    {
        events.removeAll(events);
    }

}
