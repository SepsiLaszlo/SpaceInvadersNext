package main;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

/**
 * A játékotot irányító keret.
 */
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

    }

    public void newGame() {
        add(space);
        AnimatorThread cat = new AnimatorThread(space);
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

        AnimatorThread cat = new AnimatorThread(space);
        cat.start();
        pack();
    }

}
