package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*create or replace function  fx_getStudDetails_BySno(no in number,name out varchar,addrs out varchar)return number
as
    aggr float;
begin
     select sname,sadd,avg into name,addrs,aggr from student where sno=no;
return  aggr;
end;
*/
public class CsFunctionTest {
   private static final  String CALL_QUERY= "{?= call  fx_getStudDetails_BySno(?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		
		
		try {
			sc=new Scanner(System.in);
			//read inputs
			if(sc!=null) {
				System.out.println("Enter Student number::");
				no=sc.nextInt();
			}
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			//create CallableStatement object
			if(con!=null)
				cs=con.prepareCall(CALL_QUERY);
			//register return ,OUT params with JDBC data types
			if(cs!=null) {
				cs.registerOutParameter(1,Types.FLOAT); //return Params
				cs.registerOutParameter(3,Types.VARCHAR);  //out param
				cs.registerOutParameter(4,Types.VARCHAR); //out param
			}
			
			//set values to query IN params
			if(cs!=null) 
				cs.setInt(2,no);
			//call PL/SQL Function
			if(cs!=null)
				cs.execute();
			//gather results from OUT params
			if(cs!=null) {
				System.out.println("Student Name ::"+cs.getString(3));
				System.out.println("Student Addrs::"+cs.getString(4));
				System.out.println("Studnet marks Avg::"+cs.getFloat(1));
			}
		}//try
		catch(SQLException se) {
			System.out.println("student not found");
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
