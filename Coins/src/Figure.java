import java.awt.Graphics;

public abstract class Figure {
	protected int x, y, width; 
	
	public Figure(int x, int y, int width) {
		this.x = x;
		this.width = width;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof Figure)) return false;
		Figure f = (Figure)obj;
		return this.x == f.getX() && this.y == f.getY();
	}
	
	public abstract void paint(Graphics g);
	
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	
}
