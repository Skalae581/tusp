package de.tuhh.tusp.app.precheck.dao;

import java.util.List;
import java.util.Map;

import org.jooq.Record1;
import org.jooq.TableField;
import org.jooq.generated.tables.records.PrecheckInteressentRecord;

import de.tuhh.tusp.app.precheck.pojo.Interessent;
import de.tuhh.tusp.utils.BaseDao;
import lombok.Data;
import lombok.EqualsAndHashCode;

public interface InteressentDao extends BaseDao<Interessent> {

	@Data
	@EqualsAndHashCode(callSuper = true)
	public class ExtendedInteressent extends Interessent {

		private String genderName;
		private String birthCountry;
		private String studyCountry;
		private String stg_tuhh;
		private String statusName;
	}

	List<ExtendedInteressent> findAllFilteredExtended(String countryId, String statusId, String stgId, String done,
			String zwilinge, String search, int elementList, int currentPage,
			TableField<PrecheckInteressentRecord, ?> order, boolean doubles, boolean doubles_mail);

	List<Map<String, Object>> findAllStatuses();

	Record1<String> findCountryNameById(Long id);

	Record1<String> findGenderNameById(Integer id);

	List<Map<String, Object>> findGenderNames();

	boolean getCodeIfExists(String code);

}
