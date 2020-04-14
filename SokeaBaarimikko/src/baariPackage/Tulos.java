package baariPackage;

import java.io.Serializable;
/**
 * Luokan olioon tallennetaan pelaajan nimi ja tulos
 * @author tuoma
 *
 */
public class Tulos  implements Serializable {
    private int tulos;
    private String nimi;

    public int annaTulos() {
        return tulos;
    }

    public String annaNimi() {
        return nimi;
    }

    public Tulos(String nimi, int tulos) {
        this.tulos = tulos;
        this.nimi = nimi;
    }
}