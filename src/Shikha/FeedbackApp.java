package Shikha;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FeedbackApp extends JFrame implements ActionListener{
	private JTextField txtName, txtEmail;
	private JTextArea txtComments;
	private JButton btnSubmit,btnClear,btnViewAll;
	
	private static final String DB_URL="jdbc:mysql://localhost:3306/feedback_db";
	private static final String DB_USER="root";
	private static final String DB_PASS="root";
	
	public FeedbackApp() {
		setTitle("User Feedback System");
		setSize(480,420);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JLabel lblName=new JLabel("Name:");
		lblName.setBounds(40,30,80,25);
		add(lblName);
		
		txtName=new JTextField();
		txtName.setBounds(130,30,280,25);
		add(txtName);
		
		JLabel lblEmail=new JLabel("Email:");
		lblEmail.setBounds(40,70,80,25);
	    add(lblEmail);
	    
	    txtEmail=new JTextField();
	    txtEmail.setBounds(130,70,280,25);
	    add(txtEmail);
	    
	    JLabel lblComments=new JLabel("Comments:");
	    lblComments.setBounds(40,110,80,25);
	    add(lblComments);
	    
	    txtComments = new JTextArea();
	    txtComments.setLineWrap(true);
	    txtComments.setWrapStyleWord(true);
	    
	    JScrollPane scrollComments=new JScrollPane(txtComments);
	    scrollComments.setBounds(130,110,280,120);
	    add(scrollComments);
	    
	    btnSubmit= new JButton("Submit Feedback");
	    btnSubmit.setBounds(40,260,170,32);
	    btnSubmit.addActionListener(this);
	    add(btnSubmit);
	    
	    btnClear=new JButton("Clear");
	    btnClear.setBounds(240, 260, 170, 32);
        btnClear.addActionListener(this);
        add(btnClear);
        
        btnViewAll=new JButton("View All feedback Entries:");
        btnViewAll.setBounds(40,310,370,35);
        btnViewAll.addActionListener(this);
        add(btnViewAll);
        setVisible(true);
        
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSubmit) {
			submitFeedback();
		}
		else if(e.getSource()==btnClear) {
			clearForm();
		}else if(e.getSource()==btnViewAll) {
			viewAllFeedback();
		}
	}
	private void submitFeedback() {
		String name =txtName.getText().trim();
		String email=txtEmail.getText().trim();
		String comments=txtComments.getText().trim();
		
		if(name.isEmpty()||email.isEmpty() || comments.isEmpty() ) {
			JOptionPane.showMessageDialog(this , "please fill in all the fields !","Validation error",JOptionPane.WARNING_MESSAGE);;
			return;
		}
		String insertQuery="INSERT INTO feedback(name ,email,comments) VALUES(?,?,?)";
		try(Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
				PreparedStatement ps=con.prepareStatement(insertQuery)){
					ps.setString(1, name);
					ps.setString(2, email);
		            ps.setString(3, comments);
		            int rows =ps.executeUpdate();
		            if(rows>0) {
		            	JOptionPane.showConfirmDialog(this, "Feedback submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
		            	clearForm();
		            }
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(this,"Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
					
					// TODO: handle exception
				}
		}
	private void viewAllFeedback() {
		JFrame viewFrame=new JFrame("All feedback entries");
		viewFrame.setSize(600,350);
		viewFrame.setLocationRelativeTo(this);
		
        String[] columns = {"ID", "Name", "Email", "Comments"};
        
        String countQuery = "SELECT COUNT(*) FROM feedback";
        String selectQuery = "SELECT * FROM feedback";
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery(selectQuery)) {

               // Move cursor to the end to get row count
               rs.last();
               int totalRows = rs.getRow();
               rs.beforeFirst();

               String[][] data = new String[totalRows][4];
               int i = 0;
               while (rs.next()) {
                   data[i][0] = String.valueOf(rs.getInt("id"));
                   data[i][1] = rs.getString("name");
                   data[i][2] = rs.getString("email");
                   data[i][3] = rs.getString("comments");
                   i++;
               }

               // Create JTable and embed it inside a JScrollPane
               JTable table = new JTable(data, columns);
               JScrollPane scrollPane = new JScrollPane(table);
               viewFrame.add(scrollPane, BorderLayout.CENTER);

               viewFrame.setVisible(true);

           } catch (SQLException ex) {
               JOptionPane.showMessageDialog(this, "Error fetching feedback: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
           }
       }

       // Helper to reset inputs
       private void clearForm() {
           txtName.setText("");
           txtEmail.setText("");
           txtComments.setText("");
       }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FeedbackApp();

	}

}
