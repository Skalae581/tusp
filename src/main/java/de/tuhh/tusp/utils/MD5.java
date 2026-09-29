package de.tuhh.tusp.utils;

public interface MD5 {

	/**
	 * Erzeugt Ziffer von email + status
	 *
	 * @param md5
	 * @return Anfang des MD5-Hash mit Länge length
	 */
	String md5(String md5, int length);
}