package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTestWithTWR {

	public static void main(String[] args) {
		
		//create Connection object 
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager")){
		   	if(con!=null)
			  try(Statement st=con.createStatement()){
		   	        if(st!=null)
		   	        	try(ResultSet rs=st.executeQuery("SELECT * FROM STUDENT")){
		   	        		if(rs!=null) {
		   	        			while(rs.next()) {
		   	        				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
		   	        			}//while
		   	        		}//if
		   	        	}//try3
		     	}//try2
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
