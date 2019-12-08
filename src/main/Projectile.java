package main;

import java.awt.*;
import java.io.Serializable;

/**
 * A lövedékek absztrakt őse.
 */
public abstract class Projectile implements Drawable, Serializable {
    /**
     *A lovedékek helyzetének x komponense.
     */

    public double xPosition = -1;
    /**
     *A lövedékek helyzetének y komponense.
     */
    public double yPosition = -1;
    /**
     * A lövedék által megtett pixelek száma egy mozgás során.
     */
    double step = 1;
    /**
     *Eltalálhat-e űrlényt.
     */
    boolean killsAlien;
    /**
     *Eltalálhat-e ágyút.
     */
    boolean killsCannon;
    /**
     * Az lövedék szélességét és magasságát tároló változó.
     */
    Dimension dimension = new Dimension(10, 10);


    /**
     *Az adott pontban létrehoz egy lövedéket.
     */
    Projectile(double x, double y) {
        xPosition = x - dimension.width / 2;
        yPosition = y;
    }

    /**
     *Mozgatja a lövedéket.
     */
    public abstract void move();

}
