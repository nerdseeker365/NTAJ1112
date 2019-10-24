


public class SelectTest2
{
	public  static void main(String args[])throws Exception{
		 //read inputs
          Scanner sc=new Scanner(System.in);
		  System.out.println("Enter start range of student number::");
          int start=sc.nextInt();
 		  System.out.println("Enter end range of student number::");
          int end=sc.nextInt();
		  //register JDBC drive rs/w
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  //establish the connection
		  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		  //create STaement object
		  Statement st=con.createStatement();
		  //prepare Query
		         //  select * from student where sno>=100 and sno<=200
		  String query="SELECT * FROM STUDENT WHERE SNO>="+start+" AND SNO<="+end;
		  System.out.println(query);
		  //send and execute SQL query in DB s/w
		  ResultSet rs=st.executeQuery(query);




	}
}