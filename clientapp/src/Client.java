import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class Client extends JFrame {  
    public Client(){
        JFrame newFrame = new JFrame("client");
            newFrame.setSize(180, 250);
            newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            newFrame.setLocation(dim.width / 2 - newFrame.getWidth() / 2, dim.height / 2 - newFrame.getHeight() / 2);
            newFrame.setVisible(true);

            JLabel jlab = new JLabel("Name:");
            
            JTextField jtf = new JTextField(10);
            JLabel jlab1 = new JLabel("Age:");
            

            JTextField jtf1 = new JTextField(10);
            JLabel jlab2 = new JLabel("speciality:");
            
        
            JComboBox<String> jcb = new JComboBox<>();
            jcb.addItem("");
            jcb.addItem("Im");
            jcb.addItem("big data");
            jcb.addItem("mime");
            jcb.addItem("jeux video");
            jcb.addItem("cm");

            JLabel jlab3 = new JLabel("club:");   
            JComboBox<String> jcb1 = new JComboBox<>();
            jcb1.addItem("");
            jcb1.addItem("robotique");
            jcb1.addItem("spark");
            jcb1.addItem("log game");
            jcb1.addItem("microsoft");
            jcb1.addItem("radio");

            JButton jbt = new JButton("add");
            jbt.setBackground(new Color(243, 239, 234));
            Border line = new LineBorder(Color.BLACK);
            Border margin = new EmptyBorder(5, 15, 5, 15);
            Border compound = new CompoundBorder(line, margin);
            jbt.setBorder(compound);
            jbt.addActionListener(e -> {
                String name = jtf.getText();
                if (name == null || name.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter your name");
                    return;
                }
                String age = jtf1.getText();
                if (age == null || age.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter your age");
                    return;
                }
                String speciality = (String) jcb.getSelectedItem();
                String club = (String) jcb1.getSelectedItem();

                // add client to database
                addclienttoDB(name, age, speciality, club);
                JOptionPane.showMessageDialog(null, "client added successfully");
                
               
                jtf.setText("");
                jtf1.setText("");
            });
            JButton jbt1 = new JButton("close");
            jbt1.setBackground(new Color(243, 239, 234));
            Border lin = new LineBorder(Color.BLACK);
            Border margi = new EmptyBorder(5, 15, 5, 15);
            Border compoun = new CompoundBorder(lin, margi);
            jbt1.setBorder(compoun);
            jbt1.addActionListener(e -> newFrame.dispose());
            JButton jbt2 = new JButton("return");
            
            jbt2.addActionListener(e -> {
                acceuil a = new acceuil();
                a.setVisible(true);
                dispose();
                 
            });

            JPanel p = new JPanel(new FlowLayout());
			p.setBackground(new Color(219, 183, 160));
            p.add(jlab);
            p.add(jtf);
            p.add(jlab1);
            p.add(jtf1);
            p.add(jlab2);
            p.add(jcb);
            p.add(jlab3);
            p.add(jcb1);
            p.add(jbt);
            p.add(jbt1);

            newFrame.getContentPane().add(p);
            newFrame.setVisible(true);
        }
        
        private void addclienttoDB(String name, String age, String speciality, String club) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
                PreparedStatement stmt = con.prepareStatement("insert into client(name,Age,speciality,club) values(?,?,?,?)");
                stmt.setString(1, name);
                stmt.setString(2, age);
                stmt.setString(3, speciality);
                stmt.setString(4, club);
                stmt.executeUpdate();
                con.close();
            } catch ( SQLException e) {
                System.out.println(e);
            
        } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
}
}