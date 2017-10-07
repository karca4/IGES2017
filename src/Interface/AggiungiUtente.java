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

public class AggiungiUtente extends JFrame{
	
	private JLabel lbNome;
	private JLabel lbCognome;
	private JLabel lbIndirizzo;
	
	private JTextField tfNome;
	private JTextField tfCognome;
	private JTextField tfIndirizzo;
	private JButton btConferma;
	private JButton btAnnulla;
	private ListenerButton btListener;
	private GridBagConstraints c;
	private JFrame Messaggio;
	
	private PreparedStatement preparedStatement = null;
	private Connection conn=null;
	private String stat="Call inserisciUtente(?,?,?)";
	
	private WindowEvent close;
	
	private class ListenerButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton bt=(JButton)e.getSource();
			if(bt.equals(btConferma)){
				
				if((tfNome.getText().isEmpty())||(tfCognome.getText().isEmpty())
						||(tfIndirizzo.getText().isEmpty())){
					Messaggio = new FrameError("Devi riempire tutti i campi!");
					dispatchEvent(close);
				}else{
				
				try {
					preparedStatement=(PreparedStatement)conn.prepareStatement("call creaUtente(?,?,?)");
					preparedStatement.setString(1, tfNome.getText());
					preparedStatement.setString(2, tfCognome.getText());
					preparedStatement.setString(3, tfIndirizzo.getText());
					preparedStatement.execute();
					dispatchEvent(close);
//					System.out.println(preparedStatement.execute());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			}
			else if(bt.equals(btAnnulla)){
				dispatchEvent(close);
				new FrameError("Operazione annullata!");
				
			}
		}
	}
	
	public AggiungiUtente() {
		setTitle("Aggiungi utente");
		close=new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		setSize(500,230);
		setLayout(new GridBagLayout());
		connection();
		initComponent();
		createPanel();
		setVisible(true);	
	}
	
	private void initComponent(){
		lbNome = new JLabel("Nome:");
		lbCognome = new JLabel("Cognome:");
		lbIndirizzo = new JLabel("Indirizzo:");
		btListener=new ListenerButton();
		tfNome = new JTextField();
		tfCognome = new JTextField();
		tfIndirizzo = new JTextField();
		btConferma = new JButton("Conferma");
		btConferma.addActionListener(btListener);
		btAnnulla = new JButton("Annulla");
		btAnnulla.addActionListener(btListener);
		
	}
	
	private void createPanel(){
		
		c = new GridBagConstraints();
		
		c.weightx=0.5;
		c.weighty=0.2;
		
		c.insets=new Insets(0, 30, 0, 0);
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		
		add(lbNome,c);
		
		c.gridx = 1;
		add(tfNome, c);
		
		c.gridx = 0;
		c.gridy = 1;
		add(lbCognome, c);
		
		c.gridx = 1;
		add(tfCognome, c);
		
		c.gridx = 0;
		c.gridy = 2;
		add(lbIndirizzo, c);
		
		c.gridx = 1;
		add(tfIndirizzo, c);
		
		c.gridx = 0;
		c.gridy = 3;
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
