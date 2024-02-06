import java.awt.Label;

public class Timer extends Thread{
	private Label label;
	private int s, m;
	private boolean work; 
	
	public Timer(Label l) {
		this.label = l;
		work = false;
	}
	
	public void run() {
		try {
			while(!isInterrupted()) {
				synchronized(this) {
					while(!work) wait();
				}
				label.setText(toString());
				label.revalidate();
				sleep(1000);
				s++;
				if(s % 60 == 0) {
					m++;
					s = 0;
				}
			}
		} catch(InterruptedException e) {}
	}
	
	public synchronized void go() {
		work = true;
		notifyAll();
	}
	public synchronized void pause() {
		work = false;
		notifyAll();
	}
	public synchronized void reset() {
		m = s = 0;
	}
	public String toString() {
		return String.format("%02d:%02d" , m, s);
	}
}
