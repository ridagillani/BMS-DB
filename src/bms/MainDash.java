package bms;

import javax.swing.*;
import java.awt.*;

public class MainDash extends JPanel {
    public MainDash() {


        setBackground(Color.white);
        setLayout(new BorderLayout());

        Container dashBox = new Container();
        dashBox.setLayout(new GridLayout(2,1,10,10));

        JLabel currentAmount = new JLabel();
        currentAmount.setText("Current Balance: 23,456");
        currentAmount.setFont(new Font("Gilroy", Font.BOLD,15));
        currentAmount.setHorizontalAlignment(currentAmount.CENTER);

        JLabel cnic = new JLabel();
        cnic.setText("CNIC: 37405-88880-9");
        cnic.setFont(new Font("Gilroy", Font.BOLD,15));
        cnic.setHorizontalAlignment(cnic.CENTER);

        JLabel name = new JLabel();
        name.setText("Account Title: Demo User");
        name.setFont(new Font("Gilroy", Font.BOLD,15));
        name.setHorizontalAlignment(name.CENTER);

        JLabel accNum = new JLabel();
        accNum.setText("Account Number: 12345678");
        accNum.setFont(new Font("Gilroy", Font.BOLD,15));
        accNum.setHorizontalAlignment(accNum.CENTER);

        JLabel type = new JLabel();
        type.setText("Account Type: Current");
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
                "Receiver Account No.",
                "Amount",
                "Timestamp"
        };

        String[][] data = {};

        JTable history = new JTable(data, column_name);


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
