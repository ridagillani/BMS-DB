package bms;

import javax.swing.*;
import java.awt.*;

public class Bill extends JPanel {
    JButton pay;
    JTextField CNIC, accNum, amount, pin ;
    JLabel cnicL, numLabel, amountLabel, pinLabel, headingL , bilL;
    Container align;

    public Bill(){
        setLayout(new GridBagLayout());

        align = new Container();
        align.setBackground(Color.darkGray);
        align.setLayout(new GridLayout(5,5));

        headingL = new JLabel();
        headingL.setForeground(Color.DARK_GRAY);
        headingL.setFont(new Font("Cinzel", Font.BOLD, 30));
        headingL.setText("Bill Payment");
        headingL.setHorizontalAlignment(headingL.CENTER);

//        cnicL = new JLabel();
//        cnicL.setForeground(Color.DARK_GRAY);
//        cnicL.setText("Sender's CNIC : ");

//        CNIC = new JTextField(15);

        bilL = new JLabel();
        bilL.setForeground(Color.DARK_GRAY);
        bilL.setText("Billing of :");

        String[] optionsToChoose = {"Electricity", "Water", "Gas", "Internet", "LandLine", "Government Fee", "NADRA Fee"};

        JComboBox<String> type = new JComboBox<>(optionsToChoose);
        type.setBounds(80, 50, 140, 20);

//        numLabel = new JLabel();
//        numLabel.setForeground(Color.DARK_GRAY);
//        numLabel.setText("Receiver Account : ");
//
//        accNum = new JTextField(15);

        amountLabel = new JLabel();
        amountLabel.setForeground(Color.DARK_GRAY);
        amountLabel.setText("Amount : ");

        amount = new JTextField(15);

        pinLabel = new JLabel();
        pinLabel.setForeground(Color.DARK_GRAY);
        pinLabel.setText("Your Pin : ");

        pin = new JTextField(15);

        pay = new JButton();
        pay.setText("Pay Now");
        pay.setBackground(Color.darkGray);
        pay.setForeground(Color.white);
        pay.addActionListener(e -> bill((String) type.getSelectedItem()));
        pay.setFocusable(false);
        pay.setHorizontalAlignment(pay.CENTER);

        Container buttonContainer = new Container();
        buttonContainer.setLayout(new GridBagLayout());
        buttonContainer.add(pay);

        Container mainContainer = new Container();
        mainContainer.setBackground(Color.darkGray);
        mainContainer.setLayout(new GridLayout(3,1, 40, 40));

        mainContainer.add(headingL);
//        align.add(cnicL);
//        align.add(CNIC);
//        align.add(numLabel);
//        align.add(accNum);
        align.add(amountLabel);
        align.add(amount);
        align.add(bilL);
        align.add(type);
        align.add(pinLabel);
        align.add(pin);
        mainContainer.add(align);
        mainContainer.add(buttonContainer);
        add(mainContainer);
    }

    void bill (String billOf) {
        String password = pin.getText();
        int amnt = Integer.parseInt(amount.getText());

        if(password.equals(UserDetails.getPassword())) {
            boolean paid = SQL.billPayment(UserDetails.getAccountNumber(), amnt, billOf);

            if (!paid) {
                JOptionPane.showMessageDialog(null, "Unable to pay bill!");
                return;
            }

            JOptionPane.showMessageDialog(null, "Bill paid successfully!");

        } else {
            JOptionPane.showMessageDialog(null, "Incorrect Password!");
        }
    }
}
