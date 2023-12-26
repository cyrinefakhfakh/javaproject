import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class admin extends JFrame {
    private JComboBox<String> jComboBox1;
    public admin() {
        JFrame f = new JFrame("Admin");
        f.setSize(500, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation(dim.width / 2 - f.getWidth() / 2, dim.height / 2 - f.getHeight() / 2);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(new Color(219, 183, 160));

        JLabel jLabel1 = new JLabel("Choice:");
        panel.add(jLabel1);

        jComboBox1 = new JComboBox<>();
        jComboBox1.addItem("Speciality");
        jComboBox1.addItem("Club");
    
        panel.add(jComboBox1);

        String[] columnNames = {"name", "age", "Speciality", "Club"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        try (
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from client")) {

            while (rs.next()) {
                String name = rs.getString("name");
                String age = rs.getString("age");
                String speciality = rs.getString("speciality");
                String club = rs.getString("club");
                model.addRow(new Object[]{name, age, speciality, club});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }




        JButton filtrer = new JButton("Filtrer");
        filtrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String choice = (String) jComboBox1.getSelectedItem();
                if (choice.equals("Speciality")) {                                                                                           
                            special s = new special();
                            s.setVisible(true);}
                            else if (choice.equals("Club")) {      
                                club c = new club();
                                c.setVisible(true);
                }
                
            }
        });
        panel.add(filtrer);





        JButton delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter the name of the client you want to delete");
                try (
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
                     Statement stmt = con.createStatement()) {
                    stmt.executeUpdate("delete from client where name = '" + name + "'");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Client deleted");
            }
        });
        panel.add(delete);




        JButton update = new JButton("Update");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter the name of the client you want to update");
                String age = JOptionPane.showInputDialog("Enter the new age");
                String speciality = JOptionPane.showInputDialog("Enter the new speciality");
                String club = JOptionPane.showInputDialog("Enter the new club");
                try (
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
                     Statement stmt = con.createStatement()) {
                    stmt.executeUpdate("update client set age = '" + age + "', speciality = '" + speciality + "', club = '" + club + "' where name = '" + name + "'");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Client updated");
            }
        });
        panel.add(update);



        JButton refrech = new JButton("Refresh");
        refrech.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                admin a = new admin();
                a.setVisible(true);
            }
        });
        panel.add(refrech);

        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        f.add(panel);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true); }}     