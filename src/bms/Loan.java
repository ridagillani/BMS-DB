package bms;

import javax.swing.*;
import java.awt.*;

public class Loan extends JPanel {

    JButton apply;
    JTextField amount;
    JLabel amountLabel, headingL;
    Container align, mainContainer;

    public Loan(){
        setLayout(new GridBagLayout());

        align = new Container();
        align.setBackground(Color.darkGray);
        align.setLayout(new GridLayout(3,3));

        headingL = new JLabel();
        headingL.setForeground(Color.DARK_GRAY);
        headingL.setFont(new Font("Cinzel", Font.BOLD, 35));
        headingL.setText("Apply For Loan");
        headingL.setHorizontalAlignment(headingL.CENTER);



        amountLabel = new JLabel();
        amountLabel.setForeground(Color.DARK_GRAY);
        amountLabel.setText("Required Amount : ");

        amount = new JTextField(15);



        apply = new JButton();
        apply.setText("Apply for Loan");
        apply.setBackground(Color.darkGray);
        apply.setForeground(Color.white);
        apply.addActionListener(e -> applyLoan());
        apply.setFocusable(false);
//        transfer.setSize(500,20);

        Container buttonContainer = new Container();
        buttonContainer.setLayout(new GridBagLayout());

        mainContainer = new Container();
        mainContainer.setBackground(Color.darkGray);
        mainContainer.setLayout(new GridLayout(3,1, 40, 40));


        align.add(amountLabel);
        align.add(amount);


        buttonContainer.add(apply);

        mainContainer.add(headingL);
        mainContainer.add(align);
        mainContainer.add(buttonContainer);

        add(mainContainer);
    }

    void applyLoan() {
        int amnt = Integer.parseInt(amount.getText());


        boolean applied = SQL.applyLoan(UserDetails.getAccountNumber(), amnt);

        if (!applied) {
            JOptionPane.showMessageDialog(null, "Unable to apply for loan!");
            return;
        }

        JOptionPane.showMessageDialog(null, "Loan applied successfully!");

    }
}
