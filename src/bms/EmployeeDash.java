package bms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeDash extends JFrame {
    JPanel employeePanel = new BranchEmployeePanel();

    JButton dashboard = new JButton();

    String current = "employee";

    public EmployeeDash() {
        setTitle("Employee Dashboard");
        setSize(1280,720);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel header = new JPanel();
        header.setBackground(Color.DARK_GRAY);
        header.setLayout(new GridBagLayout());
        header.setPreferredSize(new Dimension(1280,100));

        JLabel heading = new JLabel();
        heading.setText("BMS");
        heading.setForeground(Color.WHITE);
        heading.setBackground(Color.darkGray);
        heading.setFont(new Font("Cinzel", Font.BOLD, 30));

        dashboard.setText("Employees");
        dashboard.setForeground(Color.darkGray);
        dashboard.setBackground(Color.white);
        dashboard.setFocusable(false);

        dashboard.addActionListener(new EmployeeDash.DashAction());


        Container dashLabel = new Container();
        dashLabel.setLayout(new GridBagLayout());
        dashLabel.add(heading);


        Container navigation = new Container();
        navigation.setLayout(new GridBagLayout());
        navigation.add(dashboard);

        GridBagConstraints c = new GridBagConstraints();
        c.ipadx = 300;
        header.add(dashLabel,c);
        c.gridwidth = 10;
        header.add(navigation,c);
        add(header, BorderLayout.NORTH);
        add(employeePanel, BorderLayout.CENTER);
        dashboard.setBackground(Color.gray);
        dashboard.setForeground(Color.WHITE);
        current = "employee";
        setVisible(true);
    }

    class DashAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
}
