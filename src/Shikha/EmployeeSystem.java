package Shikha;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeSystem extends JFrame {

    private JTextField txtId, txtName, txtDept, txtDesig, txtSalary;
    private JTable table;
    private DefaultTableModel model;

    private static final String URL = "jdbc:mysql://localhost:3306/db";
    private static final String USER = "root";
    private static final String PASS = "root";

    public EmployeeSystem() {
        setTitle("Employee Registration System");
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        formPanel.add(new JLabel("Employee ID:"));
        txtId = new JTextField();
        formPanel.add(txtId);

        formPanel.add(new JLabel("Name:"));
        txtName = new JTextField();
        formPanel.add(txtName);

        formPanel.add(new JLabel("Department:"));
        txtDept = new JTextField();
        formPanel.add(txtDept);

        formPanel.add(new JLabel("Designation:"));
        txtDesig = new JTextField();
        formPanel.add(txtDesig);

        formPanel.add(new JLabel("Salary:"));
        txtSalary = new JTextField();
        formPanel.add(txtSalary);

        JButton btnRegister = new JButton("Register");
        JButton btnDisplay = new JButton("Display All");
        formPanel.add(btnRegister);
        formPanel.add(btnDisplay);

        add(formPanel, BorderLayout.WEST);

        // Table Panel
        String[] columns = {"ID", "Name", "Department", "Designation", "Salary"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 15));
        add(scrollPane, BorderLayout.CENTER);

        // Action Listeners
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveEmployee();
            }
        });

        btnDisplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayEmployees();
            }
        });
    } // <-- End of constructor

    private void saveEmployee() {
        String sql = "INSERT INTO employee VALUES (?,?,?,?,?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(txtId.getText().trim()));
            ps.setString(2, txtName.getText().trim());
            ps.setString(3, txtDept.getText().trim());
            ps.setString(4, txtDesig.getText().trim());
            ps.setDouble(5, Double.parseDouble(txtSalary.getText().trim()));
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Employee Registered Successfully!");

            txtId.setText("");
            txtName.setText("");
            txtDept.setText("");
            txtDesig.setText("");
            txtSalary.setText("");
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "ID must be an integer and Salary must be numeric.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayEmployees() {
        model.setRowCount(0);
        String sql = "SELECT * FROM employee";
        
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("department"),
                    rs.getString("designation"),
                    rs.getDouble("salary")
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeSystem().setVisible(true));
    }
}


