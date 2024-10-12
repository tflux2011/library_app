package edu.miu;

import edu.miu.Business.CheckoutRecordFactory;
import java.util.Date;
import edu.miu.Model.CheckoutEntry;
import edu.miu.Model.CheckoutRecord;
import edu.miu.Model.LibraryMember;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class ViewCheckedOutBooksPage {

    private JPanel panel;
    private DefaultTableModel tableModel;

    private LibraryMember[] dummyMembers;
    Map<Integer, CheckoutRecord> books;

    public ViewCheckedOutBooksPage() {
        panel = new JPanel(new BorderLayout());

        // Create search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel searchLabel = new JLabel("Search:");
        JTextField searchField = new JTextField(20);
        JButton submitButton = new JButton("Submit");
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(submitButton);
        panel.add(searchPanel, BorderLayout.NORTH);


        // Create the table for book listing
        String[] columnNames = {"Member ID","Book Title", "ISBN", "Is Due"};
        tableModel = new DefaultTableModel(columnNames, 0);

        // Add some dummy data
        addDummyMembers();

        JTable bookTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(bookTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Add search functionality
        searchField.addActionListener(e -> {
            String searchText = searchField.getText().toLowerCase();
            populateTable(filterBooks(searchText));
            System.out.println("Search...");
        });

        // Checkout button
        submitButton.addActionListener(e -> {
            String searchText = searchField.getText().toLowerCase();
            var record = filterBooks(searchText);
            populateTable(record);
            System.out.println(filterBooks(searchText).toString());
        });
    }

    private void addDummyMembers() {
        books = CheckoutRecordFactory.getAllCheckoutRecords();
        String[] row = {};

        for (Map.Entry<Integer, CheckoutRecord> entry : books.entrySet()) {
            var memberId = entry.getKey();
            var entryObj = entry.getValue().getCheckoutEntries();
            var currDate = new Date();

            for(CheckoutEntry e: entryObj) {
                row = new String[]{
                       String.valueOf(memberId),
                        e.getBookCopy().getBook().getTitle(),
                        e.getBookCopy().getBook().getIsbn(),
                        String.valueOf(e.isOverdue(currDate)),
                };
                tableModel.addRow(row);
            }

        }
    }

    private void populateTable(Map<Integer, CheckoutRecord> records) {
        // Clear existing data in the table model
        tableModel.setRowCount(0);

        String[] row = {};

        for (Map.Entry<Integer, CheckoutRecord> entry : records.entrySet()) {
            var memberId = entry.getKey();
            var entryObj = entry.getValue().getCheckoutEntries();
            var currDate = new Date();

            for(CheckoutEntry e: entryObj) {
                row = new String[]{
                        String.valueOf(memberId),
                        e.getBookCopy().getBook().getTitle(),
                        e.getBookCopy().getBook().getIsbn(),
                        String.valueOf(e.isOverdue(currDate)),
                };
                tableModel.addRow(row);
            }

        }

        // If no books match the filter, add a "No records found" message
        if (books.isEmpty()) {
            tableModel.addRow(new Object[]{"-", "No records found", "-", "-"});
        }
    }


    // Method to filter books by author, title, and year
    private Map<Integer, CheckoutRecord> filterBooks(String search) {
        return books.entrySet().stream()
                .filter(entry -> {
                    var book = entry.getValue().getCheckoutEntries();
                    for(CheckoutEntry e: book) {
                        boolean matchesTitle = search.isEmpty() || e.getBookCopy().getBook().getTitle().equalsIgnoreCase(search);
                        boolean matchesISBN = search.isEmpty() || String.valueOf(e.getBookCopy().getBook().getIsbn()).equals(search);
                        return matchesISBN || matchesTitle;
                    }
                    return false;
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    public List<LibraryMember> filterCheckoutBooks(String firstName) {
        List<LibraryMember> filteredList = new ArrayList<>();
        for (LibraryMember member : dummyMembers) {
            if (member.getFirstName().equalsIgnoreCase(firstName)) {
                filteredList.add(member);
            }
        }
        return filteredList;
    }

//    private void filterBooks(String searchText) {
//        tableModel.setRowCount(0);

//        String[][] dummyMembers = {
//                {"1", "Johaan", "Scheuta", "1011 N Street", "Fairfield", "IOWA", "55656", "6551122445"},
//                {"2", "Silvia", "Cort", "1011 Burlington Avenue", "Fairfield", "IOWA", "55656", "6551122445"},
//                {"3", "Sylvia", "Baken", "929 Westchester Drive", "Fairfield", "IOWA", "55656", "6551122445"},
//        };

//        List<LibraryMember> members = LibraryMemberDAO.getAllMembers();
//        LibraryMember[] dummyMembers = members.toArray(new LibraryMember[0]);

//        for (LibraryMember member : dummyMembers) {
//            boolean matchesSearch = false;
//            for (String field : member) {
//                if (field.toLowerCase().contains(searchText)) {
//                    matchesSearch = true;
//                    break;
//                }
//            }
//            if (matchesSearch) {
//                tableModel.addRow(member);
//            }
//        }
//    }

    public JPanel getPanel() {
        return panel;
    }
}
