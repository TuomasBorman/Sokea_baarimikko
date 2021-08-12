package baariPackage;

import java.util.Random;

public class Juomahylly {
	/*
	 * Juomahylly muuttujaan tallennetaan taulukko Juoma-olioista
	 */
	Juoma[] juomahylly;
	/**
	 * Konstruktori alustaa Juomahylly taulukon juomienMaara-muuttujan arvon maaramalla maralla Juoma-olioita.
	 * Juomien myrkyllisyys ja hyvyys arvotaan valilta -10 - 10.
	 * @param juomienMaara
	 */
	public Juomahylly(int juomienMaara) {
		juomahylly = new Juoma[juomienMaara];
		int satunnainenMyrkyllisyys;
		int satunnainenHyvyys;
		Random rand = new Random();
		for(int i=0; i<10; i++) {
			satunnainenMyrkyllisyys = rand.nextInt(10+10)-10; //range -10-10
			satunnainenHyvyys = rand.nextInt(10+10)-10; 
			
			juomahylly[i] = new Juoma(satunnainenMyrkyllisyys, satunnainenHyvyys);
		}	
	}
	/** 
	 * Metodi palauttaa kysytysta hyllyn paikasta juoman. Jos juomaa ei loydy kysytysta paikasta, metodi heittaa poikkeuksen.
	 * @param juomavalinta
	 * @return
	 * @throws VaaraArvoPoikkeus
	 */
	Juoma annaJuoma(int juomavalinta) throws VaaraArvoPoikkeus {
		if(juomavalinta>juomahylly.length-1) {
			VaaraArvoPoikkeus e = new VaaraArvoPoikkeus("There is no bottle there!");
			throw e;
		}
		else {
			return juomahylly[juomavalinta];
		}
		
	}
}

