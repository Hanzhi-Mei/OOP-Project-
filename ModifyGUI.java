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

public class ModifyGUI extends JFrame {

    private JPanel contentPane;
    private JTextField txtPModi;
    private JTextField txtNModi;
    private JTextField txtQModi;
    private JLabel lblQModify;
    private JLabel lblNModi;
    private JLabel lblPModi;
    private JButton btnConfirm;
    private JButton btnCancel;
    private String detail;
    private String NModi;
    private String PModi;
    private String QModi;
    private String[] npq;
    private FileManager fm;
    private mainGUI m;
    private String modiItem;
    private SearchGUI searchGUI; // Reference to SearchGUI

    public ModifyGUI(String modi, SearchGUI searchGUI) {
        this.modiItem = modi;
        this.searchGUI = searchGUI; // Store the SearchGUI reference
        m = mainGUI.getInstance();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblPModi = new JLabel("Price Modification");
        lblPModi.setBounds(57, 112, 127, 16);
        contentPane.add(lblPModi);

        txtPModi = new JTextField();
        txtPModi.setBounds(226, 107, 130, 26);
        contentPane.add(txtPModi);
        txtPModi.setColumns(10);

        lblQModify = new JLabel("Quantity Modification");
        lblQModify.setBounds(57, 172, 157, 16);
        contentPane.add(lblQModify);

        lblNModi = new JLabel("Name Modification");
        lblNModi.setBounds(57, 57, 127, 16);
        contentPane.add(lblNModi);

        txtNModi = new JTextField();
        txtNModi.setBounds(226, 52, 130, 26);
        contentPane.add(txtNModi);
        txtNModi.setColumns(10);

        txtQModi = new JTextField();
        txtQModi.setBounds(226, 167, 130, 26);
        contentPane.add(txtQModi);
        txtQModi.setColumns(10);

        btnConfirm = new JButton("Confirm");
        btnConfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String nModi = txtNModi.getText();
                    String pModi = txtPModi.getText();
                    String qModi = txtQModi.getText();

                    // Validate price
                    if (!pModi.isEmpty() && !pModi.equals("None")) {
                        Double.parseDouble(pModi);
                    }

                    // Validate quantity
                    if (!qModi.isEmpty() && !qModi.equals("None")) {
                        Integer.parseInt(qModi);
                    }

                    modiConfirm(m, nModi, pModi, qModi);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(contentPane, "Invalid input for price or quantity. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnConfirm.setBounds(40, 222, 117, 29);
        contentPane.add(btnConfirm);

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchGUI.setVisible(true); // Go back to SearchGUI
                ModifyGUI.this.dispose(); // Close ModifyGUI
            }
        });
        btnCancel.setBounds(242, 222, 117, 29);
        contentPane.add(btnCancel);

        loadItem(m);
    }

    public void modiConfirm(mainGUI m, String nModi, String pModi, String qModi) {
        // Split the original item into parts
        npq = modiItem.split(","); // Split by comma to match the storage format

        // Assign values based on user input or retain original values
        NModi = (nModi != null && !nModi.trim().isEmpty() && !nModi.equals("None")) ? nModi : npq[0];
        PModi = (pModi != null && !pModi.trim().isEmpty() && !pModi.equals("None")) ? pModi : npq[1];
        QModi = (qModi != null && !qModi.trim().isEmpty() && !qModi.equals("None")) ? qModi : npq[2];

        try {
            // Validate numeric inputs for price and quantity
            Double.parseDouble(PModi); // Ensure price is a valid number
            Integer.parseInt(QModi); // Ensure quantity is a valid integer

            System.out.println("Before modification: " + m.allItems);

            // Replace the modified item in the list
            for (int i = 0; i < m.allItems.size(); i++) {
                if (m.allItems.get(i).equals(modiItem)) {
                    m.allItems.set(i, NModi + "," + PModi + "," + QModi); // Update the list
                    break;
                }
            }

            System.out.println("After modification: " + m.allItems);

            // Save updated list to the file
            fm = new FileManager("modifyGUI");
            fm.save(m.allItems);

            JOptionPane.showMessageDialog(this, "Item modified successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Refresh SearchGUI and return
            searchGUI.refreshTable(); // Refresh table in SearchGUI
            searchGUI.setVisible(true);
            this.dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for price or quantity. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void loadItem(mainGUI m) {
        fm = new FileManager("modifyGUI");
        m.allItems = fm.read(); // Ensure synchronization
    }
}
