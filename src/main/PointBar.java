package main;

import javax.swing.*;
import java.awt.*;
/**
* A pontokat megjelenítő bar.
 */
public class PointBar extends JPanel {

    public static int points = 0;
    private static JLabel pointLabel;
    private static JLabel lifeLabel;

    public PointBar() {
        // TODO Auto-generated constructor stub
        setPreferredSize(new Dimension(224 * 3, 20));

        pointLabel = new JLabel("Points: " + points);
        add(pointLabel);


        lifeLabel = new JLabel("Life: " + 3);
        add(lifeLabel);

    }

    public static void addPoint(int point) {
        points += point;
        pointLabel.setText("Points: " + points);
    }

    public static void printLives(int lives) {

        lifeLabel.setText("Lives: " + lives);
    }

}
