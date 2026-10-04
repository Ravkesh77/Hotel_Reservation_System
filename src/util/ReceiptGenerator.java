package util;

import model.Booking;
import model.Customer;
import model.Payment;
import model.Room;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReceiptGenerator {

    public static void generate(
            Booking booking,
            Customer customer,
            Room room,
            Payment payment) {

        File directory = new File("receipts");

        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(
                directory,
                booking.getBookingId() + ".txt"
        );

        try (FileWriter writer = new FileWriter(file)) {

            writer.write("====================================\n");
            writer.write("       HOTEL BOOKING RECEIPT\n");
            writer.write("====================================\n\n");

            writer.write(
                    "Booking ID : " +
                    booking.getBookingId() + "\n"
            );

            writer.write(
                    "Guest Name : " +
                    customer.getName() + "\n"
            );

            writer.write(
                    "Phone      : " +
                    customer.getPhone() + "\n"
            );

            writer.write(
                    "Room       : " +
                    room.getRoomNumber() + "\n"
            );

            writer.write(
                    "Category   : " +
                    room.getCategory() + "\n"
            );

            writer.write(
                    "Check-in   : " +
                    booking.getCheckIn() + "\n"
            );

            writer.write(
                    "Check-out  : " +
                    booking.getCheckOut() + "\n"
            );

            writer.write(
                    "Amount     : Rs." +
                    booking.getTotalAmount() + "\n"
            );

            writer.write(
                    "Payment    : " +
                    payment.getPaymentMethod() + "\n"
            );

            writer.write(
                    "Transaction: " +
                    payment.getTransactionId() + "\n"
            );

            writer.write(
                    "Status     : " +
                    booking.getStatus() + "\n"
            );

            writer.write("\n====================================\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}