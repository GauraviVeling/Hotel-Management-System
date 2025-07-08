import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddRooms extends JFrame implements ActionListener {

    // Global declarations
    JTextField tfroom, tfprice;
    JComboBox<String> availablecombo, cleancombo, bedcombo;
    JButton btnAddRoom, btnCancel;

    public AddRooms() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Heading
        JLabel heading = new JLabel("Add Rooms");
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        heading.setBounds(150, 20, 200, 20);
        add(heading);

        // Room Number
        JLabel lblroom = new JLabel("Room Number");
        lblroom.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblroom.setBounds(50, 80, 100, 25);
        add(lblroom);

        tfroom = new JTextField();
        tfroom.setBounds(200, 80, 150, 30);
        add(tfroom);

        // Availability
        JLabel lblavailable = new JLabel("Availability");
        lblavailable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblavailable.setBounds(50, 140, 100, 25);
        add(lblavailable);

        String[] availabilityOptions = { "Available", "Occupied" };
        availablecombo = new JComboBox<>(availabilityOptions);
        availablecombo.setBounds(200, 140, 150, 30);
        availablecombo.setBackground(Color.WHITE);
        add(availablecombo);

        // Cleaning Status
        JLabel lblclean = new JLabel("Cleaning Status");
        lblclean.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblclean.setBounds(50, 200, 120, 25);
        add(lblclean);

        String[] cleaningOptions = { "Cleaned", "Dirty" };
        cleancombo = new JComboBox<>(cleaningOptions);
        cleancombo.setBounds(200, 200, 150, 30);
        cleancombo.setBackground(Color.WHITE);
        add(cleancombo);

        // Price
        JLabel lblprice = new JLabel("Price");
        lblprice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblprice.setBounds(50, 260, 100, 25);
        add(lblprice);

        tfprice = new JTextField();
        tfprice.setBounds(200, 260, 150, 30);
        add(tfprice);

        // Bed Type
        JLabel lblbed = new JLabel("Bed Type");
        lblbed.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblbed.setBounds(50, 320, 100, 25);
        add(lblbed);

        String[] bedOptions = { "Single", "Double" };
        bedcombo = new JComboBox<>(bedOptions);
        bedcombo.setBounds(200, 320, 150, 30);
        bedcombo.setBackground(Color.WHITE);
        add(bedcombo);

        // Add Room Button
        btnAddRoom = new JButton("Add Room");
        btnAddRoom.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAddRoom.setBounds(150, 380, 150, 30);
        btnAddRoom.setBackground(Color.BLACK);
        btnAddRoom.setForeground(Color.WHITE);
        btnAddRoom.addActionListener(this);
        add(btnAddRoom);

        // Cancel Button
        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCancel.setBounds(320, 380, 150, 30);
        btnCancel.setBackground(Color.RED);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.addActionListener(this);
        add(btnCancel);

        // Load Image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/twelve.jpg"));
        Image img = imageIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(550, 80, 300, 200);
        add(imageLabel);

        // Frame settings
        setBounds(330, 200, 940, 470);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnAddRoom) {
            // Fetch form data
            String roomNumber = tfroom.getText();
            String price = tfprice.getText();
            String availability = (String) availablecombo.getSelectedItem();
            String cleaning = (String) cleancombo.getSelectedItem();
            String bed = (String) bedcombo.getSelectedItem();

            try {
                Conn conn = new Conn();
                String query = "INSERT INTO room VALUES ('" + roomNumber + "', '" + availability + "', '" + cleaning + "', '" + price + "', '" + bed + "')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Room Added Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == btnCancel) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new AddRooms();
    }
}
