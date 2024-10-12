package edu.miu;

import edu.miu.Business.AuthorFactory;
import edu.miu.Business.BookFactory;
import edu.miu.Model.Author;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddNewBookPage {

    private JPanel panel;
    private JTextField titleField;
    private JTextField isbnField;
    private JScrollPane authorScrollPane;
    private JComboBox<Integer> maxCheckoutLengthField; // Corrected type
    private JTextField numOfCopiesField;
    private JLabel messageLabel;

    public AddNewBookPage() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("Add New Book");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size and style
        gbc.gridx = 0; // Reset column for title
        gbc.gridy = 0; // Row for title
        gbc.gridwidth = 2; // Span across both columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the title
        panel.add(titleLabel, gbc); // Add title to the panel

        // Reset grid width for other components
        gbc.gridwidth = 1;

        // Title input
        JLabel bookTitleLabel = new JLabel("📖 Title:");
        bookTitleLabel.setForeground(Color.BLACK);  // Set label color to BLACK
        titleField = new JTextField(20);
        bookTitleLabel.setFont(bookTitleLabel.getFont().deriveFont(16f));  // Increase font size
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(bookTitleLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(titleField, gbc);

        // ISBN input
        JLabel isbnLabel = new JLabel("📚 ISBN:");
        isbnLabel.setForeground(Color.BLACK);  // Set label color to BLACK
        isbnField = new JTextField(20);
        isbnLabel.setFont(isbnLabel.getFont().deriveFont(16f));  // Increase font size
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(isbnLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(isbnField, gbc);

        // Author input
        JLabel authorLabel = new JLabel("✍️ Author:");
        authorLabel.setForeground(Color.BLACK);  // Set label color to BLACK
        List<Author> authors = AuthorFactory.getAllAuthors();
        Author[] authorArray = authors.toArray(new Author[0]);

        JList<Author> authorList = new JList<>(authorArray);
        authorList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Allow multiple selections
        authorScrollPane = new JScrollPane(authorList);
        authorLabel.setFont(authorLabel.getFont().deriveFont(16f));  // Increase font size
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(authorLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(authorScrollPane, gbc);

        // Max Checkout Length input
        JLabel maxCheckoutLengthLabel = new JLabel("⏳ Max Checkout (days):");
        maxCheckoutLengthLabel.setForeground(Color.BLACK);
        maxCheckoutLengthLabel.setFont(maxCheckoutLengthLabel.getFont().deriveFont(16f));  // Increase font size
        Integer[] days = {7, 21}; // Corrected type
        maxCheckoutLengthField = new JComboBox<>(days);
        gbc.gridx = 0;
        gbc.gridy = 4; // Changed to the next row
        panel.add(maxCheckoutLengthLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4; // Changed to the next row
        panel.add(maxCheckoutLengthField, gbc);

        // Number of Copies input
        JLabel numOfCopiesLabel = new JLabel("📦 No of Copies:");
        numOfCopiesLabel.setForeground(Color.BLACK);  // Set label color to BLACK
        numOfCopiesField = new JTextField(20);
        numOfCopiesLabel.setFont(numOfCopiesLabel.getFont().deriveFont(16f));  // Increase font size
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(numOfCopiesLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(numOfCopiesField, gbc);

        // Submit button
        JButton submitButton = new JButton("✅ Submit");
        submitButton.setFont(submitButton.getFont().deriveFont(16f));  // Increase button font size
        addSubmitButtonListener(submitButton);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(submitButton, gbc);

        // Message label for feedback
        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED);  // Set error message color to red
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        panel.add(messageLabel, gbc);
    }

    public void addSubmitButtonListener(JButton btn) {
        btn.addActionListener(evt -> {
            // Clear previous message
            messageLabel.setText("");

            String title = titleField.getText().trim();
            String isbn = isbnField.getText().trim();
            JList<Author> list = (JList<Author>) authorScrollPane.getViewport().getView();
            List<Author> listOfAuthors = new ArrayList<>();

            // Validate inputs
            if (title.isEmpty()) {
                messageLabel.setText("Please enter a title.");
                return;
            }
            if (isbn.isEmpty()) {
                messageLabel.setText("Please enter an ISBN.");
                return;
            }
            if (maxCheckoutLengthField.getSelectedItem() == null) {
                messageLabel.setText("Please select max checkout length.");
                return;
            }
            if (numOfCopiesField.getText().trim().isEmpty()) {
                messageLabel.setText("Please enter number of copies.");
                return;
            }

//            if (!isbn.matches("\\d{10}|\\d{13}")) {
            if (isbn.length() != 10 && isbn.length() != 13) {
                messageLabel.setText("⚠️ ISBN must be exactly 10 or 13 characters.");
                return;
            }

            // Get the selected values from the JList
            List<Author> selectedValues = list.getSelectedValuesList();
            if (selectedValues.isEmpty()) {
                messageLabel.setText("Please select at least one author.");
                return;
            }
            for (Author selectedAuthor : selectedValues) {
                listOfAuthors.add(selectedAuthor);
            }

            // Parse numeric inputs and handle exceptions
            try {
                int maxCheckoutLength = (int) maxCheckoutLengthField.getSelectedItem(); // Get selected item as int
                int numOfCopies = Integer.parseInt(numOfCopiesField.getText().trim());

                var res = BookFactory.addBook(isbn, title, listOfAuthors, maxCheckoutLength, numOfCopies);
                if(res) {
                    messageLabel.setText("Book was created successfully.");
                    messageLabel.setForeground(new Color(0, 128, 0)); // Green for success
                }else{
                    messageLabel.setText("Book copy was updated successfully.");
                    messageLabel.setForeground(new Color(0, 128, 0)); // Green for success
                }

                // Clear fields after submission
                isbnField.setText("");
                titleField.setText("");
                numOfCopiesField.setText("");
                list.clearSelection();  // Clear selected authors
            } catch (NumberFormatException e) {
                messageLabel.setText("Please enter valid numbers for max checkout length and number of copies.");
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}