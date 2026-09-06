package Shikha;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;

public class StudentAttendanceSystem extends JFrame {

    private JTextField txtRoll, txtName, txtSubject, txtDate;
    private JComboBox<String> cmbStatus;
    private JTable table;
    private DefaultTableModel model;

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/db";
    private static final String USER = "root";
    private static final String PASS = "root"; // Update if your password is different

    public StudentAttendanceSystem() {
        setTitle("Student Attendance System");
        setSize(850, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- Left Panel: Attendance Form ---
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        formPanel.add(new JLabel("Roll No:"));
        txtRoll = new JTextField();
        formPanel.add(txtRoll);

        formPanel.add(new JLabel("Name:"));
        txtName = new JTextField();
        formPanel.add(txtName);

        formPanel.add(new JLabel("Subject:"));
        txtSubject = new JTextField();
        formPanel.add(txtSubject);

        formPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        // Automatically fills with today's date
        txtDate = new JTextField(LocalDate.now().toString());
        formPanel.add(txtDate);

        formPanel.add(new JLabel("Attendance Status:"));
        String[] statuses = {"Present", "Absent", "On Leave"};
        cmbStatus = new JComboBox<>(statuses);
        formPanel.add(cmbStatus);

        JButton btnSubmit = new JButton("Submit Attendance");
        JButton btnView = new JButton("View Records");
        formPanel.add(btnSubmit);
        formPanel.add(btnView);

        add(formPanel, BorderLayout.WEST);

        // --- Center Panel: Records Table ---
        String[] columns = {"Roll No", "Name", "Subject", "Date", "Status"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 15));
        add(scrollPane, BorderLayout.CENTER);

        // --- Event Listeners ---
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAttendance();
            }
        });

        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAttendance();
            }
        });
    }

    // Insert record into MySQL
    private void saveAttendance() {
        String sql = "INSERT INTO attendance VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(txtRoll.getText().trim()));
            ps.setString(2, txtName.getText().trim());
            ps.setString(3, txtSubject.getText().trim());
            ps.setDate(4, Date.valueOf(txtDate.getText().trim())); // Parses 'YYYY-MM-DD'
            ps.setString(5, cmbStatus.getSelectedItem().toString());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Attendance Saved Successfully!");

            // Clear inputs (retaining default today's date)
            txtRoll.setText("");
            txtName.setText("");
            txtSubject.setText("");
            txtDate.setText(LocalDate.now().toString());
            cmbStatus.setSelectedIndex(0);

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Roll number must be an integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException iae) {
            JOptionPane.showMessageDialog(this, "Invalid Date format! Use YYYY-MM-DD.", "Date Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Read and display records in JTable
    private void loadAttendance() {
        model.setRowCount(0); // Clear existing rows
        String sql = "SELECT * FROM attendance";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("roll_no"),
                    rs.getString("name"),
                    rs.getString("subject"),
                    rs.getDate("att_date"),
                    rs.getString("status")
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentAttendanceSystem().setVisible(true));
    }
}