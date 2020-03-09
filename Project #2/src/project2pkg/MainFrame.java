//Reginald Eskridge

package project2pkg;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		JFrame frame = new JFrame("Patient Collection");
		frame.setSize(800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MainPanel myPanel = new MainPanel();
		frame.getContentPane().setLayout(null);
		frame.setPreferredSize(new Dimension(800,500));
		
		MainPanel mainPanel = new MainPanel();
		frame.getContentPane().add(mainPanel);
		
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				myPanel.doClose();
			}
		});
	}

}