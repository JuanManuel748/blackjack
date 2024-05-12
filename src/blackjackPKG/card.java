package blackjackPKG;

import java.awt.Image;

public class card {
    private int puntuacion;
    private Image img;

    public card(int puntuacion, Image img) {
        this.puntuacion = puntuacion;
        this.img = img;
    }

    public int getPuntuacion() {
        return this.puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
