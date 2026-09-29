package de.tuhh.tusp.webservice.wsoutput.pojo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.his.ws.personaddressservice.Postaddress;
import de.his.ws.personaddressservice.PostaddressMeta;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HisPostAddress extends HisAddress {

	private static Logger logger = LogManager.getLogger(HisPostAddress.class);

	private String postcode;
	private String street;
	private String city;
	private String addressaddition;
	private String postofficebox;
	private String company;
	private String state;
	private String country;
	private String divisinName;
	protected Boolean editable;

	/**
	 * @see HisAddress
	 */
	public HisPostAddress() {
	}

	/**
	 * @see HisAddress
	 */
	public HisPostAddress(PostaddressMeta addr) {
		super(addr);
		postcode = addr.getPostaddress().getPostcode();
		street = addr.getPostaddress().getStreet();
		city = addr.getPostaddress().getCity();
		addressaddition = addr.getPostaddress().getAddressaddition();
		postofficebox = addr.getPostaddress().getPostofficebox();
		company = addr.getPostaddress().getCompany();
		state = addr.getPostaddress().getState();
		country = addr.getPostaddress().getCountry();
		divisinName = addr.getPostaddress().getDivisionName();
		addressTag = AddressTag.value(addr.getPostaddress().getAddresstag());
	}

	/**
	 * Erzeugt ein {@link Postaddress}-Abbild der Adresse
	 *
	 * @return die Adresse
	 */
	public Postaddress asPostAddress() {

		if (super.addressTag == null) {
			super.addressTag = AddressTag.PRIVAT;
			logger.warn("Adresse ohne AddressTag wird zu private geändert! " + this);
		}

		Postaddress addr = new Postaddress();

		addr.setAddressaddition(addressaddition);
		addr.setAddresstag(super.addressTag.name().toLowerCase());
		addr.setCity(city);
		addr.setCompany(company);
		addr.setCountry(company);
		addr.setCountry(country);
		addr.setDivisionName(divisinName);
		addr.setPostcode(postcode);
		addr.setPostofficebox(postofficebox);
		addr.setState(state);
		addr.setStreet(street);

		return addr;
	}
}
