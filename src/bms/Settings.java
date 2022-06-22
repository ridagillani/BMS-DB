package bms;

import javax.swing.*;
import java.awt.*;

public class Settings extends JPanel {
    JButton confirm;
    JTextField oldP, pin, cpinF ;
    JLabel  pinLabel, headingL , oldL, cpinLabel;
    Container align;
    public Settings(){
        setLayout(new GridBagLayout());

        align = new Container();
        align.setBackground(Color.darkGray);
        align.setLayout(new GridLayout(5,5));

        headingL = new JLabel();
        headingL.setForeground(Color.DARK_GRAY);
        headingL.setFont(new Font("Cinzel", Font.BOLD, 30));
        headingL.setText("Update App Pin");
        headingL.setHorizontalAlignment(headingL.CENTER);

        oldL = new JLabel();
        oldL.setForeground(Color.DARK_GRAY);
        oldL.setText("Old Pin : ");

        oldP = new JTextField(15);

        pinLabel = new JLabel();
        pinLabel.setForeground(Color.DARK_GRAY);
        pinLabel.setText("New Pin : ");

        pin = new JTextField(15);

        cpinLabel = new JLabel();
        cpinLabel.setForeground(Color.DARK_GRAY);
        cpinLabel.setText("Confirm Pin : ");

        cpinF = new JTextField(15);

        confirm = new JButton();
        confirm.setText("Confirm");
        confirm.setBackground(Color.darkGray);
        confirm.setForeground(Color.white);
        //  transfer.addActionListener(new SignUp.Sign());
        confirm.setFocusable(false);
        confirm.setHorizontalAlignment(confirm.CENTER);

        Container buttonContainer = new Container();
        buttonContainer.setLayout(new GridBagLayout());
        buttonContainer.add(confirm);

        Container mainContainer = new Container();
        mainContainer.setBackground(Color.darkGray);
        mainContainer.setLayout(new GridLayout(3,1, 40, 10));

        mainContainer.add(headingL);
        align.add(oldL);
        align.add(oldP);
        align.add(pinLabel);
        align.add(pin);
        align.add(cpinLabel);
        align.add(cpinF);
        mainContainer.add(align);
        mainContainer.add(buttonContainer);
        add(mainContainer);
    }
}
