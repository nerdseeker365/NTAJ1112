package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PSUpdateTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String newName=null,newAddrs=null;
		float newAvg=0.0f;
		Connection con=null;
		PreparedStatement ps=null;
		String query=null;
		int count=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
			  System.out.println("Enter student Number::");
			  no=sc.nextInt();
			  System.out.println("Enter Student new name::");
			  sc.nextLine();
			  newName=sc.nextLine();  // raja  rao
			  System.out.println("Enter  Student  new address:: ");
			   newAddrs=sc.nextLine();  // new delhi
			  System.out.println("Enter  Student new Avg::");
			   newAvg=sc.nextFloat();  // 89.55
			}//if
			//convert input values as required for the SQL Query
			newName="'"+newName+"'";
			newAddrs="'"+newAddrs+"'";
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			
			//prepare SQL Query
		     //update  student set sname='new raja',sadd='new delhi',avg=90.44 where sno=1012
		    query="UPDATE  STUDENT SET SNAME="+newName+",SADD="+newAddrs+",AVG="+newAvg+" WHERE SNO="+no;
		    System.out.println(query);

			
			//create Statement object
			if(con!=null)
				ps=con.prepareStatement(query);
			//send and execute SQL Query in DB s/w
			    if(ps!=null) 
			    	count=ps.executeUpdate();
			    //process the result
			    if(count==0)
			    	System.out.println("record(s) not found for updation");
			    else
			    	System.out.println("record(s)  found for updation");
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
