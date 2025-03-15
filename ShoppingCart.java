package OOP_final_project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ShoppingCart {
    private List<Item> items;
    private FileManager fm;
    private mainGUI m;
    private String itemName;
    private String[] Item;
    private String ItemName;
    private int quantity;
    public ShoppingCart() {
        items = new ArrayList<>();
        m = mainGUI.getInstance();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public double calculateTotal() {
        double total = 0;
        fm = new FileManager("ShoppingCart");

        // Create a new list to store updated items
        Vector<String> updatedItems = new Vector<>(m.allItems);

        for (Item item : items) {
            total += item.getUnitPrice();
            itemName = item.getName();

            // Update the quantity in the inventory
            for (int i = 0; i < updatedItems.size(); i++) {
                String[] parts = updatedItems.get(i).split(",");
                if (parts[0].equals(itemName)) {
                    int currentQuantity = Integer.parseInt(parts[2]);
                    if (currentQuantity > 0) {
                        currentQuantity -= 1;
                        if (currentQuantity == 0) {
                            // Remove the item line if quantity becomes 0
                            updatedItems.remove(i);
                        } else {
                            // Update the item line with new quantity
                            updatedItems.set(i, parts[0] + "," + parts[1] + "," + currentQuantity);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, 
                            "Item \"" + itemName + "\" is already out of stock.", 
                            "Out of Stock", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
            }
        }

        // Save updated inventory to file
        fm.save(updatedItems);

        // Update the revenue file
        updateRevenue(total);

        clear();
        return total;
    }

    public List<Item> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }
    private void updateRevenue(double total) {
        FileManager revenueManager = new FileManager("revenueGUI");
        Vector<String> revenueData = revenueManager.read();
        revenueData.add(String.valueOf(total)); // Add the total revenue for this transaction
        revenueManager.save(revenueData);
    }
}

