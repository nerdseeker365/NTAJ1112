package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PSDeleteTest {
   private static final String DELETE_QUERY="DELETE FROM STUDENT WHERE SNO=901"; 
	public static void main(String[] args) {
		int no=0;
		Connection con=null;
		PreparedStatement   ps=null;
		String query=null;
		int  count=0;
		try {
			//register JDBC driver (optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Statement object
			if(con!=null)
				ps=con.prepareStatement(DELETE_QUERY);
			//send and execute  SQL Query in Db s/w
			if(ps!=null)
				count=ps.executeUpdate();
			//process the result
			if(count==0)
				  System.out.println("Record(s) not found for deletion");
			else
				  System.out.println("Record  found and  deleted");
		}//try
		catch (SQLException se) {
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
				if(ps!=null)
					ps.close();
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
