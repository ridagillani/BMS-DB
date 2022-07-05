package bms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeDash extends JFrame {
    JPanel userPanel = new EmployeeUsers();
    JPanel loanPanel = new EmployeeLoan();

    JButton users = new JButton();
    JButton loan = new JButton();

    String current = "users";

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
        heading.setText("BMS");
        heading.setForeground(Color.WHITE);
        heading.setBackground(Color.darkGray);
        heading.setFont(new Font("Cinzel", Font.BOLD, 30));

        users.setText("Users");
        users.setSize(125,60);
        users.setForeground(Color.darkGray);
        users.setBackground(Color.white);
        users.setFocusable(false);

        loan.setText("Loan");
        loan.setSize(125,60);
        loan.setForeground(Color.darkGray);
        loan.setBackground(Color.white);
        loan.setFocusable(false);

        JButton backbutton = new JButton();
        backbutton.setText("Log Out");
        backbutton.setForeground(Color.darkGray);
        backbutton.setBackground(Color.white);
        backbutton.setFocusable(false);
        backbutton.addActionListener(e -> {
            dispose();
            new LogIn();
        });

        users.addActionListener(new EmployeeDash.DashAction());
        loan.addActionListener(new EmployeeDash.DashAction());


        Container dashLabel = new Container();
        dashLabel.setLayout(new GridBagLayout());
        dashLabel.add(heading);

        Container navigation = new Container();
        navigation.setLayout(new GridBagLayout());
        navigation.add(users);
        navigation.add(loan);
        navigation.add(backbutton);

        header.add(dashLabel);

        GridBagConstraints c = new GridBagConstraints();
        c.ipadx = 300;
        header.add(dashLabel,c);
        c.gridwidth = 10;
        header.add(navigation,c);
        add(header, BorderLayout.NORTH);
        add(userPanel, BorderLayout.CENTER);
        users.setBackground(Color.gray);
        users.setForeground(Color.WHITE);
        current = "users";
        setVisible(true);
    }

    class DashAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("Users")) {
                switch (current) {
                    case "users" -> {
                        remove(userPanel);
                        userPanel.setBackground(Color.white);
                        userPanel.setForeground(Color.darkGray);
                    }
                    case "loan" -> {
                        remove(loanPanel);
                        loan.setBackground(Color.white);
                        loan.setForeground(Color.darkGray);
                    }
                }
                userPanel = new EmployeeUsers();
                add(userPanel, BorderLayout.CENTER);
                users.setBackground(Color.gray);
                users.setForeground(Color.WHITE);
                current = "users";
                revalidate();
                repaint();
            }
            else if (e.getActionCommand().equalsIgnoreCase("Loan")) {
                switch (current) {
                    case "users" -> {
                        remove(userPanel);
                        users.setBackground(Color.white);
                        users.setForeground(Color.darkGray);
                    }
                    case "loan" -> {
                        remove(loanPanel);
                        loan.setBackground(Color.white);
                        loan.setForeground(Color.darkGray);
                    }
                }

                loanPanel = new EmployeeLoan();
                add(loanPanel, BorderLayout.CENTER);
                loan.setBackground(Color.gray);
                loan.setForeground(Color.WHITE);
                current = "loan";
                revalidate();
                repaint();
            }
        }
    }



}


