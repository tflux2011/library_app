package edu.miu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private static JPanel membersPanel;
    private static DefaultTableModel tableModel;

    public static JPanel getMembersPanel() {
        if (membersPanel == null) {
            membersPanel = new JPanel(new BorderLayout());

            String[] columnNames = {"ID", "Name", "Email", "Edit", "Delete"};
            Object[][] data = {
                    {1, "John Doe", "john@example.com", "Edit", "Delete"},
                    {2, "Jane Smith", "jane@example.com", "Edit", "Delete"}
            };

            tableModel = new DefaultTableModel(data, columnNames);

            JTable membersTable = new JTable(tableModel) {
                // Make the Edit/Delete columns buttons
                public boolean isCellEditable(int row, int column) {
                    return column == 3 || column == 4;  // Only Edit/Delete columns are editable
                }
            };

            membersTable.getColumn("Edit").setCellRenderer(new ButtonRenderer());
            membersTable.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(), "Edit"));
            membersTable.getColumn("Delete").setCellRenderer(new ButtonRenderer());
            membersTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(), "Delete"));

            JScrollPane scrollPane = new JScrollPane(membersTable);
            membersPanel.add(scrollPane, BorderLayout.CENTER);
        }
        return membersPanel;
    }

    // Renderer for buttons in table cells
    static class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // Editor for buttons in table cells
    static class ButtonEditor extends DefaultCellEditor {
        private String label;
        private boolean isEditMode;

        public ButtonEditor(JCheckBox checkBox, String buttonLabel) {
            super(checkBox);
            label = buttonLabel;
            isEditMode = buttonLabel.equals("Edit");
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            JButton button = new JButton(label);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (isEditMode) {
                        // Handle Edit: populate fields for editing
                        String memberName = (String) table.getValueAt(row, 1);
                        String memberEmail = (String) table.getValueAt(row, 2);
                        // Show dialog to edit this member's info
                        editMemberDialog(memberName, memberEmail, row);
                    } else {
                        // Handle Delete: remove the row
                        tableModel.removeRow(row);
                    }
                }
            });
            return button;
        }

        public Object getCellEditorValue() {
            return label;
        }

        private void editMemberDialog(String name, String email, int row) {
            // Create a dialog to edit member info
            JTextField nameField = new JTextField(name);
            JTextField emailField = new JTextField(email);

            JPanel panel = new JPanel(new GridLayout(2, 2));
            panel.add(new JLabel("Name:"));
            panel.add(nameField);
            panel.add(new JLabel("Email:"));
            panel.add(emailField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Edit Member", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                // Update the table with new information
                tableModel.setValueAt(nameField.getText(), row, 1);
                tableModel.setValueAt(emailField.getText(), row, 2);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Library Members");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(getMembersPanel());
        frame.setVisible(true);
    }
}
