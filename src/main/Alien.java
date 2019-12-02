package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import com.google.gson.Gson;

//urlenyeket reprezentalo osztaly
public class Alien implements Drawable, Serializable {

   
	double xPos, yPos;
	Invasion invasion;// az invayio aminek a tagja az urleny
	Direction moveDirection = Direction.RIGHT;
	double speed ;
	 static BufferedImage image;

	public static Dimension dimension = new Dimension(40, 40);// az urhalyo merete

	public Alien(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}

	public Alien() {
		this.xPos = -1;
		this.yPos = -1;
		

	}

	public Alien(Invasion invasion,int speed) {
		this.invasion = invasion;
		this.speed=speed;
		if(!(image==null))return;
		try {
			image = ImageIO.read(new File("alien.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

//kirajzolja az urlenyt
	@Override
	public void draw(Graphics g) {
		//g.fillRect(xPos, yPos, dimension.width, dimension.height);
		g.drawImage(image,(int)Math.round( xPos),(int)Math.round(yPos),dimension.width,dimension.height,null);
	}

	/*
	 * az uleny mozog a kivalasztott iranyba amig a palya syelet el nem eri. Ekkor
	 * szol az invayionak,hogy iranyt kell valtani az urleny aki a fordulast
	 * eszreveszi mar au uj iranyba lep ,hogy ne maradjon le a tarsaitol
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

	/*
	 * beallitja az urleny elhelyeykedeset az atvaltasra azert van szukseg,mert
	 * egyes helyek szamitasanal kialakulhat double ertek de rajzolo fuggvenyek csak
	 * az int tipust fogadjak el parameternel
	 */

	public void setPositon(double d, double e) {
		// TODO Auto-generated method stub
		this.xPos = d;
		this.yPos = e;

	}

	public void setDirection(Direction direction) {
		this.moveDirection = direction;
	}
	
	public void fire()
	{
		Space.projectiles.add(new AlienProjectile(xPos,yPos));
	}
}