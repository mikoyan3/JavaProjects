import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EnergetskiSistem extends Frame{
	private static final int visina = 500;
	private static final int sirina = 500;
	private Plac plac;
	private Baterija baterija;
	private Button dodaj = new Button("Dodaj");
	public EnergetskiSistem(int redovi, int kolone, int kapacitet) {
		setTitle("Energetski sistem");
		this.setBounds(700, 200, sirina, visina);
		setResizable(false);
		this.setLayout(new BorderLayout());
		plac = new Plac(redovi, kolone);
		baterija = new Baterija(kapacitet);
		Panel dugme = new Panel();
		dugme.add(dodaj);
		dodaj.addActionListener((ae)->{
			plac.dodajProizvodjaca(baterija);
		});
		this.add(plac, BorderLayout.CENTER);
		this.add(dugme, BorderLayout.NORTH);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				plac.zaustavi();
				dispose();
				
			}
		});
		this.setVisible(true);
	}
}
