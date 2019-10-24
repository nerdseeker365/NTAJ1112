package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessingTestWithTxMgmt {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		int result[]=null;
		boolean flag=false;
		try {
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Begin Tx
			con.setAutoCommit(false);
			//create JDBC Statement object
			st=con.createStatement();
			//add Queries to the batch
			st.addBatch("INSERT INTO STUDENT VALUES(104,'ramesh','hyd',90.55f)");
			st.addBatch("UPDATE STUDENT SET SADD='PAK1' WHERE SNO<=100");
			st.addBatch("DELETE FROM STUDENT WHERE SNO>=500");
			//execute Batch
			result=st.executeBatch();
			//perform Tx Mgmt
			if(result!=null) {
				for(int i=0;i<result.length;++i)
					if(result[i]==0) {
						flag=true;
						break;
					}
				if(flag) {
					con.rollback();
					System.out.println("Tx rolled back");
				}
				else {
					con.commit();
					System.out.println("Tx Committed");
				}
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
