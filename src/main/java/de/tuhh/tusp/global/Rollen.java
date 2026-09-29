package de.tuhh.tusp.global;

public enum Rollen {

	ADMIN, EXA_ADMIN, NON_SHIBBOLETH_USER, GUEST, PUBLIC, MITARBEITER, APP_ADMIN, ZULBEARB, STU_SENIOR_MANAGER, PRUEFER,
	MASSENMAIL, LEHRENDER, MITARBEITENDE, EMPLOYEE_TUHH;

	@Override
	public String toString() {

		return this.name();
	}
}
