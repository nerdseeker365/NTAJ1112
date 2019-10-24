package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableTest {

	public static void main(String[] args) {
		 Connection con=null;
		 Statement st=null;
		 String query=null;
		 int count=0;
		try {
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			//create Statement obj
			if(con!=null)
				st=con.createStatement();
			//prepare SQL query
			query="CREATE TABLE TEMP1( COL1 NUMBER(10))";
			//send and execute SQL query in DB s/w
			if(st!=null)
				count=st.executeUpdate(query);
			
			if(count==0)
				System.out.println("Table is created");
			else
				System.out.println("Table is not created");
			 
		}//try
		catch(SQLException se) {
			System.out.println("Table is not created");
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			
		}//finally

	}//main
}//class
