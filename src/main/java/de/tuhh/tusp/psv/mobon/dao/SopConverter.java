package de.tuhh.tusp.psv.mobon.dao;

public interface SopConverter {

	/**
	 * Erzeugt aus einer {@link Application} einen {@link SopApplicant}
	 *
	 * @param application
	 * @return der {@link SopApplicant}
	 */
	// SopApplicant convertApplicationToSopApplicant(Application application);

	/**
	 * Erstellt eine {@link HisPerson} aus einem {@link SopApplicant}
	 *
	 * @param sopApplicant
	 * @param grantedRoles Die Rollen, die dem Applicant gegeben werden sollen (Die
	 *                     Account-Gültigkeit wird entsprechend der ersteln Rolle
	 *                     gesetzt)
	 * @return HisPerson-Objekt aus einem SopApplicant-Objekt
	 * @throws CountryNotFoundException Wenn kein Land zur Nationalität gefunden
	 *                                  werden kann
	 * @throws RuntimeException         Wenn grantedRoles leer ist
	 */
//	HisPerson convertToHisPerson(SopApplicant sopApplicant, List<HisRole> grantedRoles)	throws CountryNotFoundException,
//																						RuntimeException;

	/**
	 * Erzeugt einen {@link HisStudent} aus der {@link HisPerson}
	 *
	 * @param hisP
	 * @param unihhAstatStg
	 * @return das neue Objekt
	 */
	// HisStudent convertToHisStudent(HisPerson hisP, String unihhAstatStg);
}
