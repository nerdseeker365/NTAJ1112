package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableRsTest1 {
  private static final String  GET_STUDENTS_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//register JDBC driver   s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create JDBC Statement object with type,mode values
			ps=con.prepareStatement(GET_STUDENTS_QUERY,
					                                          ResultSet.TYPE_SCROLL_INSENSITIVE,
					                                          ResultSet.CONCUR_READ_ONLY);
		
			//send execute SQL Query in DB s/w
			if(ps!=null)
				 rs=ps.executeQuery();
			
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
		}//finally
	}//main
}//class
