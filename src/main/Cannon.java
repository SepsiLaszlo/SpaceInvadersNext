package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 * A jatekos által mozgatható ágyú.
 */
public class Cannon implements Drawable, Serializable {

    /**
     * Az ágyú szélességét és magasságát tárolja.
     */
    Dimension dimension = new Dimension(100, 40);
    /**
     * Az ágyú helyzetlének x komponense.
     */
   public double xPosition = -1;
    /**
     * Az ágyú helyzetének y komponense.
     */
   public double yPosition = -1;
    /**
     * A játékos lenyomta a balra mozgás gombját.
     */
    boolean left = false;
    /**
     * A játékos lenyomta a jobbra mozgás gombját.
     */
    boolean right = false;
    /**
     * A játékos lenyomva tartja a lövés gombját.
     */
    boolean fire = false;
    /**
     * A játékos életeinek száma.
     */
    int health = 3;
    /**
     * A játkékos pontjainak száma.
     */
    int points = 0;
    /**
     * A játék végetérését jelzi.
     */
    boolean gameEnded = false;

    /**
     * A pixelek száma,amelyeket az ágyú egy lépés során megtesz.
     */
   public double STEP = 2;

    /**
     * Lérehozza az ágyút, elhelyezni és kiirja az adatait.
     */
    public Cannon() {
        initPosition();
        printState();
    }

    /**
     * A PointBar-ra kiírja az ágyú életeit és pontjait.
     */
    public void printState() {
        PointBar.printLives(health);
        PointBar.printPoints(points);
    }

    /**
     * Elhelyezi az ágyút a panel alján és közepén.
     */
    private void initPosition() {
        yPosition = Space.size.height - dimension.height;
        xPosition = Space.size.width / 2 - dimension.height;
    }

    /**
     * Kilő egy lövedéket az ágyú helyzetéből.
     */
    public void fire() {
        Space.projectiles.add(new CannnonProjectile(xPosition + dimension.width / 2, yPosition));

    }

    /**
     * Lépteti az ágyút,ha a játékos lenyomta a megfeleő gomokat.
     */
    public void move() {
        // TODO Auto-generated method stub
        if (right == left)
            return;

        if (left)
            xPosition -= STEP;
        if (right)
            xPosition += STEP;
    }

    /**
     *Megnézi, hogy a lövedék eltalálta-e az ágyút. És visszaadja ,ha igen.
     */
    public Projectile projectileArrive(Projectile p) {
        if (p.killsCannon && Point2D.distance(xPosition + dimension.width / 2, yPosition + dimension.height / 2,
                p.xPosition, p.yPosition) < Math.hypot(dimension.width, dimension.height) / 2) {
            hit();
            return p;
        }
        return null;
    }

    /**
     *Kirajzolja az ágyút.
     */

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        g.setColor(Color.GREEN);
        g.fillRect((int) Math.round(xPosition), (int) Math.round(yPosition), dimension.width, dimension.height);

    }

    /**
     *Növli a pontokat.
     */
    public void addPoint(int point) {
        points += point;
        PointBar.printPoints(points);
    }

    /**
     *Az ágyú találaltot kap.
     */
    public void hit() {

        health--;
        PointBar.printLives(health);
        if (health <= 0) {
            GameFrame.endGame();
            gameEnded = true;
        }
    }

    /**
     *Visszadja,hogy véget ért-e a játék.
     */
    public boolean isGameEnded() {
        return gameEnded;
    }

    /**
     *A játékos lenyomta a tüzelés gombot
     */
    public class StartFireAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            fire = true;
        }

    }

    /**
     *A játékos felengedete a tüzelés gombot.
     */
    public class StopFireAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            fire = false;
        }

    }

    /**
     *A játékos lenyomta a balra mozgás gombot.
     */
    public class MoveLeftAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            left = true;
        }

    }

    /**
     *A jákos felengedte a balramozgás gombot.
     */
    public class StopLeftAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            left = false;
        }

    }

    /**
     *A játékos lenyomta a jobbramozgás gombot.
     */
    public class MoveRightAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            right = true;
        }

    }

    /**
     *A játékos felengedte a jobbramozgás gombot.
     */
    public class StopRightAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            right = false;
        }

    }

}
