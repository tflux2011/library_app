package edu.miu;

import javax.swing.*;
import java.awt.*;

public class CreateLibraryMemberPage {

    private JPanel panel;

    public CreateLibraryMemberPage() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Name input
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(nameField, gbc);

        // Email input
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(emailLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(emailField, gbc);

        // Phone number input
        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(phoneField, gbc);

        // Submit button
        JButton submitButton = new JButton("Create Member");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(submitButton, gbc);
    }

    public JPanel getPanel() {
        return panel;
    }
}
