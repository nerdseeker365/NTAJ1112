package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*create or replace procedure  p_first_add(x in number, y in number, z out number)as
begin 
    z:=x+y;
end;
/
*/

public class CsTest1 {
  private static final String  CALL_QUERY="{ CALL p_first_add(?,?,?) } ";
	public static void main(String[] args) {
		Scanner sc=null;
		int val1=0,val2=0;
		Connection con=null;
		CallableStatement cs=null;
		int result=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("enter value1::");
				val1=sc.nextInt();
				System.out.println("enter value2::");
				val2=sc.nextInt();
			}
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			//create CallableStatement object
			if(con!=null)
				cs=con.prepareCall(CALL_QUERY);
			//register OUT params with JDBC Data types
			if(cs!=null)
				cs.registerOutParameter(3,Types.INTEGER);
			//set values to IN params
			if(cs!=null) {
				cs.setInt(1,val1);
				cs.setInt(2,val2);
			}
			//call PL/SQL procedure
			if(cs!=null)
				cs.execute();
			//gather results from OUT params
			if(cs!=null)
				result=cs.getInt(3);
			
			System.out.println("Result is:::"+result);
		}
		catch(SQLException se) {
			System.out.println(se.getErrorCode());
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
	}///main
}//class
