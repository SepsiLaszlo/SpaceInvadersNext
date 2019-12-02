package main;

import java.awt.Color;
import java.awt.Graphics;

public class AlienProjectile extends Projectile
{

	AlienProjectile(double x, double y) {
		super(x, y);
		super.killsAlien=false;
		super.killsCannon=true;
	
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
		g.fillRect((int) Math.round(xPosition), (int) Math.round(yPosition), dimension.width, dimension.height);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		super.yPosition+=super.step;
	}

}
