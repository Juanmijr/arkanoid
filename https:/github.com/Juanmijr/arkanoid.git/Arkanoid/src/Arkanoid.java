package arkanoid;
/**
 * Curso B�sico de desarrollo de Juegos en Java - Invaders
 * 
 * (c) 2004 Planetalia S.L. - Todos los derechos reservados. Prohibida su reproducci�n
 * 
 * http://www.planetalia.com
 * 
 */

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Arkanoid extends Canvas implements Stage {
	
	
	private long usedTime;
	private SpriteCache spriteCache;
	//NAVE Y PELOTA
	private Nave nave;
	private Pelota pelota;
	public static final int FPS = 100; // Frames por segundo
	// Lista de actores que se representan en pantalla
	private List<Explosion>ex = new ArrayList <Explosion>();
	private List<Objetos>objets = new ArrayList <Objetos>();
	// Estrategia de Doble Buffer
	private BufferStrategy strategy;
	// Variable para patr�n Singleton
	private static Arkanoid instancia = null;
	
	
	/**
	 * SINGLETON
	 * @return
	 */
	public static Arkanoid getInstancia () {
		if (instancia == null) {
			instancia = new Arkanoid();
		}
		return instancia;
	}
		
	public Arkanoid() {
		spriteCache = new SpriteCache();
		
		JFrame ventana = new JFrame("Invaders");
		JPanel panel = (JPanel) ventana.getContentPane();
		setBounds(0, 0, Stage.WIDTH, Stage.HEIGHT);
		panel.setPreferredSize(new Dimension(Stage.WIDTH, Stage.HEIGHT));
		panel.setLayout(null);
		panel.add(this);
		ventana.setBounds(0, 0, Stage.WIDTH, Stage.HEIGHT);
		ventana.setVisible(true);
		ventana.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				CerrarAplicacion();
			}
		});
		ventana.setResizable(false);
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		requestFocus();

		this.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				nave.keyReleased(e);
			}

			public void keyPressed(KeyEvent e) {
				nave.keyPressed(e);
			}
		});

		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				nave.mouseMoved(e);
			}
		});
	}
	
	private void CerrarAplicacion() {
		
	}

	public void initWorld() {
		
		for (int i = 0; i < 23; i++) {
			LadrilloRosa rosa = new LadrilloRosa(this);
			rosa.setX((i * rosa.width) + 12);
			rosa.setY(50 + rosa.height);
			objets.add(rosa);
		}
		for (int i = 0; i < 23; i++) {
			LadrilloAzul azul = new LadrilloAzul(this);
			azul.setX((i * azul.width) + 12);
			azul.setY(50 + azul.height + 10);
			objets.add(azul);
		}
		for (int i = 0; i < 23; i++) {
			LadrilloVerde verde = new LadrilloVerde(this);
			verde.setX((i * verde.width) + 12);
			verde.setY(50 + verde.height + 20);
			objets.add(verde);
		}

		nave = new Nave(this);
		nave.setX(Stage.WIDTH / 2);
		nave.setY(Stage.HEIGHT - 100);
		pelota = new Pelota(this);
		pelota.setX(Stage.WIDTH / 2);
		pelota.setY(Stage.HEIGHT / 2);
		pelota.setVx(1);
		pelota.setVy(1);
	}

	public void updateWorld() {
		for (int i = 0; i < objets.size(); i++) {
			Explosion explota = new Explosion (this);
			Ladrillos ladrillos = (Ladrillos) objets.get(i);
			explota.setX(ladrillos.getX());
			explota.setY(ladrillos.getY());
			ex.add(explota);
		
			
		}
		nave.act();
		pelota.act();
	}

	public void paintWorld() {
		Toolkit.getDefaultToolkit().sync();
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		g.drawImage(spriteCache.getSprite("fondo.jpg"), 0, 0, this);
		for (int i = 0; i < objets.size(); i++) {
			Objetos m = (Objetos) objets.get(i);
			Objetos v = (Objetos) objets.get(i);
			m.paint(g);
		}
		nave.paint(g);
		pelota.paint(g);

		strategy.show();
	}

	public SpriteCache getSpriteCache() {
		return spriteCache;
	}

	public void game() {
		initWorld();
		while (isVisible()) {
			updateWorld();
			checkCollisions();
			remove();
			paintWorld();
			

			try {
				Thread.sleep(SPEED);
			} catch (InterruptedException e) {
			}
		}
	}

	public void checkCollisions() {
		Rectangle pelotaBounds = pelota.getBounds();
		Rectangle naveBounds = nave.getBounds();
		if (naveBounds.intersects(pelotaBounds) && pelotaBounds.intersects(naveBounds)) {
			pelota.vy = -pelota.vy;
		}
		for (int i = 0; i < objets.size(); i++) {
			Objetos a1 = (Objetos) objets.get(i);
			Rectangle r1 = a1.getBounds();
			if (r1.intersects(pelotaBounds)) {
				pelota.collision(a1);
				a1.collision(pelota);

			}
			

		}

	}

	public void remove() {
		for (int i = 0; i < objets.size(); i++) {
			Ladrillos ladrillos = (Ladrillos) objets.get(i);
			if (ladrillos.isTouched == true) {
				objets.remove(i);
				ex.add((Explosion)explosion);
				ex.remove(explosion);
			}
		}
	}

	public static void main(String[] args) {
		Arkanoid inv = new Arkanoid();
		inv.game();
	}
}
