
package de.his.ws.personaddressservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="personAddresses" type="{http://www.his.de/ws/PersonAddressService}PersonAddresses"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "personAddresses"
})
@XmlRootElement(name = "readAddressesByNotificationResponse")
public class ReadAddressesByNotificationResponse {

    @XmlElement(required = true)
    protected PersonAddresses personAddresses;

    /**
     * Ruft den Wert der personAddresses-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PersonAddresses }
     *     
     */
    public PersonAddresses getPersonAddresses() {
        return personAddresses;
    }

    /**
     * Legt den Wert der personAddresses-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonAddresses }
     *     
     */
    public void setPersonAddresses(PersonAddresses value) {
        this.personAddresses = value;
    }

}
