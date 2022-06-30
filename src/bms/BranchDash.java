package bms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BranchDash extends JFrame {

    JPanel employeePanel = new EmployeeList();
    JPanel addEmployeePanel = new AddEmployee();
    JPanel updateEmployeePanel = new UpdateEmployee();

    JPanel panel;

    JButton remove = new JButton();

    int selectedRow;

    String current = "view";

    String[][] employeeList;

    public BranchDash () {
        setTitle("Branch Dashboard");
        setSize(1280,720);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel header = new JPanel();
        header.setBackground(Color.DARK_GRAY);
        header.setLayout(new GridBagLayout());
        header.setPreferredSize(new Dimension(1280,100));

        JLabel heading = new JLabel();
        heading.setText("List of all the employees");
        heading.setForeground(Color.WHITE);
        heading.setBackground(Color.darkGray);
        heading.setFont(new Font("Cinzel", Font.BOLD, 30));


        Container dashLabel = new Container();
        dashLabel.setLayout(new GridBagLayout());
        dashLabel.add(heading);

        header.add(dashLabel);

        panel = new JPanel();

        JButton New = new JButton();
        New.setText("Add New Employee");
        New.setSize(125, 60);
        New.setForeground(Color.darkGray);
        New.setBackground(Color.white);
        New.setFocusable(false);

        remove.setText("Remove Employee");
        remove.setSize(125, 60);
        remove.setForeground(Color.darkGray);
        remove.setBackground(Color.white);
        remove.setFocusable(false);

        JButton update = new JButton();
        update.setText("Edit Employee");
        update.setSize(125, 60);
        update.setForeground(Color.darkGray);
        update.setBackground(Color.white);
        update.setFocusable(false);

        JButton view = new JButton();
        view.setText("View Employee");
        view.setSize(125, 60);
        view.setForeground(Color.darkGray);
        view.setBackground(Color.white);
        view.setFocusable(false);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(4, 1, 0, 50));
        buttons.setBounds(0, 0, 100, 100);

        view.addActionListener(new EmployeeAction());
        New.addActionListener(new EmployeeAction());
        update.addActionListener(new EmployeeAction());
        remove.addActionListener(e -> removePopup());

        buttons.add(view);
        buttons.add(New);
        buttons.add(update);
        buttons.add(remove);
        buttons.setBackground(Color.darkGray);
        buttons.setBorder(BorderFactory.createEmptyBorder(50,0,50,0));





        panel.setLayout(new BorderLayout(0, 0));

        panel.add(buttons, BorderLayout.WEST);
        panel.add(employeePanel, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    void removePopup () {
        JFrame f = new JFrame("Product Remove");
        f.setBounds(300,300, 400,130);
        f.setLayout(new GridLayout(2,1));
        JLabel l = new JLabel("Do you want to remove the employee? ");

        JButton b = new JButton("Yes");
        JButton b2 = new JButton("No");

        Container button = new Container();
        button.setLayout(new GridLayout(1,2));

        b.addActionListener(e -> {
            removeEmployee();
            f.dispose();
        });
        b2.addActionListener(e -> f.dispose());

        button.add(b);
        button.add(b2);
        f.add(l);
        f.add(button);
        f.setVisible(true);

    }

    void removeEmployee () {
        boolean removed = SQL.removeEmployee(selectedRow);

        if (removed) {
            employeeList = SQL.getEmployeeList();

            switch (current) {
                case "view" -> panel.remove(employeePanel);
                case "add" -> panel.remove(addEmployeePanel);
                case "update" -> panel.remove(updateEmployeePanel);
            }

            employeePanel = new EmployeeList();

            panel.add(employeePanel, BorderLayout.CENTER);
            current = "view";
            revalidate();
            repaint();
        }

    }

    class EmployeeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("Add New Employee")) {
                switch (current) {
                    case "view" -> panel.remove(employeePanel);
                    case "add" -> panel.remove(addEmployeePanel);
                    case "update" -> panel.remove(updateEmployeePanel);
                }

                panel.add(addEmployeePanel, BorderLayout.CENTER);
                current = "add";
                revalidate();
                repaint();
            }
            else if (e.getActionCommand().equalsIgnoreCase("Edit Employee")) {
                switch (current) {
                    case "view" -> panel.remove(employeePanel);
                    case "add" -> panel.remove(addEmployeePanel);
                    case "update" -> panel.remove(updateEmployeePanel);
                }

                updateEmployeePanel = new UpdateEmployee();
                panel.add(updateEmployeePanel, BorderLayout.CENTER);
                current = "update";
                revalidate();
                repaint();
            }

            else if (e.getActionCommand().equalsIgnoreCase("View Employee")) {

                switch (current) {
                    case "view" -> panel.remove(employeePanel);
                    case "add" -> panel.remove(addEmployeePanel);
                    case "update" -> panel.remove(updateEmployeePanel);
                }

                employeePanel = new EmployeeList();

                panel.add(employeePanel, BorderLayout.CENTER);
                current = "view";
                revalidate();
                repaint();
            }
        }
    }


    class EmployeeList extends JPanel {
        EmployeeList () {
            setLayout(new BorderLayout());

            JPanel viewPanel = new JPanel();
            viewPanel.setLayout(new BorderLayout());


            employeeList = SQL.getEmployeeList();


            String[] column_name = {
                    "Employee ID",
                    "Employee Name",
                    "Password",
                    "CNIC",
                    "Employee Salary",
                    "Branch",
            };


            JTable productable = new JTable(employeeList, column_name) {
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
            n1.setFont(new Font("Arial", Font.BOLD,30));

            JLabel n2 = new JLabel("a");
            n2.setForeground(Color.darkGray);
            n2.setFont(new Font("Arial", Font.BOLD,80));

            JLabel n3 = new JLabel("a");
            n3.setForeground(Color.darkGray);
            n3.setFont(new Font("Arial", Font.BOLD,80));

            JLabel n4 = new JLabel("a");
            n4.setForeground(Color.darkGray);
            n4.setFont(new Font("Arial", Font.BOLD,30));

            setBackground(Color.darkGray);

            add(viewPanel, BorderLayout.CENTER);
            add(n1, BorderLayout.NORTH);
            add(n2, BorderLayout.WEST);
            add(n3, BorderLayout.EAST);
            add(n4,BorderLayout.SOUTH);

        }

    }

    class AddEmployee extends JPanel {
        JTextField namef = new JTextField();
        JTextField passwordf = new JTextField();
        JTextField salaryf = new JTextField(20);
        JTextField cnicf = new JTextField(20);


        public AddEmployee()
        {
            setLayout(new BorderLayout());

            JPanel updatePanel = new JPanel();
            updatePanel.setLayout(new GridLayout(7,1, 0, 0));

            JLabel name = new JLabel("Employee Username");
            name.setBorder(BorderFactory.createEmptyBorder(0,40,0,0));


            JLabel password = new JLabel("Password");
            password.setBorder(BorderFactory.createEmptyBorder(0,40,0,0));

            JLabel salary = new JLabel("Salary");
            salary.setBorder(BorderFactory.createEmptyBorder(0,40,0,0));


            JLabel cnic = new JLabel("CNIC");
            cnic.setBorder(BorderFactory.createEmptyBorder(0,40,0,0));


            JButton add = new JButton();
            add.setText("Add");
            add.setBackground(Color.darkGray);
            add.setForeground(Color.white);
            add.addActionListener(e -> addEmployee(namef.getText(), passwordf.getText(), cnicf.getText(), salaryf.getText()));

            JButton Cancel = new JButton();
            Cancel.setText("Cancel");
            Cancel.setBackground(Color.darkGray);
            Cancel.setForeground(Color.white);

            JPanel nameGroup = new JPanel();
            nameGroup.setLayout(new GridLayout(1, 2));
            nameGroup.setBorder(BorderFactory.createEmptyBorder(25, 180, 25, 180));
            nameGroup.add(name);
            nameGroup.add(namef);


            JPanel passwordGroup = new JPanel();
            passwordGroup.setLayout(new GridLayout(1, 2));
            passwordGroup.setBorder(BorderFactory.createEmptyBorder(25, 180, 25, 180));
            passwordGroup.add(password);
            passwordGroup.add(passwordf);


            JPanel cnicGroup = new JPanel();
            cnicGroup.setLayout(new GridLayout(1, 2));
            cnicGroup.setBorder(BorderFactory.createEmptyBorder(25, 180, 25, 180));
            cnicGroup.add(cnic);
            cnicGroup.add(cnicf);


            JPanel salaryGroup = new JPanel();
            salaryGroup.setLayout(new GridLayout(1, 2));
            salaryGroup.setBorder(BorderFactory.createEmptyBorder(25, 180, 25, 180));
            salaryGroup.add(salary);
            salaryGroup.add(salaryf);


            JPanel buttGroup = new JPanel();
            buttGroup.setLayout(new GridLayout(1, 2));
            buttGroup.setBorder(BorderFactory.createEmptyBorder(25, 180, 25, 180));
            buttGroup.add(add);
            buttGroup.add(Cancel);

            updatePanel.add(nameGroup);
            updatePanel.add(passwordGroup);
            updatePanel.add(cnicGroup);
            updatePanel.add(salaryGroup);
            updatePanel.add(buttGroup);

            JLabel n1 = new JLabel("a");
            n1.setForeground(Color.darkGray);
            n1.setFont(new Font("Arial", Font.BOLD,30));

            JLabel n2 = new JLabel("a");
            n2.setForeground(Color.darkGray);
            n2.setFont(new Font("Arial", Font.BOLD,80));

            JLabel n3 = new JLabel("a");
            n3.setForeground(Color.darkGray);
            n3.setFont(new Font("Arial", Font.BOLD,80));

            JLabel n4 = new JLabel("a");
            n4.setForeground(Color.darkGray);
            n4.setFont(new Font("Arial", Font.BOLD,30));

            setBackground(Color.darkGray);


            add(updatePanel, BorderLayout.CENTER);
            add(n1, BorderLayout.NORTH);
            add(n2, BorderLayout.WEST);
            add(n3, BorderLayout.EAST);
            add(n4,BorderLayout.SOUTH);
        }

        void addEmployee (String name, String password, String cnic, String salary) {

            boolean added = SQL.addEmployee(name, password, cnic, salary);

            if (added) {
                employeeList = SQL.getEmployeeList();

                namef.setText("");
                passwordf.setText("");
                cnicf.setText("");
                salaryf.setText("");


                JFrame f = new JFrame("Employee Added");
                f.setBounds(300, 300, 400, 100);
                f.setLayout(new GridLayout(2, 1));
                JLabel l = new JLabel("The employee has been added");

                JButton b = new JButton("Okay");

                b.addActionListener(e -> f.dispose());

                f.add(l);
                f.add(b);
                f.setVisible(true);
            } else {

                JFrame f = new JFrame("Employee not Added");
                f.setBounds(300, 300, 400, 100);
                f.setLayout(new GridLayout(2, 1));
                JLabel l = new JLabel("The employee has not been added");

                JButton b = new JButton("Okay");

                b.addActionListener(e -> f.dispose());

                f.add(l);
                f.add(b);
                f.setVisible(true);

            }


        }


    }


    class UpdateEmployee extends JPanel {
        String[] selected = new String[5];

        UpdateEmployee() {

            getSelectedEmployee();

            setLayout(new BorderLayout());

            JPanel updatePanel = new JPanel();
            updatePanel.setLayout(new GridLayout(7,1, 0, 0));

            JLabel name = new JLabel("Employee Username");
            name.setBorder(BorderFactory.createEmptyBorder(0,40,0,0));
            JTextField namef = new JTextField();
            namef.setText(selected[1]);


            JLabel password = new JLabel("Password");
            password.setBorder(BorderFactory.createEmptyBorder(0,40,0,0));
            JTextField passwordf = new JTextField(20);
            passwordf.setText(selected[2]);

            JLabel cnic = new JLabel("CNIC");
            cnic.setBorder(BorderFactory.createEmptyBorder(0,40,0,0));
            JTextField cnicf = new JTextField(20);
            cnicf.setText(selected[3]);

            JLabel salary = new JLabel("Salary");
            salary.setBorder(BorderFactory.createEmptyBorder(0,40,0,0));
            JTextField salaryf = new JTextField(20);
            salaryf.setText(selected[4]);


            JButton edit = new JButton();
            edit.setText("Update");
            edit.setBackground(Color.darkGray);
            edit.setForeground(Color.white);
            edit.addActionListener(e -> updateEmployee(selectedRow, namef.getText(), passwordf.getText(), cnicf.getText(), salaryf.getText()));


            JButton Cancel = new JButton();
            Cancel.setText("Cancel");
            Cancel.setBackground(Color.darkGray);
            Cancel.setForeground(Color.white);

            JPanel nameGroup = new JPanel();
            nameGroup.setLayout(new GridLayout(1, 2));
            nameGroup.setBorder(BorderFactory.createEmptyBorder(25, 180, 25, 180));
            nameGroup.add(name);
            nameGroup.add(namef);

            JPanel passGroup = new JPanel();
            passGroup.setLayout(new GridLayout(1, 2));
            passGroup.setBorder(BorderFactory.createEmptyBorder(25, 180, 25, 180));
            passGroup.add(password);
            passGroup.add(passwordf);

            JPanel cnicGroup = new JPanel();
            cnicGroup.setLayout(new GridLayout(1, 2));
            cnicGroup.setBorder(BorderFactory.createEmptyBorder(25, 180, 25, 180));
            cnicGroup.add(cnic);
            cnicGroup.add(cnicf);

            JPanel salaryGroup = new JPanel();
            salaryGroup.setLayout(new GridLayout(1, 2));
            salaryGroup.setBorder(BorderFactory.createEmptyBorder(25, 180, 25, 180));
            salaryGroup.add(salary);
            salaryGroup.add(salaryf);


            JPanel buttGroup = new JPanel();
            buttGroup.setLayout(new GridLayout(1, 2));
            buttGroup.setBorder(BorderFactory.createEmptyBorder(25, 180, 25, 180));
            buttGroup.add(edit);
            buttGroup.add(Cancel);

            updatePanel.add(nameGroup);
            updatePanel.add(passGroup);
            updatePanel.add(cnicGroup);
            updatePanel.add(salaryGroup);
            updatePanel.add(buttGroup);

            JLabel n1 = new JLabel("a");
            n1.setForeground(Color.darkGray);
            n1.setFont(new Font("Arial", Font.BOLD,30));

            JLabel n2 = new JLabel("a");
            n2.setForeground(Color.darkGray);
            n2.setFont(new Font("Arial", Font.BOLD,80));

            JLabel n3 = new JLabel("a");
            n3.setForeground(Color.darkGray);
            n3.setFont(new Font("Arial", Font.BOLD,80));

            JLabel n4 = new JLabel("a");
            n4.setForeground(Color.darkGray);
            n4.setFont(new Font("Arial", Font.BOLD,30));

            setBackground(Color.darkGray);


            add(updatePanel, BorderLayout.CENTER);
            add(n1, BorderLayout.NORTH);
            add(n2, BorderLayout.WEST);
            add(n3, BorderLayout.EAST);
            add(n4,BorderLayout.SOUTH);
        }

        void getSelectedEmployee () {

            for (String[] employee : employeeList) {
                if (Integer.parseInt(employee[0]) == selectedRow) {
                    selected = employee;
                    break;
                }
            }
        }

        void updateEmployee (int id, String name, String password, String cnic, String salary) {

            boolean updated = SQL.updateEmployee(id, name, password, cnic, salary);

            if (updated) {
                employeeList = SQL.getEmployeeList();

                JFrame f = new JFrame("Product Update");
                f.setBounds(300,300, 400,100);
                f.setLayout(new GridLayout(2,1));
                JLabel l = new JLabel("The product has been updated");

                JButton b = new JButton("Okay");

                b.addActionListener(e -> f.dispose());

                f.add(l);
                f.add(b);
                f.setVisible(true);
            } else {
                JFrame f = new JFrame("Product Update");
                f.setBounds(300,300, 400,100);
                f.setLayout(new GridLayout(2,1));
                JLabel l = new JLabel("The product unable to be updated");

                JButton b = new JButton("Okay");

                b.addActionListener(e -> f.dispose());

                f.add(l);
                f.add(b);
                f.setVisible(true);
            }


        }

    }

}
