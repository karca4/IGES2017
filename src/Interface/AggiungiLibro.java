package Interface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.SimpleFormatter;

import javax.print.SimpleDoc;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout.Constraints;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class AggiungiLibro extends JFrame{
	
	private JLabel lbCodice;
	private JLabel lbTitolo;
	private JLabel lbEdizione;
	private JLabel lbDataP;
	private JLabel lbDurataMaxPre;
	private JLabel lbLingua;
	private JLabel lbDenominazioneEditore;
	private JLabel lbCittaEditore;
	private JLabel lbGenere;
	private JLabel lbTipo;
	private JLabel lbNomeA;
	private JLabel lbCognomeA;
	
	private JTextField tfNomeA;
	private JTextField tfCognomeA;
	private JTextField tfCodice;
	private JTextField tfTitolo;
	private JTextField tfEdizione;
	private JTextField tfDataPubblicazione;
	private JTextField tfDurataMaxPrestito;
	private JTextField tfLingua;
	private JTextField tfDenominazioneEditore;
	private JTextField tfCittaEditore;
	private JTextField tfGenere;
	private JComboBox<String> cbTipo;
	private GridBagConstraints c;
	private JButton btConferma;
	private JButton btAnnulla;
	private ActionListener btListener;
	private WindowEvent close;
	private JFrame Messaggio;
	private PreparedStatement preparedStatement = null;
	private Connection conn=null;
	private String stat="Call inserisciLibro(?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private class ListenerButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton bt=(JButton)e.getSource();
			if(bt.equals(btConferma)){
				
				if((tfGenere.getText().isEmpty())||(tfCittaEditore.getText().isEmpty())
						||(tfCodice.getText().isEmpty())||(tfCognomeA.getText().isEmpty())||
						(tfDataPubblicazione.getText().isEmpty()) || (tfDenominazioneEditore.getText().isEmpty()) ||
						(tfDurataMaxPrestito.getText().isEmpty()) ||
						(tfEdizione.getText().isEmpty()) ||
						(tfLingua.getText().isEmpty()) ||
						(tfNomeA.getText().isEmpty()) ||
						(tfTitolo.getText().isEmpty()) ){
					Messaggio = new FrameError("Devi riempire tutti i campi!");
					dispatchEvent(close);
				}else{
				
				try{
					preparedStatement=(PreparedStatement)conn.prepareStatement(stat);
					preparedStatement.setString(1, tfCodice.getText());
					preparedStatement.setString(2, tfTitolo.getText());
					preparedStatement.setInt(3,Integer.parseInt(tfEdizione.getText()));
					preparedStatement.setString(4, tfDataPubblicazione.getText());
					preparedStatement.setInt(5, Integer.parseInt(tfDurataMaxPrestito.getText()));
					preparedStatement.setString(6, tfLingua.getText());
					preparedStatement.setString(7, tfDenominazioneEditore.getText());
					preparedStatement.setString(8, tfCittaEditore.getText());
					preparedStatement.setString(9, tfGenere.getText());
					preparedStatement.setString(10,(String)cbTipo.getSelectedItem());
					preparedStatement.setString(11, tfNomeA.getText());
					preparedStatement.setString(12, tfCognomeA.getText());
					System.out.println(preparedStatement.execute());
					Messaggio = new FrameError("Inserimento Corretto!");
					dispatchEvent(close);
				}
				catch(SQLException e1){
					System.out.println(e1);
				}
			}
			}
			else if(bt.equals(btAnnulla)){
				dispatchEvent(close);
				Messaggio = new FrameError("Annullato!");
				
				
				
			}
		}
	}
	
	
	public AggiungiLibro() {
		setTitle("Aggiungi libro");
		setSize(500,500);
		setLayout(new GridBagLayout());
		close=new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		connection();
		initComponent();
		createPanel();
		setVisible(true);
	}
	
	private void initComponent(){
		
		lbCodice = new JLabel("Codice:");
		lbTitolo = new JLabel("Titolo:");
		lbEdizione = new JLabel("Edizione:");
		lbDataP = new JLabel("Data Pubblicazione:");
		lbDurataMaxPre = new JLabel("Durata Massima Prestito:");
		lbLingua = new JLabel("Lingua:");
		lbDenominazioneEditore = new JLabel("Denominazione Editore:");
		lbCittaEditore = new JLabel("Citta'� Editore:");
		lbGenere = new JLabel("Genere:");
		lbTipo = new JLabel("Tipo:");
		lbNomeA= new JLabel("Nome Autore:");
		lbCognomeA= new JLabel("Cognome Autore:");
		
		tfNomeA= new JTextField();
		tfCognomeA= new JTextField();
		tfCodice = new JTextField();
		tfTitolo = new JTextField();
		tfEdizione = new JTextField();
		tfDataPubblicazione = new JTextField();
		tfDurataMaxPrestito = new JTextField();
		tfLingua = new JTextField();
		tfDenominazioneEditore = new JTextField();
		tfCittaEditore = new JTextField();
		cbTipo = new JComboBox<String>();
		cbTipo.addItem("politico");
		cbTipo.addItem("scientifico");
		cbTipo.addItem("letterario");
		cbTipo.addItem("economico");
		tfGenere = new JTextField();
		
		btListener=new ListenerButton();
		
		btConferma = new JButton("Conferma");
		btConferma.addActionListener(btListener);
		btAnnulla = new JButton("Annulla");
		btAnnulla.addActionListener(btListener);
	}
	
	
	private void createPanel(){
		c=new GridBagConstraints();
		c.weightx=0.5;
		c.weighty=0.2;
		
		c.insets=new Insets(0, 30, 0, 0);
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		add(lbCodice,c);
		
		c.gridx = 1;
		add(tfCodice, c);
		
		c.gridx = 0;
		c.gridy = 1;
		add(lbTitolo, c);
		
		c.gridx = 1;
		add(tfTitolo, c);
		
		c.gridx = 0;
		c.gridy = 2;
		add(lbEdizione, c);
		
		c.gridx = 1;
		add(tfEdizione, c);
		
		c.gridx = 0;
		c.gridy = 3;
		add(lbDataP, c);
		
		c.gridx = 1;
		add(tfDataPubblicazione, c);
		
		c.gridx = 0;
		c.gridy = 4;
		add(lbDurataMaxPre, c);
		
		c.gridx = 1;
		add(tfDurataMaxPrestito, c);
		
		c.gridx = 0;
		c.gridy = 5;
		add(lbLingua, c);
		
		c.gridx = 1;
		add(tfLingua, c);
		
		c.gridx = 0;
		c.gridy = 6;
		add(lbDenominazioneEditore, c);
		
		c.gridx = 1;
		add(tfDenominazioneEditore, c);
		
		c.gridx = 0;
		c.gridy = 7;
		add(lbCittaEditore, c);
		
		c.gridx = 1;
		add(tfCittaEditore, c);
		
		c.gridx = 0;
		c.gridy = 8;
		add(lbGenere, c);
		
		c.gridx = 1;
		add(tfGenere, c);
		
		c.gridx = 0;
		c.gridy = 9;
		add(lbTipo, c);
		
		c.gridx = 1;
		add(cbTipo, c);
		
		c.gridx = 0;
		c.gridy = 10;
		add(lbNomeA, c);
		
		c.gridx = 1;
		add(tfNomeA, c);
		
		c.gridx = 0;
		c.gridy = 11;
		add(lbCognomeA, c);
		
		c.gridx = 1;
		add(tfCognomeA, c);
		
		c.gridx = 0;
		c.gridy = 12;
		add(btConferma, c);
		
		c.gridx = 1;
		add(btAnnulla, c);
	}
	
	private void connection(){
		
		try{
			 try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}catch(ClassNotFoundException ex){
			System.exit(-1);
		}
		try {
			conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Biblioteca","root","");
		} catch (SQLException ex) {
			System.exit(-2);
		}
		

	}
	
	
	
	
}