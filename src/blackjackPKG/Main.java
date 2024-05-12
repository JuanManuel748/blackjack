package blackjackPKG;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) {
        System.out.println("BLACK JACK PARA BLANQUEAR DINERO");
        JFrame frame = new JFrame();
        frame.setSize(1000, 800);
        frame.setTitle("BLACK JACK");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        myPa panel = new myPa();
        frame.add(panel);
        frame.setVisible(true);

        Scanner s = new Scanner(System.in);
        System.out.println("Do you want to continue? (y/n)");
        String answer = s.nextLine();
        if (answer.equals("y")) {
            frame = new JFrame();
            frame.setSize(1000, 800);
            frame.setTitle("BLACK JACK");
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            panel = new myPa();
            frame.add(panel);
            frame.setVisible(true);
        }

    }
}


class myPa extends JPanel {
    // labels para mostrar las cartas
    private myLb cuprier;
    private myLb jugador;

    // hashmap con las cartas
    private HashMap<Integer, card> cartas = new HashMap<>();

    // score
    private int scoreCuprier = 0;
    private int scoreJugador = 0;
    private final int maxScore = 21;

    // dinero
    private int budget = 1000;
    private int apuesta = 0;

    // controllers
    private JOptionPane sc;
    private boolean cont = true;

    public myPa() {
        sc = new JOptionPane();

        setLayout(null);
        setBackground(new Color(0, 128, 0));


        // iniciar las cartas
        barajar();


        cuprier = new myLb("CUPRIER");
        cuprier.setBounds(50, 50, 850, 300);
        cuprier.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cuprier.setForeground(Color.WHITE);
        add(cuprier);

        jugador = new myLb("JUGADOR");
        jugador.setBounds(50, 400, 850, 300);
        // hacer que tenga un borde negro
        jugador.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jugador.setForeground(Color.WHITE);
        add(jugador);

        // hacer que el texto se escriba arriba a la izquierda
        cuprier.setVerticalAlignment(JLabel.TOP);
        cuprier.setHorizontalAlignment(JLabel.LEFT);
        jugador.setVerticalAlignment(JLabel.TOP);
        jugador.setHorizontalAlignment(JLabel.LEFT);




        // EJEMPLO DE DAR CARTAS




        while (cont) {
            apuesta = 50;
            apuesta = Integer.parseInt(sc.showInputDialog("¿CUANTO QUIERES APUESTAR?" + "    DINERO: " + budget));
            if (apuesta > budget) {
                sc.showMessageDialog(null, "DINERO INSUFICIENTE");
            }
            budget = budget - apuesta;
            if (budget < 0) {
                sc.showMessageDialog(null, "DINERO INSUFICIENTE, Fin del juego");
                System.exit(0);
            }



            int card1 = (int) (Math.random() * 52);
            int card2 = (int) (Math.random() * 52);
            int card3 = (int) (Math.random() * 52);

           // añadir las cartas
            jugador.addCarta(cartas.get(card1).getImg());
            jugador.addCarta(cartas.get(card2).getImg());
            cuprier.addCarta(cartas.get(card3).getImg());


            scoreJugador = cartas.get(card1).getPuntuacion() + cartas.get(card2).getPuntuacion();
            scoreCuprier = cartas.get(card3).getPuntuacion();

            cartas.remove(card1);
            cartas.remove(card2);
            cartas.remove(card3);
            // mostrar las cartas
            cuprier.setText("CUPRIER: " + scoreCuprier );
            jugador.setText("JUGADOR: " +  scoreJugador + "    Apuesta: " + apuesta + "    DINERO: " + budget);
            jugador.repaint();
            cuprier.repaint();

            pedirAc pedirAction = new pedirAc("PEDIR");
            plantarAc plantarAction = new plantarAc("PLANTAR");


            JButton pedirBTN = new JButton(pedirAction);
            JButton plantarBTN = new JButton(plantarAction);
            pedirBTN.setBounds(100, 600, 100, 50);
            plantarBTN.setBounds(250, 600, 100, 50);
            add(pedirBTN);
            add(plantarBTN);




            cont = false;
            // fin del while
        }




    }


    class pedirAc extends AbstractAction {

        public pedirAc(String text) {
            putValue(Action.NAME, text);

        }
        @Override
        public void actionPerformed(ActionEvent e) {
            int card = (int) (Math.random() * 52);
            jugador.addCarta(cartas.get(card).getImg());
            scoreJugador = scoreJugador + cartas.get(card).getPuntuacion();
            jugador.setText("JUGADOR: " +  scoreJugador + "    Apuesta: " + apuesta + "    DINERO: " + budget);
            jugador.repaint();
            if (scoreJugador > maxScore) {
                sc.showMessageDialog(null, "PERDISTE");
            }


        }
    }

    class plantarAc extends AbstractAction {

        public plantarAc(String text) {
            putValue(Action.NAME, text);

        }
        @Override
        public void actionPerformed(ActionEvent e) {

            while (scoreCuprier < scoreJugador) {
                int card = (int) (Math.random() * 52);
                cuprier.addCarta(cartas.get(card).getImg());
                scoreCuprier = scoreCuprier + cartas.get(card).getPuntuacion();
                cuprier.setText("CUPRIER: " + scoreCuprier);
                cuprier.repaint();
                if (scoreCuprier > maxScore) {
                    sc.showMessageDialog(null, "GANASTE");
                }
                if (scoreCuprier == scoreJugador) {
                    sc.showMessageDialog(null, "PERDISTE");
                }
            }




        }
    }



    class myLb extends JLabel {
        private ArrayList<ImageIcon> cartasTemp = new ArrayList<>();
        private String name;
        public myLb(String name) {
            this.name = name;
        }

        public void addCarta(Image img) {
            ImageIcon icon = new ImageIcon(img);
            cartasTemp.add(icon);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int x = 20;

            for (ImageIcon icon : cartasTemp) {
                g.drawImage(icon.getImage(), x, 50, this);
                x += icon.getIconWidth() + 20;
            }
        }
    }

    public void darCarta(myLb label, int carta) {
        Image img = cartas.get(carta).getImg();
        label.addCarta(img);
        label.repaint();
    }


    public void barajar() {
        int x = 0;
        int y = 0;
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
                String path = "";

                int puntuacion = 0;
                if (j == 0) {
                    path = "cartas/A" + palo + extension;
                    puntuacion = 11;
                } else if (j > 0 && j < 10) {
                    path = "cartas/" + String.valueOf(j+1) + palo + extension;
                    puntuacion = j +1;
                } else if (j == 10) {
                    path = "cartas/J" + palo + extension;
                    puntuacion = 10;
                } else if (j == 11) {
                    path = "cartas/Q" + palo + extension;
                    puntuacion = 10;
                } else if (j == 12) {
                    path = "cartas/K" + palo + extension;
                    puntuacion = 10;
                }


                try {
                    Image img = ImageIO.read(getClass().getResource(path));
                    cartas.put(x, new card(puntuacion, img));

                    if (cartas.get(0).getImg() == null) {
                        System.out.println("Error");
                    } else {
                        y++;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }



                x++;
            }
        }
        System.out.println("Cartas: " + y);


    }

}