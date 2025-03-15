package OOP_final_project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewReviewGUI extends JFrame {

	private JPanel contentPane;
	private JButton btnCancel;
	private String[] columnNames;
	private JScrollPane scrollPane;
	private mainGUI m;
	private DefaultTableModel tableModel;
	private JTable table;
	private sellerGUI s;
	public ViewReviewGUI() {
		m = m.getInstance();
		s = new sellerGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s.show();
				ViewReviewGUI.this.setVisible(false);
			}
		});
		btnCancel.setBounds(149, 221, 117, 29);
		contentPane.add(btnCancel);
		columnNames = new String[3];
		columnNames[0] = "Item Name";
		columnNames[1] = "Date";
		columnNames[2] = "Item Review";
		tableModel = new DefaultTableModel(columnNames, 0); 
		table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
		scrollPane.setBounds(48, 30, 324, 179);
		contentPane.add(scrollPane);
		loadReview(m);
	}
	public void loadReview(mainGUI m) {
	    FileManager fm = new FileManager("reviewGUI");
	    m.allReview = fm.read(); 
	    tableModel.setRowCount(0); 
	    for (String review : m.allReview) {
	        String[] parts = review.split(", ");
	        if (parts.length >= 3) { // Ensure there are at least 3 parts
	            tableModel.addRow(parts);
	        } else {
	            System.err.println("Invalid review format: " + review);
	        }
	    }
	}
}
