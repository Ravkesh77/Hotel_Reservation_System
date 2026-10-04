package dao;

import model.Booking;
import util.DBConnection;

import java.sql.*;

public class BookingDAO {

    public boolean createBooking(Booking booking) {

        String sql =
                "INSERT INTO bookings " +
                "(booking_id, customer_id, room_id, check_in, " +
                "check_out, total_amount, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, booking.getBookingId());
            ps.setInt(2, booking.getCustomerId());
            ps.setInt(3, booking.getRoomId());
            ps.setDate(
                    4,
                    Date.valueOf(booking.getCheckIn())
            );
            ps.setDate(
                    5,
                    Date.valueOf(booking.getCheckOut())
            );
            ps.setDouble(6, booking.getTotalAmount());
            ps.setString(7, booking.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Booking getBookingById(String bookingId) {

        String sql =
                "SELECT * FROM bookings " +
                "WHERE booking_id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, bookingId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Booking(
                        rs.getString("booking_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("room_id"),
                        rs.getDate("check_in").toLocalDate(),
                        rs.getDate("check_out").toLocalDate(),
                        rs.getDouble("total_amount"),
                        rs.getString("status")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean cancelBooking(String bookingId) {

        String sql =
                "UPDATE bookings SET status = 'Cancelled' " +
                "WHERE booking_id = ? " +
                "AND status = 'Confirmed'";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, bookingId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}