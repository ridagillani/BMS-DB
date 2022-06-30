package bms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogIn extends JFrame {
    JButton login;
    JTextField username, password;
    JLabel userLabel, passLabel, mainLabel, accountTypeLabel;
    JRadioButton user, employee, branch;
    ButtonGroup userType;

    public LogIn() {
        setTitle("User Log-in");
        setSize(1280, 720);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        panel.setLayout(null);

        mainLabel = new JLabel();
        mainLabel.setForeground(Color.WHITE);
        mainLabel.setFont(new Font("Cinzel", Font.BOLD, 30));
        mainLabel.setText("Use your log-in credentials");
        mainLabel.setBounds(400, 200, 700, 60);

        userLabel = new JLabel();
        userLabel.setForeground(Color.WHITE);
        userLabel.setText("Username:");


        username = new JTextField(15);


        passLabel = new JLabel();
        passLabel.setText("Password:");
        passLabel.setForeground(Color.WHITE);

        password = new JPasswordField(15);


        accountTypeLabel = new JLabel();
        accountTypeLabel.setForeground(Color.WHITE);
        accountTypeLabel.setText("Account Type:");

        employee = new JRadioButton("Employee");
        employee.setForeground(Color.WHITE);
        employee.setBackground(Color.darkGray);

        user = new JRadioButton("User");
        user.setForeground(Color.WHITE);
        user.setBackground(Color.darkGray);

        branch = new JRadioButton("Branch");
        branch.setForeground(Color.WHITE);
        branch.setBackground(Color.darkGray);

        userType = new ButtonGroup();
        userType.add(user);
        userType.add(employee);
        userType.add(branch);

        login = new JButton();
        login.setText("SUBMIT");
        login.setBackground(Color.WHITE);
        login.addActionListener(new LogIn.Action());
        login.setFocusable(false);

        login.setBounds(440, 450, 400, 30);
        userLabel.setBounds(440, 300, 200, 20);
        username.setBounds(640, 300, 200, 20);
        passLabel.setBounds(440, 350, 200, 20);
        password.setBounds(640, 350, 200, 20);
        accountTypeLabel.setBounds(440, 400, 200, 20);
        employee.setBounds(640, 400, 100, 20);
        user.setBounds(740, 400, 100, 20);
        branch.setBounds(840, 400, 100, 20);

        panel.add(mainLabel);
        panel.add(userLabel);
        panel.add(username);
        panel.add(passLabel);
        panel.add(password);
        panel.add(accountTypeLabel);
        panel.add(employee);
        panel.add(user);
        panel.add(branch);
        panel.add(login);

        add(panel);
    }

    class Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (branch.isSelected()) {
                boolean loggedIn = SQL.branch_login(username.getText(), password.getText());
                if (loggedIn) {
                    new BranchDash();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "The username and passwords don't match!");
                }

            } else if (user.isSelected()) {
                boolean loggedIn = SQL.user_login(username.getText(), password.getText());
                if (loggedIn) {
                    new Dashboard();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "The username and passwords don't match!");
                }
            } else if (employee.isSelected()) {
                boolean loggedIn = SQL.employee_login(username.getText(), password.getText());
                if (loggedIn) {
                    new EmployeeDash();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "The username and passwords don't match!");
                }
            }
        }
    }

}
