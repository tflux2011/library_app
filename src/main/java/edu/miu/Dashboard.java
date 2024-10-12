package edu.miu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Dashboard {

    private static JFrame frame;
    private static JSplitPane splitPane;
    private static JList<MenuItem> navigationList;
    private static JPanel contentPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the Admin Dashboard with Admin and Librarian roles
            createAdminDashboard("Admin,Librarian");  // Admin with Librarian privileges
        });
    }

    public static void createAdminDashboard(String roles) {
        frame = new JFrame("Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);  // Center the window

        // Left navigation panel (JList)
        List<MenuItem> menuItems = new ArrayList<>();

        // Dynamic Menu based on roles
        if (roles.contains("ADMIN") || roles.contains("BOTH")) {
            menuItems.add(new MenuItem("📚 All Books"));
            menuItems.add(new MenuItem("🖊️ Add New Author"));
            menuItems.add(new MenuItem("📖 Add New Book"));
            menuItems.add(new MenuItem("📖 Add Book Copy"));
            menuItems.add(new MenuItem("👤 Add New Member"));
            menuItems.add(new MenuItem("👥 Manage Members"));
        }
        if (roles.contains("BOTH") || roles.contains("LIBRARIAN")) {
            menuItems.add(new MenuItem("📥 Checkout Books"));
            menuItems.add(new MenuItem("📜 View Checked Out Books"));
        }
        menuItems.add(new MenuItem("🚪 Logout"));

        // Create a JList for the navigation
        navigationList = new JList<>(menuItems.toArray(new MenuItem[0]));
        navigationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        navigationList.setCellRenderer(new MenuItemRenderer());  // Custom renderer for JList items
        navigationList.setFixedCellHeight(50);  // Set fixed height for each item

        // Add a listener to handle menu selections
        navigationList.addListSelectionListener(e -> handleMenuSelection(navigationList.getSelectedValue().getText()));

        // Right panel for content
        contentPanel = new JPanel(new BorderLayout());
        JLabel defaultLabel = new JLabel("Welcome to Admin Dashboard");
        defaultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(defaultLabel, BorderLayout.CENTER);

        // SplitPane to separate navigation and content
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(navigationList), contentPanel);
        splitPane.setDividerLocation(200);  // Set initial size for navigation pane

        navigationList.setSelectedIndex(0);
        // Add SplitPane to the frame
        frame.getContentPane().add(splitPane);
        frame.setVisible(true);
    }

    // Handle what happens when a menu item is selected
    private static void handleMenuSelection(String selectedMenu) {
        contentPanel.removeAll();  // Clear previous content

        switch (selectedMenu) {
            case "📚 All Books":
                showViewBooksPage();
                break;
            case "📖 Add New Book":
                showAddNewBookPage();
                break;
            case "📖 Add Book Copy":
                showAddBookCopyPage();
                break;
            case "🖊️ Add New Author":
                showAddNewAuthorPage();
                break;
            case "👤 Add New Member":
                showAddNewMemberPage();
                break;
            case "👥 Manage Members":
                showManageMembersPage();
                break;
            case "📥 Checkout Books":
                showCheckoutPage();
                break;
            case "📜 View Checked Out Books":
                showViewCheckoutOutBooksPage();
                break;
            case "🔒 Logout":
                JOptionPane.showMessageDialog(frame, "Logging out...");
                frame.dispose();
                LibraryApp.main(null);
                break;
        }

        // Refresh content panel
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private static void showAddBookCopyPage(){
        ViewAddBookCopyPage viewAddBookCopyPage = new ViewAddBookCopyPage();
        updateRightPanel(viewAddBookCopyPage.getPanel());
    }
    private static void showViewCheckoutOutBooksPage() {
        ViewCheckedOutBooksPage viewCheckedOutBooksPage = new ViewCheckedOutBooksPage();
        updateRightPanel(viewCheckedOutBooksPage.getPanel());
    }

    private static void showCheckoutPage() {
        CheckoutBooksPage checkoutBooksPage = new CheckoutBooksPage();
        updateRightPanel(checkoutBooksPage.getPanel());
    }

    private static void showAddNewMemberPage() {
        AddNewMemberPage memberPage = new AddNewMemberPage();
        updateRightPanel(memberPage.getPanel());
    }

    private static void showAddNewAuthorPage() {
        AddNewAuthorPage addNewAuthorPage = new AddNewAuthorPage();
        updateRightPanel(addNewAuthorPage.getPanel());
    }

    private static void showViewBooksPage() {
        ViewBooksPage viewBooksPage = new ViewBooksPage();
        updateRightPanel(viewBooksPage.getPanel());
    }

    private static void showAddNewBookPage() {
        AddNewBookPage addNewBookPage = new AddNewBookPage();
        updateRightPanel(addNewBookPage.getPanel());
    }

    private static void showManageMembersPage() {
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

    // Inner class for menu item representation
    private static class MenuItem {
        private String text;

        public MenuItem(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return text;  // Display text in JList
        }
    }

    // Custom renderer for JList items
    private static class MenuItemRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            MenuItem menuItem = (MenuItem) value;

            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setText(menuItem.getText());
            label.setHorizontalTextPosition(SwingConstants.LEFT);
            label.setFont(label.getFont().deriveFont(13f));

            return label;
        }
    }
}