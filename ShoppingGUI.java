package OOP_final_project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class ShoppingGUI extends JFrame {

    private JPanel contentPane;
    private JTextField searchField;
    private JList<String> itemList;
    private DefaultListModel<String> itemListModel;
    private JTextArea cartArea;
    private JButton btnAddToCart, btnRemoveFromCart, btnCheckout, btnReviewItem, btnSearch, btnCancel;
    private Buyer buyer;
    private mainGUI m;

    public ShoppingGUI() {
        buyer = new Buyer();
        m = mainGUI.getInstance();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSearch = new JLabel("Search Item:");
        lblSearch.setBounds(20, 20, 100, 25);
        contentPane.add(lblSearch);

        searchField = new JTextField();
        searchField.setBounds(120, 20, 200, 25);
        contentPane.add(searchField);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(330, 20, 100, 25);
        btnSearch.addActionListener(e -> searchItem());
        contentPane.add(btnSearch);

        itemListModel = new DefaultListModel<>();
        itemList = new JList<>(itemListModel);
        JScrollPane scrollPaneItems = new JScrollPane(itemList);
        scrollPaneItems.setBounds(20, 60, 250, 200);
        contentPane.add(scrollPaneItems);

        cartArea = new JTextArea();
        cartArea.setEditable(false);
        JScrollPane scrollPaneCart = new JScrollPane(cartArea);
        scrollPaneCart.setBounds(300, 60, 250, 200);
        contentPane.add(scrollPaneCart);

        btnAddToCart = new JButton("Add to Cart");
        btnAddToCart.setBounds(20, 280, 120, 30);
        btnAddToCart.addActionListener(e -> addToCart());
        contentPane.add(btnAddToCart);

        btnRemoveFromCart = new JButton("Remove from Cart");
        btnRemoveFromCart.setBounds(150, 280, 150, 30);
        btnRemoveFromCart.addActionListener(e -> removeFromCart());
        contentPane.add(btnRemoveFromCart);

        btnCheckout = new JButton("Checkout");
        btnCheckout.setBounds(320, 280, 100, 30);
        btnCheckout.addActionListener(e -> checkout());
        contentPane.add(btnCheckout);

        btnReviewItem = new JButton("Review Item");
        btnReviewItem.setBounds(430, 280, 120, 30);
        btnReviewItem.addActionListener(e -> reviewItem());
        contentPane.add(btnReviewItem);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(250, 330, 100, 30);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainGUI.getInstance().setVisible(true);
                ShoppingGUI.this.setVisible(false);
            }
        });
        contentPane.add(btnCancel);

        loadItems();
    }

    private void loadItems() {
    	m.inventory.clear();
        FileManager fm = new FileManager("ShoppingGUI");
        m.allItems = fm.read();
        for (String line : m.allItems) {
            String[] npq = line.split(",");
            m.inventory.add(new Item(npq[0], Double.parseDouble(npq[1]), Integer.parseInt(npq[2])));
        }
        refreshItemList();
    }

    private void refreshItemList() {
        itemListModel.clear();
        for (Item item : m.inventory) {
            itemListModel.addElement(item.getName() + " - $" + item.getUnitPrice());
        }
    }
    private void updateCartArea() {
        cartArea.setText("");
        for (Item item : buyer.getShoppingCart().getItems()) {
            cartArea.append(item.getName() + " - $" + item.getUnitPrice() + "\n");
        }
    }

    private void searchItem() {
        String searchQuery = searchField.getText().toLowerCase();
        List<Item> filteredItems = m.inventory.stream()
                .filter(item -> item.getName().toLowerCase().contains(searchQuery))
                .collect(Collectors.toList());

        itemListModel.clear();
        if (filteredItems.isEmpty()) {
            itemListModel.addElement("No items found.");
        } else {
            for (Item item : filteredItems) {
                itemListModel.addElement(item.getName() + " - $" + item.getUnitPrice());
            }
        }
    }

    private void addToCart() {
        String selectedItem = itemList.getSelectedValue();
        if (selectedItem != null && !selectedItem.equals("No items found.")) {
            String[] itemDetails = selectedItem.split(" - \\$");
            String name = itemDetails[0];
            double price = Double.parseDouble(itemDetails[1]);

            // Ensure we find the correct item from the inventory
            Item item = m.inventory.stream()
                    .filter(i -> i.getName().equals(name) && i.getUnitPrice() == price)
                    .findFirst()
                    .orElse(null);

            if (item != null) {
                buyer.addToCart(item);
                updateCartArea();
                JOptionPane.showMessageDialog(this, "Item added to cart successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Item not found in inventory.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a valid item to add to the cart.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeFromCart() {
        String selectedItem = itemList.getSelectedValue();
        if (selectedItem != null) {
            String[] itemDetails = selectedItem.split(" - \\$");
            String name = itemDetails[0];
            double price = Double.parseDouble(itemDetails[1]);
            Item item = new Item(name, price);
            buyer.removeFromCart(item);
            updateCartArea();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to remove.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void checkout() {
        if (buyer.getShoppingCart().getItems().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty. Add items to checkout.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double total = buyer.getShoppingCart().calculateTotal();

        String[] paymentOptions = {"Credit Card", "Debit Card", "Gift Card"};
        String selectedPayment = (String) JOptionPane.showInputDialog(
            this,
            "Select your payment method:",
            "Payment Options",
            JOptionPane.QUESTION_MESSAGE,
            null,
            paymentOptions,
            paymentOptions[0]
        );

        if (selectedPayment == null) {
            JOptionPane.showMessageDialog(this, "Checkout canceled.", 
                    "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        switch (selectedPayment) {
            case "Credit Card":
                JOptionPane.showMessageDialog(this, 
                        "You selected Credit Card. Total: $" + total,
                        "Payment Confirmation", 
                        JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Debit Card":
                JOptionPane.showMessageDialog(this, 
                        "You selected Debit Card. Total: $" + total,
                        "Payment Confirmation", 
                        JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Gift Card":
                JOptionPane.showMessageDialog(this, 
                        "You selected Gift Card. Total: $" + total,
                        "Payment Confirmation", 
                        JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(this, 
                        "Invalid payment option selected.",
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
        }
        loadItems();
        refreshItemList();
        updateCartArea();
    }

    private void reviewItem() {
        String selectedItem = itemList.getSelectedValue();
        if (selectedItem != null && !selectedItem.equals("No items found.")) {
            String reviewText = JOptionPane.showInputDialog(this, "Write your review:");
            if (reviewText != null && !reviewText.isEmpty()) {
                String itemName = selectedItem.split(" - ")[0];
                String date = java.time.LocalDate.now().toString();

                // Save the review to the file
                FileManager reviewManager = new FileManager("reviewGUI");
                Vector<String> reviews = reviewManager.read();
                reviews.add(itemName + ", " + date + ", " + reviewText);
                reviewManager.save(reviews);

                JOptionPane.showMessageDialog(this, "Review submitted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Review cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to review.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
