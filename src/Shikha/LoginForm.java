package Shikha;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginForm extends JFrame implements ActionListener {
	private JLabel userLabel,passLabel,messageLabel;
	private JTextField userText;
	private JPasswordField passText;
	private JButton loginButton,resetButton;
	
	public LoginForm() {
		setTitle("Login Form");
		setSize(380,260);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		userLabel=new JLabel("UserName");
		userLabel.setBounds(40,30,90,25);
		add(userLabel);
		
		userText= new JTextField();
		userText.setBounds(140,30,180,25);
		add(userText);
		
		passLabel=new JLabel("Password:");
		passLabel.setBounds(40,70,90,25);
		add(passLabel);
		
		passText = new JPasswordField();
        passText.setBounds(140, 70, 180, 25);
        add(passText);
        
		loginButton= new JButton("Login");
		loginButton.setBounds(60,120,100,30);
		loginButton.addActionListener(this);
		add(loginButton);
		
		resetButton=new JButton("Reset");
		resetButton.setBounds(190,120,100,30);
		resetButton.addActionListener(this);
		add(resetButton);
		
		messageLabel=new JLabel("");
		messageLabel.setBounds(40,170,300,25);
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(messageLabel);
		
		setVisible(true);
	}
@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==loginButton) {
		String username=userText.getText();
		String password=new String (passText.getPassword());
		
		if(username.equals("admin") && password.equals("admin123")){
				messageLabel.setForeground(Color.GREEN);
				messageLabel.setText("Login Successful! welcome," + username+".");
				JOptionPane.showMessageDialog(this,"Welcome"+username+"!","Success",JOptionPane.INFORMATION_MESSAGE);
	}
	else {
		messageLabel.setForeground(Color.RED);
		messageLabel.setText("Invalid Username or Password.");
		JOptionPane.showMessageDialog(this, "Access Denied :Invalid Credentials","Error",JOptionPane.ERROR_MESSAGE);
		
	}
}else if(e.getSource()== resetButton) {
	userText.setText("");
	passText.setText("");
	messageLabel.setText("");
}
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
new LoginForm();
	}

}
