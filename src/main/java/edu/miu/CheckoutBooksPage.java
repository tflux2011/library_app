package edu.miu;

import edu.miu.Business.BookFactory;
import edu.miu.Model.Address;

import javax.swing.*;
import java.awt.*;

public class CheckoutBooksPage {

    private JPanel panel;
    private JTextField isbnField;
    private JTextField memberIdField;
    private JLabel messageLabel;

    public CheckoutBooksPage() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Book ISBN input
        JLabel isbnLabel = new JLabel("Book ISBN:");
        isbnField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(isbnLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(isbnField, gbc);

        // Member ID input
        JLabel memberIdLabel = new JLabel("Member ID:");
        memberIdField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(memberIdLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(memberIdField, gbc);

        // Checkout button
        JButton submitButton = new JButton("Submit");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(submitButton, gbc);

        addLoginButtonListener(submitButton);

        messageLabel = new JLabel("");
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        panel.add(messageLabel, gbc);
    }

    public void addLoginButtonListener(JButton btn){
        btn.addActionListener(evt -> {
            String isbn = isbnField.getText().trim();
            String memberID = memberIdField.getText().trim();

            var res = BookFactory.checkoutBook(isbn, memberID);
            messageLabel.setText(res);

            isbnField.setText("");
            memberIdField.setText("");

        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
