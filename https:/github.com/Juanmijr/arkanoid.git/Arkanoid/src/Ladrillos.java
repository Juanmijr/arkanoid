package arkanoid;

public class Ladrillos extends Objetos{

	public boolean isTouched=false;

	public Ladrillos(Stage stage) {
		super(stage);
		// TODO Auto-generated constructor stub
	}

	public void collision(Objetos a) {
		if(a instanceof Pelota) {
			isTouched = true;
		}
	}
}
