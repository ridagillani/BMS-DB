package bms;

import javax.swing.*;
import java.awt.*;

public class Transfer extends JPanel {
    JButton transfer;
    JTextField amount, pin;
    JLabel numLabel, amountLabel, pinLabel, headingL;
    Container align, mainContainer;
    JComboBox<String[]> accNum;


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

        String[][] beneficiaries = SQL.getBeneficiaryList(UserDetails.getUserID());

        accNum = new JComboBox(beneficiaries);

        accNum.setRenderer(new ComboBoxRenderer());




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
        transfer.addActionListener(e -> transfer());
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

    void transfer () {
        String[] item = (String[]) accNum.getSelectedItem();
        int beneficiary_account = Integer.parseInt(item[2]);
        String password = pin.getText();
        int amnt = Integer.parseInt(amount.getText());

        if(password.equals(UserDetails.getPassword())) {
            boolean transfered = SQL.transfer(UserDetails.getAccountNumber(), amnt, beneficiary_account);

            if (!transfered) {
                JOptionPane.showMessageDialog(null, "Unable to transfer amount!");
                return;
            }

            JOptionPane.showMessageDialog(null, "Money Transferred successfully!");

        } else {
            JOptionPane.showMessageDialog(null, "Incorrect Password!");
        }
    }

    class ComboBoxRenderer extends JLabel implements ListCellRenderer {

        public Component getListCellRendererComponent(
                JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {


            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            String[] item = (String[])value;

            setText(item[1]);

            return this;
        }

    }
}
