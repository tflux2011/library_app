package edu.miu;

import edu.miu.Business.LibraryMemberFactory;
import edu.miu.Model.Address;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class AddNewMemberPage {

    private JPanel panel;
    JTextField firstNameField;
    JTextField lastNameField;
    JTextField phoneNumberField;
    JTextField streetField;
    JTextField cityField;
    JComboBox<String> stateComboBox;
    JTextField zipField;
    JLabel messageLabel;

    // List of all U.S. states
    private static final String[] STATES = {
            "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
            "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
            "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan",
            "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire",
            "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio",
            "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
            "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia",
            "Wisconsin", "Wyoming"
    };

    public AddNewMemberPage() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components

        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Panel padding

        Font labelFont = new Font("Arial", Font.PLAIN, 16);

        // Title Label
        JLabel titleLabel = new JLabel("Add New Member");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Font size and style
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // First Name Input
        JLabel firstNameLabel = new JLabel("👤 First Name:");
        firstNameLabel.setFont(labelFont);
        firstNameField = new JTextField(20);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(firstNameLabel, gbc);
        gbc.gridx = 1;
        panel.add(firstNameField, gbc);

        // Last Name Input
        JLabel lastNameLabel = new JLabel("👤 Last Name:");
        lastNameLabel.setFont(labelFont);
        lastNameField = new JTextField(20);
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(lastNameLabel, gbc);
        gbc.gridx = 1;
        panel.add(lastNameField, gbc);

        // Phone Number Input
        JLabel phoneNumberLabel = new JLabel("📱 Phone Number:");
        phoneNumberLabel.setFont(labelFont);
        phoneNumberField = new JTextField(20);
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(phoneNumberLabel, gbc);
        gbc.gridx = 1;
        panel.add(phoneNumberField, gbc);

        // Street Input
        JLabel streetLabel = new JLabel("🏠 Street:");
        streetLabel.setFont(labelFont);
        streetField = new JTextField(20);
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(streetLabel, gbc);
        gbc.gridx = 1;
        panel.add(streetField, gbc);

        // City Input
        JLabel cityLabel = new JLabel("🏙️ City:");
        cityLabel.setFont(labelFont);
        cityField = new JTextField(20);
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(cityLabel, gbc);
        gbc.gridx = 1;
        panel.add(cityField, gbc);

        // State Dropdown
        JLabel stateLabel = new JLabel("🌎 State:");
        stateLabel.setFont(labelFont);
        stateComboBox = new JComboBox<>(STATES);
        stateComboBox.setSelectedIndex(-1); // Default to no selection
        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(stateLabel, gbc);
        gbc.gridx = 1;
        panel.add(stateComboBox, gbc);

        // Zip Input
        JLabel zipLabel = new JLabel("📮 Zip:");
        zipLabel.setFont(labelFont);
        zipField = new JTextField(20);
        gbc.gridx = 0; gbc.gridy = 7;
        panel.add(zipLabel, gbc);
        gbc.gridx = 1;
        panel.add(zipField, gbc);

        // Submit Button
        JButton submitButton = new JButton("✅ Submit");
        submitButton.setFont(submitButton.getFont().deriveFont(16f));
        gbc.gridx = 1; gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.EAST;
        addSubmitButtonListener(submitButton);
        panel.add(submitButton, gbc);

        // Message Label for Feedback
        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED);
        gbc.gridx = 0; gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(messageLabel, gbc);
    }

    // Adds a listener to the submit button
    private void addSubmitButtonListener(JButton btn) {
        btn.addActionListener(evt -> {
            messageLabel.setText("");

            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String phone = phoneNumberField.getText().trim();
            String street = streetField.getText().trim();
            String city = cityField.getText().trim();
            String state = (String) stateComboBox.getSelectedItem();
            String zip = zipField.getText().trim();

            if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() ||
                    street.isEmpty() || city.isEmpty() || state == null || zip.isEmpty()) {
                messageLabel.setText("⚠️ All fields are required.");
                return;
            }

            if (!phone.matches("\\d{10}")) {
                messageLabel.setText("⚠️ Phone number must be 10 digits.");
                return;
            }

            if (!zip.matches("\\d{5}")) {
                messageLabel.setText("⚠️ Zip code must be 5 digits.");
                return;
            }

            boolean result = LibraryMemberFactory.addMember(firstName, lastName, phone, street, city, state, zip);
            if(result) {
                firstNameField.setText("");
                lastNameField.setText("");
                phoneNumberField.setText("");
                streetField.setText("");
                cityField.setText("");
                stateComboBox.setSelectedIndex(-1); // Reset dropdown
                zipField.setText("");
                messageLabel.setText("Member was created successfully.");
            }else{
                messageLabel.setText("Sorry, could not create member.");
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}