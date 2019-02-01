/**
 * Curso Básico de desarrollo de Juegos en Java - Invaders
 * 
 * (c) 2004 Planetalia S.L. - Todos los derechos reservados. Prohibida su reproducción
 * 
 * http://www.planetalia.com
 * 
 */
package tutorialJava.SpaceInvaders.version28;

import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

public class SpriteCache extends ResourceCache{
	
	protected Object loadResource(URL url) {
		try {
			return ImageIO.read(url);
		} catch (Exception e) {
			System.out.println("No se pudo cargar la imagen de "+url);
			System.out.println("El error fue : "+e.getClass().getName()+" "+e.getMessage());
			System.exit(0);
			return null;
		}
	}
	
	public BufferedImage getSprite(String name) {
		return (BufferedImage)getResource(name);
	}
}
