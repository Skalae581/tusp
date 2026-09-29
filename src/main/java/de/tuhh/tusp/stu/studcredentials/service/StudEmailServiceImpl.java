package de.tuhh.tusp.stu.studcredentials.service;

import org.springframework.stereotype.Component;

@Component
public class StudEmailServiceImpl implements StudEmailService {

	@Override
	public String getTextForEmail(String deText, String enText) {
		String body = "";

		body += "" + "--English version below -- </br></br>" + "Ihr Studierendenaccount wurde eingerichtet:</br></br>"

				+ deText
				+ "Ab sofort steht Ihnen in Ihrem TUNE-Portal Ihr TU-Account inklusive einer TUHH-Mail-Adresse zur Verfügung.</br>"
				+ "Wie Sie diesen Einrichten erfahren Sie unter "
				+ "<a href='https://www.tuhh.de/t3resources/tuhh/download/studium/studierende/InfoTU-Account012020.pdf'>https://www.tuhh.de/t3resources/tuhh/download/studium/studierende/InfoTU-Account012020.pdf</a>"
				+ "</br></br>"

				+ "Sie sind zum regelmäßigen Abrufen dieser Mailadresse verpflichtet. Die TUHH kommuniziert nur über diese Mailadresse mit Ihnen.</br></br>"

				+ "Den Account benötigen Sie um sich Immatrikulationsbescheinigungen und Bescheinigungen für das BAfög-Amt "
				+ "auszudrucken und alle weiteren Selbstbedienungsfunktionen (Ab- und Anmelden von Prüfungen, StudIP usw.) zu nutzen.</br></br>"

				+ "Nachdem Sie den Account eingerichtet haben, können Sie sich als Studierende auf</br>"
				+ "<a href='https://tune.tuhh.de/'>https://tune.tuhh.de/</a> einloggen.</br></br>"

				+ "Sofern Sie Schwierigkeiten bei der Einrichtung des Accounts haben, wenden Sie sich bitte an <a href='servicedesk@tuhh.de'>servicedesk@tuhh.de</a></br></br></br></br>"

				+ "----</br></br>"

				+ "Your student account has been set up: </br</br>"

				+ enText

				+ "From now on you can find your TU account and your TU-email-address in the TUNE-portal.</br></br>"

				+ "To get to know how to acticate your TU account please check: <a href='https://www.tuhh.de/t3resources/tuhh/download/studium/studierende/InfoTU-Account012020.pdf'>https://www.tuhh.de/t3resources/tuhh/download/studium/studierende/InfoTU-Account012020.pdf</a></br></br>"

				+ "You are obligated to check for emails from your TUHH account on a regular basis. The TUHH only communicates to you via this email address.</br></br>"

				+ "You  need your TU account to print certificates of enrolment and certificates for the BAföG office and to access all the other self service functions (registration and deregistration for examinations</br>"
				+ "Stud.IP etc.).</br></br>"

				+ "After you have activated your account you can login as student on <a href='https://tune.tuhh.de/'>https://tune.tuhh.de/</a></br></br>"

				+ "If you have  problems to activate  TUHH account, please  contact <a href='servicedesk@tuhh.de'>servicedesk@tuhh.de</a>";

		return body;
	}

}
