package edu.miu;

import edu.miu.Business.BookFactory;
import edu.miu.Model.Book;
import edu.miu.Model.Author;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class ViewBooksPage {

    private JPanel panel;
    private DefaultTableModel tableModel;
    private Book[] allBooks;


    public ViewBooksPage() {
        panel = new JPanel(new BorderLayout());

        // Create search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel searchLabel = new JLabel("Search:");
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearButton);

        panel.add(searchPanel, BorderLayout.NORTH);


        // Create the table for book listing
        String[] columnNames = {"Title", "ISBN", "AUTHOR", "No of Copies", "Max Checkout"};
        tableModel = new DefaultTableModel(columnNames, 0);

        // Add some dummy data
        try{
            addDummyBooks();
        }catch (Exception e) {
            tableModel.addRow(new Object[] { "No records found", "", "" });
        }

        JTable bookTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(bookTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().toLowerCase();
                filterBooks(searchText);
            }
        });
        // Add search functionality
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().toLowerCase();
                filterBooks(searchText);
            }
        });
        // Clear button functionality
        clearButton.addActionListener(e -> {
            searchField.setText("");  // Clear the search field
            reloadBooks();  // Reload all books
        });
    }

    public static String[][] convertBookListTo2DArray(List<Book> books) {
        // Create a 2D array with the number of rows equal to the size of the book list
        // and the number of columns (e.g., title and author)
        String[][] bookArray = new String[books.size()][5];

        // Populate the 2D array with book information
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            String authors = book.getAuthors().stream()
                    .map(Author::toString)  // Use the toString() method for each Author
                    .collect(Collectors.joining(", "));

            bookArray[i][0] = book.getTitle();  // First column: title
            bookArray[i][1] = book.getIsbn();
            bookArray[i][2] = authors; // Second column: author
            bookArray[i][3] = String.valueOf(book.getCopies().size());
            bookArray[i][4] = String.valueOf(book.getMaxCheckoutLength());
        }

        return bookArray;
    }
    private void addDummyBooks() {
        List<Book> books = BookFactory.getAllBooks();
        allBooks = books.toArray(new Book[0]);

        for (Book book : allBooks) {
            // Convert each Author object to a String array (each row is a String array)
            String authors = book.getAuthors().stream()
                    .map(Author::toString) // Calls the overridden toString method
                    .collect(Collectors.joining(", "));
            String[] row = new String[] {
                    book.getTitle(),
                    book.getIsbn(),
                    authors,
                    String.valueOf(book.getCopies().size()),
                    String.valueOf(book.getMaxCheckoutLength()),
            };
            // Add the row to the table model
            tableModel.addRow(row);
        }
    }

    private void filterBooks(String searchText) {
        tableModel.setRowCount(0);

        List<Book> books = BookFactory.getAllBooks();
        allBooks = books.toArray(new Book[0]);

        var arr = ViewBooksPage.convertBookListTo2DArray(books);

        for (String[] book : arr) {
            boolean matchesSearch = false;
            for (String field : book) {
                if (field.toLowerCase().contains(searchText)) {
                    matchesSearch = true;
                    break;
                }
            }
            if (matchesSearch) {
                tableModel.addRow(book);
            }
        }
    }

    private void reloadBooks() {
        tableModel.setRowCount(0);  // Clear existing rows
        addDummyBooks();  // Reload all books into the table
    }

    public JPanel getPanel() {
        return panel;
    }
}
