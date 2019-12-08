package junittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import  org.junit.*;

import main.AnimatorThread;
import main.App;
import main.Cannon;
import main.GameFrame;
import main.Invasion;
import main.PointBar;
import main.Projectile;
import main.Space;

public class AppTest {
		double error=0.00001;
	   PointBar pb =new PointBar();
	   Space s;
	    Cannon c;
	    Invasion i;
	    
	@Before
	public  void init() {	
		s=new Space();
		c=s.cannon;
		i=s.invasion;  
	}
	
	
	 
	@Test
	 public void testCannnoMovementLeft(){
		 
		  double originalX=c.xPosition;
		  c.new MoveLeftAction().actionPerformed(null);
		  c.move();
		  assertEquals(originalX-c.STEP, c.xPosition,error );
	  }	 
	@Test
	  public void testCannnoMovementRight(){
			 
		  double originalX=c.xPosition;
		  c.new MoveRightAction().actionPerformed(null);
		  c.move();
		  assertEquals(originalX+c.STEP, c.xPosition,error );
	  }	  
	 @Test
	 public void testCannnoMovementRighAndtLeft(){
		 
		  double originalX=c.xPosition;
		  c.new MoveLeftAction().actionPerformed(null);
		  c.new MoveRightAction().actionPerformed(null);

		  c.move();
		  assertEquals(originalX, c.xPosition,error );
	  }	 
	

	 
	 @Test
	 public void TestCannonFire() {
		 int projectileNum=s.projectiles.size();
		 c.fire();
		 assertEquals(projectileNum+1, s.projectiles.size());
	 }
	 @Test
	 public void TestCannonProjectileMovement() {
		 c.fire();
		 Projectile p=s.projectiles.get(0);
		double yPos= p.yPosition;
		p.move();
		assertNotEquals(yPos, p.yPosition);
		 
	 }
	 
	 
	 @Test
	 public void TestInvasioFire() {
		 int projectileNum=s.projectiles.size();
		 i.fire();
		 assertEquals(projectileNum+1, s.projectiles.size());
	 }
	 
	 
	 
	 @SuppressWarnings("deprecation")
	@Test
	 public void TestThread() {
		 AnimatorThread at=new AnimatorThread(s);
		 Exception exc=null;
		 try {
			 at.run();
		} catch (Exception e) {
			exc=e;
		}
		 at.stop();
		 assertNull(exc);
		 
	 }
	 @Test 
	 public void testGameFrame() {
		 GameFrame gf=new GameFrame();
		 assertNotNull(gf);
	 }
	 @Test
	 public void testAppFrame() {
		 App af;
		 Exception exc=null;
		 try {
			af=new App();
		} catch (Exception e) {
			exc=e;
		}
		 assertNull(exc);
	 }
	 
	 @Test
	 public void testSaveAndLoad() {
		 GameFrame gf=new GameFrame();
		 gf.loadGame();
		 
		 assertNotNull(gf.space);
	 }
	 
	  
}
