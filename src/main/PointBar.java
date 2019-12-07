package main;

import javax.swing.*;
import java.awt.*;

/**
 * A pontokat megjelenítő bar.
 */
public class PointBar extends JPanel {
    /**
     *A pontokat megjelenítő cimke.
     */
    private static JLabel pointLabel;
    /**
     *Az életpontokat megjelítő cimke.
     */
    private static JLabel lifeLabel;
    /**
     *Inicializálja a panelet.
     */
    public PointBar() {
        // TODO Auto-generated constructor stub
        setPreferredSize(new Dimension(224 * 3, 20));

        pointLabel = new JLabel("Points: " + 0);
        add(pointLabel);

        lifeLabel = new JLabel("Life: " + 3);
        add(lifeLabel);

    }
    /**
     *Kiírja a paraméterként kapott pontokat.
     */
    public static void printPoints(int points) {
        pointLabel.setText("Points: " + points);
    }

    /**
     *Kiírja a paraméterként kapott életeket.
     */
    public static void printLives(int lives) {
        lifeLabel.setText("Lives: " + lives);
    }

}
