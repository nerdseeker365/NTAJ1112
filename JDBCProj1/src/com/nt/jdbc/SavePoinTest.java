package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Scanner;

public class SavePoinTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int pid=0;
		int acno=0;
		Connection con=null;
		Statement st=null;
		int result1=0, result2=0;
		Savepoint svpt1=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter  product number::");
				pid=sc.nextInt();
				System.out.println("Enter Account number::");
				acno=sc.nextInt();
			}
			//register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			//crerate Statement object
			if(con!=null)
				st=con.createStatement();
			//begin Tx
			con.setAutoCommit(false);
			//book the product
			result1=st.executeUpdate("UPDATE PRODUCT SET QTY=QTY-1 WHERE PID="+pid);
			//create SavePoint
			svpt1=con.setSavepoint("sp1");
			// deduct the amount
			result2=st.executeUpdate("UPDATE JDBC_ACCOUNT SET BALANCE=BALANCE-(SELECT PRICE FROM PRODUCT WHERE PID="+pid+") WHERE ACNO="+acno);
			
			if(result1==1 && result2==1) {
				con.commit();
				System.out.println("Product booking and Payment done  successfully");
			}
			else if(result1==1 && result2==0) {
				con.rollback(svpt1);
				System.out.println("Product booking success, but Payment failed ,SO COD is enabled");
			}
			else { 
				System.out.println("Product booking  and Payment failed");
				con.rollback();
			}
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
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
			
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception se) {
				se.printStackTrace();
			}
		}
		
	}

}
