package blackjackPKG;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;

public class myPa extends JPanel {
    protected static myLb cuprier, player;
    protected static rankLb ranking;

    protected static String name = "PLAYER";

    protected static displayLb betLb, budgetLb;

    protected static HashMap <Integer, card> cartas = new HashMap<>();

    protected static int scoreC = 0, scoreP = 0;

    protected static int bet = 0;
    protected static int budget = 0;

    protected static boolean cont = false;

    protected static Connection con;
    protected static Statement stmt;
    protected static ResultSet rs;
    protected static String sqlInstruction;


    public myPa () {
        setLayout(null);
        setBackground(new Color(0, 128, 0));

        cuprier = new myLb("CUPRIER");
        cuprier.setBounds(50, 50, 1000, 300);
        cuprier.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cuprier.setForeground(Color.WHITE);
        add(cuprier);


        player = new myLb(name);
        player.setBounds(50, 400, 1000, 300);
        // hacer que tenga un borde negro
        player.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        player.setForeground(Color.WHITE);
        add(player);


        ranking = new rankLb("RANKING");
        //En el medio de la pantalla a la derecha
        ranking.setBounds( 1100, 225, 350, 300);
        ranking.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(ranking);

        betLb = new displayLb("BET");
        betLb.setBounds( 1100, 50, 350, 150);
        betLb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(betLb);

        budgetLb = new displayLb("BUDGET");
        budgetLb.setBounds( 1100, 550, 350, 150);
        budgetLb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        budgetLb.setBackground(new Color(0, 128, 0));
        add(budgetLb);


        myAc pedirAction = new myAc("PEDIR");
        myAc plantarAction = new myAc("PLANTAR");
        myAc doblarAction = new myAc("DOBLAR");
        myAc repetirAction = new myAc("SEGUIR");
        myAc guardarAction = new myAc("GUARDAR");

        JButton pedirBTN = new JButton(pedirAction);
        JButton plantarBTN = new JButton(plantarAction);
        JButton doblarBTN = new JButton(doblarAction);
        JButton repetirBTN = new JButton(repetirAction);
        JButton guardarBTN = new JButton(guardarAction);

        pedirBTN.setBounds(100, 600, 100, 50);
        plantarBTN.setBounds(250, 600, 100, 50);
        doblarBTN.setBounds(400, 600, 100, 50);
        repetirBTN.setBounds(550, 600, 100, 50);
        guardarBTN.setBounds(700, 600, 100, 50);

        add(pedirBTN);
        add(plantarBTN);
        add(doblarBTN);
        add(repetirBTN);
        add(guardarBTN);


        // HACER METODO QUE LE PASE POR PARAMETROS EL CONNECTION Y EL STATEMENT

        metodos.conectarBD();

        // HACER METODO QUE SE LE PASO POR PARAMETROS EL NAME,

        metodos.log_in();


        metodos.jugar();
    }
    
}
