package com.stayhub.services;

import com.stayhub.models.Rental;

public interface Discountable {

    // Applies this discount to a subtotal and returns the new total
    double apply(double subtotal);

    // Returns false if this discount cannot be used on this rental/duration combo
    boolean isValidFor(Rental rental, int duration);

    // Human-readable description shown on the receipt
    String getDescription();
}
