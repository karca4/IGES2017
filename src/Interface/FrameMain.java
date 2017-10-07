package Interface;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.mysql.jdbc.Connection;

public class FrameMain extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTabbedPane pane;
	private JPanel panel1;
	private JPanel panel2;
	
	public FrameMain() {
		
		setTitle("Home");
		initComponent();
		pane.addTab("Gestione", panel1);
		pane.addTab("Ricerca", panel2);
		this.add(pane);
//		JFrame libro = new AggiungiLibro();
//		JFrame manuale = new AggiungiManuale();
//		JFrame periodico = new AggiungiPeriodico();
//		JFrame utente = new AggiungiUtente();
//		JFrame copia = new AggiungiCopia();
		
	}
	
	private class listener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e) {
			JTabbedPane tb=(JTabbedPane)e.getSource();
			if(tb.getSelectedIndex()==0){
				setSize(1300, 300);
			}
			else if(tb.getSelectedIndex()==1){
				setSize(800, 400);
			}	
		}
	}
	
	
	
	private void initComponent() {
		pane = new JTabbedPane();
		pane.addChangeListener(new listener());
		panel1 = new Gestione();
		panel2 = new Ricerca();
	}
		
}
