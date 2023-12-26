import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class club  extends JFrame  {
    private JComboBox<String> jComboBox1;
    String[] columnNames = {"name", "age", "Speciality", "Club"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

    public club() {
        JFrame fram = new JFrame("Club");
                            fram.setSize(500, 500);
                            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                            fram.setLocation(dim.width / 2 - fram.getWidth() / 2, dim.height / 2 - fram.getHeight() / 2); 
                            JPanel panel = new JPanel();
                            panel.setLayout(new FlowLayout());
                            panel.setBackground(new Color(219, 183, 160));
                            
                            JLabel jLabel1 = new JLabel("Choice:");
                            panel.add(jLabel1);

                            jComboBox1 = new JComboBox<>();
                            jComboBox1.addItem("");
                            jComboBox1.addItem("robotique");
                            jComboBox1.addItem("spark");
                            jComboBox1.addItem("log game"); 
                            jComboBox1.addItem("microsoft");
                            jComboBox1.addItem("radio");
                            panel.add(jComboBox1);

                            
                            jComboBox1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String choice = (String) jComboBox1.getSelectedItem();
                                    
                                    
                                    
                                    try (
                                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
                                        Statement stmt = con.createStatement();
                                        ResultSet rs = stmt.executeQuery("select * from client where club = '" + choice + "'")) {
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
                                    
                                    
                                }
                            });
                                    JTable table = new JTable(model);
                                    JScrollPane scrollPane = new JScrollPane(table);
                                    panel.add(scrollPane);      
                                    fram.add(panel);
                                    fram.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    fram.setVisible(true);
    }
}
