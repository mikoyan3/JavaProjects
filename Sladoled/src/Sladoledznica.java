import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Sladoledznica extends Frame{
	private AparatZaTocenje aparat = new AparatZaTocenje();
	private Panel dodavanjeUkusa = new Panel(new FlowLayout(FlowLayout.LEFT, 0, 0));
	private Label labelaNaziv = new Label("Naziv: ");
	private Label labelaBoja = new Label("Boja: ");
	private TextField poljeNaziv = new TextField(20);
	private TextField poljeBoja = new TextField(20);
	private Button dodajUkus = new Button("Dodaj ukus");
	
	public Sladoledznica() {
		setTitle("Sladoleddznica");
		Dimension dimenzijeEkrana = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((dimenzijeEkrana.width - 500) / 2, (dimenzijeEkrana.height - 500) / 2, 500, 500);
		setResizable(false);
		setVisible(true);
		setLayout(new BorderLayout());
		aparat.populate();
		dodavanjeUkusa.setBackground(Color.CYAN);
		add(aparat, BorderLayout.CENTER);
		dodavanjeUkusa.add(labelaNaziv); dodavanjeUkusa.add(poljeNaziv);
		dodavanjeUkusa.add(labelaBoja); dodavanjeUkusa.add(poljeBoja);
		dodavanjeUkusa.add(dodajUkus);
		dodajUkus.addActionListener((ae)->{
			try {
				Ukus noviUkus = new Ukus(poljeNaziv.getText(), Color.decode("#" + poljeBoja.getText()));
				aparat.dodajUkus(noviUkus);
			}  catch (GSadrziUkus e) {
				e.printStackTrace();
			}
		});
		add(dodavanjeUkusa, BorderLayout.SOUTH);
		resized();
		pack();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent componentEvent) {
				resized();
			}
		});
	}
	public void resized() {
		aparat.resized(getWidth(), getHeight());
	}
}
