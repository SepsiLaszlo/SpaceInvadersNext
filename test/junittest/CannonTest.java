package junittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.Alien;
import main.Cannon;

public class CannonTest {
	Cannon c;

	@Before
	public void init() {
		c = new Cannon();
	}

	@Test
	public void testMovementLeft() {
		c.new MoveLeftAction().actionPerformed(null);
		c.move();
		c.new StopLeftAction().actionPerformed(null);
		c.move();
	}
	
	@Test
	public void testMovementRight() {
		c.new MoveRightAction().actionPerformed(null);
		c.move();
		c.new StopRightAction().actionPerformed(null);
		c.move();


	}
	@Test
	public void testFire() {
		c.new StartFireAction().actionPerformed(null);
		c.new StopFireAction().actionPerformed(null);
	}
	

}
