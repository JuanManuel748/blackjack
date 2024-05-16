package blackjackPKG;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) {
        System.out.println("BLACK JACK PARA BLANQUEAR DINERO");
        JFrame frame = new JFrame();
        frame.setSize(1500, 800);
        frame.setTitle("BLACK JACK");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        myPa panel = new myPa();
        frame.add(panel);
        frame.setVisible(true);

    }
}



