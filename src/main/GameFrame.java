package main;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

/**
 * A játékotot irányító keret.
 */
public class GameFrame extends JFrame {
    /**
     * A panel, amin az aktuális játékelemek vannak.
     */
   public Space space;
    /**
     * A játékot mozgató thread példánya.
     */
    AnimatorThread cat;

    /**
     *Inicializálja a keretet, bállitja a bázáráskor megívódó mentést.
     */
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

    /**
     *A jatek végekor hívódik meg.
     */
    public static void endGame() {
        System.out.println("GameOver");
    }

    /**
     *Új játékot kezd hoz létre.
     */
    public void newGame() {
        add(space);
        cat = new AnimatorThread(space);
        cat.start();
        pack();
    }

    /**
     *Elmenti a játékot.
     */
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

    /**
     *Visszatölti az elmentett játákot.
     */
    public void loadGame() {

        try {
            FileInputStream f = new FileInputStream("space.txt");
            ObjectInputStream in = new ObjectInputStream(f);
            space = (Space) in.readObject();
            space.cannon.printState();
            in.close();
        } catch (IOException ex) {

        } catch (ClassNotFoundException ex) {

        }
        add(space);

        cat = new AnimatorThread(space);
        cat.start();
        pack();
    }
}
