import java.awt.Color;

public class Vodena extends Parcela{

	public Vodena() {
		super();
		this.boja = Color.CYAN;
		this.oznaka = "~";
		this.setText(oznaka);
		this.setBackground(this.boja);
	}

}
