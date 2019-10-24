
import java.sql.*;

public class SelectTest1
{
	public static void main(String args[])throws Exception{
		//regsiter JDBC driver s/w with DriverMAnager SErvice (optional)
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //Establish the connection
		 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		 //create JDBC sTatement obj
		 Statement st=con.createStatement();
		 //send and execute  SQL Query in Db s/w
		 ResultSet rs=st.executeQuery("SELECT *  FROM STUDENT");
		 //process the ResultSet 
		 while(rs.next()!=false){
             //System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			 System.out.println(rs.getInt("sno")+"  "+rs.getString("sname")+"  "+rs.getString("sadd")+"  "+rs.getFloat("avg"));
		 }

		 System.out.println("con obj class name"+con.getClass());
		 System.out.println("st obj class name"+st.getClass());
		 System.out.println("rs obj class name"+rs.getClass());



		 //close jdbc objs
		  rs.close();
			 st.close();
		 con.close();
	}//main
}//class