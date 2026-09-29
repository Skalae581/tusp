package de.tuhh.tusp.app.precheck.pojo;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class Interessent {

	// diese Validation-Groups können genutzt werden um die Bean stückweise
	// zu validieren. Pro Formular soll eine Gruppe gamacht werden....
	public interface PersonDetails {
	}

	public interface StudyDetails {
	}

	private long id;

	@Length(min = 2, max = 255, groups = { PersonDetails.class })
	private String nachname;

	@Size(min = 2, max = 255, groups = { PersonDetails.class })
	private String vorname;

	@NotEmpty(groups = { PersonDetails.class })
	@Email(groups = { PersonDetails.class })
	private String email;

	@Size(min = 2, max = 255, groups = { StudyDetails.class })
	private String abschluss;

	@Size(min = 2, max = 255, groups = { PersonDetails.class })
	private String stadt;

	@Size(min = 2, max = 255, groups = { StudyDetails.class })
	private String uni;

	@Size(min = 2, max = 255, groups = { StudyDetails.class })
	private String abgeschlossenesstd;

	private Date bearbeitungsdatum;

	@NotNull(groups = { PersonDetails.class })
	private Integer geschlecht;

	@NotNull(groups = { PersonDetails.class })
	private Date geburtsdatum;

	@NotNull(groups = { StudyDetails.class })
	private Integer stdDauer;

	@NotNull(groups = { StudyDetails.class })
	@Min(groups = { StudyDetails.class }, value = 1900)
	@Max(groups = { StudyDetails.class }, value = 2100)
	private Integer abschlussJahr;

	@NotNull(groups = { StudyDetails.class })
	private Double minNote;

	@NotNull(groups = { StudyDetails.class })
	private Double maxNote;

	@NotNull(groups = { StudyDetails.class })
	private Double erreichteNote;

	private String kommentar;

	@NotNull(groups = { StudyDetails.class })
	private Long stgTuhh; // FK

	@NotNull
	private Long status = null; // FK

	@NotNull(groups = { PersonDetails.class })
	private Long geburtsland; // FK

	@NotNull(groups = { StudyDetails.class })
	private Long studienland; // FK

	private Date bewerbungsdatum;

	@NotNull
	private float tuhhNote;

	@NotNull
	private String bearbeitet;

	private String verifierCode;

	private String institution;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Interessent interessent = (Interessent) o;
		return vorname.equals(interessent.vorname) && nachname.equals(interessent.nachname)
				&& email.equals(interessent.email);

	}

	@Override
	public int hashCode() {
		return Objects.hash(vorname, nachname, email);
	}

}
