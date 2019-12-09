package main;

import java.util.ArrayList;


/**
 * A játékelemek mozgásáért felelős osztály.
 */
public class AnimatorThread extends Thread {
    /**
     * Az JPanel,amin minden elem elhelyezkedi.
     */
    Space space;
    /**
     * A thread által alvással töltött idő.
     */
    private int SELEEP_TIME = 1;
    /**
     *Az ágyú ultolsó mozgásának az ideje.Az ágyú mozgási sebességének szabályozásához szükséges
     */
    private long LAST_CANNON_MOVEMENT_TIME = 1;
    /**
     *Az ágyú két mozgása között eltelelt idő(milisec).
     */
    private long CANNON_MOVE_RATE = 5;
    /**
     *Az invázió utolsó mozgásának ideje.
     */
    private long INVASION_LAST_MOVEMENT_TIME = 1;
    /**
     * Az invázió mozgásai közöztt eltelt idő.
     */
    private long INVASION_MOVE_RATE = 500;
    /**
     *Az invázió utolsó lövésének az ideje.
     */
    private long INVASION_FIRE_TIME = 1;
    /**
     *Két lövés közöztt eltelt idő.
     */
    private long INVASION_FIRE_RATE = 1000;
    /**
     *Az ágyú ultolsó lövésének az ideje.
     */
    private long LAST_CANNON_FIRE_TIME = 1;
    /**
     *Két ágyúlövés között eltelt idő.
     */
    private long TIME_BETWEEN_SHOTS = 500;

    /**
     * Létrehozza a threadet.
     * @param space Az űr melynek elemeit mozgatni fog a thread.
     */
    public AnimatorThread(Space space) {
        this.space = space;
    }

    /**
     * Mozgatja a megfelelő játékelemeket.
     */
    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (!space.cannon.isGameEnded()) {

            if (System.currentTimeMillis() > INVASION_LAST_MOVEMENT_TIME + INVASION_MOVE_RATE) {
                space.invasion.move();
                INVASION_LAST_MOVEMENT_TIME = System.currentTimeMillis();
            }
            if (System.currentTimeMillis() > INVASION_FIRE_TIME + INVASION_FIRE_RATE) {
                space.invasion.fire();
                INVASION_FIRE_TIME = System.currentTimeMillis();
            }

            ArrayList<Projectile> toBeRemoved = new ArrayList<Projectile>();
            for (Projectile p : Space.projectiles) {
                p.move();

                Projectile ip = space.invasion.projectileArrive(p);
                if (ip != null) {
                    space.cannon.addPoint(10);
                }
                toBeRemoved.add(ip);
                toBeRemoved.add(space.cannon.projectileArrive(p));
            }
            Space.projectiles.removeAll(toBeRemoved);

            if (space.cannon.fire && System.currentTimeMillis() > LAST_CANNON_FIRE_TIME + TIME_BETWEEN_SHOTS) {
                space.cannon.fire();
                LAST_CANNON_FIRE_TIME = System.currentTimeMillis();
            }

            if (System.currentTimeMillis() > LAST_CANNON_MOVEMENT_TIME + CANNON_MOVE_RATE) {
                space.cannon.move();
                LAST_CANNON_MOVEMENT_TIME = System.currentTimeMillis();
            }

            space.repaint();

            try {
                sleep(SELEEP_TIME);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        try {
        	
            space.add(new MainMenu(true));
        } catch (Exception e) {
            // TODO: handle exception
        }


    }

}
