package OOP_final_project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Item {
    private String name;
    private double unitPrice;
    private int quantity;
    public Item(String name, double unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = 1;
    }
    public Item (String name, double unitPrice, int quantity) {
    	this.name = name;
    	this.unitPrice = unitPrice;
    	this.quantity = quantity;
    }
    
    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
    public int getQuantity() {
    	return  quantity;
    }
    public void setName(String name) {
    	this.name = name;
    }
    public void setUnitPrice(double unitPrice) {
    	this.unitPrice = unitPrice;
    }
    public void setQuantity(int quantity) {
    	this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + " - $" + String.format("%.2f", unitPrice);
    }


    @Override
    public boolean equals(Object object) {
        if(object ==this)
            return true;
        if(!(object instanceof Item))
            return false;
        Item item = (Item) object;
        return this.name.equals(item.getName()) && Math.abs(this.getUnitPrice() - item.getUnitPrice()) < 0.001;
    }
}
