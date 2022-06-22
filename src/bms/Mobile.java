package bms;

import javax.swing.*;
import java.awt.*;

public class Mobile extends JPanel {
    JButton load;
    JTextField contact, amount, pin;
    JLabel numLabel, amountLabel, pinLabel, headingL, networkL;
    Container align;
    public Mobile(){
        setLayout(new GridBagLayout());

        align = new Container();
        align.setBackground(Color.darkGray);
        align.setLayout(new GridLayout(5,5));

        headingL = new JLabel();
        headingL.setForeground(Color.DARK_GRAY);
        headingL.setFont(new Font("Cinzel", Font.BOLD, 30));
        headingL.setText("Mobile Load");
        headingL.setHorizontalAlignment(headingL.CENTER);

        numLabel = new JLabel();
        numLabel.setForeground(Color.DARK_GRAY);
        numLabel.setText("Mobile Number : ");

        contact = new JTextField(15);

        networkL = new JLabel();
        networkL.setForeground(Color.DARK_GRAY);
        networkL.setText("Mobile Network : ");

        String[] optionsToChoose = {"Zong", "Ufone", "Jazz", "Telenor", "Mobilink"};

        JComboBox<String> network = new JComboBox<>(optionsToChoose);
        network.setBounds(80, 50, 140, 20);

        amountLabel = new JLabel();
        amountLabel.setForeground(Color.DARK_GRAY);
        amountLabel.setText("Amount : ");

        amount = new JTextField(15);

        pinLabel = new JLabel();
        pinLabel.setForeground(Color.DARK_GRAY);
        pinLabel.setText("Your Pin : ");

        pin = new JTextField(15);

        load = new JButton();
        load.setText("Transfer Amount");
        load.setBackground(Color.darkGray);
        load.setForeground(Color.white);
        //  transfer.addActionListener(new SignUp.Sign());
        load.setFocusable(false);

        Container buttonContainer = new Container();
        buttonContainer.setLayout(new GridBagLayout());

        Container mainContainer = new Container();
        mainContainer.setBackground(Color.darkGray);
        mainContainer.setLayout(new GridLayout(3,1, 40, 10));

        mainContainer.add(headingL);
        align.add(numLabel);
        align.add(contact);
        align.add(networkL);
        align.add(network);
        align.add(amountLabel);
        align.add(amount);
        align.add(pinLabel);
        align.add(pin);
        mainContainer.add(align);
        buttonContainer.add(load);
        mainContainer.add(buttonContainer);
        add(mainContainer);
    }
}
