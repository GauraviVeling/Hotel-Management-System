

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UpdateRoom extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField txt_Room, txt_Ava, txt_Status;
    private Choice c1;
    private JButton btnCheck, btnUpdate, btnBack;

    public UpdateRoom() {
        setTitle("Update Room Status");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 1000, 450);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Update Room Status");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTitle.setBounds(85, 11, 206, 34);
        contentPane.add(lblTitle);

        JLabel lblId = new JLabel("Guest ID:");
        lblId.setBounds(27, 87, 90, 14);
        contentPane.add(lblId);

        c1 = new Choice();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                c1.add(rs.getString("id_number")); // Corrected
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(160, 84, 140, 20);
        contentPane.add(c1);

        JLabel lblRoomId = new JLabel("Room Number:");
        lblRoomId.setBounds(27, 133, 100, 14);
        contentPane.add(lblRoomId);

        txt_Room = new JTextField();
        txt_Room.setBounds(160, 130, 140, 20);
        contentPane.add(txt_Room);

        JLabel lblAvailability = new JLabel("Availability:");
        lblAvailability.setBounds(27, 187, 90, 14);
        contentPane.add(lblAvailability);

        txt_Ava = new JTextField();
        txt_Ava.setBounds(160, 184, 140, 20);
        contentPane.add(txt_Ava);

        JLabel lblStatus = new JLabel("Clean Status:");
        lblStatus.setBounds(27, 240, 90, 14);
        contentPane.add(lblStatus);

        txt_Status = new JTextField();
        txt_Status.setBounds(160, 237, 140, 20);
        contentPane.add(txt_Status);

        // Buttons
        btnCheck = new JButton("Check");
        btnCheck.setBounds(120, 315, 89, 23);
        btnCheck.setBackground(Color.BLACK);
        btnCheck.setForeground(Color.WHITE);
        contentPane.add(btnCheck);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(60, 355, 89, 23);
        btnUpdate.setBackground(Color.BLACK);
        btnUpdate.setForeground(Color.WHITE);
        contentPane.add(btnUpdate);

        btnBack = new JButton("Back");
        btnBack.setBounds(180, 355, 89, 23);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        // Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
        Image i3 = i1.getImage().getScaledInstance(550, 250, Image.SCALE_DEFAULT);
        JLabel l1 = new JLabel(new ImageIcon(i3));
        l1.setBounds(400, 80, 600, 250);
        add(l1);

        // Register ActionListeners
        btnCheck.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnBack.addActionListener(this);

        setVisible(true);
    }

    // --------------- ACTION LOGIC AT THE END ----------------

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedId = c1.getSelectedItem();

        if (e.getSource() == btnCheck) {
            try {
                Conn c = new Conn();

                // Get Room Number of selected customer
                ResultSet rs1 = c.s.executeQuery("SELECT room_number FROM customer WHERE id_number = '" + selectedId + "'");
                if (rs1.next()) {
                    txt_Room.setText(rs1.getString("room_number"));
                }

                // Get Availability and Status of the room
                String roomNo = txt_Room.getText();
                ResultSet rs2 = c.s.executeQuery("SELECT * FROM room WHERE room_number = '" + roomNo + "'");
                if (rs2.next()) {
                    txt_Ava.setText(rs2.getString("availability"));
                    txt_Status.setText(rs2.getString("status")); // Corrected from 'clean_status'
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        else if (e.getSource() == btnUpdate) {
            try {
                Conn c = new Conn();
                String roomNo = txt_Room.getText();
                String status = txt_Status.getText();

                String updateQuery = "UPDATE room SET status = '" + status + "' WHERE room_number = '" + roomNo + "'";
                c.s.executeUpdate(updateQuery);

                JOptionPane.showMessageDialog(null, "Room Status Updated Successfully");
                new Reception().setVisible(true);
                setVisible(false);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Update Failed");
            }
        }

        else if (e.getSource() == btnBack) {
            new Reception().setVisible(true);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new UpdateRoom();
    }
}
