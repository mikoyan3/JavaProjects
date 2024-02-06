import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

public class Svemir extends Canvas implements Runnable{
	private static final Color pozadina = Color.BLACK;
	private ArrayList<NebeskoTelo> nebeskaTela = new ArrayList<>();
	private static final int vreme = 100;
	private static final int pomeraj = 5;
	private Thread nit = null;
	private boolean pokrenut = false;
	public Svemir() {
		this.setBackground(pozadina);
		this.setPreferredSize(new Dimension(200, 340));
	}
	public synchronized void dodajTelo(NebeskoTelo n) {
		nebeskaTela.add(n);
		repaint();
	}
	public void go() {
		nit = new Thread(this);
		pokrenut = true;
		nit.start();
	}
	
	public void ugasi() {
		nit = null;
		nebeskaTela.clear();
		pokrenut = false;
		repaint();
	}
	public void pomeri() {
		for(int i = 0; i<nebeskaTela.size(); i++) {
				nebeskaTela.get(i).pomeriY(pomeraj);
		}
	}
	@Override
	public void run() {
		try {
			while(pokrenut) {
				Thread.sleep(vreme);
				repaint();
				this.pomeri();
			}
		} catch(InterruptedException e) {}
		synchronized(this) {
			nit = null;
			notifyAll();
		}
	}
	@Override 
	public void paint(Graphics g) {
		for(int i = 0; i<nebeskaTela.size(); i++) {
			nebeskaTela.get(i).paint(g);
		}
	}
}
