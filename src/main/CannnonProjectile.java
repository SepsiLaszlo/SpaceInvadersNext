package main;

import java.awt.Color;
import java.awt.Graphics;

public class CannnonProjectile extends Projectile implements Drawable {


	CannnonProjectile(double x, double y) {
		super(x, y);
		super.killsAlien=true;
		super.killsCannon=false;
		// TODO Auto-generated constructor stub
	}
	public void move() {
		super.yPosition-=super.step;
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.GREEN);
		g.fillRect((int) Math.round(xPosition), (int) Math.round(yPosition), dimension.width, dimension.height);
		
	}
}
