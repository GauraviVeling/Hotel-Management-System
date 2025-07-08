
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ManagerInfo extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private JButton btnLoadData, btnExit;

    public ManagerInfo() {
        setTitle("Manager Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(430, 200, 1000, 600);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        // Column Labels
        contentPane.add(createLabel("Name", 40));
        contentPane.add(createLabel("Age", 150));
        contentPane.add(createLabel("Gender", 260));
        contentPane.add(createLabel("Job", 370));
        contentPane.add(createLabel("Salary", 490));
        contentPane.add(createLabel("Phone", 600));
        contentPane.add(createLabel("Aadhar", 710));
        contentPane.add(createLabel("Gmail", 820));

        // Table Setup
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
            "Name", "Age", "Gender", "Job", "Salary", "Phone", "Aadhar", "Email"
        });

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 40, 960, 430);
        contentPane.add(scrollPane);

        // Load Data Button
        btnLoadData = new JButton("Load Data");
        btnLoadData.setBounds(350, 500, 120, 30);
        btnLoadData.setBackground(Color.BLACK);
        btnLoadData.setForeground(Color.WHITE);
        btnLoadData.addActionListener(this);
        contentPane.add(btnLoadData);

        // Back Button
        btnExit = new JButton("Back");
        btnExit.setBounds(510, 500, 120, 30);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        btnExit.addActionListener(this);
        contentPane.add(btnExit);

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
        if (e.getSource() == btnLoadData) {
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("SELECT * FROM employee WHERE job = 'Manager'");

                model.setRowCount(0); // Clear old data

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
                JOptionPane.showMessageDialog(null, "Error loading manager data.");
            }
        } else if (e.getSource() == btnExit) {
            new Reception().setVisible(true);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new ManagerInfo();
    }
}
