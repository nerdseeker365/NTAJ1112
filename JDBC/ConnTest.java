
import java.sql.*;
public class ConnTest  
{
	public static void main(String ags[])throws Exception{
             //load jdbc driver class
			  Class.forName("oracle.jdbc.driver.OracleDriver");

        //Establish the connection 
		    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");

               System.out.println("jdbc con obj class name::"+con.getClass());

            //check the connection
			  if(con==null)
				     System.out.println("Connection is not established");
			  else
                   System.out.println("Connection is  established");
 	}//main
}//class
//>javac ConnTest.java
//>java  ConnTest

