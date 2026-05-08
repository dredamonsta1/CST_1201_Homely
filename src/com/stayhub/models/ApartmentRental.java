package com.stayhub.models;

public class ApartmentRental extends Rental {

    private double pricePerMonth;
    private double securityDeposit;

    public ApartmentRental(String name, String location, double pricePerMonth, double securityDeposit) {
        super(name, location);
        this.pricePerMonth   = pricePerMonth;
        this.securityDeposit = securityDeposit;
    }

    // Total = (monthly rate × months) + one-time security deposit
    @Override
    public double calculateTotal(int months) {
        return (pricePerMonth * months) + securityDeposit;
    }

    @Override public String getRentalType()    { return "Long-Term Apartment"; }
    @Override public String getDurationUnit()  { return "month(s)"; }

    public double getPricePerMonth()    { return pricePerMonth; }
    public double getSecurityDeposit()  { return securityDeposit; }
}
