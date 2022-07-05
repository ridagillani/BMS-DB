package bms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {
    JPanel dashPanel = new MainDash();
    JPanel transferPanel = new Transfer();
    JPanel billPanel = new Bill();
    JPanel loanPanel = new Loan();
    JPanel mobPanel = new Mobile();
    JPanel beneficiaryPanel = new Beneficiary();
    JPanel settingPanel = new Settings();
    JPanel zakaatPanel = new Zakaat();


    JButton dashboard = new JButton();
    JButton transfer = new JButton();
    JButton bill = new JButton();
    JButton loan = new JButton();
    JButton mobile = new JButton();
    JButton beneficiary = new JButton();
    JButton settings = new JButton();
    JButton zakaat = new JButton();

    String current = "dash";

    public Dashboard()
    {
        setTitle("User Dashboard");
        setSize(1280,720);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel header = new JPanel();
        header.setBackground(Color.DARK_GRAY);
        header.setLayout(new GridBagLayout());
        header.setPreferredSize(new Dimension(1280,100));

        JLabel heading = new JLabel();
        heading.setText("BMS");
        heading.setForeground(Color.WHITE);
        heading.setBackground(Color.darkGray);
        heading.setFont(new Font("Cinzel", Font.BOLD, 30));

        transfer.setText("Transfer Money");
        transfer.setSize(125,60);
        transfer.setForeground(Color.darkGray);
        transfer.setBackground(Color.white);
        transfer.setFocusable(false);

        loan.setText("Loan");
        loan.setSize(125,60);
        loan.setForeground(Color.darkGray);
        loan.setBackground(Color.white);
        loan.setFocusable(false);

        mobile.setText("Mobile Load");
        mobile.setSize(125,60);
        mobile.setForeground(Color.darkGray);
        mobile.setBackground(Color.white);
        mobile.setFocusable(false);

        bill.setText("Bill Payment");
        bill.setForeground(Color.darkGray);
        bill.setBackground(Color.white);
        bill.setFocusable(false);

        beneficiary.setText("Beneficiary");
        beneficiary.setForeground(Color.darkGray);
        beneficiary.setBackground(Color.white);
        beneficiary.setFocusable(false);

        zakaat.setText("Zakaat");
        zakaat.setForeground(Color.darkGray);
        zakaat.setBackground(Color.white);
        zakaat.setFocusable(false);

        settings.setText("Settings");
        settings.setForeground(Color.darkGray);
        settings.setBackground(Color.white);
        settings.setFocusable(false);


        JButton backbutton = new JButton();
        backbutton.setText("Log Out");
        backbutton.setForeground(Color.darkGray);
        backbutton.setBackground(Color.white);
        backbutton.setFocusable(false);
        backbutton.addActionListener(e -> {
            dispose();
            new LogIn();
        });

        dashboard.setText("Dashboard");
        dashboard.setForeground(Color.darkGray);
        dashboard.setBackground(Color.white);
        dashboard.setFocusable(false);

        transfer.addActionListener(new DashAction());
        bill.addActionListener(new DashAction());
        dashboard.addActionListener(new DashAction());
        loan.addActionListener(new DashAction());
        mobile.addActionListener(new DashAction());
        beneficiary.addActionListener(new DashAction());
        zakaat.addActionListener(new DashAction());
        settings.addActionListener(new DashAction());

        Container dashLabel = new Container();
        dashLabel.setLayout(new GridBagLayout());
        dashLabel.add(heading);

        Container navigation = new Container();
        navigation.setLayout(new GridBagLayout());
        navigation.add(dashboard);
        navigation.add(transfer);
        navigation.add(bill);
        navigation.add(loan);
        navigation.add(mobile);
        navigation.add(beneficiary);
        navigation.add(zakaat);
        navigation.add(settings);
        navigation.add(backbutton);

        GridBagConstraints c = new GridBagConstraints();
        c.ipadx = 300;
        header.add(dashLabel,c);
        c.gridwidth = 10;
        header.add(navigation,c);
        add(header, BorderLayout.NORTH);
        add(dashPanel, BorderLayout.CENTER);
        dashboard.setBackground(Color.gray);
        dashboard.setForeground(Color.WHITE);
        current = "dash";
        setVisible(true);

    }
    class DashAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("Transfer Money")) {
                switch (current) {
                    case "dash" -> {
                        remove(dashPanel);
                        dashboard.setBackground(Color.white);
                        dashboard.setForeground(Color.darkGray);
                    }
                    case "zakaat" -> {
                        remove(zakaatPanel);
                        zakaat.setBackground(Color.white);
                        zakaat.setForeground(Color.darkGray);
                    }
                    case "bill" -> {
                        remove(billPanel);
                        bill.setBackground(Color.white);
                        bill.setForeground(Color.darkGray);
                    }
                    case "loan" -> {
                        remove(loanPanel);
                        loan.setBackground(Color.white);
                        loan.setForeground(Color.darkGray);
                    }
                    case "mobile" -> {
                        remove(mobPanel);
                        mobile.setBackground(Color.white);
                        mobile.setForeground(Color.darkGray);
                    }
                    case "beneficiary" -> {
                        remove(beneficiaryPanel);
                        beneficiary.setBackground(Color.white);
                        beneficiary.setForeground(Color.darkGray);
                    }
                    case "settings" -> {
                        remove(settingPanel);
                        settings.setBackground(Color.white);
                        settings.setForeground(Color.darkGray);
                    }
                }
                transferPanel = new Transfer();
                add(transferPanel, BorderLayout.CENTER);
                transfer.setBackground(Color.gray);
                transfer.setForeground(Color.WHITE);
                current = "transfer";
                revalidate();
                repaint();
            }
            else if (e.getActionCommand().equalsIgnoreCase("Dashboard")) {
                switch (current) {
                    case "transfer" -> {
                        remove(transferPanel);
                        transfer.setBackground(Color.white);
                        transfer.setForeground(Color.darkGray);
                    }
                    case "zakaat" -> {
                        remove(zakaatPanel);
                        zakaat.setBackground(Color.white);
                        zakaat.setForeground(Color.darkGray);
                    }
                    case "bill" -> {
                        remove(billPanel);
                        bill.setBackground(Color.white);
                        bill.setForeground(Color.darkGray);
                    }
                    case "loan" -> {
                        remove(loanPanel);
                        loan.setBackground(Color.white);
                        loan.setForeground(Color.darkGray);
                    }
                    case "mobile" -> {
                        remove(mobPanel);
                        mobile.setBackground(Color.white);
                        mobile.setForeground(Color.darkGray);
                    }
                    case "beneficiary" -> {
                        remove(beneficiaryPanel);
                        beneficiary.setBackground(Color.white);
                        beneficiary.setForeground(Color.darkGray);
                    }
                    case "settings" -> {
                        remove(settingPanel);
                        settings.setBackground(Color.white);
                        settings.setForeground(Color.darkGray);
                    }
                }

                dashPanel = new MainDash();
                add(dashPanel, BorderLayout.CENTER);
                dashboard.setBackground(Color.gray);
                dashboard.setForeground(Color.WHITE);
                current = "dash";
                revalidate();
                repaint();
            }

            else if (e.getActionCommand().equalsIgnoreCase("Bill Payment")) {

                switch (current) {
                    case "dash" -> {
                        remove(dashPanel);
                        dashboard.setBackground(Color.white);
                        dashboard.setForeground(Color.darkGray);
                    }
                    case "zakaat" -> {
                        remove(zakaatPanel);
                        zakaat.setBackground(Color.white);
                        zakaat.setForeground(Color.darkGray);
                    }
                    case "transfer" -> {
                        remove(transferPanel);
                        transfer.setBackground(Color.white);
                        transfer.setForeground(Color.darkGray);
                    }
                    case "loan" -> {
                        remove(loanPanel);
                        loan.setBackground(Color.white);
                        loan.setForeground(Color.darkGray);
                    }
                    case "mobile" -> {
                        remove(mobPanel);
                        mobile.setBackground(Color.white);
                        mobile.setForeground(Color.darkGray);
                    }
                    case "beneficiary" -> {
                        remove(beneficiaryPanel);
                        beneficiary.setBackground(Color.white);
                        beneficiary.setForeground(Color.darkGray);
                    }
                    case "settings" -> {
                        remove(settingPanel);
                        settings.setBackground(Color.white);
                        settings.setForeground(Color.darkGray);
                    }
                }


                add(billPanel, BorderLayout.CENTER);
                bill.setBackground(Color.gray);
                bill.setForeground(Color.WHITE);
                current = "bill";
                revalidate();
                repaint();
            }
            else if (e.getActionCommand().equalsIgnoreCase("Loan")) {
                switch (current) {
                    case "dash" -> {
                        remove(dashPanel);
                        dashboard.setBackground(Color.white);
                        dashboard.setForeground(Color.darkGray);
                    }
                    case "zakaat" -> {
                        remove(zakaatPanel);
                        zakaat.setBackground(Color.white);
                        zakaat.setForeground(Color.darkGray);
                    }
                    case "bill" -> {
                        remove(billPanel);
                        bill.setBackground(Color.white);
                        bill.setForeground(Color.darkGray);
                    }
                    case "transfer" -> {
                        remove(transferPanel);
                        transfer.setBackground(Color.white);
                        transfer.setForeground(Color.darkGray);
                    }
                    case "mobile" -> {
                        remove(mobPanel);
                        mobile.setBackground(Color.white);
                        mobile.setForeground(Color.darkGray);
                    }
                    case "beneficiary" -> {
                        remove(beneficiaryPanel);
                        beneficiary.setBackground(Color.white);
                        beneficiary.setForeground(Color.darkGray);
                    }
                    case "settings" -> {
                        remove(settingPanel);
                        settings.setBackground(Color.white);
                        settings.setForeground(Color.darkGray);
                    }
                }
                add(loanPanel, BorderLayout.CENTER);
                loan.setBackground(Color.gray);
                loan.setForeground(Color.WHITE);
                current = "loan";
                revalidate();
                repaint();
            }
            else if (e.getActionCommand().equalsIgnoreCase("Mobile Load")) {
                switch (current) {
                    case "dash" -> {
                        remove(dashPanel);
                        dashboard.setBackground(Color.white);
                        dashboard.setForeground(Color.darkGray);
                    }
                    case "zakaat" -> {
                        remove(zakaatPanel);
                        zakaat.setBackground(Color.white);
                        zakaat.setForeground(Color.darkGray);
                    }
                    case "bill" -> {
                        remove(billPanel);
                        bill.setBackground(Color.white);
                        bill.setForeground(Color.darkGray);
                    }
                    case "transfer" -> {
                        remove(transferPanel);
                        transfer.setBackground(Color.white);
                        transfer.setForeground(Color.darkGray);
                    }
                    case "loan" -> {
                        remove(loanPanel);
                        loan.setBackground(Color.white);
                        loan.setForeground(Color.darkGray);
                    }
                    case "beneficiary" -> {
                        remove(beneficiaryPanel);
                        beneficiary.setBackground(Color.white);
                        beneficiary.setForeground(Color.darkGray);
                    }
                    case "settings" -> {
                        remove(settingPanel);
                        settings.setBackground(Color.white);
                        settings.setForeground(Color.darkGray);
                    }
                }


                add(mobPanel, BorderLayout.CENTER);
                mobile.setBackground(Color.gray);
                mobile.setForeground(Color.WHITE);
                current = "mobile";
                revalidate();
                repaint();
            }
            else if (e.getActionCommand().equalsIgnoreCase("Beneficiary")) {
                switch (current) {
                    case "dash" -> {
                        remove(dashPanel);
                        dashboard.setBackground(Color.white);
                        dashboard.setForeground(Color.darkGray);
                    }
                    case "zakaat" -> {
                        remove(zakaatPanel);
                        zakaat.setBackground(Color.white);
                        zakaat.setForeground(Color.darkGray);
                    }
                    case "bill" -> {
                        remove(billPanel);
                        bill.setBackground(Color.white);
                        bill.setForeground(Color.darkGray);
                    }
                    case "transfer" -> {
                        remove(transferPanel);
                        transfer.setBackground(Color.white);
                        transfer.setForeground(Color.darkGray);
                    }
                    case "mobile" -> {
                        remove(mobPanel);
                        mobile.setBackground(Color.white);
                        mobile.setForeground(Color.darkGray);
                    }
                    case "loan" -> {
                        remove(loanPanel);
                        loan.setBackground(Color.white);
                        loan.setForeground(Color.darkGray);
                    }
                    case "settings" -> {
                        remove(settingPanel);
                        settings.setBackground(Color.white);
                        settings.setForeground(Color.darkGray);
                    }
                }
                add(beneficiaryPanel, BorderLayout.CENTER);
                beneficiary.setBackground(Color.gray);
                beneficiary.setForeground(Color.WHITE);
                current = "beneficiary";
                revalidate();
                repaint();
            }
            else if (e.getActionCommand().equalsIgnoreCase("Zakaat")) {
                switch (current) {
                    case "dash" -> {
                        remove(dashPanel);
                        dashboard.setBackground(Color.white);
                        dashboard.setForeground(Color.darkGray);
                    }
                    case "loan" -> {
                        remove(loanPanel);
                        loan.setBackground(Color.white);
                        loan.setForeground(Color.darkGray);
                    }
                    case "bill" -> {
                        remove(billPanel);
                        bill.setBackground(Color.white);
                        bill.setForeground(Color.darkGray);
                    }
                    case "transfer" -> {
                        remove(transferPanel);
                        transfer.setBackground(Color.white);
                        transfer.setForeground(Color.darkGray);
                    }
                    case "mobile" -> {
                        remove(mobPanel);
                        mobile.setBackground(Color.white);
                        mobile.setForeground(Color.darkGray);
                    }
                    case "beneficiary" -> {
                        remove(beneficiaryPanel);
                        beneficiary.setBackground(Color.white);
                        beneficiary.setForeground(Color.darkGray);
                    }
                    case "settings" -> {
                        remove(settingPanel);
                        settings.setBackground(Color.white);
                        settings.setForeground(Color.darkGray);
                    }
                }


                add(zakaatPanel, BorderLayout.CENTER);
                zakaat.setBackground(Color.gray);
                zakaat.setForeground(Color.WHITE);
                current = "zakaat";
                revalidate();
                repaint();
            }
            else if (e.getActionCommand().equalsIgnoreCase("Settings"))
            {
                switch (current) {
                    case "dash" -> {
                        remove(dashPanel);
                        dashboard.setBackground(Color.white);
                        dashboard.setForeground(Color.darkGray);
                    }
                    case "zakaat" -> {
                        remove(zakaatPanel);
                        zakaat.setBackground(Color.white);
                        zakaat.setForeground(Color.darkGray);
                    }
                    case "bill" -> {
                        remove(billPanel);
                        bill.setBackground(Color.white);
                        bill.setForeground(Color.darkGray);
                    }
                    case "transfer" -> {
                        remove(transferPanel);
                        transfer.setBackground(Color.white);
                        transfer.setForeground(Color.darkGray);
                    }
                    case "mobile" -> {
                        remove(mobPanel);
                        mobile.setBackground(Color.white);
                        mobile.setForeground(Color.darkGray);
                    }
                    case "beneficiary" -> {
                        remove(beneficiaryPanel);
                        beneficiary.setBackground(Color.white);
                        beneficiary.setForeground(Color.darkGray);
                    }
                    case "loan" -> {
                        remove(loanPanel);
                        loan.setBackground(Color.white);
                        loan.setForeground(Color.darkGray);
                    }
                }


                add(settingPanel, BorderLayout.CENTER);
                settings.setBackground(Color.gray);
                settings.setForeground(Color.WHITE);
                current = "settings";
                revalidate();
                repaint();
            }
        }
    }
}


