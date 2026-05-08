package com.stayhub.models;

public class RoomRental extends Rental {

    private double pricePerNight;
    private double cleaningFee;

    public RoomRental(String name, String location, double pricePerNight, double cleaningFee) {
        super(name, location);
        this.pricePerNight = pricePerNight;
        this.cleaningFee   = cleaningFee;
    }

    // Total = (nightly rate × nights) + one-time cleaning fee
    @Override
    public double calculateTotal(int nights) {
        return (pricePerNight * nights) + cleaningFee;
    }

    @Override public String getRentalType()    { return "Short-Term Room"; }
    @Override public String getDurationUnit()  { return "night(s)"; }

    public double getPricePerNight() { return pricePerNight; }
    public double getCleaningFee()   { return cleaningFee; }
}
