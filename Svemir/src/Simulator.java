import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Simulator extends Frame{
	private static final int visina = 400;
	private static final int sirina = 200;
	private Svemir svemir = new Svemir();
	private Generator generator;
	private Panel komande = new Panel(new FlowLayout(FlowLayout.CENTER, 0, 0));
	private Button pokreni = new Button("Pokreni!");
	private boolean stisnut = false;
	public Simulator() {
		setTitle("Simulator");
		this.setBounds(700, 200, sirina, visina);
		setResizable(false);
		setLayout(new BorderLayout());
		generator = new Generator(svemir);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				generator.ugasi();
				dispose();
			}
		});
		this.setVisible(true);
		svemir.paint(this.getGraphics());
		this.add(svemir, BorderLayout.CENTER);
		pokreni.addActionListener((ae)->{
			generator.go();
			pokreni.setEnabled(false);
		});
		komande.add(pokreni);
		this.add(komande, BorderLayout.SOUTH);
		pack();
		/*MenuItem(addActionListener(ie->)) -> Menu -> MenuBar -> setMenuBar*/
	}
}
