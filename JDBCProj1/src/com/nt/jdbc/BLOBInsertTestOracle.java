package com.nt.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*SQL> create table Matrimony_tab(appId number(5) primary key,appName varchar2(15),appAddrs varchar2(15),appSalary float,appPhoto BLOB);
Table created.
SQL> create sequence AppId_SEQ start with  1 increment by 1;
Sequence created.
*/
public class BLOBInsertTestOracle {
   private static final String  INSERT_MATRIMONY_QUERY="INSERT INTO MATRIMONY_TAB VALUES(APPID_SEQ.NEXTVAL,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String name=null,addrs=null,photoPath=null;
		float salary=0.0f;
		Connection con=null;
		PreparedStatement ps=null;
		File file=null;
		InputStream is=null;
		long length=0;
		int result=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("enter  Bride/groom name::");
				name=sc.next();
				System.out.println("Enter addrss::");
				addrs=sc.next();
				System.out.println("Enter salary::");
				salary=sc.nextFloat();
				System.out.println("Enter  photo Path::");
				photoPath=sc.next();
			}
			 //register  JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(INSERT_MATRIMONY_QUERY);
			//Locate file and get the length of the file
			    file=new File(photoPath);
			    length=file.length();
			    //create InputStream locating the file
			    is=new FileInputStream(file);
			
			//set values to Query params
			if(ps!=null && is!=null) {
				ps.setString(1, name);
				ps.setString(2,addrs);
				ps.setFloat(3,salary );
				ps.setBinaryStream(4,is,length);
			}
			//execute the Query
			 if(ps!=null) 
				 result=ps.executeUpdate();
			 //process the results
			 if(result==0)
				 System.out.println("record insertion failed");
			 else
				 System.out.println("record insertion succeded");
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(FileNotFoundException fnf) {
			fnf.printStackTrace();
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
				if(is!=null)
					is.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally

	}//main
}//class
