import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Resolution {
    public Resolution() {
        JFrame res = new JFrame();
        res.setSize(300, 500);

        JPanel buttons = new JPanel();
        buttons.setSize(300, 200);
        buttons.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        JButton b1 = new JButton("800x600");
        b1.setPreferredSize(new Dimension(200, 50));
        c.ipadx = 86;
        c.ipady = 20;
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = GridBagConstraints.REMAINDER;
        b1.addActionListener(e -> {
            res.dispose();
            UI u = new UI(1,800,600);
        });
        buttons.add(b1, c);

        JButton b2 = new JButton("1024x768");
        b2.setPreferredSize(new Dimension(200, 50));
        c.ipadx = 86;
        c.ipady = 20;
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = GridBagConstraints.REMAINDER;
        b2.addActionListener(e -> {
            res.dispose();
            UI u = new UI(1.6384,1024,768);
            System.out.println(u.getResprc());
        });
        buttons.add(b2, c);

        JButton b3 = new JButton("1280x960");
        b3.setPreferredSize(new Dimension(200, 50));
        c.ipadx = 86;
        c.ipady = 20;
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = GridBagConstraints.REMAINDER;
        b3.addActionListener(e -> {
            res.dispose();
            UI u = new UI(2.56,1280,960);
            u.setResprc(2.56);
        });
        buttons.add(b3, c);

        JButton b4 = new JButton("1400x1050");
        b4.setPreferredSize(new Dimension(200, 50));
        c.ipadx = 86;
        c.ipady = 20;
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = GridBagConstraints.REMAINDER;
        b4.addActionListener(e -> {
            res.dispose();
            UI u = new UI(3.0625,1400,1050);
            u.setResprc(3.0625);
        });
        buttons.add(b4, c);

        res.add(buttons, BorderLayout.SOUTH);
        res.setResizable(false);
        res.setLocationRelativeTo(null);
        res.setVisible(true);
        res.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
