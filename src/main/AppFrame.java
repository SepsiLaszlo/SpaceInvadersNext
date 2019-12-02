package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*Az ablak megjeleniteseert felelos osztaly
* a nem mértezezhetõ a mérete megfelel  a Panel méretének 
*/
public class AppFrame extends JFrame {

	public AppFrame() {
		// TODO Auto-generated constructor stub
		// setSize(dimension);
		setResizable(false);

		add(new PointBar(), BorderLayout.NORTH);
		add(new MainMenu());
		setLocationRelativeTo(null);

		
		pack();

		setVisible(true);
	}

	public void newGame() {
		
		
	}
	
	class NewGameAction implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			newGame();
		}
	}
}