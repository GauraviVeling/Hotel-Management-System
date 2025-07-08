

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CheckOut extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField t1;
    private Choice c1;
    private JButton btnCheckout, btnBack;

    public CheckOut() {
        setTitle("Check Out");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 800, 294);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Check Out");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitle.setBounds(70, 11, 140, 35);
        contentPane.add(lblTitle);

        JLabel lblNumber = new JLabel("ID Number:");
        lblNumber.setBounds(20, 85, 100, 20);
        contentPane.add(lblNumber);

        c1 = new Choice();
        c1.setBounds(130, 85, 150, 20);
        contentPane.add(c1);

        JLabel lblRoom = new JLabel("Room Number:");
        lblRoom.setBounds(20, 132, 100, 20);
        contentPane.add(lblRoom);

        t1 = new JTextField();
        t1.setBounds(130, 132, 150, 20);
        contentPane.add(t1);

        btnCheckout = new JButton("Check Out");
        btnCheckout.setBounds(50, 200, 100, 25);
        btnCheckout.setBackground(Color.BLACK);
        btnCheckout.setForeground(Color.WHITE);
        contentPane.add(btnCheckout);

        btnBack = new JButton("Back");
        btnBack.setBounds(160, 200, 100, 25);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        // Right Image
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
        Image i = img.getImage().getScaledInstance(400, 225, Image.SCALE_DEFAULT);
        JLabel imageLabel = new JLabel(new ImageIcon(i));
        imageLabel.setBounds(300, 0, 500, 225);
        contentPane.add(imageLabel);

        // Load ID numbers into choice box
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                c1.add(rs.getString("id_number"));  // Ensure this matches your DB
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Listener for ID selection (auto-load room number)
        c1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                loadRoomNumberForSelectedID();
            }
        });

        btnCheckout.addActionListener(this);
        btnBack.addActionListener(this);

        setVisible(true);
    }

    // -------------- ACTIONS AT BOTTOM ----------------

    private void loadRoomNumberForSelectedID() {
        String selectedId = c1.getSelectedItem();
        try {
            Conn c = new Conn();
            // ✅ Exact query you asked for
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer WHERE id_number = '" + selectedId + "'");
            if (rs.next()) {
                t1.setText(rs.getString("room_number"));
            } else {
                t1.setText("");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCheckout) {
            String id = c1.getSelectedItem();
            String room = t1.getText();

            try {
                Conn c = new Conn();
                String deleteQuery = "DELETE FROM customer WHERE id_number = '" + id + "'";
                String updateRoom = "UPDATE room SET availability = 'Available' WHERE room_number = '" + room + "'";

                c.s.executeUpdate(deleteQuery);
                c.s.executeUpdate(updateRoom);

                JOptionPane.showMessageDialog(null, "Check Out successful");
                new Reception().setVisible(true);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Check Out failed");
            }

        } else if (e.getSource() == btnBack) {
            new Reception().setVisible(true);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new CheckOut();
    }
}
