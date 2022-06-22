package bms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewPage extends JFrame {
    public NewPage() {

        setTitle("Bank Management System");
        setSize(1280, 720);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1940, 1041);
        panel.setBackground(Color.darkGray);
        panel.setLayout(null);


        JLabel label = new JLabel();
        label.setText("Bank Management System");
        label.setBounds(390, 150, 1400, 120);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Cinzel", Font.BOLD, 38));
        panel.add(label);

        JButton login = new JButton();
        login.setText("Log-in");
        login.setBounds(550, 400, 160, 70);
        login.setBackground(Color.WHITE);
        login.setForeground(Color.darkGray);
        login.setFont(new Font("Cinzel", Font.BOLD, 15));
        login.setFocusable(false);

        ButtonGroup buttons = new ButtonGroup();

        panel.add(login);
        buttons.add(login);
        login.addActionListener(new Action());

        JButton signup = new JButton();
        signup.setText("Sign Up");
        signup.setBounds(550, 300, 160, 70);
        signup.setBackground(Color.WHITE);
        signup.setForeground(Color.darkGray);
        signup.setFont(new Font("Cinzel", Font.BOLD, 15));
        signup.setFocusable(false);

        panel.add(signup);
        buttons.add(signup);
        signup.addActionListener(new Action());

        add(panel);
        setVisible(true);
    }
    class Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("Log-in")) {
                new LogIn();
                dispose();
            }
            else if (e.getActionCommand().equalsIgnoreCase("Sign Up")){
                new SignUp();
                dispose();
            }

        }
    }

}
