package edu.miu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewBooksPage {

    private JPanel panel;
    private DefaultTableModel tableModel;

    public ViewBooksPage() {
        panel = new JPanel(new BorderLayout());

        // Create search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel searchLabel = new JLabel("Search:");
        JTextField searchField = new JTextField(20);
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        panel.add(searchPanel, BorderLayout.NORTH);

        // Create the table for book listing
        String[] columnNames = {"Title", "ISBN", "Book Author", "Availability"};
        tableModel = new DefaultTableModel(columnNames, 0);

        // Add some dummy data
        addDummyBooks();

        JTable bookTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(bookTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

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
        String[][] dummyBooks = {
                {"Effective Java", "978-0134685991", "Joshua Bloch", "Available"},
                {"Clean Code", "978-0132350884", "Robert C. Martin", "Checked Out"},
                {"Design Patterns", "978-0201633610", "Erich Gamma", "Available"},
                {"The Pragmatic Programmer", "978-0201616224", "Andrew Hunt", "Available"},
                {"Head First Java", "978-0596009205", "Kathy Sierra", "Checked Out"}
        };

        for (String[] book : dummyBooks) {
            tableModel.addRow(book);
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
