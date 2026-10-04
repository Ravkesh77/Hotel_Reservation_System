CREATE DATABASE IF NOT EXISTS hotel_reservation;

USE hotel_reservation;

CREATE TABLE rooms (
    room_id INT PRIMARY KEY AUTO_INCREMENT,
    room_number INT UNIQUE NOT NULL,
    category VARCHAR(30) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    capacity INT NOT NULL,
    available BOOLEAN DEFAULT TRUE
);

CREATE TABLE customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(15),
    email VARCHAR(100)
);

CREATE TABLE bookings (
    booking_id VARCHAR(30) PRIMARY KEY,
    customer_id INT NOT NULL,
    room_id INT NOT NULL,
    check_in DATE NOT NULL,
    check_out DATE NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    status VARCHAR(30) NOT NULL,

    FOREIGN KEY (customer_id)
        REFERENCES customers(customer_id),

    FOREIGN KEY (room_id)
        REFERENCES rooms(room_id)
);

CREATE TABLE payments (
    transaction_id VARCHAR(50) PRIMARY KEY,
    booking_id VARCHAR(30) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    payment_method VARCHAR(30) NOT NULL,
    payment_status VARCHAR(30) NOT NULL,

    FOREIGN KEY (booking_id)
        REFERENCES bookings(booking_id)
);

INSERT INTO rooms
(room_number, category, price, capacity, available)
VALUES
(101, 'Standard', 2000, 2, TRUE),
(102, 'Standard', 2000, 2, TRUE),
(201, 'Deluxe', 3500, 3, TRUE),
(202, 'Deluxe', 3500, 3, TRUE),
(301, 'Suite', 6000, 4, TRUE),
(302, 'Suite', 6000, 4, TRUE);