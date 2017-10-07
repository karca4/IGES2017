package Interface;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class AggiungiCopia extends JFrame {
		
		private JLabel lbNumeroRegistrazione;
		private JLabel lbScaffale;
		private JLabel lbPosizione;
		private JLabel lbCodice;
		private JLabel lbDisponibilita;
		
		private Connection conn=null;
		private PreparedStatement preparedStatement = null;
		private ResultSet resultSet = null;
		private String stat;
		
		
		private JTextField tfNumeroRegistrazione;
		private JTextField tfScaffale;
		private JTextField tfPosizione;
		private JTextField tfCodice;
		private JTextField tfDisponibilita;
		
		private GridBagConstraints c;
		
		private JButton btConferma;
		private JButton btAnnulla;
		private WindowEvent close;
		
		private JFrame Messaggio;
		
		
		private ListenerButton btListener;
		
		private class ListenerButton implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JButton bottone = new JButton();
				bottone= (JButton)e.getSource();
				if(bottone.equals(btConferma)){
					
						if((tfCodice.getText().isEmpty())||(tfDisponibilita.getText().isEmpty())
								||(tfNumeroRegistrazione.getText().isEmpty())||(tfScaffale.getText().isEmpty())||
								(tfPosizione.getText().isEmpty())){
							Messaggio = new FrameError("Devi riempire tutti i campi!");
							dispatchEvent(close);
							}
						else{
					try {
							stat ="Call CreaCopia(?,?,?,?,?)";
							preparedStatement=(PreparedStatement) conn.prepareStatement(stat);
							preparedStatement.setString(1, tfNumeroRegistrazione.getText());
							preparedStatement.setString(2, tfScaffale.getText());
							preparedStatement.setInt(3, Integer.parseInt(tfPosizione.getText()));
							preparedStatement.setString(4, tfCodice.getText());
							preparedStatement.setInt(5, Integer.parseInt("1"));
							preparedStatement.execute();
							Messaggio = new FrameError("Inserimento Corretto!");
							dispatchEvent(close);
							
						}
					 catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				}
				else if(bottone.equals(btAnnulla)){
					dispatchEvent(close);
					Messaggio = new FrameError("Annullato!");
				}
			}
		}
	
		public AggiungiCopia() {
			setTitle("Aggiungi Copia");
			setSize(500,500);
			setLayout(new GridBagLayout());
			close=new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
			initComponent();
			createPanel();
			setVisible(true);
			
		}
		
	private void initComponent(){
			
		//Database connessione
		connection();
		//model
		try {
			preparedStatement=(PreparedStatement) conn.prepareStatement("Select * From Volume");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
			lbNumeroRegistrazione = new JLabel("Numero Registrazione:");
			lbScaffale = new JLabel("Scaffale(Piano-LetteraScaffale):");
			lbPosizione = new JLabel("Posizione:");
			lbCodice = new JLabel("Codice Volume:");
			lbDisponibilita = new JLabel("Disponibilita':");
			
			tfCodice = new JTextField();
			tfNumeroRegistrazione = new JTextField();
			tfScaffale = new JTextField();
			tfPosizione= new JTextField();
			tfDisponibilita = new JTextField();
			tfDisponibilita.setText("1");
			tfDisponibilita.setEditable(false);
	
			btListener= new ListenerButton();
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
			add(lbNumeroRegistrazione, c);
			
			c.gridx = 1;
			add(tfNumeroRegistrazione, c);
			
			c.gridx = 0;
			c.gridy = 2;
			add(lbScaffale, c);
			
			c.gridx = 1;
			add(tfScaffale, c);
			
			c.gridx = 0;
			c.gridy = 3;
			add(lbPosizione, c);
			
			c.gridx = 1;
			add(tfPosizione, c);
			
			c.gridx = 0;
			c.gridy = 4;
			add(lbCodice, c);
			
			c.gridx = 1;
			add(tfCodice, c);
			
			c.gridx = 0;
			c.gridy = 5;
			add(lbDisponibilita, c);
			
			c.gridx = 1;
			add(tfDisponibilita, c);
			
			c.gridx = 0;
			c.gridy = 9;
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