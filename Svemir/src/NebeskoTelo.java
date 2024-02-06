import java.awt.Color;
import java.awt.Graphics;

public class NebeskoTelo extends Objekat{
	protected int r;

	public NebeskoTelo(int x, int y, Color boja, int r) {
		super(x, y, boja);
		this.r = r;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(this.getBoja());
		g.fillOval(getX(), getY(), r, r);
	}
}
