package com.nt.jdbc;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleJDBCRowSet;

public class JDBCRowSetDemo {

	public static void main(String[] args) {
		OracleJDBCRowSet  jrs=null;
		try {
		    //jdbc RowSet object
			jrs=new OracleJDBCRowSet();
			jrs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			jrs.setUsername("system");
			jrs.setPassword("manager");
			jrs.setCommand("SELECT * FROM STUDENT");
			jrs.execute();
			//process the ResultSet
			while(jrs.next()) {
				System.out.println(jrs.getInt(1)+"  "+jrs.getString(2)+"  "+jrs.getString(3)+"  "+jrs.getFloat(4));
			}
				
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}//main
}//class
