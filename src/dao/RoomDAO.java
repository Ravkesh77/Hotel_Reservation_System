package dao;

import model.Room;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    public List<Room> getAvailableRooms() {

        List<Room> rooms = new ArrayList<>();

        String sql =
                "SELECT * FROM rooms " +
                "WHERE available = TRUE " +
                "ORDER BY room_number";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                rooms.add(mapRoom(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    public List<Room> searchByCategory(String category) {

        List<Room> rooms = new ArrayList<>();

        String sql =
                "SELECT * FROM rooms " +
                "WHERE category = ? AND available = TRUE";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, category);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rooms.add(mapRoom(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    public Room getRoomById(int roomId) {

        String sql =
                "SELECT * FROM rooms WHERE room_id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, roomId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapRoom(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateAvailability(
            int roomId,
            boolean available) {

        String sql =
                "UPDATE rooms SET available = ? " +
                "WHERE room_id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setBoolean(1, available);
            ps.setInt(2, roomId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addRoom(Room room) {

        String sql =
                "INSERT INTO rooms " +
                "(room_number, category, price, capacity, available) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, room.getRoomNumber());
            ps.setString(2, room.getCategory());
            ps.setDouble(3, room.getPrice());
            ps.setInt(4, room.getCapacity());
            ps.setBoolean(5, room.isAvailable());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Room mapRoom(ResultSet rs)
            throws SQLException {

        return new Room(
                rs.getInt("room_id"),
                rs.getInt("room_number"),
                rs.getString("category"),
                rs.getDouble("price"),
                rs.getInt("capacity"),
                rs.getBoolean("available")
        );
    }
}