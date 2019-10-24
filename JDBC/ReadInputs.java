import java.io.*;
import  java.util.*;

public  class   ReadInputs
{
	public static void main(String[] args) throws Exception
	{
		//using cmd line args
		String  name1=args[0];

		//Using System Property
		String name2=System.getProperty("my.name");
		
		//Using Scaner
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter name3::");
		String name3=sc.next();

		//Using  Console
		Console cons=System.console();
		System.out.println("Etner name4::");
		String name4=cons.readLine();
		
		//Using IO Strems
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter name5:");
		String name5=br.readLine();

          System.out.println(name1+"  "+name2+"  "+name3+"  "+name4+"  "+name5);

	}//main
}//class
//>javac  ReadInputs.java
//>java    -Dmy.name=raja2  ReadInputs    raja1

