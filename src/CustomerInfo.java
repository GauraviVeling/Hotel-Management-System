

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CustomerInfo extends JFrame implements ActionListener {

    JPanel contentPane;
    JTable table;
    JButton btnLoad, btnBack;
    DefaultTableModel model;

    public CustomerInfo() {
        setTitle("Customer Information");
        setBounds(530, 200, 900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        // Table headings
        contentPane.add(createLabel("ID Type", 20));
        contentPane.add(createLabel("ID Number", 90));
        contentPane.add(createLabel("Name", 180));
        contentPane.add(createLabel("Gender", 270));
        contentPane.add(createLabel("Country", 370));
        contentPane.add(createLabel("Room", 460));
        contentPane.add(createLabel("Check-in Status", 550));
        contentPane.add(createLabel("Deposit", 700));

        // Table and model setup
        table = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
            "ID Type", "ID Number", "Name", "Gender", "Country", "Room", "Check-in Status", "Deposit"
        });
        table.setModel(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 40, 860, 440);
        contentPane.add(scrollPane);

        // Load Data Button
        btnLoad = new JButton("Load Data");
        btnLoad.setBounds(300, 500, 120, 30);
        btnLoad.setBackground(Color.BLACK);
        btnLoad.setForeground(Color.WHITE);
        btnLoad.addActionListener(this);
        contentPane.add(btnLoad);

        // Back Button
        btnBack = new JButton("Back");
        btnBack.setBounds(450, 500, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        btnBack.addActionListener(this);
        contentPane.add(btnBack);

        setVisible(true);
    }

    private JLabel createLabel(String text, int x) {
        JLabel label = new JLabel(text);
        label.setBounds(x, 10, 150, 20);
        label.setFont(new Font("Tahoma", Font.BOLD, 12));
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLoad) {
            try {
                Conn c = new Conn(); // your DB connection class
                ResultSet rs = c.s.executeQuery("SELECT * FROM customer");

                // Clear old data
                model.setRowCount(0);

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("id_type"),
                        rs.getString("id_number"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("country"),
                        rs.getString("room_number"),
                        rs.getString("checkin_status"),
                        rs.getString("deposit")
                    });
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading customer data.");
            }
        } else if (e.getSource() == btnBack) {
            new Reception().setVisible(true);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new CustomerInfo();
    }
}
