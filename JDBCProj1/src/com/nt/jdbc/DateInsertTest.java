package com.nt.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateInsertTest {
  private static final String  PERSON_INSERT_QUERY="INSERT INTO PERSON_TAB VALUES(?,?,?,?,?)";
	public static void main(String[] args) {
       Scanner sc=null;
       int pid=0;
       String pname=null,dob=null,doj=null,dom=null;
       Connection con=null;
        PreparedStatement ps=null;
        SimpleDateFormat sdf1=null,sdf2=null;
        java.util.Date udob=null,udoj=null;
        java.sql.Date sdob=null,sdoj=null,sdom=null;
        int count=0;
        
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("enter PErosn Id::");
				pid=sc.nextInt();
				System.out.println("Enter Person name::");
				pname=sc.next();
				System.out.println("enter DOB(dd-MM-yyyy)::");
				dob=sc.next();
				System.out.println("enter DOJ(MM-dd-yyyy)::");
				doj=sc.next();
				System.out.println("enter DOM(yyyy-MM-dd)::");
				dom=sc.next();
			}
	/*		//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager"); */
			//register JDBC driver s/w
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj1112DB","root","root");
			
			
			
			//create PreparedStatement obj having pre-compiled SQL Query 
			if(con!=null)
				ps=con.prepareStatement(PERSON_INSERT_QUERY);
			 // converting String date values to  java.util.Date class objs
			     //for DOB
			        sdf1=new SimpleDateFormat("dd-MM-yyyy");
			        udob=sdf1.parse(dob);
			      //for DOJ
			        sdf2=new SimpleDateFormat("MM-dd-yyyy");
			        udoj=sdf2.parse(doj);
			   //converting java.util.Date class objs to java.sql.Date class objs     
			        sdob=new java.sql.Date(udob.getTime());
			        sdoj=new java.sql.Date(udoj.getTime());
			     //convert String date value (DOM) directly to java.sql.Date class obj
			        sdom=java.sql.Date.valueOf(dom);
			        //set values to Query params
			        if(ps!=null) {
			        	ps.setInt(1, pid);
			        	ps.setString(2,pname);
			        	ps.setDate(3,sdob);
			        	ps.setDate(4,sdoj);
			        	ps.setDate(5,sdom);
			        }
			        //execute the SQL Query
			        if(ps!=null) 
			          count=ps.executeUpdate();
			        
			        //process the Results
			        if(count==0)
			        	  System.out.println("Record not inserted");
			        else
			        	System.out.println("Record inserted");
			}//try
		catch(SQLException se) {
			System.out.println("record not inserted");
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
