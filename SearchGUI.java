package OOP_final_project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchGUI extends JFrame {
    private mainGUI m;
    private JPanel contentPane;
    private JTextField txtItemName;
    private DefaultTableModel tableModel;
    private JLabel lblItemName;
    private JButton btnSearch;
    private JButton btnBack;
    private JButton btnReset;
    private JTable table;
    private int selectedRow;
    protected String modi;
    private ModifyGUI modify;
    private String[] columnNames;
    private JButton btnModify;
    private JScrollPane scrollPane;
    public SearchGUI() {
        m = m.getInstance();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblItemName = new JLabel("Item Name");
        lblItemName.setBounds(10, 10, 80, 25);
        contentPane.add(lblItemName);

        txtItemName = new JTextField();
        txtItemName.setBounds(100, 10, 200, 25);
        contentPane.add(txtItemName);
        txtItemName.setColumns(10);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(310, 10, 80, 25);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                search(m, txtItemName.getText());
            }
        });
        contentPane.add(btnSearch);

        btnBack = new JButton("Back");
        btnBack.setBounds(330, 210, 80, 25);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewGUI().setVisible(true);
                SearchGUI.this.dispose();
            }
        });
        contentPane.add(btnBack);
        
        btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
				    modify(m,selectedRow);
				} else {
				    JOptionPane.showMessageDialog(contentPane, "Please select a contact to modify.");
				}
			}
		});
		btnModify.setBounds(10, 210, 80, 25);
		contentPane.add(btnModify);		
        btnReset = new JButton("Reset");
        btnReset.setBounds(221, 210, 80, 25);
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SearchGUI().setVisible(true);
                SearchGUI.this.dispose();
            }
        });
        contentPane.add(btnReset);
        columnNames = new String[3];
        columnNames[0] = "Items Name";
        columnNames[1] = "Unit Price";
        columnNames[2] = "Available Quantity";
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 50, 400, 150);
        contentPane.add(scrollPane);
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow(); // Get selected row
                if (selectedRow != -1) {
                    // Confirm before deleting
                    int confirmation = JOptionPane.showConfirmDialog(
                        SearchGUI.this, 
                        "Are you sure you want to delete this item?", 
                        "Confirm Delete", 
                        JOptionPane.YES_NO_OPTION
                    );

                    if (confirmation == JOptionPane.YES_OPTION) {
                        delete(m, selectedRow); // Call delete method
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Please select an item to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnDelete.setBounds(108, 208, 87, 29);
        contentPane.add(btnDelete);
    }
    public void modify(mainGUI m, int id) {
        modi = m.allItems.get(id);
        // Pass the item directly to ModifyGUI without removing it
        ModifyGUI modifyGUI = new ModifyGUI(modi, this);
        modifyGUI.setVisible(true);
        this.setVisible(false);
    }

    public void removeFromList(mainGUI m, int id) {
		m.allItems.remove(id);
		tableModel.removeRow(id);
	}
    public void delete(mainGUI m, int id) {
        String itemToDelete = m.allItems.get(id);

        m.allItems.remove(id);

        tableModel.removeRow(id);

        FileManager fm = new FileManager("searchGUI");
        fm.save(m.allItems);

        JOptionPane.showMessageDialog(this, "Item deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void search(mainGUI m, String detail) {
        FileManager fm = new FileManager("searchGUI");
        m.allItems = fm.read();
        tableModel.setRowCount(0);
        for (String item : m.allItems) {
            String[] parts = item.split(",");
            if (parts.length == 3) {
                String itemName = parts[0];
                if (itemName.equalsIgnoreCase(detail) || itemName.contains(detail)) {
                    tableModel.addRow(parts);
                }
            }
        }

        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(contentPane, "No matching contacts found.");
        }
    }
    public void refreshTable() {
        tableModel.setRowCount(0); // Clear the table

        // Populate the table with updated data
        for (String item : m.allItems) {
            System.out.println("Processing item: " + item); // Debug each item

            String[] parts = item.split(",");
            if (parts.length == 3) {
                tableModel.addRow(parts); // Add rows back to the table
            } else {
                System.err.println("Invalid item format: " + item); // Log invalid entries
            }
        }
    }


}
