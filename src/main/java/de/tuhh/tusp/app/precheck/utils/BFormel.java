package de.tuhh.tusp.app.precheck.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.precheck.dao.GradeDao;
import de.tuhh.tusp.webservice.sync.dao.CountryDao;
import de.tuhh.tusp.webservice.sync.pojo.Country;

@Component
public class BFormel {

	private static Logger logger = LogManager.getLogger(BFormel.class);

	@Autowired
	private GradeDao gradeDao;

	@Autowired
	private CountryDao countryDao;

	/**
	 * Berechnet die deutsche Note
	 *
	 * @param grade
	 * @param max
	 * @param min
	 * @param average
	 * @return deutsche Note
	 */
	public float calculateGrade(float grade, float max, float min, float average) {

		BigDecimal nmax = new BigDecimal(max, MathContext.DECIMAL32); // 10

		BigDecimal nmin = new BigDecimal(min, MathContext.DECIMAL32); // 4

		BigDecimal naverage = new BigDecimal(average, MathContext.DECIMAL32); // 7,4

		BigDecimal r1 = nmax.subtract(nmin, MathContext.DECIMAL32);

		BigDecimal r2 = nmax.subtract(naverage, MathContext.DECIMAL32);

		BigDecimal div = r2.divide(r1, MathContext.DECIMAL32);
		BigDecimal mul = div.multiply(new BigDecimal(3), MathContext.DECIMAL32);
		BigDecimal result = mul.add(BigDecimal.ONE, MathContext.DECIMAL32);

		return result.setScale(1, RoundingMode.DOWN).floatValue();
	}

	/**
	 * Berechnet den Status
	 *
	 * @param grade
	 * @param max
	 * @param min
	 * @param average
	 * @return status
	 */
	public Long calculateStatus(float grade, float max, float min, float average) {

		Long status = null;

		if (grade == 9.9f) {
			status = 2L;
		} else if (grade == 10.0f) {
			status = 1L;
		} else if (grade == 20.0f) {
			status = 2L;
		} else {

			double result = calculateGrade(grade, max, min, average);

			if (result < 0) {
				status = 4L;
			} else if (result <= grade) {
				status = 1L;
			} else if (result > grade) {
				status = 4L;
			} else {
				status = 3L;
			}
		}
		return status;
	}

	/**
	 * Berechnet die landes- und hochschulspezifischen Notenabweichungen
	 *
	 * @param countryId
	 * @param institution
	 * @return angepasste Note
	 */
	public float getGrade(Long countryId, String institution) {

		Float note = -1.0f;

		Country country = countryDao.fetchById(countryId);
		if (country == null) {
			logger.error("Keine Note hinterlegt für CountryID " + countryId);
			return note;
		}
		String countryName = country.getUniquename();
		System.out.println("Country: " + countryName);

		if (countryName.equals("VRC") || countryName.equals("TJ")) { // China
			if (institution != null) {
				if (institution.equals("211")) {
					note = 2.5f;
				} else if (institution.equals("Other")) {
					note = 2.1f;
				}
			}

		} else if (countryName.equals("IND")) { // Indien
			if (institution != null) {
				if (institution.equals("Nit")) {
					note = 2.5f;
				} else if (institution.equals("Other")) {
					note = 2.2f;
				}
			}
		} else if (countryName.equals("Other")) {
			note = 2.0f;
		} else {
			if (countryName != null && !countryName.equals("")) {

				note = gradeDao.getGradeForCountry(countryId);
				if (note == null) {
					note = 2.0f;
					logger.error("Keine Note hinterlegt für " + countryName + " mit CountryID " + countryId);
				}
			}
		}

		return note;
	}

}
