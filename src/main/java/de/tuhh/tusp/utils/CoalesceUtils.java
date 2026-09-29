package de.tuhh.tusp.utils;

public class CoalesceUtils {

	/**
	 *
	 * Liefert den ersten Wert der nicht null ist
	 *
	 * @param items
	 * @return den ersten nicht null wert oder null
	 */
	@SafeVarargs
	public static <T> T coalesce(T... items) {

		for (T i : items) {
			if (i != null) {
				return i;
			}
		}
		return null;
	}

	/**
	 * Liefert den ersten Wert der nicht null ist
	 *
	 * @param a
	 * @param b
	 * @return den ersten nicht null wert oder null
	 */
	public static <T> T coalesce(T a, T b) {

		return a == null ? b : a;
	}

	/**
	 *
	 * Liefert den ersten Wert der nicht null ist
	 *
	 * @param a
	 * @param b
	 * @param c
	 * @return den ersten nicht null wert oder null
	 */
	public static <T> T coalesce(T a, T b, T c) {

		return a != null ? a : b != null ? b : c;
	}
}
