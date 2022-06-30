package bms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeDash extends JFrame {
    JPanel userPanel = new UserList();
    JPanel addUserPanel = new AddUser();
    JPanel updateUserPanel = new UpdateUser();

    JPanel panel;

    JButton remove = new JButton();

    int selectedRow;

    String current = "view";

    public EmployeeDash() {
        setTitle("Employee Dashboard");
        setSize(1280, 720);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel header = new JPanel();
        header.setBackground(Color.DARK_GRAY);
        header.setLayout(new GridBagLayout());
        header.setPreferredSize(new Dimension(1280, 100));

        JLabel heading = new JLabel();
        heading.setText("List of all the users");
        heading.setForeground(Color.WHITE);
        heading.setBackground(Color.darkGray);
        heading.setFont(new Font("Cinzel", Font.BOLD, 30));


        Container dashLabel = new Container();
        dashLabel.setLayout(new GridBagLayout());
        dashLabel.add(heading);

        header.add(dashLabel);

        panel = new JPanel();

        JButton New = new JButton();
        New.setText("Add New User");
        New.setSize(125, 60);
        New.setForeground(Color.darkGray);
        New.setBackground(Color.white);
        New.setFocusable(false);

        remove.setText("Remove User");
        remove.setSize(125, 60);
        remove.setForeground(Color.darkGray);
        remove.setBackground(Color.white);
        remove.setFocusable(false);

        JButton update = new JButton();
        update.setText("Edit User");
        update.setSize(125, 60);
        update.setForeground(Color.darkGray);
        update.setBackground(Color.white);
        update.setFocusable(false);

        JButton view = new JButton();
        view.setText("View User");
        view.setSize(125, 60);
        view.setForeground(Color.darkGray);
        view.setBackground(Color.white);
        view.setFocusable(false);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(4, 1, 0, 50));
        buttons.setBounds(0, 0, 100, 100);

        view.addActionListener(new EmployeeDash.UserAction());
        New.addActionListener(new EmployeeDash.UserAction());
        update.addActionListener(new EmployeeDash.UserAction());
        remove.addActionListener(e -> removePopup());

        buttons.add(view);
        buttons.add(New);
        buttons.add(update);
        buttons.add(remove);
        buttons.setBackground(Color.darkGray);
        buttons.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));


        panel.setLayout(new BorderLayout(0, 0));

        panel.add(buttons, BorderLayout.WEST);
        panel.add(userPanel, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    void removePopup() {
        JFrame f = new JFrame("Remove User");
        f.setBounds(300, 300, 400, 130);
        f.setLayout(new GridLayout(2, 1));
        JLabel l = new JLabel("Do you want to remove the user? ");

        JButton b = new JButton("Yes");
        JButton b2 = new JButton("No");

        Container button = new Container();
        button.setLayout(new GridLayout(1, 2));

        b.addActionListener(e -> {
            removeProduct();
            f.dispose();
        });
        b2.addActionListener(e -> f.dispose());

        button.add(b);
        button.add(b2);
        f.add(l);
        f.add(button);
        f.setVisible(true);

    }

    void removeProduct() {

//        for (int i = 0; i < products.size(); i++) {
//            if (products.get(i).getPId() == selectedRow) {
//                products.remove(i);
//            }
//        }
//
//        products = fileM.writeProducts(products);
//
//
//        switch (current) {
//            case "view" -> remove(productPanel);
//            case "add" -> remove(addProductPanel);
//            case "update" -> remove(updateProductPanel);
//        }
//
//        productPanel = new ProductList();
//
//        add(productPanel, BorderLayout.CENTER);
//        current = "view";
//        revalidate();
//        repaint();
    }

    class UserAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand().equalsIgnoreCase("Add New User")) {
                switch (current) {
                    case "view" -> panel.remove(userPanel);
                    case "add" -> panel.remove(addUserPanel);
                    case "update" -> panel.remove(updateUserPanel);
                }

                panel.add(addUserPanel, BorderLayout.CENTER);
                current = "add";
                revalidate();
                repaint();
            } else if (e.getActionCommand().equalsIgnoreCase("Edit User")) {
                switch (current) {
                    case "view" -> panel.remove(userPanel);
                    case "add" -> panel.remove(addUserPanel);
                    case "update" -> panel.remove(updateUserPanel);
                }

              //  updateEmployeePanel = new EmployeeDash().UpdateUser();
                panel.add(updateUserPanel, BorderLayout.CENTER);
                current = "update";
                revalidate();
                repaint();
            } else if (e.getActionCommand().equalsIgnoreCase("View User")) {

                switch (current) {
                    case "view" -> panel.remove(userPanel);
                    case "add" -> panel.remove(addUserPanel);
                    case "update" -> panel.remove(updateUserPanel);
                }

            //    employeePanel = new EmployeeDash().EmployeeList();

                panel.add(userPanel, BorderLayout.CENTER);
                current = "view";
                revalidate();
                repaint();
            }
        }
    }


    class UserList extends JPanel {
        UserList() {
            setLayout(new BorderLayout());

            JPanel viewPanel = new JPanel();
            viewPanel.setLayout(new BorderLayout());

//            products = fileM.readProduct();


            String[] column_name = {
                    "Customer ID",
                    "Customer Username",
                    "Father's Name",
                    "DOB",
                    "CNIC",
                    "Account Type"
            };

//            String[][] data = new String[products.size()][6];
            String[][] data = {};

//            for (int i=0; i < products.size(); i++) {
//                String[] n = {Integer.toString(products.get(i).getPId()), products.get(i).getPname(), products.get(i).getDescription(), Integer.toString(products.get(i).getPQuantity()), products.get(i).getCategory(), Double.toString(products.get(i).getPrice()), Double.toString(products.get(i).getCost())};
//                data[i] = n;
//
//            }

            JTable productable = new JTable(data, column_name) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            productable.setAutoCreateRowSorter(true);
            productable.setSelectionBackground(Color.LIGHT_GRAY);
            productable.setRowSelectionAllowed(true);
            productable.getSelectionModel().addListSelectionListener(event -> {
                if (!event.getValueIsAdjusting()) {
                    selectedRow = Integer.parseInt(productable.getValueAt(productable.getSelectedRow(), 0).toString());
                }
            });


            productable.setRowHeight(35);
            viewPanel.add(productable.getTableHeader(), BorderLayout.NORTH);
            viewPanel.add(productable, BorderLayout.CENTER);


            JLabel n1 = new JLabel("a");
            n1.setForeground(Color.darkGray);
            n1.setFont(new Font("Arial", Font.BOLD, 30));

            JLabel n2 = new JLabel("a");
            n2.setForeground(Color.darkGray);
            n2.setFont(new Font("Arial", Font.BOLD, 80));

            JLabel n3 = new JLabel("a");
            n3.setForeground(Color.darkGray);
            n3.setFont(new Font("Arial", Font.BOLD, 80));

            JLabel n4 = new JLabel("a");
            n4.setForeground(Color.darkGray);
            n4.setFont(new Font("Arial", Font.BOLD, 30));

            setBackground(Color.darkGray);

            add(viewPanel, BorderLayout.CENTER);
            add(n1, BorderLayout.NORTH);
            add(n2, BorderLayout.WEST);
            add(n3, BorderLayout.EAST);
            add(n4, BorderLayout.SOUTH);

        }

    }

    class AddUser extends JPanel {
        JTextField namef = new JTextField();
        JTextField descriptionf = new JTextField();
        JTextField quantityf = new JTextField(20);
        JTextField pricef = new JTextField(20);
        JTextField costf = new JTextField(20);
        JTextField dobf = new JTextField(20);
        Container typef = new Container();
        ButtonGroup typeGroup = new ButtonGroup();

        JRadioButton current;
        JRadioButton saving;


        //  JComboBox j1;

        public AddUser() {
            setLayout(new BorderLayout());

            JPanel updatePanel = new JPanel();
            updatePanel.setLayout(new GridLayout(9, 1, 0, 0));

            JLabel name = new JLabel("Customer Username");
            name.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));


            JLabel description = new JLabel("Customer Id");
            description.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));

            JLabel quantity = new JLabel("Password");
            quantity.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));

            JLabel price = new JLabel("Father's Name");
            price.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));


            JLabel cost = new JLabel("CNIC");
            cost.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));

            JLabel dob = new JLabel("DOB");
            dob.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));

            JLabel type = new JLabel("Account Type");
            type.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));


//            JLabel CategoryLabel = new JLabel("Category");
//            CategoryLabel.setBorder(BorderFactory.createEmptyBorder(0,40,0,0));


//            String[] s1 = {"Technology","Grocery","Crockery","Clothing" , "Perfumes"};
//            j1 = new JComboBox(s1);


            JButton add = new JButton();
            add.setText("Add");
            add.setBackground(Color.darkGray);
            add.setForeground(Color.white);
            add.addActionListener(e -> addProduct(namef.getText(), descriptionf.getText(), quantityf.getText(), pricef.getText(), toString(), costf.getText()));

            JButton Cancel = new JButton();
            Cancel.setText("Cancel");
            Cancel.setBackground(Color.darkGray);
            Cancel.setForeground(Color.white);

            JPanel nameGroup = new JPanel();
            nameGroup.setLayout(new GridLayout(1, 2));
            nameGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            nameGroup.add(name);
            nameGroup.add(namef);

            JPanel descGroup = new JPanel();
            descGroup.setLayout(new GridLayout(1, 2));
            descGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            descGroup.add(description);
            descGroup.add(descriptionf);

            JPanel priceGroup = new JPanel();
            priceGroup.setLayout(new GridLayout(1, 2));
            priceGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            priceGroup.add(price);
            priceGroup.add(pricef);


            JPanel costGroup = new JPanel();
            costGroup.setLayout(new GridLayout(1, 2));
            costGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            costGroup.add(cost);
            costGroup.add(costf);


            JPanel quanGroup = new JPanel();
            quanGroup.setLayout(new GridLayout(1, 2));
            quanGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            quanGroup.add(quantity);
            quanGroup.add(quantityf);

            JPanel dobGroup = new JPanel();
            dobGroup.setLayout(new GridLayout(1, 2));
            dobGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            dobGroup.add(dob);
            dobGroup.add(dobf);

            current = new JRadioButton("Current");
            saving = new JRadioButton("Saving");

            typeGroup.add(current);
            typeGroup.add(saving);

            typef.setLayout(new GridLayout(1, 2));
            typef.add(saving);
            typef.add(current);

            JPanel typeGroup = new JPanel();
            typeGroup.setLayout(new GridLayout(1, 2));
            typeGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            typeGroup.add(type);
            typeGroup.add(typef);

//            JPanel catGroup = new JPanel();
//            catGroup.setLayout(new GridLayout(1, 2));
//            catGroup.setBorder(BorderFactory.createEmptyBorder(25, 180, 25, 180));
            // catGroup.add(CategoryLabel);
            //catGroup.add(j1);

            JPanel buttGroup = new JPanel();
            buttGroup.setLayout(new GridLayout(1, 2));
            buttGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            buttGroup.add(add);
            buttGroup.add(Cancel);

            updatePanel.add(nameGroup);
            updatePanel.add(descGroup);
            updatePanel.add(priceGroup);
            updatePanel.add(costGroup);
            updatePanel.add(quanGroup);
            updatePanel.add(dobGroup);
            updatePanel.add(typeGroup);
            updatePanel.add(buttGroup);

            JLabel n1 = new JLabel("a");
            n1.setForeground(Color.darkGray);
            n1.setFont(new Font("Arial", Font.BOLD, 30));

            JLabel n2 = new JLabel("a");
            n2.setForeground(Color.darkGray);
            n2.setFont(new Font("Arial", Font.BOLD, 80));

            JLabel n3 = new JLabel("a");
            n3.setForeground(Color.darkGray);
            n3.setFont(new Font("Arial", Font.BOLD, 80));

            JLabel n4 = new JLabel("a");
            n4.setForeground(Color.darkGray);
            n4.setFont(new Font("Arial", Font.BOLD, 30));

            setBackground(Color.darkGray);


            add(updatePanel, BorderLayout.CENTER);
            add(n1, BorderLayout.NORTH);
            add(n2, BorderLayout.WEST);
            add(n3, BorderLayout.EAST);
            add(n4, BorderLayout.SOUTH);
        }

        void addProduct(String name, String description, String quantity, String price, String category, String cost) {
//            Product last;
//            int id = 0;
//            if (products.size() > 0) {
//                last = products.get(products.size() - 1);
//                id = last.getPId() + 1;
//            }
//
//            Product newProd = new Product(id, name, description, Integer.parseInt(quantity), category, Double.parseDouble(price), Double.parseDouble(cost));
//
//            products.add(newProd);
//
//            products = fileM.writeProducts(products);

            namef.setText("");
            descriptionf.setText("");
            quantityf.setText("");
            pricef.setText("");


            JFrame f = new JFrame("Product Added");
            f.setBounds(300, 300, 400, 100);
            f.setLayout(new GridLayout(2, 1));
            JLabel l = new JLabel("The product has been added");

            JButton b = new JButton("Okay");

            b.addActionListener(e -> f.dispose());

            f.add(l);
            f.add(b);
            f.setVisible(true);


        }


    }


    class UpdateUser extends JPanel {
        JTextField namef = new JTextField();
        JTextField descriptionf = new JTextField();
        JTextField quantityf = new JTextField(20);
        JTextField pricef = new JTextField(20);
        JTextField costf = new JTextField(20);
        JTextField dobf = new JTextField(20);
        Container typef = new Container();
        ButtonGroup typeGroup = new ButtonGroup();

        JRadioButton current;
        JRadioButton saving;
//        Product selected;

        UpdateUser() {

            getSelectedProduct();

            setLayout(new BorderLayout());

            setLayout(new BorderLayout());

            JPanel updatePanel = new JPanel();
            updatePanel.setLayout(new GridLayout(9, 1, 0, 0));

            JLabel name = new JLabel("Customer Username");
            name.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));


            JLabel description = new JLabel("Customer Id");
            description.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));

            JLabel quantity = new JLabel("Password");
            quantity.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));

            JLabel price = new JLabel("Father's Name");
            price.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));


            JLabel cost = new JLabel("CNIC");
            cost.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));

            JLabel dob = new JLabel("DOB");
            dob.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));

            JLabel type = new JLabel("Account Type");
            type.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));


//            JLabel CategoryLabel = new JLabel("Category");
//            CategoryLabel.setBorder(BorderFactory.createEmptyBorder(0,40,0,0));


//            String[] s1 = {"Technology","Grocery","Crockery","Clothing" , "Perfumes"};
//            j1 = new JComboBox(s1);


            JButton update = new JButton();
            update.setText("Update");
            update.setBackground(Color.darkGray);
            update.setForeground(Color.white);
//            add.addActionListener(e -> addProduct(namef.getText(), descriptionf.getText(), quantityf.getText(), pricef.getText(), toString(), costf.getText()));

            JButton Cancel = new JButton();
            Cancel.setText("Cancel");
            Cancel.setBackground(Color.darkGray);
            Cancel.setForeground(Color.white);

            JPanel nameGroup = new JPanel();
            nameGroup.setLayout(new GridLayout(1, 2));
            nameGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            nameGroup.add(name);
            nameGroup.add(namef);

            JPanel descGroup = new JPanel();
            descGroup.setLayout(new GridLayout(1, 2));
            descGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            descGroup.add(description);
            descGroup.add(descriptionf);

            JPanel priceGroup = new JPanel();
            priceGroup.setLayout(new GridLayout(1, 2));
            priceGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            priceGroup.add(price);
            priceGroup.add(pricef);


            JPanel costGroup = new JPanel();
            costGroup.setLayout(new GridLayout(1, 2));
            costGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            costGroup.add(cost);
            costGroup.add(costf);


            JPanel quanGroup = new JPanel();
            quanGroup.setLayout(new GridLayout(1, 2));
            quanGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            quanGroup.add(quantity);
            quanGroup.add(quantityf);

            JPanel dobGroup = new JPanel();
            dobGroup.setLayout(new GridLayout(1, 2));
            dobGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            dobGroup.add(dob);
            dobGroup.add(dobf);

            current = new JRadioButton("Current");
            saving = new JRadioButton("Saving");

            typeGroup.add(current);
            typeGroup.add(saving);

            typef.setLayout(new GridLayout(1, 2));
            typef.add(saving);
            typef.add(current);

            JPanel typeGroup = new JPanel();
            typeGroup.setLayout(new GridLayout(1, 2));
            typeGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            typeGroup.add(type);
            typeGroup.add(typef);

//            JPanel catGroup = new JPanel();
//            catGroup.setLayout(new GridLayout(1, 2));
//            catGroup.setBorder(BorderFactory.createEmptyBorder(25, 180, 25, 180));
            // catGroup.add(CategoryLabel);
            //catGroup.add(j1);

            JPanel buttGroup = new JPanel();
            buttGroup.setLayout(new GridLayout(1, 2));
            buttGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            buttGroup.add(update);
            buttGroup.add(Cancel);

            updatePanel.add(nameGroup);
            updatePanel.add(descGroup);
            updatePanel.add(priceGroup);
            updatePanel.add(costGroup);
            updatePanel.add(quanGroup);
            updatePanel.add(dobGroup);
            updatePanel.add(typeGroup);
            updatePanel.add(buttGroup);

            JLabel n1 = new JLabel("a");
            n1.setForeground(Color.darkGray);
            n1.setFont(new Font("Arial", Font.BOLD, 30));

            JLabel n2 = new JLabel("a");
            n2.setForeground(Color.darkGray);
            n2.setFont(new Font("Arial", Font.BOLD, 80));

            JLabel n3 = new JLabel("a");
            n3.setForeground(Color.darkGray);
            n3.setFont(new Font("Arial", Font.BOLD, 80));

            JLabel n4 = new JLabel("a");
            n4.setForeground(Color.darkGray);
            n4.setFont(new Font("Arial", Font.BOLD, 30));

            setBackground(Color.darkGray);


            add(updatePanel, BorderLayout.CENTER);
            add(n1, BorderLayout.NORTH);
            add(n2, BorderLayout.WEST);
            add(n3, BorderLayout.EAST);
            add(n4, BorderLayout.SOUTH);
        }

        void getSelectedProduct() {
//            for (Product product : products) {
//                if (product.getPId() == selectedRow) {
//                    selected = product;
//                    break;
//                }
//            }
        }

        void updateProduct(String name, String description, String quantity, String price, String category, String cost) {
//            selected.setPname(name);
//            selected.setDescription(description);
//            selected.setPQuantity(Integer.parseInt(quantity));
//            selected.setPrice(Double.parseDouble(price));
//            selected.setCategory(category);
//            selected.setCost(Double.parseDouble(cost));

//            for (int i = 0; i < products.size(); i++) {
//                if (products.get(i).getPId() == selected.getPId()) {
//                    products.set(i, selected);
//                }
//            }

//            products = fileM.writeProducts(products);


            JFrame f = new JFrame("Product Update");
            f.setBounds(300, 300, 400, 100);
            f.setLayout(new GridLayout(2, 1));
            JLabel l = new JLabel("The product has been updated");

            JButton b = new JButton("Okay");

            b.addActionListener(e -> f.dispose());

            f.add(l);
            f.add(b);
            f.setVisible(true);
        }

    }
}


