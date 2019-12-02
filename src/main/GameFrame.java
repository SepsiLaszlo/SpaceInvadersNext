package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import com.google.gson.*;

public class GameFrame extends JFrame {

	Space space;

	public GameFrame() {

		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				saveGame();
			}
		});

		setVisible(true);

		space = new Space();

	}

	public static void endGame() {
		System.out.print("end");
	}

	public void newGame() {
		add(space);
		CannonAnimatorThread cat = new CannonAnimatorThread(space);
		cat.start();
		pack();
	}

	public void saveGame() {
		try {

			FileOutputStream f = new FileOutputStream("space.txt");
			ObjectOutputStream out = new ObjectOutputStream(f);
			out.writeObject(space);
			out.close();
			f.close();
		} catch (IOException ex) {
			ex.printStackTrace();

		}
	}

	public void loadGame() {

		try {
			FileInputStream f = new FileInputStream("space.txt");
			ObjectInputStream in = new ObjectInputStream(f);
			space = (Space) in.readObject();
			in.close();
		} catch (IOException ex) {

		} catch (ClassNotFoundException ex) {

		}
		add(space);

		CannonAnimatorThread cat = new CannonAnimatorThread(space);
		cat.start();
		pack();
	}

}
