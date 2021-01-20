package by.khmara.calculator.main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Calculator {
	
	 JFrame window;
	 
	 Calculator() {
		 
		 ImageIcon image = new ImageIcon(getClass().getResource("\\images\\netflix.jpeg"));
		 
		 window = new JFrame("Calculator");
		 window.setIconImage(image.getImage());
		 window.setSize(415,637);
		 window.setVisible(true);
		 window.setLocationRelativeTo(null);
		 window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 window.add(new Panel());
		 
		 
	 }
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				new Calculator();
			}
		});

	}

}
