import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

public class createbillFrame extends JFrame {

	private JPanel contentPane;
	private JTextField mobileField;
	private JTextField nameField;
	private JLabel lblItem;
	private JTextField itemField;
	private JLabel lblUnitPrice;
	private JTextField unitField;
	private JLabel lblQuantity;
	private JTextField quantityField;
	private JLabel lblDiscount;
	private JTextField discountField;
	private JButton addItembutton;
	private JLabel lblDate;
	private JTextField dateField;
	private JDesktopPane desktopPane_1;
	private JDesktopPane desktopPane_2;
	private int customerkey;
	private int orderidkey;
	private double totalSum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createbillFrame frame = new createbillFrame();
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
	private JTable table;
	public createbillFrame() {
		connection=sqliteConnection.dbConnector();//class.method()
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		setBounds(100, 100, 1120, 750);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.inactiveCaptionBorder);
		desktopPane.setBounds(30, 10, 1044, 339);
		contentPane.add(desktopPane);
		
		desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBackground(SystemColor.inactiveCaptionBorder);
		desktopPane_1.setBounds(10, 359, 1086, 285);
		contentPane.add(desktopPane_1);
		

		JTextPane totalField = new JTextPane();
		totalField.setFont(new Font("Tahoma", Font.BOLD, 15));
		totalField.setBounds(763, 243, 278, 29);
		desktopPane_1.add(totalField);
		
		JLabel nameLabel = new JLabel("NAME : ");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		nameLabel.setBackground(SystemColor.inactiveCaptionBorder);
		nameLabel.setBounds(26, 84, 155, 42);
		desktopPane.add(nameLabel);
		
		JLabel mobileLabel = new JLabel("MOBILE :");
		mobileLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		mobileLabel.setBackground(SystemColor.inactiveCaptionBorder);
		mobileLabel.setBounds(597, 88, 112, 42);
		desktopPane.add(mobileLabel);
		
		mobileField = new JTextField();
		mobileField.setColumns(10);
		mobileField.setBounds(693, 84, 263, 42);
		desktopPane.add(mobileField);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(102, 84, 337, 42);
		desktopPane.add(nameField);
		
		lblItem = new JLabel("ITEM : ");
		lblItem.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblItem.setBackground(SystemColor.inactiveCaptionBorder);
		lblItem.setBounds(10, 149, 155, 42);
		desktopPane.add(lblItem);
		
		itemField = new JTextField();
		itemField.setColumns(10);
		itemField.setBounds(138, 149, 211, 42);
		desktopPane.add(itemField);
		
		lblUnitPrice = new JLabel("UNIT PRICE : ");
		lblUnitPrice.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUnitPrice.setBackground(SystemColor.inactiveCaptionBorder);
		lblUnitPrice.setBounds(10, 209, 155, 42);
		desktopPane.add(lblUnitPrice);
		
		unitField = new JTextField();
		unitField.setColumns(10);
		unitField.setBounds(138, 209, 211, 42);
		desktopPane.add(unitField);
		
		lblQuantity = new JLabel("QUANTITY : ");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblQuantity.setBackground(SystemColor.inactiveCaptionBorder);
		lblQuantity.setBounds(10, 275, 155, 42);
		desktopPane.add(lblQuantity);
		
		quantityField = new JTextField();
		quantityField.setColumns(10);
		quantityField.setBounds(138, 275, 211, 42);
		desktopPane.add(quantityField);
		
		lblDiscount = new JLabel("DISCOUNT % :");
		lblDiscount.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDiscount.setBackground(SystemColor.inactiveCaptionBorder);
		lblDiscount.setBounds(392, 275, 155, 42);
		desktopPane.add(lblDiscount);
		
		discountField = new JTextField();
		discountField.setColumns(10);
		discountField.setBounds(540, 279, 211, 42);
		desktopPane.add(discountField);
		
		addItembutton = new JButton("ADD ITEM");
		addItembutton.setFont(new Font("Tahoma", Font.BOLD, 20));
		addItembutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query="insert into purchaseTable (itemName,unitPrice,quantity,discount,total)"
							+ " values(?,?,?,?,?)";
					
				
					double unitP= Double.parseDouble(unitField.getText());
					double quan= Double.parseDouble(quantityField.getText());
					double dis= Double.parseDouble(discountField.getText());
					double ans=(unitP*quan)-(unitP*quan*dis/100);
								
					PreparedStatement  pst=connection.prepareStatement(query);
					
					pst.setString(1, itemField.getText());
					pst.setDouble(2,unitP);
					pst.setDouble(3,quan);
					pst.setDouble(4, dis);
					pst.setDouble(5, ans);
					
					itemField.setText("");
					unitField.setText("");
					quantityField.setText("");
					discountField.setText("");
					
					pst.execute();
					pst.close();
					
					String queryforPrinting="select itemName,unitPrice,quantity,discount,total from purchaseTable ";
					PreparedStatement  pst02=connection.prepareStatement(queryforPrinting);
					ResultSet rs=pst02.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst02.execute();
					pst02.close();
					rs.close();

				}catch(Exception e2)
				{
					JOptionPane.showMessageDialog(null,e2);
				}
				try {
					String queryTotalSum="SELECT SUM(total) as total FROM purchaseTable";



					PreparedStatement  pstTotal=connection.prepareStatement(queryTotalSum);
					ResultSet rs=pstTotal.executeQuery();
					 totalSum=rs.getDouble("total");
					
					totalField.setText(""+totalSum);					
					


					pstTotal.execute();
					pstTotal.close();
					rs.close();

					}
				catch(Exception e2)
				{
					JOptionPane.showMessageDialog(null,e2);
				}
				
			}
		});
		
		
		addItembutton.setBounds(772, 275, 233, 46);
		desktopPane.add(addItembutton);
		
		lblDate = new JLabel("DATE : ");
		lblDate.setToolTipText("yyyy-mm-dd");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDate.setBackground(SystemColor.inactiveCaptionBorder);
		lblDate.setBounds(693, 10, 81, 42);
		desktopPane.add(lblDate);
		
		dateField = new JTextField();
		dateField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		dateField.setFont(new Font("Tahoma", Font.ITALIC, 18));
		dateField.setText("yyyy-mm-dd");
		dateField.setToolTipText("yyyy-mm-dd");
		dateField.setColumns(10);
		dateField.setBounds(784, 10, 224, 42);
		desktopPane.add(dateField);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 1066, 223);
		desktopPane_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("TOTAL :");
		label.setFont(new Font("Tahoma", Font.BOLD, 17));
		label.setBackground(SystemColor.inactiveCaptionBorder);
		label.setBounds(572, 243, 155, 29);
		desktopPane_1.add(label);
		
		
		desktopPane_2 = new JDesktopPane();
		desktopPane_2.setBackground(SystemColor.inactiveCaptionBorder);
		desktopPane_2.setBounds(30, 654, 1044, 49);
		contentPane.add(desktopPane_2);
		
		
		JButton saveButton = new JButton("SAVE");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//customer information is unique or not AND getting unique id for customer
				try {
					String querycheckName="select * "
							+ "from customerDetail"
							+ " where custMobile=?";
					
					PreparedStatement  pstCheckifPresent=connection.prepareStatement(querycheckName);
							
					pstCheckifPresent.setString(1, mobileField.getText());
					
					ResultSet rs=pstCheckifPresent.executeQuery();
				

					if(rs.next())
					{						
						JOptionPane.showMessageDialog(null, "Customer Present");
						customerkey = rs.getInt(1);  			
						rs.close();
					}
					else {
						try {
							String queryforName="insert into customerDetail (custName,custMobile) values(?,?)";



							PreparedStatement  pstName=connection.prepareStatement(queryforName,Statement.RETURN_GENERATED_KEYS);

							pstName.setString(1, nameField.getText());
							pstName.setString(2, mobileField.getText());
							
							pstName.execute();

							rs = pstName.getGeneratedKeys();
					            if(rs != null && rs.next())
					            {
					               customerkey=rs.getInt(1);
					            }				
		
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
				}catch(Exception e3)
				{
					JOptionPane.showMessageDialog(null,e3);
				}
				
							
				//assign order id and linking it with customer 
			
				try {
					String queryfororderid="insert into orderIDtable (custID,custMobile,totalPurchase,Date) values(?,?,?,?)";



					PreparedStatement  pstOrderid=connection.prepareStatement(queryfororderid,Statement.RETURN_GENERATED_KEYS);

					pstOrderid.setInt(1, customerkey);
					pstOrderid.setString(2, mobileField.getText());
					pstOrderid.setDouble(3,totalSum);
					pstOrderid.setString(4,dateField.getText());
				
					pstOrderid.execute();
					ResultSet rsorderid = pstOrderid.getGeneratedKeys();
		            if(rsorderid != null && rsorderid.next()){
		               orderidkey=rsorderid.getInt(1);
		            }
		          
					
					pstOrderid.close();
					rsorderid.close();

					}
				catch(Exception e2)
				{
					JOptionPane.showMessageDialog(null,e2);
				}
				
				//linking order id and placed order 
				
				try {
					String queryLinkingPurchaseAndId="UPDATE purchaseTable SET orderID =?, customerID=?, date=?, custMobile=?, totalPurchase=?";



					PreparedStatement  pstlinkId=connection.prepareStatement(queryLinkingPurchaseAndId);

					pstlinkId.setInt(1, orderidkey);
					pstlinkId.setInt(2, customerkey);
					pstlinkId.setString(3,dateField.getText());
					pstlinkId.setString(4,mobileField.getText());
					pstlinkId.setDouble(5,totalSum);
					
				
					pstlinkId.execute();
					
					
					pstlinkId.close();
					

					}
				catch(Exception e2)
				{
					JOptionPane.showMessageDialog(null,e2);
				}
				
			
				try
				{
						String finallyMergeQuery="insert into invoiceTable (customerID,orderID,itemName,quantity,unitPrice,discount,date,custMobile,total,totalPurchase)"
								+ " SELECT customerID,orderID,itemName,quantity,unitPrice,discount,date,custMobile,total,totalPurchase from purchaseTable";
						
						
						
								
						PreparedStatement pstMerge=connection.prepareStatement(finallyMergeQuery);	
						pstMerge.execute();
						pstMerge.close();
						
					
				}		
				catch(Exception e2)
				{
					JOptionPane.showMessageDialog(null,e2);
				}
					
				
			//before existing
				try
				{
					String querytoDelete02="DELETE FROM lastBill";
					PreparedStatement pstDelete02=connection.prepareStatement(querytoDelete02);
					
					pstDelete02.execute();
					pstDelete02.close();
					
					String queryLastbill="INSERT INTO lastBill SELECT * FROM purchaseTable";
					PreparedStatement pstLastbill=connection.prepareStatement(queryLastbill);
					
					pstLastbill.execute();
					pstLastbill.close();
					
					
					String querytoDelete="DELETE FROM purchaseTable";
					PreparedStatement pstDelete=connection.prepareStatement(querytoDelete);
					
					pstDelete.execute();
					pstDelete.close();
			
				}
				catch(Exception e3)
				{
					JOptionPane.showMessageDialog(null,e3);
				}
							
				  nameField.setText("");
				  mobileField.setText("");
				  
				JOptionPane.showMessageDialog(null, "BILL SAVED");
				dispose();
				
				askForPrint askPrint =new askForPrint();
				askPrint.setVisible(true);
						
				
			}
		});
		
		saveButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		saveButton.setBounds(71, 10, 206, 29);
		desktopPane_2.add(saveButton);
		
		JButton cancelButton = new JButton("CANCEL");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//deleting purchaseTable
				try
				{
					String querytoDelete="DELETE FROM purchaseTable";
					PreparedStatement pstDelete=connection.prepareStatement(querytoDelete);
					
					pstDelete.execute();
					pstDelete.close();
			
				}
				catch(Exception e3)
				{
					JOptionPane.showMessageDialog(null,e3);
				}
				  nameField.setText("");
				  mobileField.setText("");
			
				JOptionPane.showMessageDialog(null, "BILL NOT ADDED");
				dispose();
				homeFrame home =new homeFrame();
				home.setVisible(true);
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		cancelButton.setBounds(777, 10, 206, 29);
		desktopPane_2.add(cancelButton);
		
		
	}
}
