

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class NewCustomer extends JFrame implements ActionListener {

    JPanel contentPane;
    JTextField numberField, nameField, countryField, checkinField, depositField;
    JComboBox<String> idComboBox;
    JRadioButton maleRadio, femaleRadio;
    Choice roomChoice;
    JButton addButton, backButton;

    public NewCustomer() {
        setBounds(530, 200, 850, 550);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        // Image on the right
        ImageIcon imgIcon = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.png"));
        Image scaledImg = imgIcon.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        JLabel imgLabel = new JLabel(new ImageIcon(scaledImg));
        imgLabel.setBounds(480, 10, 300, 500);
        contentPane.add(imgLabel);

        JLabel heading = new JLabel("NEW CUSTOMER FORM");
        heading.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        heading.setBounds(118, 11, 260, 53);
        contentPane.add(heading);

        // ID Type
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(35, 76, 200, 14);
        contentPane.add(idLabel);

        idComboBox = new JComboBox<>(new String[]{"Passport", "Aadhar Card", "Voter Id", "Driving License"});
        idComboBox.setBounds(271, 73, 150, 20);
        contentPane.add(idComboBox);

        // ID Number
        JLabel numberLabel = new JLabel("Number:");
        numberLabel.setBounds(35, 111, 200, 14);
        contentPane.add(numberLabel);

        numberField = new JTextField();
        numberField.setBounds(271, 111, 150, 20);
        contentPane.add(numberField);

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(35, 151, 200, 14);
        contentPane.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(271, 151, 150, 20);
        contentPane.add(nameField);

        // Gender
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(35, 191, 200, 14);
        contentPane.add(genderLabel);

        maleRadio = new JRadioButton("Male");
        maleRadio.setBounds(271, 191, 70, 20);
        maleRadio.setBackground(Color.WHITE);
        contentPane.add(maleRadio);

        femaleRadio = new JRadioButton("Female");
        femaleRadio.setBounds(350, 191, 100, 20);
        femaleRadio.setBackground(Color.WHITE);
        contentPane.add(femaleRadio);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        // Country
        JLabel countryLabel = new JLabel("Country:");
        countryLabel.setBounds(35, 231, 200, 14);
        contentPane.add(countryLabel);

        countryField = new JTextField();
        countryField.setBounds(271, 231, 150, 20);
        contentPane.add(countryField);

        // Room
        JLabel roomLabel = new JLabel("Allocated Room Number:");
        roomLabel.setBounds(35, 274, 200, 14);
        contentPane.add(roomLabel);

        roomChoice = new Choice();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM room WHERE availability = 'Available'");
            while (rs.next()) {
                roomChoice.add(rs.getString("room_number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        roomChoice.setBounds(271, 274, 150, 20);
        contentPane.add(roomChoice);

        // Check-in
        JLabel checkinLabel = new JLabel("Checked-In:");
        checkinLabel.setBounds(35, 316, 200, 14);
        contentPane.add(checkinLabel);

        checkinField = new JTextField();
        checkinField.setBounds(271, 316, 150, 20);
        contentPane.add(checkinField);

        // Deposit
        JLabel depositLabel = new JLabel("Deposit:");
        depositLabel.setBounds(35, 359, 200, 14);
        contentPane.add(depositLabel);

        depositField = new JTextField();
        depositField.setBounds(271, 359, 150, 20);
        contentPane.add(depositField);

        // Add Button
        addButton = new JButton("Add");
        addButton.setBounds(100, 430, 120, 30);
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(this);
        contentPane.add(addButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(260, 430, 120, 30);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        contentPane.add(backButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String idType = (String) idComboBox.getSelectedItem();
            String idNumber = numberField.getText();
            String name = nameField.getText();
            String gender = maleRadio.isSelected() ? "Male" : (femaleRadio.isSelected() ? "Female" : "");
            String country = countryField.getText();
            String room = roomChoice.getSelectedItem();
            String checkin = checkinField.getText();
            String deposit = depositField.getText();

            if (idNumber.isEmpty() || name.isEmpty() || gender.isEmpty() || country.isEmpty() || checkin.isEmpty() || deposit.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields.");
                return;
            }

            try {
                Conn conn = new Conn();
                String q1 = "INSERT INTO customer VALUES('" + idType + "','" + idNumber + "','" + name + "','" + gender + "','" + country + "','" + room + "','" + checkin + "','" + deposit + "')";
                String q2 = "UPDATE room SET availability = 'Occupied' WHERE room_number = '" + room + "'";

                conn.s.executeUpdate(q1);
                conn.s.executeUpdate(q2);

                JOptionPane.showMessageDialog(null, "Customer Added Successfully");
                new Reception().setVisible(true);
                setVisible(false);

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error in saving customer data.");
            }

        } else if (e.getSource() == backButton) {
            new Reception().setVisible(true);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NewCustomer();
    }
}
