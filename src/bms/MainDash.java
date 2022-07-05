package bms;

import javax.swing.*;
import java.awt.*;

public class MainDash extends JPanel {
    String[][] transactions;
    public MainDash() {
        SQL.updateUserDetails();
        transactions = SQL.getTransactions(UserDetails.getUserID());

        setBackground(Color.white);
        setLayout(new BorderLayout());

        Container dashBox = new Container();
        dashBox.setLayout(new GridLayout(2,1,10,10));

        JLabel currentAmount = new JLabel();
        currentAmount.setText("Current Balance: " + UserDetails.getBalance());
        currentAmount.setFont(new Font("Gilroy", Font.BOLD,15));
        currentAmount.setHorizontalAlignment(currentAmount.CENTER);

        JLabel cnic = new JLabel();
        cnic.setText("CNIC: " + UserDetails.getCnic());
        cnic.setFont(new Font("Gilroy", Font.BOLD,15));
        cnic.setHorizontalAlignment(cnic.CENTER);

        JLabel name = new JLabel();
        name.setText("Account Title: " + UserDetails.getUserName());
        name.setFont(new Font("Gilroy", Font.BOLD,15));
        name.setHorizontalAlignment(name.CENTER);

        JLabel accNum = new JLabel();
        accNum.setText("Account Number: " + UserDetails.getAccountNumber());
        accNum.setFont(new Font("Gilroy", Font.BOLD,15));
        accNum.setHorizontalAlignment(accNum.CENTER);

        JLabel type = new JLabel();
        type.setText("Account Type: " + UserDetails.getAccountType());
        type.setFont(new Font("Gilroy", Font.BOLD,15));
        type.setHorizontalAlignment(type.CENTER);

        JPanel info = new JPanel();
        info.setLayout(new GridLayout(5,2));


        info.add(currentAmount);
        info.add(cnic);
        info.add(name);
        info.add(accNum);
        info.add(type);

        JPanel down = new JPanel();
        down.setLayout(new BorderLayout());

        setBackground(Color.white);
        setLayout(new BorderLayout());

        String[] column_name = {
                "Receiver Name",
                "Amount",
                "Timestamp"
        };

        JTable history = new JTable(transactions, column_name);


        history.setAutoCreateRowSorter(true);
        history.setEnabled(false);
        history.setRowHeight(35);

        JPanel tableP = new JPanel();
        tableP.setBorder(BorderFactory.createLineBorder(Color.darkGray,5));
        tableP.setLayout(new BorderLayout(0,0));
        tableP.add(history.getTableHeader(), BorderLayout.NORTH);
        tableP.add(history, BorderLayout.CENTER);

        tableP.add(history);


        down.add(tableP, BorderLayout.CENTER);


        dashBox.add(info);
        dashBox.add(down);

        add(dashBox , BorderLayout.CENTER);

}

}
