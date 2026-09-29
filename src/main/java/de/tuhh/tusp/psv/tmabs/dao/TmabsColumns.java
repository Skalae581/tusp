package de.tuhh.tusp.psv.tmabs.dao;

import static de.tuhh.tusp.utils.csv.EnumWithWidth.QuoteMode.*;

import de.tuhh.tusp.utils.csv.EnumWithWidth;

public enum TmabsColumns implements EnumWithWidth {
	Anrede(4, always), Titel(0, ifNotEmpty), Nachname(35, always), Vorname(30, always), Geschl(1, always),
	Staat(3, always), Gebdat(8, never), Gebname(35, ifNotEmpty), Gebort(25, always), Gebland(3, always),
	Postrasse(30, always), Pozusatz(30, ifNotEmpty), Poplz(10, always), Poort(30, always), Pokfz(3, always),
	Potel(50, ifNotEmpty), Email(100, always), Mobiltelefon(10, always),
	// Identnr(0),
	Stg(3, always), Stgsem(1, never), Semester(5, never), Status(1, always), Mtknr(10, always), Mtknr_uhh(8, always);

	private int width;
	private QuoteMode quote;

	/**
	 * Construct
	 *
	 * @param width
	 */
	TmabsColumns(int width, QuoteMode quote) {
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
