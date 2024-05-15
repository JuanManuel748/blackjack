package blackjackPKG;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import static blackjackPKG.myPa.*;

public class displayLb extends JLabel {
    private String text;
    private int x = 0;

    public displayLb(String text) {
        this.text = text;
        this.setVerticalAlignment(JLabel.TOP);
        this.setHorizontalAlignment(JLabel.LEFT);
        if(text.equalsIgnoreCase("budget")){
            this.x = budget;
        } else if (text.equalsIgnoreCase("bet")) {
            this.x=bet;
        }

    }

    public void setValue(int x){
        this.x = x;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont( new Font("Arial", Font.BOLD, 20));
        g.drawString(this.text + ": ", 20, 20);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString(String.valueOf(x), 50, 100);
    }

}
