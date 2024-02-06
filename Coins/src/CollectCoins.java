import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CollectCoins extends Frame{
	private Panel bottomPanel = new Panel();
	private Label time = new Label();
	Panel centerPanel = new Panel();
	TextArea log = new TextArea();
	Label score = new Label();
	private Scene scene = new Scene(this);
	Timer timer;
	
	private void populateWindow() {
		log.setColumns(20);
		log.setRows(1);
		log.setEditable(false);
		add(log, BorderLayout.EAST);
		
		int dim = (this.getWidth()/2) / scene.getRows() * scene.getRows();
		scene.setBackground(Color.GREEN);
		scene.setPreferredSize(new Dimension(dim, dim));
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		centerPanel.add(scene);
		add(centerPanel, BorderLayout.CENTER);
		
		TextField coins = new TextField("10");
		Button toss = new Button("Toss");
		
		toss.addActionListener((ae)->{
			scene.tossCoins(Integer.parseInt(coins.getText()));
			scene.repaint();
			if(timer!=null) timer.interrupt();
			timer = new Timer(time);
			timer.start();
			timer.go();
			scene.requestFocus();
		});
		
		bottomPanel.add(new Label("Time: ")); bottomPanel.add(time);
		bottomPanel.add(new Label("Score: ")); bottomPanel.add(score);
		bottomPanel.add(new Label("Coins: ")); bottomPanel.add(coins);
		bottomPanel.add(toss); 
		
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	public CollectCoins() {
		setBounds(700, 200, 500, 300);
		setResizable(true);
		setTitle("Collect coins");
		populateWindow();
		pack();
		
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent c) {
				scene.repaint();
				pack();
			}
		});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(timer != null) {
					timer.interrupt();
				}
				dispose();
				
			}
		});
		setVisible(true);
	}
	
}
