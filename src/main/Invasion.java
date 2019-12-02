package main;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Az űrlényeket irányítő osztály.
 */
public class Invasion implements Serializable {

    // private Alien[][] aliens = new Alien[5][11];
    ArrayList<ArrayList<Alien>> aliens = new ArrayList<ArrayList<Alien>>();
    double spacing = 1.5f;
    int speed = 5;
    int ALIEN_POINT = 10;
    private Direction alienDirection;

    // letrehozza a kezdetben a hangarban levo urlenyeket(-1,-1),majd csatapoyici=ba
    // rendezi oket
    public Invasion() {
        // TODO Auto-generated constructor stub
        createAliens();
        getInPosition();
    }

    public static <T> List<T> reverseList(List<T> list) {
        return list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(ArrayList::new), lst -> {
            Collections.reverse(lst);
            return lst.stream();
        })).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
     * a panel meretehey aranyosan, egymastol azonos tavolsagra elhelzez az
     * urlenyeket
     */

    public void createAliens() {
        for (int i = 0; i < 11; i++) {
            ArrayList<Alien> colum = new ArrayList<Alien>();
            for (int j = 0; j < 5; j++) {
                colum.add(new Alien(this, speed));
            }
            aliens.add(colum);
        }
    }

    public void fire() {
        if (aliens.size() == 0) return;

        try {
            getRandomAlien().fire();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void getInPosition() {

        int alienWidth = Alien.dimension.width;
        int alienHight = Alien.dimension.width;
        int frameWidth = Space.size.width;
        int frameHeight = Space.size.width;

        double spaceingHorizontally = frameWidth * (0.8f) / 11;
        double spaceingVertically = frameHeight * (0.2f) / 5;
        double offsetHorizontally = frameWidth * (0.2f) / 2;

        for (ArrayList<Alien> cols : aliens) {
            for (Alien alien : cols) {

                int y = cols.indexOf(alien);
                int x = aliens.indexOf(cols);

                alien.setPositon(offsetHorizontally + x * spaceingHorizontally, y * spaceingVertically);

            }
        }
    }
    /*
     * mozgatja az osszes urlenyt fontos,hogy mindig a haladsi irany szerinti elso
     * sor mozogjon elosszor, mert ha nem osszecsusznak a sorok
     */

    /*
     * megjelenti as osszes urlenyt SpacePanel paintComponentje parameterzi fel
     */
    public void show(Graphics g) {

        for (int y = 0; y < aliens.size(); y++) {
            for (int x = 0; x < aliens.get(y).size(); x++) {
                aliens.get(y).get(x).draw(g);
            }
        }
    }

    public void move() {

        if (aliens.size() <= 0) {
            createAliens();
            getInPosition();
        }

        if (alienDirection == Direction.LEFT) {
            for (ArrayList<Alien> row : aliens) {
                for (Alien alien : row) {
                    alien.move();
                }
            }
        } else {
            for (ArrayList<Alien> row : reverseList(aliens)) {
                for (Alien alien : reverseList(row)) {
                    alien.move();
                }
            }
        }
    }

    /*
     * ha egy urleny elerte a palya valmelyik szelet szol, ekkor az osszes hajo
     * megfordul
     */
    public void changeDirection(Direction direction) {
        alienDirection = direction;
        for (int y = 0; y < aliens.size(); y++) {
            for (int x = 0; x < aliens.get(y).size(); x++) {
                aliens.get(y).get(x).setDirection(direction);
                aliens.get(y).get(x).yPos += Alien.dimension.height;
            }
        }
    }

    public Projectile projectileArrive(Projectile p) {

        for (int y = 0; y < aliens.size(); y++) {
            for (int x = 0; x < aliens.get(y).size(); x++) {
                Alien a = aliens.get(y).get(x);
                if (p.killsAlien && Point2D.distance(a.xPos + Alien.dimension.width / 2, a.yPos + Alien.dimension.height / 2,
                        p.xPosition, p.yPosition) < Math.hypot(Alien.dimension.width, Alien.dimension.height) / 2) {
                    aliens.get(y).remove(a);
                    PointBar.addPoint(ALIEN_POINT);
                    return p;
                }

            }
        }
        return null;

    }
    // visszaad egy v�letlen �rl�nyt,az�rt shuffle ,hogy ne kelljen a hosszokra
    // figyelni

    private Alien getRandomAlien() {


        removeEmptyCols();
        ArrayList<ArrayList<Alien>> aliensCopy = new ArrayList<ArrayList<Alien>>();
        aliensCopy.addAll(aliens);

        Collections.shuffle(aliensCopy);
        for (ArrayList<Alien> row : aliensCopy) {
            Collections.shuffle(row);
        }


        return aliensCopy.get(0).get(0);

    }

    private void removeEmptyCols() {
        ArrayList<ArrayList<Alien>> rmCols = new ArrayList<ArrayList<Alien>>();

        for (ArrayList<Alien> col : aliens) {
            if (col.size() == 0) {
                rmCols.add(col);
            }
        }
        aliens.removeAll(rmCols);

    }

}
