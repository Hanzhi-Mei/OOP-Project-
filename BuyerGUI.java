package OOP_final_project;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuyerGUI extends JFrame {

	private JPanel contentPane;
	private mainGUI m;
	
	public BuyerGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(146, 16, 155, 29);
		contentPane.add(btnBrowse);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(146, 71, 155, 29);
		contentPane.add(btnSearch);
		
		JButton btnViewCart = new JButton("View Shopping Cart");
		btnViewCart.setBounds(146, 133, 155, 29);
		contentPane.add(btnViewCart);
		
	}
}
