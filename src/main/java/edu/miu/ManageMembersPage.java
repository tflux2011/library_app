package edu.miu;

import edu.miu.Business.LibraryMemberFactory;
import edu.miu.Model.Author;
import edu.miu.Model.LibraryMember;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class ManageMembersPage {

    private JPanel panel;
    private DefaultTableModel tableModel;

    private LibraryMember[] dummyMembers;

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

        try{
            // Add some dummy data
            addDummyMembers();
        }catch(Exception e){
            tableModel.addRow(new Object[] { "No records found", "", "" });
        }


        JTable bookTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(bookTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Add search functionality
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().toLowerCase();
                filterLibraryMembers(searchText);
            }
        });
    }

    private void addDummyMembers() {
        List<LibraryMember> members = LibraryMemberFactory.getAllMembers();
        dummyMembers = members.toArray(new LibraryMember[0]);
        System.out.println(dummyMembers);

        for (LibraryMember member : dummyMembers) {
            // Convert each Author object to a String array (each row is a String array)
            String[] row = new String[] {
                    String.valueOf(member.getMemberId()),
                    member.getFirstName(),
                    member.getLastName(),
                    member.getAddress().getStreet(),
                    member.getAddress().getCity(),
                    member.getAddress().getState(),
                    member.getAddress().getZip(),
                    member.getPhone()
            };
            // Add the row to the table model
            tableModel.addRow(row);
        }
    }

    public List<LibraryMember> filterLibraryMembers(String firstName) {
        List<LibraryMember> filteredList = new ArrayList<>();
        for (LibraryMember member : dummyMembers) {
            if (member.getFirstName().equalsIgnoreCase(firstName)) {
                filteredList.add(member);
            }
        }
        return filteredList;
    }

    private void filterBooks(String searchText) {
        tableModel.setRowCount(0);

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
    }

    public JPanel getPanel() {
        return panel;
    }
}
