package com.nt.jdbc;

import java.io.Writer;
import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.Predicate;

import oracle.jdbc.rowset.OracleFilteredRowSet;


class Filter1 implements Predicate{
	private String condData;
	
	public Filter1(String condData) {
	     this.condData=condData;
	}
	

	@Override
	public boolean evaluate(RowSet rs) {
		System.out.println("bb");
		try {
		if(rs.getString("ENAME").startsWith(condData)) {
			System.out.println("aa");
           return true;		
		}
		else {
			return false;
		}
		}//try
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean evaluate(Object value, int column) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean evaluate(Object value, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
}


public class FilteredRowSetDemo {

	public static void main(String[] args) {
		OracleFilteredRowSet frs=null;
		try {
		
			//Cached Rowset1
			frs=new OracleFilteredRowSet();
			frs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			frs.setUsername("system");
			frs.setPassword("manager");
			frs.setCommand("SELECT EMPNO,ENAME,JOB,DEPTNO FROM EMP");
			//add  Filter
			frs.setFilter(new Filter1("A"));
			frs.execute();
			//process Rowset
			while(frs.next()) {
				System.out.println(frs.getInt(1)+" "+frs.getString(2)+" "+frs.getString(3)+" "+frs.getInt(4));
			}
			//close stream
			frs.close();
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main
}//class
