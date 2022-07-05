package bms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeUsers extends JPanel {
    JPanel userPanel = new UserList();
    JPanel addUserPanel = new AddUser();
    JPanel updateUserPanel = new UpdateUser();

    JButton remove = new JButton();

    int selectedRow;

    String current = "view";

    String[][] userList;

    public EmployeeUsers() {
        setBackground(Color.white);

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

        view.addActionListener(new UserAction());
        New.addActionListener(new UserAction());
        update.addActionListener(new UserAction());
        remove.addActionListener(e -> removePopup());

        buttons.add(view);
        buttons.add(New);
        buttons.add(update);
        buttons.add(remove);
        buttons.setBackground(Color.darkGray);
        buttons.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));

        setLayout(new BorderLayout(0, 0));

        add(buttons, BorderLayout.WEST);
        add(userPanel, BorderLayout.CENTER);
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
            removeUser();
            f.dispose();
        });
        b2.addActionListener(e -> f.dispose());

        button.add(b);
        button.add(b2);
        f.add(l);
        f.add(button);
        f.setVisible(true);

    }

    void removeUser() {
        boolean removed = SQL.removeUser(selectedRow);

        if (removed) {
            userList = SQL.getUserList();

            switch (current) {
                case "view" -> remove(userPanel);
                case "add" -> remove(addUserPanel);
                case "update" -> remove(updateUserPanel);
            }

            userPanel = new UserList();

            add(userPanel, BorderLayout.CENTER);
            current = "view";
            revalidate();
            repaint();
        }

    }

    class UserAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand().equalsIgnoreCase("Add New User")) {
                switch (current) {
                    case "view" -> remove(userPanel);
                    case "add" -> remove(addUserPanel);
                    case "update" -> remove(updateUserPanel);
                }

                add(addUserPanel, BorderLayout.CENTER);
                current = "add";
                revalidate();
                repaint();
            } else if (e.getActionCommand().equalsIgnoreCase("Edit User")) {
                switch (current) {
                    case "view" -> remove(userPanel);
                    case "add" -> remove(addUserPanel);
                    case "update" -> remove(updateUserPanel);
                }

                updateUserPanel = new UpdateUser();
                add(updateUserPanel, BorderLayout.CENTER);
                current = "update";
                revalidate();
                repaint();
            } else if (e.getActionCommand().equalsIgnoreCase("View User")) {

                switch (current) {
                    case "view" -> remove(userPanel);
                    case "add" -> remove(addUserPanel);
                    case "update" -> remove(updateUserPanel);
                }

                userPanel = new UserList();

                add(userPanel, BorderLayout.CENTER);
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

            userList = SQL.getUserList();


            String[] column_name = {
                    "Customer ID",
                    "Customer Username",
                    "Father's Name",
                    "DOB",
                    "CNIC",
                    "Account Type"
            };


            JTable usertable = new JTable(userList, column_name) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            usertable.setAutoCreateRowSorter(true);
            usertable.setSelectionBackground(Color.LIGHT_GRAY);
            usertable.setRowSelectionAllowed(true);
            usertable.getSelectionModel().addListSelectionListener(event -> {
                if (!event.getValueIsAdjusting()) {
                    selectedRow = Integer.parseInt(usertable.getValueAt(usertable.getSelectedRow(), 0).toString());
                }
            });


            usertable.setRowHeight(35);
            viewPanel.add(usertable.getTableHeader(), BorderLayout.NORTH);
            viewPanel.add(usertable, BorderLayout.CENTER);


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
        JTextField namef = new JTextField(20);
        JTextField passwordf = new JTextField(20);
        JTextField father_namef = new JTextField(20);
        JTextField cnicf = new JTextField(20);
        JTextField dobf = new JTextField(20);
        Container typef = new Container();
        ButtonGroup typeGroup = new ButtonGroup();

        JRadioButton current;
        JRadioButton saving;


        public AddUser() {
            setLayout(new BorderLayout());

            JPanel updatePanel = new JPanel();
            updatePanel.setLayout(new GridLayout(9, 1, 0, 0));

            JLabel name = new JLabel("Customer Username");
            name.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));


            JLabel password = new JLabel("Password");
            password.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));

            JLabel father_name = new JLabel("Father's Name");
            father_name.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));


            JLabel cnic = new JLabel("CNIC");
            cnic.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));

            JLabel dob = new JLabel("DOB");
            dob.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));

            JLabel type = new JLabel("Account Type");
            type.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));



            JButton add = new JButton();
            add.setText("Add");
            add.setBackground(Color.darkGray);
            add.setForeground(Color.white);
            add.addActionListener(e -> addUser(namef.getText(), cnicf.getText(), dobf.getText(), father_namef.getText(), passwordf.getText()));

            JButton Cancel = new JButton();
            Cancel.setText("Cancel");
            Cancel.setBackground(Color.darkGray);
            Cancel.setForeground(Color.white);

            JPanel nameGroup = new JPanel();
            nameGroup.setLayout(new GridLayout(1, 2));
            nameGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            nameGroup.add(name);
            nameGroup.add(namef);

            JPanel passGroup = new JPanel();
            passGroup.setLayout(new GridLayout(1, 2));
            passGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            passGroup.add(password);
            passGroup.add(passwordf);

            JPanel fatherGroup = new JPanel();
            fatherGroup.setLayout(new GridLayout(1, 2));
            fatherGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            fatherGroup.add(father_name);
            fatherGroup.add(father_namef);


            JPanel cnicGroup = new JPanel();
            cnicGroup.setLayout(new GridLayout(1, 2));
            cnicGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            cnicGroup.add(cnic);
            cnicGroup.add(cnicf);


            JPanel dobGroup = new JPanel();
            dobGroup.setLayout(new GridLayout(1, 2));
            dobGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            dobGroup.add(dob);
            dobGroup.add(dobf);

            current = new JRadioButton("Current");
            saving = new JRadioButton("Savings");

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


            JPanel buttGroup = new JPanel();
            buttGroup.setLayout(new GridLayout(1, 2));
            buttGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            buttGroup.add(add);
            buttGroup.add(Cancel);

            updatePanel.add(nameGroup);
            updatePanel.add(passGroup);
            updatePanel.add(fatherGroup);
            updatePanel.add(cnicGroup);
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

        void addUser(String name, String cnic, String dob, String father, String password) {
            String accountType = "";
            if (saving.isSelected()) {
                accountType = saving.getText();
            }

            if (current.isSelected()) {
                accountType = current.getText();
            }

            boolean added = SQL.signup(name, cnic, dob, father, password, accountType);

            if(added) {
                userList = SQL.getUserList();

                namef.setText("");
                father_namef.setText("");
                passwordf.setText("");
                cnicf.setText("");
                dobf.setText("");


                JFrame f = new JFrame("User Added");
                f.setBounds(300, 300, 400, 100);
                f.setLayout(new GridLayout(2, 1));
                JLabel l = new JLabel("The user has been added");

                JButton b = new JButton("Okay");

                b.addActionListener(e -> f.dispose());

                f.add(l);
                f.add(b);
                f.setVisible(true);
            } else {

                JFrame f = new JFrame("User not Added");
                f.setBounds(300, 300, 400, 100);
                f.setLayout(new GridLayout(2, 1));
                JLabel l = new JLabel("The user has not been added");

                JButton b = new JButton("Okay");

                b.addActionListener(e -> f.dispose());

                f.add(l);
                f.add(b);
                f.setVisible(true);

            }


        }


    }


    class UpdateUser extends JPanel {
        Container typef = new Container();
        ButtonGroup typeGroup = new ButtonGroup();

        JRadioButton current;
        JRadioButton saving;

        String[] selected = new String[6];

        UpdateUser() {

            getSelectedUser();

            setLayout(new BorderLayout());

            setLayout(new BorderLayout());

            JPanel updatePanel = new JPanel();
            updatePanel.setLayout(new GridLayout(9, 1, 0, 0));

            JLabel name = new JLabel("Customer Username");
            name.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
            JTextField namef = new JTextField(20);
            namef.setText(selected[1]);

            JLabel father_name = new JLabel("Father's Name");
            father_name.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
            JTextField father_namef = new JTextField(20);
            father_namef.setText(selected[2]);


            JLabel cnic = new JLabel("CNIC");
            cnic.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
            JTextField cnicf = new JTextField(20);
            cnicf.setText(selected[4]);

            JLabel dob = new JLabel("DOB");
            dob.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
            JTextField dobf = new JTextField(20);
            dobf.setText(selected[3]);

            JLabel type = new JLabel("Account Type");
            type.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));

            current = new JRadioButton("Current");
            saving = new JRadioButton("Savings");

            typeGroup.add(current);
            typeGroup.add(saving);

            typef.setLayout(new GridLayout(1, 2));
            typef.add(saving);
            typef.add(current);


            if(selected[5].equals("Savings")) {
                saving.setSelected(true);
                current.setSelected(false);
            } else if (selected[5].equals("Current")) {
                saving.setSelected(false);
                current.setSelected(true);
            }




            JButton update = new JButton();
            update.setText("Update");
            update.setBackground(Color.darkGray);
            update.setForeground(Color.white);
            update.addActionListener(e -> updateUser(selectedRow, namef.getText(), father_namef.getText(), cnicf.getText(), dobf.getText()));

            JButton Cancel = new JButton();
            Cancel.setText("Cancel");
            Cancel.setBackground(Color.darkGray);
            Cancel.setForeground(Color.white);

            JPanel nameGroup = new JPanel();
            nameGroup.setLayout(new GridLayout(1, 2));
            nameGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            nameGroup.add(name);
            nameGroup.add(namef);


            JPanel fatherGroup = new JPanel();
            fatherGroup.setLayout(new GridLayout(1, 2));
            fatherGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            fatherGroup.add(father_name);
            fatherGroup.add(father_namef);


            JPanel cnicGroup = new JPanel();
            cnicGroup.setLayout(new GridLayout(1, 2));
            cnicGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            cnicGroup.add(cnic);
            cnicGroup.add(cnicf);


            JPanel dobGroup = new JPanel();
            dobGroup.setLayout(new GridLayout(1, 2));
            dobGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            dobGroup.add(dob);
            dobGroup.add(dobf);

            JPanel typeGroup = new JPanel();
            typeGroup.setLayout(new GridLayout(1, 2));
            typeGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            typeGroup.add(type);
            typeGroup.add(typef);

            JPanel buttGroup = new JPanel();
            buttGroup.setLayout(new GridLayout(1, 2));
            buttGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            buttGroup.add(update);
            buttGroup.add(Cancel);

            updatePanel.add(nameGroup);
            updatePanel.add(fatherGroup);
            updatePanel.add(cnicGroup);
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

        void getSelectedUser () {

            for (String[] user : userList) {
                if (Integer.parseInt(user[0]) == selectedRow) {
                    selected = user;
                    return;
                }
            }
            selected[5] = "";
        }

        void updateUser(int id, String name, String fatherName, String cnic, String dob) {
            String accountType = "";
            if (saving.isSelected()) {
                accountType = saving.getText();
            }

            if (current.isSelected()) {
                accountType = current.getText();
            }

            boolean updated = SQL.updateUser(id, name, fatherName, cnic, dob, accountType);


            if (updated) {
                userList = SQL.getUserList();

                JFrame f = new JFrame("User Update");
                f.setBounds(300,300, 400,100);
                f.setLayout(new GridLayout(2,1));
                JLabel l = new JLabel("The user has been updated");

                JButton b = new JButton("Okay");

                b.addActionListener(e -> f.dispose());

                f.add(l);
                f.add(b);
                f.setVisible(true);
            } else {
                JFrame f = new JFrame("User Update");
                f.setBounds(300,300, 400,100);
                f.setLayout(new GridLayout(2,1));
                JLabel l = new JLabel("The user unable to be updated");

                JButton b = new JButton("Okay");

                b.addActionListener(e -> f.dispose());

                f.add(l);
                f.add(b);
                f.setVisible(true);
            }

        }

    }
}
