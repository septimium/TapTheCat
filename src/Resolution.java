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

        JButton b1 = new JButton("1280x720 | HD");
        b1.setPreferredSize(new Dimension(200, 50));
        c.ipadx = 86;
        c.ipady = 20;
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = GridBagConstraints.REMAINDER;
        b1.addActionListener(e -> {
//            res.dispose();
            UI u = new UI(1.5,1280,720);
        });
        buttons.add(b1, c);


        JButton b2 = new JButton("1920x1080 | HD");
        b2.setPreferredSize(new Dimension(200, 50));
        c.ipadx = 86;
        c.ipady = 20;
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = GridBagConstraints.REMAINDER;
        b2.addActionListener(e -> {
//            res.dispose();
            UI u = new UI(2,1920,1080);
        });
        buttons.add(b2, c);

        res.add(buttons, BorderLayout.SOUTH);
        res.setResizable(false);
        res.setLocationRelativeTo(null);
        res.setVisible(true);
        res.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
