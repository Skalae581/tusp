package de.tuhh.tusp.utils;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationUnitTestConfig;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class TuspMessageSourceTest {

	private TuspMessageSource candidateDe;
	private TuspMessageSource candidateEn;
	private Locale de;
	private Locale en;

	@Before
	public void before() {

		de = new Locale("de");
		en = new Locale("en");
		candidateDe = new TuspMessageSource(de);
		candidateEn = new TuspMessageSource(en);
		String[] baseNames = candidateDe.scanForBasenames();
		final String i18 = candidateDe.getRessourceFolder();

		ResourceLoader loader = new ResourceLoader() {

			@Override
			public ClassLoader getClassLoader() {

				return null;
			}

			@Override
			public Resource getResource(String arg0) {

				File f = new File(i18 + "/../" + arg0);
				return new FileSystemResource(f);
			}
		};
		candidateDe.setBasenames(baseNames);
		candidateEn.setBasenames(baseNames);

		candidateDe.setResourceLoader(loader);
		candidateEn.setResourceLoader(loader);
	}

	@Test
	public void resolveCodeTest() {

		// Get existing
		assertEquals("Bilingualer Testkey", candidateDe.getMessage("test.key.bi", null, de));
		assertEquals("Deutscher Testkey", candidateDe.getMessage("test.key.de", null, de));

		// Use key as fallback in DefaultLocale
		assertEquals("test.key.en", candidateDe.getMessage("test.key.en", null, de));
		assertEquals("test.key.null", candidateDe.getMessage("test.key.null", null, de));

		// Get existing
		assertEquals("Bilingualer Testkey (en)", candidateEn.getMessage("test.key.bi", null, en));
		assertEquals("Englischer Testkey", candidateEn.getMessage("test.key.en", null, en));

		// Use Default as fallback in DefaultLocale
		assertEquals("Deutscher Testkey", candidateEn.getMessage("test.key.de", null, en));

		// Use key as fallback in DefaultLocale
		assertEquals("test.key.null", candidateEn.getMessage("test.key.null", null, en));
	}
}
