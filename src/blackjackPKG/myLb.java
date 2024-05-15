package blackjackPKG;

import javax.swing.*;

import java.awt.*;
import java.util.*;

import static blackjackPKG.myPa.*;

public class myLb extends JLabel {
    private final ArrayList<ImageIcon> cartasTemp = new ArrayList<>();
    private final String text;
    private int x = 0;

    public myLb(String text) {
        this.text = text;
        this.setVerticalAlignment(JLabel.TOP);
        this.setHorizontalAlignment(JLabel.LEFT);
    }

    public void addCard(int x){
        ImageIcon card = new ImageIcon(cartas.get(x).getImg());
        cartasTemp.add(card);
        if (text.equalsIgnoreCase("CUPRIER")) {
            scoreC = scoreC + cartas.get(x).getPuntuacion();
            this.x = scoreC;
        } else if (text.equalsIgnoreCase("PLAYER")) {
            scoreP = scoreP + cartas.get(x).getPuntuacion();
            this.x = scoreP;
        }
        cartas.remove(x);
        repaint();
    }

    public void clear(){
        cartasTemp.clear();
        this.x= 0;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont( new Font("Arial", Font.BOLD, 20));
        g.drawString(this.text + ": " + x, 20, 20);
        int x = 20;

        for (ImageIcon icon : cartasTemp) {
            g.drawImage(icon.getImage(), x, 50, this);
            x += icon.getIconWidth() + 20;
        }
    }

}
