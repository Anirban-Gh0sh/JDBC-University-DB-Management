import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class CollegeApp {
    private JFrame frame;
    private JLabel headingLabel;
    private JButton insertButton;
    private JButton deleteButton;

    // MySQL database connection details
    private final String DB_URL = "jdbc:mysql://localhost:3306/college";
    private final String USERNAME = "root";
    private final String PASSWORD = "password";

    // Constructor
    public CollegeApp() {
        frame = new JFrame("College Application");
        frame.setLayout(new BorderLayout());
        frame.setSize(400, 200);
         frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        headingLabel = new JLabel("COLLEGE OF ENGINEERING BHUBANESWAR");
        JPanel headingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headingPanel.add(headingLabel);
        frame.add(headingPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        insertButton = new JButton("Insert Record");
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openInsertWindow();
            }
        });
        buttonPanel.add(insertButton);

        deleteButton = new JButton("Delete Record");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openDeleteWindow();
            }
        });
        buttonPanel.add(deleteButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Method to open the insert window
    private void openInsertWindow() {
        JFrame insertFrame = new JFrame("Insert Record");
        insertFrame.setLayout(new GridLayout(6, 2));
        insertFrame.setSize(300, 200);
        insertFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JLabel regNoLabel = new JLabel("Registration No:");
        JTextField regNoField = new JTextField(10);
        insertFrame.add(regNoLabel);
        insertFrame.add(regNoField);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(10);
        insertFrame.add(nameLabel);
        insertFrame.add(nameField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField(10);
        insertFrame.add(phoneLabel);
        insertFrame.add(phoneField);

        JLabel genderLabel = new JLabel("Gender:");
        JTextField genderField = new JTextField(10);
        insertFrame.add(genderLabel);
        insertFrame.add(genderField);

        JLabel cityLabel = new JLabel("City:");
        JTextField cityField = new JTextField(10);
        insertFrame.add(cityLabel);
        insertFrame.add(cityField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String regNo = regNoField.getText();
                String name = nameField.getText();
                String phone = phoneField.getText();
                String gender = genderField.getText();
                String city = cityField.getText();
                insertRecord(regNo, name, phone, gender, city);
                JOptionPane.showMessageDialog(insertFrame, "Record inserted successfully!");
                insertFrame.dispose();
            }
        });
        insertFrame.add(submitButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                regNoField.setText("");
                nameField.setText("");
                phoneField.setText("");
                genderField.setText("");
                cityField.setText("");
            }
        });
        insertFrame.add(refreshButton);

        insertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        insertFrame.setVisible(true);
    }

    // Method to insert a record into the database
    private void insertRecord(String regNo, String name, String phone, String gender, String city) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO college_students (reg_no, name, phone, gender, city) VALUES ('" +
                    regNo + "', '" + name + "', '" + phone + "', '" + gender + "', '" + city + "')";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Method to open the delete window
    private void openDeleteWindow() {
        JFrame deleteFrame = new JFrame("Delete Record");
        deleteFrame.setLayout(new GridLayout(3, 1));
        deleteFrame.setSize(300, 200);
        deleteFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JLabel regNoLabel = new JLabel("Registration No:");
        JTextField regNoField = new JTextField(10);
        deleteFrame.add(regNoLabel);
        deleteFrame.add(regNoField);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(10);
        deleteFrame.add(nameLabel);
        deleteFrame.add(nameField);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String regNo = regNoField.getText();
                String name = nameField.getText();
                deleteRecord(regNo, name);
                JOptionPane.showMessageDialog(deleteFrame, "Record deleted successfully!");
                deleteFrame.dispose();
            }
        });
        deleteFrame.add(deleteButton);

        deleteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteFrame.setVisible(true);
    }

    // Method to delete a record from the database
    private void deleteRecord(String regNo, String name) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM college_students WHERE reg_no = '" + regNo + "' AND name = '" + name + "'";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CollegeApp();
            }
        });
    }
}
