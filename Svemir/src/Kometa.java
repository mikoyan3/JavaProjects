import java.awt.Color;
import java.awt.Graphics;

public class Kometa extends NebeskoTelo{
	private boolean flag = false;
	private double orijentacija;
	public Kometa(int x, int y, Color boja, int r) {
		super(x, y, Color.GRAY, r);
		orijentacija = Math.random() * 2 * Math.PI;
	}
	@Override
	public void paint(Graphics g) {
		int[] xp = new int[5];
		int[] yp = new int[5];
		double inc = 2 * Math.PI / 5;
		int i = 0;
		for(double angle = 0; angle < 2 * Math.PI; angle += inc) {
			xp[i] = getX() + (int)(Math.cos(angle + orijentacija) * r);
			yp[i] = getY() + (int)(Math.sin(angle + orijentacija) * r);
			i++;
		}
		g.setColor(this.getBoja());
		g.fillPolygon(xp, yp, 5);
	}
}
