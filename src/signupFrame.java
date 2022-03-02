import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class signupFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userField;
	private JTextField passwordField;

	/**
	 * Launch the application.
	 */
	Connection connection =null;//connection object
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signupFrame frame = new signupFrame();
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
	public signupFrame() {
		connection=sqliteConnection.dbConnector();//class.method()
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1225, 735);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(73, 100, 1082, 501);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserID :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setBounds(72, 104, 292, 45);
		panel.add(lblNewLabel);
		
		userField = new JTextField();
		userField.setFont(new Font("Tahoma", Font.BOLD, 28));
		userField.setBounds(589, 104, 381, 51);
		panel.add(userField);
		userField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblPassword.setBounds(72, 199, 292, 45);
		panel.add(lblPassword);
		
		passwordField = new JTextField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 28));
		passwordField.setColumns(10);
		passwordField.setBounds(589, 199, 381, 51);
		panel.add(passwordField);
		
		JButton btnNewButton = new JButton("CREATE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String querycheckUser="select userName,password "
							+ "from loginDatabase"
							+ " where userName=? and password=?";
					
					PreparedStatement  pstCheckifPresent=connection.prepareStatement(querycheckUser);
							
					pstCheckifPresent.setString(1, userField.getText());
					pstCheckifPresent.setString(2, passwordField.getText());

					
					ResultSet rs=pstCheckifPresent.executeQuery();
				

					if(rs.next())
					{						
						JOptionPane.showMessageDialog(null, "User Already Present");
						  			
					
					}
					else {
						try {
							String queryCreate="insert into loginDatabase (username,password) values(?,?)";



							PreparedStatement  pstName=connection.prepareStatement(queryCreate);

							pstName.setString(1, userField.getText());
							pstName.setString(2, passwordField.getText());
							
							pstName.execute();

							JOptionPane.showMessageDialog(null, "Acoount Created");			
		
							pstName.close();
							rs.close();

							}
						catch(Exception e2)
						{
							JOptionPane.showMessageDialog(null,e2);
						}
					}
					rs.close();
					pstCheckifPresent.close();
					dispose();
					homeFrame home =new homeFrame();
					home.setVisible(true);
				}catch(Exception e3)
				{
					JOptionPane.showMessageDialog(null,e3);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 27));
		btnNewButton.setBounds(618, 330, 310, 61);
		panel.add(btnNewButton);
	}

}
