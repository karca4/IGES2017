package Interface;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class AggiungiAutore extends JFrame {		
		private JLabel lbCodiceFiscale;
		private JLabel lbNomeA;
		private JLabel lbCognomeA;
		private JLabel lbDataNascita;
		private JLabel lbCittaResidenza;
		
		private JTextField tfCodiceFiscale;
		private JTextField tfNomeA;
		private JTextField tfCognomeA;
		private JTextField tfDataNascita;
		private JTextField tfCittaResidenza;

		
		private GridBagConstraints c;
		
		private PreparedStatement preparedStatement = null;
		private Connection conn=null;
		private String stat="Call creaAutore(?,?,?,?,?)";
		private WindowEvent close;
		private JFrame Messaggio;
		
		
		private JButton btConferma;
		private JButton btAnnulla;
		
		private ActionListener btListener;
		
		private class ListenerButton implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton bt=(JButton)e.getSource();
				if(bt.equals(btConferma)){
					
					if((tfCodiceFiscale.getText().isEmpty())||(tfCognomeA.getText().isEmpty())
							||(tfNomeA.getText().isEmpty())||(tfCittaResidenza.getText().isEmpty())||
							(tfDataNascita.getText().isEmpty())){
						Messaggio = new FrameError("Devi riempire tutti i campi!");
						dispatchEvent(close);
						
					}else{
					
					try{
						preparedStatement=(PreparedStatement)conn.prepareStatement(stat);
						preparedStatement.setString(1, tfCodiceFiscale.getText());
						preparedStatement.setString(2, tfNomeA.getText());
						preparedStatement.setString(3, tfCognomeA.getText());
						preparedStatement.setString(4, tfDataNascita.getText());
						preparedStatement.setString(5, tfCittaResidenza.getText());
						preparedStatement.execute();
						Messaggio = new FrameError("Inserimento Corretto!");
						dispatchEvent(close);
					}
					catch(SQLException e1){
						//System.out.println(e1);
					}
				}
				}
				else if(bt.equals(btAnnulla)){
					dispatchEvent(close);
					Messaggio = new FrameError("Annullato!");
					
				}
			}
		}
		
		public AggiungiAutore() {
			setTitle("Aggiungi Autore");
			setSize(500,500);
			setLayout(new GridBagLayout());
			close=new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
			connection();
			initComponent();
			createPanel();
			setVisible(true);	
		}
		
	private void initComponent(){
			
			lbCodiceFiscale = new JLabel("Codice Fiscale:");
			lbNomeA = new JLabel("Nome:");
			lbCognomeA = new JLabel("Cognome:");
			lbDataNascita = new JLabel("Data di Nascita:");
			lbCittaResidenza = new JLabel("Citta' di Residenza:");
			
			tfNomeA= new JTextField();
			tfCognomeA= new JTextField();
			tfCodiceFiscale = new JTextField();
			tfDataNascita= new JTextField();
			tfCittaResidenza = new JTextField();
			
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
			add(lbCodiceFiscale,c);
			
			c.gridx = 1;
			add(tfCodiceFiscale, c);
			
			c.gridx = 0;
			c.gridy = 1;
			add(lbNomeA, c);
			
			c.gridx = 1;
			add(tfNomeA, c);
			
			c.gridx = 0;
			c.gridy = 2;
			add(lbCognomeA, c);
			
			c.gridx = 1;
			add(tfCognomeA, c);
			
			c.gridx = 0;
			c.gridy = 3;
			add(lbDataNascita, c);
			
			c.gridx = 1;
			add(tfDataNascita, c);
			
			c.gridx = 0;
			c.gridy = 4;
			add(lbCittaResidenza, c);
			
			c.gridx = 1;
			add(tfCittaResidenza, c);
	
			
			c.gridx = 0;
			c.gridy = 5;
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
