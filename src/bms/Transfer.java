package bms;

import javax.swing.*;
import java.awt.*;

public class Transfer extends JPanel {
    JButton transfer;
    JTextField accNum, amount, pin;
    JLabel numLabel, amountLabel, pinLabel, headingL;
    Container align, mainContainer;

    public Transfer(){
        setLayout(new GridBagLayout());

        align = new Container();
        align.setBackground(Color.darkGray);
        align.setLayout(new GridLayout(3,3));

        headingL = new JLabel();
        headingL.setForeground(Color.DARK_GRAY);
        headingL.setFont(new Font("Cinzel", Font.BOLD, 35));
        headingL.setText("Send Money");
        headingL.setHorizontalAlignment(headingL.CENTER);

        numLabel = new JLabel();
        numLabel.setForeground(Color.DARK_GRAY);
        numLabel.setText("Receiver Account: ");

        accNum = new JTextField(15);

        amountLabel = new JLabel();
        amountLabel.setForeground(Color.DARK_GRAY);
        amountLabel.setText("Amount : ");

        amount = new JTextField(15);

        pinLabel = new JLabel();
        pinLabel.setForeground(Color.DARK_GRAY);
        pinLabel.setText("Your Pin : ");

        pin = new JTextField(15);

        transfer = new JButton();
        transfer.setText("Transfer Amount");
        transfer.setBackground(Color.darkGray);
        transfer.setForeground(Color.white);
      //  transfer.addActionListener(new SignUp.Sign());
        transfer.setFocusable(false);
//        transfer.setSize(500,20);

        Container buttonContainer = new Container();
        buttonContainer.setLayout(new GridBagLayout());

        mainContainer = new Container();
        mainContainer.setBackground(Color.darkGray);
        mainContainer.setLayout(new GridLayout(3,1, 40, 60));

        align.add(numLabel);
        align.add(accNum);
        align.add(amountLabel);
        align.add(amount);
        align.add(pinLabel);
        align.add(pin);

        buttonContainer.add(transfer);

        mainContainer.add(headingL);
        mainContainer.add(align);
        mainContainer.add(buttonContainer);

        add(mainContainer);
    }
}
