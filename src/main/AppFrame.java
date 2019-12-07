package main;

import javax.swing.*;
import java.awt.*;

/**Az ablak megjelenitéséért felelős osztaly.
 *
 */
public class AppFrame extends JFrame {
    /**
     * Hozzádja a kerethez a PointBart és a MainMenut
     */
    public AppFrame() {
        setResizable(false);

        add(new PointBar(), BorderLayout.NORTH);
        add(new MainMenu(false));
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

}