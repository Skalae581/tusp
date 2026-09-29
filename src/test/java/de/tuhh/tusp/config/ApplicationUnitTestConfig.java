package de.tuhh.tusp.config;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.QuoteMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.WritableResource;

import de.tuhh.tusp.utils.csv.CsvWriter;

@Configuration
@Import({ UnitTestDatabaseConfig.class, PropertiesConfig.class })
public class ApplicationUnitTestConfig {

	private static Logger logger = LogManager.getLogger(ApplicationUnitTestConfig.class);

	@Bean
	public CsvWriter getCsvWriter() {

		return new CsvWriter() {

			@Override
			public <E extends Enum<E>> void write(WritableResource outFile, List<Map<E, String>> data, Charset encoding,
					Class<E> enumType, char delimiter, boolean withHeader, QuoteMode mode)
					throws IOException, NullPointerException {

				logger.info(data);
			}

			@Override
			public <E extends Enum<E>> void writeWithNewLineSeparator(WritableResource outFile,
					List<Map<E, String>> data, Charset encoding, Class<E> enumType, char delimiter, boolean withHeader,
					QuoteMode mode) throws IOException, NullPointerException {
				logger.info(data);

			}

		};
	}

}
