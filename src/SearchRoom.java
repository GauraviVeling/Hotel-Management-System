import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class SearchRoom extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private Choice c1;
    private JCheckBox checkRoom;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SearchRoom frame = new SearchRoom();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SearchRoom() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 800, 500);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblSearchForRoom = new JLabel("Search For Room");
        lblSearchForRoom.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblSearchForRoom.setBounds(280, 11, 200, 30);
        contentPane.add(lblSearchForRoom);

        JLabel lblRoomAvailable = new JLabel("Room Bed Type:");
        lblRoomAvailable.setBounds(50, 73, 120, 14);
        contentPane.add(lblRoomAvailable);

        c1 = new Choice();
        c1.add("Single Bed");
        c1.add("Double Bed");
        c1.setBounds(170, 70, 120, 20);
        contentPane.add(c1);

        checkRoom = new JCheckBox("Only display Available");
        checkRoom.setBackground(Color.WHITE);
        checkRoom.setBounds(400, 69, 200, 23);
        contentPane.add(checkRoom);

        // Table Header Labels
        JLabel lblRoomNumber = new JLabel("Room Number");
        lblRoomNumber.setBounds(30, 162, 100, 14);
        contentPane.add(lblRoomNumber);

        JLabel lblAvailability = new JLabel("Availability");
        lblAvailability.setBounds(160, 162, 100, 14);
        contentPane.add(lblAvailability);

        JLabel lblCleanStatus = new JLabel("Clean Status");
        lblCleanStatus.setBounds(280, 162, 100, 14);
        contentPane.add(lblCleanStatus);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(420, 162, 80, 14);
        contentPane.add(lblPrice);

        JLabel lblBedType = new JLabel("Bed Type");
        lblBedType.setBounds(520, 162, 100, 14);
        contentPane.add(lblBedType);

        // Table
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 187, 780, 180);
        contentPane.add(scrollPane);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(200, 400, 120, 30);
        btnSearch.setBackground(Color.BLACK);
        btnSearch.setForeground(Color.WHITE);
        contentPane.add(btnSearch);

        JButton btnExit = new JButton("Back");
        btnExit.setBounds(380, 400, 120, 30);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);

        // Action Listeners
        btnSearch.addActionListener(e -> fetchData());
        btnExit.addActionListener(e -> {
            new Reception().setVisible(true);
            setVisible(false);
        });
    }

    private void fetchData() {
        String bedType = c1.getSelectedItem();
        boolean onlyAvailable = checkRoom.isSelected();

        String query;
        if (onlyAvailable) {
            query = "SELECT * FROM room WHERE availability = 'Available' AND bed_type = '" + bedType + "'";
        } else {
            query = "SELECT * FROM room WHERE bed_type = '" + bedType + "'";
        }

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery(query);

            // Set up table model manually
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Room Number");
            model.addColumn("Availability");
            model.addColumn("Clean Status");
            model.addColumn("Price");
            model.addColumn("Bed Type");

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("room_number"),
                    rs.getString("availability"),
                    rs.getString("clean_status"),
                    rs.getString("price"),
                    rs.getString("bed_type")
                });
            }

            table.setModel(model);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading room data.");
        }
    }
}
