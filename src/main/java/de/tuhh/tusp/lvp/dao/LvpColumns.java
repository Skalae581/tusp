package de.tuhh.tusp.lvp.dao;

import static de.tuhh.tusp.utils.csv.EnumWithWidth.QuoteMode.always;
import static de.tuhh.tusp.utils.csv.EnumWithWidth.QuoteMode.never;

import de.tuhh.tusp.utils.csv.EnumWithWidth;

public enum LvpColumns implements EnumWithWidth {

	// Veranstaltungsnummer
	// Bezeichnung der Veranstaltung
	// Dozent oder Raumbucher
	// Typ der Veranstaltung
	// Organisationseinheit
	lv_nr_s(0, never), titel(0, always), nutzer(0, always), lv_typ(0, always), bereich(0, always);

	private int width;
	private QuoteMode quote;

	/**
	 * Construct
	 *
	 * @param width
	 */
	LvpColumns(int width, QuoteMode quote) {
		this.width = width;
		this.quote = quote;
	}

	@Override
	public QuoteMode getQuote() {

		return quote;
	}

	@Override
	public int getWidth() {

		return width;
	}
}
