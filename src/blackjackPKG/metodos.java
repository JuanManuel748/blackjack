package blackjackPKG;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

import static blackjackPKG.myPa.*;


public class metodos {
    public static void conectarBD() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blackJack", "root", "");
            stmt = con.createStatement();
        } catch (SQLException SQLe){
            System.out.println("ERROR TRYING TO CONNECT TO DATABASE");
        }
    }

    public static void log_in(){
        budget = 50;
        name = JOptionPane.showInputDialog(null, "Introduce tu nombre");
        try {
            if (name.equalsIgnoreCase("") || name.equalsIgnoreCase("player") || name.equalsIgnoreCase("jugador")) {
                name = "PLAYER";
            } else {
                sqlInstruction = "SELECT * FROM jugadores WHERE nombre = '" + name + "'";
                rs = stmt.executeQuery(sqlInstruction);
                int exists = 0;
                while (rs.next()) {
                    exists++;
                    budget = rs.getInt("dinero");
                    if (budget <= 0){
                        JOptionPane.showMessageDialog(null, "No tienes dinero, intentalo de nuevo con otro nombre");
                        System.exit(0);
                    }
                }
                if (exists == 0) {
                    sqlInstruction = "INSERT INTO jugadores (nombre, dinero) VALUES ('" + name + "', 50)";
                    stmt.executeUpdate(sqlInstruction);
                }

            }

            System.out.println("Usuario " + name + " con " + budget + " euros");

        } catch (SQLException SQLe){
            System.out.println("ERROR TRYING TO CONNECT TO DATABASE, log_in");
        }
        
    }

    public static void barajar() {
        int x = 0;
        for (int i = 0; i < 4; i++) {
            String palo = "";
            String extension = "";

            switch (i) {
                case 0:
                    palo = "_corazones";
                    extension = ".jpg";
                    break;
                case 1:
                    palo = "_diamantes";
                    extension = ".png";
                    break;
                case 2:
                    palo = "_pica";
                    extension = ".jpg";
                    break;
                case 3:
                    palo = "_treboles";
                    extension = ".png";
                    break;
            }

            for (int j = 0; j < 13; j++) {
                String path;

                int puntuacion;
                if (j == 0) {
                    path = "cartas/A" + palo + extension;
                    puntuacion = 11;
                } else if (j < 10) {
                    path = "cartas/" + (j + 1) + palo + extension;
                    puntuacion = j + 1;
                } else if (j == 10) {
                    path = "cartas/J" + palo + extension;
                    puntuacion = 10;
                } else if (j == 11) {
                    path = "cartas/Q" + palo + extension;
                    puntuacion = 10;
                } else {
                    path = "cartas/K" + palo + extension;
                    puntuacion = 10;
                }


                try {
                    Image img = ImageIO.read(Objects.requireNonNull(metodos.class.getResource(path)));
                    cartas.put(x, new card(puntuacion, img));

                    if (cartas.get(0).getImg() == null) {
                        System.out.println("Error");
                    }

                } catch (IOException e) {
                    System.out.println("ERROR in loading images");
                }


                x++;
            }
        }

    }

    public static void jugar(){
        cont = true;
        barajar();
        scoreP = 0;
        scoreC = 0;

        if (budget <= 0) {
            JOptionPane.showMessageDialog(null, "No te quedan dineros, te vamos a borrar la cuenta\nTe recomendamos dejar de jugar");
            sqlInstruction = "DELETE FROM jugadores WHERE nombre = '" + name + "'";
            try {
                stmt.executeUpdate(sqlInstruction);
            } catch (SQLException SQLe) {
                System.out.println("ERROR TRYING TO DELETE FROM DATABASE");
            }
            System.exit(0);
        }

        player.clear();
        cuprier.clear();

        budgetLb.setValue(budget);
        betLb.setValue(bet);

        while(true){
            try {
                bet = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduce tu apuesta: "));
                break;
            } catch (NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, "Introduce un valor numerico");
            }
        }


        if (bet > budget) {
            JOptionPane.showMessageDialog(null, "No tienes tanto dinero para apostar, prueba otra cantidad");
            sqlInstruction = "UPDATE Jugadores SET dinero = "+budget+" WHERE nombre = '" + name + "'";
            try {
                stmt.executeUpdate(sqlInstruction);
            } catch (SQLException SQLe) {
                System.out.println("ERROR TRYING TO UPDATE DATABASE");
            }
            System.exit(0);
        }



        budget = budget - bet;

        betLb.setValue(bet);
        budgetLb.setValue(budget);

        int random = (int) (Math.random()*52);

        player.addCard(random);

        while (true) {
            try {
                random = (int) (Math.random() * 52);
                cuprier.addCard(random);
                break;
            }catch (Exception ex) {
                System.out.println("Carta repetida, reasignando");
            }
        }

        while(true){
            try{
                random = (int) (Math.random() * 52);
                player.addCard(random);
                break;
            } catch (Exception ex){
                System.out.println("Carta repetida, reasignando");
            }
        }






    }



    public static void lose(){
        JOptionPane.showMessageDialog(null, "PERDISTE");

        cont = false;
    }

    public static void win(){
        JOptionPane.showMessageDialog(null, "GANASTE");
        cont = false;
        budget += bet * 2;
        budgetLb.setValue(budget);
    }

    public static void draw() {
        JOptionPane.showMessageDialog(null, "EMPATE");
        budget += bet;
        cont = false;
        budgetLb.setValue(budget);
    }


}
