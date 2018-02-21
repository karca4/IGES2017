package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionManager {


	protected String DRIVER = "com.mysql.jdbc.Driver";
	protected String url = "jdbc:mysql://localhost/biblioteca";
	protected String user = "root";
	protected String psw = "";
	
	
	
	public Connection connect(){
	
		Connection con=null;
		
		try{                   
			Class.forName(DRIVER);                 
			con = DriverManager.getConnection(url,user,psw);
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return con;
	
	}
	
	
	
	public void freeResources(Connection con, Statement st, ResultSet rs){
	
		try	{           
			rs.close();           
			st.close();
			con.close();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}       
	
	}



}
