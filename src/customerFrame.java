import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class customerFrame extends JFrame {

	private JPanel customerPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customerFrame frame = new customerFrame();
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
	Connection connection =null;
	public customerFrame() {
		connection=sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1312, 761);
		customerPane = new JPanel();
		customerPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(customerPane);
		customerPane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.inactiveCaptionBorder);
		desktopPane.setBounds(71, 107, 1163, 573);
		customerPane.add(desktopPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 33, 1103, 508);
		desktopPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				dispose();
				homeFrame home =new homeFrame();
				home.setVisible(true);
			
			
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 28));
		btnBack.setBounds(966, 27, 257, 44);
		customerPane.add(btnBack);
		
		JButton button_1 = new JButton("VIEW");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				String queryforCustomer="select custID,custName,custMobile from customerDetail ";
				PreparedStatement  pstCustomer=connection.prepareStatement(queryforCustomer);
				ResultSet rs=pstCustomer.executeQuery();
				
				table.setModel(DbUtils.resultSetToTableModel(rs));
				pstCustomer.execute();
				pstCustomer.close();
				rs.close();

			}catch(Exception e2)
			{
				JOptionPane.showMessageDialog(null,e2);
			}
				
				
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 28));
		button_1.setBounds(118, 27, 257, 44);
		customerPane.add(button_1);
	}

}
