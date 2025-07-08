import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class HotelManagementSystem extends JFrame implements ActionListener{

    public HotelManagementSystem() {
        setSize(1366, 565);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
        JLabel image = new JLabel(i1);
        image.setLayout(null); 
        add(image);

        JLabel text = new JLabel("HOTEL MANAGEMENT SYSTEM");
        text.setBounds(50, 500, 1500, 80);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("serif", Font.PLAIN, 50));
        image.add(text);

        JButton next = new JButton(" Next  ");
        next.setBounds(1100, 530, 120, 40);
        next.setBackground(Color.WHITE);
        next.setForeground(Color.MAGENTA);
        next.addActionListener(this);
        next.setFont(new Font("serif" , Font.PLAIN , 24));
        image.add(next); 

        setVisible(true);

        while (true) {
            text.setVisible(false);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            text.setVisible(true);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void actionPerformed(ActionEvent ae){
        dispose();
        new Login();
    }
    public static void main(String[] args) {
        new HotelManagementSystem();
    }
}
