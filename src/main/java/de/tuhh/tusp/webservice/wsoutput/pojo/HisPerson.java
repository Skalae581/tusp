package de.tuhh.tusp.webservice.wsoutput.pojo;

import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import de.tuhh.tusp.webservice.wsoutput.service.HisAddressesServiceOutput;
import lombok.Data;

@Data
public class HisPerson {

	private Long idPerson;
	private Long idExtern; // oft synonym zur mtknr
	private String objGuid;
	private String firstname;
	private String surname;
	private String birthname;
	private String artistname;
	private String nameprefix;
	private String namesuffix;
	private String allfirstnames;
	private XMLGregorianCalendar dateofbirth;
	private String birthcity;
	private Long genderId;
	private String academicdegreesuffix;
	private Long academicdegreeId;
	private Long titleId;
	private Long countryId;
	private XMLGregorianCalendar createdAt;
	private XMLGregorianCalendar updatedAt;
	private String status;
	private Long nationalityId;
	private Long secondNationalityId;
	private Long selfregistrationStatusId;
	private Long semester;
	private String hmkfzkz;
	private Long hmkfz;
	private XMLGregorianCalendar immdat;

	private String hzbart;

	private HisAccount account;
	private PersonAdressesWrapper addresses;
	private List<HisRole> roles;

	/**
	 * Erstellt einen Studenten ohne weitere Werte
	 *
	 * @param personId
	 * @param mtknr
	 */
	public HisPerson(Long personId, HisAddressesServiceOutput hisAdressesServiceOutput) {
		this.idPerson = personId;
		if (hisAdressesServiceOutput != null) {
			this.addresses = hisAdressesServiceOutput.readPersonAddresses(personId);
		}
		else {
			this.addresses = new PersonAdressesWrapper(personId);
		}
	}

	/**
	 * Erstellt einen kurzen String, mit dem der Datensatz erkannt werden kann.
	 *
	 * @return string
	 */
	public String toShortString() {

		return "HisPerson(personId=" + idPerson + ", externeMtknr=" + idExtern + ", firstname=" + firstname
				+ ", surname=" + surname + ", account=" + account + ")";
	}
}
