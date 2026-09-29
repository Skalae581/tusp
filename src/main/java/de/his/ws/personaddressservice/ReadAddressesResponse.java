
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
 *         &lt;element name="personAddressesByNotifications" type="{http://www.his.de/ws/PersonAddressService}personAddressesByNotificationsType"/&gt;
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
    "personAddressesByNotifications"
})
@XmlRootElement(name = "readAddressesResponse")
public class ReadAddressesResponse {

    @XmlElement(required = true)
    protected PersonAddressesByNotificationsType personAddressesByNotifications;

    /**
     * Ruft den Wert der personAddressesByNotifications-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PersonAddressesByNotificationsType }
     *     
     */
    public PersonAddressesByNotificationsType getPersonAddressesByNotifications() {
        return personAddressesByNotifications;
    }

    /**
     * Legt den Wert der personAddressesByNotifications-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonAddressesByNotificationsType }
     *     
     */
    public void setPersonAddressesByNotifications(PersonAddressesByNotificationsType value) {
        this.personAddressesByNotifications = value;
    }

}
