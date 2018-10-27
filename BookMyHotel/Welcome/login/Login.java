package login;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Login
{
	Connection conn;
	private JFrame frame;
	private JTextField txtLogin;
	private JPasswordField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Login window = new Login();
					window.frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login()
	{
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");
			initialize();
		}
		catch(SQLException e)
		{
			System.out.println("Eerr");
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(176, 224, 230));
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 900, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(151, 117, 583, 529);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		JLabel loginLabel = new JLabel("Username");
		loginLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		loginLabel.setBounds(173, 182, 128, 31);
		panel.add(loginLabel);
		
		txtLogin = new JTextField();
		txtLogin.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtLogin.setBounds(281, 185, 136, 25);
		panel.add(txtLogin);
		txtLogin.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBackground(new Color(255, 255, 255));
		btnSubmit.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnSubmit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				checkLogin(frame);
			}
		});
		btnSubmit.setBounds(239, 318, 105, 31);
		panel.add(btnSubmit);
		
		txtPass = new JPasswordField();
		txtPass.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtPass.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					checkLogin(frame);
			}
		});
		txtPass.setBounds(281, 253, 136, 25);
		panel.add(txtPass);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblPassword.setBounds(173, 250, 128, 31);
		panel.add(lblPassword);
		
		JLabel lblBookmyhotel = new JLabel("BookMyHotel");
		lblBookmyhotel.setFont(new Font("Roboto", Font.BOLD, 38));
		lblBookmyhotel.setBounds(10, 10, 266, 46);
		frame.getContentPane().add(lblBookmyhotel);
	}
	
	private void checkLogin(JFrame f)
	{	
		
		
		String uname = txtLogin.getText();
		String pass = txtPass.getText();
		
		try
		{
			Statement stmt = conn.createStatement();
			String qry = "SELECT password FROM login WHERE username='"+uname+"'";
			ResultSet rs = stmt.executeQuery(qry);
		
			if(rs.next())
				if(rs.getString(1).equals(pass))
					JOptionPane.showMessageDialog(f, "Successful login");
				else
					JOptionPane.showMessageDialog(f, "Invalid login");
			else
				JOptionPane.showMessageDialog(f, "Invalid username");
		}
		catch(SQLException e)
		{
			
		}
	}
}