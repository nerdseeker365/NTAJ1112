package com.nt.jdbc;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIScrollableRSApp extends JFrame implements ActionListener{
	private static final String GET_ALL_STUDENTS="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	private JLabel  lno,lname,laddrs,lavg;
	private  JTextField tno,tname,taddrs,tavg;
	private JButton bFirst,bNext,bPrevious,bLast;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs; 
	
	public GUIScrollableRSApp() {
		System.out.println("GUIScrollableRSApp.0-param constructor");
		setTitle("ScrollFrame App");
		setLayout(new FlowLayout());
		setSize(300, 300);
		//add comps
		lno=new JLabel("student number::");
		add(lno);
		tno=new JTextField(10);
		add(tno);
		
		lname=new JLabel("student name::");
		add(lname);
		tname=new JTextField(10);
		add(tname);
		
		laddrs=new JLabel("student Addrs::");
		add(laddrs);
		taddrs=new JTextField(10);
		add(taddrs);
		
		lavg=new JLabel("student Average::");
		add(lavg);
		tavg=new JTextField(10);
		add(tavg);
		
		bFirst=new JButton("first");
		bFirst.addActionListener(this);
		add(bFirst);
		bNext=new JButton("next");
		bNext.addActionListener(this);
		add(bNext);
		bPrevious=new JButton("previous");
		bPrevious.addActionListener(this);
		add(bPrevious);
		bLast=new JButton(" Last");
		bLast.addActionListener(this);
		add(bLast);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jdbcInitialize();
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("GUIScrollableRSApp.GUIScrollableRSApp().new WindowAdapter() {...}.windowClosing()");
				//close jdbc objs
				try {
					if(rs!=null)
						rs.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				try {
					if(ps!=null)
						ps.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				try {
					if(con!=null)
						con.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}); //method call
	}//constructor
	
	private  void  jdbcInitialize() {
		System.out.println("GUIScrollableRSApp.jdbcInitialize()");
		try {
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PreparedStatement obj having type, mode
			ps=con.prepareStatement(GET_ALL_STUDENTS,
					                                          ResultSet.TYPE_SCROLL_SENSITIVE,
					                                          ResultSet.CONCUR_UPDATABLE);
			//create Scrollable ResultSet obj
			rs=ps.executeQuery();
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//jdbcInitialize
	

	public static void main(String[] args) {
		System.out.println("GUIScrollableRSApp.main()");
		new GUIScrollableRSApp();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("GUIScrollableRSApp.actionPerformed(-)");
		boolean flag=false;
		try {
		if(ae.getSource()==bFirst) {
			System.out.println("GUIScrollableRSApp.actionPerformed()-->bFirst");
			rs.first();
			flag=true;
		}
		else if(ae.getSource()==bNext) {
			System.out.println("GUIScrollableRSApp.actionPerformed()-->bNext");
			if(!rs.isLast()) {
				rs.next();
				flag=true;
			}
		}
		else if(ae.getSource()==bPrevious) {
			System.out.println("GUIScrollableRSApp.actionPerformed()-->bPrevious");
			if(!rs.isFirst()) {
				rs.previous();
				flag=true;
			}
		}
		else {
			System.out.println("GUIScrollableRSApp.actionPerformed()-->bLast");
				rs.last();
				flag=true;
		}
		
		 if(flag==true) {
			 //write record values to Text boxes
			 tno.setText(rs.getString(1));
			 tname.setText(rs.getString(2));
			 taddrs.setText(rs.getString(3));
			 tavg.setText(rs.getString(4));
		 }
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		
	}//actionPerformed(-)
}//class
