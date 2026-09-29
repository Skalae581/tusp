package de.tuhh.tusp.exa.massmails.dao;

import java.util.List;

import de.tuhh.tusp.exa.massmails.pojo.Receiver;
import de.tuhh.tusp.utils.BaseDao;
import lombok.Data;

public interface ReceiverDao extends BaseDao<Receiver> {

	@Data
	public class Verteiler {

		String name;
		Long year;
		String sem;
		Long count;
		Long semester;
	}

	List<Receiver> findAllByName(String name);

	List<Receiver> findAllByVerteilername(String name, Long semester);

	List<Verteiler> getVerteiler();

}
