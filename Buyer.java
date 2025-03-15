package OOP_final_project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.List;

public class Buyer {
    private ShoppingCart cart;

    public Buyer() {
        this.cart = new ShoppingCart();
    }

    public void addToCart(Item item) {
        cart.addItem(item);
    }

    public void removeFromCart(Item item) {
        cart.removeItem(item);
    }

    public double checkout() {
        double total = cart.calculateTotal();
        cart = new ShoppingCart();  
        return total;
    }

    public List<Item> getCartItems() {
        return cart.getItems();
    }

    public ShoppingCart getShoppingCart() {
        return cart;
    }
}
