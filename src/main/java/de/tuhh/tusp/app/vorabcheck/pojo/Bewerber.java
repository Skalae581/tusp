package de.tuhh.tusp.app.vorabcheck.pojo;

import java.sql.Timestamp;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "bewerber")
@NoArgsConstructor
@Data
public class Bewerber {
	private Long id;
	private String codenummer;
	private Long stgId;
	private String anmerk;
	private String zusatzErbracht;
	private String zusatzAusstehend;
	private String stgName;
	private List<Ects> ects;
	private List<Extra> extra;
	private String extratitel;
	private String ergebnis;
	private String semester;
	private Timestamp vorabcheckZeitstempel;

	private String unittype;
	private String unittotal;
	private String unitduration;

	public Bewerber(String codenummer, String stgName, Long stgId, String anmerk, String zusatzErbracht,
			String zusatzAusstehend) {
		this.codenummer = codenummer;
		this.stgName = stgName;
		this.stgId = stgId;
		this.anmerk = anmerk;
		this.zusatzErbracht = zusatzErbracht;
		this.zusatzAusstehend = zusatzAusstehend;
	}

	public List<Ects> getEcts() {
		return ects;
	}

	public List<Extra> getExtra() {
		return extra;
	}

	@XmlElement(name = "ects")
	public void setEcts(List<Ects> listEcts) {
		this.ects = listEcts;
	}
}
