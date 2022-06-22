package bms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends  JFrame{
    JButton signup;
    JTextField username, password, confirmPass , name, father, dob, cnic;
    JLabel userLabel, passLabel, mainLabel, fatherLabel, nameLabel, confirmLabel, DOB, CNIC, accLabel;

    public SignUp() {
        setTitle("User Sign Up");
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
        mainLabel.setText("Fill in the required information");
        mainLabel.setBounds(400, 70, 700, 60);

        JRadioButton savings = new JRadioButton("Savings");
        JRadioButton current = new JRadioButton("Current");
        savings.setFont(new Font("Cinzel", Font.BOLD, 10));
        current.setFont(new Font("Cinzel", Font.BOLD, 10));
        savings.setFocusable(false);
        current.setFocusable(false);

        accLabel = new JLabel();
        accLabel.setForeground(Color.WHITE);
        accLabel.setText("Account Type:");

        nameLabel = new JLabel();
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setText("Name:");


        name = new JTextField(15);

        fatherLabel = new JLabel();
        fatherLabel.setForeground(Color.WHITE);
        fatherLabel.setText("Father's Name:");


        father = new JTextField(15);

        userLabel = new JLabel();
        userLabel.setForeground(Color.WHITE);
        userLabel.setText("Username:");


        username = new JTextField(15);

        DOB = new JLabel();
        DOB.setForeground(Color.WHITE);
        DOB.setText("Date of Birth:");


        dob = new JTextField(15);

        CNIC = new JLabel();
        CNIC.setForeground(Color.WHITE);
        CNIC.setText("CNIC:");


        cnic = new JTextField(15);


        passLabel = new JLabel();
        passLabel.setText("Password:");
        passLabel.setForeground(Color.WHITE);

        password = new JPasswordField(15);

        confirmLabel = new JLabel();
        confirmLabel.setText("Confirm Password:");
        confirmLabel.setForeground(Color.WHITE);

        confirmPass = new JPasswordField(15);

        signup = new JButton();
        signup.setText("SUBMIT");
        signup.setBackground(Color.WHITE);
       signup.addActionListener(new Sign());
        signup.setFocusable(false);

        signup.setBounds(440, 580, 400, 30);
        signup.setFocusable(false);
        savings.setBounds(640, 150, 100, 20);
        current.setBounds(770, 150, 100, 20);
        accLabel.setBounds(440, 150,200,20);
        userLabel.setBounds(440, 200, 200, 20);
        username.setBounds(640, 200, 200, 20);
        fatherLabel.setBounds(440, 250, 200, 20);
        father.setBounds(640,250,200,20);
        nameLabel.setBounds(440, 300, 200, 20);
        name.setBounds(640,300,200,20);
        dob.setBounds(640, 350, 200, 20);
        DOB.setBounds(440, 350,200,20);
        cnic.setBounds(640, 400, 200, 20);
        CNIC.setBounds(440, 400,200,20);
        passLabel.setBounds(440, 450, 200, 20);
        password.setBounds(640, 450, 200, 20);
        confirmLabel.setBounds(440, 500, 200, 20);
        confirmPass.setBounds(640, 500, 200, 20);

        panel.add(mainLabel);
        panel.add(accLabel);
        panel.add(savings);
        panel.add(current);
        panel.add(nameLabel);
        panel.add(name);
        panel.add(fatherLabel);
        panel.add(father);
        panel.add(userLabel);
        panel.add(username);
        panel.add(DOB);
        panel.add(dob);
        panel.add(CNIC);
        panel.add(cnic);
        panel.add(passLabel);
        panel.add(password);
        panel.add(confirmLabel);
        panel.add(confirmPass);
        panel.add(signup);

        add(panel);
    }

    class Sign implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            String userValue = username.getText();
            String passValue = password.getText();
            String nameValue = name.getText();
            String confValue = confirmPass.getText();
            String dateValue = dob.getText();
            String CNICv = cnic.getText();
            if (!dateValue.equals("") && !CNICv.equals("") &&!userValue.equals("") && !passValue.equals("") && !nameValue.equals("") && !confValue.equals("")) {  //if authentic, navigate user to a new page
                //boolean status = SQL.signup("Talal", "3820130029971", 02, "Iftikhar", 1);
                new Dashboard();
                dispose();

            }
            else
            {
                //show error message
                JOptionPane.showMessageDialog(null, "Please fill all the fields!");
            }

        }
    }

}
