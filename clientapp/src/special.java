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

public class special  extends JFrame {
    private JComboBox<String> jComboBox1;
    String[] columnNames = {"name", "age", "Speciality", "Club"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
JTable table = new JTable(model);

    public special(){
         JFrame fra = new JFrame("Speciality");
                        fra.setSize(500, 300);
                        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                        fra.setLocation(dim.width / 2 - fra.getWidth() / 2, dim.height / 2 - fra.getHeight() / 2);
                
                        JPanel panel = new JPanel();
                        panel.setLayout(new FlowLayout());
                        panel.setBackground(new Color(219, 183, 160));
                
                        JLabel jLabel1 = new JLabel("Choice:");
                        panel.add(jLabel1);
                
                        jComboBox1 = new JComboBox<>();
                        jComboBox1.addItem("");
                        jComboBox1.addItem("Im");
                        jComboBox1.addItem("big data");
                        jComboBox1.addItem("mime");
                        jComboBox1.addItem("jeux video");
                        jComboBox1.addItem("cm");
                        panel.add(jComboBox1);
                
                        
                        jComboBox1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String choice = (String) jComboBox1.getSelectedItem();
                               
                        
                                
                                try (
                                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
                                    Statement stmt = con.createStatement();
                                    ResultSet rs = stmt.executeQuery("select * from client where speciality = '" + choice + "'")) {
                                    model.setRowCount(0);
                                    while (rs.next()) {
                                        String name = rs.getString("name");
                                        String age = rs.getString("age");
                                        String speciality = rs.getString("speciality");
                                        String club = rs.getString("club");
                                        
                                        model.addRow(new Object[]{name, age, speciality, club});
                                    }
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                               
                                
                                
                                
                            }});
                                JScrollPane scrollPane = new JScrollPane(table);
                                panel.add(scrollPane);
                                fra.add(panel);
                                fra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                fra.setVisible(true);
    }
    
}
