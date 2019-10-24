package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BulkInsertTestWithPS {
	private static  final   String  INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";

	public static void main(String[] args) {
		Scanner sc=null;
		int count=0;
		Connection con=null;
		PreparedStatement ps=null;
		int no=0;
		String name=null,addrs=null;
		float avg=0.0f;
		int result=0;
		
		try {
			//inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Students count::");
				count=sc.nextInt();
			}//if
			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PreparedStatement obj having pre-compiled SQL Query
			if(con!=null)
				ps=con.prepareStatement(INSERT_QUERY);
			//read and set input vlaues to Query params
			if(ps!=null && sc!=null) {
				for(int i=1;i<=count;++i) {
					//read each student details
					System.out.println("Enter "+i+" student details ");
					 System.out.println("Enter number::");
					 no=sc.nextInt();
					 System.out.println("Enter name::");
					 name=sc.next();
					 System.out.println("Enter addrs::");
					 addrs=sc.next();
					 System.out.println("Enter avg::");
					 avg=sc.nextFloat();
					 //set  each student details to Query params values
					 ps.setInt(1,no);
					 ps.setString(2,name);
					 ps.setString(3,addrs);
					 ps.setFloat(4,avg);
					 //execute the query
					 result=ps.executeUpdate();
					 //process the result
					 if(result==0)
						   System.out.println(i+" Record not inserted");
					 else
						  System.out.println(i+" Record  inserted");
				}//for
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception  e) {
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
		}//finally
	}//main
}//class
