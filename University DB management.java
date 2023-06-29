import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentRegistrationForm extends JFrame 
{
    private JTextField regNumberField;
    private JTextField nameField;
    private JTextField phoneNumberField;
    private JTextField cityField;
        public StudentRegistrationForm() 
        {
        setTitle("Student Registration Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel regNumberLabel = new JLabel("Registration Number:");
        regNumberLabel.setBounds(20, 20, 150, 20);
        add(regNumberLabel);

        regNumberField = new JTextField();
        regNumberField.setBounds(180, 20, 150, 20);
        add(regNumberField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 60, 150, 20);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(180, 60, 150, 20);
        add(nameField);

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setBounds(20, 100, 150, 20);
        add(phoneNumberLabel);

        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(180, 100, 150, 20);
        add(phoneNumberField);

        JLabel cityLabel = new JLabel("City:");
        cityLabel.setBounds(20, 140, 150, 20);
        add(cityLabel);

        cityField = new JTextField();
        cityField.setBounds(180, 140, 150, 20);
        add(cityField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(100, 200, 80, 30);
        add(submitButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBounds(200, 200, 80, 30);
        add(refreshButton);

        submitButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                String regNumber = regNumberField.getText();
                String name = nameField.getText();
                String phoneNumber = phoneNumberField.getText();
                String city = cityField.getText();

                insertStudentData(regNumber, name, phoneNumber, city);

                JOptionPane.showMessageDialog(null, "Data stored successfully!");
            }
        });

        refreshButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                regNumberField.setText("");
                nameField.setText("");
                phoneNumberField.setText("");
                cityField.setText("");
            }
        });
        
        setVisible(true);
    }
    private void insertStudentData(String regNumber, String name, String phoneNumber, String city) {
        String url = "jdbc:mysql://localhost:3306/jdbc_db";
        String username = "root";
        String password = "Password";

        try 
        {
            Connection conn = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO students (reg_number, name, phone_number, city) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,regNumber);
            pstmt.setString(2,name);
            pstmt.setString(3,phoneNumber);
            pstmt.setString(4,city);
            pstmt.executeUpdate();
            conn.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() 
            {
                new StudentRegistrationForm();
            }
        });
    }
}

