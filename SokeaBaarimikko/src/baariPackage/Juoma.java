package baariPackage;

public class Juoma {
	protected final int myrkyllisyys;
	protected final int hyvyys;
	/**
	 * Konstruktori alustaa saamillansa arvoilla myrkyllisyys- ja hyvyys-muutttujien arvot.
	 * @param myrkyllisyys
	 * @param hyvyys
	 */
	public Juoma(int myrkyllisyys, int hyvyys) {
		this.myrkyllisyys = myrkyllisyys;
		this.hyvyys = hyvyys;
	}
	/**
	 * Metodi palauttaa myrkyllisyyden
	 * @return
	 */
	int annaMyrkyllisyys() {
		return myrkyllisyys;
	}
	/**
	 * Metodi palauttaa hyvyyden
	 * @return
	 */
	int annaHyvyys() {
		return hyvyys;
	}
}
