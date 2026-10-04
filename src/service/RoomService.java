package service;

import dao.RoomDAO;
import model.Room;

import java.util.List;

public class RoomService {

    private RoomDAO roomDAO = new RoomDAO();

    public List<Room> getAvailableRooms() {
        return roomDAO.getAvailableRooms();
    }

    public List<Room> searchByCategory(String category) {
        return roomDAO.searchByCategory(category);
    }

    public Room getRoom(int roomId) {
        return roomDAO.getRoomById(roomId);
    }

    public boolean setAvailability(
            int roomId,
            boolean available) {

        return roomDAO.updateAvailability(
                roomId,
                available
        );
    }

    public boolean addRoom(Room room) {
        return roomDAO.addRoom(room);
    }
}