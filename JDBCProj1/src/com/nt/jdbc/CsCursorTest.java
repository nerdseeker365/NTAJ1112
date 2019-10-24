package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/*create or replace procedure p_getStudDetailsByInitialChars(initChars in varchar,
        details out sys_refcursor)
as
begin
open details for
select sno,sname,sadd,avg  from student where  sname like initChars;
end ;
/
*/

public class CsCursorTest {
   private static final String  CALL_QUERY="{call  p_getStudDetailsByInitialChars(?,?) } ";
	public static void main(String[] args) {
		Scanner sc=null;
		String initChars=null;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		boolean flag=false;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Student initialChars");
				initChars=sc.next();
			}
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Callalble Statement object
			if(con!=null)
				cs=con.prepareCall(CALL_QUERY);
			//register OUT params with JDBC data types
			if(cs!=null)
				  cs.registerOutParameter(2,OracleTypes.CURSOR);
			//set values to Query IN params
			if(cs!=null)
				cs.setString(1,initChars+"%");
			//call PL/SQL procedure
			   if(cs!=null)
				   cs.execute();
			   //gather results from OUT Params
			   if(cs!=null)
				   rs=(ResultSet)cs.getObject(2);
			   
			   //process the ResultSet obj
			   if(rs!=null) {
				   System.out.println("Student details whose name starts with "+initChars );
				   while(rs.next()) {
					   flag=true;
					   System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+" "+rs.getFloat(4));
				   }//while
				   if(flag==false)
					   System.out.println("Record not found");
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
				if(cs!=null)
					cs.close();
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
			
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}//finally

	}//main
}//class
