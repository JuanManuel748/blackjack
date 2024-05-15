package blackjackPKG;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import static blackjackPKG.myPa.*;

public class myAc extends AbstractAction {
    private String text;

        public myAc(String text) {
            putValue(Action.NAME, text);
            this.text = text;
        }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (text){
            case "PEDIR":
                if (cont){
                    int random;
                    while (true) {
                        try {
                            random = (int) (Math.random() * 52);
                            player.addCard(random);
                            break;
                        } catch (Exception ex) {
                            System.out.println("Carta repetida, reasignando");
                        }
                    }

                    if (scoreP > 21){
                        metodos.lose();
                    }

                }
                break;
            case "DOBLAR":
                if (cont){
                    budget = budget - bet;
                    bet = bet * 2;

                    int random;
                    while (true) {
                        try {
                            random = (int) (Math.random() * 52);
                            player.addCard(random);
                            break;
                        } catch (Exception ex) {
                            System.out.println("Carta repetida, reasignando");
                        }
                    }

                    if (scoreP > 21){
                        metodos.lose();
                    }
                }
                break;
            case "PLANTAR":
                if (cont) {
                    int random = 0;
                    while (scoreC < scoreP) {
                        try {
                            random = (int) (Math.random() * 52);
                            cuprier.addCard(random);
                        } catch (Exception ex) {
                            System.out.println("Carta repetida, reasignando");
                        }
                    }

                    if (scoreC > 21){
                        metodos.win();
                    }
                    if (scoreC >= scoreP && scoreC <= 21){
                        metodos.lose();
                    } else if (scoreP > scoreC && scoreP <= 21){
                        metodos.win();
                    }
                }
                break;
            case "GUARDAR":
                if (!cont) {
                    try {
                        sqlInstruction = "UPDATE jugadores SET dinero = " + budget + " WHERE nombre = '" + name + "'";
                        stmt.executeUpdate(sqlInstruction);
                        System.out.println("Jugador  " + name + " guardado con " + budget + " euros");
                    } catch (SQLException SQLe){
                        System.out.println("ERROR TRYING TO CONNECT TO DATABASE");
                    }
                }
                break;
            case "SEGUIR":
                    if (!cont) {
                        metodos.jugar();
                    }
                break;
            default:
                JOptionPane.showMessageDialog(null, "FATAL ERROR");
                System.exit(0);
                break;
        }


    }
}

