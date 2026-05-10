package com.stayhub;

import com.stayhub.models.ApartmentRental;
import com.stayhub.models.RoomRental;
import com.stayhub.services.BookingService;
import com.stayhub.services.DiscountCode;
import com.stayhub.services.FlatDiscount;
import com.stayhub.services.PercentageDiscount;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // ── Discount codes ──────────────────────────────────────────────────
        // SAVE10    : 10% off — short-term rooms only, minimum 3 nights
        // LONGSTAY  : 15% off — long-term apartments only
        // WELCOME20 : $20 flat off — any rental
        // CLEANFEE5 : $5  flat off — any rental

        PercentageDiscount save10Discount = new PercentageDiscount(0.10, 3, "short-term");

        BookingService service = new BookingService();
        service.addDiscountCode(new DiscountCode("SAVE10",    save10Discount));
        service.addDiscountCode(new DiscountCode("LONGSTAY",  new PercentageDiscount(0.15, 1, "long-term")));
        service.addDiscountCode(new DiscountCode("WELCOME20", new FlatDiscount(20.00, "any")));
        service.addDiscountCode(new DiscountCode("CLEANFEE5", new FlatDiscount(5.00,  "any")));

        // ── Available properties ────────────────────────────────────────────
        RoomRental      beachRoom = new RoomRental("Beachside Studio",  "Miami, FL",      85.00, 35.00);
        RoomRental      cozyCabin = new RoomRental("Cozy Mountain Cabin","Asheville, NC", 60.00, 25.00);
        ApartmentRental cityLoft  = new ApartmentRental("Downtown Loft","New York, NY", 1800.00, 2000.00);
        ApartmentRental suburbApt = new ApartmentRental("Parkview Apartment","Austin, TX", 1200.00, 1500.00);

        Scanner scanner = new Scanner(System.in);

    Runnable printWelcomeMessage = () ->{

            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║         Welcome to StayHub           ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println();
            System.out.println("Available discount codes:");
            System.out.println("  SAVE10    — 10% off short-term rooms (min 3 nights)");
            System.out.println("  LONGSTAY  — 15% off long-term apartments");
            System.out.println("  WELCOME20 — $20 flat off any rental");
            System.out.println("  CLEANFEE5 — $5  flat off any rental");
        System.out.println();
        // pressEnterToContinue(scanner);
    };
    
    printWelcomeMessage.run();
    

        // ── Booking 1: Short-Term Room ──────────────────────────────────────
        Runnable bookShortTermRoom = () -> {
            System.out.println("══ BOOKING 1 — Short-Term Room ══════════");
            System.out.println("  1. Beachside Studio  — Miami, FL        $85/night + $35 cleaning");
            System.out.println("  2. Cozy Mountain Cabin — Asheville, NC  $60/night + $25 cleaning");
            System.out.print("Choose a room (1 or 2): ");
        };
        // bookShortTermRoom.run();


    




    int roomChoice = readInt(scanner);
    RoomRental chosenRoom = (roomChoice == 2) ? cozyCabin : beachRoom;

    // System.out.print("Number of nights: ");
    // int nights = readInt(scanner);

    // System.out.print("Discount code (Enter to skip): ");
    // String roomCode = scanner.nextLine().trim();

    // service.book(chosenRoom,nights,roomCode);

    Runnable bookLongTermApartment = () -> {
    // // ── Booking 2: Long-Term Apartment ──────────────────────────────────
    System.out.println("══ BOOKING 2 — Long-Term Apartment ══════");
    System.out.println("  1. Downtown Loft   — New York, NY   $1800/month + $2000 deposit");
    System.out.println("  2. Parkview Apt    — Austin, TX     $1200/month + $1500 deposit");
    System.out.print("Choose an apartment (1 or 2): ");
    };


         //Booking  preference selection long term or short term
    Runnable chooseBookingType = () -> {
        System.out.println("Choose booking type:");
        System.out.println("  1. Short-Term Room");
        System.out.println("  2. Long-Term Apartment");
        System.out.print("Enter choice (1 or 2): ");

        if (readInt(scanner) == 2) {
            bookLongTermApartment.run();
            // System.out.println("You selected: Long-Term Apartment");
        } else {
            bookShortTermRoom.run();
            // System.out.println("You selected: Short-Term Room");
        }
    };
    chooseBookingType.run();



    // int aptChoice = readInt(scanner);
    // ApartmentRental chosenApt = (aptChoice == 2) ? suburbApt : cityLoft;

    // System.out.print("Number of months: ");
    // int months = readInt(scanner);

    // System.out.print("Discount code (Enter to skip): ");
    // String aptCode = scanner.nextLine().trim();

    // service.book(chosenApt,months,aptCode);

    // // ── Live Modification Demo ───────────────────────────────────────────
    // // For your Expert Defense video: change 10% → 15% and rebook the room.
    // System.out.println("══ LIVE MODIFICATION DEMO ═══════════════");System.out.println("Changing SAVE10 from 10% → 15% and re-running the room booking...");save10Discount.setPercentage(0.15);service.book(chosenRoom,nights,"SAVE10");

    scanner.close();
    }

    private static int readInt(Scanner scanner) {
        int value = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline
        return value;
    }
}
