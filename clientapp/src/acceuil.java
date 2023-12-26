import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class acceuil extends JFrame  {
    public acceuil() {
        JLabel t1 = new JLabel("Welcome,  choose your role");
        t1.setFont(new Font("https://kinsta.com/fr/blog/polices-caracteres-web-safe/#5-calisto-mt", Font.BOLD, 15));
        t1.setForeground(new Color(10));
        JButton b1 = new JButton("admin");
        JButton b2 = new JButton("student");
    
        
        JPanel p1 = new JPanel();
        p1.setBorder(BorderFactory.createLineBorder(new Color(10)));
		p1.setLayout(new FlowLayout());
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admin a = new admin();
                a.setVisible(true);
                
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client c = new Client();
                c.setVisible(true);
               
            }
        });
        p1.add(t1);
		
	    b2.setBackground(new Color(221, 207, 198));
		b1.setBackground(new Color(221, 207, 198));

		b1.setOpaque(true);
		b2.setOpaque(true);

		b1.setBorderPainted(false);
		b2.setBorderPainted(false);
		
        p1.add(b1);
        p1.add(b2);
		p1.setBackground(new Color(219, 183, 160));
        add(p1);


        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2 - getHeight() / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 200);
        setVisible(true);
        }
    }
    





    

