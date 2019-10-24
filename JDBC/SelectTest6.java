//SelectTest6.java
package com.nt.jdbc;


import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class  SelectTest6
{
	public static void main(String[] args) 
	{
		Scanner sc=null;
		int deptno=0;
		String query=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter Dept Number:");
				deptno=sc.nextInt();
			}//if

				 //register  JDBC driver s/w
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 //establish the connection with DB s/w
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			 //create Statemet object
			 if(con!=null)
                   st=con.createStatement();
			 //prepare SQL Query
                   query="SELECT * FROM DEPT WHERE DEPTNO="+deptno;
            //send execute the SQL Query
			  if(st!=null)
                         rs=st.executeQuery(query);
			  //process the ResultSet
			  if(rs!=null){
                   if(rs.next()){
                          System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
				   }
				   else{
					   System.out.println("Dept  not found ");
				   }
			  }//if
		}//try
		catch(SQLException se){
                se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){
              cnf.printStackTrace();
		}
		catch(Exception e){
			  e.printStackTrace();
		}
		finally{
			try{
             if(rs!=null)
				 rs.close();
			}
			catch(SQLException se){
				  se.printStackTrace();
			}
			try{
             if(st!=null)
				 st.close();
			}
			catch(SQLException se){
				  se.printStackTrace();
			}

			try{
             if(con!=null)
				 con.close();
			}
			catch(SQLException se){
				  se.printStackTrace();
			}

			try{
             if(sc!=null)
				 sc.close();
			}
			catch(Exception e){
				  e.printStackTrace();
			}
		}//finally
	}//main
}//class
//>javac  -d   .  SelectTest6.java
//>java  com.nt.jdbc.SelectTest6

