package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIFrontEndStudentRegistrationApp extends JFrame implements ActionListener,WindowListener {
	private static final String INSERT_QUERY="INSERT INTO STUDENT VALUES(SID_SEQ.NEXTVAL,?,?,?)";
	private JLabel  lname,laddrs,lavg,lresult;
	private JTextField  tname,taddrs,tavg;
	private  JButton  register;
	private Connection con;
 private PreparedStatement ps;
	
	
	public GUIFrontEndStudentRegistrationApp() {
		super("Student Registration Window");
		System.out.println("GUIFrontEndStudentRegistrationApp:0-param constructor");
		setSize(300, 200);
		setLayout(new FlowLayout());
		//add comps
		lname=new JLabel("student name::");
		add(lname);
		tname=new JTextField(10);
		add(tname);
		laddrs=new JLabel("student Address::");
		add(laddrs);
		taddrs=new JTextField(10);
		add(taddrs);
		lavg=new JLabel("student Marks Avg::");
		add(lavg);
		tavg=new JTextField(10);
		add(tavg);
		
		register=new JButton("Student Registration");
		add(register);
		register.addActionListener(this);
		
		lresult=new JLabel("");
		add(lresult);
		
		setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //add WindowListener  framewindow
	    this.addWindowListener(this);
	    jdbcInitialize();
	}
	
	private  void jdbcInitialize() {
		System.out.println("GUIFrontEndStudentRegistrationApp.jdbcInitialize()");
		try {
			//register JDBC Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			//create PreparedStatement obj
			ps=con.prepareStatement(INSERT_QUERY);
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		  System.out.println("GUIFrontEndStudentRegistrationApp.main() start");
		   new  GUIFrontEndStudentRegistrationApp();
		  System.out.println("GUIFrontEndStudentRegistrationApp.main() end");
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("GUIFrontEndStudentRegistrationApp.actionPerformed(-)");
		String name=null,addrs=null;
		float avg=0.0f;
		int count=0;
		System.out.println(ae.getActionCommand());
		System.out.println(ae.getSource()==register);
		try {
			//read textbox values
			name=tname.getText();
			addrs=taddrs.getText();
			avg=Float.parseFloat(tavg.getText());
			//set these values as query param values
			ps.setString(1,name );
			ps.setString(2,addrs);
			ps.setFloat(3,avg);
			//execute the query
			count=ps.executeUpdate();
			//process the Result
			if(count==0) {
				lresult.setForeground(Color.RED);
				lresult.setText("Registration failed");
			}
			else {
				lresult.setForeground(Color.GREEN);
				lresult.setText("Registration Succeded");
			}
		}//try
		catch(SQLException se) {
			lresult.setForeground(Color.RED);
			lresult.setText("Registration failed");
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}//actionPerfomed(-)

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent we) {
		System.out.println("GUIFrontEndStudentRegistrationApp.windowClosed()");
		
	}

	@Override
	public void windowClosing(WindowEvent we) {
		System.out.println("GUIFrontEndStudentRegistrationApp.windowClosing(-)");
		//close jdbc objs
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

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}//class
