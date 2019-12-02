package main;

import javax.swing.*;
import java.awt.*;

/**Az ablak megjelenitéséért felelős osztaly.
 *
 */
public class AppFrame extends JFrame {

    public AppFrame() {
        setResizable(false);

        add(new PointBar(), BorderLayout.NORTH);
        add(new MainMenu());
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

}