package de.tuhh.tusp.utils.csv;

public interface EnumWithWidth {

	public enum QuoteMode {
		always, never, ifNotEmpty
	}

	/**
	 * gibt zurück ob das Feld gequoted werden soll
	 *
	 * @return true wenn Quoting gewünscht
	 */
	QuoteMode getQuote();

	/**
	 * Gibt die benötigte Spaltenbreite wieder
	 *
	 * @return die Breite
	 */
	int getWidth();

	/**
	 *
	 * @return this.toString()
	 */
	default String name() {

		return this.toString();
	}
}
