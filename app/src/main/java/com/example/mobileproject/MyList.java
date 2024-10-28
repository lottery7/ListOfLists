package com.example.mobileproject;

import java.util.ArrayList;

public class MyList {
    public String uid;
    public String name;
    public ArrayList<String> items;

    public MyList(String uid, String name, ArrayList<String> items) {
        this.uid = uid;
        this.name = name;
        this.items = items;
    }
}
