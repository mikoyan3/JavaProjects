import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;

public class MestoZaTocenje extends Canvas implements Runnable{
	private AparatZaTocenje aparat;
	private final static int vreme = 500;
	private final static int velicinaCase = 200;
	private final static int broj_sipanja = (int)(Math.ceil(1.0*velicinaCase/20));
	private Sladoled sladoled = null;
	private Thread timer = null;
	private Ukus trenutniUkus = null;
	private ArrayList<Ukus> ukusi = new ArrayList<>();
	
	public MestoZaTocenje(AparatZaTocenje aparat) {
		this.aparat = aparat;
	}
	
	public void postUkus(Ukus u) {
		trenutniUkus = u;
	}
	
	public synchronized void go() {
		if(trenutniUkus == null) return;
		if(sladoled == null) sladoled = new Sladoled(velicinaCase);
		pause();
		timer = new Thread(this);
		timer.start();
	}
	
	public synchronized void pause() {
		if(timer!=null) timer.interrupt();
		while(timer != null) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
	}
	public void stop() {
		pause();
		System.out.println(sladoled);
		sladoled = null;
		ukusi.clear();
		repaint();
	}
	public boolean tocenjeUToku() {
		return sladoled!=null;
	}
	@Override
	public void run() {
		try {
			while(!sladoled.pun()) {
				Thread.sleep(vreme);
				sladoled.dodajUkus(trenutniUkus, 20);
				ukusi.add(trenutniUkus);
				aparat.azuriraj(sladoled);
				repaint();
			}
		} catch (InterruptedException e) {}
		if(sladoled.pun()) {
			aparat.omoguciKupovinu();
		}
		synchronized(this) {
			timer = null;
			notifyAll();
		}
	}
	public void paint(Graphics g) {
		int visina = getHeight();
		int sirina = getWidth();
		double odbirak = 1.0 * visina / broj_sipanja;
		int trenutnaKolicina = 0;
		for(Ukus u : ukusi) {
			++trenutnaKolicina;
			g.setColor(u.getBoja());
			g.fillRect(0, (int)(visina - trenutnaKolicina*odbirak), sirina, (int)odbirak);
		}
	}
}
