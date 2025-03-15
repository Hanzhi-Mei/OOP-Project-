package OOP_final_project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewRevenueGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblTransactionRecord;
	private JLabel lblTotalRevenue;
	private String[]columnNames;
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextPane txtTotalRevenue;
	private JButton btnCalculate;
	private JButton btnCancel;
	private mainGUI m;
	private sellerGUI s;
	public ViewRevenueGUI() {
		m = m.getInstance();
		s = new sellerGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTransactionRecord = new JLabel("Transaction Record");
		lblTransactionRecord.setBounds(37, 25, 145, 16);
		contentPane.add(lblTransactionRecord);
		
		lblTotalRevenue = new JLabel("Total Revenue");
		lblTotalRevenue.setBounds(37, 183, 145, 16);
		contentPane.add(lblTotalRevenue);
		
		columnNames = new String[2];
		columnNames[0] = "Trasaction ID";
		columnNames[1] = "Revenue";
		tableModel = new DefaultTableModel(columnNames, 0); 
		table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
		scrollPane.setBounds(180, 24, 228, 141);
		contentPane.add(scrollPane);
		
		txtTotalRevenue = new JTextPane();
		txtTotalRevenue.setBounds(180, 183, 131, 16);
		contentPane.add(txtTotalRevenue);
		
		btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTotalRevenue.setText(calculate(m));
			}
		});
		btnCalculate.setBounds(323, 178, 117, 29);
		contentPane.add(btnCalculate);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s.show();
				ViewRevenueGUI.this.setVisible(false);
			}
		});
		btnCancel.setBounds(177, 223, 117, 29);
		contentPane.add(btnCancel);
		loadRevenue(m);
	}
	public void loadRevenue(mainGUI m) {
		FileManager fm = new FileManager("revenueGUI");
	    m.allRevenue =fm.read(); 
	    tableModel.setRowCount(0); 
	    int count = 1;
	    for (String revenue : m.allRevenue) {
	        String [] parts = new String[2];
	        parts[0] = String.valueOf(count);
	        parts[1] = revenue;
	        if (parts.length == 2) {
	            tableModel.addRow(parts);
	        }
	        count += 1;
	    }
	}
	 public String calculate(mainGUI m) {
		 float totalR = 0;
		 FileManager fm = new FileManager("revenueGUI");
		 m.allRevenue =fm.read(); 
	     for (String revenue : m.allRevenue) {
	    	 String parts = revenue;
	    	 try {
	    		 totalR += Float.parseFloat(parts); 
	    		 } 
	    	 catch (NumberFormatException e) {
	    		 System.out.println("Invalid revenue value: " + parts);
	    		 }
	    	 }
	     return String.valueOf(totalR); 
	  }
}
