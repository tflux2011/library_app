package edu.miu;

import edu.miu.DAO.AuthorDAO;
import edu.miu.DAO.BookDAO;
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
    private JTextField maxCheckoutLengthField;
    private JTextField numOfCopiesField;
    private JLabel messageLabel;

    public AddNewBookPage() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title input
        JLabel titleLabel = new JLabel("Title:");
         titleField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(titleField, gbc);

        // ISBN input
        JLabel isbnLabel = new JLabel("ISBN:");
         isbnField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(isbnLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(isbnField, gbc);

        // Author input
        JLabel authorLabel = new JLabel("Author:");
        List<Author> authors = AuthorDAO.getAllAuthors();//{"Author 1", "Author 2", "Author 3", "Author 4"};
        Author[] authorArray = authors.toArray(new Author[0]);

        JList<Author> authorList = new JList<>(authorArray);
//        authorList.setModel(authors);
        authorList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Allow multiple selections
        authorScrollPane = new JScrollPane(authorList);


        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(authorLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(authorScrollPane, gbc);


        JLabel maxCheckoutLengthLabel = new JLabel("Max Checkout:");
         maxCheckoutLengthField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(maxCheckoutLengthLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(maxCheckoutLengthField, gbc);


        JLabel numOfCopiesLabel = new JLabel("No of Copies:");
         numOfCopiesField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(numOfCopiesLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(numOfCopiesField, gbc);

        // Submit button
        JButton submitButton = new JButton("Submit");
        addLoginButtonListener(submitButton);
        gbc.gridx = 1;
        gbc.gridy = 5;
//        gbc.gridwidth = 2;
        panel.add(submitButton, gbc);

        messageLabel = new JLabel("");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(messageLabel, gbc);
    }

    public void addLoginButtonListener(JButton btn){
        btn.addActionListener(evt -> {


            String title = titleField.getText().trim();
            String isbn = isbnField.getText().trim();
            JList<Author> list = (JList<Author>) authorScrollPane.getViewport().getView();
            List<Author> listOfAuthors = new ArrayList<>();

            // Get the selected values from the JList
            List<Author> selectedValues = list.getSelectedValuesList();
            for(var selectedAuthor : selectedValues){
                listOfAuthors.add(selectedAuthor);
            }


            int maxCheckoutLength = Integer.parseInt(maxCheckoutLengthField.getText().trim());
            int numOfCopies = Integer.parseInt(numOfCopiesField.getText().trim());

            var res = BookDAO.addBook(title,isbn,listOfAuthors, maxCheckoutLength, numOfCopies);
            messageLabel.setText(res);

            isbnField.setText("");
            titleField.setText("");
            maxCheckoutLengthField.setText("");
            numOfCopiesField.setText("");

        });
    }

    public JPanel getPanel() {
        return panel;
    }
}