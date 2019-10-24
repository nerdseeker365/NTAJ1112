package com.nt.jdbc;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleCachedRowSet;

public class CachedRowSetDemo {

	public static void main(String[] args) {
		OracleCachedRowSet  crs=null;
		try {
			crs=new OracleCachedRowSet();
			crs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			crs.setUsername("system");
			crs.setPassword("manager");
			
			crs.setCommand("SELECT EMPNO,ENAME,JOB,SAL FROM EMP");
			//execute Query
			crs.execute();
			//process RowSet
			while(crs.next()) {
				System.out.println(crs.getInt(1)+"  "+crs.getString(2)+"  "+crs.getString(3)+"  "+crs.getFloat(4));
			}
			//enable wrtie operations
			System.out.println(".......");
			System.out.println("stop Db s/w");
			Thread.sleep(30000);  // Stop DB s/w during this sleep period
			
			crs.absolute(4);
			crs.updateString(3,"china12");
			crs.updateRow();
			
			System.out.println("start DB s/w");
			Thread.sleep(40000);  // Start DB s/w during this sleep period
			 crs.acceptChanges();
               
             while(crs.next()) {
   				System.out.println(crs.getInt(1)+"  "+crs.getString(2)+"  "+crs.getString(3)+"  "+crs.getFloat(4));
   			}
               
			//close rowset
			crs.close();
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
