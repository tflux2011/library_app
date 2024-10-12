package edu.miu;

import edu.miu.Business.BookFactory;
import edu.miu.Business.CheckoutRecordFactory;

import javax.swing.*;
import java.awt.*;

public class ViewAddBookCopyPage {

    private JPanel panel;
    private JTextField isbnField;
    private JTextField memberIdField;
    private JLabel messageLabel;

    public ViewAddBookCopyPage() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components

        Font labelFont = new Font("Arial", Font.PLAIN, 16); // Common label font

        // Title Label
        JLabel titleLabel = new JLabel("Checkout Book");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Larger font for title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        // Reset grid width and alignment for inputs
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Book ISBN input
        JLabel isbnLabel = new JLabel("🔖 Book ISBN:");
        isbnLabel.setFont(labelFont); // Set font size 16
        isbnField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(isbnLabel, gbc);
        gbc.gridx = 1;
        panel.add(isbnField, gbc);

        // Member ID input
        JLabel memberIdLabel = new JLabel("🆔 Member ID:");
        memberIdLabel.setFont(labelFont); // Set font size 16
        memberIdField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(memberIdLabel, gbc);
        gbc.gridx = 1;
        panel.add(memberIdField, gbc);

        // Checkout button
        JButton submitButton = new JButton("✅ Checkout");
        submitButton.setFont(submitButton.getFont().deriveFont(16f)); // Slightly larger button text
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        addSubmitButtonListener(submitButton);
        panel.add(submitButton, gbc);

        // Message label for feedback
        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED); // Red for error messages
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(messageLabel, gbc);
    }

    // Listener for the submit button
    private void addSubmitButtonListener(JButton btn) {
        btn.addActionListener(evt -> {
            messageLabel.setText(""); // Clear previous message

            String isbn = isbnField.getText().trim();
            String memberIdText = memberIdField.getText().trim();

            // Validate inputs
            if (isbn.isEmpty() || memberIdText.isEmpty()) {
                messageLabel.setText("⚠️ All fields are required.");
                return;
            }

            if (!isbn.matches("\\d{10}|\\d{13}")) {
                messageLabel.setText("⚠️ ISBN must be 10 or 13 digits.");
                return;
            }

            try {
                int memberID = Integer.parseInt(memberIdText);

                try{
                    CheckoutRecordFactory.addCheckoutEntry(memberID, isbn);

                    messageLabel.setForeground(new Color(0, 128, 0)); // Green for success
                    messageLabel.setText("🎉 Checkout was successful!");


                    // Clear fields after successful checkout
                    isbnField.setText("");
                    memberIdField.setText("");
                }catch(Exception e){
                    messageLabel.setText("⚠️ Checkout was not successful, no copy of book available.");
                    messageLabel.setForeground(new Color(255, 0, 0));
                }



            } catch (NumberFormatException e) {
                messageLabel.setText("⚠️ Member ID must be a number.");
            } catch (Exception ex) {
                messageLabel.setText("⚠️ " + ex.getMessage());
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}