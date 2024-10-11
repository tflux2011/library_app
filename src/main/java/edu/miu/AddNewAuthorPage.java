package edu.miu;

import edu.miu.Business.AuthorFactory;
import edu.miu.Model.Author;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class AddNewAuthorPage {

    private JPanel panel;
    JTextField firstNameField;
    JTextField lastNameField;
    JTextArea credentialsField;
    JTextArea bioField;
    JLabel messageLabel;

    public AddNewAuthorPage() {
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


        //Credentials
        JLabel credentialsLabel = new JLabel("Credentials:");
        credentialsField = new JTextArea(5, 20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(credentialsLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(credentialsField, gbc);

        //Credentials
        JLabel bioLabel = new JLabel("Bio:");
        bioField = new JTextArea(5, 20);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(bioLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(bioField, gbc);

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
        JButton submitButton = new JButton("Add Author");
        addLoginButtonListener(submitButton);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(submitButton, gbc);

        messageLabel = new JLabel("");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(messageLabel, gbc);
    }

    public void addLoginButtonListener(JButton btn){
        btn.addActionListener(evt -> {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String credentials = credentialsField.getText().trim();
            String bio = bioField.getText().trim();
            Author author = new Author(firstName, lastName, credentials,bio);
            var res = AuthorFactory.addAuthor(author);

            messageLabel.setText(res);

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