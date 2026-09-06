package Shikha;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.*;
public class numchar {

	public static void main(String[] args) {
		JFrame frame= new JFrame("Character Count");
		frame.setSize(400,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        JTextField textField= new JTextField();
        textField.setBounds(50,50,200,30);
        frame.add(textField);
         
        JLabel label = new JLabel("Characters");
        label.setBounds(50,100,300,30);
        frame.add(label);
        textField.addKeyListener(new KeyAdapter() {
        	
        	public void keyReleased(KeyEvent e) {
        		String text=textField.getText();
        		int length= text.length();
        	label.setText("Characters typed "+length);
        	}
        });
        frame.setVisible(true);
	}

}
