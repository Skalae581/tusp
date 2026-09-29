package de.tuhh.tusp.stu.sptim.utils;

import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class StringSanitizerImpl implements StringSanitizer {

	// Liste der kombinierenden Buchstaben (schreiben sich über verherigen)
	private static final List<Character> combining = new ArrayList<>();

	static {
		// ́, ̂, ̃, ̄, ̅, ̆, ̇, ̈, ̉, ̊, ̋, ̌, ̍, ̎, ̏, ̐, ̑, ̒, ̓, ̔, ̕, ̖, ̗, ̘, ̙, ̚, ̛, ̜, ̝, ̞, ̟, ̠, ̡, ̢, ̣, ̤,
		// ̥, ̦, ̧, ̨, ̩, ̪, ̫, ̬, ̭, ̮, ̯, ̰, ̱, ̲, ̳, ̴, ̵, ̶, ̷, ̸, ̹, ̺, ̻, ̼, ̽, ̾, ̿, ̀, ́, ͂, ̓, ̈́, ͅ, ͆, ͇, ͈,
		// ͉, ͊, ͋, ͌, ͍, ͎
		for (int i = 0x0300; i <= 0x034e; i++) {
			combining.add((char) i);
		}

		// Liste unvollständig siehe:
		// https://de.wikipedia.org/wiki/Unicodeblock_Kombinierende_diakritische_Zeichen
		// https://de.wikipedia.org/wiki/Unicodeblock_Kombinierende_diakritische_Zeichen,_Erg%C3%A4nzung
		// https://de.wikipedia.org/wiki/Unicodeblock_Kombinierende_diakritische_Zeichen,_erweitert
	}

	@Override
	public String sanitize(	String string, List<String> allowed,
							Map<String, String> replaces) throws UnsupportedCharsetException {

		if (string == null) {
			return null;
		}

		StringBuilder builder = new StringBuilder();

		final int length = string.length();
		for (int offset = 0; offset < length;) {
			int len = 1;
			while (offset + len < length && combining.contains(string.charAt(offset + len))) {
				len++;
			}
			String c = string.substring(offset, offset + len);

			if (allowed.contains(c)) {
				builder.append(c);
			}
			else if (replaces.containsKey(c)) {
				if (replaces.get(c) != null) {
					builder.append(replaces.get(c));
				}
			}
			else {
				throw new UnsupportedCharsetException("Buchstabe '" + c + "' ist nicht erlaubt oder umwandelbar.");
			}

			offset += c.length();
		}

		return builder.toString();
	}

}
