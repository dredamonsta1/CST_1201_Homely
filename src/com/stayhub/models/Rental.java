package com.stayhub.models;

public abstract class Rental {

    private String name;
    private String location;

    public Rental(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName()     { return name; }
    public String getLocation() { return location; }

    // Every rental type must define how its total is calculated
    public abstract double calculateTotal(int duration);

    // Short description shown on receipts (e.g. "Short-Term Room")
    public abstract String getRentalType();

    // "night(s)" for rooms, "month(s)" for apartments
    public abstract String getDurationUnit();
}
