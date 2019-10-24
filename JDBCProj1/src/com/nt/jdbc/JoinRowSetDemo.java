package com.nt.jdbc;

import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.SQLException;

import oracle.jdbc.rowset.OracleCachedRowSet;
import oracle.jdbc.rowset.OracleJoinRowSet;
import oracle.jdbc.rowset.OracleWebRowSet;

public class JoinRowSetDemo {

	public static void main(String[] args) {
		OracleJoinRowSet jrs=null;
		OracleCachedRowSet crs1=null,crs2=null;
		Writer writer=null;
		
		try {
		
			//Cached Rowset1
			crs1=new OracleCachedRowSet();
			crs1.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			crs1.setUsername("system");
			crs1.setPassword("manager");
			crs1.setCommand("SELECT EMPNO,ENAME,JOB,DEPTNO FROM EMP");
			crs1.setMatchColumn(4);
			crs1.execute();
			//Cached Rowset2
			crs2=new OracleCachedRowSet();
			crs2.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			crs2.setUsername("system");
			crs2.setPassword("manager");
			crs2.setCommand("SELECT DEPTNO,DNAME,LOC FROM DEPT");
			crs2.setMatchColumn(1);
			crs2.execute();
			//create JoinRowSet
			jrs=new OracleJoinRowSet();
			jrs.addRowSet(crs2);
			jrs.addRowSet(crs1);
			//jrs.execute();
			//process Rowset
			while(jrs.next()) {
				System.out.println(jrs.getInt(1)+" "+jrs.getString(2)+" "+jrs.getString(3)+" "+jrs.getString(4)+"  "+jrs.getString(5));
			}
			//close stream
			jrs.close();
			crs1.close();
			crs2.close();
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main
}//class
