package edu.miu;

import javax.swing.*;
import java.awt.*;

public class CheckoutBooksPage {

    private JPanel panel;

    public CheckoutBooksPage() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Book ISBN input
        JLabel isbnLabel = new JLabel("Book ISBN:");
        JTextField isbnField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(isbnLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(isbnField, gbc);

        // Member ID input
        JLabel memberIdLabel = new JLabel("Member ID:");
        JTextField memberIdField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(memberIdLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(memberIdField, gbc);

        // Checkout button
        JButton checkoutButton = new JButton("Checkout Book");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(checkoutButton, gbc);
    }

    public JPanel getPanel() {
        return panel;
    }
}
