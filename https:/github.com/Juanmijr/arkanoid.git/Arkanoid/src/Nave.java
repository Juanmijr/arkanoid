package arkanoid;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Nave extends Objetos {
	protected int vx;
	protected int vy;
	private boolean izqda,dcha;
	protected static final int VELOCIDAD_JUG = 2;
		
	
	public Nave(Stage stage) {
		super(stage);
		setSpriteNames( new String[] {"nave.png"});
	}
	
	public void act() {
		super.act();
	x+=vx;
		if (x < 0 ) {
			x=0;
		}
		  
		if (x>Stage.WIDTH-width-8) {
			x= Stage.WIDTH-width-8;
		}
	
	}
	
	protected void updateSpeed() {
		vx=0;
		
		if(izqda) vx = -VELOCIDAD_JUG;
		if (dcha) vx = VELOCIDAD_JUG;
	}

	public int getVx() { return vx; }
	public void setVx(int i) {vx = i;	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT : izqda = false; break;
		case KeyEvent.VK_RIGHT : dcha = false; break;
		}
	updateSpeed();	
	}
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT : izqda = true; break;
		case KeyEvent.VK_RIGHT : dcha = true; break;
		}
	updateSpeed();	
	}
	
	public void mouseMoved (MouseEvent e) {
		x = e.getXOnScreen()- (getWidth()*2 + getWidth()/2);
		act();
	}
	public void collision(Objetos a){
		if (a instanceof Pelota) {
		vy=-vy;
		}
	}
	

}
