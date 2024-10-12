package edu.miu;

import edu.miu.Business.CheckoutRecordFactory;
import edu.miu.Model.CheckoutEntry;
import edu.miu.Model.CheckoutRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;

public class ViewCheckedOutBooksPage {

    private JPanel panel;
    private DefaultTableModel tableModel;
    private Map<Integer, CheckoutRecord> books;
    private Map<Integer, CheckoutRecord> updatedListOfBooks;

    public ViewCheckedOutBooksPage() {
        panel = new JPanel(new BorderLayout());

        // Create the search panel
        JPanel searchPanel = new JPanel(new GridLayout(2, 1)); // 2 rows for separate searches

        // Row 1: Search by ISBN
        JPanel isbnSearchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel isbnLabel = new JLabel("Search by ISBN:");
        JTextField isbnField = new JTextField(20);
        JButton isbnSubmitButton = new JButton("Search");
        JButton isbnClearButton = new JButton("Clear");

        isbnSearchPanel.add(isbnLabel);
        isbnSearchPanel.add(isbnField);
        isbnSearchPanel.add(isbnSubmitButton);
        isbnSearchPanel.add(isbnClearButton);

        // Row 2: Search by Member ID
        JPanel memberIdSearchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel memberIdLabel = new JLabel("Search by Member ID:");
        JTextField memberIdField = new JTextField(20);
        JButton memberIdSubmitButton = new JButton("Search");
        JButton memberIdClearButton = new JButton("Clear");
        JButton memberIdPrintButton = new JButton("Print");

        memberIdSearchPanel.add(memberIdLabel);
        memberIdSearchPanel.add(memberIdField);
        memberIdSearchPanel.add(memberIdSubmitButton);
        memberIdSearchPanel.add(memberIdClearButton);
        memberIdSearchPanel.add(memberIdPrintButton);

        // Add both rows to the search panel
        searchPanel.add(isbnSearchPanel);
        searchPanel.add(memberIdSearchPanel);
        panel.add(searchPanel, BorderLayout.NORTH);

        // Create the table for book listings
        String[] columnNames = {"Member ID", "Book Title", "ISBN", "Due Date","Is Due"};
        tableModel = new DefaultTableModel(columnNames, 0);

        addDummyMembers(); // Load initial data

        JTable bookTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(bookTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // ISBN search functionality
        isbnSubmitButton.addActionListener(e -> {
            String isbnSearchText = isbnField.getText().trim().toLowerCase();
            populateTable(filterBooksByISBN(isbnSearchText));
        });

        isbnField.addActionListener(e -> {
            String isbnSearchText = isbnField.getText().trim().toLowerCase();
            populateTable(filterBooksByISBN(isbnSearchText));
        });

        isbnClearButton.addActionListener(e -> {
            isbnField.setText(""); // Clear the ISBN field
            populateTable(books); // Reset the table with all records
        });

        // Member ID search functionality
        memberIdSubmitButton.addActionListener(e -> {
            String memberIdSearchText = memberIdField.getText().trim();
            populateTable(filterBooksByMemberID(memberIdSearchText));
        });

        memberIdField.addActionListener(e -> {
            String memberIdSearchText = memberIdField.getText().trim();
            populateTable(filterBooksByMemberID(memberIdSearchText));
        });

        memberIdClearButton.addActionListener(e -> {
            memberIdField.setText(""); // Clear the Member ID field
            populateTable(books); // Reset the table with all records
        });

        memberIdPrintButton.addActionListener(e -> {
            Date currDate = new Date();
            for (Map.Entry<Integer, CheckoutRecord> entry : updatedListOfBooks.entrySet()) {
                int memberId = entry.getKey();
                List<CheckoutEntry> checkoutEntries = entry.getValue().getCheckoutEntries();

                for (CheckoutEntry el : checkoutEntries) {
                    // Create a SimpleDateFormat with the desired format
                    SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/y");
                    // Format the date
                    String formattedDate = dateFormat.format(el.getDueDate());
//                    row = new String[]{
                    System.out.println("----------");
                    System.out.println("Member ID: "+ memberId);
                    System.out.println("Title: "+ el.getBookCopy().getBook().getTitle());
                    System.out.println("ISBN:" +el.getBookCopy().getBook().getIsbn());
                    System.out.println("Due Date: "+formattedDate);
                    System.out.println("Is Overdue: "+el.isOverdue(currDate));
                    System.out.println("---------- \n");
                }
            }
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
        Date currDate = new Date();

        for (Map.Entry<Integer, CheckoutRecord> entry : records.entrySet()) {
            int memberId = entry.getKey();
            List<CheckoutEntry> checkoutEntries = entry.getValue().getCheckoutEntries();

            for (CheckoutEntry e : checkoutEntries) {
                // Create a SimpleDateFormat with the desired format
                SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/y");
                // Format the date
                String formattedDate = dateFormat.format(e.getDueDate());
                row = new String[]{
                        String.valueOf(memberId),
                        e.getBookCopy().getBook().getTitle(),
                        e.getBookCopy().getBook().getIsbn(),
                        formattedDate,
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

    // Filter books by ISBN
    private Map<Integer, CheckoutRecord> filterBooksByISBN(String isbnSearch) {
        if (isbnSearch.isEmpty()) return books; // Return all records if no input

        var listOfBooks = books.entrySet().stream()
                .filter(entry -> entry.getValue().getCheckoutEntries().stream()
                        .anyMatch(e -> e.getBookCopy().getBook().getIsbn().toLowerCase().contains(isbnSearch)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        updatedListOfBooks = listOfBooks;
        return listOfBooks;
    }

    // Filter books by Member ID
    private Map<Integer, CheckoutRecord> filterBooksByMemberID(String memberIdSearch) {
        if (memberIdSearch.isEmpty()) return books; // Return all records if no input

        try {
            int memberId = Integer.parseInt(memberIdSearch); // Parse to int
            var listOfBooks =  books.entrySet().stream()
                    .filter(entry -> entry.getKey() == memberId)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            updatedListOfBooks = listOfBooks;
            return listOfBooks;
        } catch (NumberFormatException e) {
            return new HashMap<>(); // Return empty if input is not a valid integer
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}