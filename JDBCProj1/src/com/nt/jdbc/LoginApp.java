package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {

	public static void main(String[] args) {
		Scanner sc=null;
		String user=null,pass=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		int count=0;
		//read inputs
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("enter usernmae::");
				user=sc.nextLine(); //gives raja 
				System.out.println("enter password ::");
				pass=sc.nextLine(); //gives hyd
			}//if
			
			//convert inputs as required for the SQL Query
			user="'"+user+"'";  //givers  'raja'
			pass="'"+pass+"'";  //gives  'rani'
			
		}//try
		catch(Exception e) {
			e.printStackTrace();
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
		
		//register JDBC driver s/w
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		
		
		try {
			//establish  the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL query with  inputs
			             //select count(*) from userlist where uname='raja' and  pwd='rani';
			  query="SELECT COUNT(*) FROM USERLIST WHERE UNAME="+user+"AND PWD="+pass;
			  System.out.println(query);
			  
			  //send and execute SQL Query in DB s/w
			  if(st!=null)
			       rs=st.executeQuery(query);
			  //process the ResultSet object 
			  if(rs!=null) {
				   rs.next();
			    count=rs.getInt(1);
			  }
			  if(count==0)
				  System.out.println("Invalid Credentials");
			  else
				  System.out.println("Valid Credetials");
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close  jdbc objs
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
