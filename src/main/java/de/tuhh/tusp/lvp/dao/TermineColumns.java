package de.tuhh.tusp.lvp.dao;

import static de.tuhh.tusp.utils.csv.EnumWithWidth.QuoteMode.always;
import static de.tuhh.tusp.utils.csv.EnumWithWidth.QuoteMode.never;

import de.tuhh.tusp.utils.csv.EnumWithWidth;

public enum TermineColumns implements EnumWithWidth {

	// veranstaltungs nr
	// Gebäude
	// raum
	// datum anfang
	// datum ende
	// uhrzeit anfang
	// uhrzeit ende
	lv_nr_s(0, never), gbd_s(0, always), raum_s(0, always), dbeg(0, always), dend(0, always), beginn(0, always),
	ende(0, always);

	private int width;
	private QuoteMode quote;

	/**
	 * Construct
	 *
	 * @param width
	 */
	TermineColumns(int width, QuoteMode quote) {
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
