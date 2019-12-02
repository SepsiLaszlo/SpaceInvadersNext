package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.sql.Date;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;



public class CannonAnimatorThread extends Thread {

	Space space;
	private int SELEEP_TIME=1;
	
	private long LAST_CANNON_MOVEMENT_TIME=1;
	private long CANNON_MOVE_RATE=5;
	
	private long INVASION_MOVEMENT_TIME=1;
	private long INVASION_MOVE_RATE=500;
	
	private long INVASION_FIRE_TIME=1;
	private long INVASION_FIRE_RATE=1000;
	
	private  long  TIME_BETWEEN_SHOTS=500;

	private long LAST_POJECTILE_MOVEMENT_TIME=1;
	
	

	private int TIME=1;
	private long lastFiredTime=0;
	public CannonAnimatorThread(Space space) {
		// TODO Auto-generated constructor stub
		this.space = space;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			
			if(System.currentTimeMillis()>INVASION_MOVEMENT_TIME+INVASION_MOVE_RATE) {
				space.invasion.move();
				INVASION_MOVEMENT_TIME=System.currentTimeMillis();
			}
			if(System.currentTimeMillis()>INVASION_FIRE_TIME+INVASION_FIRE_RATE) {
				space.invasion.fire();
				INVASION_FIRE_TIME=System.currentTimeMillis();
			}
			
			ArrayList<Projectile>toBeRemoved=new ArrayList<Projectile>();
			for(Projectile p: space.projectiles) {
				p.move();
			toBeRemoved.add(space.invasion.projectileArrive(p));
			toBeRemoved.add(space.cannon.projectileArrive(p));
			}
			space.projectiles.removeAll(toBeRemoved);
			
			if(space.cannon.fire&&System.currentTimeMillis()>LAST_POJECTILE_MOVEMENT_TIME+TIME_BETWEEN_SHOTS) {
				space.cannon.fire();
				LAST_POJECTILE_MOVEMENT_TIME=System.currentTimeMillis();
				}
			
			if(System.currentTimeMillis()>LAST_CANNON_MOVEMENT_TIME+CANNON_MOVE_RATE) {
				space.cannon.move();
				LAST_CANNON_MOVEMENT_TIME=System.currentTimeMillis();
			}
			
			space.repaint();
			
			try {
				sleep(SELEEP_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

	}

}
