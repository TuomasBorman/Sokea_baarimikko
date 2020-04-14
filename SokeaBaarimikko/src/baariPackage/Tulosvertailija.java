package baariPackage;

import java.util.Comparator;
/**
 * Luokan olio vertailee saamiaan tuloksia keskenaan ja palauttaa niiden suuruusjarjestyksen
 * @author tuoma
 *
 */
public class Tulosvertailija implements Comparator<Tulos> {
   Tulosvertailija(){
	   
   }
	public int compare(Tulos tulos1, Tulos tulos2) {

        int sc1 = tulos1.annaTulos();
        int sc2 = tulos2.annaTulos();

        if (sc1 > sc2){
            return -1;
        }else if (sc1 < sc2){
            return +1;
        }else{
            return 0;
        }
    }
}