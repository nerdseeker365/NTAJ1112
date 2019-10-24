package com.nt.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*SQL> create table JobSeeker_tab (jsId number(6) primary key,jsName varchar2(20),jsAddrs varchar2(20),jsQlfy varchar2(20),resume CLOB);

Table created.

SQL> create sequence jsId_seq  start with 1 increment by 1;

Sequence created. */

import java.util.Scanner;

public class CLOBInsertwithOracle {
 private static final String  INSERT_JOBSEEKER_QUERY="INSERT INTO JOBSEEKER_TAB VALUES(JSID_SEQ.NEXTVAL,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String jsName=null;
		String jsAddrs=null;
		String jsQlfy=null;
		String resumePath=null;
		Connection con=null; 
		PreparedStatement ps=null;
		File file=null;
		long length=0;
		Reader reader=null;
		int count=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("enter JobSeeker name::");
				jsName=sc.next();
				System.out.println("enter job seeker addrs::");
				jsAddrs=sc.next();
				System.out.println("enter job seeker qualification::");
				jsQlfy=sc.next();
				System.out.println("Enter Job seeker resume doc location::");
				resumePath=sc.next();
			}//if
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","manager");
			//create PReparedStatement object
			if(con!=null)
				ps=con.prepareStatement(INSERT_JOBSEEKER_QUERY);
			//Locate resume doc file
			  file=new File(resumePath);
			  //get the length of the file
			    length=file.length();
			  //create Reader stream pointing to resume doc Location
			    reader=new FileReader(file);
			//set values to query params
			if(ps!=null && reader!=null) {
				ps.setString(1,jsName );
				ps.setString(2,jsAddrs);
				ps.setString(3,jsQlfy);
				ps.setCharacterStream(4, reader,length);
			}
			//execute the Query
			if(ps!=null)
				count=ps.executeUpdate();
			//process the reuslt
			if(count==0)
				  System.out.println("Job seekers Info is not inserted");
			else
				  System.out.println("Job seekers Info is  inserted");
		}//try
		catch(SQLException se) {
			 System.out.println("Job seekers Info is not inserted");
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
			
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(reader!=null)
					reader.close();
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}//finally
	}//main
}//class
