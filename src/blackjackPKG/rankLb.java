package blackjackPKG;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import static blackjackPKG.myPa.*;

public class rankLb extends JLabel {
    private String text;
    public rankLb(String text) {
        this.text = text;
        this.setVerticalAlignment(JLabel.TOP);
        this.setHorizontalAlignment(JLabel.LEFT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont( new Font("Arial", Font.BOLD, 20));
        g.drawString(this.text + ": ", 20, 20);
        try {
            sqlInstruction = "SELECT * FROM jugadores ORDER BY dinero DESC LIMIT 5";
            rs = stmt.executeQuery(sqlInstruction);
            int x = 20;
            int rank = 1;
            String ranking = "";
            while (rs.next()) {
                switch (rank) {
                    case 1:
                        g.setColor(new Color(255, 215, 0));
                        break;
                    case 2:
                        g.setColor(new Color(200, 200, 200));
                        break;
                    case 3:
                        g.setColor(new Color(205, 127, 50));
                        break;
                    default:
                        g.setColor(Color.BLACK);
                        break;
                }
                String nameVar = rs.getString("nombre");
                int budgetVar = rs.getInt("dinero");
                ranking = rank + ". " + nameVar + " " + budgetVar;
                g.drawString(ranking, (this.getWidth()/2 - 75), 50 * rank);

                rank++;
        }
    } catch (SQLException SQLe){
        System.out.println("ERROR TRYING TO CONNECT TO DATABASE, rankLb class");
        System.exit(0);
    } catch (Exception e){
        System.out.println("FATAL ERROR, rankLb class");
        System.exit(0);
    }
    }
}
