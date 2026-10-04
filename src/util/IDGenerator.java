package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class IDGenerator {

    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern(
                    "yyyyMMddHHmmss"
            );

    public static String generateBookingId() {

        return "BK" +
                LocalDateTime.now()
                        .format(FORMAT);
    }

    public static String generateTransactionId() {

        return "TXN" +
                LocalDateTime.now()
                        .format(FORMAT) +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 4)
                        .toUpperCase();
    }
}