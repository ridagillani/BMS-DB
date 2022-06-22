package bms;

import javax.swing.*;
import java.awt.*;

public class Loan extends JPanel {

    JButton apply;
    JTextField reason, amount, cnic, name;
    JLabel reasonLabel, amountLabel, cnicLabel, headingL;
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

        reasonLabel = new JLabel();
        reasonLabel.setForeground(Color.DARK_GRAY);
        reasonLabel.setText("Applicant's CNIC : ");

        cnic = new JTextField(15);

        amountLabel = new JLabel();
        amountLabel.setForeground(Color.DARK_GRAY);
        amountLabel.setText("Required Amount : ");

        amount = new JTextField(15);

        cnicLabel = new JLabel();
        cnicLabel.setForeground(Color.DARK_GRAY);
        cnicLabel.setText("Applicant's Name : ");

        name = new JTextField(15);

        apply = new JButton();
        apply.setText("Apply for Loan");
        apply.setBackground(Color.darkGray);
        apply.setForeground(Color.white);
        //  transfer.addActionListener(new SignUp.Sign());
        apply.setFocusable(false);
//        transfer.setSize(500,20);

        Container buttonContainer = new Container();
        buttonContainer.setLayout(new GridBagLayout());

        mainContainer = new Container();
        mainContainer.setBackground(Color.darkGray);
        mainContainer.setLayout(new GridLayout(3,1, 40, 40));

        align.add(reasonLabel);
        align.add(cnic);
        align.add(amountLabel);
        align.add(amount);
        align.add(cnicLabel);
        align.add(name);

        buttonContainer.add(apply);

        mainContainer.add(headingL);
        mainContainer.add(align);
        mainContainer.add(buttonContainer);

        add(mainContainer);
    }
}
