package edu.miu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LibrarianDashboard {

    private static JFrame frame;
    private static JSplitPane splitPane;
    private static JPanel leftPanel;
    private static JPanel rightPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibrarianDashboard::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Librarian Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // SplitPane setup
        splitPane = new JSplitPane();
        splitPane.setDividerLocation(200);
        splitPane.setEnabled(false);  // Disallow resizing

        // Left Panel (Navigation)
        leftPanel = createLeftPanel();
        splitPane.setLeftComponent(leftPanel);

        // Right Panel (Content)
        rightPanel = new JPanel(new BorderLayout());
        splitPane.setRightComponent(rightPanel);

        // Default to View Books Page
        showViewBooksPage();

        // Add splitPane to the frame
        frame.getContentPane().add(splitPane);

        // Show the frame
        frame.setVisible(true);
    }

    private static JPanel createLeftPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] navOptions = {"View Books", "Checkout Books", "Logout"};

        JList<String> navList = new JList<>(navOptions);
        navList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        navList.setFont(new Font("Arial", Font.PLAIN, 16));

        navList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String selected = navList.getSelectedValue();
                if (selected != null) {
                    switch (selected) {
                        case "View Books":
                            showViewBooksPage();
                            break;
                        case "Checkout Books":
                            showCheckoutBooksPage();
                            break;
                        case "Logout":
                            JOptionPane.showMessageDialog(frame, "You have been logged out.");
                            frame.dispose();  // Close the application
                            LibraryApp.main(null);
                            break;
                    }
                }
            }
        });

        panel.add(navList, BorderLayout.CENTER);
        return panel;
    }

    // Method to show the View Books Page
    private static void showViewBooksPage() {
        ViewBooksPage viewBooksPage = new ViewBooksPage();
        updateRightPanel(viewBooksPage.getPanel());
    }

    // Method to show the Checkout Books Page
    private static void showCheckoutBooksPage() {
        CheckoutBooksPage checkoutBooksPage = new CheckoutBooksPage();
        updateRightPanel(checkoutBooksPage.getPanel());
    }

    // Update the right panel with new content
    private static void updateRightPanel(JPanel newContent) {
        rightPanel.removeAll();
        rightPanel.add(newContent, BorderLayout.CENTER);
        rightPanel.revalidate();
        rightPanel.repaint();
    }
}