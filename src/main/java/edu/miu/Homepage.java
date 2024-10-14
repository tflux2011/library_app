package edu.miu;

import edu.miu.Business.BookFactory;
import edu.miu.Business.CheckoutRecordFactory;
import edu.miu.Business.LibraryMemberFactory;

import javax.swing.*;
import java.awt.*;

public class Homepage{

    private JPanel panel;
    private JLabel messageLabel;

    public Homepage() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components

        Font labelFont = new Font("Arial", Font.PLAIN, 16); // Common font

        // Title Label
        JLabel titleLabel = new JLabel("Library Management System - Home");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Larger font for title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        // Reset grid width for subsequent components
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // App Information Section
        JLabel appInfoLabel = new JLabel("🛠️ Built Using:");
        appInfoLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(appInfoLabel, gbc);

        JTextArea technologiesArea = new JTextArea(
                "- Java (Backend with Swing UI)\n" +
                        "- Role-based access: Admin, Librarian\n" +
                        "- SMS/Email Notification System (Coming soon)"
        );
        technologiesArea.setFont(new Font("Arial", Font.PLAIN, 14));
        technologiesArea.setEditable(false);
        technologiesArea.setOpaque(false);
        gbc.gridx = 1;
        panel.add(technologiesArea, gbc);

        // Statistics Section
        JLabel statsLabel = new JLabel("📊 Current Statistics:");
        statsLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(statsLabel, gbc);
        var totalNumOfBooks = BookFactory.getAllBooks().size();
        var totalNumOfMembers = LibraryMemberFactory.getAllMembers().size();
        var totalNumOfCheckedOutBooks = CheckoutRecordFactory.getAllCheckoutRecords().size();
        var totalNumOfOverdueBooks = CheckoutRecordFactory.getOverdueBooks().size();
        JTextArea statsArea = new JTextArea(
                "- Total Books: "+totalNumOfBooks+"\n" +
                        "- Registered Members: "+totalNumOfMembers+"\n" +
                        "- Books Borrowed Today: "+totalNumOfCheckedOutBooks+"\n" +
                        "- Overdue Books: "+totalNumOfOverdueBooks
        );
        statsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        statsArea.setEditable(false);
        statsArea.setOpaque(false);
        gbc.gridx = 1;
        panel.add(statsArea, gbc);

        // Message Label for Feedback
        messageLabel = new JLabel("Built with 🔥 from LoTo group, © 2024");
//        messageLabel.setForeground(Color.RED); // Red for error messages
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(messageLabel, gbc);

    }

    // Listener for Logout Button
    private void addLogoutButtonListener(JButton btn) {
        btn.addActionListener(e -> System.exit(0));
    }

    // Listener for Proceed Button
    private void addProceedButtonListener(JButton btn) {
        btn.addActionListener(e -> {
            messageLabel.setForeground(new Color(0, 128, 0)); // Green for success
            messageLabel.setText("✔️ Proceeding to the dashboard...");
            // Logic to navigate to the dashboard screen can go here
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Home Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);  // Responsive size
        frame.setLocationRelativeTo(null);

        Homepage homepage = new Homepage();
        frame.add(homepage.getPanel());
        frame.setVisible(true);
    }
}
