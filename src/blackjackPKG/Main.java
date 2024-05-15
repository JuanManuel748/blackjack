package blackjackPKG;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.nimbus.State;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.sql.*;

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



