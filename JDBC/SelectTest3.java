
import  java.sql.*;
import java.util.*;


public class SelectTest3
{
	public  static void main(String args[])throws Exception{
		 //read inputs
          Scanner sc=new Scanner(System.in);
		  System.out.println("Enter city name::");
          String city=sc.next();  //gives hyd
		  city="'"+city+"'"; //gives 'hyd'

		  //register JDBC drive rs/w
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  //establish the connection
		  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		  //create STaement object
		  Statement st=con.createStatement();
		  //prepare Query
		         //  select * from student where sadd='hyd'
		  String query="SELECT * FROM STUDENT WHERE SADD="+city;
		  System.out.println(query);
		  //send and execute SQL query in DB s/w
		  ResultSet rs=st.executeQuery(query);
		  //process the ResultSet obj
		  boolean flag=false;
		  while(rs.next()){
			  flag=true;
			  System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
		  }

		  if(flag==false)
			   System.out.println("Records not found");

		  //close jdbc objs
		  rs.close();
		  st.close();
		  con.close();
	}
}