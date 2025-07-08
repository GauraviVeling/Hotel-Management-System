
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UpdateCheck extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField txt_ID, txt_Name, txt_Status, txt_Deposit, txt_Pending;
    private Choice c1;
    private JButton btnCheck, btnUpdate, btnBack;

    public UpdateCheck() {
        setTitle("Update Customer Check-In Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 950, 500);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Check-In Details");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTitle.setBounds(124, 11, 222, 25);
        contentPane.add(lblTitle);

        JLabel lblId = new JLabel("ID Number:");
        lblId.setBounds(25, 88, 100, 14);
        contentPane.add(lblId);

        c1 = new Choice();
        c1.setBounds(248, 85, 140, 20);
        contentPane.add(c1);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                c1.add(rs.getString("id_number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lblRoom = new JLabel("Room Number:");
        lblRoom.setBounds(25, 129, 107, 14);
        contentPane.add(lblRoom);

        txt_ID = new JTextField();
        txt_ID.setBounds(248, 126, 140, 20);
        contentPane.add(txt_ID);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(25, 174, 107, 14);
        contentPane.add(lblName);

        txt_Name = new JTextField();
        txt_Name.setBounds(248, 171, 140, 20);
        contentPane.add(txt_Name);

        JLabel lblStatus = new JLabel("Check-in Status:");
        lblStatus.setBounds(25, 216, 120, 14);
        contentPane.add(lblStatus);

        txt_Status = new JTextField();
        txt_Status.setBounds(248, 216, 140, 20);
        contentPane.add(txt_Status);

        JLabel lblDeposit = new JLabel("Deposit Paid (Rs):");
        lblDeposit.setBounds(25, 261, 120, 14);
        contentPane.add(lblDeposit);

        txt_Deposit = new JTextField();
        txt_Deposit.setBounds(248, 258, 140, 20);
        contentPane.add(txt_Deposit);

        JLabel lblPending = new JLabel("Pending Amount (Rs):");
        lblPending.setBounds(25, 302, 150, 14);
        contentPane.add(lblPending);

        txt_Pending = new JTextField();
        txt_Pending.setBounds(248, 299, 140, 20);
        contentPane.add(txt_Pending);

        // Buttons
        btnCheck = new JButton("Check");
        btnCheck.setBounds(56, 378, 89, 23);
        btnCheck.setBackground(Color.BLACK);
        btnCheck.setForeground(Color.WHITE);
        contentPane.add(btnCheck);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(168, 378, 89, 23);
        btnUpdate.setBackground(Color.BLACK);
        btnUpdate.setForeground(Color.WHITE);
        contentPane.add(btnUpdate);

        btnBack = new JButton("Back");
        btnBack.setBounds(281, 378, 89, 23);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        // Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
        JLabel l1 = new JLabel(i1);
        l1.setBounds(450, 70, 476, 270);
        add(l1);

        // Add action listeners at the bottom
        btnCheck.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnBack.addActionListener(this);

        setVisible(true);
    }

    // ----------- ACTION LOGIC AT BOTTOM --------------

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedId = c1.getSelectedItem();

        if (e.getSource() == btnCheck) {
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("SELECT * FROM customer WHERE id_number = '" + selectedId + "'");
                if (rs.next()) {
                    txt_ID.setText(rs.getString("room_number"));
                    txt_Name.setText(rs.getString("name"));
                    txt_Status.setText(rs.getString("checkin_status"));
                    txt_Deposit.setText(rs.getString("deposit"));
                }

                // Get room price and compute pending amount
                String roomNo = txt_ID.getText();
                ResultSet rs2 = c.s.executeQuery("SELECT price FROM room WHERE room_number = '" + roomNo + "'");
                if (rs2.next()) {
                    int price = Integer.parseInt(rs2.getString("price"));
                    int paid = Integer.parseInt(txt_Deposit.getText());
                    int pending = price - paid;
                    txt_Pending.setText(String.valueOf(pending));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        else if (e.getSource() == btnUpdate) {
            try {
                String room = txt_ID.getText();
                String name = txt_Name.getText();
                String status = txt_Status.getText();
                String deposit = txt_Deposit.getText();

                Conn c = new Conn();
                String updateQuery = "UPDATE customer SET room_number = '" + room +
                        "', name = '" + name +
                        "', checkin_status = '" + status +
                        "', deposit = '" + deposit +
                        "' WHERE id_number = '" + selectedId + "'";

                c.s.executeUpdate(updateQuery);

                JOptionPane.showMessageDialog(null, "Data Updated Successfully");
                new Reception().setVisible(true);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        else if (e.getSource() == btnBack) {
            new Reception().setVisible(true);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new UpdateCheck();
    }
}
