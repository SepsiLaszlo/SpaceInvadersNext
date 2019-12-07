package main;

import java.awt.*;
import java.io.Serializable;

/**
 * Egy űrlény lövedéke.
 */

public class AlienProjectile extends Projectile implements Serializable {

    AlienProjectile(double x, double y) {
        super(x, y);
        super.killsAlien = false;
        super.killsCannon = true;
    }



    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) Math.round(xPosition), (int) Math.round(yPosition), dimension.width, dimension.height);
    }


    @Override
    public void move() {
        // TODO Auto-generated method stub
        super.yPosition += super.step;
    }

}
