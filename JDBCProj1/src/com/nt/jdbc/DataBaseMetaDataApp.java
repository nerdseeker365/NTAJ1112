package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DataBaseMetaDataApp {

	public static void main(String[] args) {
		Connection con=null;
		DatabaseMetaData dbmd=null;
		  try {
			  //register JDBC driver s/w
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  //establish the connection
			  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			  //create DataBaseMetaData object
			  if(con!=null)
				  dbmd=con.getMetaData();
			  //get Limitations  and capabilities of DB s/w
			  if(dbmd!=null) {
				  System.out.println("DB name::"+dbmd.getDatabaseProductName());
				  System.out.println("DB Major Version:: "+dbmd.getDatabaseMajorVersion());
				  System.out.println("DB Minor Version:: "+dbmd.getDatabaseMinorVersion());
				  System.out.println("DB producut version ::"+dbmd.getDatabaseProductVersion());
				  System.out.println("All SQL keywords ::"+dbmd.getSQLKeywords());
				  System.out.println("All System functions ::"+dbmd.getSystemFunctions());
				  System.out.println("All  String functions ::"+dbmd.getStringFunctions());
				  System.out.println("Max chars in db table name ::"+dbmd.getMaxTableNameLength());
				  System.out.println("Max chars in column nmae::"+dbmd.getMaxColumnNameLength());
				  System.out.println("Max row size ::"+dbmd.getMaxRowSize());
				  System.out.println("Supports PL/SQL procedures::"+dbmd.supportsStoredProcedures());
			  }//if
			  }//try
			  catch(SQLException se) {
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
			  if(con!=null)
				  con.close();
		  }//try
			  catch(SQLException se) {
				  se.printStackTrace();
			  }
		  }//finally
	}//main
}//class
