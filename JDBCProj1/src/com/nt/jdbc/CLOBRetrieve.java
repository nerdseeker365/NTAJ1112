package com.nt.jdbc;

import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class CLOBRetrieve {
    private static final String  JOBSEEKER_RETRIEVE="SELECT JSID,JSNAME,JSADDRS,JSQLFY,RESUME FROM JOBSEEKER_TAB WHERE JSID=?";
	public static void main(String[] args) {
		int jsId=0;
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String name=null,addrs=null,qlfy=null;
		Reader reader=null;
		Writer writer=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("enter JobSeeker Id::");
				jsId=sc.nextInt();
			}
	/*		//register JDBC driver s/w
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///NTAJ1112DB","root","root");  */
			
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager"); 
			
			//create PreparedStatement obj
			if(con!=null)
				ps=con.prepareStatement(JOBSEEKER_RETRIEVE);
			//set values  to Query params
			if(ps!=null)
				ps.setInt(1, jsId);
			//execute SQL Query
			if(ps!=null) 
				rs=ps.executeQuery();
			//process the ResulSEt
			if(rs!=null) {
				if(rs.next()) {
					jsId=rs.getInt(1);
					name=rs.getString(2);
					addrs=rs.getString(3);
					qlfy=rs.getString(4);
					reader=rs.getCharacterStream(5);
					//create Writer Steam point to dest file
					writer=new FileWriter("profile.txt");
					//write streams,buffer based logic to perform file copy operation
					if(reader!=null && writer!=null) {
					        IOUtils.copy(reader,writer);
						System.out.println(jsId+" "+name+"  "+addrs+" "+qlfy);
						System.out.println("CLOB File is retrieved");
					}//if
					
				}//if
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
			//close jdbc objs and streams
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
			try {
				if(reader!=null)
					reader.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			try {
				if(writer!=null)
					writer.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally

	}//main
}//class
