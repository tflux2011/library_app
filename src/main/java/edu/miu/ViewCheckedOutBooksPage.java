package edu.miu;

import edu.miu.Business.CheckoutRecordFactory;
import edu.miu.Model.CheckoutEntry;
import edu.miu.Model.CheckoutRecord;
import edu.miu.Model.LibraryMember;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ViewCheckedOutBooksPage {

    private JPanel panel;
    private DefaultTableModel tableModel;
    private Map<Integer, CheckoutRecord> books;

    public ViewCheckedOutBooksPage() {
        panel = new JPanel(new BorderLayout());

        // Create search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel searchLabel = new JLabel("Search ISBN:");
        JTextField searchField = new JTextField(20);
        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(submitButton);
        searchPanel.add(clearButton);
        panel.add(searchPanel, BorderLayout.NORTH);

        // Create the table for book listing
        String[] columnNames = {"Member ID", "Book Title", "ISBN", "Is Due"};
        tableModel = new DefaultTableModel(columnNames, 0);

        // Add some dummy data
        addDummyMembers();

        JTable bookTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(bookTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Add search functionality
        submitButton.addActionListener(e -> {
            String searchText = searchField.getText().trim().toLowerCase();
            populateTable(filterBooks(searchText));
        });

        searchField.addActionListener(e -> {
            String searchText = searchField.getText().trim().toLowerCase();
            populateTable(filterBooks(searchText));
        });

        // Clear button functionality
        clearButton.addActionListener(e -> {
            searchField.setText(""); // Clear the search field
            populateTable(books); // Reset the table with all records
        });
    }

    private void addDummyMembers() {
        books = CheckoutRecordFactory.getAllCheckoutRecords();
        populateTable(books);
    }

    private void populateTable(Map<Integer, CheckoutRecord> records) {
        // Clear existing data in the table model
        tableModel.setRowCount(0);

        String[] row;

        for (Map.Entry<Integer, CheckoutRecord> entry : records.entrySet()) {
            int memberId = entry.getKey();
            List<CheckoutEntry> checkoutEntries = entry.getValue().getCheckoutEntries();
            Date currDate = new Date();

            for (CheckoutEntry e : checkoutEntries) {
                row = new String[]{
                        String.valueOf(memberId),
                        e.getBookCopy().getBook().getTitle(),
                        e.getBookCopy().getBook().getIsbn(),
                        String.valueOf(e.isOverdue(currDate))
                };
                tableModel.addRow(row);
            }
        }

        // If no books match the filter, add a "No records found" message
        if (tableModel.getRowCount() == 0) {
            tableModel.addRow(new Object[]{"-", "No records found", "-", "-"});
        }
    }

    // Method to filter books by ISBN
    private Map<Integer, CheckoutRecord> filterBooks(String search) {
        if (search.isEmpty()) return books; // Return all records if the search is empty

        return books.entrySet().stream()
                .filter(entry -> entry.getValue().getCheckoutEntries().stream()
                        .anyMatch(e -> e.getBookCopy().getBook().getIsbn().toLowerCase().contains(search)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public JPanel getPanel() {
        return panel;
    }
}