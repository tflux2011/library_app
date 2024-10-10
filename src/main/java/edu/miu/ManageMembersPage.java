package edu.miu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageMembersPage {

    private JPanel panel;
    private DefaultTableModel tableModel;

    public ManageMembersPage() {
        panel = new JPanel(new BorderLayout());

        // Create search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel searchLabel = new JLabel("Search:");
        JTextField searchField = new JTextField(20);
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        panel.add(searchPanel, BorderLayout.NORTH);

        // Create the table for book listing
        String[] columnNames = {"Member ID", "First Name", "Last Name", "Street","City","State","Zip", "Phone"};
        tableModel = new DefaultTableModel(columnNames, 0);

        // Add some dummy data
        addDummyMembers();

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

    private void addDummyMembers() {
        String[][] dummyMembers = {
                {"1", "Johaan", "Scheuta", "1011 N Street", "Fairfield", "IOWA", "55656", "6551122445"},
                {"2", "Silvia", "Cort", "1011 Burlington Avenue", "Fairfield", "IOWA", "55656", "6551122445"},
                {"3", "Sylvia", "Baken", "929 Westchester Drive", "Fairfield", "IOWA", "55656", "6551122445"},
        };

        for (String[] member : dummyMembers) {
            tableModel.addRow(member);
        }
    }

    private void filterBooks(String searchText) {
        tableModel.setRowCount(0);

        String[][] dummyMembers = {
                {"1", "Johaan", "Scheuta", "1011 N Street", "Fairfield", "IOWA", "55656", "6551122445"},
                {"2", "Silvia", "Cort", "1011 Burlington Avenue", "Fairfield", "IOWA", "55656", "6551122445"},
                {"3", "Sylvia", "Baken", "929 Westchester Drive", "Fairfield", "IOWA", "55656", "6551122445"},
        };

        for (String[] member : dummyMembers) {
            boolean matchesSearch = false;
            for (String field : member) {
                if (field.toLowerCase().contains(searchText)) {
                    matchesSearch = true;
                    break;
                }
            }
            if (matchesSearch) {
                tableModel.addRow(member);
            }
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
