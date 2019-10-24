package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonAgeCalculatorUsingOracleSQL {
	private static final String PERSON_AGE_CALCULATOR= "SELECT  (SYSDATE-DOB)/365.0 FROM PERSON_TAB WHERE PID=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int pid=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("enter Person Id::");
				pid=sc.nextInt();
			}
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the conenction
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PReparedSTatement object
			if(con!=null)
				ps=con.prepareStatement(PERSON_AGE_CALCULATOR);
			//set input value to SQL Query
			if(ps!=null)
				ps.setInt(1,pid);
			//execute the Query
			if(ps!=null) 
				rs=ps.executeQuery();
			//process the ResultSet obj
			  if(rs!=null) {
				  if(rs.next()) 
					  System.out.println("Person Age::"+rs.getFloat(1));
				  else 
					  System.out.println("Person Not found");
			  }
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
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
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
			
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}//finally
		
	}

}
