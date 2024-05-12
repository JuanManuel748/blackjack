package blackjackPKG;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

    }
}


class myPa extends JPanel {
    // labels para mostrar las cartas
    private myLb cuprier;
    private myLb jugador;

    // hashmap con las cartas
    private HashMap<Integer, Image> cartas = new HashMap<>();

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


        cuprier = new myLb();
        cuprier.setBounds(50, 50, 850, 300);
        cuprier.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cuprier.setForeground(Color.WHITE);
        add(cuprier);

        jugador = new myLb();
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
            /*
            apuesta = Integer.parseInt(sc.showInputDialog("¿CUANTO QUIERES APUESTAR?"));
            if (apuesta > budget) {
                sc.showMessageDialog(null, "DINERO INSUFICIENTE");
            } else if (apuesta > 0) {
                cont = false;
            }
            */


            budget = budget - apuesta;

            cuprier.setText("CUPRIER: " + scoreCuprier );
            jugador.setText("JUGADOR: " +  scoreJugador + "    Apuesta: " + apuesta + "    DINERO: " + budget);


           // añadir las cartas

            jugador.addCarta(cartas.get(0));
            jugador.addCarta(cartas.get(1));

            cuprier.addCarta(cartas.get(2));

            jugador.repaint();
            cuprier.repaint();

            cont = false;

            // fin del while
        }




    }




    class myLb extends JLabel {
        private ArrayList<ImageIcon> cartas = new ArrayList<>();

        public myLb() {
        }

        public void addCarta(Image img) {
            ImageIcon icon = new ImageIcon(img);
            cartas.add(icon);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int x = 0;
            for (ImageIcon icon : cartas) {
                g.drawImage(icon.getImage(), x, 0, this);
                x += icon.getIconWidth();
            }
        }
    }

    public void darCarta(myLb label, int carta) {
        Image img = cartas.get(carta);
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
                    puntuacion = j + 1;
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
                    cartas.put(x, img);
                } catch (IOException e) {
                    e.printStackTrace();
                }



                x++;
            }
        }
        System.out.println("Cartas: " + y);


    }

}