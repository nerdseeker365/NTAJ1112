package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetMetaDataTest {
    private static final String  GET_STUDENTS="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int count=0;
		ResultSetMetaData rsmd=null;
		try {
			//register JDBc driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PrepredSatement object
			if(con!=null)
				ps=con.prepareStatement(GET_STUDENTS);
			//send and execute SQL Query
			if(ps!=null)
				rs=ps.executeQuery();
			//create ResultSEt MetaData of object
			if(rs!=null)
				rsmd=rs.getMetaData();
			// get cols count
			if(rsmd!=null)
			count=rsmd.getColumnCount();
			//print col names
			if(rsmd!=null) {
			for(int i=1;i<=count;++i)
				System.out.print(rsmd.getColumnLabel(i)+"   ");
			}
			System.out.println();
			
			if(rsmd!=null) {
				for(int i=1;i<=count;++i)
					System.out.print(rsmd.getColumnTypeName(i)+"   ");
				}
				System.out.println();
				
				
				
			
			
			//oprocess the ResultSEt 
			if(rs!=null) {
				while(rs.next()) {
					for(int i=1;i<=count;++i)
						System.out.print(rs.getString(i)+"  ");
					System.out.println();
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
		}
	
		

	}

}
