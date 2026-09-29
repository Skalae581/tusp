package de.tuhh.tusp.exa.massmails.dao;

import java.util.List;

import de.tuhh.tusp.exa.massmails.pojo.Receiver;

public interface HioPersonDao {

	void closeDbCon();

	List<Long> getLehrendenIds(Long termtypeId, Long termYear);

	List<Long> getLehrpersonIds();

	List<Long> getLehrpersonVertreterIds();

	Receiver getPersonData(Long personId);

	List<Long> getPrueferIds();

	List<Long> getPrueferIds(Long termtypeId, Long termYear);

	List<Long> getPrueferVertreterIds();

	List<Long> getPrueferVertreterIds(Long termtypeId, Long termYear);
}
