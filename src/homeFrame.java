import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class homeFrame extends JFrame {

	private JPanel homePane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					homeFrame frame = new homeFrame();
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
	public homeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1120, 750);
		homePane = new JPanel();
		homePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(homePane);
		homePane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.inactiveCaptionBorder);
		desktopPane.setBounds(63, 123, 968, 503);
		homePane.add(desktopPane);
		
		JButton gotoCreateButton = new JButton("CREATE BILL");
		gotoCreateButton.setBounds(88, 32, 274, 57);
		desktopPane.add(gotoCreateButton);
		gotoCreateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				createbillFrame bill =new createbillFrame();
				bill.setVisible(true);
				
			}
		});
		gotoCreateButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnViewCustomer = new JButton("View Customer");
		btnViewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				customerFrame c =new customerFrame();
				c.setVisible(true);
				
				
			}
		});
		btnViewCustomer.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnViewCustomer.setBounds(559, 175, 274, 57);
		desktopPane.add(btnViewCustomer);
		
		JButton btnViewSales = new JButton("View Sales");
		btnViewSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				viewInvoicetable c =new viewInvoicetable();
				c.setVisible(true);
			}
		});
		btnViewSales.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnViewSales.setBounds(559, 32, 274, 57);
		desktopPane.add(btnViewSales);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnExit.setBounds(628, 388, 274, 57);
		desktopPane.add(btnExit);
		
		JButton printButton = new JButton("PRINT BILL");
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				printFrame02 c =new printFrame02();
				c.setVisible(true);
			}
		});
		printButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		printButton.setBounds(88, 175, 274, 57);
		desktopPane.add(printButton);
	}
}
