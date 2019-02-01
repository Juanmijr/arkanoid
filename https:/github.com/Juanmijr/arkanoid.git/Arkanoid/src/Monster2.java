/**
 * Curso Básico de desarrollo de Juegos en Java - Space Invaders
 * 
 * (c) 2004 Planetalia S.L. - Todos los derechos reservados. Prohibida su reproducción
 * 
 * http://www.planetalia.com
 * 
 */

package arkanoid;
public class Monster2 extends Objetos {
	protected int vx;
	protected int vy;
	
	public Monster2(Stage stage) {
		super(stage);
		setSpriteNames( new String[] {"bola.png"});
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

}
