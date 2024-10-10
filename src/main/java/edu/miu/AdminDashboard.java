package edu.miu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDashboard {

    private static JFrame frame;
    private static JSplitPane splitPane;
    private static JList<String> navigationList;
    private static JPanel contentPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the Admin Dashboard with Admin and Librarian roles
            createAdminDashboard("Admin,Librarian");  // Admin with Librarian privileges
        });
    }

    public static void createAdminDashboard(String roles) {
        System.out.println(roles);
        frame = new JFrame("Admin Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);  // Center the window

        // Left navigation panel (JList)
        List<String> menuItems = new ArrayList<>();

        // Dynamic Menu based on roles
        if (roles.contains("ADMIN") || roles.contains("BOTH")) {
            menuItems.add("View Books");
            menuItems.add("Add New Book");
            menuItems.add("Manage Members");
        }
        if (roles.contains("BOTH")) {
            menuItems.add("Checkout Books");
            menuItems.add("View Checked Out Books");
        }
        menuItems.add("Logout");

        // Convert the list to an array for JList
        String[] menuArray = menuItems.toArray(new String[0]);

        // Create a JList for the navigation
        navigationList = new JList<>(menuArray);
        navigationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add a listener to handle menu selections
        navigationList.addListSelectionListener(e -> handleMenuSelection(navigationList.getSelectedValue()));

        // Right panel for content
        contentPanel = new JPanel(new BorderLayout());
        JLabel defaultLabel = new JLabel("Welcome to Admin Dashboard");
        defaultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(defaultLabel, BorderLayout.CENTER);

        // SplitPane to separate navigation and content
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(navigationList), contentPanel);
        splitPane.setDividerLocation(200);  // Set initial size for navigation pane

        // Add SplitPane to the frame
        frame.getContentPane().add(splitPane);
        frame.setVisible(true);
    }

    // Handle what happens when a menu item is selected
    private static void handleMenuSelection(String selectedMenu) {
        contentPanel.removeAll();  // Clear previous content

        switch (selectedMenu) {
            case "View Books":
                showViewBooksPage();
                // Add your View Books table or panel here
                break;
            case "Add New Book":
                showAddNewBookPage();
                // Add your Add Book form here
                break;
            case "Manage Members":
                showManageMembersPage();
                // Add your Manage Members panel here
                break;
            case "Checkout Books":
                contentPanel.add(new JLabel("Here you can checkout books"), BorderLayout.CENTER);
                // Add your Checkout Books form here
                break;
            case "View Checked Out Books":
                contentPanel.add(new JLabel("Here you can view checked out books"), BorderLayout.CENTER);
                // Add your View Checked Out Books panel here
                break;
            case "Logout":
                JOptionPane.showMessageDialog(frame, "Logging out...");
                frame.dispose();
                // You can redirect to the login page here
                LibraryApp.main(null);
                break;
        }


        // Refresh content panel
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private static void showViewBooksPage() {
        ViewBooksPage viewBooksPage = new ViewBooksPage();
        updateRightPanel(viewBooksPage.getPanel());
    }

    private static void showAddNewBookPage(){
        AddNewBookPage addNewBookPage = new AddNewBookPage();
        updateRightPanel(addNewBookPage.getPanel());
    }

    private static void showManageMembersPage(){
        ManageMembersPage manageMembersPage = new ManageMembersPage();
        updateRightPanel(manageMembersPage.getPanel());
    }

    // Update the right panel with new content
    private static void updateRightPanel(JPanel newContent) {
        contentPanel.removeAll();
        contentPanel.add(newContent, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}