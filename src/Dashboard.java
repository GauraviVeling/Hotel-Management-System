import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Dashboard extends JFrame implements ActionListener {

    JMenuItem addemployee, addrooms, adddrivers, receptionMenuItem;

    public Dashboard() {

        setBounds(0, 0, 1550, 1000);
        setLayout(null);

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1550, 1000, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1550, 1000);
        image.setLayout(null);
        add(image);

        // Welcome Text
        JLabel text = new JLabel("THE TAJ GROUP WELCOMES YOU !");
        text.setBounds(400, 80, 1000, 50);
        text.setFont(new Font("Tahoma", Font.PLAIN, 46));
        text.setForeground(Color.WHITE);
        image.add(text);

        // Menu Bar
        JMenuBar mb = new JMenuBar();
        mb.setBounds(0, 0, 1550, 30);
        image.add(mb);

        // HOTEL MANAGEMENT Menu
        JMenu hotel = new JMenu("HOTEL MANAGEMENT");
        hotel.setForeground(Color.RED);
        mb.add(hotel);

        // Reception Item under Hotel Management
        receptionMenuItem = new JMenuItem("RECEPTION");
        receptionMenuItem.addActionListener(this);
        hotel.add(receptionMenuItem);

        // ADMIN Menu
        JMenu admin = new JMenu("ADMIN");
        admin.setForeground(Color.BLUE);
        mb.add(admin);

        addemployee = new JMenuItem("ADD EMPLOYEE");
        addemployee.addActionListener(this);
        admin.add(addemployee);

        addrooms = new JMenuItem("ADD ROOMS");
        addrooms.addActionListener(this);
        admin.add(addrooms);

        adddrivers = new JMenuItem("ADD DRIVERS");
        adddrivers.addActionListener(this);
        admin.add(adddrivers);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addemployee) {
            new AddEmployee();
        } else if (ae.getSource() == addrooms) {
            new AddRooms();
        } else if (ae.getSource() == adddrivers) {
            new AddDrivers();
        } else if (ae.getSource() == receptionMenuItem) {
            new Reception().setVisible(true); // ✅ Reception opens on click
        }
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
