

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Employee extends JFrame implements ActionListener {

    JPanel contentPane;
    JTable table;
    JButton btnLoad, btnBack;
    DefaultTableModel model;

    public Employee() {
        setTitle("Employee Information");
        setBounds(430, 200, 1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        // Column headers
        contentPane.add(createLabel("Name", 20));
        contentPane.add(createLabel("Age", 130));
        contentPane.add(createLabel("Gender", 230));
        contentPane.add(createLabel("Job", 330));
        contentPane.add(createLabel("Salary", 440));
        contentPane.add(createLabel("Phone", 550));
        contentPane.add(createLabel("Aadhar", 670));
        contentPane.add(createLabel("Email", 800));

        // Table setup
        table = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
            "Name", "Age", "Gender", "Job", "Salary", "Phone", "Aadhar", "Email"
        });
        table.setModel(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 40, 960, 420);
        contentPane.add(scrollPane);

        // Load Data Button
        btnLoad = new JButton("Load Data");
        btnLoad.setBounds(350, 500, 120, 30);
        btnLoad.setBackground(Color.BLACK);
        btnLoad.setForeground(Color.WHITE);
        btnLoad.addActionListener(this);
        contentPane.add(btnLoad);

        // Back Button
        btnBack = new JButton("Back");
        btnBack.setBounds(510, 500, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        btnBack.addActionListener(this);
        contentPane.add(btnBack);

        setVisible(true);
    }

    private JLabel createLabel(String text, int x) {
        JLabel label = new JLabel(text);
        label.setBounds(x, 10, 100, 20);
        label.setFont(new Font("Tahoma", Font.BOLD, 12));
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLoad) {
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("SELECT * FROM employee");

                // Clear existing rows
                model.setRowCount(0);

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("name"),
                        rs.getString("age"),
                        rs.getString("gender"),
                        rs.getString("job"),
                        rs.getString("salary"),
                        rs.getString("phone"),
                        rs.getString("aadhar"),
                        rs.getString("email")
                    });
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to load employee data.");
            }
        } else if (e.getSource() == btnBack) {
            new Reception().setVisible(true);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Employee();
    }
}
