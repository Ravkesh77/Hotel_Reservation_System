package service;

import dao.PaymentDAO;
import model.Payment;
import payment.PaymentMethod;
import util.IDGenerator;

public class PaymentService {

    private PaymentDAO paymentDAO =
            new PaymentDAO();

    public Payment process(
            PaymentMethod method,
            String bookingId,
            double amount) {

        boolean success =
                method.processPayment(amount);

        Payment payment =
                new Payment(
                        IDGenerator.generateTransactionId(),
                        bookingId,
                        amount,
                        method.getMethodName(),
                        success ? "PAID" : "FAILED"
                );

        if (success) {
            paymentDAO.savePayment(payment);
        }

        return payment;
    }
}