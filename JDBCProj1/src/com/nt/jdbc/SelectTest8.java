package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest8 {

	public static void main(String[] args) {
Connection con=null;
Statement st=null;
 String query=null;
 ResultSet rs=null;		
                       		try {
			                                               //register  JDBC driver s/w with DriverManager service
			Class.forName("oracle.jdbc.driver.OracleDriver"); //optional
			//establish 
			//the connection
			con=DriverManager.
					 getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create JDbc Statement object
			if(con!=null)
				st=con.createStatement();
			//prepare Query
			query="SELECT COUNT(*) FROM STUDENT";
			//send and excute SQL query in DB s/w
			if(st!=null) 
				rs=st.executeQuery(query);
			//process the ResultSet object
			if(rs!=null) {
				rs.next();
				   // System.out.println("records count::"+rs.getInt(1));
				System.out.println("records count::"+rs.getInt("count(*)"));
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
