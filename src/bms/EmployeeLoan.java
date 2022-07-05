package bms;

import javax.swing.*;
import java.awt.*;

public class EmployeeLoan extends JPanel {
    JPanel loanPanel = new LoanList();

    int selectedRow;
    String[] selected = new String[5];

    JPanel viewPanel;

    String[][] loanList;
    public EmployeeLoan () {
        setBackground(Color.white);

        JButton approve = new JButton();
        approve.setText("Approve");
        approve.setSize(125, 60);
        approve.setForeground(Color.darkGray);
        approve.setBackground(Color.white);
        approve.setFocusable(false);

        JButton disapprove = new JButton();
        disapprove.setText("Disapprove");
        disapprove.setSize(125, 60);
        disapprove.setForeground(Color.darkGray);
        disapprove.setBackground(Color.white);
        disapprove.setFocusable(false);


        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(4, 1, 0, 50));
        buttons.setBounds(0, 0, 100, 100);


        approve.addActionListener(e -> approve());
        disapprove.addActionListener(e -> disapprove());


        buttons.add(approve);
        buttons.add(disapprove);
        buttons.setBackground(Color.darkGray);
        buttons.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));

        setLayout(new BorderLayout(0, 0));

        add(buttons, BorderLayout.WEST);
        add(loanPanel, BorderLayout.CENTER);
    }

    class LoanList extends JPanel {

        LoanList() {
            setLayout(new BorderLayout());

            viewPanel = new JPanel();
            viewPanel.setLayout(new BorderLayout());

            loanList = SQL.getLoanList();


            String[] column_name = {
                    "Loan ID",
                    "Applicant Username",
                    "Applicant ID",
                    "Amount",
                    "Status",
            };


            JTable usertable = new JTable(loanList, column_name) {
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

    void getSelected () {
        for (String[] loan : loanList) {
            if (Integer.parseInt(loan[0]) == selectedRow) {
                selected = loan;
                return;
            }
        }
    }

    void approve() {
        getSelected();

        boolean status = SQL.updateLoan(Integer.parseInt(selected[0]), Integer.parseInt(selected[2]), "APPROVED", Integer.parseInt(selected[3]));

        if (!status) {
            JOptionPane.showMessageDialog(null, "Unable to update loan!");
            return;
        }

        JOptionPane.showMessageDialog(null, "Loan updated successfully!");

        loanList = SQL.getLoanList();
    }

    void disapprove () {
        getSelected();

        boolean status = SQL.updateLoan(Integer.parseInt(selected[0]), Integer.parseInt(selected[2]), "DISAPPROVED", 0);

        if (!status) {
            JOptionPane.showMessageDialog(null, "Unable to update loan!");
            return;
        }

        JOptionPane.showMessageDialog(null, "Loan updated successfully!");
        loanList = SQL.getLoanList();
    }
}
