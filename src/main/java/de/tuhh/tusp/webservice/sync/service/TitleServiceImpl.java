package de.tuhh.tusp.webservice.sync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.keyvalueservice.AbstractKeyValue;
import de.his.ws.keyvalueservice.TitleValue;
import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.dao.TitleDao;
import de.tuhh.tusp.webservice.sync.pojo.AbstractHioPojo;
import de.tuhh.tusp.webservice.sync.pojo.Title;

@Component
public class TitleServiceImpl extends AbstractHioSyncImpl implements TitleService {

	@Autowired
	protected TitleDao dao;

	@Override
	protected BaseDao<?> getDao() {

		return dao;
	}

	@Override
	protected AbstractHioPojo getEmptyObject() {

		return new Title();
	}

	@Override
	protected String getValueClass() {

		return "TitleValue";
	}

	@Override
	public int orderNum() {

		return 5;
	}

	@Override
	protected void setAdditionalValues(AbstractHioPojo pojo, AbstractKeyValue de, AbstractKeyValue en) {

		Title d = (Title) pojo;
		TitleValue titleDe = (TitleValue) de;
		TitleValue titleEn = (TitleValue) en;
		d.setLettersalutationDe(titleDe.getLettersalutation());
		d.setLettersalutationFemaleDe(titleDe.getLettersalutationFemale());
		d.setDefaulttextFemaleDe(titleDe.getDefaulttextFemale());
		d.setLongtextFemaleDe(titleDe.getLongtextFemale());
		d.setShorttextFemaleDe(titleDe.getShorttextFemale());
		d.setDefaulttextFemaleEn(titleEn.getDefaulttextFemale());
		d.setLongtextFemaleEn(titleEn.getLongtextFemale());
		d.setShorttextFemaleEn(titleEn.getShorttextFemale());
		d.setLettersalutationEn(titleEn.getLettersalutation());
		d.setLettersalutationFemaleEn(titleEn.getLettersalutationFemale());

	}

}
