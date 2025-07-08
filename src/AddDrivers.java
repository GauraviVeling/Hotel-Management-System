
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddDrivers extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField nameField, ageField, companyField, brandField, locationField;
    private JComboBox<String> genderCombo, availableCombo;
    private JButton addButton, backButton;

    public AddDrivers() {
        setTitle("Add Drivers");
        setBounds(450, 200, 1000, 500);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Driver image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
        Image img = imageIcon.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setBounds(400, 30, 500, 370);
        add(imageLabel);

        JLabel heading = new JLabel("Add Drivers");
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        heading.setBounds(194, 10, 120, 22);
        contentPane.add(heading);

        // Name
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(64, 70, 102, 22);
        contentPane.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(174, 70, 156, 20);
        contentPane.add(nameField);

        // Age
        JLabel ageLabel = new JLabel("Age");
        ageLabel.setBounds(64, 110, 102, 22);
        contentPane.add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(174, 110, 156, 20);
        contentPane.add(ageField);

        // Gender
        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setBounds(64, 150, 102, 22);
        contentPane.add(genderLabel);

        genderCombo = new JComboBox<>(new String[]{"Male", "Female"});
        genderCombo.setBounds(174, 150, 156, 20);
        contentPane.add(genderCombo);

        // Car Company
        JLabel companyLabel = new JLabel("Car Company");
        companyLabel.setBounds(64, 190, 102, 22);
        contentPane.add(companyLabel);

        companyField = new JTextField();
        companyField.setBounds(174, 190, 156, 20);
        contentPane.add(companyField);

        // Car Brand
        JLabel brandLabel = new JLabel("Car Brand");
        brandLabel.setBounds(64, 230, 102, 22);
        contentPane.add(brandLabel);

        brandField = new JTextField();
        brandField.setBounds(174, 230, 156, 20);
        contentPane.add(brandField);

        // Availability
        JLabel availableLabel = new JLabel("Available");
        availableLabel.setBounds(64, 270, 102, 22);
        contentPane.add(availableLabel);

        availableCombo = new JComboBox<>(new String[]{"Yes", "No"});
        availableCombo.setBounds(174, 270, 156, 20);
        contentPane.add(availableCombo);

        // Location
        JLabel locationLabel = new JLabel("Location");
        locationLabel.setBounds(64, 310, 102, 22);
        contentPane.add(locationLabel);

        locationField = new JTextField();
        locationField.setBounds(174, 310, 156, 20);
        contentPane.add(locationField);

        // Buttons
        addButton = new JButton("Add");
        addButton.setBounds(64, 380, 111, 33);
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(this);
        contentPane.add(addButton);

        backButton = new JButton("Back");
        backButton.setBounds(198, 380, 111, 33);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        contentPane.add(backButton);

        contentPane.setBackground(Color.WHITE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addButton) {
            try {
                String name = nameField.getText();
                String age = ageField.getText();
                String gender = (String) genderCombo.getSelectedItem();
                String company = companyField.getText();
                String brand = brandField.getText();
                String available = (String) availableCombo.getSelectedItem();
                String location = locationField.getText();

                String query = "INSERT INTO driver VALUES('" + name + "','" + age + "','" + gender + "','" + company + "','" + brand + "','" + available + "','" + location + "')";

                Conn conn = new Conn();
                conn.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Driver Successfully Added");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error Adding Driver");
            }
        } else if (ae.getSource() == backButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddDrivers();
    }
}
