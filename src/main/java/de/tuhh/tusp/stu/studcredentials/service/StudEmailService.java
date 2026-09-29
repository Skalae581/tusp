package de.tuhh.tusp.stu.studcredentials.service;

public interface StudEmailService {

	/**
	 * Text für E-Mail die an Studenten gesenden wird
	 *
	 * @param deText Benutzername + Initial Pwd //de
	 * @param enText Benutzername + Initial Pwd //en
	 * @return
	 */
	String getTextForEmail(String deText, String enText);

}
