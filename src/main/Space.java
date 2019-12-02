package main;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *A játékelemeket tartalmazó panel.
 */
public class Space extends JPanel implements Serializable {
    // eredeti jatek felbontasanak a tobbszorose
    public static Dimension size = new Dimension(224 * 3, 256 * 3);
    static List<Projectile> projectiles = Collections.synchronizedList(new ArrayList<Projectile>());
    Invasion invasion = new Invasion();
    Cannon cannon = new Cannon();

    public Space() {
        setPreferredSize(size);

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed SPACE"), "START_FIRE");
        this.getActionMap().put("START_FIRE", cannon.new StartFireAction());
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released SPACE"), "STOP_FIRE");
        this.getActionMap().put("STOP_FIRE", cannon.new StopFireAction());

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed LEFT"), "MOVE_LEFT");
        this.getActionMap().put("MOVE_LEFT", cannon.new MoveLeftAction());
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released LEFT"), "STOP_LEFT");
        this.getActionMap().put("STOP_LEFT", cannon.new StopLeftAction());

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed RIGHT"), "MOVE_RIGHT");
        this.getActionMap().put("MOVE_RIGHT", cannon.new MoveRightAction());
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released RIGHT"), "STOP_RIGHT");
        this.getActionMap().put("STOP_RIGHT", cannon.new StopRightAction());


    }

    //egy kirajzolja a jatekelemeket
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        invasion.show(g);
        cannon.draw(g);
        for (Projectile projectile : projectiles) {
            projectile.draw(g);
        }
    }

}
