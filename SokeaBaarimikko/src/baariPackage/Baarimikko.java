package baariPackage;
/**
 * Perii luokan Ihminen. Luokka kuvaa pelaajaa.
 * @author tuoma
 *
 */
public class Baarimikko extends Ihminen {
	int suosio;
	String nimi;
	double raha;
	double illanTienestit;
	/**
	 * Luokan konstruktori, tallentaa nimen ja alustaa suosion ja rahan 0:aan.
	 * @param nimi
	 */
	Baarimikko(String nimi) {
		super();
		suosio = 0;
		this.nimi = nimi;
		this.raha = 0;
	}
	/**
	 * Muuttaa suosio-muuttujan arvoa
	 * @param suosio
	 */
	void lisaaSuosio(int suosio) {
		int apu = this.suosio+suosio;
		if(apu<0) {
			this.suosio=0;
		}
		this.suosio += apu;
	}
	/**
	 * Antaa suosion
	 * @return
	 */
	int annaSuosio() {
		return suosio;
	}
	/**
	 * Antaa nimen
	 * @return
	 */
	String annaNimi() {
		return nimi;
	}
	/**
	 * Muuttaa raha-muuttujan arvoa
	 * @param raha
	 */
	void lisaaRaha(double raha) {
		this.raha=this.raha+raha;
	}
	double annaRaha() {
		return raha;
	}
	/**
	 * Muuttaa muuttujan illanTienestit arvoa
	 * @param tienestit
	 */
	void asetaIllanTienestit(double tienestit) {
		this.illanTienestit = tienestit;
	}
	/**
	 * Palauttaa muuttujan illanTienestit
	 * @return
	 */
	double annaIllanTienestit() {
		return illanTienestit;
	}
}
