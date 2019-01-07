package com.example.osamakhalid.thesmartinterviewer.models;

/**
 * Created by Osama Khalid on 1/2/2019.
 */

public class Company {
    String name, location, category;

    public Company(String name, String location, String category) {
        this.name = name;
        this.location = location;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getCategory() {
        return category;
    }
}
