package com.nt.jdbc;

import java.awt.EventQueue;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GUIScrollFrameApp1 extends JFrame {
   private static final String  GET_ALL_STUDENTS="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIScrollFrameApp1 frame = new GUIScrollFrameApp1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIScrollFrameApp1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("sno");
		lblNewLabel.setBounds(57, 37, 56, 16);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(170, 34, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("sname");
		lblNewLabel_1.setBounds(57, 83, 56, 16);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(170, 80, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("sadd");
		lblNewLabel_2.setBounds(57, 141, 56, 16);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(170, 138, 116, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("avg");
		lblNewLabel_3.setBounds(57, 185, 56, 16);
		contentPane.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(170, 179, 116, 22);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("first");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					rs.first();
					//set record values to text boxes
					textField.setText(rs.getString(1));
					textField_1.setText(rs.getString(2));
					textField_2.setText(rs.getString(3));
					textField_3.setText(rs.getString(4));
				}//try
				catch(SQLException se) {
					se.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(12, 215, 97, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("next");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					 if(!rs.isLast())
					     rs.next();
					//set record values to text boxes
					textField.setText(rs.getString(1));
					textField_1.setText(rs.getString(2));
					textField_2.setText(rs.getString(3));
					textField_3.setText(rs.getString(4));
				}//try
				catch(SQLException se) {
					se.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(121, 215, 97, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("previous");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!rs.isFirst())
						rs.previous();
					//set record values to text boxes
					textField.setText(rs.getString(1));
					textField_1.setText(rs.getString(2));
					textField_2.setText(rs.getString(3));
					textField_3.setText(rs.getString(4));
				}//try
				catch(SQLException se) {
					se.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(230, 214, 97, 25);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("last");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs.last();
					//set record values to text boxes
					textField.setText(rs.getString(1));
					textField_1.setText(rs.getString(2));
					textField_2.setText(rs.getString(3));
					textField_3.setText(rs.getString(4));
				}//try
				catch(SQLException se) {
					se.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(346, 215, 97, 25);
		contentPane.add(btnNewButton_3);
		jdbcInitialize();
		this.addWindowListener(new WindowAdapter() {
			  @Override
			public void windowClosing(WindowEvent e) {
				  System.out.println("GUIScrollFrameApp1.GUIScrollFrameApp1().new WindowAdapter() {...}.windowClosing()");
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
		});
	}//constructor
	
	private void jdbcInitialize() {
		try {
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			//create PreparedStatement object
			ps=con.prepareStatement(GET_ALL_STUDENTS,ResultSet.TYPE_SCROLL_SENSITIVE,
					                                         ResultSet.CONCUR_UPDATABLE);
			//create Scrollable ResultSet object
			rs=ps.executeQuery();
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
	}
	
}
