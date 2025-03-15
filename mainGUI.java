package OOP_final_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class mainGUI extends JFrame {

	private JPanel contentPane;
	private JButton btnBuyerLogin;
	private JButton btnSellerLogin;
	private BuyerGUI b;
	private sellerGUI s;
	public Vector<String> allItems;
	public Vector<String> allRevenue;
	public Vector<String> allReview;
	private ShoppingGUI shopping;
	protected List<Item> inventory = new ArrayList<>();
	private static mainGUI instance = null;
	/**
	 * Create the frame.
	 */
	public mainGUI() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnBuyerLogin = new JButton("Buyer Log in");
		btnBuyerLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			buyerLogin();
			}
		});
		btnBuyerLogin.setBounds(134, 58, 117, 29);
		contentPane.add(btnBuyerLogin);
		
		btnSellerLogin = new JButton("Seller Log in");
		btnSellerLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellerLogin();
			}
		});
		btnSellerLogin.setBounds(134, 147, 117, 29);
		contentPane.add(btnSellerLogin);
	}
	public void buyerLogin() {
		shopping = new ShoppingGUI();
		shopping.show();
		mainGUI.this.setVisible(false);
	}
	public static mainGUI getInstance() {
		if(instance == null) {
			instance = new mainGUI ();
		}
		return instance;
	}
	public void sellerLogin() {
		s = new sellerGUI();
		s.show();
		mainGUI.this.setVisible(false);
		
	}
}
