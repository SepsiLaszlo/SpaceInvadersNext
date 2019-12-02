package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu extends JFrame {

	public MainMenu() {
		JPanel jp = new JPanel();

		JButton lb = new JButton("Load Game");
		lb.addActionListener(new LoadAction());
		jp.add(lb);

		JButton nb = new JButton("New Game");
		nb.addActionListener(new NewAction());
		jp.add(nb);

		add(jp);
		pack();
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setVisible(true);

	}

	class LoadAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			PointBar pb = new PointBar();
			GameFrame gf = new GameFrame();
			gf.loadGame();
			gf.add(pb, BorderLayout.NORTH);
			gf.pack();
		}
	}
	class NewAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			PointBar pb=new PointBar();
			GameFrame gf = new GameFrame();
			gf.newGame();
			gf.add(pb, BorderLayout.NORTH);
			gf.pack();

		}

	}

}
