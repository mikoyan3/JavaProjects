import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Scene extends Canvas{
	private int squareWidth;
	private CollectCoins owner;
	private int rows = 10;
	private Player player;
	private ArrayList<Figure> figures = new ArrayList<>();
	private int score = 0;
	public void tossCoins(int coins) {
		owner.score.setText("");
		owner.log.setText("");
		player = null;
		figures = new ArrayList<>();
		score = 0;
		
		while(coins >= rows*rows) coins /= 4;
		int x, y, coinR = squareWidth / 2;
		for(int i = 0; i<coins; i++) {
			x = (int)(Math.random() * rows) * squareWidth + coinR;
			y = (int)(Math.random() * rows) * squareWidth + coinR;
			Coin c = new Coin(x, y, coinR);
			if(figures.contains(c)) { i--; continue;}
			figures.add(c);
 		}
		do {
			x = (int)(Math.random() * rows) * squareWidth + squareWidth / 2;
			y = (int)(Math.random() * rows) * squareWidth + squareWidth / 2;
			player = new Player(x, y, squareWidth);
		} while(figures.contains(player));
		figures.add(player);
	}
	
	public Scene(CollectCoins owner) {
		this.owner = owner;
		addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char key = Character.toUpperCase(e.getKeyChar());
				switch(key) {
				case KeyEvent.VK_W:{
					int y = player.getY() - squareWidth;
					player.setY(y < 0 ? player.getY() : y);
					break;
				}
				case KeyEvent.VK_S: {
					int y = player.getY() + squareWidth;
					player.setY(y > getHeight() ? player.getY() : y);
					break;
				}
				case KeyEvent.VK_A: {
					int x = player.getX() - squareWidth;
					player.setX(x < 0 ? player.getX() : x);
					break;
				}
				case KeyEvent.VK_D: {
					int x = player.getX() + squareWidth;
					player.setX(x > getWidth() ? player.getX() : x);
					break;
				}
				}
				repaint();
			}
		});
	}
	
	public int getRows() {
		return rows;
	}
	
	public void paint(Graphics g) {
		squareWidth = getDim() / rows;
		adjustScore();
		drawLines();
		drawFigures();
	}

	private void drawFigures() {
		if(player == null) return;
		for(Figure f: figures) {
			f.paint(getGraphics());
		}
		player.paint(getGraphics());
	}

	private void drawLines() {
		int dim = getDim();
		int step = dim / rows;
		Graphics g = this.getGraphics();
		g.setColor(Color.BLACK);
		for(int i = 0; i < dim; i+=step) {
			g.drawLine(0, i, dim-1, i);
			g.drawLine(i, 0, i, dim-1);
		}
	}

	private int getDim() {
		int height = owner.centerPanel.getHeight();
		int width = owner.centerPanel.getWidth();
		int w = width/rows*rows;
		int h = height/rows*rows;
		if(w>h) return w;
		else return h;
	}

	private void adjustScore() {
		for(Figure f: figures) {
			if(player.equals(f) && player!=f) {
				score++;
				owner.score.setText("" + score);
				owner.log.append("Coin collected at: " + owner.timer.toString() + "\n");
				figures.remove(f);
				if(figures.size() == 1) {
					owner.timer.interrupt();
					
				}
				break;
			}
		}
	}
}
