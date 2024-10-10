package edu.miu;

import javax.swing.*;
import java.awt.*;

public class AddNewBookPage {

    private JPanel panel;

    public AddNewBookPage() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title input
        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(titleField, gbc);

        // ISBN input
        JLabel isbnLabel = new JLabel("ISBN:");
        JTextField isbnField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(isbnLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(isbnField, gbc);

        // Author input
        JLabel authorLabel = new JLabel("Author:");
        JTextField authorField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(authorLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(authorField, gbc);

        // Availability input
        JLabel availabilityLabel = new JLabel("Availability:");
        String[] availabilityOptions = {"Available", "Checked Out"};
        JComboBox<String> availabilityCombo = new JComboBox<>(availabilityOptions);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(availabilityLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(availabilityCombo, gbc);

        // Submit button
        JButton submitButton = new JButton("Add Book");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(submitButton, gbc);
    }

    public static JPanel getAddBookPanel(){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title input
        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(titleField, gbc);

        // ISBN input
        JLabel isbnLabel = new JLabel("ISBN:");
        JTextField isbnField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(isbnLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(isbnField, gbc);

        // Author input
        JLabel authorLabel = new JLabel("Author:");
        JTextField authorField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(authorLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(authorField, gbc);

        // Availability input
        JLabel availabilityLabel = new JLabel("Availability:");
        String[] availabilityOptions = {"Available", "Checked Out"};
        JComboBox<String> availabilityCombo = new JComboBox<>(availabilityOptions);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(availabilityLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(availabilityCombo, gbc);

        // Submit button
        JButton submitButton = new JButton("Add Book");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(submitButton, gbc);

        return panel;
    }

    public JPanel getPanel() {
        return panel;
    }
}