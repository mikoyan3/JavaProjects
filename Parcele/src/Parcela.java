import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Parcela extends Label{
	protected String oznaka;
	protected Color boja;
	
	public Parcela() {
		this.setFont(new Font("Serif", Font.BOLD, 14));
		this.setForeground(Color.WHITE);
		this.setAlignment(CENTER);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Plac plac = (Plac)(getParent());
				plac.izaberiParcelu(Parcela.this);
			}
		});
	}
	public void changeColor(Color c) {
		boja = c;
	}
}
