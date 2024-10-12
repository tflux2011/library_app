package edu.miu;

import edu.miu.Business.BookFactory;
import edu.miu.Business.CheckoutRecordFactory;

import javax.swing.*;
import java.awt.*;

public class ViewAddBookCopyPage {

    private JPanel panel;
    private JTextField isbnField;
    private JTextField numOfCopiesField;
    private JLabel messageLabel;
    private JLabel searchMessageLabel;

    public ViewAddBookCopyPage() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components

        Font labelFont = new Font("Arial", Font.PLAIN, 16); // Common label font

        // Title Label
        JLabel titleLabel = new JLabel("Add Book Copy");
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
        JButton searchButton = new JButton("🔍 Search");
        gbc.gridx = 2;
        panel.add(searchButton, gbc);

        // Message label for feedback
        searchMessageLabel = new JLabel("");
        searchMessageLabel.setForeground(Color.RED); // Red for error messages
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(searchMessageLabel, gbc);

        // Reset grid width and alignment for inputs
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Number of copies input
        JLabel numOfCopiesLabel = new JLabel("🆔 Num of Copies:");
        numOfCopiesLabel.setFont(labelFont); // Set font size 16
        numOfCopiesField = new JTextField(20);
        numOfCopiesField.setEnabled(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(numOfCopiesLabel, gbc);
        gbc.gridx = 1;
        panel.add(numOfCopiesField, gbc);

        // Checkout button
        JButton submitButton = new JButton("✅ Submit");
        submitButton.setFont(submitButton.getFont().deriveFont(16f)); // Slightly larger button text
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;

        addSearchButtonListener(searchButton);
        addSubmitButtonListener(submitButton);
        panel.add(submitButton, gbc);

        // Message label for feedback
        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED); // Red for error messages
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(messageLabel, gbc);
    }

    private void addSearchButtonListener(JButton btn){
        btn.addActionListener(evt -> {
            String isbn = isbnField.getText().trim();
            if (isbn.isEmpty()) {
                searchMessageLabel.setText("⚠️ ISBN is required.");
                return;
            }
            try {
                var res = BookFactory.getBookByIsbn(isbn);
                searchMessageLabel.setText(res.get().getTitle() + " was found, please specify number of copies.");
                numOfCopiesField.setEnabled(true);
                searchMessageLabel.setForeground(new Color(0, 128, 0));
            }catch(Exception e){
                searchMessageLabel.setText("Sorry, book with "+isbn+" was not found.");
                searchMessageLabel.setForeground(new Color(255, 0, 0));
            }
        });
    }
    // Listener for the submit button
    private void addSubmitButtonListener(JButton btn) {
        btn.addActionListener(evt -> {
            messageLabel.setText(""); // Clear previous message

            String isbn = isbnField.getText().trim();
            String numOfCopies = numOfCopiesField.getText().trim();

            // Validate inputs
            if (isbn.isEmpty() || numOfCopies.isEmpty()) {
                messageLabel.setText("⚠️ All fields are required.");
                return;
            }

//            if (!isbn.matches("\\d{10}|\\d{13}")) {
//                messageLabel.setText("⚠️ ISBN must be 10 or 13 digits.");
//                return;
//            }

            try {
                int numOfCop = Integer.parseInt(numOfCopies);
                try{
                    BookFactory.addBookCopies(isbn, numOfCop);

                    messageLabel.setForeground(new Color(0, 128, 0)); // Green for success
                    messageLabel.setText("🎉 "+numOfCop+" new copies were added to book with ISBN: "+isbn);

                    // Clear fields after successful checkout
                    isbnField.setText("");
                    numOfCopiesField.setText("");
                }catch(Exception e){
                    messageLabel.setText("⚠️ Sorry, Book copies were not added successfully!");
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