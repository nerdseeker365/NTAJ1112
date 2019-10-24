package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*SQL> create table  Railway_Reservation(tktId number(5) primary key,psngrName varchar2(15),source varchar2(10),dest varchar2(10),price float,age number(2));
 * SQL> create sequence tktId_seq start with 1 increment by 1;
*/
public class GroupReservation_PSBatchTest {
  private static final String GROUP_INSERT_QUERY="INSERT INTO RAILWAY_RESERVATION VALUES(TKTID_SEQ.NEXTVAL,?,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		int count=0;
		String srcPlace=null, destPlace=null;
		float fare=0.0f;
		Connection con=null;
		PreparedStatement ps=null;
		String name=null;
		int age=0;
		int result[]=null;
		int sum=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("enter group size::");
				count=sc.nextInt();
				System.out.println("Enter source place::");
				srcPlace=sc.next();
				System.out.println("Enter Dest Place::");
				destPlace=sc.next();
				System.out.println("Enter Fare:::");
				fare=sc.nextFloat();
			}//if
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(GROUP_INSERT_QUERY);
			if(ps!=null) {
				for(int i=1;i<=count;++i) {
					System.out.println("Enter "+i+" passegener name::");
					name=sc.next();
					System.out.println("Enter "+i+" passegener age::");
					age=sc.nextInt();
					//add each passgenger details to query parameters batch
					ps.setString(1,name);
					ps.setString(2,srcPlace);
					ps.setString(3,destPlace);
					ps.setFloat(4,fare);
					ps.setInt(5,age);
					ps.addBatch();
				}//for
			}//if
			//execute the Batch
			if(ps!=null)
				result=ps.executeBatch();
			
			if(result==null)
				System.out.println("Record insertion failed");
			else
				System.out.println("Record insertion succeded");
			
			//find out the sum of element values
			if(ps!=null) {
				for(int i=0;i<result.length;++i) {
					sum=sum+result[i];
				}//for
			}//if
			System.out.println("no.of records effected::"+sum);
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
