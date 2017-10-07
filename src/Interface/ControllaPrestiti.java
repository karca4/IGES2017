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

public class ControllaPrestiti extends JFrame{
	
	private JLabel lbUtente;
	
	private JTextField tfUtente;
	private JButton btConferma;
	private JButton btControlla;
	private GridBagConstraints c;
	private ActionListener btListener;
	private JFrame Messaggio;
	private WindowEvent close;
	
	private PreparedStatement preparedStatement = null;
	private Connection conn=null;
	private String stat="Call azzeraAvvisi(?)";
	
	public ControllaPrestiti(){
		
		setTitle("Controlla Prestiti");
		setSize(300,200);
		setLayout(new GridBagLayout());
		close=new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		connection();
		initComponent();
		createPanel();
		setVisible(true);
		
	}
	
	
	private void initComponent(){
		
		lbUtente = new JLabel("Tessera utente:");
		tfUtente = new JTextField();
		btListener=new ListenerButton();
		
		btConferma = new JButton("Azzera Avvisi");
		btControlla = new JButton("Controlla prestiti");
		btConferma.addActionListener(btListener);
		btControlla.addActionListener(btListener);
		
	}
	
	private class ListenerButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton bt=(JButton)e.getSource();
			if(bt.equals(btConferma)){
			
				try{
					preparedStatement=(PreparedStatement)conn.prepareStatement(stat);
					preparedStatement.setString(1, tfUtente.getText());
					preparedStatement.execute();
					Messaggio = new FrameError("Avvisi azzerati correttamente!");
					dispatchEvent(close);
				}
				catch(SQLException e1){
					Messaggio = new FrameError("Avvisi non azzerati!");
					System.out.println(e1);
				}
			}else if(bt.equals(btControlla)){
				try{
					preparedStatement=(PreparedStatement)conn.prepareStatement("call controllaPrestiti()");
					preparedStatement.execute();
					Messaggio = new FrameError("Controllo avvenuto correttamente!");
					dispatchEvent(close);
				}
				catch(SQLException e1){
					Messaggio = new FrameError("Controllo non possibile!");
					System.out.println(e1);
				}
				
			}
			
		}
	}
	
	private void createPanel(){
		c=new GridBagConstraints();
		c.weightx=0.5;
		c.weighty=0.2;
		
		c.insets=new Insets(0, 5, 0, 5);
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		add(lbUtente,c);
		
		c.weightx=1;
		c.weighty=1;
		c.gridx =1;
		add(tfUtente, c);
		
		c.gridx=0;
		c.gridy =1;
		c.gridwidth=1;
		add(btConferma,c);
		
		c.gridwidth =1;
		c.gridx = 1;
//		c.gridy = 2;
		add(btControlla, c);
		
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
			conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","");
		} catch (SQLException ex) {
			System.exit(-2);
		}
	}

}
