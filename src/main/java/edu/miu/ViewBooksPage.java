package edu.miu;

import edu.miu.Business.BookFactory;
import edu.miu.Model.Book;
import edu.miu.Model.LibraryMember;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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


        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        panel.add(searchPanel, BorderLayout.NORTH);


        // Create the table for book listing
        String[] columnNames = {"Title", "ISBN", "AUTHOR", "No of Copies", "Max Checkout"};
        tableModel = new DefaultTableModel(columnNames, 0);

        // Add some dummy data
        addDummyBooks();

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
    }

    private void addDummyBooks() {
        List<Book> books = BookFactory.getAllBooks();
            allBooks = books.toArray(new Book[0]);

            for (Book book : allBooks) {
                // Convert each Author object to a String array (each row is a String array)
                String[] row = new String[] {
                        book.getTitle(),
                        book.getIsbn(),
                        book.getAuthors().toString(),
                        String.valueOf(book.getCopies().size()),
                        String.valueOf(book.getMaxCheckoutLength()),
                };
                // Add the row to the table model
                tableModel.addRow(row);
            }
    }

    private void filterBooks(String searchText) {
        tableModel.setRowCount(0);

        String[][] dummyBooks = {
                {"Effective Java", "978-0134685991", "Joshua Bloch", "Available"},
                {"Clean Code", "978-0132350884", "Robert C. Martin", "Checked Out"},
                {"Design Patterns", "978-0201633610", "Erich Gamma", "Available"},
                {"The Pragmatic Programmer", "978-0201616224", "Andrew Hunt", "Available"},
                {"Head First Java", "978-0596009205", "Kathy Sierra", "Checked Out"}
        };

        for (String[] book : dummyBooks) {
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

    public JPanel getPanel() {
        return panel;
    }
}
