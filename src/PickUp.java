import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class PickUp extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTable table;
    private Choice c1;
    private JButton btnDisplay, btnBack;

    public PickUp() {
        setTitle("Pick Up Service");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 800, 600);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Pick Up Service");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTitle.setBounds(90, 11, 200, 25);
        contentPane.add(lblTitle);

        JLabel lblTypeOfCar = new JLabel("Type of Car");
        lblTypeOfCar.setBounds(32, 97, 89, 14);
        contentPane.add(lblTypeOfCar);

        c1 = new Choice();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT DISTINCT car_brand FROM driver");
            while (rs.next()) {
                c1.add(rs.getString("car_brand"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(123, 94, 150, 25);
        contentPane.add(c1);

        // Column headers
        String[] columnNames = { "Name", "Age", "Gender", "Company", "Brand", "Available", "Location" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 230, 780, 250);
        contentPane.add(scrollPane);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(24, 208, 80, 14);
        contentPane.add(lblName);

        JLabel lblAge = new JLabel("Age");
        lblAge.setBounds(165, 208, 80, 14);
        contentPane.add(lblAge);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(264, 208, 80, 14);
        contentPane.add(lblGender);

        JLabel lblCompany = new JLabel("Company");
        lblCompany.setBounds(366, 208, 80, 14);
        contentPane.add(lblCompany);

        JLabel lblBrand = new JLabel("Brand");
        lblBrand.setBounds(486, 208, 80, 14);
        contentPane.add(lblBrand);

        JLabel lblAvailable = new JLabel("Available");
        lblAvailable.setBounds(600, 208, 80, 14);
        contentPane.add(lblAvailable);

        JLabel lblLocation = new JLabel("Location");
        lblLocation.setBounds(700, 208, 80, 14);
        contentPane.add(lblLocation);

        // Buttons
        btnDisplay = new JButton("Display");
        btnDisplay.setBounds(200, 500, 120, 30);
        btnDisplay.setBackground(Color.BLACK);
        btnDisplay.setForeground(Color.WHITE);
        contentPane.add(btnDisplay);

        btnBack = new JButton("Back");
        btnBack.setBounds(420, 500, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        // Action Listeners
        btnDisplay.addActionListener(this);
        btnBack.addActionListener(this);

        setVisible(true);
    }

    // ------------------- ACTION HANDLING AT BOTTOM -------------------

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDisplay) {
            String selectedCarBrand = c1.getSelectedItem();
            String query = "SELECT * FROM driver WHERE car_brand = '" + selectedCarBrand + "'";

            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0); // Clear previous data

                while (rs.next()) {
                    String name = rs.getString("name");
                    String age = rs.getString("age");
                    String gender = rs.getString("gender");
                    String company = rs.getString("car_company");
                    String brand = rs.getString("car_brand");
                    String available = rs.getString("availability");
                    String location = rs.getString("location");

                    model.addRow(new Object[] { name, age, gender, company, brand, available, location });
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to retrieve data");
            }
        }

        else if (e.getSource() == btnBack) {
            new Reception().setVisible(true);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new PickUp();
    }
}
