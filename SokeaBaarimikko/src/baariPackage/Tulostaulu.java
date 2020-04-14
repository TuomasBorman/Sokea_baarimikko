package baariPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Luokan olio tallentaa tuloksen tiedostoon
 * @author tuoma
 *
 */
public class Tulostaulu {

	//Tulokset-niminen Tulos-olioita sisltava lista
    private ArrayList<Tulos> tulokset;

    // tulostaulu-tiedoston nimi
    private static final String tulostaulu_tiedosto = "tulokset.dat";

    //Alustetaan inputStream ja outputStream
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;
    /**
     * Konstruktorissa alustetaan tulokset-lista
     */
    public Tulostaulu() {
        tulokset = new ArrayList<Tulos>();
    }
    /**
     * Metodilla lisataan tulos tulokset-listaan ja tallennetaan se tiedostoon.
     * @param nimi
     * @param tulos
     */
    public void lisaaTulos(String nimi, int tulos) {
        lataaTiedosto();
        tulokset.add(new Tulos(nimi, tulos));
        paivitaTiedosto();
    }
    /**
     * Metodi palauttaa jarjestetyn tuloksia sisaltavan listan, joka on haettu tiedostosta
     * @return
     */
    public ArrayList<Tulos> annaTulokset() {
        lataaTiedosto();
        sort();
        return tulokset;
    }
    /**
     * Metodilla jarjestetaan tulosvertailija-olion avulla tulokset
     */
    private void sort() {
    	Tulosvertailija tulosvertailija = new Tulosvertailija();
        Collections.sort(tulokset, tulosvertailija);
    }
    
    /**
     * Metodin avulla ladataan tuloksia sisaltava tiedosto. Tiedoston tiedot tallennetan tulokset-listaan.
     */
    public void lataaTiedosto() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(tulostaulu_tiedosto));
            tulokset = (ArrayList<Tulos>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("[Laad] FNF Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[Laad] IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("[Laad] CNF Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Laad] IO Error: " + e.getMessage());
            }
        }
    }
    
    /**
     * Metodin avulla tiedostoon ladataan tuloksia sisaltava lista
     */
    public void paivitaTiedosto() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(tulostaulu_tiedosto));
            outputStream.writeObject(tulokset);
        } catch (FileNotFoundException e) {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("[Update] IO Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }
    }
    
    /**
     * Metodi palauttaa tulos-listan merkkijonona
     * @return
     */
    public String annaTulosmerkkijono() {
        String tulosmerkkijono = "";
	int max = 10;

ArrayList<Tulos> tulokset;
        tulokset = annaTulokset();

        int i = 0;
        int x = tulokset.size();
        if (x > max) {
            x = max;
        }
        while (i < x) {
            tulosmerkkijono += (i + 1) + ".\t" + tulokset.get(i).annaNimi() + "\t\t" + tulokset.get(i).annaTulos() + "\n";
            i++;
        }
        return tulosmerkkijono;
    }
    
    
}