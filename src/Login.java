import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener{

    JTextField username ;
    JPasswordField password;
    JButton login , cancel ;

    public Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel user = new JLabel("Username");
        user.setBounds(40, 20, 100, 30);
        add(user);

        username = new JTextField();
        username.setBounds(150, 20, 150, 30);
        add(username);

        JLabel pass = new JLabel("Password");
        pass.setBounds(40, 70, 100, 30);
        add(pass);

        password = new JPasswordField();
        password.setBounds(150, 70, 150, 30);
        add(password);

        login = new JButton("Login");
        login.setBounds(40, 130, 120, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(180, 130, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        // Image on the right
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 20, 200, 200);
        add(image);

        setSize(600, 300); // Fixed size looks better here than full screen
        setLocationRelativeTo(null); // Center on screen
        setUndecorated(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
        
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == login){
            String user = username.getText();
            String pass = new String(password.getPassword()); // better than getText()

            try {
                Conn c = new Conn();

                // Prefer prepared statement
                PreparedStatement ps = c.c.prepareStatement("SELECT * FROM login WHERE username=? AND password=?");
                ps.setString(1, user);
                ps.setString(2, pass);

                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    dispose();
                    new Dashboard(); // assuming Dashboard() is implemented
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(ae.getSource() == cancel){
            dispose();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
