
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Reception extends JFrame implements ActionListener {

    JPanel contentPanel;

    JButton btnNewCustomer, btnRoom, btnDepartment, btnEmployee, btnCustomerInfo;
    JButton btnManagerInfo, btnCheckout, btnUpdateCheck, btnUpdateRoom;
    JButton btnPickup, btnSearchRoom, btnLogout;

    public Reception() {
        setBounds(530, 200, 850, 570);
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(Color.WHITE);
        setContentPane(contentPanel);

        // Background Image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/fourth.jpg"));
        Image img = imageIcon.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        JLabel imgLabel = new JLabel(new ImageIcon(img));
        imgLabel.setBounds(250, 30, 500, 470);
        contentPanel.add(imgLabel); // Corrected from add() to contentPanel.add()

        // Buttons with styling and action listeners
        btnNewCustomer = createButton("New Customer Form", 30);
        btnRoom = createButton("Room", 70);
        btnDepartment = createButton("Department", 110);
        btnEmployee = createButton("All Employee Info", 150);
        btnCustomerInfo = createButton("Customer Info", 190);
        btnManagerInfo = createButton("Manager Info", 230);
        btnCheckout = createButton("Check Out", 270);
        btnUpdateCheck = createButton("Update Check Status", 310);
        btnUpdateRoom = createButton("Update Room Status", 350);
        btnPickup = createButton("Pick up Service", 390);
        btnSearchRoom = createButton("Search Room", 430);
        btnLogout = createButton("Log Out", 470);

        setVisible(true);
    }

    private JButton createButton(String text, int y) {
        JButton button = new JButton(text);
        button.setBounds(10, y, 200, 30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.addActionListener(this);
        contentPanel.add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNewCustomer) {
            new NewCustomer().setVisible(true);
            setVisible(false);
        }
        else if(e.getSource() == btnRoom){
            new Room().setVisible(true);
            setVisible(false);
        }
        else if(e.getSource() == btnDepartment){
            new Department().setVisible(true);
            setVisible(false);
        }
        else if (e.getSource() == btnDepartment) {
            new Department().setVisible(true);
            setVisible(false);
        }else if (e.getSource() == btnEmployee) {
            new Employee().setVisible(true);
            setVisible(false);
        }
        else if (e.getSource() == btnCustomerInfo) {
            new CustomerInfo().setVisible(true);
            setVisible(false);
        } else if (e.getSource() == btnManagerInfo) {
            new ManagerInfo().setVisible(true);
            setVisible(false);
        } else if (e.getSource() == btnCheckout) {
             new CheckOut().setVisible(true);
             setVisible(false);
        }
        else if (e.getSource() == btnUpdateCheck) {
            new UpdateCheck().setVisible(true);
             setVisible(false);
        }else if (e.getSource() == btnUpdateRoom) {
            new UpdateRoom().setVisible(true);
            setVisible(false);
        }else if (e.getSource() == btnPickup) {
            new PickUp().setVisible(true);
            setVisible(false);
        } else if (e.getSource() == btnSearchRoom) {
            new SearchRoom().setVisible(true);
            setVisible(false);
        } else if (e.getSource() == btnLogout) {
            new Login().setVisible(true);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Reception();
    }
}
