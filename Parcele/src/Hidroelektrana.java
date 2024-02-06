import java.awt.Color;

public class Hidroelektrana extends Proizvodjac{
	private int brojOkruzujucih;
	public Hidroelektrana(Baterija baterija) {
		super("H", Color.BLUE, 1500, baterija);
	}
	
	@Override
	public boolean proizvedi() {
		if(brojOkruzujucih >= 1) {
			this.baterija.dopuni(1);
			return true;
		} else return false;
	}
	public void setOkruzujuce(int o) {
		brojOkruzujucih = o;
	}
	
}
