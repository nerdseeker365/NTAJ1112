package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableRsTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//register JDBC driver   s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create JDBC Statement object with type,mode values
		/*	st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					                                    ResultSet.CONCUR_UPDATABLE); */
			 // st=con.createStatement(1004,1007);
			st=con.createStatement();
			//send execute SQL Query in DB s/w
			if(st!=null)
				rs=st.executeQuery("SELECT SNO,SNAME,SADD,AVG FROM STUDENT");
			//process the ResultSet  (top-Bottom)
			System.out.println("Top --- Bottom");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			}
			System.out.println("......................................");
			System.out.println("Bottom--- TOP");
			while(rs.previous()) {
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			}
			//display records randomly
			rs.last();
			System.out.println(rs.getRow()+" record::"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			rs.first();
			System.out.println(rs.getRow()+" record::"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			rs.absolute(3);
			System.out.println(rs.getRow()+" record::"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			rs.relative(1);
			System.out.println(rs.getRow()+" record::"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			rs.relative(-2);
			System.out.println(rs.getRow()+" record::"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			rs.absolute(-4);
			System.out.println(rs.getRow()+" record::"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
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
