

import java.sql.*;
import java.util.*;

public class SelectTest4
{
	public  static  void  main(String args[])throws Exception{
		 //read inputs
		 Scanner sc=new Scanner(System.in);
		 System.out.println("Enter  initial chars of emp name:");
		 String initChars=sc.next();  //gives S
		 //convert  input values as required for the SQL Query
		 initChars="'"+initChars+"%'";  //gives 'S%'
		 //register JDBC driver s/w
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //establish the connection
		 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		 //create Statement obj
		 Statement st=con.createStatement();
		 //prepare SQL Query 
		      // select ename,job,sal,empno from emp where ename like 'S%'
		 String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE "+initChars;
		 System.out.println(query);
		 //send and execute SQL query in DB s/w
		   ResultSet rs=st.executeQuery(query);
		   //process the ResultSet obj
		   boolean flag=false;
		   while(rs.next()){
			   flag=true;
			   System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"    "+rs.getInt(4));
		   }

		   if(flag==false)
			    System.out.println("Records not found");
		   else
			    System.out.println("Records  found");

		   //close jdbc objs
		    rs.close();
			st.close();
			con.close();
	}//main
}//class
//>javac SelectTest4.java
//>java SelectTest4
