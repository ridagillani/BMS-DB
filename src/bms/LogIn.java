package bms;

import javax.swing.*;
import java.awt.*;

public class LogIn extends JFrame {
    JButton login;
    JTextField username, password;
    JLabel userLabel, passLabel, mainLabel;

    public LogIn() {
        setTitle("User Log-in");
        setSize(1280, 720);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        panel.setLayout(null);

        mainLabel = new JLabel();
        mainLabel.setForeground(Color.WHITE);
        mainLabel.setFont(new Font("Cinzel", Font.BOLD, 30));
        mainLabel.setText("Use your log-in credentials");
        mainLabel.setBounds(400, 200, 700, 60);

        userLabel = new JLabel();
        userLabel.setForeground(Color.WHITE);
        userLabel.setText("Username:");


        username = new JTextField(15);


        passLabel = new JLabel();
        passLabel.setText("Password:");
        passLabel.setForeground(Color.WHITE);

        password = new JPasswordField(15);

        login = new JButton();
        login.setText("SUBMIT");
        login.setBackground(Color.WHITE);
      //  login.addActionListener(new LogIn());
        login.setFocusable(false);

        login.setBounds(440, 400, 400, 30);
        userLabel.setBounds(440, 300, 200, 20);
        username.setBounds(640, 300, 200, 20);
        passLabel.setBounds(440, 350, 200, 20);
        password.setBounds(640, 350, 200, 20);

        panel.add(mainLabel);
        panel.add(userLabel);
        panel.add(username);
        panel.add(passLabel);
        panel.add(password);
        panel.add(login);

        add(panel);
    }

}
