package OOP_final_project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class BrowseGUI extends JFrame {

	private JPanel contentPane;
	private ViewGUI v;
	private DefaultTableModel tableModel;
	private JTable table;
	private mainGUI m;
	private JButton btnCancel;
	public BrowseGUI() {
		v = new ViewGUI();
		m = m.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.show();
				BrowseGUI.this.setVisible(false);
			}
		});
		btnCancel.setBounds(307, 221, 117, 29);
		contentPane.add(btnCancel);
		String[]columnNames = {"Item Name", "Unit Price", "Available Quantity"};
		tableModel = new DefaultTableModel(columnNames, 0); 
		table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 30, 386, 179);
		contentPane.add(scrollPane);
		
		
		loadItem();
	}
	public void loadItem() {
		FileManager fm = new FileManager("browseGUI");
	    m.allItems = fm.read(); 
	    tableModel.setRowCount(0);
	    for (String item : m.allItems) {
	        String[] parts = item.split(",");
	        if (parts.length == 3) {
	            tableModel.addRow(parts);
	        }
	    }
	}
	
}
