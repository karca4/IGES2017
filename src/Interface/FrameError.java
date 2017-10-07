package Interface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FrameError extends JFrame {

	private JLabel lbError;
	private JButton btExit;
	private WindowEvent close;
	private String error;
	private GridBagConstraints c;
	
	public FrameError(String error) {
		this.setTitle(error);
		setLayout(new GridBagLayout());
		setLocation(250, 250);
		setMaximumSize(new Dimension(600, 150));
		setMinimumSize(new Dimension(600, 150));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		this.error = error;
		
		initComponent();		
		createFrameError();
		btExit.addActionListener(new Listener());
		
	}
	
	private class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			dispatchEvent(close);
		}
	}
	
	public void initComponent(){
		c = new GridBagConstraints();
		close = new WindowEvent( this , WindowEvent.WINDOW_CLOSING);
		btExit = new JButton("Ok");
		lbError=new JLabel(error);
		lbError.setForeground(Color.RED);
		lbError.setFont(new Font("testo", Font.ITALIC, 15));
	}
	
	public void createFrameError(){
		//label
		c.insets = new Insets(10, 30, 10, 30);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		add(lbError, c);
		
		//bottone
		c.insets = new Insets(10, 150, 10, 150);
		c.gridy = 1;
		add(btExit, c);
		
	}
	
	
}
