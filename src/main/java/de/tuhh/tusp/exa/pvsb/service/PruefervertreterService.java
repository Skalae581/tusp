package de.tuhh.tusp.exa.pvsb.service;

import java.util.List;

import de.tuhh.tusp.exa.pvsb.pojo.Pruefervertreter;
import de.tuhh.tusp.utils.CrudService;

public interface PruefervertreterService extends CrudService<Pruefervertreter> {

	List<Pruefervertreter> findAllRequests();

	// Wenn möglich solltest du
	// HisPersonServiceOutputImpl.createPerson(HisPerson user) verwenden. Du
	// könntest aber auch direkt auf der Webservices-Ebene arbeiten mit dem
	// HisPersonHelperDaoImpl. Das wäre aber eher nicht so toll. Außerdem
	// brauchst du noch die Classe
	// HisRolesServiceOutputImpl.updateOrCreateRolesForPerson(HisPerson user,
	// long personId) für Rollenzuweisung und für Accounts
	// HisAccountServiceOutputImpl.addAccountForPerson(HisPerson user). Hierfür
	// kannst du auch eine HisPerson zusammenbauen und alles reinstecken und
	// dann mit HisDataServiceOutputImpl savePerson(HisPerson user, HisStudent
	// student, MessageCollector collector, WritableResource file,
	// UpdateStudentFileWriter updateStudentFileWriter). Das könnte aber etwas
	// sehr magisch werden. Vielleicht lässt man das lieber, dann gibt es
	// weniger Nebeneffekte.

}
