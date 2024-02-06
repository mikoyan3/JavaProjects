import java.util.ArrayList;

public class Sladoled {
	private static class Ukusi{
		Ukus u;
		int kolicina;
		Ukusi(Ukus u, int kolicina){
			this.u = u;
			this.kolicina = kolicina;
		}
	}
	private ArrayList<Ukusi> ukusi = new ArrayList<>();
	private int velicinaCase;
	private int trenutnaKolicina;
	
	public Sladoled(int velicinaCase) {
		this.velicinaCase = velicinaCase;
	}
	public int getUkus(String naziv) {
		for(int i = 0; i<ukusi.size(); i++) {
			if(ukusi.get(i).u.getNaziv().equals(naziv)) return i;
		}
		return -1;
	}
	public void dodajUkus(Ukus u, int kolicina) {
		if(trenutnaKolicina == velicinaCase) return;
		if(trenutnaKolicina + kolicina > velicinaCase) {
			kolicina = velicinaCase - trenutnaKolicina;
		}
		Ukusi novi = new Ukusi(u, kolicina);
		trenutnaKolicina += kolicina;
		int flag = getUkus(u.getNaziv());
		if(flag>=0) {
			ukusi.get(flag).kolicina+=kolicina;
		} else ukusi.add(novi);
	}
	public boolean pun() {
		return trenutnaKolicina == velicinaCase;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<ukusi.size(); i++) {
			sb.append(ukusi.get(i).kolicina).append("ml").append(ukusi.get(i).u);
			sb.append(" ");
		}
		return sb.toString();
	}
	
}
