package OOP_final_project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewGUI extends JFrame {

	private JPanel contentPane;
	private BrowseGUI b;
	private SearchGUI search;
	private sellerGUI s;
	private JButton btnBrowse;
	private JButton btnSearch;
	private JButton btnCancel;
	public ViewGUI() {
		s = new sellerGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				browse();
			}
		});
		btnBrowse.setBounds(153, 50, 117, 29);
		contentPane.add(btnBrowse);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnSearch.setBounds(153, 122, 117, 29);
		contentPane.add(btnSearch);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s = new sellerGUI();
				s.show();
				ViewGUI.this.setVisible(false);
			}
		});
		btnCancel.setBounds(153, 196, 117, 29);
		contentPane.add(btnCancel);
	}
	public void browse() {
		b = new BrowseGUI();
		b.show();
		ViewGUI.this.setVisible(false);
	}
	public void search() {
		search = new SearchGUI();
		search.show();
		ViewGUI.this.setVisible(false);
	}
}
