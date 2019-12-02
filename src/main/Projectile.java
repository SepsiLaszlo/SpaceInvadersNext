package main;

import java.awt.*;
import java.io.Serializable;

/**
 * A lövedékek absztrakt őse.
 */
public abstract class Projectile implements Drawable, Serializable {

    double xPosition = -1;
    double yPosition = -1;
    double step = 1;
    Direction d;
    boolean killsAlien;
    boolean killsCannon;
    Dimension dimension = new Dimension(10, 10);


    Projectile(double x, double y) {
        xPosition = x - dimension.width / 2;
        yPosition = y;
    }

    public abstract void move();

}
