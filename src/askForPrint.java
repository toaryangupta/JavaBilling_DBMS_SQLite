import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class askForPrint extends JFrame {

	private JPanel askPrintPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					askForPrint frame = new askForPrint();
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
	public askForPrint() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 439, 300);
		askPrintPane = new JPanel();
		askPrintPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(askPrintPane);
		askPrintPane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.inactiveCaptionBorder);
		desktopPane.setBounds(65, 47, 300, 54);
		askPrintPane.add(desktopPane);
		
		JLabel lblNewLabel = new JLabel("Do You want to Print ?");
		lblNewLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 10, 300, 34);
		desktopPane.add(lblNewLabel);
		
		JDesktopPane desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBackground(SystemColor.inactiveCaptionBorder);
		desktopPane_1.setBounds(10, 182, 405, 54);
		askPrintPane.add(desktopPane_1);
		
		JButton yesButton = new JButton("YES !");
		yesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				printFrame printBill=new printFrame();
				printBill.setVisible(true);
			}
		});
		yesButton.setFont(new Font("Consolas", Font.BOLD, 18));
		yesButton.setBounds(10, 10, 170, 32);
		desktopPane_1.add(yesButton);
		
		JButton noButton = new JButton("NO !");
		noButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				homeFrame home =new homeFrame();
				home.setVisible(true);
			}
		});
		noButton.setFont(new Font("Consolas", Font.BOLD, 18));
		noButton.setBounds(225, 10, 170, 32);
		desktopPane_1.add(noButton);
	}
}
