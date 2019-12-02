package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.io.Serializable;

import javax.swing.AbstractAction;

// a jatekos altal mozgathato agyu
public class Cannon implements Drawable,Serializable {

	Dimension dimension = new Dimension(100, 40);
	double xPosition = -1;
	double yPosition = -1;
	Direction moveDirection = Direction.NONE;
	boolean left = false;
	boolean right = false;
	boolean fire =false;
	int health=3;

	double STEP = 2;

	public Cannon() {

		initPosition();
	}

	private void initPosition() {
		yPosition = Space.size.height - dimension.height;
		xPosition = (int) (Space.size.width / 2 - dimension.height);
	}

	void fire() {
		// System.out.println("Fire! " + xPosition + " " + yPosition);
		
		Space.projectiles.add(new CannnonProjectile(xPosition+dimension.width/2, yPosition));

	}
// a felengedést is követni kell

	public void move() {
		// TODO Auto-generated method stub
		if (right == left)
			return;

		if (left)
			xPosition -= STEP;
		if (right)
			xPosition += STEP;
	}
	
	public Projectile projectileArrive(Projectile p) {
		if (p.killsCannon && Point2D.distance(xPosition + dimension.width / 2, yPosition + dimension.height / 2,
				p.xPosition, p.yPosition) < Math.hypot(dimension.width, dimension.height) / 2) {
			hit();
			return p;
		}
		return null;
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.GREEN);
		g.fillRect((int) Math.round(xPosition), (int) Math.round(yPosition), dimension.width, dimension.height);
		
	}
	
	public void hit() {
		
		health--;
		PointBar.printLives(health);
		if(health<=0) {GameFrame.endGame();}
	}

	public class StartFireAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			fire=true;
		}
                               
	}
	
	public class StopFireAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			fire=false;
		}

	}

	public class MoveLeftAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			left = true;
		}

	}

	public class StopLeftAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			left = false;
		}

	}

	public class MoveRightAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			right = true;
		}

	}

	public class StopRightAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			right = false;
		}

	}
	
	

}
