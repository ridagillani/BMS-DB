package bms;

import javax.swing.*;
import java.awt.*;
import java.util.ConcurrentModificationException;

public class Zakaat extends JPanel {
    JButton donate;
    JTextField   amount, pin;
    JLabel charityL, mainLabel, amountL, pinLabel;
    Container align;

    public Zakaat(){

        setLayout(new GridBagLayout());

        align = new Container();
        align.setBackground(Color.darkGray);
        align.setLayout(new GridLayout(3,3));

        mainLabel = new JLabel();
        mainLabel.setForeground(Color.DARK_GRAY);
        mainLabel.setFont(new Font("Cinzel", Font.BOLD, 30));
        mainLabel.setText("Zakaat Payment");

        charityL = new JLabel();
        charityL.setForeground(Color.DARK_GRAY);
        charityL.setText("Select Charity :");

        String[] optionsToChoose = {"Shaukat Khanam", "PM Relief Fund", "Red Crescent", "Cyte Foundation", "Rizq", "Eidhi Foundation", "WWF Pakistan"};

        JComboBox<String> charity = new JComboBox<>(optionsToChoose);
        charity.setBounds(80, 50, 140, 20);

        amountL = new JLabel();
        amountL.setForeground(Color.DARK_GRAY);
        amountL.setText("Amount : ");

        amount = new JTextField(15);

        pinLabel = new JLabel();
        pinLabel.setForeground(Color.DARK_GRAY);
        pinLabel.setText("Your Pin : ");

        pin = new JTextField(15);

        donate = new JButton();
        donate.setText("Pay Now");
        donate.setBackground(Color.darkGray);
        donate.setForeground(Color.white);
        donate.addActionListener(e -> zakaat((String) charity.getSelectedItem()));
        donate.setFocusable(false);

        Container buttonContainer = new Container();
        buttonContainer.setLayout(new GridBagLayout());

        Container mainContainer = new Container();
        mainContainer.setBackground(Color.darkGray);
        mainContainer.setLayout(new GridLayout(3,1, 40, 40));

        mainContainer.add(mainLabel);
        align.add(charityL);
        align.add(charity);
        align.add(amountL);
        align.add(amount);
        align.add(pinLabel);
        align.add(pin);
        mainContainer.add(align);
        buttonContainer.add(donate);
        mainContainer.add(buttonContainer);
        add(mainContainer);
    }

    void zakaat(String charity) {
        String password = pin.getText();
        int amnt = Integer.parseInt(amount.getText());

        if(password.equals(UserDetails.getPassword())) {
            boolean paid = SQL.zakaat(UserDetails.getAccountNumber(), amnt, charity);

            if (!paid) {
                JOptionPane.showMessageDialog(null, "Unable to pay zakaat!");
                return;
            }

            JOptionPane.showMessageDialog(null, "Zakaat paying successfully!");

        } else {
            JOptionPane.showMessageDialog(null, "Incorrect Password!");
        }
    }
}
