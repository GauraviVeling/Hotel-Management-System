import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Room extends JFrame implements ActionListener {

    JPanel contentPane;
    JTable roomTable;
    JButton btnLoad, btnBack;

    public Room() {
        setTitle("Room Details");
        setBounds(450, 200, 1100, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        // Labels
        contentPane.add(createLabel("Room Number", 10));
        contentPane.add(createLabel("Availability", 120));
        contentPane.add(createLabel("Clean Status", 230));
        contentPane.add(createLabel("Price", 340));
        contentPane.add(createLabel("Bed Type", 440));

        // Table
        roomTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(roomTable);
        scrollPane.setBounds(10, 40, 480, 400);
        contentPane.add(scrollPane);

        // Load Data Button
        btnLoad = new JButton("Load Data");
        btnLoad.setBounds(100, 470, 120, 30);
        btnLoad.setBackground(Color.BLACK);
        btnLoad.setForeground(Color.WHITE);
        btnLoad.addActionListener(this);
        contentPane.add(btnLoad);

        // Back Button
        btnBack = new JButton("Back");
        btnBack.setBounds(280, 470, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        btnBack.addActionListener(this);
        contentPane.add(btnBack);

        // Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        JLabel imageLabel = new JLabel(new ImageIcon(i2));
        imageLabel.setBounds(500, 0, 600, 600);
        contentPane.add(imageLabel);

        setVisible(true);
    }

    private JLabel createLabel(String text, int x) {
        JLabel label = new JLabel(text);
        label.setBounds(x, 15, 100, 20);
        label.setFont(new Font("Tahoma", Font.BOLD, 12));
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLoad) {
            try {
                Conn c = new Conn();
                String query = "SELECT * FROM room";
                ResultSet rs = c.s.executeQuery(query);

                // Table Model
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Room Number");
                model.addColumn("Availability");
                model.addColumn("Clean Status");
                model.addColumn("Price");
                model.addColumn("Bed Type");

                while (rs.next()) {
                    String rno = rs.getString("room_number");
                    String availability = rs.getString("availability");
                    String status = rs.getString("status");
                    String price = rs.getString("price");
                    String bed = rs.getString("bed_type");
                    model.addRow(new Object[]{rno, availability, status, price, bed});
                }
                roomTable.setModel(model);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to load data");
            }
        } else if (e.getSource() == btnBack) {
            new Reception().setVisible(true);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Room();
    }
}
