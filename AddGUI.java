package OOP_final_project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblItemName;
	private JTextField textItemName;
	private JLabel lblUnitPrice;
	private JTextField textUnitPrice;
	private JLabel lblQuantity;
	private JTextField textQuantity;
	private JButton btnSave;
	private JButton btnReset;
	private JButton btnCancel;
	private sellerGUI s;
	private String detail;
	private mainGUI m;
	private AddGUI a;
	private String[] itemDetails;
	public AddGUI() {
		s = new sellerGUI();
		m = m.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblItemName = new JLabel("Item Name");
		lblItemName.setBounds(32, 34, 93, 16);
		contentPane.add(lblItemName);
		
		textItemName = new JTextField();
		textItemName.setBounds(147, 29, 203, 26);
		contentPane.add(textItemName);
		textItemName.setColumns(10);
		
		lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setBounds(32, 86, 93, 16);
		contentPane.add(lblUnitPrice);
		
		textUnitPrice = new JTextField();
		textUnitPrice.setBounds(147, 81, 203, 26);
		contentPane.add(textUnitPrice);
		textUnitPrice.setColumns(10);
		
		lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(32, 142, 61, 16);
		contentPane.add(lblQuantity);
		
		textQuantity = new JTextField();
		textQuantity.setBounds(147, 137, 203, 26);
		contentPane.add(textQuantity);
		textQuantity.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				detail = textItemName.getText() + "," + textUnitPrice.getText() + "," + textQuantity.getText();
				save(m,detail);
				JOptionPane.showMessageDialog(contentPane, "Item saved successfully!");
			}
		});
		btnSave.setBounds(32, 220, 117, 29);
		contentPane.add(btnSave);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnReset.setBounds(177, 220, 117, 29);
		contentPane.add(btnReset);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s = new sellerGUI();
				s.show();
				AddGUI.this.setVisible(false);
			}
		});
		btnCancel.setBounds(327, 220, 117, 29);
		contentPane.add(btnCancel);
		loadItem(m);
	}
	public void save(mainGUI m, String detail) {
		FileManager fm = new FileManager("addGUI");
		m.allItems.add(detail);
		fm.save(m.allItems);
		itemDetails = new String[3];
		itemDetails = detail.split(",");
	}
	public void reset() {
		AddGUI.this.setVisible(false);
		a = new AddGUI();
		a.show();
	}
	public void loadItem(mainGUI m) {
		FileManager fm = new FileManager("addGUI");
	    m.allItems = (fm.read()); 
	}
}
