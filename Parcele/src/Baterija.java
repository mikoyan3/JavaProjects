
public class Baterija {
	private int trenutnaEnergija, maksimalniKapacitet;
	public Baterija(int max) {
		trenutnaEnergija = maksimalniKapacitet = max;
	}
	public synchronized void dopuni(int dopuna) {
		trenutnaEnergija+=dopuna;
		if(trenutnaEnergija > maksimalniKapacitet) trenutnaEnergija = maksimalniKapacitet;
	}
	public synchronized void isprazni() {
		trenutnaEnergija = 0;
	}
	public synchronized boolean puna() {
		return trenutnaEnergija == maksimalniKapacitet;
	}
}
