package edu.miu;

import edu.miu.DAO.LibraryMemberDAO;
import edu.miu.Model.Address;
import edu.miu.Model.LibraryMember;

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
    JTextField stateField;
    JTextField zipField;
    JLabel messageLabel;

    public AddNewMemberPage() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Top, Left, Bottom, Right paddin

        // First name input
        JLabel firstNameLabel = new JLabel("First name:");
        firstNameField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(firstNameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(firstNameField, gbc);

        // Last name input
        JLabel lastNameLabel = new JLabel("Last name:");
        lastNameField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lastNameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(lastNameField, gbc);

        //Phone Number
        JLabel phoneNumberLabel = new JLabel("Phone number:");
        phoneNumberField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(phoneNumberLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(phoneNumberField, gbc);

        //Street
        JLabel streetLabel = new JLabel("Street:");
        streetField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(streetLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(streetField, gbc);

        //City
        JLabel cityLabel = new JLabel("City:");
        cityField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(cityLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(cityField, gbc);

        JLabel stateLabel = new JLabel("State:");
        stateField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(stateLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(stateField, gbc);

        JLabel zipLabel = new JLabel("Zip:");
        zipField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(zipLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(zipField, gbc);




        // Author input
//        JLabel authorLabel = new JLabel("Author:");
//        String[] authors = {"Author 1", "Author 2", "Author 3", "Author 4"};
//        JList<String> authorList = new JList<>(authors);
//        authorList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Allow multiple selections
//        JScrollPane authorScrollPane = new JScrollPane(authorList);
//
//
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        panel.add(authorLabel, gbc);
//        gbc.gridx = 1;
//        gbc.gridy = 2;
//        panel.add(authorScrollPane, gbc);


        // Submit button
        JLabel emptyLabel = new JLabel("");
        JButton submitButton = new JButton("Submit");
        addLoginButtonListener(submitButton);
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(emptyLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(submitButton, gbc);

        messageLabel = new JLabel("");
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        panel.add(messageLabel, gbc);
    }

    public void addLoginButtonListener(JButton btn){
        btn.addActionListener(evt -> {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String phone = phoneNumberField.getText().trim();
            String street = streetField.getText().trim();
            String city = cityField.getText().trim();
            String state = stateField.getText().trim();
            String zip = zipField.getText().trim();

            Address address = new Address(street, city,state,zip);
            var member = LibraryMemberDAO.addMember(firstName,lastName,address,phone);
            messageLabel.setText(member);

            firstNameField.setText("");
            lastNameField.setText("");
            phoneNumberField.setText("");
            streetField.setText("");
            cityField.setText("");
            stateField.setText("");
            zipField.setText("");
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}