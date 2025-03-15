package OOP_final_project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class sellerGUI extends JFrame {

	private JPanel contentPane;
	private JButton btnView;
	private JButton btnAdd;
	private JButton btnViewRevenue;
	private JButton btnViewReview;
	private JButton btnBack;
	private mainGUI m;
	private AddGUI a;
	private ViewGUI v;
	private ViewRevenueGUI vrevenue;
	private ViewReviewGUI vreview;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public sellerGUI() {
		m = m.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnView = new JButton("View and Search Storage");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view();
			}
		});
		btnView.setBounds(134, 14, 190, 29);
		contentPane.add(btnView);
		
		btnAdd = new JButton("Add Storage");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add();
			}
		});
		btnAdd.setBounds(134, 66, 190, 29);
		contentPane.add(btnAdd);
		
		btnViewRevenue = new JButton("View Revenue");
		btnViewRevenue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewRevenue();
			}
		});
		btnViewRevenue.setBounds(134, 127, 190, 29);
		contentPane.add(btnViewRevenue);
		
		btnViewReview = new JButton("View Review");
		btnViewReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewReview();
			}
		});
		btnViewReview.setBounds(134, 185, 190, 29);
		contentPane.add(btnViewReview);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		btnBack.setBounds(134, 237, 190, 29);
		contentPane.add(btnBack);
	}
	public void view() {
		v = new ViewGUI();
		v.show();
		sellerGUI.this.setVisible(false);
	}
	public void add() {
		a = new AddGUI();
		a.show();
		sellerGUI.this.setVisible(false);
	}
	public void viewRevenue() {
		vrevenue = new ViewRevenueGUI();
		vrevenue.show();
		sellerGUI.this.setVisible(false);
	}
	public void viewReview() {
		vreview = new ViewReviewGUI();
		vreview.show();
		sellerGUI.this.setVisible(false);
	}
	public void back() {
		m.show();
		sellerGUI.this.setVisible(false);
	}
}
