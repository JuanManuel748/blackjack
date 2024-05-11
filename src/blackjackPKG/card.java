package blackjackPKG;

import javax.swing.*;

public class card {

    public ImageIcon image;
    public int puntuacion;


    public card(String path, int puntuacion) {
        this.image = new ImageIcon(path);
        this.puntuacion = puntuacion;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
