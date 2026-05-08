package com.stayhub.services;

import com.stayhub.models.Rental;
import com.stayhub.models.ApartmentRental;
import com.stayhub.models.RoomRental;

public class FlatDiscount implements Discountable {

    private double amount;
    private String requiredType; // "any", "short-term", or "long-term"

    public FlatDiscount(double amount, String requiredType) {
        this.amount       = amount;
        this.requiredType = requiredType;
    }

    @Override
    public double apply(double subtotal) {
        return Math.max(0, subtotal - amount);
    }

    @Override
    public boolean isValidFor(Rental rental, int duration) {
        switch (requiredType) {
            case "short-term": return rental instanceof RoomRental;
            case "long-term":  return rental instanceof ApartmentRental;
            default:           return true;
        }
    }

    @Override
    public String getDescription() {
        String scope = switch (requiredType) {
            case "short-term" -> ", rooms only";
            case "long-term"  -> ", apts only";
            default           -> "";
        };
        return String.format("$%.2f flat off%s", amount, scope);
    }
}
