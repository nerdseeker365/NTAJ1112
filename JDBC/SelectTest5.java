//SelectTest5.java
package com.nt.jdbc;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest5
{
	public static void main(String args[]){
         Scanner sc=null;
		 String desg1=null,desg2=null,desg3=null;
		 String cond=null;
		 Connection con=null;
		 Statement st=null;
		 String query=null;
		 ResultSet rs=null;
		 boolean flag=false;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter Employee Desg1::");
				 desg1=sc.next();  //gives CLERK
				 System.out.println("Enter Employee Desg2::");
				 desg2=sc.next();  //gives MANAGER
 				 System.out.println("Enter Employee Desg3::");
				 desg3=sc.next();  //gives  SALESMAN
			}//if
			//convert  input values as required for  SQL condition
                                      //('CLERK','MANAGER','SALESMAN')
                 cond="('"+desg1+"','"+desg2+"','"+desg3+"')";
				 System.out.println(cond);
			 //register  JDBC driver s/w
			 Class.forName("oracle.jdbc.driver.OracleDriver1");
			 //establish the connection with DB s/w
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			 //create Statemet object
			 if(con!=null)
                   st=con.createStatement();
			 //prepare SQL Query
			           // SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN('CLERK','MANAGER','SALESMAN') ORDER BY JOB
               query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN"+cond +" ORDER BY JOB";
			 //send and execute SQL query in DB s/w
			 if(st!=null)
				   rs=st.executeQuery(query);
			 //process the  ResultSet obj
			       if(rs!=null){
					   while(rs.next()){
						     flag=true;
                            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+" "+rs.getInt(4));
					   }//while

					   if(flag==false)
						   System.out.println("No records found ");
				   }//if
				   
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){
		  System.out.println(cnf.toString());
		}
		catch(Exception e){
              e.printStackTrace();
		}
		finally{
            try{
				if(rs!=null)
					rs.close();
			}//try
			catch(SQLException se){
                    se.printStackTrace();
			}

			try{
				if(st!=null)
					st.close();
			}//try
			catch(SQLException se){
                    se.printStackTrace();
			}

			try{
				if(con!=null)
					con.close();
			}//try
			catch(SQLException se){
                    se.printStackTrace();
			}
    	}//finally
	}//main
}//class
//>javac -d   .   SelectTest5.java
//>java  com.nt.jdbc.SelectTest5
