package de.tuhh.tusp.stu.sptim.utils;

import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimStringUtilsImpl implements TimStringUtils {

	private static final List<String> allowed;
	private static final Map<String, String> replaces;

	static {
		// Liste der erlaubten Zeichen erstell nach:
		// http://xoev.de/latinchars/1_1/datatypes/latinchars.xsd
		allowed = new ArrayList<>();

		allowChar(0x9); // HT
		allowChar(0xa); // LF
		allowChar(0xd); // CR

		// 0-9, a-z, A-Z, , !, #, $, %, &, ', (, ), *, +, ,, -, ., /, [, \, ], ^, _, `, {, |, }, ~
		// Nicht erlaubt: "
		allowCharRange(0x20, 0x21);
		allowCharRange(0x23, 0x7e);

		// ¡, ¢, £, ¤, ¥, ¦, §, ¨, ©, ª, «, ¬
		allowCharRange(0xa1, 0xac);

		// ®, ¯, °, ±, ², ³, ´, µ, ¶, ·, ¸, ¹, º, », ¼, ½, ¾, ¿, À, Á, Â, Ã, Ä, Å, Æ, Ç, È, É, Ê, Ë, Ì, Í, Î, Ï, Ð, Ñ,
		// Ò, Ó, Ô, Õ, Ö, ×, Ø, Ù, Ú, Û, Ü, Ý, Þ, ß, à, á, â, ã, ä, å, æ, ç, è, é, ê, ë, ì, í, î, ï, ð, ñ, ò, ó, ô, õ,
		// ö, ÷, ø, ù, ú, û, ü, ý, þ, ÿ, Ā, ā, Ă, ă, Ą, ą, Ć, ć
		allowCharRange(0xae, 0x107);

		// Ċ, ċ, Č, č, Ď, ď, Đ, đ, Ē, ē, Ĕ, ĕ, Ė, ė, Ę, ę, Ě, ě
		allowCharRange(0x10a, 0x11b);

		// Ğ, ğ, Ġ, ġ, Ģ, ģ
		allowCharRange(0x11e, 0x123);

		// Ħ, ħ, Ĩ, ĩ, Ī, ī, Ĭ, ĭ, Į, į, İ, ı
		allowCharRange(0x126, 0x131);

		// Ĵ, ĵ, Ķ, ķ, ĸ, Ĺ, ĺ, Ļ, ļ, Ľ, ľ, Ŀ, ŀ, Ł, ł, Ń, ń, Ņ, ņ, Ň, ň, ŉ, Ŋ, ŋ, Ō, ō, Ŏ, ŏ, Ő, ő, Œ, œ, Ŕ, ŕ, Ŗ, ŗ,
		// Ř, ř, Ś, ś
		allowCharRange(0x134, 0x15b);

		// Ş, ş, Š, š, Ţ, ţ, Ť, ť, Ŧ, ŧ, Ũ, ũ, Ū, ū
		allowCharRange(0x15e, 0x16b);

		// Ů, ů, Ű, ű, Ų, ų, Ŵ, ŵ, Ŷ, ŷ, Ÿ, Ź, ź, Ż, ż, Ž, ž
		allowCharRange(0x16e, 0x17e);

		allowChar(0x18f); // Ə

		// Ơ, ơ
		allowCharRange(0x1a0, 0x1a1);

		// Ư, ư
		allowCharRange(0x1af, 0x1b0);

		allowChar(0x1b7); // Ʒ

		// Ǎ, ǎ, Ǐ, ǐ, Ǒ, ǒ, Ǔ, ǔ
		allowCharRange(0x1cd, 0x1d4);

		// Ǟ, ǟ
		allowCharRange(0x1de, 0x1df);

		// Ǥ, ǥ, Ǧ, ǧ, Ǩ, ǩ, Ǫ, ǫ, Ǭ, ǭ, Ǯ, ǯ, ǰ
		allowCharRange(0x1e4, 0x1f0);

		// Ǵ, ǵ
		allowCharRange(0x1f4, 0x1f5);

		// Ǻ, ǻ, Ǽ, ǽ, Ǿ, ǿ
		allowCharRange(0x1fa, 0x1ff);

		// Ș, ș, Ț, ț
		allowCharRange(0x218, 0x21b);

		// Ȟ, ȟ
		allowCharRange(0x21e, 0x21f);

		// Ȫ, ȫ
		allowCharRange(0x22a, 0x22b);

		// Ȯ, ȯ, Ȱ, ȱ, Ȳ, ȳ
		allowCharRange(0x22e, 0x233);

		allowChar(0x259); // ə

		allowChar(0x292); // ʒ

		// Ḃ, ḃ
		allowCharRange(0x1e02, 0x1e03);

		// Ḋ, ḋ
		allowCharRange(0x1e0a, 0x1e0b);

		// Ḑ, ḑ
		allowCharRange(0x1e10, 0x1e11);

		// Ḟ, ḟ, Ḡ, ḡ
		allowCharRange(0x1e1e, 0x1e21);

		// Ḥ, ḥ, Ḧ, ḧ
		allowCharRange(0x1e24, 0x1e27);

		// Ḱ, ḱ
		allowCharRange(0x1e30, 0x1e31);

		// Ṁ, ṁ
		allowCharRange(0x1e40, 0x1e41);

		// Ṅ, ṅ
		allowCharRange(0x1e44, 0x1e45);

		// Ṗ, ṗ
		allowCharRange(0x1e56, 0x1e57);

		// Ṡ, ṡ, Ṣ, ṣ
		allowCharRange(0x1e60, 0x1e63);

		// Ṫ, ṫ
		allowCharRange(0x1e6a, 0x1e6b);

		// Ẁ, ẁ, Ẃ, ẃ, Ẅ, ẅ
		allowCharRange(0x1e80, 0x1e85);

		// Ẍ, ẍ, Ẏ, ẏ, Ẑ, ẑ, Ẓ, ẓ
		allowCharRange(0x1e8c, 0x1e93);

		allowChar(0x1e9e); // ẞ

		// Ạ, ạ, Ả, ả, Ấ, ấ, Ầ, ầ
		allowCharRange(0x1ea0, 0x1ea7);

		// Ẫ, ẫ, Ậ
		allowCharRange(0x1eaa, 0x1eac);

		// Ắ, ắ, Ằ, ằ, Ẳ, ẳ, Ẵ, ẵ, Ặ, ặ, Ẹ, ẹ, Ẻ, ẻ, Ẽ, ẽ, Ế, ế, Ề, ề
		allowCharRange(0x1eae, 0x1ec1);

		// Ễ, ễ, Ệ, ệ, Ỉ, ỉ, Ị, ị, Ọ, ọ, Ỏ, ỏ, Ố, ố, Ồ, ồ
		allowCharRange(0x1ec4, 0x1ed3);

		// Ỗ, ỗ, Ộ, ộ, Ớ, ớ, Ờ, ờ
		allowCharRange(0x1ed6, 0x1edd);

		// Ụ, ụ, Ủ, ủ, Ứ, ứ, Ừ, ừ, Ử, ử, Ữ, ữ, Ự, ự, Ỳ, ỳ, Ỵ, ỵ, Ỷ, ỷ, Ỹ, ỹ
		allowCharRange(0x1ee4, 0x1ef9);

		allowChar(0x20ac); // €
		allowSpecialChar(0x4d, 0x302); // M̂
		allowSpecialChar(0x4e, 0x302); // N̂
		allowSpecialChar(0x6d, 0x302); // m̂
		allowSpecialChar(0x6e, 0x302); // n̂
		allowSpecialChar(0x44, 0x302); // D̂
		allowSpecialChar(0x64, 0x302); // d̂
		allowSpecialChar(0x4a, 0x30c); // J̌
		allowSpecialChar(0x4c, 0x302); // L̂
		allowSpecialChar(0x6c, 0x302); // l̂

		replaces = new HashMap<>();

		// Ersetzungen von APP/HiO
		replaceChar(0x200, 'Ä'); // Ȁ
		replaceChar(0x201, 'ä'); // ȁ
		replaceChar(0x202, 'A'); // Ȃ
		replaceChar(0x203, 'a'); // ȃ
		replaceChar(0x204, 'E'); // Ȅ
		replaceChar(0x205, 'e'); // ȅ
		replaceChar(0x206, 'E'); // Ȇ
		replaceChar(0x207, 'e'); // ȇ
		replaceChar(0x208, 'I'); // Ȉ
		replaceChar(0x209, 'i'); // ȉ
		replaceChar(0x20a, 'I'); // Ȋ
		replaceChar(0x20b, 'i'); // ȋ
		replaceChar(0x20c, 'Ö'); // Ȍ
		replaceChar(0x20d, 'ö'); // ȍ
		replaceChar(0x20e, 'O'); // Ȏ
		replaceChar(0x20f, 'o'); // ȏ
		replaceChar(0x210, 'R'); // Ȑ
		replaceChar(0x211, 'r'); // ȑ
		replaceChar(0x212, 'R'); // Ȓ
		replaceChar(0x213, 'r'); // ȓ
		replaceChar(0x214, 'Ü'); // Ȕ
		replaceChar(0x215, 'ü'); // ȕ
		replaceChar(0x216, 'U'); // Ȗ
		replaceChar(0x217, 'u'); // ȗ
		replaceChar(0x220, 'N'); // Ƞ
		replaceChar(0x221, 'd'); // ȡ
		replaceChar(0x224, 'Z'); // Ȥ
		replaceChar(0x225, 'z'); // ȥ
		replaceChar(0x226, 'A'); // Ȧ
		replaceChar(0x227, 'a'); // ȧ
		replaceChar(0x228, 'E'); // Ȩ
		replaceChar(0x229, 'e'); // ȩ
		replaceChar(0x22c, 'O'); // Ȭ
		replaceChar(0x22d, 'o'); // ȭ
		replaceChar(0x234, 'l'); // ȴ
		replaceChar(0x235, 'n'); // ȵ
		replaceChar(0x236, 't'); // ȶ
		replaceChar(0x23b, 'C'); // Ȼ
		replaceChar(0x23c, 'c'); // ȼ
		replaceChar(0x23d, 'L'); // Ƚ
		replaceChar(0x23e, 'T'); // Ⱦ
		replaceChar(0x23f, 's'); // ȿ
		replaceChar(0x240, 'z'); // ɀ
		replaceChar(0x108, 'C'); // Ĉ
		replaceChar(0x109, 'c'); // ĉ
		replaceChar(0x11c, 'G'); // Ĝ
		replaceChar(0x11d, 'g'); // ĝ
		replaceChar(0x124, 'h'); // Ĥ
		replaceChar(0x125, 'h'); // ĥ
		replaceChar(0x132, 'Y'); // Ĳ
		replaceChar(0x133, 'y'); // ĳ
		replaceChar(0x15c, 's'); // Ŝ
		replaceChar(0x15d, 's'); // ŝ
		replaceChar(0x16c, 'U'); // Ŭ
		replaceChar(0x16d, 'u'); // ŭ
		replaceChar(0x17f, 's'); // ſ
		replaceChar(0x180, 'b'); // ƀ
		replaceChar(0x181, 'B'); // Ɓ
		replaceChar(0x182, 'B'); // Ƃ
		replaceChar(0x183, 'b'); // ƃ
		replaceChar(0x186, 'O'); // Ɔ
		replaceChar(0x187, 'C'); // Ƈ
		replaceChar(0x188, 'c'); // ƈ
		replaceChar(0x189, 'D'); // Ɖ
		replaceChar(0x18a, 'D'); // Ɗ
		replaceChar(0x18b, 'D'); // Ƌ
		replaceChar(0x18c, 'd'); // ƌ
		replaceChar(0x18e, 'E'); // Ǝ
		replaceChar(0x190, 'E'); // Ɛ
		replaceChar(0x191, 'F'); // Ƒ
		replaceChar(0x192, 'f'); // ƒ
		replaceChar(0x193, 'G'); // Ɠ
		replaceChar(0x197, 'I'); // Ɨ
		replaceChar(0x198, 'K'); // Ƙ
		replaceChar(0x199, 'k'); // ƙ
		replaceChar(0x19a, 'l'); // ƚ
		replaceChar(0x19c, 'M'); // Ɯ
		replaceChar(0x19d, 'N'); // Ɲ
		replaceChar(0x19e, 'N'); // ƞ
		replaceChar(0x19f, 'O'); // Ɵ
		replaceChar(0x1a4, 'P'); // Ƥ
		replaceChar(0x1a5, 'p'); // ƥ
		replaceChar(0x1ab, 't'); // ƫ
		replaceChar(0x1ac, 'T'); // Ƭ
		replaceChar(0x1ad, 't'); // ƭ
		replaceChar(0x1ae, 'T'); // Ʈ
		replaceChar(0x1b2, 'V'); // Ʋ
		replaceChar(0x3b2, 'b'); // β
		replaceChar(0x1b3, 'Y'); // Ƴ
		replaceChar(0x1b4, 'y'); // ƴ
		replaceChar(0x1b5, 'Z'); // Ƶ
		replaceChar(0x1b6, 'z'); // ƶ
		replaceChar(0x1d5, 'Ü'); // Ǖ
		replaceChar(0x1d6, 'ü'); // ǖ
		replaceChar(0x1d7, 'Ü'); // Ǘ
		replaceChar(0x1d8, 'ü'); // ǘ
		replaceChar(0x1d9, 'Ü'); // Ǚ
		replaceChar(0x1da, 'ü'); // ǚ
		replaceChar(0x1db, 'Ü'); // Ǜ
		replaceChar(0x1dc, 'ü'); // ǜ
		replaceChar(0x1dd, 'e'); // ǝ
		replaceChar(0x1e0, 'A'); // Ǡ
		replaceChar(0x1e1, 'a'); // ǡ
		replaceChar(0x1e2, 'Ä'); // Ǣ
		replaceChar(0x1e3, 'ä'); // ǣ
		replaceChar(0x1f8, 'N'); // Ǹ
		replaceChar(0x1f9, 'n'); // ǹ
		replaceChar('\"', '\'');
		replaceChar('‘', '\'');
	}

	/**
	 * Erlaubt einen Buchstaben
	 *
	 * @param ord
	 */
	private static void allowChar(int ord) {

		allowed.add("" + (char) ord);
	}

	/**
	 * Erlaubt einen Bereich von Zeichen
	 *
	 * @param from
	 *            (inklusive)
	 * @param to
	 *            (inklusive)
	 */
	private static void allowCharRange(int from, int to) {

		for (int i = from; i <= to; i++) {
			allowChar((char) i);
		}
	}

	/**
	 * Erlaubt ein Zeichen, dass aus zweien zusammengesetzt ist
	 *
	 * @param ord
	 * @param ord2
	 */
	private static void allowSpecialChar(int ord, int ord2) {

		allowed.add("" + (char) ord + (char) ord2);
	}

	/**
	 * Trägt ein Zeichen und seine Ersätzung ein
	 *
	 * @param search
	 * @param replace
	 */
	private static void replaceChar(int search, char replace) {

		replaces.put("" + (char) search, "" + replace);
	}

	@Autowired
	private StringSanitizer sanitizer;

	@Override
	public String sanitize(String value) throws UnsupportedCharsetException {

		return sanitizer.sanitize(value, allowed, replaces);
	}

}
