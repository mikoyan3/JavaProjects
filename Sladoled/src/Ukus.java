import java.awt.Color;

public class Ukus {
	private String naziv;
	private Color boja;
	public String getNaziv() {
		return naziv;
	}
	public Color getBoja() {
		return boja;
	}
	
	public Ukus(String naziv, Color boja) {
		this.naziv = naziv;
		this.boja = boja;
	}
	
	public boolean equals(Ukus u) {
		return naziv.equals(u.naziv);
	}
	public String toString() {
		return "[" + naziv + "]";
	}
}
