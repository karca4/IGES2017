package Interface;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.ResultSetMetaData;

public class Ricerca extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private MDTable modelTb;
	private JLabel lbTxt;
	private JTextField tfTxt;
	private ButtonGroup group;
	private JRadioButton rbAutore;
	private JRadioButton rbTitolo;
	private JRadioButton rbEditore;
	private JButton but;
	private GridBagConstraints c;
	private ListenerButton ls;
	private Connection conn=null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private String stat;
	
	private JRadioButton rbCopia;
	private JRadioButton rbGenere;
	private JRadioButton rbPrestitiTot;
	private JRadioButton rbPrestitiGiorn;
	private JRadioButton rbVisualizzaUtenti;
	private JRadioButton rbVolume;
	private JRadioButton rbLibro;
	private JRadioButton rbManuale;
	private JRadioButton rbPeriodico;
	private JRadioButton rbDizionario;
	private JRadioButton rbCollana;
	
	private class ListenerButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(rbTitolo.isSelected()){
				try {
					
					if(tfTxt.getText().isEmpty()){
						preparedStatement=(PreparedStatement) conn.prepareStatement("Select * From volume order by Titolo");
					}else{
						stat ="Select * from volume where titolo = ?";
						preparedStatement=(PreparedStatement) conn.prepareStatement(stat);
						preparedStatement.setString(1, tfTxt.getText());
						tfTxt.setText("");
					}
					modelTb=createModel(preparedStatement);
					table.setModel(modelTb);
					JTableHeader hed=table.getTableHeader();
					TableColumnModel modCol=hed.getColumnModel();
					for(int i=0;i<modelTb.getColumnName().length;i++){
						TableColumn tc=modCol.getColumn(i);
						tc.setHeaderValue(modelTb.getColumnName()[i]);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			else if(rbEditore.isSelected()){
				try {
					if(tfTxt.getText().isEmpty()){
						preparedStatement=(PreparedStatement) conn.prepareStatement("Select distinct casaeditrice.*, volume.titolo From Volume right join casaeditrice"
								+ " on volume.denominazioneEditore = casaeditrice.denominazione and volume.cittaEditore=casaeditrice.cittaDiAppartenenza");
					}else{
						stat ="Select * from volume where DenominazioneEditore = ?";
						preparedStatement=(PreparedStatement) conn.prepareStatement(stat);
						preparedStatement.setString(1, tfTxt.getText());
						tfTxt.setText("");
					}
					modelTb=createModel(preparedStatement);
					table.setModel(modelTb);
					JTableHeader hed=table.getTableHeader();
					TableColumnModel modCol=hed.getColumnModel();
					for(int i=0;i<modelTb.getColumnName().length;i++){
						TableColumn tc=modCol.getColumn(i);
						tc.setHeaderValue(modelTb.getColumnName()[i]);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			else if(rbCopia.isSelected()){
				try {
					if(tfTxt.getText().isEmpty()){
						preparedStatement=(PreparedStatement) conn.prepareStatement("Select * From Copia");
					}else{
						stat ="Call RicercaCopia(?)";
						preparedStatement=(PreparedStatement) conn.prepareStatement(stat);
						preparedStatement.setString(1, tfTxt.getText());
						tfTxt.setText("");
					}
					modelTb=createModel(preparedStatement);
					table.setModel(modelTb);
					JTableHeader hed=table.getTableHeader();
					TableColumnModel modCol=hed.getColumnModel();
					for(int i=0;i<modelTb.getColumnName().length;i++){
						TableColumn tc=modCol.getColumn(i);
						tc.setHeaderValue(modelTb.getColumnName()[i]);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			else if(rbAutore.isSelected()){
				try {
					if(tfTxt.getText().isEmpty()){
						preparedStatement=(PreparedStatement) conn.prepareStatement("select autore.*, scritto.CodVolume from autore left join scritto on autore.CodiceFiscale = scritto.CodAutore where 1");
					}else{
						String nome = new String();
						String cognome= new String();
						String frase[]=tfTxt.getText().split(" ");
						nome = frase[0];
						cognome=frase[1];
						stat ="Call RicercaAutore(?,?)";
						preparedStatement=(PreparedStatement) conn.prepareStatement(stat);
						preparedStatement.setString(1, nome);
						preparedStatement.setString(2, cognome);
						tfTxt.setText("");
					}
					modelTb=createModel(preparedStatement);
					table.setModel(modelTb);
					JTableHeader hed=table.getTableHeader();
					TableColumnModel modCol=hed.getColumnModel();
					for(int i=0;i<modelTb.getColumnName().length;i++){
						TableColumn tc=modCol.getColumn(i);
						tc.setHeaderValue(modelTb.getColumnName()[i]);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			
			else if(rbGenere.isSelected()){
				try {
					if(tfTxt.getText().isEmpty()){
						preparedStatement=(PreparedStatement) conn.prepareStatement("Select volume.*,libro.tipo From Libro"
								+ " join volume on volume.codice=libro.CodVolume");
					}else{
						
						stat ="Call RicercaGenere(?)";
						preparedStatement=(PreparedStatement) conn.prepareStatement(stat);
						preparedStatement.setString(1, tfTxt.getText());
						tfTxt.setText("");
					}
					modelTb=createModel(preparedStatement);
					table.setModel(modelTb);
					JTableHeader hed=table.getTableHeader();
					TableColumnModel modCol=hed.getColumnModel();
					for(int i=0;i<modelTb.getColumnName().length;i++){
						TableColumn tc=modCol.getColumn(i);
						tc.setHeaderValue(modelTb.getColumnName()[i]);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			else if(rbPrestitiTot.isSelected()){
				try {
					if(tfTxt.getText().isEmpty()){
						preparedStatement=(PreparedStatement) conn.prepareStatement("Select * from Prestito");
					}else{
						
						stat ="Call RicercaPrestitiUtente(?)";
						preparedStatement=(PreparedStatement) conn.prepareStatement(stat);
						preparedStatement.setString(1, tfTxt.getText());
						tfTxt.setText("");
					}
					modelTb=createModel(preparedStatement);
					table.setModel(modelTb);
					JTableHeader hed=table.getTableHeader();
					TableColumnModel modCol=hed.getColumnModel();
					for(int i=0;i<modelTb.getColumnName().length;i++){
						TableColumn tc=modCol.getColumn(i);
						tc.setHeaderValue(modelTb.getColumnName()[i]);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			else if(rbPrestitiGiorn.isSelected()){
				try {
					if(tfTxt.getText().isEmpty()){
						preparedStatement=(PreparedStatement) conn.prepareStatement("Select * from Prestito where datarestituzione = current_date()");
					}else{
						
						stat ="Select * from Prestito where datarestituzione = current_date() and numTessUtente= ?";
						preparedStatement=(PreparedStatement) conn.prepareStatement(stat);
						preparedStatement.setString(1, tfTxt.getText());
						tfTxt.setText("");
					}
					modelTb=createModel(preparedStatement);
					table.setModel(modelTb);
					JTableHeader hed=table.getTableHeader();
					TableColumnModel modCol=hed.getColumnModel();
					for(int i=0;i<modelTb.getColumnName().length;i++){
						TableColumn tc=modCol.getColumn(i);
						tc.setHeaderValue(modelTb.getColumnName()[i]);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			else if(rbVisualizzaUtenti.isSelected()){
				try {
					if(tfTxt.getText().isEmpty()){
						preparedStatement=(PreparedStatement) conn.prepareStatement("Select * from utente");
					}else{
						
						stat ="call VisualizzaUtenti(?)";
						preparedStatement=(PreparedStatement) conn.prepareStatement(stat);
						preparedStatement.setString(1, tfTxt.getText());
						tfTxt.setText("");
					}
					modelTb=createModel(preparedStatement);
					table.setModel(modelTb);
					JTableHeader hed=table.getTableHeader();
					TableColumnModel modCol=hed.getColumnModel();
					for(int i=0;i<modelTb.getColumnName().length;i++){
						TableColumn tc=modCol.getColumn(i);
						tc.setHeaderValue(modelTb.getColumnName()[i]);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			else if(rbVolume.isSelected()){
				try {
					if(tfTxt.getText().isEmpty()){
						preparedStatement=(PreparedStatement) conn.prepareStatement("Select * from Volume");
					}else{
						
						stat ="Select * from Volume where Codice = ?";
						preparedStatement=(PreparedStatement) conn.prepareStatement(stat);
						preparedStatement.setString(1, tfTxt.getText());
						tfTxt.setText("");
					}
					modelTb=createModel(preparedStatement);
					table.setModel(modelTb);
					JTableHeader hed=table.getTableHeader();
					TableColumnModel modCol=hed.getColumnModel();
					for(int i=0;i<modelTb.getColumnName().length;i++){
						TableColumn tc=modCol.getColumn(i);
						tc.setHeaderValue(modelTb.getColumnName()[i]);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

			else if(rbLibro.isSelected()){
				try {
					if(tfTxt.getText().isEmpty()){
						preparedStatement=(PreparedStatement) conn.prepareStatement("Select volume.*,libro.tipo,libro.genere from Libro join volume on volume.codice = libro.codVolume");
					}else{
						
						stat ="call ricercaLibro(?)";
						preparedStatement=(PreparedStatement) conn.prepareStatement(stat);
						preparedStatement.setString(1, tfTxt.getText());
						tfTxt.setText("");
					}
					modelTb=createModel(preparedStatement);
					table.setModel(modelTb);
					JTableHeader hed=table.getTableHeader();
					TableColumnModel modCol=hed.getColumnModel();
					for(int i=0;i<modelTb.getColumnName().length;i++){
						TableColumn tc=modCol.getColumn(i);
						tc.setHeaderValue(modelTb.getColumnName()[i]);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			else if(rbManuale.isSelected()){
				try {
					if(tfTxt.getText().isEmpty()){
						preparedStatement=(PreparedStatement) conn.prepareStatement("Select volume.*,manuale.categoria from Manuale join volume on volume.codice= manuale.codvolume ");
					}else{
						
						stat ="call ricercaManuale(?)";
						preparedStatement=(PreparedStatement) conn.prepareStatement(stat);
						preparedStatement.setString(1, tfTxt.getText());
						tfTxt.setText("");
					}
					modelTb=createModel(preparedStatement);
					table.setModel(modelTb);
					JTableHeader hed=table.getTableHeader();
					TableColumnModel modCol=hed.getColumnModel();
					for(int i=0;i<modelTb.getColumnName().length;i++){
						TableColumn tc=modCol.getColumn(i);
						tc.setHeaderValue(modelTb.getColumnName()[i]);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			else if(rbPeriodico.isSelected()){
				try {
					if(tfTxt.getText().isEmpty()){
						preparedStatement=(PreparedStatement) conn.prepareStatement("Select volume.*, periodico.frequenza from periodico join volume on volume.codice=periodico.codvolume");
					}else{
						
						stat ="call ricercaPeriodico(?)";
						preparedStatement=(PreparedStatement) conn.prepareStatement(stat);
						preparedStatement.setString(1, tfTxt.getText());
						tfTxt.setText("");
					}
					modelTb=createModel(preparedStatement);
					table.setModel(modelTb);
					JTableHeader hed=table.getTableHeader();
					TableColumnModel modCol=hed.getColumnModel();
					for(int i=0;i<modelTb.getColumnName().length;i++){
						TableColumn tc=modCol.getColumn(i);
						tc.setHeaderValue(modelTb.getColumnName()[i]);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			else if(rbDizionario.isSelected()){
				try {
					if(tfTxt.getText().isEmpty()){
						preparedStatement=(PreparedStatement) conn.prepareStatement("Select volume.*, dizionario.secondalingua from dizionario join volume on volume.codice=dizionario.codvolume");
					}else{
						
						stat ="call ricercaDizionario(?)";
						preparedStatement=(PreparedStatement) conn.prepareStatement(stat);
						preparedStatement.setString(1, tfTxt.getText());
						tfTxt.setText("");
					}
					modelTb=createModel(preparedStatement);
					table.setModel(modelTb);
					JTableHeader hed=table.getTableHeader();
					TableColumnModel modCol=hed.getColumnModel();
					for(int i=0;i<modelTb.getColumnName().length;i++){
						TableColumn tc=modCol.getColumn(i);
						tc.setHeaderValue(modelTb.getColumnName()[i]);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			else if(rbCollana.isSelected()){
				try {
					if(tfTxt.getText().isEmpty()){
						preparedStatement=(PreparedStatement) conn.prepareStatement("Select volume.titolo from  appartiene join volume on appartiene.codvolume = volume.codice");
					}
					else{
						stat ="call ricercaCollana(?)";
						preparedStatement=(PreparedStatement) conn.prepareStatement(stat);
						preparedStatement.setString(1, tfTxt.getText());
						tfTxt.setText("");
					}
					modelTb=createModel(preparedStatement);
					table.setModel(modelTb);
					JTableHeader hed=table.getTableHeader();
					TableColumnModel modCol=hed.getColumnModel();
					for(int i=0;i<modelTb.getColumnName().length;i++){
						TableColumn tc=modCol.getColumn(i);
						tc.setHeaderValue(modelTb.getColumnName()[i]);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		}
	}
	
	
	public Ricerca() {
		this.setLayout(new GridBagLayout());
		initComponent();
		craeteFrame();
	}
	
	private void initComponent(){
		//Database connessione
			connection();
		//model
		try {
			preparedStatement=(PreparedStatement) conn.prepareStatement("Select * From Volume");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		modelTb=createModel(preparedStatement);
		///
		table=new JTable(modelTb);
		
		JTableHeader hed=table.getTableHeader();
		TableColumnModel modCol=hed.getColumnModel();
		for(int i=0;i<modelTb.getColumnName().length;i++){
			TableColumn tc=modCol.getColumn(i);
			tc.setHeaderValue(modelTb.getColumnName()[i]);
		}
		
		ls=new ListenerButton();
		lbTxt=new JLabel("Inserisci Valore Da Ricercare:");

		rbLibro = new JRadioButton("Libro");
		rbPeriodico= new JRadioButton("Periodico");
		rbManuale = new JRadioButton("Manuale");
		rbDizionario = new JRadioButton("Dizionario");
		rbAutore=new JRadioButton("Autore");
		rbEditore=new JRadioButton("Editore");
		rbTitolo=new JRadioButton("Titolo");
		rbTitolo.setSelected(true);
		rbCopia = new JRadioButton("Copia");
		rbGenere = new JRadioButton("Tipo");
		rbPrestitiTot = new JRadioButton("Prestiti totali");
		rbPrestitiGiorn = new JRadioButton("Prestiti con restituzione giornaliera");
		rbVisualizzaUtenti = new JRadioButton("Visualizza Utenti");
		rbVolume= new JRadioButton("Volume");
		rbCollana = new JRadioButton("Collana");
		
		group=new ButtonGroup();
		group.add(rbAutore);
		group.add(rbEditore);
		group.add(rbTitolo);
		group.add(rbCopia);
		group.add(rbGenere);
		group.add(rbPrestitiTot);
		group.add(rbPrestitiGiorn);
		group.add(rbVisualizzaUtenti);
		group.add(rbVolume);
		group.add(rbLibro);
		group.add(rbPeriodico);
		group.add(rbManuale);
		group.add(rbDizionario);
		group.add(rbCollana);
		
		tfTxt=new JTextField();
		but=new JButton("Cerca");
		but.addActionListener(ls);

	}
	
	
	private void craeteFrame() {
		c=new GridBagConstraints();
		c.weightx=0.5;
		c.weighty=0.2;
		
		c.insets=new Insets(0, 30, 0, 0);
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		this.add(lbTxt,c);
		
		c.insets=new Insets(0, 0, 0, 30);
		c.gridx=1;
		c.gridwidth=1;
		this.add(tfTxt, c);
		
		c.insets=new Insets(0, 60, 0, 20);
		c.gridx=2;
		c.gridwidth=1;
		this.add(but, c);
		
		c.weightx=0.2;
		c.weighty=0.2;
		
		c.insets=new Insets(0, 30, 0, 0);
		c.gridy=1;
		c.gridx=0;
		this.add(rbTitolo, c);
		
		c.gridx=1;
		this.add(rbAutore, c);
		
		c.gridx=2;
		this.add(rbEditore, c);
		
		
		c.gridx = 0;
		c.gridy = 2;
		add(rbCopia, c);
		
		c.gridx = 1;
		add(rbGenere, c);
		
		c.gridx = 2;
		add(rbPrestitiTot, c);
		
		c.gridx = 0;
		c.gridy = 3;
		add(rbPrestitiGiorn, c);
		
		c.gridx = 1;
		c.gridy = 3;
		add(rbVisualizzaUtenti, c);
		
		c.gridx = 2;
		c.gridy = 3;
		add(rbVolume, c);
		
		c.gridx = 0;
		c.gridy = 4;
		add(rbLibro, c);
		
		c.gridx = 1;
		c.gridy = 4;
		add(rbManuale, c);
		
		c.gridx = 2;
		c.gridy = 4;
		add(rbPeriodico, c);
		
		c.gridx = 0;
		c.gridy = 5;
		add(rbDizionario, c);
		
		c.gridx = 1;
		c.gridy = 5;
		add(rbCollana, c);
		
		c.insets=new Insets(0, 30, 0, 30);
		c.fill=GridBagConstraints.BOTH;
		c.weightx=1;
		c.weighty=7;
		c.gridwidth=3;
		c.gridheight=2;
		c.gridx=0;
		c.gridy=6;
		this.add(new JScrollPane(table), c);
			
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
			ex.printStackTrace();
			System.exit(-2);
		}
		
	}
	
	
	private MDTable createModel(PreparedStatement query){
		
		MDTable model=null;
		
		try {

			resultSet=(ResultSet) preparedStatement.executeQuery();
			
			ResultSetMetaData mt=(ResultSetMetaData) resultSet.getMetaData();
			int k = 0;
			while(resultSet.next()) k++;
			int j = 0;
			j = mt.getColumnCount();
			model=new MDTable(k,j);
			
			resultSet.first();
			j++;
			for(int x=0;x<=k;x++){
				
				for(int i=1;i<j;i++){
					int type=mt.getColumnType(i);
					model.setColumnName(mt.getColumnName(i), i-1);
					if(type==Types.VARCHAR || type==Types.CHAR){
						model.setValueAt(resultSet.getString(i), x, i-1);
					}else if(type==Types.INTEGER){
						model.setValueAt(resultSet.getInt(i), x, i-1);
					}else if(type==Types.DATE){
						model.setValueAt(resultSet.getDate(i),x, i-1);
					}else if(type==Types.BOOLEAN){
						model.setValueAt(resultSet.getBoolean(i),x, i-1);
					}else
						model.setValueAt(resultSet.getString(i), x, i-1);
					
				}
				if(resultSet.isLast() == false)	resultSet.next();
				else break;
			}
			
			
			
		} catch (SQLException e) {
			// Tabella vuota
		}
		
		return model;
		
	}
	
	
}
