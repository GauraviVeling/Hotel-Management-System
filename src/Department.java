

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Department extends JFrame implements ActionListener {

    JPanel contentPane;
    JTable deptTable;
    JButton btnLoad, btnBack;

    public Department() {
        setTitle("Department Details");
        setBounds(600, 200, 700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        // Table headers
        contentPane.add(createLabel("Department", 120));
        contentPane.add(createLabel("Budget", 420));

        // Table setup
        deptTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(deptTable);
        scrollPane.setBounds(10, 40, 660, 350);
        contentPane.add(scrollPane);

        // Load Data button
        btnLoad = new JButton("Load Data");
        btnLoad.setBounds(150, 410, 120, 30);
        btnLoad.setBackground(Color.BLACK);
        btnLoad.setForeground(Color.WHITE);
        btnLoad.addActionListener(this);
        contentPane.add(btnLoad);

        // Back button
        btnBack = new JButton("Back");
        btnBack.setBounds(400, 410, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        btnBack.addActionListener(this);
        contentPane.add(btnBack);

        setVisible(true);
    }

    // Helper method to create consistent labels
    private JLabel createLabel(String text, int x) {
        JLabel label = new JLabel(text);
        label.setBounds(x, 10, 120, 20);
        label.setFont(new Font("Tahoma", Font.BOLD, 12));
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLoad) {
            try {
                Conn c = new Conn();
                String query = "SELECT * FROM department";
                ResultSet rs = c.s.executeQuery(query);

                // Define table model
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Department");
                model.addColumn("Budget");

                while (rs.next()) {
                    String department = rs.getString("department");
                    String budget = rs.getString("budget");
                    model.addRow(new Object[]{department, budget});
                }
                deptTable.setModel(model);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to load department data");
            }
        } else if (e.getSource() == btnBack) {
            new Reception().setVisible(true);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Department();
    }
}
