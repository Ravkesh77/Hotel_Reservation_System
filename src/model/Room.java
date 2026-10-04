package model;

public class Room {
    private int roomId;
    private int roomNumber;
    private String category;
    private double price;
    private int capacity;
    private boolean available;

    public Room() {}

    public Room(int roomId, int roomNumber, String category,
                double price, int capacity, boolean available) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.capacity = capacity;
        this.available = available;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Room ID: " + roomId +
                " | Room No: " + roomNumber +
                " | Category: " + category +
                " | Price: ₹" + price +
                " | Capacity: " + capacity +
                " | Status: " +
                (available ? "Available" : "Booked");
    }
}