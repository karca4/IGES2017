package Interface;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class Gestione extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JFrame Messaggio;
	//private WindowEvent close;
	
	// Per gli statement
	private Connection conn=null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private String stat;
	//---

	// Operazione 1
	private JLabel lbTesseraUt;
	private JTextField tfPrestitiUt;
	private JButton btPrestitiUt;
	private GridBagConstraints c;
	private ButtonListener btListener;
	
	//---
	private JSeparator separator;
	private JSeparator separator1;
	private JSeparator separator2;
	private JSeparator separator3;
	private JSeparator separator4;
	private JSeparator separator5;
	private JSeparator separator6;
	//---
	
	// Operazione 2
	private JRadioButton rbLibro;
	private JRadioButton rbManuale;
	private JRadioButton rbPeriodico;
	private JRadioButton rbDizionario;
	private ButtonGroup btgrouprb;
	private JButton btAggiungi;
	//Aggiunta utente
	private JRadioButton rbUtente;
	// Aggiunta Autore
	private JRadioButton rbAutore;
	
	// Operazione 6
	private JLabel lbAutore;
	private JTextField tfAutore;
	private JButton btAutore;
	
	// Operazione 8
	private JLabel lbCodCli;
	private JTextField tfCodCli;
	private JLabel lbCodVol;
	private JTextField tfCodVol;
	private JLabel lbScaffale;
	private JTextField tfScaffale;
	private JLabel lbPos;
	private JTextField tfPos;
	private JButton btPrestito;
	
	// Operazione 9
	private JLabel lbCodCopia;
	private JTextField tfCodCopia;
	private JButton btRestituisci;
	
	// Aggiunta volume in collana
	private JLabel lbCodVolCollana;
	private JLabel lbNomeCollana2;
	private JLabel lbNOrdineCollana;
	private JTextField tfCodVolCollana;
	private JTextField tfNomeCollana2;
	private JTextField tfNOrdineCollana;
	private JButton btAggiungiVolumeCollana;
	
	// Creazione Collana
	private JLabel lbNomeCollana;
	private JTextField tfNomeCollana;
	private JButton btCreaCollana;
	
	// Creazione Casa editrice
	private JLabel lbDenominazione;
	private JLabel lbCitta;
	private JTextField tfDenominazione;
	private JTextField tfCitta;
	private JButton btCreaCasaEditrice;
	
	//Aggiunta copia
	private JButton btAddCopia;
	
	//Controllo prestiti
	private JButton btControllaPrestiti;
	
	public Gestione() {
		this.setLayout(new GridBagLayout());
		initComponent();
		createPanel();
	}
	
	private void initComponent() {
		connection();
		btListener = new ButtonListener();
		
		lbTesseraUt = new JLabel("Codice Tessera Utente:");
		tfPrestitiUt = new JTextField();
		btPrestitiUt = new JButton("Cerca prestiti");
		btPrestitiUt.addActionListener(btListener);
		
		separator = new JSeparator(JSeparator.HORIZONTAL);
		separator1 = new JSeparator(JSeparator.HORIZONTAL);
		separator2 = new JSeparator(JSeparator.HORIZONTAL);
		separator3 = new JSeparator(JSeparator.HORIZONTAL);
		separator4 = new JSeparator(JSeparator.HORIZONTAL);
		separator5 = new JSeparator(JSeparator.HORIZONTAL);
		separator6 = new JSeparator(JSeparator.HORIZONTAL);
		
		rbLibro = new JRadioButton("Libro");
		rbLibro.setSelected(true);
		rbManuale = new JRadioButton("Manuale");
		rbPeriodico = new JRadioButton("Periodico");
		rbDizionario = new JRadioButton("Dizionario");
		rbUtente = new JRadioButton("Utente");
		rbAutore = new JRadioButton("Autore");
		btgrouprb = new ButtonGroup();
		btgrouprb.add(rbLibro);
		btgrouprb.add(rbManuale);
		btgrouprb.add(rbPeriodico);
		btgrouprb.add(rbDizionario);
		btgrouprb.add(rbUtente);
		btgrouprb.add(rbAutore);
		btAggiungi = new JButton("Aggiungi");
		btAggiungi.addActionListener(btListener);
		
		lbAutore = new JLabel("Codice Fiscale Autore:");
		tfAutore = new JTextField();
		btAutore = new JButton("Cancella autore");
		btAutore.addActionListener(btListener);
		
		lbCodCli = new JLabel("Codice Tessera Cliente:");
		tfCodCli = new JTextField();
		lbCodVol = new JLabel("Codice volume:");
		tfCodVol = new JTextField();
		lbScaffale = new JLabel("Scaffale:");
		tfScaffale = new JTextField();
		lbPos = new JLabel("Posizione:");
		tfPos = new JTextField();
		btPrestito = new JButton("Effettua prestito");
		btPrestito.addActionListener(btListener);
		
		lbCodCopia = new JLabel("Codice copia:");
		tfCodCopia = new JTextField();
		btRestituisci = new JButton("Restituisci");
		btRestituisci.addActionListener(btListener);
		
		lbCodVolCollana = new JLabel("Codice volume:");
		lbNomeCollana2 = new JLabel("Nome collana:");
		lbNOrdineCollana = new JLabel("# ordine:");
		tfCodVolCollana = new JTextField();
		tfNomeCollana2 = new JTextField();
		tfNOrdineCollana = new JTextField();
		btAggiungiVolumeCollana = new JButton("Aggiungi volume a collana");
		btAggiungiVolumeCollana.addActionListener(btListener);
		
		lbNomeCollana = new JLabel("Nome collana:");
		tfNomeCollana = new JTextField();
		btCreaCollana = new JButton("Crea collana");
		btCreaCollana.addActionListener(btListener);
		
		lbDenominazione = new JLabel("Denominazione:");
		lbCitta = new JLabel("Citta':");
		tfDenominazione = new JTextField();
		tfCitta = new JTextField();
		btCreaCasaEditrice = new JButton("Crea casa editrice");
		btCreaCasaEditrice.addActionListener(btListener);
		
		btAddCopia = new JButton("Aggiungi copia");
		btAddCopia.addActionListener(btListener);
		
		btControllaPrestiti = new JButton("Controlla prestiti");
		btControllaPrestiti.addActionListener(btListener);
	}
	
	
	private void createPanel() {
		c=new GridBagConstraints();
		c.insets = new Insets(0, 0, 0, 0);
		c.fill=GridBagConstraints.HORIZONTAL;
//		c.gridx=0;
//		c.gridy=0;
//		
//		c.weightx = 0.3;
//		c.gridx = 1;
//		c.insets = new Insets(0, 5, 0, 0);
//		add(tfPrestitiUt, c);
////		c.insets = new Insets(0, 5, 0, 5);
//		c.weightx = 0.1;
//		c.gridx = 2;
//		add(btPrestitiUt,c);
//		
//		c.insets = new Insets(1, 0, 1, 0);
//		c.gridy = 1;
//		c.gridx = 0;
//		c.gridwidth = 9;
//		add(separator, c);
		
		c.insets = new Insets(0, 0, 0, 0);
		c.gridwidth = 1;
		c.gridy = 0;
		c.gridx = 0;
		add(rbLibro, c);
		c.gridx = 1;
		add(rbManuale,c);
		c.gridx = 2;
		add(rbPeriodico, c);
		c.gridx = 3;
		add(rbDizionario);
		
		c.gridx = 4;
		add(rbUtente);
		c.gridx = 5;
		add(rbAutore,c);
		c.gridx = 6;
		add(btAggiungi, c);
		c.gridx = 7;
		add(btAddCopia, c);
		c.gridx = 8;
		add(btControllaPrestiti, c);
		
		c.insets = new Insets(1, 0, 1, 0);
		c.gridy = 2;
		c.gridx = 0;
		c.gridwidth = 9;
		add(separator1, c);
		
		c.insets = new Insets(0, 0, 0, 0);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0.1;
		add(lbAutore, c);
		
		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 1;
		add(tfAutore, c);
		
//		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 2;
		add(btAutore, c);
		
		c.insets = new Insets(1, 0, 1, 0);
		c.gridy = 4;
		c.gridx = 0;
		c.gridwidth = 9;
		add(separator2, c);
		
		c.insets = new Insets(0, 0, 0, 0);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 5;
		add(lbCodCli, c);
		
		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 1;
		add(tfCodCli,c);
		
		c.insets = new Insets(0, 50, 0, 0);
		c.gridx = 2;
		add(lbCodVol,c);
		
		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 3;
		add(tfCodVol,c);
		
		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 4;
		add(lbScaffale, c);
		
		c.gridx = 5;
		add(tfScaffale, c);
		
		c.gridx = 6;
		add(lbPos,c);
		
		c.gridx = 7;
		add(tfPos, c);
		
		c.gridx = 8;
		add(btPrestito, c);
		
		c.insets = new Insets(1, 0, 1, 0);
		c.gridy = 6;
		c.gridx = 0;
		c.gridwidth = 9;
		add(separator3, c);
		
		c.insets = new Insets(0, 0, 0, 0);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 7;
		add(lbCodCopia, c);
		
		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 1;
		add(tfCodCopia, c);
		
		c.gridx = 2;
		add(btRestituisci, c);
		
		c.insets = new Insets(1, 0, 1, 0);
		c.gridy = 8;
		c.gridx = 0;
		c.gridwidth = 9;
		add(separator4, c);
		
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 9;
		add(lbCodVolCollana, c);
		
		c.gridx = 1;
		add(tfCodVolCollana, c);
		
		c.insets = new Insets(0, 50, 0, 0);
		c.gridx = 2;
		add(lbNomeCollana2, c);
		
		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 3;
		add(tfNomeCollana2, c);
		
		c.insets = new Insets(0, 50, 0, 0);
		c.gridx = 4;
		add(lbNOrdineCollana, c);
		
		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 5;
		add(tfNOrdineCollana, c);
		
		c.gridx = 6;
		add(btAggiungiVolumeCollana, c);
		
		c.insets = new Insets(1, 0, 1, 0);
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 9;
		add(separator5, c);
		
		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 11;
		c.gridwidth = 1;
		add(lbNomeCollana, c);
		
		c.gridx = 1;
		add(tfNomeCollana, c);
		
		c.gridx = 2;
		add(btCreaCollana, c);
	
		c.insets = new Insets(1, 0, 1, 0);
		c.gridx = 0;
		c.gridy = 12;
		c.gridwidth = 9;
		add(separator6, c);
		
		c.insets = new Insets(0, 0, 0, 0);
		c.gridwidth = 1;
		c.gridy = 13;
		c.gridx = 0;
		add(lbDenominazione, c);
		
		c.gridx = 1;
		add(tfDenominazione, c);
		
		c.insets = new Insets(0, 50, 0, 0);
		c.gridx = 2;
		add(lbCitta, c);
		
		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 3;
		add(tfCitta, c);
		
		c.gridx = 4;
		add(btCreaCasaEditrice, c);
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton bottone = (JButton)e.getSource();
			if(bottone.equals(btPrestitiUt)){
//				stat ="Select volume.* from volume,autore,publicazione where autore = ? ";
				try {
					preparedStatement=(PreparedStatement)conn.prepareStatement("call VisualizzaUtenti(?)");
					preparedStatement.setString(1, tfPrestitiUt.getText());
					preparedStatement.execute();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}else if(bottone.equals(btAggiungi)){
				if(rbLibro.isSelected()) new AggiungiLibro();
				else if(rbManuale.isSelected()) new AggiungiManuale();
				else if(rbPeriodico.isSelected()) new AggiungiPeriodico();
				else if(rbDizionario.isSelected()) new AggiungiDizionario();
				else if(rbUtente.isSelected()) new AggiungiUtente();
				else if(rbAutore.isSelected()) new AggiungiAutore();
								
			}else if(bottone.equals(btAddCopia)) new AggiungiCopia();
			else if(bottone.equals(btControllaPrestiti)) new ControllaPrestiti();
			else if(bottone.equals(btAutore)){
				try {
					
					preparedStatement=(PreparedStatement)conn.prepareStatement("call cancellaautore(?)");
					preparedStatement.setString(1, tfAutore.getText());
					preparedStatement.execute();
					Messaggio = new FrameError("Cancellato Correttamente!");
					tfAutore.setText("");
					//dispatchEvent(close);
				} catch (SQLException e1) {
					Messaggio = new FrameError("Impossibile cancellare autore!");
					//e1.printStackTrace();
				}
			}else if(bottone.equals(btPrestito)){
				try {
					preparedStatement=(PreparedStatement)conn.prepareStatement("call prestito(?,?,?,?)");
					preparedStatement.setString(1, tfCodVol.getText());
					preparedStatement.setString(2, tfScaffale.getText());
					preparedStatement.setString(3, tfPos.getText());
					preparedStatement.setString(4, tfCodCli.getText());
					preparedStatement.execute();
					Messaggio = new FrameError("Prestito effettuato!");
					
				} catch (SQLException e1) {
					Messaggio = new FrameError("Prestito non effettuabile!");
					//e1.printStackTrace();
				}
			}else if(bottone.equals(btRestituisci)){
				try {
				preparedStatement=(PreparedStatement)conn.prepareStatement("call restituzione(?)");
				preparedStatement.setString(1, tfCodCopia.getText());
				preparedStatement.execute();
				Messaggio = new FrameError("Restituito Correttamente!");
				}catch(SQLException e1) {
					Messaggio = new FrameError("Restituzione non effettuata!");
					//e1.printStackTrace();
				}
			}else if(bottone.equals(btAggiungiVolumeCollana)){
				try {
					preparedStatement=(PreparedStatement)conn.prepareStatement("call AppartenenzaCollana(?,?,?)");
					preparedStatement.setString(1, tfCodVolCollana.getText());
					preparedStatement.setString(2, tfNomeCollana2.getText());
					preparedStatement.setString(3, tfNOrdineCollana.getText());
					preparedStatement.execute();
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
			}else if(bottone.equals(btCreaCollana)){
				try {
					preparedStatement=(PreparedStatement)conn.prepareStatement("call creaCollana(?)");
					preparedStatement.setString(1, tfNomeCollana.getText());
					preparedStatement.execute();
					Messaggio = new FrameError("Collana Creata Correttamente!");
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
			}else if(bottone.equals(btCreaCasaEditrice)){
				try {
					preparedStatement=(PreparedStatement)conn.prepareStatement("call creaCasaEditrice(?,?)");
					preparedStatement.setString(1, tfDenominazione.getText());
					preparedStatement.setString(2, tfCitta.getText());
					preparedStatement.execute();
					Messaggio = new FrameError("Casa Editrice Creata Correttamente!");
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
			}
		}
	}
	
private void connection(){
		try{
			 try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			 } catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
			System.exit(-1);
			
		}
		try {
			conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root","");
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.exit(-2);
		}
		
	}
	
}
	

