package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {

	public static void main(String[] args) {
		  Scanner sc=null;
		  int no=0;
		  String name=null,addrs=null;
		  float avg=0.0f;
		  Connection con=null;
		  Statement st=null;
		  String query=null;
		  int count=0;
		  try {
			  //read inputs
			  sc=new Scanner(System.in);
			  if(sc!=null) {
				  System.out.println("Enter Student number::");
				  no=sc.nextInt();  //567
				  System.out.println("enter student name::");
				  name=sc.next(); // raja
				  System.out.println("enter Student address::");
				  addrs=sc.next(); // hyd
				  System.out.println("Enter student avg::");
				  avg=sc.nextFloat(); //89.55
			  }
			  //convert input vlaues as required for SQL Query
			  name="'"+name+"'";  //gives 'raja'
			  addrs="'"+addrs+"'"; //gives  'hyd'
			  
			  //register JDBC drive rs/w
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  //Establish the connection
			  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			  //create STatement object
			  if(con!=null)
				  st=con.createStatement();
			  //prepare  SQL Query
			         //insert into student values(101,'raja','hyd',90.4)
			    query="INSERT INTO STUDENT VALUES("+no+","+name+","+addrs+","+avg+")";
			    System.out.println(query);
			    //send execute SQL Query in DB s/w
			    if(st!=null)
			    	  count=st.executeUpdate(query);
			    //process the
			    if (count==1)
			    	 System.out.println("Record inserted");
		  }//try
		  catch(SQLException se) {
			  if(se.getErrorCode()==1)
				  System.out.println("student already registered with this number");
			  else if(se.getErrorCode()==12899)
				  System.out.println("Given value size is bigger than column size");
			  else if(se.getErrorCode()==947)
				  System.out.println("All  values are not supplied ");
			  else if(se.getErrorCode()>=900 && se.getErrorCode()<=1000)
				  System.out.println("Query syntax problem ");
			  else
				  System.out.println("Unknown DB problem ");
			  se.printStackTrace();
		  }
		  catch(ClassNotFoundException cnf) {
			  System.out.println("Problem in driver loading");
			  cnf.printStackTrace();
		  }
		  catch(Exception e) {
			  System.out.println("Unknown internal problem");
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
