package com.stayhub.services;

import com.stayhub.models.Rental;
import com.stayhub.models.ApartmentRental;
import com.stayhub.models.RoomRental;

public class PercentageDiscount implements Discountable {

    private double percentage;   // e.g. 0.10 = 10% off
    private int    minDuration;  // 0 means no minimum
    private String requiredType; // "any", "short-term", or "long-term"

    public PercentageDiscount(double percentage, int minDuration, String requiredType) {
        this.percentage   = percentage;
        this.minDuration  = minDuration;
        this.requiredType = requiredType;
    }

    @Override
    public double apply(double subtotal) {
        return subtotal * (1.0 - percentage);
    }

    @Override
    public boolean isValidFor(Rental rental, int duration) {
        if (requiredType.equals("short-term") && !(rental instanceof RoomRental))     return false;
        if (requiredType.equals("long-term")  && !(rental instanceof ApartmentRental)) return false;
        if (minDuration > 0 && duration < minDuration) return false;
        return true;
    }

    @Override
    public String getDescription() {
        String scope = switch (requiredType) {
            case "short-term" -> ", rooms only";
            case "long-term"  -> ", apts only";
            default           -> "";
        };
        String min = minDuration > 0 ? " ≥" + minDuration + (requiredType.equals("long-term") ? "mo" : "ni") : "";
        return String.format("%.0f%% off%s%s", percentage * 100, scope, min);
    }

    // Exposed so the live-modification demo in Main.java works cleanly
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentage() { return percentage; }
}
