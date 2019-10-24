package com.nt.basics;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateConversionTest {

	public static void main(String[] args) throws Exception{
		
		//converting String date value to  java.util.Date class object
		SimpleDateFormat sdf=null;
		String d1="45-24-1990"; //dd-MM-yyyy
		sdf=new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date ud1=sdf.parse(d1);
		System.out.println("util date"+ud1);
		//converting java.util.Date class obj to java.sql.Date class obj
		long ms=ud1.getTime();
		java.sql.Date  sd1=new java.sql.Date(ms);
		System.out.println("sql date"+sd1);
		//converting  string date values directly  java.sql.Date class obj
		//with out converting it to  java.util.Date class obj but  the
		//date pattern must be yyyy-MM-dd
		String d2="1947-08-15"; //yyyy-MM-dd
		java.sql.Date sd2=java.sql.Date.valueOf(d2);
		System.out.println("sql date2::"+sd2);
		
		//get system Date
		java.util.Date ud2=new java.util.Date();
		System.out.println("sysDate (ud)"+ud2);
		java.sql.Date sd3=new java.sql.Date(ud2.getTime());
		System.out.println("sql date3::"+sd3);
		
		//converting java.sql.Date class obj to java.util.Date class obj
		java.util.Date ud3=(java.util.Date)sd3;
		System.out.println("util date(ud3)"+ud3);
		//converting java.util.Date class obj to String date values
		SimpleDateFormat  sdf2=new SimpleDateFormat("yyyy-MMM-dd");
		String d3=sdf2.format(ud3);
		System.out.println("String date value::"+d3);
		
		
		
		

	}

}
