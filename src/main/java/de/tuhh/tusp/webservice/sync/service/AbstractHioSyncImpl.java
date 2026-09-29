package de.tuhh.tusp.webservice.sync.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import de.his.ws.keyvalueservice.AbstractKeyValue;
import de.his.ws.keyvalueservice.KeyvalueService;
import de.his.ws.keyvalueservice.KeyvalueServicePortType;
import de.his.ws.keyvalueservice.ServiceFault_Exception;
import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.pojo.AbstractHioPojo;
import de.tuhh.tusp.webservice.sync.pojo.BasePojo;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

public abstract class AbstractHioSyncImpl extends WebserviceSyncImpl implements WebserviceSync {

	@Autowired
	private WebserviceFactory webserviceFactory;

	abstract protected BaseDao<?> getDao();

	/**
	 * Liefert ein leeres Objekt
	 *
	 * @return Objekt mit benötigtem Typ
	 */
	protected abstract AbstractHioPojo getEmptyObject();

	/**
	 * Gibt den Namen der ValueClass zurück
	 *
	 * @return z.B. "TermTypeValue"
	 */
	protected abstract String getValueClass();

	/**
	 * Kann idividuelle Felder setzen
	 *
	 * @param pojo
	 * @param de
	 * @param en
	 */
	protected abstract void setAdditionalValues(AbstractHioPojo pojo, AbstractKeyValue de, AbstractKeyValue en);

	@Override
	public void sync() {

		List<AbstractKeyValue> listDe;
		List<AbstractKeyValue> listEn;
		try {
			KeyvalueServicePortType port = webserviceFactory.getWebservicePort(KeyvalueService.class);
			listDe = port.getAllExtended(getValueClass(), "de").getValue();
			listEn = port.getAllExtended(getValueClass(), "en").getValue();
		}
		catch (ServiceFault_Exception e) {
			throw new RuntimeException("Konnte Termtypes nicht abrufen.", e);
		}

		List<BasePojo> serviceData = new ArrayList<>();
		for (AbstractKeyValue de : listDe) {

			AbstractHioPojo pojo = getEmptyObject();
			pojo.setId(de.getId());
			pojo.setDefaulttextDe(de.getDefaulttext());
			pojo.setLongtextDe(de.getLongtext());
			pojo.setShorttextDe(de.getShorttext());
			pojo.setSortorder(de.getSortorder() + "");
			pojo.setUniquename(de.getUniquename());
			pojo.setValid(true);
			Optional<AbstractKeyValue> en = listEn.stream().filter(x -> x.getId() == de.getId()).findFirst();

			if (en.isPresent()) {
				pojo.setDefaulttextEn(en.get().getDefaulttext());
				pojo.setLongtextEn(en.get().getLongtext());
				pojo.setShorttextEn(en.get().getShorttext());
				setAdditionalValues(pojo, de, en.get());
			}
			else {
				pojo.setDefaulttextEn(de.getDefaulttext());
				pojo.setLongtextEn(de.getLongtext());
				pojo.setShorttextEn(de.getShorttext());
				setAdditionalValues(pojo, de, de);
			}
			serviceData.add(pojo);
		}

		syncData(serviceData, getDao());
	}
}
