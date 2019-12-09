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
    /**
     * Az űrlényeket tárolód "Kétdimenziós" lista
     */
    ArrayList<ArrayList<Alien>> aliens = new ArrayList<ArrayList<Alien>>();

    /**
     *Az űrlények által mozgáskor megtett pixelek száma.
     */
    int speed = 5;
    /**
     *Az űrlények mozgási iránya.
     */
    private Direction alienDirection;
    /**
     *Létrehozza a kezdetbena a hangárban lévő űrlényeket(-1,-1), madj csatasorba rendezyi őket,
     */
    public Invasion() {
        createAliens();
        getInPosition();
    }

    /**
     *Visszaadja az paraméterként kapott lista másolatát megfordítva.
     */
    public static <T> List<T> reverseList(List<T> list) {
        return list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(ArrayList::new), lst -> {
            Collections.reverse(lst);
            return lst.stream();
        })).collect(Collectors.toCollection(ArrayList::new));
    }
    /**
     * Létrehozza az űrlények adatastuktúrát.
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

    /**
     *Véletlenszerűen választ egy űrlényt, ami lövést ad le.
     */
    public void fire() {
        if (aliens.size() == 0) return;
        try {
            getRandomAlien().fire();
        } catch (Exception e) {
        }

    }

    /**
     *Az úrlényeket elrendezi a pályán.
     */
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
     * Megjelníti az összes űrlényt
     */
    public void show(Graphics g) {

        for (int y = 0; y < aliens.size(); y++) {
            for (int x = 0; x < aliens.get(y).size(); x++) {
                aliens.get(y).get(x).draw(g);
            }
        }
    }

    /**
     * Lépteti az összes űrlényt.
     */
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
     * Ha egy urleny elerte a palya valmelyik szelet szol, ekkor az osszes hajo
     * megfordul
     * Ha egy űrlény elérte a pálya szélét jelez, ekkor az összes hajó megfordul.
     */
    public void changeDirection(Direction direction) {
        alienDirection = direction;
      
        for (ArrayList<Alien> row : aliens) {
            for (Alien alien : row) {
                alien.setDirection(direction);
                alien.yPos += Alien.dimension.height;
            }
        }
        
    }

    /**
     *Megnézi,hogy az adott lövedék elaláta-e valamelyik űrlényt.
     */
    public Projectile projectileArrive(Projectile p) {

    	boolean hit=false;
    	
    	
        for (int y = 0; y < aliens.size(); y++) {
        	
        	List<Alien>toBeRemoved=new ArrayList<Alien>();
            for (int x = 0; x < aliens.get(y).size(); x++) {
                Alien a = aliens.get(y).get(x);
                
                if (p.killsAlien && Point2D.distance(a.xPos + Alien.dimension.width / 2, a.yPos + Alien.dimension.height / 2,
                        p.xPosition, p.yPosition) < Math.hypot(Alien.dimension.width, Alien.dimension.height) / 2) {
                    //aliens.get(y).remove(a);
                	hit=true;
                	toBeRemoved.add(a);
                    
                }

            }
            aliens.get(y).removeAll(toBeRemoved);
        }
        
        if(hit)return p;
        else return null;

    }


    /**
     *Visszad egy véletlenszerűen választott űrlényt.
     */
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

    /**
     *Az üres oszlopokat kiszedi,hogy ne okozzon gondot a lista iterálása közben.
     */
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
