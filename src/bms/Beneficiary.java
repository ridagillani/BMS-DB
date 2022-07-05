package bms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Beneficiary extends JPanel {
    JPanel beneficiaryPanel = new BeneficiaryList();
    JPanel addBeneficiaryPanel = new AddBeneficiary();
    JPanel updateBeneficiaryPanel = new UpdateBeneficiary();

    JButton remove = new JButton();

    String current = "view";

    String[][] beneficiaryList;

    int selectedRow;

    public Beneficiary(){

        JButton New = new JButton();
        New.setText("Add New Beneficiary");
        New.setSize(125, 60);
        New.setForeground(Color.darkGray);
        New.setBackground(Color.white);
        New.setFocusable(false);

        remove.setText("Remove Beneficiary");
        remove.setSize(125, 60);
        remove.setForeground(Color.darkGray);
        remove.setBackground(Color.white);
        remove.setFocusable(false);

        JButton update = new JButton();
        update.setText("Edit Beneficiary");
        update.setSize(125, 60);
        update.setForeground(Color.darkGray);
        update.setBackground(Color.white);
        update.setFocusable(false);

        JButton view = new JButton();
        view.setText("View Beneficiary");
        view.setSize(125, 60);
        view.setForeground(Color.darkGray);
        view.setBackground(Color.white);
        view.setFocusable(false);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(4, 1, 0, 50));
        buttons.setBounds(0, 0, 100, 100);

        view.addActionListener(new BeneficiaryAction());
        update.addActionListener(new BeneficiaryAction());
        New.addActionListener(new BeneficiaryAction());
        remove.addActionListener(e -> removePopup());

        buttons.add(view);
        buttons.add(New);
        buttons.add(update);
        buttons.add(remove);
        buttons.setBackground(Color.darkGray);
        buttons.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));

        setLayout(new BorderLayout(0, 0));

        add(buttons, BorderLayout.WEST);
        add(beneficiaryPanel, BorderLayout.CENTER);


        setVisible(true);

    }

    void removePopup() {
        JFrame f = new JFrame("Remove Beneficiary");
        f.setBounds(300, 300, 400, 130);
        f.setLayout(new GridLayout(2, 1));
        JLabel l = new JLabel("Do you want to remove the beneficiary? ");

        JButton b = new JButton("Yes");
        JButton b2 = new JButton("No");

        Container button = new Container();
        button.setLayout(new GridLayout(1, 2));

        b.addActionListener(e -> {
            removeBeneficiary();
            f.dispose();
        });
        b2.addActionListener(e -> f.dispose());

        button.add(b);
        button.add(b2);
        f.add(l);
        f.add(button);
        f.setVisible(true);

    }

    void removeBeneficiary() {
        boolean removed = SQL.removeBeneficiary(selectedRow);

        if (removed) {
            beneficiaryList = SQL.getBeneficiaryList(UserDetails.getUserID());

            switch (current) {
                case "view" -> remove(beneficiaryPanel);
                case "add" -> remove(addBeneficiaryPanel);
                case "update" -> remove(updateBeneficiaryPanel);
            }

            beneficiaryPanel = new BeneficiaryList();

            add(beneficiaryPanel, BorderLayout.CENTER);
            current = "view";
            revalidate();
            repaint();
        }

    }

    class BeneficiaryAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand().equalsIgnoreCase("Add New Beneficiary")) {
                switch (current) {
                    case "view" -> remove(beneficiaryPanel);
                    case "add" -> remove(addBeneficiaryPanel);
                    case "update" -> remove(updateBeneficiaryPanel);
                }

                add(addBeneficiaryPanel, BorderLayout.CENTER);
                current = "add";
                revalidate();
                repaint();
            } else if (e.getActionCommand().equalsIgnoreCase("Edit Beneficiary")) {
                switch (current) {
                    case "view" -> remove(beneficiaryPanel);
                    case "add" -> remove(addBeneficiaryPanel);
                    case "update" -> remove(updateBeneficiaryPanel);
                }

                updateBeneficiaryPanel = new UpdateBeneficiary();
                add(updateBeneficiaryPanel, BorderLayout.CENTER);
                current = "update";
                revalidate();
                repaint();
            } else if (e.getActionCommand().equalsIgnoreCase("View Beneficiary")) {

                switch (current) {
                    case "view" -> remove(beneficiaryPanel);
                    case "add" -> remove(addBeneficiaryPanel);
                    case "update" -> remove(updateBeneficiaryPanel);
                }

                beneficiaryPanel = new BeneficiaryList();

                add(beneficiaryPanel, BorderLayout.CENTER);
                current = "view";
                revalidate();
                repaint();
            }
        }
    }

    class BeneficiaryList extends JPanel {
        BeneficiaryList () {
            setLayout(new BorderLayout());
            JPanel viewPanel = new JPanel();
            viewPanel.setLayout(new BorderLayout());


            String[] column_name = {
                    "Beneficiary Name",
                    "Beneficiary Account No.",
                    "Last Transaction",
                    "Timestamp"
            };


            beneficiaryList = SQL.getBeneficiaryList(UserDetails.getUserID());

            JTable usertable = new JTable(beneficiaryList, column_name) {
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

    class AddBeneficiary extends JPanel {
        JTextField namef = new JTextField(20);
        JTextField accountNumberf = new JTextField(20);

        AddBeneficiary() {
            setLayout(new BorderLayout());

            JPanel updatePanel = new JPanel();
            updatePanel.setLayout(new GridLayout(9, 1, 0, 0));

            JLabel name = new JLabel("Beneficiary Name: ");
            name.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));


            JLabel password = new JLabel("Beneficiary Account Number: ");
            password.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));

            JButton add = new JButton();
            add.setText("Add");
            add.setBackground(Color.darkGray);
            add.setForeground(Color.white);
            add.addActionListener(e -> addBeneficiary());

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
            passGroup.add(accountNumberf);

            JPanel buttGroup = new JPanel();
            buttGroup.setLayout(new GridLayout(1, 2));
            buttGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            buttGroup.add(add);
            buttGroup.add(Cancel);

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

            updatePanel.add(nameGroup);
            updatePanel.add(passGroup);
            updatePanel.add(buttGroup);


            add(updatePanel, BorderLayout.CENTER);
            add(n1, BorderLayout.NORTH);
            add(n2, BorderLayout.WEST);
            add(n3, BorderLayout.EAST);
            add(n4, BorderLayout.SOUTH);
        }

        void addBeneficiary () {
            boolean added = SQL.addBeneficiary(UserDetails.getUserID(), namef.getText(), Integer.parseInt(accountNumberf.getText()));

            if(added) {
                beneficiaryList = SQL.getBeneficiaryList(UserDetails.getUserID());

                namef.setText("");
                accountNumberf.setText("");


                JFrame f = new JFrame("Beneficiary Added");
                f.setBounds(300, 300, 400, 100);
                f.setLayout(new GridLayout(2, 1));
                JLabel l = new JLabel("The beneficiary has been added");

                JButton b = new JButton("Okay");

                b.addActionListener(e -> f.dispose());

                f.add(l);
                f.add(b);
                f.setVisible(true);
            } else {

                JFrame f = new JFrame("Beneficiary not Added");
                f.setBounds(300, 300, 400, 100);
                f.setLayout(new GridLayout(2, 1));
                JLabel l = new JLabel("The beneficiary has not been added");

                JButton b = new JButton("Okay");

                b.addActionListener(e -> f.dispose());

                f.add(l);
                f.add(b);
                f.setVisible(true);

            }
        }
    }

    class UpdateBeneficiary extends JPanel {
        JTextField namef = new JTextField(20);
        JTextField accountNumberf = new JTextField(20);

        String[] selected = new String[4];

        UpdateBeneficiary() {
            getSelectedBeneficiary();

            setLayout(new BorderLayout());

            JPanel updatePanel = new JPanel();
            updatePanel.setLayout(new GridLayout(9, 1, 0, 0));

            JLabel name = new JLabel("Beneficiary Name: ");
            name.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
            namef.setText(selected[1]);


            JLabel password = new JLabel("Beneficiary Account Number: ");
            password.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
            accountNumberf.setText(selected[2]);

            JButton update = new JButton();
            update.setText("Update");
            update.setBackground(Color.darkGray);
            update.setForeground(Color.white);
            update.addActionListener(e -> updateBeneficiary());

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
            passGroup.add(accountNumberf);

            JPanel buttGroup = new JPanel();
            buttGroup.setLayout(new GridLayout(1, 2));
            buttGroup.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));
            buttGroup.add(update);
            buttGroup.add(Cancel);

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

            updatePanel.add(nameGroup);
            updatePanel.add(passGroup);
            updatePanel.add(buttGroup);

            add(updatePanel, BorderLayout.CENTER);
            add(n1, BorderLayout.NORTH);
            add(n2, BorderLayout.WEST);
            add(n3, BorderLayout.EAST);
            add(n4, BorderLayout.SOUTH);

        }

        void getSelectedBeneficiary() {
            for (String[] user : beneficiaryList) {
                if (Integer.parseInt(user[0]) == selectedRow) {
                    selected = user;
                    return;
                }
            }
        }

        void updateBeneficiary () {
            boolean added = SQL.updateBeneficiary(selectedRow, namef.getText(), Integer.parseInt(accountNumberf.getText()));

            if(added) {
                beneficiaryList = SQL.getBeneficiaryList(UserDetails.getUserID());

                namef.setText("");
                accountNumberf.setText("");


                JFrame f = new JFrame("Beneficiary Updated");
                f.setBounds(300, 300, 400, 100);
                f.setLayout(new GridLayout(2, 1));
                JLabel l = new JLabel("The beneficiary has been updated");

                JButton b = new JButton("Okay");

                b.addActionListener(e -> f.dispose());

                f.add(l);
                f.add(b);
                f.setVisible(true);
            } else {

                JFrame f = new JFrame("Beneficiary not Updated");
                f.setBounds(300, 300, 400, 100);
                f.setLayout(new GridLayout(2, 1));
                JLabel l = new JLabel("The beneficiary has not been updated");

                JButton b = new JButton("Okay");

                b.addActionListener(e -> f.dispose());

                f.add(l);
                f.add(b);
                f.setVisible(true);

            }
        }
    }
}
