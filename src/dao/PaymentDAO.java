package dao;

import model.Payment;
import util.DBConnection;

import java.sql.*;

public class PaymentDAO {

    public boolean savePayment(Payment payment) {

        String sql =
                "INSERT INTO payments " +
                "(transaction_id, booking_id, amount, " +
                "payment_method, payment_status) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, payment.getTransactionId());
            ps.setString(2, payment.getBookingId());
            ps.setDouble(3, payment.getAmount());
            ps.setString(4, payment.getPaymentMethod());
            ps.setString(5, payment.getPaymentStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}