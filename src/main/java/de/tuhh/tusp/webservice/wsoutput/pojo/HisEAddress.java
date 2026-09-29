package de.tuhh.tusp.webservice.wsoutput.pojo;

import de.his.ws.personaddressservice.EmailMeta;
import de.his.ws.personaddressservice.PhoneMeta;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HisEAddress extends HisAddress {

	private EAdressType eaddressType;
	private String value;

	/**
	 * @see HisAddress
	 */
	public HisEAddress() {}

	/**
	 * @see HisAddress
	 */
	public HisEAddress(EmailMeta addr) {
		super(addr);

		eaddressType = EAdressType.value(addr.getEmail().getEaddresstype());
		addressTag = AddressTag.value(addr.getEmail().getAddresstag());
		value = addr.getEmail().getEmailValue();
	}

	/**
	 * @see HisAddress
	 */
	public HisEAddress(PhoneMeta addr) {
		super(addr);

		eaddressType = EAdressType.value(addr.getPhone().getEaddresstype());
		addressTag = AddressTag.value(addr.getPhone().getAddresstag());
		value = addr.getPhone().getPhoneNumber();

	}
}
