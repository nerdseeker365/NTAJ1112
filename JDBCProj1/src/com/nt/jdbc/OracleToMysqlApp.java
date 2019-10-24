package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleToMysqlApp {
  private static final String MYSQL_STUDENT_INSERT="INSERT INTO STUDENT VALUES(?,?,?,?)";
  private static final String  ORACLE_STUDENT_SELECT="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	public static void main(String[] args) {
		Connection oraCon=null,mysqlCon=null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int no=0;
		float avg=0.0f;
		String name=null,addrs=null;
		
		try {
			//register JDBC driver s/ws
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Establish the connections
			oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			mysqlCon=DriverManager.getConnection("jdbc:mysql:///ntaj1112DB", "root","root");
			//create JDBC STatement objects
			if(oraCon!=null)
			    st=oraCon.createStatement();
			if(mysqlCon!=null)
			     ps=mysqlCon.prepareStatement(MYSQL_STUDENT_INSERT);
			//execute Select Query on Oracle DB s/w
			if(st!=null) 
			  rs=st.executeQuery(ORACLE_STUDENT_SELECT);	
			// process the ResultSet obj(oracle records) and insert records to mysql
			if(rs!=null && ps!=null) {
				while(rs.next()) {
					//get each record from oracle DB s/w (ResultSet)
					 no=rs.getInt(1);
					 name=rs.getString(2);
					 addrs=rs.getString(3);
					 avg=rs.getFloat(4);
					 //set the above values mysql insert query param values
					 ps.setInt(1,no);
					 ps.setString(2,name);
					 ps.setString(3,addrs);
					 ps.setFloat(4,avg);
					 //execute insert Query
					 ps.executeUpdate();
				}//while
				System.out.println("records are copied");
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
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(oraCon!=null)
					oraCon.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(mysqlCon!=null)
					mysqlCon.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally

	}//main
}//class
