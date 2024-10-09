import javax.swing.*;
import java.awt.*;

public class UI {
    int score;
    double resprc;
    public UI(double r, int w, int h) {
        this.score = 0;
        this.resprc = r;
        JFrame ui = new JFrame();
        ui.setSize(w,h);
        ui.getContentPane().setBackground(Color.black);

        JLabel scor = new JLabel(String.valueOf(getScore()));
        scor.setBounds(851, 200, 200,200);
        scor.setBackground(Color.black);
        scor.setForeground(Color.white);
        scor.setFont(new Font("Arial", Font.BOLD, 70));
        ui.add(scor);

//        ImageIcon kittenimage = new ImageIcon(getClass().getClassLoader().getResource("kitten2.png"));
        JButton kitten = new JButton();
        kitten.setBounds(0, 200, 550,500);
        kitten.addActionListener(e -> {
            setScore(getScore() + 1);
            scor.setText(String.valueOf(getScore()));
        });
        kitten.setBorder(null);
//        kitten.setIcon(kittenimage);
        kitten.setFocusPainted(false);
        kitten.setBorderPainted(false);
        kitten.setBorder(null);
        kitten.setContentAreaFilled(false);
        kitten.setBackground(Color.BLACK);
        kitten.setSize(550, 500);
        ui.add(kitten);


        ui.setLayout(null);
        ui.setResizable(false);
        ui.setVisible(true);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getResprc() {
        return resprc;
    }

    public void setResprc(double resprc) {
        this.resprc = resprc;
    }
}
