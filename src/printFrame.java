import java.awt.BorderLayout;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.print.PrinterException;
import java.awt.print.Printable;
import java.awt.print.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;
import javax.swing.JTextPane;

public class printFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int orderid;
	private double totalP;
	private int customerid;
	private String date;
	private String mobilenum;
	
/*	private void printRecord(JPanel panel)
	{
		PrinterJob printerJob=PrinterJob.getPrinterJob();
		
		printerJob.setJobName("Print Record");
		
		printerJob.setPrintable(new Printable())
		{
			@overide
			public int print(Graphics graphics,PageFormat,int pageIndex(),throws PrintException) {
				if(pageIndex>0)
				{
					return Printable.NO_SUCH_PAGE;
				}
			}
		}; 
	}
*/
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					printFrame frame = new printFrame();
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
	Connection connection =null;//connection object
	private JTextField customeridField;
	private JTextField orderidField;
	private JTextField mobileField;
	private JTable table;
	private JTextField totalField;
	private JTextField dateField;
	private JTable nameTable;
	
	public printFrame() {
		connection=sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1120, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBounds(699, 10, 169, 39);
		contentPane.add(panel_1);
		
		
		
		
		JButton confirmPrintButton = new JButton("PRINT");
		confirmPrintButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//printRecord(printingPanel);
			}
		});
		confirmPrintButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(confirmPrintButton);
		
	
			 

			 
			 JDesktopPane desktopPane = new JDesktopPane();
			 desktopPane.setBounds(38, 92, 1058, 581);
			 contentPane.add(desktopPane);
			 
			 JPanel printingPanel = new JPanel();
			 printingPanel.setBounds(10, 10, 1038, 561);
			 desktopPane.add(printingPanel);
			 printingPanel.setLayout(null);
			 
			 JLabel label = new JLabel("CUSTOMER ID : ");
			 label.setFont(new Font("Tahoma", Font.BOLD, 20));
			 label.setBounds(363, 67, 165, 36);
			 printingPanel.add(label);
			 
			 customeridField = new JTextField();
			 customeridField.setColumns(10);
			 customeridField.setBounds(340, 113, 188, 36);
			 printingPanel.add(customeridField);
			 
			 JLabel label_1 = new JLabel("INVOICE");
			 label_1.setHorizontalAlignment(SwingConstants.CENTER);
			 label_1.setFont(new Font("Tahoma", Font.BOLD, 26));
			 label_1.setBounds(466, 10, 165, 36);
			 printingPanel.add(label_1);
			 
			 JLabel label_2 = new JLabel("ORDER ID : ");
			 label_2.setFont(new Font("Tahoma", Font.BOLD, 20));
			 label_2.setBounds(699, 10, 165, 36);
			 printingPanel.add(label_2);
			 
			 orderidField = new JTextField();
			 orderidField.setColumns(10);
			 orderidField.setBounds(874, 17, 154, 36);
			 printingPanel.add(orderidField);
			 
			 JLabel label_3 = new JLabel("MOBILE :");
			 label_3.setFont(new Font("Tahoma", Font.BOLD, 20));
			 label_3.setBounds(787, 67, 112, 36);
			 printingPanel.add(label_3);
			 
			 mobileField = new JTextField();
			 mobileField.setColumns(10);
			 mobileField.setBounds(764, 113, 188, 36);
			 printingPanel.add(mobileField);
			 
			 table = new JTable();
			 table.setBounds(22, 186, 994, 333);
			 printingPanel.add(table);
			 
			 JLabel label_4 = new JLabel("TOTAL  : ");
			 label_4.setFont(new Font("Tahoma", Font.BOLD, 20));
			 label_4.setBounds(711, 529, 165, 36);
			 printingPanel.add(label_4);
			 
			 totalField = new JTextField();
			 totalField.setColumns(10);
			 totalField.setBounds(874, 529, 154, 36);
			 printingPanel.add(totalField);
			 
			 JLabel label_5 = new JLabel("DATE :");
			 label_5.setFont(new Font("Tahoma", Font.BOLD, 20));
			 label_5.setBounds(22, 10, 165, 36);
			 printingPanel.add(label_5);
			 
			 dateField = new JTextField();
			 dateField.setColumns(10);
			 dateField.setBounds(100, 10, 239, 36);
			 printingPanel.add(dateField);
			 
			 JLabel label_6 = new JLabel("NAME  :");
			 label_6.setFont(new Font("Tahoma", Font.BOLD, 20));
			 label_6.setBounds(80, 67, 165, 36);
			 printingPanel.add(label_6);
			 
			 nameTable = new JTable();
			 nameTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
			 nameTable.setBounds(46, 113, 188, 36);
			 printingPanel.add(nameTable);
			 
			 JPanel panel = new JPanel();
			 panel.setBackground(SystemColor.inactiveCaptionBorder);
			 panel.setBounds(306, 10, 169, 39);
			 contentPane.add(panel);
			 
			 JButton btnShow = new JButton("SHOW");
			 btnShow.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		

			 			try {
			 				String queryGetlastbill="SELECT * FROM lastBill";
			 				
			 				PreparedStatement  pstfetchdetails=connection.prepareStatement(queryGetlastbill);
			 							
			 				ResultSet rs=pstfetchdetails.executeQuery();
			 				
			 				if(rs.next())
			 				{	
			 				
			 					orderid = rs.getInt("orderID");
			 				 orderidField.setText(""+orderid);		
			 				 mobileField.setText(rs.getString("custMobile"));	
			 			 totalField.setText(rs.getString("totalPurchase"));	
			 				customeridField.setText(rs.getString("customerID"));	
			 				 dateField.setText(rs.getString("date"));	
			 				 orderidField.setText(rs.getString("orderID"));	
			 				 
			 				 
			 				 
			 				 /**	private int orderid;
			 	private double totalP;
			 	private int customerid;
			 	private String date;
			 	private String mobilenum;*/
			 				 
			 				}
			 				pstfetchdetails.execute();
		 				    pstfetchdetails.close();
		 					rs.close();
			 				}
			 				catch(Exception e2)
			 				{
			 					JOptionPane.showMessageDialog(null,e2);
			 				}
			 			try
						{
						String queryforTable="select itemName,unitPrice,quantity,discount,total from lastBill ";
						PreparedStatement  pstCustomer02=connection.prepareStatement(queryforTable);
						ResultSet rs=pstCustomer02.executeQuery();
						
						table.setModel(DbUtils.resultSetToTableModel(rs));
						pstCustomer02.execute();
						pstCustomer02.close();
						rs.close();

					}catch(Exception e2)
					{
						JOptionPane.showMessageDialog(null,e2);
					}
			 			try
						{
						String queryforName="select custName from customerDetail where custID=? ";
						PreparedStatement  pstName=connection.prepareStatement(queryforName);
						pstName.setString(1, customeridField.getText());
						ResultSet rs01=pstName.executeQuery();
						
						nameTable.setModel(DbUtils.resultSetToTableModel(rs01));
						pstName.execute();
						pstName.close();
						rs01.close();

					}catch(Exception e2)
					{
						JOptionPane.showMessageDialog(null,e2);
					}
			 		}
			 	
			 });
			 btnShow.setFont(new Font("Tahoma", Font.BOLD, 16));
			 panel.add(btnShow);
		 
			
	}
}
