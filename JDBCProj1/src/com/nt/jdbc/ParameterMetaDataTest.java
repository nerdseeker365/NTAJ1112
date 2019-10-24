package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ParameterMetaDataTest {
   private static final String  INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ParameterMetaData pmd=null;
		int  count=0;
		try {
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PrepredStatement object
			if(con!=null)
				ps=con.prepareStatement(INSERT_QUERY);
			//create ParameterMetaData object
			if(ps!=null)
				pmd=ps.getParameterMetaData();
			//get Parameter count
			if(pmd!=null) {
				count=pmd.getParameterCount();
				System.out.println("parameters count::"+count);
				//get parameter details
				for(int i=1;i<=count;++i) {
					System.out.println("parameter number::"+i);
					System.out.println("pameter mode::"+pmd.getParameterMode(i));
					System.out.println("pameter type name::"+pmd.getParameterTypeName(i));
					System.out.println("parameter scale ::"+pmd.getScale(i));
					System.out.println("parmeter precision::"+pmd.getPrecision(i));
					System.out.println("pameter is nullable?"+pmd.isNullable(i));
				}//for
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


	}//main
}//class
