package main;

import java.awt.Dimension;
import java.io.Serializable;

public abstract class Projectile implements Drawable,Serializable{
	
	double xPosition = -1;
	double yPosition = -1;
	double step=1;
	Direction d;
	boolean killsAlien;
	boolean killsCannon;
	
	Projectile(double x,double y) {
			xPosition=x-dimension.width/2;
			yPosition=y;
		}
	
	
	Dimension dimension = new Dimension(10, 10);
	public abstract void move();
		
}
