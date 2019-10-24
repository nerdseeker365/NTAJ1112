package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsTest {

	public static void main(String[] args) {
		Properties props=null;
		InputStream is=null;
		try {
			//Locate properties file
			is=new FileInputStream("src\\com\\nt\\commons\\info.properties");
			//Load properties file content  to java.util.Properties class object
			props=new Properties();
			props.load(is);
			
			System.out.println("props data :::"+props);
			System.out.println("name key value::"+props.getProperty("name"));
		}//try
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
