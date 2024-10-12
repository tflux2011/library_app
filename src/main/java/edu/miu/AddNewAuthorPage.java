package edu.miu;

import edu.miu.Business.AuthorFactory;
import edu.miu.Model.Author;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class AddNewAuthorPage {

    private JPanel panel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextArea credentialsField;
    private JTextArea bioField;
    private JLabel messageLabel;

    public AddNewAuthorPage() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Margin for all components
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding around the panel

        // Set common label font size
        Font labelFont = new Font("Arial", Font.PLAIN, 16);

        // Title Label
        JLabel titleLabel = new JLabel("Add New Author");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size and style
        gbc.gridx = 0; // Reset column for title
        gbc.gridy = 0; // Row for title
        gbc.gridwidth = 2; // Span across both columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the title
        panel.add(titleLabel, gbc); // Add title to the panel

        // First Name Input
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel firstNameLabel = new JLabel("👤 First Name:");
        firstNameLabel.setFont(labelFont);
        panel.add(firstNameLabel, gbc);

        gbc.gridx = 1;
        firstNameField = new JTextField(20);
        panel.add(firstNameField, gbc);

        // Last Name Input
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lastNameLabel = new JLabel("👤 Last Name:");
        lastNameLabel.setFont(labelFont);
        panel.add(lastNameLabel, gbc);

        gbc.gridx = 1;
        lastNameField = new JTextField(20);
        panel.add(lastNameField, gbc);

        // Credentials Input
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel credentialsLabel = new JLabel("🎓 Credentials:");
        credentialsLabel.setFont(labelFont);
        panel.add(credentialsLabel, gbc);

        gbc.gridx = 1;
        credentialsField = new JTextArea(5, 20);
        credentialsField.setLineWrap(true);
        credentialsField.setWrapStyleWord(true);
        JScrollPane credentialsScroll = new JScrollPane(credentialsField);
        panel.add(credentialsScroll, gbc);

        // Bio Input
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel bioLabel = new JLabel("📝 Bio:");
        bioLabel.setFont(labelFont);
        panel.add(bioLabel, gbc);

        gbc.gridx = 1;
        bioField = new JTextArea(5, 20);
        bioField.setLineWrap(true);
        bioField.setWrapStyleWord(true);
        JScrollPane bioScroll = new JScrollPane(bioField);
        panel.add(bioScroll, gbc);

        // Submit Button
        JButton submitButton = new JButton("✅ Submit");
        submitButton.setFont(submitButton.getFont().deriveFont(16f)); // Button font size
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        addSubmitButtonListener(submitButton);
        panel.add(submitButton, gbc);

        // Message Label for Feedback
        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(messageLabel, gbc);
    }

    private void addSubmitButtonListener(JButton submitButton) {
        submitButton.addActionListener(evt -> {
            // Clear previous message
            messageLabel.setText("");

            // Retrieve input values
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String credentials = credentialsField.getText().trim();
            String bio = bioField.getText().trim();

            // Validate fields
            if (firstName.isEmpty() || lastName.isEmpty() || credentials.isEmpty() || bio.isEmpty()) {
                messageLabel.setText("⚠️ All fields are required.");
                return;
            }

            // Create a new author and submit
            String response = AuthorFactory.addAuthor(firstName, lastName, credentials, bio);
            messageLabel.setText(response);

            // Clear fields after successful submission
            firstNameField.setText("");
            lastNameField.setText("");
            credentialsField.setText("");
            bioField.setText("");
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}