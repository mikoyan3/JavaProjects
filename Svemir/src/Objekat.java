import java.awt.Color;
import java.awt.Graphics;

public abstract class Objekat {
	private int x, y;
	private Color boja;
	
	public Objekat(int x, int y, Color boja) {
		this.x = x;
		this.y = y;
		this.boja = boja;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Color getBoja() {
		return boja;
	}
	public void pomeriX(int pomeraj) {
		x += pomeraj;
	}
	public void pomeriY(int pomeraj) {
		y += pomeraj;
	}
	public abstract void paint(Graphics g);
}
