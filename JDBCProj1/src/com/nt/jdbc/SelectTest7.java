package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest7 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		boolean flag=false;
		try {
			//register JDBC Driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			//create JDBC Statement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query
			query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)";
			//send and execute SQL Query in DB s/w
			if(st!=null)
				rs=st.executeQuery(query);
			//process the ResultSet 
			 if(rs!=null) {
				 while(rs.next()) {
					 flag=true;
					 System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getInt(4));
				 }//while
				 
				 if(flag==false) {
					 System.out.println("no records found");
				 }
			 }//if
		}//try
		catch(SQLException se) {  // known exception
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {  //known exception 
			cnf.printStackTrace();
		}
		catch(Exception e) {  // unknown exception
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
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
