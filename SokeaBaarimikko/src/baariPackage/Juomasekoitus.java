package baariPackage;

import java.util.ArrayList;

public class Juomasekoitus {
	/**
	 * Juomasekoitus-listaan tallennetaan juoma-olioita.
	 */
	ArrayList<Juoma> juomasekoitus = new ArrayList<Juoma>();
	/**
	 * Konstruktori, joka vain luo olion
	 */
	Juomasekoitus(){
	}
	/**
	 * Metodi lisaa Juoma-olion juomasekoitus-listaan.
	 * @param lisattava Juoma-olio
	 */
	void lisaaJuomasekoitukseen(Juoma juoma) {
		juomasekoitus.add(juoma);
	}
	/**
	 * Metodi palauttaa juomasekoitus-listan.
	 * @return
	 */
	ArrayList<Juoma> annaJuomasekoitus() {
		return juomasekoitus;
	}
	/**
	 * Metodi palauttaa tilavuuden eli juomasekoituksessa olevien juomien maaran.
	 * @return
	 */
	int annaTilavuus() {
		return juomasekoitus.size();
	}

	/**
	 * Metodi palauttaa juomasekoitus-listan sisaltaman yhteismyrkyllisyyden
	 * @return
	 */
	int annaSekoituksenMyrkyllisyys() {
		int myrkyllisyys = 0;
		
		for(Juoma juoma : juomasekoitus) {
			myrkyllisyys += juoma.annaMyrkyllisyys();
		}
		return myrkyllisyys;
	}
	/**
	 * Metodi palauttaa juomasekoitus-listan sisaltaman yhteishyvyyden
	 * @return
	 */
	int annaSekoituksenHyvyys() {
		int hyvyys = 0;
		
		for(Juoma juoma : juomasekoitus) {
			hyvyys += juoma.annaHyvyys();
		}
		return hyvyys;
	}
}
