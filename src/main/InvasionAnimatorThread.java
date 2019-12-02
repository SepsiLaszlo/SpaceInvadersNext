package main;

import java.awt.Color;




public class InvasionAnimatorThread extends Thread {

	Space space;
	private int SELEEP_TIME=500;

	public InvasionAnimatorThread(Space space) {
		// TODO Auto-generated constructor stub
		this.space = space;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			
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
