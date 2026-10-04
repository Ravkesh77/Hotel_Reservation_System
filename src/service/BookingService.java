package service;

import dao.BookingDAO;
import dao.CustomerDAO;
import model.Booking;
import model.Customer;
import model.Payment;
import model.Room;
import payment.PaymentMethod;
import util.IDGenerator;
import util.ReceiptGenerator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BookingService {

    private BookingDAO bookingDAO =
            new BookingDAO();

    private CustomerDAO customerDAO =
            new CustomerDAO();

    private RoomService roomService =
            new RoomService();

    private PaymentService paymentService =
            new PaymentService();

    public Booking createBooking(
            Customer customer,
            Room room,
            LocalDate checkIn,
            LocalDate checkOut,
            PaymentMethod paymentMethod) {

        if (checkIn == null ||
            checkOut == null ||
            !checkOut.isAfter(checkIn)) {

            throw new IllegalArgumentException(
                    "Check-out must be after check-in."
            );
        }

        if (!room.isAvailable()) {
            throw new IllegalStateException(
                    "Room is not available."
            );
        }

        long nights =
                ChronoUnit.DAYS.between(
                        checkIn,
                        checkOut
                );

        double total =
                nights * room.getPrice();

        int customerId =
                customerDAO.addCustomer(customer);

        if (customerId == -1) {
            throw new IllegalStateException(
                    "Could not save customer."
            );
        }

        String bookingId =
                IDGenerator.generateBookingId();

        Payment payment =
                paymentService.process(
                        paymentMethod,
                        bookingId,
                        total
                );

        if (!payment.getPaymentStatus()
                .equalsIgnoreCase("PAID")) {

            throw new IllegalStateException(
                    "Payment failed."
            );
        }

        Booking booking =
                new Booking(
                        bookingId,
                        customerId,
                        room.getRoomId(),
                        checkIn,
                        checkOut,
                        total,
                        "Confirmed"
                );

        if (!bookingDAO.createBooking(booking)) {
            throw new IllegalStateException(
                    "Could not save booking."
            );
        }

        roomService.setAvailability(
                room.getRoomId(),
                false
        );

        ReceiptGenerator.generate(
                booking,
                customer,
                room,
                payment
        );

        return booking;
    }

    public Booking getBooking(String bookingId) {

        return bookingDAO.getBookingById(
                bookingId
        );
    }

    public boolean cancelBooking(String bookingId) {

        Booking booking =
                bookingDAO.getBookingById(
                        bookingId
                );

        if (booking == null ||
            !booking.getStatus()
                    .equalsIgnoreCase("Confirmed")) {

            return false;
        }

        boolean cancelled =
                bookingDAO.cancelBooking(
                        bookingId
                );

        if (cancelled) {

            roomService.setAvailability(
                    booking.getRoomId(),
                    true
            );
        }

        return cancelled;
    }
}