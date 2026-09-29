
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
 *         &lt;element name="notificationCategories" type="{http://www.his.de/ws/PersonAddressService}notificationCategoriesType"/&gt;
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
    "notificationCategories"
})
@XmlRootElement(name = "deleteAddressResponse")
public class DeleteAddressResponse {

    @XmlElement(required = true)
    protected NotificationCategoriesType notificationCategories;

    /**
     * Ruft den Wert der notificationCategories-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link NotificationCategoriesType }
     *     
     */
    public NotificationCategoriesType getNotificationCategories() {
        return notificationCategories;
    }

    /**
     * Legt den Wert der notificationCategories-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link NotificationCategoriesType }
     *     
     */
    public void setNotificationCategories(NotificationCategoriesType value) {
        this.notificationCategories = value;
    }

}
