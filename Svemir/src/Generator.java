import java.awt.Color;
import java.util.Random;

public class Generator extends Thread{
	private Svemir svemir;
	private static final int vreme = 900;
	private boolean pokrenut = false;
	public Generator(Svemir svemir) {
		this.svemir = svemir;
	}
	public synchronized void go() {
		pokrenut = true;
		this.start();
		svemir.go();
	}
	public void ugasi() {
		svemir.ugasi();
		pokrenut = false;
		interrupt();
		
	}
	public void dodaj() {
		int x = (int)(Math.random() * 200);
		Random gen = new Random();
		int r = gen.nextInt(20) + 10;
		Kometa k = new Kometa(x, 0, Color.RED, r);
		synchronized(svemir) { svemir.dodajTelo(k); }
	}
	@Override 
	public void run() {
		try {
			while(pokrenut) {
				Thread.sleep(vreme);
				dodaj();
			}
		} catch(InterruptedException e) {}
	}
}
