package baariPackage;

import java.util.ArrayList;
import java.util.Scanner;
/* 
 * Sokea baarimikko -niminen peli. Pelin idea on kerata mahdollisimman monta kolikkoa valmistamalla juomasekoituksia.
 * Ohjelma tulostaa tuloslistan ja sen tiedostoon. Ohjelmassa on 11 luokkaa. Baari-luokka on ohjelman paaluokka.
 * 
 */
public class Baari {

	public static void main(String[] args) {
		//Kysytaan pelaajalta nimi ja tallennetaan se muuttujaan nimi
		System.out.println("Tervetuloa pelaamaan Sokeaa baarimikkoa!");
		Scanner skanneri = new Scanner(System.in);
		System.out.print("Kirjoita nimesi: ");
		System.out.println("");
		String nimi = skanneri.nextLine();
		
		/*
		 * Luodaan juomahylly-olio, jossa on 10 eri juomaa
		 * iltojenMaara-muuttuja kertoo yritysten maaran
		 * iltojenMaara-muuttujan avulla maaritetaan pelaajalla olevat yritykset
		 * Luodaan asiakasoliolista
		 * Luodaan baarimikko-olio
		 */
		Juomahylly juomahylly = new Juomahylly(10);
		int iltojenMaara = 0;
		ArrayList<Ihminen> asiakkaat = new ArrayList<Ihminen>();
		Baarimikko baarimikko = new Baarimikko(nimi);
		System.out.println("Tervehdys "+nimi+"! Tehtavanasi on ansaita mahdollisimman monta kolikkoa valmistamalla juomia.");
		System.out.println("Hyvista juomista asiakasmaarasi seka tienestisi kasvavat ja huonoista ne laskevat.");
		System.out.println("Sinulla on yhteensa viisi mahdollisuutta.");
		
		/*
		 * While-loopin avulla pelaajalla on 5 mahdollisuutta
		 * Ohjelma tulostaa "En voi hyvin", jos baarimikon Health-muuttuja on liian alhainen 
		 * Luo asiakaslistan asiakkaidenLuominen-metodilla
		 */
		while(iltojenMaara<5) {
			System.out.println(" ");
			baarimikko.asetaIllanTienestit(0.0);
			if(baarimikko.annaHealth()<50) {
				System.out.println("En voi hyvin...");
				System.out.println(" ");
			}
			iltojenMaara++;
			asiakkaat = asiakkaidenLuominen(baarimikko);
			/*
			 * Pelaaja valmistaa juoman juomanValmistus-metodilla
			 */
			Juomasekoitus juomasekoitus;
			juomasekoitus = juomanValmistus(juomahylly);
			/*
			 * Pelaaja saa mahdollisuuden maistaa juomaa maistaminen-metodissa. Maistaminen metodi palauttaa baarimikon health-muuttujan.
			 * Saadun health-muuttujan avulla baarimikko-oliosta vahennetaan vahennaHealth-metodilla baarimikon health-muuttujaa.
			 * Jos baarimikon health-muuttuja on alle 0, peli paattyy pelaajan kuolemaan. Pelaaja ei saa pisteita ja tulostaulu tulostetaan.
			 */
			baarimikko.vahennaHealth(maistaminen(baarimikko, juomasekoitus));
			if(baarimikko.annaHealth()<=0) {
				System.out.println("******************************************************************************");
				System.out.println("Kuolit.");
		        System.out.println(" ");
		        Tulostaulu tulostaulu = new Tulostaulu();
		        System.out.println(" ");
		        System.out.println("TULOSTAULU");
		        System.out.println(tulostaulu.annaTulosmerkkijono());
				System.exit(0);
			}
			/*
			 * Pelaaja voi tarjoilla juotavan juomanTarjoileminen-metodilla. Metodi palauttaa baarimikko-olion, joka tallennetaan baarimikko-nimiseksi olioksi.
			 */
			baarimikko = juomanTarjoileminen(baarimikko, juomasekoitus, asiakkaat);


		}
		/*
		 * 5 yrityksen jalkeen while-looppi paattyy. Peli paattyy ja pelaajan tulokset seka tuloslista tulostetaan. Tuloslista tallennetaan tiedostoon.
		 */
		skanneri.close();
		System.out.println("******************************************************************************");
		int raha = (int) Math.round((baarimikko.annaRaha()));
		
		if(raha>=0) {
			System.out.println("Peli loppui. Sait "+ raha +" kolikkoa "+iltojenMaara+" illassa!");
		}
		else{
			System.out.println("Peli loppui. Menetit "+ Math.abs(raha) +" kolikkoa "+iltojenMaara+" illassa!");
		}

		Tulostaulu tulostaulu = new Tulostaulu();
        tulostaulu.lisaaTulos(nimi,raha);
        System.out.println(" ");
        System.out.println("TULOSTAULU");
        System.out.println(tulostaulu.annaTulosmerkkijono());

	}
	/**
	 * Metodilla pelaaja saa mahdollisuuden tarjoilla juotavaa. Juoman tarjoileminen muuttaa asiakkaiden health-muuttujien arvoja. 
	 * Pelaajalle annetaan suosio-pisteita seka annetaan tai otetaan kolikoita. Metodi tulostaa illan tienestit.
	 * @param baarimikko1 pelaaja
	 * @param juomasekoitus1 pelaajan tekema juomasekoitus
	 * @param asiakkaat1 baarin asiakkaat listana
	 * @return metodi palauttaa baarimikko-olion
	 */
	private static Baarimikko juomanTarjoileminen(Baarimikko baarimikko1, Juomasekoitus juomasekoitus1, ArrayList<Ihminen> asiakkaat1) {
		Baarimikko baarimikko = baarimikko1;
		Juomasekoitus juomasekoitus = juomasekoitus1;
		ArrayList<Ihminen> asiakkaat = asiakkaat1;

		while(true) {
			Scanner skanneri2 = new Scanner(System.in);
			System.out.println(" ");
			System.out.print("Haluatko tarjoilla juotavaa? (K/E): ");
			System.out.println("");	
			String vastaus = skanneri2.next();
				
				
			if(vastaus.equals("K") || vastaus.equals("k")) {
				int hyvyys = juomasekoitus.annaSekoituksenHyvyys();
				int myrkyllisyys = juomasekoitus.annaSekoituksenMyrkyllisyys();
				asiakkaat.get(0).vahennaHealth(4*myrkyllisyys);
				if(asiakkaat.get(0).annaHealth()<1) {
					System.out.println("Asiakkaat kuolivat.");
					baarimikko.lisaaSuosio(-20*asiakkaat.size());
					int apu = hyvyys*asiakkaat.size();
					if(apu>0) {
						
					}
					baarimikko.asetaIllanTienestit(0.5*hyvyys*asiakkaat.size()-20*asiakkaat.size());
					System.out.println(" ");
					System.out.println("Illan tienestit: " + (int) Math.round(baarimikko.annaIllanTienestit()) + " kolikkoa");
					baarimikko.lisaaRaha(baarimikko.annaIllanTienestit());					
					break;
				}
				else{
					if(hyvyys>40) {
						baarimikko.lisaaSuosio(hyvyys); 
					}

					baarimikko.asetaIllanTienestit(0.5*hyvyys*asiakkaat.size());
					System.out.println(" ");
					System.out.println("Illan tienestit: " + (int) Math.round(baarimikko.annaIllanTienestit()) + " kolikkoa");
					baarimikko.lisaaRaha(baarimikko.annaIllanTienestit());
					break;
				}
			}
			if(vastaus.equals("E") || vastaus.equals("e")) {
				break;
			}
			else {
				System.out.println("Anna vastaus muodossa: kylla = K, ei = E!");
			}
			
		}
		return baarimikko;
	}
	/**
	 * Metodin avulla pelaaja saa mahdollisuuden maistaa juomaansa ja tietaa sen hyvyyden. Ohjelma antaa karkean arvion juomasta.
	 * Metodi muuttaa baarimikon health-muuttujaa juoman myrkyllisyyden mukaan.
	 * @param baarimikko Pelaaja
	 * @param juomasekoitus Pelaajan valmistama juomasekoitus
	 * @return Palauttaa baarimikon eli pelaajan health-muuttujan
	 */
	private static int maistaminen(Baarimikko baarimikko, Juomasekoitus juomasekoitus) {
		while(true) {
			Scanner skanneri2 = new Scanner(System.in);
			System.out.println(" ");
			System.out.print("Haluatko maistaa juotavaa? (K/E): ");
			System.out.println("");	
			String vastaus = skanneri2.next();
			
			if(vastaus.equals("K") || vastaus.equals("k")) {
				int hyvyys = juomasekoitus.annaSekoituksenHyvyys();
				if(hyvyys<=20) {
					System.out.println("Hyi helvetti...");
				}
				if(hyvyys>20 && hyvyys<50) {
					System.out.println("Ihan jees...");
				}
				if(hyvyys>=50) {
					System.out.println("Namskista...");
				}
				baarimikko.vahennaHealth(2*juomasekoitus.annaSekoituksenMyrkyllisyys());
				break;
			}
			if(vastaus.equals("E") || vastaus.equals("e")) {
				break;
			}
			else {
				System.out.println("Anna vastaus muodossa: kylla = K, ei = E!");
			}
		}
		return baarimikko.annaHealth();
	}
	/**
	 * Ohjelma pyytaa pelaajaa valitsemaan hyllysta olevan juoma-olion ja juoma-olion maaran Juomasekoituksen sisaltamassa listassa. Ohjelma ei hyvaksy arvoja, jotka eivat ole kokonaislukuarvoja
	 * valilta 0-10.
	 * @param juomahylly Ohjelma ottaa vastaan juomahylly-olion, johon on tallennettu juoma-olioita sisaltava lista.
	 * @return Ohjelma tekee tulos-nimisen Juomasekoitus-olion, johon tallennetaan pelaajan valitsemat juotavat. Tulos palautetaan.
	 */
	private static Juomasekoitus juomanValmistus(Juomahylly juomahylly) {
		Juomasekoitus tulos = new Juomasekoitus();
		int juomavalinta;
		int juomanMaara;
		System.out.println("Valmista juomasekoitusta yli 10 yksikkoa!");
		while(tulos.annaTilavuus() <= 10) {
			Scanner skanneri2 = new Scanner(System.in);
			System.out.print("Valitse hyllysta juotava (1-10): ");
			System.out.println("");	
			try{
				juomavalinta = skanneri2.nextInt();
			}
			catch(Exception e) {
				System.out.println("Anna kokonaisluku valilta 0-10!");
				skanneri2.nextLine();
				continue;
			}
			System.out.print("Anna juoman maara (1-10): ");
			System.out.println("");
			try {
				juomanMaara = skanneri2.nextInt();
				if(juomanMaara>10) {
					System.out.println("Juomaa ei voi lisata yli 10 yksikkoa!");
					continue;
				}
			}
			catch(Exception e) {
				System.out.println("Anna kokonaisluku valilta 1-10!");
				skanneri2.nextLine();
				continue;
			}
			
			for(int i=0; i<juomanMaara; i++) {
				Juoma juoma;
				try{
					juoma = juomahylly.annaJuoma(juomavalinta-1);
				}
				catch(VaaraArvoPoikkeus e){
					System.out.println(e.getMessage());
					break;
				}
				catch(Exception E) {
					System.out.println("Anna arvo valilta 1-10");
					break;
				}
				tulos.lisaaJuomasekoitukseen(juoma);
			}
		}
		return tulos;
	}
	/**
	 * 
	 * @param baarimikko Ottaa vastaan baarimikko-olion, hakee baarimikon suosion ja 
	 * suosion avulla maarittaa asiakkaiden maaran 
	 * @return palauttaa asiakaslistan
	 */
	private static ArrayList<Ihminen> asiakkaidenLuominen(Baarimikko baarimikko) {
		int suosio = baarimikko.annaSuosio();
		int asiakasmaara;
		ArrayList<Ihminen> tulos = new ArrayList<Ihminen>();
		
		if(suosio<=1) {
			asiakasmaara = 1;
		}
		else {
			asiakasmaara = suosio;
		}
		
		
		for(int i=0; i<asiakasmaara; i++) {
			Asiakas a = new Asiakas();
			tulos.add(a);
		}
		
		return tulos;
	}
	
}
