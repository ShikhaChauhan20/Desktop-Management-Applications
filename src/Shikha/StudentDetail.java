package Shikha;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.*;

public class StudentDetail {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
          JFrame frame = new JFrame("Student Details");
          frame.setSize(430,420);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setLayout(null);
          
          JLabel nameLabel=new JLabel("name:");
          nameLabel.setBounds(50,30,100,30);
          JTextField nameField=new JTextField();
          nameField.setBounds(150,30,200,30);
          
          JLabel rollLabel= new JLabel("roll no.");
          rollLabel.setBounds(50, 70, 100, 30);
          JTextField rollField= new JTextField();
          rollField.setBounds(150,70,200,30);
          
          JLabel genderLabel= new JLabel("gender:");
          genderLabel.setBounds(50,110,100,30);
          JRadioButton female = new JRadioButton("Female");
          female.setBounds(150,110,80,30);
          JRadioButton male = new JRadioButton("Male");
          male.setBounds(230,110,80,30);
          ButtonGroup genderGroup= new ButtonGroup();
          genderGroup.add(female);
          genderGroup.add(male);
          
          JLabel courseLabel= new JLabel("course");
          courseLabel.setBounds(50,150,100,30);
          String[] courses= {"BTech", "BCA", "Mtech","MBA"};
          JComboBox<String> courseBox= new JComboBox<>(courses);
          courseBox.setBounds(150,150,200,30);
          
          JLabel subjectLabel = new JLabel("subjects:");
          subjectLabel.setBounds(50,190,100,30);
          JCheckBox sub1= new JCheckBox("JAVA");
          sub1.setBounds(150,190,60,30);
          JCheckBox sub2= new JCheckBox("DSA");
          sub2.setBounds(220, 190, 60, 30);
          JCheckBox sub3= new JCheckBox("MATH");
          sub3.setBounds(290,190,60,30);
          
          JLabel addressLabel = new JLabel("address:");
          addressLabel.setBounds(50,230,100,30);
          JTextArea addressArea = new JTextArea();
          addressArea.setBounds(150,230,200,60);
          
          JButton submitButton= new JButton("submit");
          submitButton.setBounds(150,310,100,30);
          
          frame.add(nameLabel); frame.add(nameField);
          frame.add(rollLabel); frame.add(rollField);
          frame.add(genderLabel); frame.add(female); frame.add(male);
          frame.add(courseLabel); frame.add(courseBox);
          frame.add(subjectLabel); frame.add(sub1); frame.add(sub2); frame.add(sub3);
          frame.add(addressLabel); frame.add(addressArea);
          frame.add(submitButton);
          
          submitButton.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent e) {
        		  String name= nameField.getText();
        		  String roll= rollField.getText();
        		  String gender=male.isSelected() ? "Male" : (female.isSelected()? "Female":"Not Selected");
        		  String course=(String) courseBox.getSelectedItem();
        		  String subjects="";
        		  if(sub1.isSelected()) subjects+="Advanced JAVA";
        		  if(sub2.isSelected()) subjects+="DSA";
        		  if(sub3.isSelected()) subjects+="MATH";        				  
        		 
        		  String address= addressArea.getText();
        		  String message =  "name:" + name+ "\nRoll NO." + roll+
        				  "\nGender:" +gender+ "\n Course:" + course+
        				  "\subjects:"+subjects+"\nAddress:" +address;
        		  JOptionPane.showMessageDialog(frame, message,"Student Details", JOptionPane.INFORMATION_MESSAGE);
        		  
        	  }
          });
          frame.setVisible(true);
	}

}
