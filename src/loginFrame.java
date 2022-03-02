import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class loginFrame {

	private JFrame frame;
	private JTextField userNamefield;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginFrame window = new loginFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Connection connection =null;
	
	public loginFrame() {
		initialize();
		connection=sqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1120, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.inactiveCaptionBorder);
		desktopPane.setBounds(181, 167, 771, 292);
		frame.getContentPane().add(desktopPane);
		
		JLabel label = new JLabel("USERNAME : ");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 21));
		label.setBounds(25, 42, 210, 44);
		desktopPane.add(label);
		
		userNamefield = new JTextField();
		userNamefield.setColumns(10);
		userNamefield.setBounds(322, 42, 399, 44);
		desktopPane.add(userNamefield);
		
		JLabel label_1 = new JLabel("PASSWORD :");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 21));
		label_1.setBounds(25, 137, 210, 44);
		desktopPane.add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(322, 137, 399, 36);
		desktopPane.add(passwordField);
		
		JButton loginButton = new JButton("LOGIN");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="select * "
							+ "from loginDatabase"
							+ " where userName=? and password=?";
					
					PreparedStatement  pst=connection.prepareStatement(query);
					
					pst.setString(1, userNamefield.getText());
					pst.setString(2, passwordField.getText());
					
					ResultSet rs=pst.executeQuery();
					int count=0;
					while(rs.next())
							{
						count++;
							};
							if(count==1)
							{
								JOptionPane.showMessageDialog(null, "WELCOME");
								frame.dispose();
								homeFrame home =new homeFrame();
								home.setVisible(true);
							}
							else if(count>1)
							{
								JOptionPane.showMessageDialog(null, "Duplicate Name and Password");
							}
							else {
								JOptionPane.showMessageDialog(null, "Incorrect Username / password ");
							}
					rs.close();
					pst.close();
				}catch(Exception e2)
				{
					JOptionPane.showMessageDialog(null,e2);
				}
			}
		});
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		loginButton.setBounds(427, 217, 235, 44);
		desktopPane.add(loginButton);
		
		JButton btnSignUp = new JButton("SIGN UP");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				signupFrame s =new signupFrame();
				s.setVisible(true);
				
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSignUp.setBounds(50, 217, 235, 44);
		desktopPane.add(btnSignUp);
	}

}
