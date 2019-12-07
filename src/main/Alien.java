package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;


/**
 * Az űrlényeket reprezentáló osztály.
 */
public class Alien implements Drawable, Serializable {

    /**
     * Egy űrlény szélességét és magasságát tárolja.
     */
    static Dimension dimension = new Dimension(40, 40);
    /**
     * Az űrlényekhez tartozó kép.
     */
    private static BufferedImage image;
    /**
     * Az űtlény helyét meghatározó koordináták.
     */
    double xPos, yPos;
    /**
     * Az ivázió amihez az egyed tartozik.
     */
    private Invasion invasion;
    /**
     * Az irány amibe halad.
     */
    private Direction moveDirection = Direction.RIGHT;
    /**
     * A megadott függőleges haladási sebesség.
     */
    private double speed;

    /**
     * Űrlény létrehozása egy adott inváziban, sadott sebességgel.
     */
    public Alien(Invasion invasion, int speed) {
        this.invasion = invasion;
        this.speed = speed;
        if (!(image == null)) return;
        try {
            image = ImageIO.read(new File("alien.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    /**
     * Kirajozlja az űrlényt.
     */
    @Override
    public void draw(Graphics g) {
        //g.fillRect(xPos, yPos, dimension.width, dimension.height);
        g.drawImage(image, (int) Math.round(xPos), (int) Math.round(yPos), dimension.width, dimension.height, null);
    }

    /**
     * Az űrlényt mozgatja a kiválasztott irányba, amíg az el nem éri a pálya szélét
     * Ekkor az űrlény jelez az inváziónak,hogy irányt kell váltani.
     */
    public void move() {

        switch (moveDirection) {
            case RIGHT:
                if (xPos + dimension.width + speed >= Space.size.width) {
                    invasion.changeDirection(Direction.LEFT);
                    xPos -= speed;
                } else {
                    xPos += speed;
                }
                break;
            case LEFT:
                if (xPos - speed <= 0) {
                    invasion.changeDirection(Direction.RIGHT);
                    xPos += speed;
                } else {
                    xPos -= speed;
                }
                break;

            default:
                throw new IllegalArgumentException("Unexpected value: " + moveDirection);
        }

    }

    /**
     *un Bellítja az űrlény koordinátáit.
     */

    public void setPositon(double d, double e) {
        // TODO Auto-generated method stub
        this.xPos = d;
        this.yPos = e;

    }

    /**
     *Beállítja az űrlény haladási irányát.
     */

    public void setDirection(Direction direction) {
        this.moveDirection = direction;
    }

    /**
     *Kilő egy lövedéket egyed pozíciőjáből, lefelé.
     */

    public void fire() {
        Space.projectiles.add(new AlienProjectile(xPos, yPos));
    }
}