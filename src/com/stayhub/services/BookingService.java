package com.stayhub.services;

import com.stayhub.models.Rental;
import java.util.HashMap;
import java.util.Map;

public class BookingService {

    private Map<String, DiscountCode> codes = new HashMap<>();

    public void addDiscountCode(DiscountCode code) {
        codes.put(code.getCode(), code);
    }

    public void book(Rental rental, int duration, String codeInput) {
        double subtotal     = rental.calculateTotal(duration);
        double finalTotal   = subtotal;
        String codeLabel    = "None";
        String codeDetail   = "";

        if (codeInput != null && !codeInput.isBlank()) {
            DiscountCode code = codes.get(codeInput.toUpperCase());

            if (code == null) {
                System.out.println("  [!] Code \"" + codeInput + "\" not recognised.");
            } else if (!code.isValidFor(rental, duration)) {
                System.out.println("  [!] Code \"" + codeInput + "\" is not valid for this booking.");
            } else {
                finalTotal = code.applyTo(subtotal);
                codeLabel  = codeInput.toUpperCase();
                codeDetail = code.getDescription();
            }
        }

        printReceipt(rental, duration, subtotal, finalTotal, codeLabel, codeDetail);
    }

    private void printReceipt(Rental rental, int duration, double subtotal,
                              double total, String codeLabel, String codeDetail) {
        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║          STAYHUB RECEIPT             ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.printf( "║  Property : %-24s ║%n", rental.getName());
        System.out.printf( "║  Location : %-24s ║%n", rental.getLocation());
        System.out.printf( "║  Type     : %-24s ║%n", rental.getRentalType());
        System.out.printf( "║  Duration : %-3d %-20s ║%n", duration, rental.getDurationUnit());
        System.out.println("╠══════════════════════════════════════╣");
        System.out.printf( "║  Subtotal : $%-23.2f ║%n", subtotal);
        System.out.printf( "║  Code     : %-24s ║%n", codeLabel);
        if (!codeDetail.isEmpty()) {
            System.out.printf("║  Savings  : %-24s ║%n", codeDetail);
        }
        System.out.printf( "║  TOTAL    : $%-23.2f ║%n", total);
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println();
    }
}
