package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DropTableTest {

	public static void main(String[] args) {
		Scanner sc=null;
		String tabName=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		int count=0;
		try {
		//read inputs
		  sc=new Scanner(System.in);
		  if(sc!=null) {
			  System.out.println("Enter Db table name to drop::");
			  tabName=sc.next();  //gives temp
		  }
		}//try
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
		finally {
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//register JDBC driver
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
			return;
		}
		
		
	try {	
		//establish the connection
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
		}
		catch(SQLException se) {
			se.printStackTrace();
			return;
		}
		
		//create Statement object
		try {
			if(con!=null)
				 st=con.createStatement();
		}
		catch(SQLException se) {
			se.printStackTrace();
			return;
		}
		
		//prepare SQL Query
		             //DROP TABLE  TEMP
		 query="DROP TABLE   "+tabName;
		 try {
			 if(st!=null) {
				 count=st.executeUpdate(query);
			 }
			 //process the result
			 if(count==0)
				 System.out.println("Table dropped");
			 else
				 System.out.println("Table is not dropped");
		 }//try
		 catch(SQLException se) {
			 se.printStackTrace();
			 return;
		 }
	}//try
	
	finally {
		System.out.println("closing jdbc objs...");
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
