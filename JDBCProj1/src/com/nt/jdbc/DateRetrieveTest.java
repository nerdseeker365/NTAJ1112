package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class DateRetrieveTest {
  private  static final String  GET_DATES_QUERY="SELECT PID,PNAME,DOB,DOJ,DOM  FROM PERSON_TAB";
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int no=0;
		String name=null;
	    java.sql.Date sdob=null,sdoj=null,sdom=null;
	    java.util.Date udob=null,udoj=null,udom=null;
	    SimpleDateFormat sdf=null;
	    String dob=null,doj=null,dom=null;
		
		try {
		/*	//register JDBC driver s/w
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  //establish the connection
			  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager"); */
			
			//register JDBC driver s/w
			  Class.forName("com.mysql.cj.jdbc.Driver");
			  //establish the connection
			  con=DriverManager.getConnection("jdbc:mysql:///ntaj1112DB", "root","root"); 
			
			  //create Statement object
			  if(con!=null)
				  st=con.createStatement();
			  //send and execute SQL Query in DB s/w
			   if(st!=null)
				   rs=st.executeQuery(GET_DATES_QUERY);
			   //process the ResultSet
			   if(rs!=null) {
				   while(rs.next()) {
					   no=rs.getInt(1);
					   name=rs.getString(2);
					   sdob=rs.getDate(3);
					   sdoj=rs.getDate(4);
					   sdom=rs.getDate(5);
					   //convert java.sql.Date class objs to java.util.Date class objs
					   udob=(java.util.Date) sdob;
					   udoj=(java.util.Date) sdoj;
					   udom=(java.util.Date) sdom;
					 //convert java.util.Date class objs to String Date vlaues (MMM-yyyy-dd)
					   sdf=new SimpleDateFormat("MMM-yyyy-dd");
					   dob=sdf.format(udob);
					   doj=sdf.format(udoj);
					   dom=sdf.format(udom);
					   System.out.println(no+"  "+name+"  "+ dob+"   "+doj+"   "+dom);
				   }//while
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
			//close jdbc objs
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
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
		}//finally
	}//main
}//class
