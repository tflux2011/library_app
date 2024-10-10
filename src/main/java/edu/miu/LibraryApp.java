package edu.miu;
import javax.swing.*;

import edu.miu.Model.Data;
import edu.miu.Model.User;
import edu.miu.Model.Util;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class LibraryApp {

    private static JFrame frame;
    private static JPanel loginPanel;
    private static JTextField usernameField;
    private static JPasswordField passwordField;
    private static JComboBox<String> roleComboBox;
    private static HashMap<String, String> userRoles = new HashMap<>(); // St
    public static void main(String[] args) {
        userRoles.put("librarian", "Librarian");
        userRoles.put("admin", "Admin");
        userRoles.put("adminlibrarian", "Admin,Librarian");

        SwingUtilities.invokeLater(LibraryApp::createAndShowLogin);
    }

    // Create and display the login page
    private static void createAndShowLogin() {
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);  // Increased the size to accommodate the image
        frame.setLocationRelativeTo(null); // Center the window

        // Login Panel
        loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add an image at the top
        JLabel imageLabel = new JLabel();
//        ImageIcon icon = new ImageIcon("loto.png"); // Replace with your image path
        ImageIcon icon = new ImageIcon(LibraryApp.class.getResource("/loto.png"));
        imageLabel.setIcon(icon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(imageLabel, gbc);
        gbc.gridwidth = 1;  // Reset the gridwidth for further components

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(usernameField, gbc);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(passwordField, gbc);

        // Role selection (Librarian or Admin)
//        JLabel roleLabel = new JLabel("Role:");
//        String[] roles = {"Librarian", "Admin"};
//        roleComboBox = new JComboBox<>(roles);
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        loginPanel.add(roleLabel, gbc);
//        gbc.gridx = 1;
//        gbc.gridy = 3;
//        loginPanel.add(roleComboBox, gbc);

        // Login button
        JButton loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, gbc);

        // ActionListener for login button
//        loginButton.addActionListener(addLoginButtonListener());
        addLoginButtonListener(loginButton);

        frame.getContentPane().add(loginPanel);
        frame.setVisible(true);
    }

    private static void addLoginButtonListener(JButton butn) {
        butn.addActionListener(evt -> {
            String user = usernameField.getText().trim();
            String pwd = new String(passwordField.getPassword()).trim();

            if (user.length() == 0 || pwd.length() == 0) {
                JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
            } else {
                List<User> list = Data.logins;
                User login = new User(user, pwd, null);
                User u = Util.findUser(list, login);
                if (u == null) {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Data.currentAuth = u.authorization;

                    if(Data.currentAuth.toString().equals("LIBRARIAN")){
                        LibrarianDashboard.main(null);
                    }else{
                        AdminDashboard.createAdminDashboard(Data.currentAuth.toString());
                    }
                    frame.dispose();
                }
            }
        });
    }

    // Handle login action
    private static void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Dummy password check (In a real application, you'd validate against a database)
        if (password.equals("password")) {
            String role = userRoles.get(username);

            if (role != null) {
                if (role.contains("Admin")) {
                    // Admin has librarian privileges, check if they have checkout privileges too
                    frame.dispose();  // Close the login window
                    AdminDashboard.main(null);
                } else if (role.contains("Librarian")) {
                    // Load Librarian Dashboard
                    frame.dispose();  // Close the login window
                    LibrarianDashboard.main(null);
                }
            } else {
                // Show error message if the user is not found
                JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Show error message for incorrect password
            JOptionPane.showMessageDialog(frame, "Invalid password. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
