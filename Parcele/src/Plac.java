import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Plac extends Panel{
	private int redovi, kolone;
	private Parcela aktivna;
	private Parcela[][] parcele;
	private ArrayList<Hidroelektrana> h = new ArrayList<>();
	public Plac(int redovi, int kolone) {
		super(new GridLayout(redovi, kolone, 2, 2));
		this.redovi = redovi;
		this.kolone = kolone;
		parcele = new Parcela[redovi][kolone];
		int nasumicno;
		for(int i = 0; i < redovi; i++) {
			for(int j = 0; j < kolone; j++) {
				nasumicno = (int)(Math.random() * 10);
				if(nasumicno < 7) {
					Travnata t = new Travnata();
					parcele[i][j] = t;
					this.add(t);
				} else {
					Vodena v = new Vodena();
					parcele[i][j] = v;
					this.add(v);
				}
			}
		}
	}
	public synchronized void izaberiParcelu(Parcela p) {
		if(!(p instanceof Parcela)) return;
		p.setFont(new Font("Serif", Font.BOLD, 20));
		if(aktivna == null) aktivna = p;
		else {
			aktivna.setFont(new Font("Serif", Font.BOLD, 14));
			aktivna = p;
		}
		revalidate();
	}
	public void dodajProizvodjaca(Baterija b) {
		if(aktivna == null) return;
		Hidroelektrana h = new Hidroelektrana(b);
		int i = 0;
		int j = 0;
		while(parcele[i][j] != aktivna) {
			j++;
			if(j == kolone) {
				j = 0; i++;
			}
		}
		if(aktivna instanceof Hidroelektrana) return;
		int pozicija = i*kolone + j;
		this.remove(pozicija);
		this.add(h, pozicija);
		revalidate();
		this.h.add(h);
		parcele[i][j] = h;
		aktivna = null;
		azuriraj();
	}
	public void azuriraj() {
		for(int i = 0; i < redovi; i++) {
			for(int j = 0; j < kolone; j++) {
				if(parcele[i][j] instanceof Hidroelektrana) {
					int k = 0;
					int o = 0;
					while(parcele[i][j] != h.get(k)) k++;
					if(i == 0) {
						if(j == 0) {
							if(parcele[0][1] instanceof Vodena) o++;
							if(parcele[1][0] instanceof Vodena) o++;
							if(parcele[1][1] instanceof Vodena) o++;
							h.get(k).setOkruzujuce(o);
						} else if(j == kolone - 1) {
							if(parcele[0][j-1] instanceof Vodena) o++;
							if(parcele[1][j] instanceof Vodena) o++;
							if(parcele[1][j-1]instanceof Vodena) o++;
							h.get(k).setOkruzujuce(o);
						} else {
							if(parcele[0][j-1] instanceof Vodena) o++;
							if(parcele[0][j+1] instanceof Vodena) o++;
							if(parcele[1][j] instanceof Vodena) o++;
							if(parcele[1][j-1] instanceof Vodena) o++;
							if(parcele[1][j+1] instanceof Vodena) o++;
							h.get(k).setOkruzujuce(o);
						}
					} else if(i == redovi - 1) {
						if(j == 0) {
							if(parcele[i-1][1] instanceof Vodena) o++;
							if(parcele[i-1][0] instanceof Vodena) o++;
							if(parcele[i][1] instanceof Vodena) o++;
							h.get(k).setOkruzujuce(o);
						} else if(j == kolone - 1) {
							if(parcele[i][j-1] instanceof Vodena) o++;
							if(parcele[i-1][j] instanceof Vodena) o++;
							if(parcele[i-1][j-1]instanceof Vodena) o++;
							h.get(k).setOkruzujuce(o);
						} else {
							if(parcele[i][j-1] instanceof Vodena) o++;
							if(parcele[i][j+1] instanceof Vodena) o++;
							if(parcele[i- 1][j] instanceof Vodena) o++;
							if(parcele[i-1][j-1] instanceof Vodena) o++;
							if(parcele[i-1][j+1] instanceof Vodena) o++;
							h.get(k).setOkruzujuce(o);
						}
					} else {
						if(j == 0) {
							if(parcele[i-1][j] instanceof Vodena) o++;
							if(parcele[i+1][j] instanceof Vodena) o++;
							if(parcele[i][j+1] instanceof Vodena) o++;
							if(parcele[i-1][j+1] instanceof Vodena) o++;
							if(parcele[i+1][j+1] instanceof Vodena) o++;
							h.get(k).setOkruzujuce(o);
						} else if(j == kolone - 1) {
							if(parcele[i-1][j] instanceof Vodena) o++;
							if(parcele[i+1][j] instanceof Vodena) o++;
							if(parcele[i][j-1] instanceof Vodena) o++;
							if(parcele[i-1][j-1] instanceof Vodena) o++;
							if(parcele[i+1][j-1] instanceof Vodena) o++;
							h.get(k).setOkruzujuce(o);
						} else {
							if(parcele[i][j+1] instanceof Vodena) o++;
							if(parcele[i][j-1] instanceof Vodena) o++;
							if(parcele[i+1][j] instanceof Vodena) o++;
							if(parcele[i-1][j] instanceof Vodena) o++;
							if(parcele[i-1][j-1] instanceof Vodena) o++;
							if(parcele[i-1][j+1] instanceof Vodena) o++;
							if(parcele[i+1][j-1] instanceof Vodena) o++;
							if(parcele[i+1][j+1] instanceof Vodena) o++;
							h.get(k).setOkruzujuce(o);
						}
					}
				}
			}
		}
	}
	public void zaustavi() {
		for(int i = 0; i < h.size(); i++) {
			h.get(i).zavrsi();
		}
	}
}
