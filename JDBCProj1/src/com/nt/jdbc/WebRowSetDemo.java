package com.nt.jdbc;

import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.SQLException;

import oracle.jdbc.rowset.OracleWebRowSet;

public class WebRowSetDemo {

	public static void main(String[] args) {
		OracleWebRowSet wrs=null;
		Writer writer=null;
		
		try {
			wrs=new OracleWebRowSet();
			wrs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			wrs.setUsername("system");
			wrs.setPassword("manager");
			wrs.setCommand("SELECT SNO,SNAME,SADD FROM STUDENT");
			wrs.execute();
			while(wrs.next()) {
				System.out.println(wrs.getInt(1)+"  "+wrs.getString(2)+" "+wrs.getString(3));
			}
			System.out.println("....................");
			writer =new FileWriter("e:/student.xml");
			wrs.writeXml(writer);
			System.out.println(".................");
			writer=new StringWriter();
			wrs.writeXml(writer);
			System.out.println(writer);
			//close stream
			wrs.close();
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main
}//class
