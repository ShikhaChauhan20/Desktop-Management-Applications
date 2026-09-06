package Shikha;

import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.MouseEvent;
import java.awt.event.*;

public class mouseCoordinate {
 
	public static void main(String[] args) {
		JFrame frame= new JFrame("Mouse Coordinates");
		frame.setSize(400,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		JLabel label=new JLabel("Move the mouse inside the frame");
		label.setBounds(50,100,300,30);
		frame.add(label);
		
		frame.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int x=e.getX();
				int y=e.getY();
			    label.setText("Mouse Coordinates: x=" +x+ "Y=" +y);	
			}
		});
		frame.setVisible(true);
	}
}
