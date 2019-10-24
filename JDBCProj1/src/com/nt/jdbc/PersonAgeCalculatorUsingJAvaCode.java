package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class PersonAgeCalculatorUsingJAvaCode {
	private static final String PERSON_DOB= "SELECT  DOB FROM PERSON_TAB WHERE PID=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int pid=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		java.sql.Date sqdob=null;
		java.util.Date sysDate=null;
		float age=0;
		long dobMS=0,sysDateMs=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("enter Person Id::");
				pid=sc.nextInt();
			}
			/*			//register JDBC driver s/w
						Class.forName("oracle.jdbc.driver.OracleDriver");
						//establish the conenction
						con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager"); 
			*/			
			//register JDBC driver s/w
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the conenction
			con=DriverManager.getConnection("jdbc:mysql:///ntaj1112DB","root","root"); 
			
			//create PReparedSTatement object
			if(con!=null)
				ps=con.prepareStatement(PERSON_DOB);
			//set input value to SQL Query
			if(ps!=null)
				ps.setInt(1,pid);
			//execute the Query
			if(ps!=null) 
				rs=ps.executeQuery();
			//process the ResultSet obj
			  if(rs!=null) {
				  if(rs.next()) {
					  // get MillSeconds representing boht SysDAte and DOB to calculate age
					  sqdob=rs.getDate(1);
					  sysDate=new Date();
					  dobMS=sqdob.getTime();
					  sysDateMs=sysDate.getTime();
					  age=((sysDateMs-dobMS)/(1000.0f*60L*60L*24L*365L));
					  System.out.println("Person Age::"+age);
				  }
				  else {
					  System.out.println("Person not found");
				  }
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
		
	}//main
}//class
