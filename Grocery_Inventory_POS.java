
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.border.LineBorder;

public class Grocery_Inventory_POS extends JFrame {

    private ArrayList<String> productNames = new ArrayList<>();
    private ArrayList<Double> productPrices = new ArrayList<>();
    private ArrayList<Integer> productQuantities = new ArrayList<>();

    // Components shared between both systems
    private JLabel productJLB, qtyJLB, totalJLB, totalValueJLB, searchJLB, nameJLB, priceJLB, quantiJLB;
    private JTextField qtyJTF, nameJTF, priceJTF, quantiJTF, searchJTF;
    private JComboBox<String> productJCB;
    private JButton buyJBTN, inventoryJBTN, printJBTN, addJBTN, updateJBTN, deleteJBTN, posJBTN;
    private DefaultTableModel tableTM;
    private JTable itemsTB;
    private JScrollPane itemJSP;
    private JLabel titleJLB;

    private DefaultTableModel model;
    private JPanel leftpanelJP, rightpanelJP, mainJP;
    private JScrollPane scrollJSP;
    private JTable tableJT;

    public Grocery_Inventory_POS() {
        initializeMainMenu();
    }

    private void initializeMainMenu() {
        Container cp = getContentPane();
        cp.setLayout(null);
        cp.setBackground(new Color(0, 92, 120));

        inventoryJBTN = new JButton("GO TO Inventory and POS System");
        inventoryJBTN.setBounds(85, 75, 330, 80);
        inventoryJBTN.setFocusPainted(false);
        inventoryJBTN.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 17));
        inventoryJBTN.setForeground(Color.white);
        inventoryJBTN.setBackground(new Color(232, 141, 103));
        inventoryJBTN.setBorder(new LineBorder(Color.white));
        cp.add(inventoryJBTN);

        inventoryJBTN.addActionListener(e -> {
            new GroceryInventory().setVisible(true);
            dispose();
        });
        setTitle("Inventory and Point of Sale (POS) system");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class GroceryInventory extends JFrame {
        public GroceryInventory() {
            InventoryGUI();
            preloadSampleItems();
            loadTable();
        }

        private void InventoryGUI() {
            Container cp = getContentPane();
            cp.setLayout(new BorderLayout());
            cp.setBackground(new Color(0, 92, 120));

            // Left Panel - Form Fields and Table
            leftpanelJP = new JPanel(null);
            leftpanelJP.setBackground(new Color(0, 92, 120));
            leftpanelJP.setBounds(10, 10, 380, 460);

            nameJLB = new JLabel("Name: ");
            nameJLB.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
            nameJLB.setForeground(Color.white);
            nameJLB.setBounds(10, 50, 80, 20);
            leftpanelJP.add(nameJLB);

            nameJTF = new JTextField();
            nameJTF.setFont(new Font("Arial", Font.PLAIN, 14));
            nameJTF.setForeground(new Color(0, 92, 120));
            nameJTF.setBackground(new Color(243, 247, 236));
            nameJTF.setBounds(10, 75, 200, 25);
            leftpanelJP.add(nameJTF);

            priceJLB = new JLabel("Price: ");
            priceJLB.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
            priceJLB.setForeground(Color.white);
            priceJLB.setBounds(10, 110, 80, 20);
            leftpanelJP.add(priceJLB);

            priceJTF = new JTextField();
            priceJTF.setFont(new Font("Arial", Font.PLAIN, 14));
            priceJTF.setForeground(new Color(0, 92, 120));
            priceJTF.setBackground(new Color(243, 247, 236));
            priceJTF.setBounds(10, 135, 200, 25);
            leftpanelJP.add(priceJTF);

            qtyJLB = new JLabel("Quantity: ");
            qtyJLB.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
            qtyJLB.setForeground(Color.white);
            qtyJLB.setBounds(10, 170, 80, 20);
            leftpanelJP.add(qtyJLB);

            qtyJTF = new JTextField();
            qtyJTF.setFont(new Font("Arial", Font.PLAIN, 14));
            qtyJTF.setForeground(new Color(0, 92, 120));
            qtyJTF.setBackground(new Color(243, 247, 236));
            qtyJTF.setBounds(10, 195, 200, 25);
            leftpanelJP.add(qtyJTF);

            model = new DefaultTableModel(new Object[]{"Name", "Price", "Quantity"}, 0);
            tableJT = new JTable(model);
            tableJT.setFont(new Font("Arial", Font.PLAIN, 12));
            tableJT.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
            tableJT.setRowHeight(20);
            tableJT.setForeground(Color.black);
            tableJT.setBackground(new Color(243, 247, 236));

            scrollJSP = new JScrollPane(tableJT);
            scrollJSP.setBounds(10, 230, 360, 220);
            leftpanelJP.add(scrollJSP);

            // Right Panel - Search and Buttons
            rightpanelJP = new JPanel(null);
            rightpanelJP.setBackground(new Color(0, 105, 137));
            rightpanelJP.setBounds(400, 10, 170, 460);

            searchJLB = new JLabel("Search: ");
            searchJLB.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
            searchJLB.setForeground(Color.white);
            searchJLB.setBounds(10, 20, 80, 20);
            rightpanelJP.add(searchJLB);

            searchJTF = new JTextField();
            searchJTF.setFont(new Font("Arial", Font.PLAIN, 14));
            searchJTF.setForeground(new Color(0, 92, 120));
            searchJTF.setBackground(new Color(243, 247, 236));
            searchJTF.setBounds(10, 45, 150, 25);
            rightpanelJP.add(searchJTF);

            addJBTN = new JButton("Add");
            addJBTN.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
            addJBTN.setForeground(Color.white);
            addJBTN.setBackground(new Color(232, 141, 103));
            addJBTN.setBorder(new LineBorder(Color.white));
            addJBTN.setBounds(10, 90, 150, 30);
            rightpanelJP.add(addJBTN);

            updateJBTN = new JButton("Update");
            updateJBTN.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
            updateJBTN.setForeground(Color.white);
            updateJBTN.setBackground(new Color(232, 141, 103));
            updateJBTN.setBorder(new LineBorder(Color.white));
            updateJBTN.setBounds(10, 130, 150, 30);
            rightpanelJP.add(updateJBTN);

            deleteJBTN = new JButton("Delete");
            deleteJBTN.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
            deleteJBTN.setForeground(Color.white);
            deleteJBTN.setBackground(new Color(232, 141, 103));
            deleteJBTN.setBorder(new LineBorder(Color.white));
            deleteJBTN.setBounds(10, 170, 150, 30);
            rightpanelJP.add(deleteJBTN);

            posJBTN = new JButton("Go To POS");
            posJBTN.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
            posJBTN.setForeground(Color.white);
            posJBTN.setBackground(new Color(232, 141, 103));
            posJBTN.setBorder(new LineBorder(Color.white));
            posJBTN.setBounds(10, 420, 150, 30);
            rightpanelJP.add(posJBTN);

            // Main Panel
            mainJP = new JPanel(null);
            mainJP.setBackground(new Color(0, 92, 120));
            mainJP.setPreferredSize(new Dimension(580, 480));
            mainJP.add(leftpanelJP);
            mainJP.add(rightpanelJP);

            cp.add(mainJP, BorderLayout.CENTER);

            // Event Listeners
            addJBTN.addActionListener(e -> addItem());
            updateJBTN.addActionListener(e -> updateItem());
            deleteJBTN.addActionListener(e -> deleteItem());
            searchJTF.addActionListener(e -> searchItem());
            tableJT.getSelectionModel().addListSelectionListener(e -> loadFields());
            posJBTN.addActionListener(e -> {
                new GroceryPOS().setVisible(true);
                dispose();
            });

            titleJLB = new JLabel("Inventory System");
            titleJLB.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
            titleJLB.setForeground(Color.white);
            titleJLB.setBounds(10, 10, 250, 30);
            leftpanelJP.add(titleJLB);

            setTitle("Inventory Management");
            setSize(600, 520);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
        }

        private void preloadSampleItems() {
            for (int i = 1; i <= 50; i++) {
                productNames.add("Product " + i);
                productPrices.add(10.0 + i);
                productQuantities.add(i * 2);
            }
        }

        private void loadTable() {
            model.setRowCount(0);
            for (int i = 0; i < productNames.size(); i++) {
                model.addRow(new Object[]{productNames.get(i), productPrices.get(i), productQuantities.get(i)});
            }
        }

        private void loadFields() {
            int viewRow = tableJT.getSelectedRow();
            if (viewRow >= 0) {
                String name = (String) model.getValueAt(viewRow, 0);
                int realIndex = productNames.indexOf(name);
                if (realIndex >= 0) {
                    nameJTF.setText(productNames.get(realIndex));
                    priceJTF.setText(String.valueOf(productPrices.get(realIndex)));
                    qtyJTF.setText(String.valueOf(productQuantities.get(realIndex)));
                }
            }
        }

        private void addItem() {
            try {
                String name = nameJTF.getText().trim();
                double price = Double.parseDouble(priceJTF.getText().trim());
                int qty = Integer.parseInt(qtyJTF.getText().trim());

                if (name.isEmpty() || price < 0 || qty < 0) {
                    JOptionPane.showMessageDialog(this, "Name cannot be empty and values must be positive",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (name.matches(".*\\d.*")) {
                    JOptionPane.showMessageDialog(this, "Name cannot contain numbers",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                productNames.add(name);
                productPrices.add(price);
                productQuantities.add(qty);
                loadTable();
                clear();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for price and quantity",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void updateItem() {
            int viewRow = tableJT.getSelectedRow();
            if (viewRow < 0) {
                JOptionPane.showMessageDialog(this, "Please select an item to update",
                        "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                String name = nameJTF.getText().trim();
                double price = Double.parseDouble(priceJTF.getText().trim());
                int qty = Integer.parseInt(qtyJTF.getText().trim());

                if (name.isEmpty() || price < 0 || qty < 0) {
                    JOptionPane.showMessageDialog(this, "Name cannot be empty and values must be positive",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (name.matches(".*\\d.*")) {
                    JOptionPane.showMessageDialog(this, "Name cannot contain numbers",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int index = productNames.indexOf(model.getValueAt(viewRow, 0).toString());
                if (index >= 0) {
                    productNames.set(index, name);
                    productPrices.set(index, price);
                    productQuantities.set(index, qty);
                    loadTable();
                    clear();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for price and quantity",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void deleteItem() {
            int viewRow = tableJT.getSelectedRow();
            if (viewRow < 0) {
                JOptionPane.showMessageDialog(this, "Please select an item to delete",
                        "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int index = productNames.indexOf(model.getValueAt(viewRow, 0).toString());
            if (index >= 0) {
                productNames.remove(index);
                productPrices.remove(index);
                productQuantities.remove(index);
                loadTable();
                clear();
            }
        }

        private void searchItem() {
            String query = searchJTF.getText().toLowerCase().trim();
            model.setRowCount(0);
            for (int i = 0; i < productNames.size(); i++) {
                if (productNames.get(i).toLowerCase().contains(query)) {
                    model.addRow(new Object[]{productNames.get(i), productPrices.get(i), productQuantities.get(i)});
                }
            }
        }

        private void clear() {
            nameJTF.setText("");
            priceJTF.setText("");
            qtyJTF.setText("");
            tableJT.clearSelection();
        }
    }

    class GroceryPOS extends JFrame {
        public GroceryPOS() {
            posGUI();
            itemDropdown();
        }
        private void preloadSampleItems() {
            for (int i = 1; i <= 50; i++) {
                productNames.add("Product " + i);
                productPrices.add(10.0 + i);
                productQuantities.add(i * 2);
            }
        }

        private void posGUI() {
            Container cp = getContentPane();
            cp.setLayout(null);
            cp.setBackground(new Color(0, 92, 120));

            productJLB = new JLabel("Product: ");
            productJLB.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
            productJLB.setForeground(Color.white);
            productJLB.setBounds(20, 30, 80, 25);
            cp.add(productJLB);

            productJCB = new JComboBox<>();
            productJCB.setForeground(new Color(0, 92, 120));
            productJCB.setBackground(new Color(243, 247, 236));
            productJCB.setBounds(100, 30, 180, 25);
            cp.add(productJCB);

            quantiJLB = new JLabel("Quantity: ");
            quantiJLB.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
            quantiJLB.setForeground(Color.white);
            quantiJLB.setBounds(20, 65, 80, 25);
            cp.add(quantiJLB);

            quantiJTF = new JTextField();
            quantiJTF.setForeground(new Color(0, 92, 120));
            quantiJTF.setBackground(new Color(243, 247, 236));
            quantiJTF.setBounds(100, 65, 180, 25);
            cp.add(quantiJTF);

            buyJBTN = new JButton("Buy");
            buyJBTN.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
            buyJBTN.setForeground(Color.white);
            buyJBTN.setBackground(new Color(232, 141, 103));
            buyJBTN.setBorder(new LineBorder(Color.white));
            buyJBTN.setBounds(300, 40, 100, 40);
            cp.add(buyJBTN);

            printJBTN = new JButton("Print Receipt");
            printJBTN.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
            printJBTN.setForeground(Color.white);
            printJBTN.setBackground(new Color(232, 141, 103));
            printJBTN.setBorder(new LineBorder(Color.white));
            printJBTN.setBounds(410, 40, 150, 40);
            cp.add(printJBTN);

            inventoryJBTN = new JButton("Go to Inventory");
            inventoryJBTN.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
            inventoryJBTN.setForeground(Color.white);
            inventoryJBTN.setBorder(new LineBorder(Color.white));
            inventoryJBTN.setBackground(new Color(232, 141, 103));
            inventoryJBTN.setBounds(20, 400, 300, 50);
            cp.add(inventoryJBTN);

            totalJLB = new JLabel("Total: ");
            totalJLB.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
            totalJLB.setForeground(Color.white);
            totalJLB.setBounds(450, 410, 200, 30);
            cp.add(totalJLB);

            totalValueJLB = new JLabel("$0.00");
            totalValueJLB.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
            totalValueJLB.setForeground(Color.white);
            totalValueJLB.setBounds(500, 410, 200, 30);
            cp.add(totalValueJLB);

            String[] col = {"Item/s", "Quantity", "Price", "Total"};
            tableTM = new DefaultTableModel(col, 0);
            itemsTB = new JTable(tableTM);
            itemJSP = new JScrollPane(itemsTB);
            itemJSP.setBounds(20, 110, 540, 280);
            cp.add(itemJSP);

            buyJBTN.addActionListener(e -> POSsales());
            printJBTN.addActionListener(e -> PrintReceipt());
            inventoryJBTN.addActionListener(e -> {
                new GroceryInventory().setVisible(true);
                dispose();
            });

            setTitle("Point of Sales (POS)");
            setSize(600, 500);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            
        }

        private void itemDropdown() {
            productJCB.removeAllItems();
            for (int i = 0; i < productNames.size(); i++) {
                if (productQuantities.get(i) > 0) {
                    productJCB.addItem(productNames.get(i));
                }
            }
        }

        private void PrintReceipt() {
            JOptionPane.showMessageDialog(this, "Printed Successfully", "Successful", JOptionPane.INFORMATION_MESSAGE);
        }

        private void POSsales() {
            String selectedProduct = (String) productJCB.getSelectedItem();
            if (selectedProduct == null) {
                JOptionPane.showMessageDialog(this, "No product selected.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int index = productNames.indexOf(selectedProduct);
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Product not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int qty = Integer.parseInt(quantiJTF.getText().trim());
                if (qty <= 0) {
                    JOptionPane.showMessageDialog(this,
                            "Enter a positive number.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double price = productPrices.get(index);
                double total = price * qty;

                Object[] row = {selectedProduct, qty,
                    String.format("$%.2f", price),
                    String.format("$%.2f", total)};
                tableTM.addRow(row);

                updateTotal();
                quantiJTF.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Enter a valid integer for quantity.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void updateTotal() {
            double total = 0;
            for (int i = 0; i < itemsTB.getRowCount(); i++) {
                String totalStr = (String) itemsTB.getValueAt(i, 3);
                total += Double.parseDouble(totalStr.substring(1));
            }
            totalValueJLB.setText(String.format("$%.2f", total));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Grocery_Inventory_POS());
    }
}
