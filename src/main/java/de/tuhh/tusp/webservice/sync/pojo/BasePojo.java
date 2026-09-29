package de.tuhh.tusp.webservice.sync.pojo;

public interface BasePojo {

	/**
	 * Gibt ID zurück
	 * 
	 * @return ID
	 */
	Long getId();

	/**
	 * Gibt Gültigkeit zurück
	 * 
	 * @return valid
	 */
	Boolean isValid();

	/**
	 * Setz Gültigkeit
	 * 
	 * @param valid
	 */
	void setValid(Boolean valid);
}
