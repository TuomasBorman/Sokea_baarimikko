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
		//System.out.println("Tervetuloa pelaamaan Sokeaa baarimikkoa!\nWelcome to play The Blind Bartender!");
		System.out.println("Welcome to play The Blind Bartender!");
		Scanner skanneri = new Scanner(System.in);
		//System.out.print("Kirjoita nimesi:");
		System.out.print("What is your name: ");
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
		//System.out.println("Tervehdys "+nimi+"! Tehtavanasi on ansaita mahdollisimman monta kolikkoa valmistamalla juomia.");
		//System.out.println("Hyvista juomista asiakasmaarasi seka tienestisi kasvavat ja huonoista ne laskevat.");
		//System.out.println("Sinulla on yhteensa viisi mahdollisuutta.");
		System.out.println("Howdy "+nimi+"! You've always wanted to be a bartender. Haven't you?\n"
				+ "Haters have said that you cannot be a bartender because you're blind. But that doesn't stop you!\n"
				+ "You've applied to open position and you got the job. \nProbably because your were the only job seeker \n"
				+ "and the bar owner didn't really check your application and doesn't know you're blind...\n"
				+ "... but anyway, congrats! "
				+ "Today is your first night in the bar.\n"
				+ "*************************************************************\n"
				+ "'Ok, "+nimi+" here is your working place. Behind you, you can find all our liquours.\n"
						+ "They're in that shelf in a row... not there, here. Are you blind? Just joking.\n"
						+ "So, just mix something and to somewhere and so on, you know these things...\n"
						+ "I have to go to my office. I have there some... administrative stuff, yes...\n"
						+ "And by the way, the cleaning lady is idiot and always mess our liquour shelf.\n"
						+ "I have found there rat poison bottles. So, don't fu*k up. \n"
						+ "Usually, you have to do 5 drinks per night, and this night is not exception.\n"
						+ "Good luck!'");
		
		/*
		 * While-loopin avulla pelaajalla on 5 mahdollisuutta
		 * Ohjelma tulostaa "En voi hyvin", jos baarimikon Health-muuttuja on liian alhainen 
		 * Luo asiakaslistan asiakkaidenLuominen-metodilla
		 */
		while(iltojenMaara<5) {
			System.out.println(" ");
			baarimikko.asetaIllanTienestit(0.0);
			if(baarimikko.annaHealth()<50) {
				System.out.println("I feel sick...");
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
				System.out.println("You died.");
		        System.out.println(" ");
		        Tulostaulu tulostaulu = new Tulostaulu();
		        System.out.println(" ");
		        System.out.println("SCORING BOARD");
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
		
		System.out.println("******************************************************************************");
		int raha = (int) Math.round((baarimikko.annaRaha()));
		
		if(raha>=0) {
			System.out.println("The End. You got "+ raha +" coins in "+iltojenMaara+" evenings!");
		}
		else{
			System.out.println("The End. You lost "+ Math.abs(raha) +" coins in "+iltojenMaara+" evenings!\n"
					+ "Bar owner will be mad...");
		}

		Tulostaulu tulostaulu = new Tulostaulu();
        tulostaulu.lisaaTulos(nimi,raha);
        System.out.println(" ");
        System.out.println("SCORING BOARD");
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
			System.out.print("Do you want to serve the drink? (Y/N): ");
			System.out.println("");	
			String vastaus = skanneri2.next();
				
				
			if(vastaus.equals("Y") || vastaus.equals("y")) {
				int hyvyys = juomasekoitus.annaSekoituksenHyvyys();
				int myrkyllisyys = juomasekoitus.annaSekoituksenMyrkyllisyys();
				asiakkaat.get(0).vahennaHealth(4*myrkyllisyys);
				if(asiakkaat.get(0).annaHealth()<1) {
					System.out.println("Your customers are dead.");
					baarimikko.lisaaSuosio(-20*asiakkaat.size());
					int apu = hyvyys*asiakkaat.size();
					if(apu>0) {
						
					}
					baarimikko.asetaIllanTienestit(0.5*hyvyys*asiakkaat.size()-20*asiakkaat.size());
					System.out.println(" ");
					System.out.println("That drink equals " + (int) Math.round(baarimikko.annaIllanTienestit()) + " coins");
					baarimikko.lisaaRaha(baarimikko.annaIllanTienestit());					
					break;
				}
				else{
					if(hyvyys>40) {
						baarimikko.lisaaSuosio(hyvyys); 
					}

					baarimikko.asetaIllanTienestit(0.5*hyvyys*asiakkaat.size());
					System.out.println(" ");
					System.out.println("That drink equals " + (int) Math.round(baarimikko.annaIllanTienestit()) + " coins");
					baarimikko.lisaaRaha(baarimikko.annaIllanTienestit());
					break;
				}
			}
			if(vastaus.equals("N") || vastaus.equals("n")) {
				break;
			}
			else {
				System.out.println("Give the answer in the format: yes = Y, no = N!");
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
		Scanner skanneri2 = new Scanner(System.in);
		while(true) {
			
			System.out.println(" ");
			System.out.print("Do you want to taste the drink? (Y/N): ");
			System.out.println("");	
			String vastaus = skanneri2.next();
			
			if(vastaus.equals("Y") || vastaus.equals("y")) {
				int hyvyys = juomasekoitus.annaSekoituksenHyvyys();
				if(hyvyys<=20) {
					System.out.println("This is shit...");
				}
				if(hyvyys>20 && hyvyys<50) {
					System.out.println("That's ok...");
				}
				if(hyvyys>=50) {
					System.out.println("This is the shit!");
				}
				baarimikko.vahennaHealth(2*juomasekoitus.annaSekoituksenMyrkyllisyys());
				break;
			}
			if(vastaus.equals("E") || vastaus.equals("e")) {
				break;
			}
			else {
				System.out.println("Give the answer in the format: yes = Y, no = N!");
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
		System.out.println("You have to make a drink whose volume is over 10 units!");
		Scanner skanneri2 = new Scanner(System.in);
		while(tulos.annaTilavuus() <= 10) {
			System.out.print("Choose the bottle (1-10): ");
			System.out.println("");	
			try{
				juomavalinta = skanneri2.nextInt();
			}
			catch(Exception e) {
				System.out.println("Give an integer between 0-10!");
				skanneri2.nextLine();
				continue;
			}
			System.out.print("Give the amount of liquour (1-10): ");
			System.out.println("");
			try {
				juomanMaara = skanneri2.nextInt();
				if(juomanMaara>10) {
					System.out.println("You can't add that over 10 units!");
					continue;
				}
			}
			catch(Exception e) {
				System.out.println("Give an integer between 0-10!");
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
					System.out.println("Give an integer between 0-10!");
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
