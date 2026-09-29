
package de.his.ws.personaddressservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für personAddressesByNotificationsType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="personAddressesByNotificationsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="personAddressesByNotification" type="{http://www.his.de/ws/PersonAddressService}PersonAddressesByNotification" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "personAddressesByNotificationsType", propOrder = {
    "personAddressesByNotification"
})
public class PersonAddressesByNotificationsType {

    protected List<PersonAddressesByNotification> personAddressesByNotification;

    /**
     * Gets the value of the personAddressesByNotification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the personAddressesByNotification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPersonAddressesByNotification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PersonAddressesByNotification }
     * 
     * 
     */
    public List<PersonAddressesByNotification> getPersonAddressesByNotification() {
        if (personAddressesByNotification == null) {
            personAddressesByNotification = new ArrayList<PersonAddressesByNotification>();
        }
        return this.personAddressesByNotification;
    }

}
