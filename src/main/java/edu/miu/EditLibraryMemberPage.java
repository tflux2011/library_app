package edu.miu;

import javax.swing.*;
import java.awt.*;

public class EditLibraryMemberPage {

    private JPanel panel;

    public EditLibraryMemberPage() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Member ID input (to search for the member to edit)
        JLabel memberIdLabel = new JLabel("Member ID:");
        JTextField memberIdField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(memberIdLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(memberIdField, gbc);

        // New Name input
        JLabel nameLabel = new JLabel("New Name:");
        JTextField nameField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(nameField, gbc);

        // New Email input
        JLabel emailLabel = new JLabel("New Email:");
        JTextField emailField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(emailLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(emailField, gbc);

        // New Phone number input
        JLabel phoneLabel = new JLabel("New Phone:");
        JTextField phoneField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(phoneField, gbc);

        // Submit button
        JButton submitButton = new JButton("Update Member");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(submitButton, gbc);
    }

    public JPanel getPanel() {
        return panel;
    }
}