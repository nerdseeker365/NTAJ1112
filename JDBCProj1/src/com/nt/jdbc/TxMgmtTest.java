package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*SQL> select * from JDBC_ACCOUNT;

ACNO HOLDER             BALANCE
---------- --------------- ----------
 101 raja                  9000
 102 ramesh                8000
*/
public class TxMgmtTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int srcAcno=0,destAcno=0;
		float amount=0.0f;
		Connection con=null;
		Statement st=null;
		int result[]=null;
		boolean flag=false;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Source Account number::");
				srcAcno=sc.nextInt();
				System.out.println("Enter Dest Account number::");
				destAcno=sc.nextInt();
				System.out.println("Enter Amount to transfer::");
				amount=sc.nextFloat();
			}
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Statement object
			 if(con!=null)
			   st=con.createStatement();
			 //Begin Tx (Disable AutoCommit mode)
			 if(con!=null)
			   con.setAutoCommit(false);
			 //add withdraw, deposite queries to the batch and execute the batch
			 if(st!=null) {
				 st.addBatch("UPDATE JDBC_ACCOUNT SET BALANCE=BALANCE-"+amount+" WHERE ACNO="+srcAcno); //withdraw query
				 st.addBatch("UPDATE JDBC_ACCOUNT SET BALANCE=BALANCE+"+amount+" WHERE ACNO="+destAcno); //deposite query
			 }
			 //exeucte the batch
			 if(st!=null)
				 result=st.executeBatch();
			 //perform Tx Mgmt
			 if(result!=null) {
				for(int i=0;i<result.length;++i)
					if(result[i]==0) {
						flag=true;
						break;
					}
			 }
			 
			 if(flag==true) {
				 con.rollback();
				 System.out.println("Tx rolled back, Money not transfered");
			 }
			 else {
				 con.commit();
				 System.out.println("Tx Committed, Money  transfered");
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
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
