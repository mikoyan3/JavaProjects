import java.awt.Color;

public abstract class Proizvodjac extends Parcela implements Runnable{
	private int osnovnoVreme, ukupnoVreme;
	protected Baterija baterija;
	private Thread nit = null;
	public Proizvodjac(String oznaka, Color boja, int osnovnoVreme, Baterija baterija) {
		super();
		this.oznaka = oznaka;
		this.boja = boja;
		this.baterija = baterija;
		this.setText(this.oznaka);
		this.setBackground(this.boja);
		ukupnoVreme = osnovnoVreme + (int)(Math.random()*300);
		nit = new Thread(this);
		nit.start();
	}
	public abstract boolean proizvedi();
	@Override
	public void run() {
		try {
			while(!nit.interrupted()) {
				Thread.sleep(ukupnoVreme);
				boolean uspeh = proizvedi();
				if(uspeh == true) {
					this.setForeground(Color.RED);
					this.revalidate();
				}
				Thread.sleep(300);
				this.setForeground(Color.WHITE);
				this.revalidate();
			}
		} catch(InterruptedException e) {}
	}
	public void zavrsi() {
		nit.interrupt();
		nit = null;
	}
}
