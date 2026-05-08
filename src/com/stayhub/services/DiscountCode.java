package com.stayhub.services;

import com.stayhub.models.Rental;

public class DiscountCode {

    private String      code;
    private Discountable discount;

    public DiscountCode(String code, Discountable discount) {
        this.code     = code;
        this.discount = discount;
    }

    public String getCode() { return code; }

    public boolean isValidFor(Rental rental, int duration) {
        return discount.isValidFor(rental, duration);
    }

    public double applyTo(double subtotal) {
        return discount.apply(subtotal);
    }

    public String getDescription() {
        return discount.getDescription();
    }
}
