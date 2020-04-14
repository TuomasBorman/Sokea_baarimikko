package baariPackage;

public class Ihminen {
	/*
	 * Health-muuttuja kertoo olion terveyden
	 * Konstruktori alustaa muuttujan arvon 100:aan
	 */
	int health;
	Ihminen() { 
		this.health = 100;
	}
	/**
	 * Metodin avulla muutetaan olion health-muuttujan arvoa. Arvo ei voi olla yli 100.
	 * @param damage muuttuja, joka maaraa health-muuttujan muutoksen
	 */
	void vahennaHealth(int damage) {
		int apu = health+damage;
		if(apu<= 100) {
			this.health = apu;
		}
		else {
			this.health = 100;
		}
		
	}
	/**
	 * Palauttaa olion health-muuttujan
	 * @return
	 */
	int annaHealth() {
		return health;
	}
}
