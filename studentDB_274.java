import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class StudentDB_274 {
    public static void main(String[] a) throws Exception {
        JFrame f_274 = new JFrame("Students");
        f_274.setLayout(new FlowLayout());

        JTextField id_274 = new JTextField(3),
                n_274 = new JTextField(6),
                d_274 = new JTextField(6),
                m_274 = new JTextField(4),
                s_274 = new JTextField(6);

        JButton add_274 = new JButton("Add"),
                search_274 = new JButton("Search");

        f_274.add(new JLabel("ID"));
        f_274.add(id_274);
        f_274.add(new JLabel("Name"));
        f_274.add(n_274);
        f_274.add(new JLabel("Dept"));
        f_274.add(d_274);
        f_274.add(new JLabel("Marks"));
        f_274.add(m_274);
        f_274.add(add_274);
        f_274.add(new JLabel("Search Name"));
        f_274.add(s_274);
        f_274.add(search_274);

        Connection c_274 = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "himank");
        c_274.createStatement().execute("CREATE TABLE IF NOT EXISTS students(id INT, name VARCHAR(50), department VARCHAR(50), marks INT)");

        add_274.addActionListener(e -> {
            try {
                PreparedStatement ps = c_274.prepareStatement("INSERT INTO students VALUES(?,?,?,?)");
                ps.setInt(1, Integer.parseInt(id_274.getText()));
                ps.setString(2, n_274.getText());
                ps.setString(3, d_274.getText());
                ps.setInt(4, Integer.parseInt(m_274.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(f_274, "Added!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f_274, ex.getMessage());
            }
        });

        search_274.addActionListener(e -> {
            try {
                PreparedStatement ps = c_274.prepareStatement("SELECT * FROM students WHERE name=?");
                ps.setString(1, s_274.getText());
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                    JOptionPane.showMessageDialog(f_274,
                            "ID:" + rs.getInt(1) +
                                    "\nName:" + rs.getString(2) +
                                    "\nDept:" + rs.getString(3) +
                                    "\nMarks:" + rs.getInt(4));
                else
                    JOptionPane.showMessageDialog(f_274, "No record found!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f_274, ex.getMessage());
            }
        });

        f_274.setSize(280, 250);
        f_274.setVisible(true);
    }
}
