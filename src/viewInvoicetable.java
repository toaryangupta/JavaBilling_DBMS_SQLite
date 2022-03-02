import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class viewInvoicetable extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewInvoicetable frame = new viewInvoicetable();
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
	public viewInvoicetable() {
		connection=sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1225, 738);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(21, 175, 1180, 482);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 1160, 452);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("View Invoice Database");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				String queryforWholeInvoice="select * from invoiceTable ";
				PreparedStatement  pstInvoice=connection.prepareStatement(queryforWholeInvoice);
				ResultSet rs=pstInvoice.executeQuery();
				
				table.setModel(DbUtils.resultSetToTableModel(rs));
				pstInvoice.execute();
				pstInvoice.close();
				rs.close();

			}catch(Exception e2)
			{
				JOptionPane.showMessageDialog(null,e2);
			}
			}
		});
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 16));
		btnNewButton.setBounds(81, 27, 261, 43);
		contentPane.add(btnNewButton);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				homeFrame home =new homeFrame();
				home.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Arial Black", Font.BOLD, 16));
		btnBack.setBounds(918, 27, 261, 43);
		contentPane.add(btnBack);
	}

}
