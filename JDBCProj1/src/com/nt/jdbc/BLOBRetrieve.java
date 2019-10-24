package com.nt.jdbc;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BLOBRetrieve {
  private static final String  BLOB_RETRIEVE_QUERY="SELECT APPID,APPNAME,APPADDRS,APPSALARY,APPPHOTO FROM MATRIMONY_TAB WHERE APPID=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int pid=0;
		String name=null,addrs=null;
		float salary=0.0f;
		InputStream is=null;
		OutputStream os=null;
		byte[] buffer=null;
		int bytesRead=0;
		
		
		
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter App Id::");
				no=sc.nextInt();
			}
			/*		//register JDBC driver s/w
					Class.forName("oracle.jdbc.driver.OracleDriver");
					//establish the connection
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");*/
			
			//register JDBC driver s/w
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj1112DB", "root","root");
			
			// create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(BLOB_RETRIEVE_QUERY);
			//set value to query paramter
			if(ps!=null)
				ps.setInt(1,no);
			//execute the Query
			if(ps!=null)
				rs=ps.executeQuery();
			//process the ResultSet object
		if(rs!=null) {	
			if(rs.next()) {
				no=rs.getInt(1);
				name=rs.getString(2);
				addrs=rs.getString(3);
				salary=rs.getFloat(4);
				is=rs.getBinaryStream(5);
				//create OutputStream pointing to dest file..
				os=new FileOutputStream("pict.jpg");
				//write  stream based logic using buffer support to write
				//BLOB col value to dest empty file..
				buffer=new byte[4096];
				while((bytesRead=is.read(buffer))!=-1) {
					os.write(buffer,0,bytesRead);
				}
				System.out.println(no+" "+name+" "+addrs+"  "+salary);
				System.out.println("BLOB value retrieved to pict.jpg");
			}
			else {
				System.out.println("record not found");
			}
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
				if(is!=null)
					is.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			try {
				if(os!=null)
					os.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}//finally
	}//main
}//class
