package arkanoid;

public class Pelota extends Objetos {
	protected int vx;
	protected int vy;
	
	
	public Pelota(Stage stage) {
		super(stage);
		setSpriteNames( new String[] {"Bola.png"});
	}
	
	public void act() {
		x+=vx;
		y+=vy;
		if (x < 0 || x > Stage.WIDTH-15)			
		  vx = -vx;
		if (y<0 || y > Stage.HEIGHT-35)
			vy= -vy;
	}

	public int getVx() { return vx; }
	public int getVy() { return vy; }
	public void setVx(int i) {vx = i;	}
	public void setVy(int i) {vy = i; }
	
	public void collision(Objetos a){
		if (a instanceof Ladrillos) {
		vy = -vy;

		}
		if (a instanceof Nave) {
			vy = -vy;
		}
	}

	
	

}
