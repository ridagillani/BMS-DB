package bms;

import javax.swing.*;
import java.awt.*;

public class Beneficiary extends JPanel {

//    JButton transfer;
//    JTextField accNum, amount, pin;
//    JLabel numLabel, amountLabel, pinLabel, headingL;
//    Container align, mainContainer;

    public Beneficiary(){
        setLayout(new BorderLayout());

        String[] column_name = {
                "Beneficiary Name",
                "Beneficiary Account No.",
                "Last Transaction",
                "Timestamp"
        };

        String[][] data = {};

        JTable history = new JTable(data, column_name);


        history.setAutoCreateRowSorter(true);
        history.setEnabled(false);
        history.setRowHeight(40);

        JPanel tableP = new JPanel();
        tableP.setBorder(BorderFactory.createLineBorder(Color.darkGray,2));
        tableP.setLayout(new BorderLayout(0,0));
        tableP.add(history.getTableHeader(), BorderLayout.NORTH);
        tableP.add(history, BorderLayout.CENTER);

        tableP.add(history);


        add(tableP);

    }
}
