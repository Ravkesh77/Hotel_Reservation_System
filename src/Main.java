import model.Room;
import model.Booking;
import model.Customer;
import service.RoomService;
import service.BookingService;
import payment.PaymentMethod;
import payment.UPIPayment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class Main extends JFrame {

    private final RoomService roomService = new RoomService();
    private final BookingService bookingService = new BookingService();

    private JPanel contentPanel;

    // Current user booking/session data
    private Booking activeBooking;
    private Customer activeCustomer;
    private Room activeRoom;

    // =========================================================
    // COLORS
    // =========================================================

    private static final Color NAVY =
            new Color(15, 31, 48);

    private static final Color NAVY_LIGHT =
            new Color(24, 48, 72);

    private static final Color BLUE =
            new Color(37, 99, 235);

    private static final Color BLUE_DARK =
            new Color(29, 78, 216);

    private static final Color GOLD =
            new Color(212, 160, 23);

    private static final Color GOLD_LIGHT =
            new Color(255, 248, 225);

    private static final Color GREEN =
            new Color(22, 163, 74);

    private static final Color RED =
            new Color(220, 38, 38);

    private static final Color BACKGROUND =
            new Color(244, 247, 250);

    private static final Color CARD =
            Color.WHITE;

    private static final Color TEXT =
            new Color(25, 42, 61);

    private static final Color MUTED =
            new Color(92, 108, 124);

    private static final Color BORDER =
            new Color(220, 227, 234);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public Main() {

        setTitle("Hotel Reservation System");

        setSize(1450, 850);

        setMinimumSize(
                new Dimension(1100, 700)
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        createUI();
    }

    // =========================================================
    // MAIN UI
    // =========================================================

    private void createUI() {

        JPanel root =
                new JPanel(
                        new BorderLayout()
                );

        root.setBackground(
                BACKGROUND
        );

        root.add(
                createHeader(),
                BorderLayout.NORTH
        );

        root.add(
                createSidebar(),
                BorderLayout.WEST
        );

        contentPanel =
                new JPanel(
                        new BorderLayout()
                );

        contentPanel.setBackground(
                BACKGROUND
        );

        root.add(
                contentPanel,
                BorderLayout.CENTER
        );

        setContentPane(root);

        showDashboard();
    }

    // =========================================================
    // HEADER
    // =========================================================

    private JPanel createHeader() {

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setPreferredSize(
                new Dimension(
                        0,
                        82
                )
        );

        header.setBackground(
                NAVY
        );

        header.setBorder(
                new EmptyBorder(
                        10,
                        25,
                        10,
                        25
                )
        );

        // LEFT SIDE

        JPanel left =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                15,
                                0
                        )
                );

        left.setOpaque(false);

        JLabel logo =
                new JLabel(
                        new HotelLogoIcon(
                                52,
                                GOLD,
                                Color.WHITE
                        )
                );

        left.add(logo);

        JLabel title =
                new JLabel(
                        "<html>" +
                        "<span style='color:white;'>HOTEL </span>" +
                        "<span style='color:#D4A017;'>RESERVATION</span>" +
                        "<span style='color:white;'> SYSTEM</span>" +
                        "</html>"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        left.add(title);

        header.add(
                left,
                BorderLayout.WEST
        );

        // RIGHT SIDE

        JPanel user =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                12,
                                8
                        )
                );

        user.setOpaque(false);

        JLabel userIcon =
                new JLabel(
                        new UserIcon(
                                34,
                                Color.WHITE
                        )
                );

        JLabel welcome =
                new JLabel(
                        "Welcome, Guest"
                );

        welcome.setForeground(
                Color.WHITE
        );

        welcome.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        user.add(userIcon);
        user.add(welcome);

        header.add(
                user,
                BorderLayout.EAST
        );

        return header;
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    private JPanel createSidebar() {

        JPanel sidebar =
                new JPanel();

        sidebar.setPreferredSize(
                new Dimension(
                        245,
                        0
                )
        );

        sidebar.setBackground(
                NAVY
        );

        sidebar.setLayout(
                new BoxLayout(
                        sidebar,
                        BoxLayout.Y_AXIS
                )
        );

        sidebar.setBorder(
                new EmptyBorder(
                        25,
                        12,
                        15,
                        12
                )
        );

        JLabel menu =
                new JLabel(
                        "USER MENU"
                );

        menu.setForeground(
                Color.WHITE
        );

        menu.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        menu.setBorder(
                new EmptyBorder(
                        0,
                        10,
                        20,
                        0
                )
        );

        menu.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        sidebar.add(menu);

        JButton dashboard =
                createSidebarButton(
                        "Dashboard",
                        new HomeIcon(
                                24,
                                Color.WHITE
                        )
                );

        JButton rooms =
                createSidebarButton(
                        "Available Rooms",
                        new BedIcon(
                                24,
                                Color.WHITE
                        )
                );

        JButton search =
                createSidebarButton(
                        "Search Rooms",
                        new SearchIcon(
                                24,
                                Color.WHITE
                        )
                );

        JButton booking =
                createSidebarButton(
                        "My Booking",
                        new CalendarIcon(
                                24,
                                Color.WHITE
                        )
                );

        JButton profile =
                createSidebarButton(
                        "My Profile",
                        new UserIcon(
                                24,
                                Color.WHITE
                        )
                );

        sidebar.add(dashboard);
        sidebar.add(Box.createVerticalStrut(7));

        sidebar.add(rooms);
        sidebar.add(Box.createVerticalStrut(7));

        sidebar.add(search);
        sidebar.add(Box.createVerticalStrut(7));

        sidebar.add(booking);
        sidebar.add(Box.createVerticalStrut(7));

        sidebar.add(profile);

        sidebar.add(
                Box.createVerticalGlue()
        );

        JButton exit =
                createSidebarButton(
                        "Exit",
                        new ExitIcon(
                                24,
                                Color.WHITE
                        )
                );

        sidebar.add(exit);

        dashboard.addActionListener(
                e -> {
                    setActiveButton(
                            dashboard
                    );
                    showDashboard();
                }
        );

        rooms.addActionListener(
                e -> {
                    setActiveButton(
                            rooms
                    );
                    showRooms();
                }
        );

        search.addActionListener(
                e -> {
                    setActiveButton(
                            search
                    );
                    showSearch();
                }
        );

        booking.addActionListener(
                e -> {
                    setActiveButton(
                            booking
                    );
                    showBooking();
                }
        );

        profile.addActionListener(
                e -> {
                    setActiveButton(
                            profile
                    );
                    showProfile();
                }
        );

        exit.addActionListener(
                e -> exitApplication()
        );

        setActiveButton(
                dashboard
        );

        return sidebar;
    }

    // =========================================================
    // SIDEBAR BUTTON
    // =========================================================

    private JButton createSidebarButton(
            String text,
            Icon icon
    ) {

        JButton button =
                new JButton(
                        text,
                        icon
                );

        button.setPreferredSize(
                new Dimension(
                        220,
                        52
                )
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        52
                )
        );

        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setIconTextGap(15);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                NAVY
        );

        button.setOpaque(true);

        button.setContentAreaFilled(true);

        button.setBorderPainted(false);

        button.setFocusPainted(false);

        button.setRolloverEnabled(false);

        button.setBorder(
                new EmptyBorder(
                        8,
                        15,
                        8,
                        10
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // ACTIVE SIDEBAR BUTTON
    // =========================================================

    private void setActiveButton(
            JButton selected
    ) {

        Container parent =
                selected.getParent();

        if (parent == null) {
            return;
        }

        for (
                Component component :
                parent.getComponents()
        ) {

            if (
                    component instanceof JButton
            ) {

                JButton button =
                        (JButton) component;

                button.setBackground(
                        NAVY
                );

                button.setForeground(
                        Color.WHITE
                );
            }
        }

        selected.setBackground(
                GOLD
        );

        selected.setForeground(
                NAVY
        );
    }

    // =========================================================
    // DASHBOARD
    // =========================================================

    private void showDashboard() {

        contentPanel.removeAll();

        JPanel page =
                new JPanel(
                        new BorderLayout(
                                0,
                                20
                        )
                );

        page.setBackground(
                BACKGROUND
        );

        page.setBorder(
                new EmptyBorder(
                        30,
                        35,
                        30,
                        35
                )
        );

        // -----------------------------------------------------
        // HERO
        // -----------------------------------------------------

        JPanel hero =
                new JPanel(
                        new BorderLayout()
                );

        hero.setPreferredSize(
                new Dimension(
                        0,
                        230
                )
        );

        hero.setBackground(
                Color.WHITE
        );

        hero.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                25,
                                30,
                                25,
                                30
                        )
                )
        );

        JPanel heroText =
                new JPanel();

        heroText.setOpaque(false);

        heroText.setLayout(
                new BoxLayout(
                        heroText,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel welcome =
                new JLabel(
                        "WELCOME TO"
                );

        welcome.setForeground(
                GOLD
        );

        welcome.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        JLabel title =
                new JLabel(
                        "Hotel Reservation System"
                );

        title.setForeground(
                TEXT
        );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        36
                )
        );

        JLabel description =
                new JLabel(
                        "<html>Search rooms, check availability and manage<br>" +
                        "your hotel reservation easily and comfortably.</html>"
                );

        description.setForeground(
                MUTED
        );

        description.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        heroText.add(welcome);

        heroText.add(
                Box.createVerticalStrut(8)
        );

        heroText.add(title);

        heroText.add(
                Box.createVerticalStrut(10)
        );

        heroText.add(description);

        heroText.add(
                Box.createVerticalStrut(15)
        );

        JPanel goldLine =
                new JPanel();

        goldLine.setBackground(
                GOLD
        );

        goldLine.setMaximumSize(
                new Dimension(
                        100,
                        4
                )
        );

        goldLine.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        heroText.add(
                goldLine
        );

        hero.add(
                heroText,
                BorderLayout.WEST
        );

        JPanel building =
                new JPanel(
                        new GridBagLayout()
                );

        building.setOpaque(false);

        building.add(
                new JLabel(
                        new HotelBuildingIcon(
                                175,
                                BLUE,
                                GOLD
                        )
                )
        );

        hero.add(
                building,
                BorderLayout.EAST
        );

        page.add(
                hero,
                BorderLayout.NORTH
        );

        // -----------------------------------------------------
        // CARDS
        // -----------------------------------------------------

        JPanel cards =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                20,
                                20
                        )
                );

        cards.setOpaque(false);

        cards.add(
                createDashboardCard(
                        "Available Rooms",
                        "View all available rooms with price and capacity.",
                        new BedIcon(
                                42,
                                BLUE
                        ),
                        BLUE,
                        e -> showRooms()
                )
        );

        cards.add(
                createDashboardCard(
                        "Search Rooms",
                        "Find rooms according to category and availability.",
                        new SearchIcon(
                                42,
                                GOLD
                        ),
                        GOLD,
                        e -> showSearch()
                )
        );

        cards.add(
                createDashboardCard(
                        "My Booking",
                        "View and manage your hotel reservation.",
                        new CalendarIcon(
                                42,
                                GREEN
                        ),
                        GREEN,
                        e -> showBooking()
                )
        );

        page.add(
                cards,
                BorderLayout.CENTER
        );

        contentPanel.add(
                page,
                BorderLayout.CENTER
        );

        refresh();
    }

    // =========================================================
    // DASHBOARD CARD
    // =========================================================

    private JPanel createDashboardCard(
            String title,
            String description,
            Icon icon,
            Color accent,
            java.awt.event.ActionListener action
    ) {

        JPanel card =
                new JPanel();

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBackground(
                CARD
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                25,
                                25,
                                25,
                                25
                        )
                )
        );

        JLabel iconLabel =
                new JLabel(
                        icon
                );

        iconLabel.setOpaque(true);

        iconLabel.setBackground(
                new Color(
                        Math.min(
                                255,
                                accent.getRed() + 225
                        ),
                        Math.min(
                                255,
                                accent.getGreen() + 225
                        ),
                        Math.min(
                                255,
                                accent.getBlue() + 225
                        )
                )
        );

        iconLabel.setBorder(
                new EmptyBorder(
                        12,
                        12,
                        12,
                        12
                )
        );

        iconLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        card.add(
                iconLabel
        );

        card.add(
                Box.createVerticalStrut(20)
        );

        JLabel heading =
                new JLabel(
                        title
                );

        heading.setForeground(
                TEXT
        );

        heading.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        21
                )
        );

        heading.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        card.add(
                heading
        );

        card.add(
                Box.createVerticalStrut(8)
        );

        JLabel descriptionLabel =
                new JLabel(
                        "<html><div style='width:260px'>" +
                        description +
                        "</div></html>"
                );

        descriptionLabel.setForeground(
                MUTED
        );

        descriptionLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        descriptionLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        card.add(
                descriptionLabel
        );

        card.add(
                Box.createVerticalGlue()
        );

        JButton open =
                createPrimaryButton(
                        "Open"
                );

        open.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        open.addActionListener(
                action
        );

        card.add(
                open
        );

        return card;
    }

    // =========================================================
    // PRIMARY BUTTON
    // =========================================================

    private JButton createPrimaryButton(
            String text
    ) {

        JButton button =
                new JButton(
                        text
                );

        button.setPreferredSize(
                new Dimension(
                        115,
                        42
                )
        );

        button.setMaximumSize(
                new Dimension(
                        140,
                        42
                )
        );

        button.setBackground(
                BLUE
        );

        button.setForeground(
                Color.WHITE
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        button.setOpaque(true);

        button.setContentAreaFilled(true);

        button.setBorderPainted(false);

        button.setFocusPainted(false);

        button.setRolloverEnabled(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // AVAILABLE ROOMS
    // =========================================================

    private void showRooms() {

        contentPanel.removeAll();

        JPanel page =
                createPage(
                        "Available Rooms",
                        "Browse all currently available hotel rooms."
                );

        JPanel roomsPanel =
                new JPanel(
                        new GridLayout(
                                0,
                                2,
                                20,
                                20
                        )
                );

        roomsPanel.setBackground(
                BACKGROUND
        );

        try {

            List<Room> rooms =
                    roomService
                            .getAvailableRooms();

            if (
                    rooms == null ||
                    rooms.isEmpty()
            ) {

                roomsPanel.add(
                        createEmptyMessage(
                                "No rooms are currently available."
                        )
                );

            } else {

                for (
                        Room room :
                        rooms
                ) {

                    roomsPanel.add(
                            createRoomCard(
                                    room
                            )
                    );
                }
            }

        } catch (Exception e) {

            roomsPanel.add(
                    createEmptyMessage(
                            "Unable to load rooms."
                    )
            );

            e.printStackTrace();
        }

        JScrollPane scroll =
                new JScrollPane(
                        roomsPanel
                );

        scroll.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scroll.getVerticalScrollBar()
                .setUnitIncrement(16);

        page.add(
                scroll,
                BorderLayout.CENTER
        );

        contentPanel.add(
                page,
                BorderLayout.CENTER
        );

        refresh();
    }

    // =========================================================
    // ROOM CARD
    // =========================================================

    private JPanel createRoomCard(
            Room room
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                15,
                                10
                        )
                );

        card.setBackground(
                CARD
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        JLabel icon =
                new JLabel(
                        new BedIcon(
                                55,
                                BLUE
                        )
                );

        card.add(
                icon,
                BorderLayout.WEST
        );

        JPanel info =
                new JPanel();

        info.setOpaque(false);

        info.setLayout(
                new BoxLayout(
                        info,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel roomNumber =
                new JLabel(
                        "Room " +
                        room.getRoomNumber()
                );

        roomNumber.setForeground(
                TEXT
        );

        roomNumber.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        21
                )
        );

        JLabel category =
                new JLabel(
                        "Category: " +
                        room.getCategory()
                );

        JLabel price =
                new JLabel(
                        "Price: Rs. " +
                        room.getPrice() +
                        " / night"
                );

        JLabel capacity =
                new JLabel(
                        "Capacity: " +
                        room.getCapacity() +
                        " guests"
                );

        category.setForeground(
                MUTED
        );

        price.setForeground(
                MUTED
        );

        capacity.setForeground(
                MUTED
        );

        info.add(
                roomNumber
        );

        info.add(
                Box.createVerticalStrut(8)
        );

        info.add(
                category
        );

        info.add(
                price
        );

        info.add(
                capacity
        );

        JLabel status =
                new JLabel(
                        room.isAvailable()
                                ? "AVAILABLE"
                                : "NOT AVAILABLE"
                );

        status.setForeground(
                room.isAvailable()
                        ? GREEN
                        : RED
        );

        status.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        info.add(
                Box.createVerticalStrut(8)
        );

        info.add(
                status
        );

        card.add(
                info,
                BorderLayout.CENTER
        );

        JButton book =
                createPrimaryButton(
                        "Book Now"
                );

        book.addActionListener(
                e ->
                        showBookingForm(
                                room
                        )
        );

        card.add(
                book,
                BorderLayout.SOUTH
        );

        return card;
    }

    // =========================================================
    // SEARCH ROOMS
    // =========================================================

    private void showSearch() {

        contentPanel.removeAll();

        JPanel page =
                createPage(
                        "Search Rooms",
                        "Search hotel rooms by category."
                );

        JPanel searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                10
                        )
                );

        searchPanel.setBackground(
                Color.WHITE
        );

        searchPanel.setBorder(
                BorderFactory.createLineBorder(
                        BORDER
                )
        );

        JComboBox<String> category =
                new JComboBox<>(
                        new String[]{
                                "Standard",
                                "Deluxe",
                                "Suite"
                        }
                );

        category.setPreferredSize(
                new Dimension(
                        150,
                        38
                )
        );

        JTextField searchField =
                new JTextField();

        searchField.setPreferredSize(
                new Dimension(
                        220,
                        38
                )
        );

        searchField.setToolTipText(
                "Enter room number"
        );

        JButton searchButton =
                createPrimaryButton(
                        "Search"
                );

        searchPanel.add(
                new JLabel(
                        "Category:"
                )
        );

        searchPanel.add(
                category
        );

        searchPanel.add(
                searchField
        );

        searchPanel.add(
                searchButton
        );

        page.add(
                searchPanel,
                BorderLayout.NORTH
        );

        JPanel resultPanel =
                new JPanel(
                        new GridLayout(
                                0,
                                2,
                                20,
                                20
                        )
                );

        resultPanel.setBackground(
                BACKGROUND
        );

        JScrollPane scroll =
                new JScrollPane(
                        resultPanel
                );

        scroll.setBorder(
                BorderFactory.createEmptyBorder()
        );

        page.add(
                scroll,
                BorderLayout.CENTER
        );

        searchButton.addActionListener(
                e -> {

                    resultPanel.removeAll();

                    String selectedCategory =
                            category
                                    .getSelectedItem()
                                    .toString();

                    try {

                        List<Room> rooms =
                                roomService
                                        .searchByCategory(
                                                selectedCategory
                                        );

                        boolean found =
                                false;

                        if (
                                rooms != null
                        ) {

                            for (
                                    Room room :
                                    rooms
                            ) {

                                String number =
                                        searchField
                                                .getText()
                                                .trim();

                                boolean numberMatch =
                                        number.isEmpty()
                                                ||
                                        String.valueOf(
                                                room.getRoomNumber()
                                        ).contains(number);

                                if (
                                        room.isAvailable()
                                                &&
                                        numberMatch
                                ) {

                                    resultPanel.add(
                                            createRoomCard(
                                                    room
                                            )
                                    );

                                    found = true;
                                }
                            }
                        }

                        if (!found) {

                            resultPanel.add(
                                    createEmptyMessage(
                                            "No matching rooms found."
                                    )
                            );
                        }

                        resultPanel.revalidate();
                        resultPanel.repaint();

                    } catch (Exception ex) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Search failed.\n" +
                                ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
        );

        refresh();
    }

    // =========================================================
    // BOOKING FORM
    // =========================================================

    private void showBookingForm(
            Room room
    ) {

        if (room == null || !room.isAvailable()) {
            JOptionPane.showMessageDialog(
                    this,
                    "This room is no longer available.",
                    "Room Unavailable",
                    JOptionPane.WARNING_MESSAGE
            );
            showRooms();
            return;
        }

        JPanel form = new JPanel(
                new GridLayout(0, 2, 12, 12)
        );

        JTextField name = new JTextField(
                activeCustomer == null ? "" : activeCustomer.getName()
        );

        JTextField phone = new JTextField(
                activeCustomer == null ? "" : activeCustomer.getPhone()
        );

        JTextField email = new JTextField();

        JTextField checkIn = new JTextField(
                LocalDate.now().toString()
        );

        JTextField checkOut = new JTextField(
                LocalDate.now().plusDays(1).toString()
        );

        form.add(new JLabel("Guest Name:"));
        form.add(name);

        form.add(new JLabel("Phone:"));
        form.add(phone);

        form.add(new JLabel("Email:"));
        form.add(email);

        form.add(new JLabel("Check-in (YYYY-MM-DD):"));
        form.add(checkIn);

        form.add(new JLabel("Check-out (YYYY-MM-DD):"));
        form.add(checkOut);

        form.add(new JLabel("Selected Room:"));
        form.add(new JLabel(
                "Room " + room.getRoomNumber() +
                " - " + room.getCategory() +
                " - Rs. " + room.getPrice() + "/night"
        ));

        int result = JOptionPane.showConfirmDialog(
                this,
                form,
                "Book Room " + room.getRoomNumber(),
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        if (name.getText().trim().isEmpty() ||
                phone.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter guest name and phone.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            LocalDate inDate = LocalDate.parse(
                    checkIn.getText().trim()
            );

            LocalDate outDate = LocalDate.parse(
                    checkOut.getText().trim()
            );

            if (!outDate.isAfter(inDate)) {
                throw new IllegalArgumentException(
                        "Check-out must be after check-in."
                );
            }

            Customer customer = createCustomer(
                    name.getText().trim(),
                    phone.getText().trim(),
                    email.getText().trim()
            );

            String upiId = JOptionPane.showInputDialog(
                    this,
                    "Enter UPI ID for payment:\nExample: user@upi",
                    "Payment - UPI",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (upiId == null || upiId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Payment cancelled. Booking was not created.",
                        "Payment Cancelled",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            PaymentMethod paymentMethod =
                    new UPIPayment(upiId.trim());

            Booking booking = bookingService.createBooking(
                    customer,
                    room,
                    inDate,
                    outDate,
                    paymentMethod
            );

            // Store the successfully created booking for My Booking.
            activeBooking = booking;
            activeCustomer = customer;
            activeRoom = room;

            JOptionPane.showMessageDialog(
                    this,
                    "BOOKING CONFIRMED!\n\n" +
                    "Booking ID : " + booking.getBookingId() + "\n" +
                    "Guest      : " + customer.getName() + "\n" +
                    "Room       : " + room.getRoomNumber() + "\n" +
                    "Check-in   : " + booking.getCheckIn() + "\n" +
                    "Check-out  : " + booking.getCheckOut() + "\n" +
                    "Total      : Rs. " + booking.getTotalAmount() + "\n" +
                    "Status     : " + booking.getStatus(),
                    "Booking Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Immediately show the active booking.
            showBooking();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Booking failed.\n\n" + ex.getMessage(),
                    "Booking Error",
                    JOptionPane.ERROR_MESSAGE
            );

            ex.printStackTrace();
        }
    }

    // Creates Customer without assuming a particular constructor signature.
    // This keeps Main.java compatible with the Customer model already in the project.
    private Customer createCustomer(
            String name,
            String phone,
            String email
    ) throws Exception {

        // Most common constructor: Customer(String, String, String)
        try {
            java.lang.reflect.Constructor<Customer> c =
                    Customer.class.getConstructor(
                            String.class,
                            String.class,
                            String.class
                    );

            return c.newInstance(name, phone, email);

        } catch (NoSuchMethodException ignored) {
            // Try other common model structures below.
        }

        // Common constructor: Customer(int, String, String, String)
        try {
            java.lang.reflect.Constructor<Customer> c =
                    Customer.class.getConstructor(
                            int.class,
                            String.class,
                            String.class,
                            String.class
                    );

            return c.newInstance(0, name, phone, email);

        } catch (NoSuchMethodException ignored) {
            // Try a no-argument model with setters.
        }

        Customer customer =
                Customer.class.getDeclaredConstructor().newInstance();

        try {
            Customer.class
                    .getMethod("setName", String.class)
                    .invoke(customer, name);
        } catch (NoSuchMethodException ignored) {
        }

        try {
            Customer.class
                    .getMethod("setPhone", String.class)
                    .invoke(customer, phone);
        } catch (NoSuchMethodException ignored) {
        }

        try {
            Customer.class
                    .getMethod("setEmail", String.class)
                    .invoke(customer, email);
        } catch (NoSuchMethodException ignored) {
        }

        return customer;
    }

    // =========================================================
    // MY BOOKING
    // =========================================================

    private void showBooking() {

        contentPanel.removeAll();

        JPanel page = createPage(
                "My Booking",
                "View and manage your hotel reservation."
        );

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER),
                        new EmptyBorder(30, 30, 30, 30)
                )
        );

        if (activeBooking == null) {

            JLabel icon = new JLabel(
                    new CalendarIcon(55, BLUE)
            );

            JLabel title = new JLabel(
                    "No active booking"
            );
            title.setForeground(TEXT);
            title.setFont(
                    new Font("Segoe UI", Font.BOLD, 22)
            );

            JLabel text = new JLabel(
                    "Book a room from Available Rooms or Search Rooms."
            );
            text.setForeground(MUTED);

            card.add(icon);
            card.add(Box.createVerticalStrut(15));
            card.add(title);
            card.add(Box.createVerticalStrut(8));
            card.add(text);

        } else {

            JLabel icon = new JLabel(
                    new CalendarIcon(55, GREEN)
            );

            JLabel title = new JLabel(
                    "Booking Confirmed"
            );
            title.setForeground(GREEN);
            title.setFont(
                    new Font("Segoe UI", Font.BOLD, 24)
            );

            card.add(icon);
            card.add(Box.createVerticalStrut(15));
            card.add(title);
            card.add(Box.createVerticalStrut(20));

            card.add(createBookingRow(
                    "Booking ID",
                    activeBooking.getBookingId()
            ));
            card.add(Box.createVerticalStrut(10));

            card.add(createBookingRow(
                    "Guest Name",
                    activeCustomer == null
                            ? "Guest"
                            : activeCustomer.getName()
            ));
            card.add(Box.createVerticalStrut(10));

            card.add(createBookingRow(
                    "Phone",
                    activeCustomer == null
                            ? "-"
                            : activeCustomer.getPhone()
            ));
            card.add(Box.createVerticalStrut(10));

            card.add(createBookingRow(
                    "Room",
                    activeRoom == null
                            ? "-"
                            : String.valueOf(activeRoom.getRoomNumber())
            ));
            card.add(Box.createVerticalStrut(10));

            card.add(createBookingRow(
                    "Category",
                    activeRoom == null
                            ? "-"
                            : activeRoom.getCategory()
            ));
            card.add(Box.createVerticalStrut(10));

            card.add(createBookingRow(
                    "Check-in",
                    String.valueOf(activeBooking.getCheckIn())
            ));
            card.add(Box.createVerticalStrut(10));

            card.add(createBookingRow(
                    "Check-out",
                    String.valueOf(activeBooking.getCheckOut())
            ));
            card.add(Box.createVerticalStrut(10));

            card.add(createBookingRow(
                    "Total Amount",
                    "Rs. " + activeBooking.getTotalAmount()
            ));
            card.add(Box.createVerticalStrut(10));

            card.add(createBookingRow(
                    "Status",
                    activeBooking.getStatus()
            ));
            card.add(Box.createVerticalStrut(25));

            JButton cancel = createDangerButton(
                    "Cancel Booking"
            );
            cancel.setAlignmentX(Component.LEFT_ALIGNMENT);
            cancel.addActionListener(
                    e -> cancelCurrentBooking()
            );
            card.add(cancel);
        }

        page.add(card, BorderLayout.CENTER);
        contentPanel.add(page, BorderLayout.CENTER);
        refresh();
    }

    private JPanel createBookingRow(
            String label,
            String value
    ) {

        JPanel row = new JPanel(
                new BorderLayout(20, 0)
        );

        row.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 38)
        );
        row.setBackground(new Color(248, 250, 252));
        row.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER),
                        new EmptyBorder(8, 12, 8, 12)
                )
        );

        JLabel left = new JLabel(label);
        left.setForeground(TEXT);
        left.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        JLabel right = new JLabel(value);
        right.setForeground(MUTED);
        right.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        row.add(left, BorderLayout.WEST);
        row.add(right, BorderLayout.EAST);

        return row;
    }

    private JButton createDangerButton(String text) {

        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 42));
        button.setMaximumSize(new Dimension(180, 42));
        button.setBackground(RED);
        button.setForeground(Color.WHITE);
        button.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );
        return button;
    }

    private void cancelCurrentBooking() {

        if (activeBooking == null) {
            return;
        }

        int result = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to cancel booking " +
                activeBooking.getBookingId() + "?",
                "Cancel Booking",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (result != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            boolean cancelled = bookingService.cancelBooking(
                    activeBooking.getBookingId()
            );

            if (cancelled) {
                activeBooking = null;
                activeCustomer = null;
                activeRoom = null;

                JOptionPane.showMessageDialog(
                        this,
                        "Booking cancelled successfully.",
                        "Booking Cancelled",
                        JOptionPane.INFORMATION_MESSAGE
                );

                showBooking();
                showRooms();

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Unable to cancel this booking.",
                        "Cancellation Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Cancellation failed.\n\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // PROFILE
    // =========================================================

    private void showProfile() {

        contentPanel.removeAll();

        JPanel page =
                createPage(
                        "My Profile",
                        "Manage your guest profile information."
                );

        JPanel profileCard =
                new JPanel();

        profileCard.setLayout(
                new BoxLayout(
                        profileCard,
                        BoxLayout.Y_AXIS
                )
        );

        profileCard.setBackground(
                Color.WHITE
        );

        profileCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                30,
                                30,
                                30,
                                30
                        )
                )
        );

        JLabel userIcon =
                new JLabel(
                        new UserIcon(
                                70,
                                BLUE
                        )
                );

        userIcon.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        profileCard.add(
                userIcon
        );

        profileCard.add(
                Box.createVerticalStrut(20)
        );

        profileCard.add(
                createProfileRow(
                        "Name",
                        "Guest"
                )
        );

        profileCard.add(
                Box.createVerticalStrut(10)
        );

        profileCard.add(
                createProfileRow(
                        "Email",
                        "guest@example.com"
                )
        );

        profileCard.add(
                Box.createVerticalStrut(10)
        );

        profileCard.add(
                createProfileRow(
                        "Phone",
                        "Not provided"
                )
        );

        page.add(
                profileCard
        );

        contentPanel.add(
                page,
                BorderLayout.CENTER
        );

        refresh();
    }

    // =========================================================
    // PAGE
    // =========================================================

    private JPanel createPage(
            String title,
            String subtitle
    ) {

        JPanel page =
                new JPanel(
                        new BorderLayout(
                                0,
                                20
                        )
                );

        page.setBackground(
                BACKGROUND
        );

        page.setBorder(
                new EmptyBorder(
                        30,
                        35,
                        30,
                        35
                )
        );

        JPanel heading =
                new JPanel();

        heading.setOpaque(false);

        heading.setLayout(
                new BoxLayout(
                        heading,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel =
                new JLabel(
                        title
                );

        titleLabel.setForeground(
                TEXT
        );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        JLabel subtitleLabel =
                new JLabel(
                        subtitle
                );

        subtitleLabel.setForeground(
                MUTED
        );

        subtitleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        heading.add(
                titleLabel
        );

        heading.add(
                Box.createVerticalStrut(5)
        );

        heading.add(
                subtitleLabel
        );

        page.add(
                heading,
                BorderLayout.NORTH
        );

        return page;
    }

    // =========================================================
    // PROFILE ROW
    // =========================================================

    private JPanel createProfileRow(
            String label,
            String value
    ) {

        JPanel row =
                new JPanel(
                        new BorderLayout()
                );

        row.setPreferredSize(
                new Dimension(
                        650,
                        55
                )
        );

        row.setMaximumSize(
                new Dimension(
                        650,
                        55
                )
        );

        row.setBackground(
                new Color(
                        248,
                        250,
                        252
                )
        );

        row.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                10,
                                15,
                                10,
                                15
                        )
                )
        );

        JLabel left =
                new JLabel(
                        label
                );

        left.setForeground(
                TEXT
        );

        left.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        JLabel right =
                new JLabel(
                        value
                );

        right.setForeground(
                MUTED
        );

        right.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        row.add(
                left,
                BorderLayout.WEST
        );

        row.add(
                right,
                BorderLayout.EAST
        );

        return row;
    }

    // =========================================================
    // EMPTY MESSAGE
    // =========================================================

    private JPanel createEmptyMessage(
            String message
    ) {

        JPanel panel =
                new JPanel(
                        new GridBagLayout()
                );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                BorderFactory.createLineBorder(
                        BORDER
                )
        );

        JLabel label =
                new JLabel(
                        message
                );

        label.setForeground(
                MUTED
        );

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        panel.add(label);

        return panel;
    }

    // =========================================================
    // EXIT
    // =========================================================

    private void exitApplication() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to exit?",
                        "Exit Application",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

        if (
                result ==
                JOptionPane.YES_OPTION
        ) {

            System.exit(0);
        }
    }

    // =========================================================
    // REFRESH
    // =========================================================

    private void refresh() {

        contentPanel.revalidate();

        contentPanel.repaint();
    }

    // =========================================================
    // HOTEL LOGO
    // =========================================================

    static class HotelLogoIcon
            implements Icon {

        private final int size;
        private final Color building;
        private final Color roof;

        HotelLogoIcon(
                int size,
                Color building,
                Color roof
        ) {

            this.size = size;
            this.building = building;
            this.roof = roof;
        }

        public int getIconWidth() {

            return size;
        }

        public int getIconHeight() {

            return size;
        }

        public void paintIcon(
                Component c,
                Graphics g,
                int x,
                int y
        ) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(
                    roof
            );

            Polygon roofShape =
                    new Polygon();

            roofShape.addPoint(
                    x + size / 2,
                    y + 2
            );

            roofShape.addPoint(
                    x + size - 5,
                    y + 20
            );

            roofShape.addPoint(
                    x + 5,
                    y + 20
            );

            g2.fillPolygon(
                    roofShape
            );

            g2.setColor(
                    building
            );

            g2.fillRoundRect(
                    x + 10,
                    y + 17,
                    size - 20,
                    size - 20,
                    8,
                    8
            );

            g2.setColor(
                    NAVY
            );

            for (
                    int row = 0;
                    row < 3;
                    row++
            ) {

                for (
                        int col = 0;
                        col < 3;
                        col++
                ) {

                    g2.fillRoundRect(
                            x + 17 + col * 9,
                            y + 26 + row * 8,
                            5,
                            5,
                            2,
                            2
                    );
                }
            }

            g2.dispose();
        }
    }

    // =========================================================
    // HOTEL BUILDING
    // =========================================================

    static class HotelBuildingIcon
            implements Icon {

        private final int size;
        private final Color blue;
        private final Color gold;

        HotelBuildingIcon(
                int size,
                Color blue,
                Color gold
        ) {

            this.size = size;
            this.blue = blue;
            this.gold = gold;
        }

        public int getIconWidth() {

            return size;
        }

        public int getIconHeight() {

            return size;
        }

        public void paintIcon(
                Component c,
                Graphics g,
                int x,
                int y
        ) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(
                    new Color(
                            232,
                            240,
                            250
                    )
            );

            g2.fillRoundRect(
                    x + 10,
                    y + 10,
                    size - 20,
                    size - 20,
                    20,
                    20
            );

            g2.setColor(
                    blue
            );

            g2.fillRoundRect(
                    x + 45,
                    y + 25,
                    size - 90,
                    size - 40,
                    8,
                    8
            );

            g2.setColor(
                    gold
            );

            for (
                    int row = 0;
                    row < 4;
                    row++
            ) {

                for (
                        int col = 0;
                        col < 3;
                        col++
                ) {

                    g2.fillRect(
                            x + 55 + col * 22,
                            y + 38 + row * 25,
                            10,
                            12
                    );
                }
            }

            g2.setColor(
                    blue
            );

            g2.fillRect(
                    x + size / 2 - 15,
                    y + size - 50,
                    30,
                    50
            );

            g2.dispose();
        }
    }

    // =========================================================
    // HOME ICON
    // =========================================================

    static class HomeIcon
            implements Icon {

        private final int size;
        private final Color color;

        HomeIcon(
                int size,
                Color color
        ) {

            this.size = size;
            this.color = color;
        }

        public int getIconWidth() {

            return size;
        }

        public int getIconHeight() {

            return size;
        }

        public void paintIcon(
                Component c,
                Graphics g,
                int x,
                int y
        ) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setColor(
                    color
            );

            Polygon roof =
                    new Polygon();

            roof.addPoint(
                    x + size / 2,
                    y + 2
            );

            roof.addPoint(
                    x + size - 2,
                    y + size / 2
            );

            roof.addPoint(
                    x + 2,
                    y + size / 2
            );

            g2.fillPolygon(
                    roof
            );

            g2.fillRect(
                    x + 5,
                    y + size / 2 - 1,
                    size - 10,
                    size / 2
            );

            g2.setColor(
                    NAVY
            );

            g2.fillRect(
                    x + size / 2 - 4,
                    y + size / 2 + 6,
                    8,
                    10
            );

            g2.dispose();
        }
    }

    // =========================================================
    // BED ICON
    // =========================================================

    static class BedIcon
            implements Icon {

        private final int size;
        private final Color color;

        BedIcon(
                int size,
                Color color
        ) {

            this.size = size;
            this.color = color;
        }

        public int getIconWidth() {

            return size;
        }

        public int getIconHeight() {

            return size;
        }

        public void paintIcon(
                Component c,
                Graphics g,
                int x,
                int y
        ) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setColor(
                    color
            );

            g2.fillRoundRect(
                    x + 4,
                    y + size / 2,
                    size - 8,
                    size / 3,
                    5,
                    5
            );

            g2.fillRoundRect(
                    x + 7,
                    y + size / 3,
                    size / 3,
                    size / 4,
                    4,
                    4
            );

            g2.fillRect(
                    x + 4,
                    y + size / 3,
                    4,
                    size / 2
            );

            g2.fillRect(
                    x + 8,
                    y + size - 7,
                    4,
                    6
            );

            g2.fillRect(
                    x + size - 12,
                    y + size - 7,
                    4,
                    6
            );

            g2.dispose();
        }
    }

    // =========================================================
    // SEARCH ICON
    // =========================================================

    static class SearchIcon
            implements Icon {

        private final int size;
        private final Color color;

        SearchIcon(
                int size,
                Color color
        ) {

            this.size = size;
            this.color = color;
        }

        public int getIconWidth() {

            return size;
        }

        public int getIconHeight() {

            return size;
        }

        public void paintIcon(
                Component c,
                Graphics g,
                int x,
                int y
        ) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setColor(
                    color
            );

            g2.setStroke(
                    new BasicStroke(
                            3,
                            BasicStroke.CAP_ROUND,
                            BasicStroke.JOIN_ROUND
                    )
            );

            g2.drawOval(
                    x + 3,
                    y + 3,
                    size - 12,
                    size - 12
            );

            g2.drawLine(
                    x + size - 10,
                    y + size - 10,
                    x + size - 2,
                    y + size - 2
            );

            g2.dispose();
        }
    }

    // =========================================================
    // CALENDAR ICON
    // =========================================================

    static class CalendarIcon
            implements Icon {

        private final int size;
        private final Color color;

        CalendarIcon(
                int size,
                Color color
        ) {

            this.size = size;
            this.color = color;
        }

        public int getIconWidth() {

            return size;
        }

        public int getIconHeight() {

            return size;
        }

        public void paintIcon(
                Component c,
                Graphics g,
                int x,
                int y
        ) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setColor(
                    color
            );

            g2.setStroke(
                    new BasicStroke(
                            3
                    )
            );

            g2.drawRoundRect(
                    x + 3,
                    y + 5,
                    size - 6,
                    size - 8,
                    5,
                    5
            );

            g2.drawLine(
                    x + 3,
                    y + 14,
                    x + size - 3,
                    y + 14
            );

            g2.fillRect(
                    x + 8,
                    y + 1,
                    4,
                    9
            );

            g2.fillRect(
                    x + size - 12,
                    y + 1,
                    4,
                    9
            );

            g2.dispose();
        }
    }

    // =========================================================
    // USER ICON
    // =========================================================

    static class UserIcon
            implements Icon {

        private final int size;
        private final Color color;

        UserIcon(
                int size,
                Color color
        ) {

            this.size = size;
            this.color = color;
        }

        public int getIconWidth() {

            return size;
        }

        public int getIconHeight() {

            return size;
        }

        public void paintIcon(
                Component c,
                Graphics g,
                int x,
                int y
        ) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setColor(
                    color
            );

            g2.fillOval(
                    x + size / 3,
                    y + 2,
                    size / 3,
                    size / 3
            );

            g2.fillRoundRect(
                    x + size / 5,
                    y + size / 2,
                    size * 3 / 5,
                    size / 2 - 2,
                    10,
                    10
            );

            g2.dispose();
        }
    }

    // =========================================================
    // EXIT ICON
    // =========================================================

    static class ExitIcon
            implements Icon {

        private final int size;
        private final Color color;

        ExitIcon(
                int size,
                Color color
        ) {

            this.size = size;
            this.color = color;
        }

        public int getIconWidth() {

            return size;
        }

        public int getIconHeight() {

            return size;
        }

        public void paintIcon(
                Component c,
                Graphics g,
                int x,
                int y
        ) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setColor(
                    color
            );

            g2.setStroke(
                    new BasicStroke(
                            3,
                            BasicStroke.CAP_ROUND,
                            BasicStroke.JOIN_ROUND
                    )
            );

            g2.drawLine(
                    x + 3,
                    y + size / 2,
                    x + size - 3,
                    y + size / 2
            );

            g2.drawLine(
                    x + size - 3,
                    y + size / 2,
                    x + size - 11,
                    y + size / 2 - 7
            );

            g2.drawLine(
                    x + size - 3,
                    y + size / 2,
                    x + size - 11,
                    y + size / 2 + 7
            );

            g2.drawRoundRect(
                    x + 3,
                    y + 4,
                    size / 2,
                    size - 8,
                    5,
                    5
            );

            g2.dispose();
        }
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    try {

                        UIManager.setLookAndFeel(
                                UIManager
                                        .getSystemLookAndFeelClassName()
                        );

                    } catch (Exception ignored) {
                    }

                    Main window =
                            new Main();

                    window.setVisible(
                            true
                    );
                }
        );
    }
}