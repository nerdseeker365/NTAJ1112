package com.nt.jdbc;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*create or replace procedure p_find_result(m1 in number,m2 in number,m3 in number ,result out varchar) as
begin
   if(m1<35 or m2<35 or m3<35 )then
       result:='FAIL';
   else
      result:='PASS';
end if;
end;
/
*/

/*SQL> select * from All_student;

SNO SNAME                        M1         M2         M3
---------- -------------------- ---------- ---------- ----------
1001 raja                              90         67         67
1002 ramesh                       40         47         47
1003 lokesh                         30         57         47
1004 sita                               34         54         46
1006 anitha                           67         84         88*/

public class MiniProject2 extends JFrame  implements ActionListener{
	private static final String GET_ALL_SNOS_QUERY="SELECT SNO FROM ALL_STUDENT";
	private  static final String  GET_STUDENT_BY_NO="SELECT SNO,SNAME,M1,M2,M3 FROM ALL_STUDENT WHERE SNO=?";
	private  static final String CALL_QUERY="{CALL P_FIND_RESULT(?,?,?,?) }";
	private JLabel lno,lname,lm1,lm2,lm3,lresult;
	private  JComboBox<Integer>  tno;
	private JTextField  tname,tm1,tm2,tm3,tresult;
	JButton bDetails ,bResult;
	private  Connection con;
	private Statement st;
	private PreparedStatement ps;
	private CallableStatement cs;
	private ResultSet rs1=null,rs2=null;
	
	public MiniProject2() {
		super("Mini Project using All the 3 statement objects");
		setLayout(new FlowLayout());
		setSize(300, 400);
		lno=new JLabel("student number::");
		add(lno);
		tno=new JComboBox<Integer>();
		add(tno);
		
		bDetails=new JButton("details");
		bDetails.addActionListener(this);
		add(bDetails);
		
		lname=new JLabel("student name");
		add(lname);
		tname=new JTextField(10);
		add(tname);
		
		lm1=new JLabel("Marks1");
		add(lm1);
		tm1=new JTextField(10);
		add(tm1);
		
		lm2=new JLabel("Marks2");
		add(lm2);
		tm2=new JTextField(10);
		add(tm2);
		
		lm3=new JLabel("Marks3");
		add(lm3);
		tm3=new JTextField(10);
		add(tm3);
		
		bResult=new JButton("result");
		bResult.addActionListener(this);
		add(bResult);
		
		lresult=new JLabel("result is");
		add(lresult);
		tresult=new JTextField(10);
		add(tresult);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jdbcInitialize();
		// addWindow Listener to frame
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("MiniProject2.windowClosing()");
				System.out.println("MiniProject.windowClosing()");
				//close jdbc objs
				try {
					if(rs1!=null)
						rs1.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				
				try {
					if(rs2!=null)
						rs2.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				
				try {
					if(st!=null)
						st.close();
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
					if(cs!=null)
						cs.close();
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
		});
		
		//disable editing of text box values
		tname.setEditable(false);
		tm1.setEditable(false);
		tm2.setEditable(false);
		tm3.setEditable(false);
		tresult.setEditable(false);
	}
	
	private  void  jdbcInitialize() {
		try {
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			//create Statement object
			st=con.createStatement();
			//write load on starup logic  to get snos and to store them ComboBox 
			rs1=st.executeQuery(GET_ALL_SNOS_QUERY);
			while(rs1.next()) {
				tno.addItem(rs1.getInt(1));
			}
			//create PreparedStatement object
			ps=con.prepareStatement(GET_STUDENT_BY_NO);
			//create CallableStastement object
			cs=con.prepareCall(CALL_QUERY);
			cs.registerOutParameter(4,Types.VARCHAR);
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//jdbcInitialize
	

	public static void main(String[] args) {
		  new MiniProject2();
		

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("MiniProject.actionPerformed()");
		int no=0;
		int m1=0,m2=0,m3=0;
		if(ae.getSource()==bDetails) {
			System.out.println("deails button is clicked");
			try {
			//get selected item from combobox
			no=(int) tno.getSelectedItem();
			//set recived number as PS query parameter
			ps.setInt(1,no);
			//execute the query
			  rs2=ps.executeQuery();
			  //copy the record of ResultSet obj to text boxes
			  if(rs2.next()) {
				  tname.setText(rs2.getString(2));
				  tm1.setText(String.valueOf(rs2.getInt(3)));
				  tm2.setText(String.valueOf(rs2.getInt(4)));
				  tm3.setText(String.valueOf(rs2.getInt(5)));
			  }
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("result button is cliked");
			try {
				//read mark values fromt text boxes 
				m1=Integer.parseInt(tm1.getText());
				m2=Integer.parseInt(tm2.getText());
				m3=Integer.parseInt(tm3.getText());
				//set marks to IN param values
				cs.setInt(1, m1);
				cs.setInt(2, m2);
				cs.setInt(3, m3);
				//call PL/SQL procedure
				cs.execute();
				//gather results from out param and set to text box
				tresult.setText(cs.getString(4));
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//else
	}//actionPerformed(-)
	
	
}//class
