package junittest;

import static org.junit.Assert.*;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import main.Invasion;
import main.MainMenu;
import main.Space;

public class InvasionTest {

	MainMenu mm;
	@Before
	public void init() {
		 mm = new MainMenu();
	}
	
	@Test
	public void testLoad() {
		mm.new LoadAction().actionPerformed(null);
		
	}
	
	@Test
	public void testNew() {
		mm.new NewAction().actionPerformed(null);
	}
	@Test
	public void testSpacePaint() {
		Space s=new Space();
		s.repaint();
	}
	
	
	
	

}
