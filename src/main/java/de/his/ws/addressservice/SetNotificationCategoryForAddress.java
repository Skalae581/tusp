
package de.his.ws.addressservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="addressId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="notificationCategory" type="{http://www.his.de/ws/AddressService}notificationCategoryType"/&gt;
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
    "addressId",
    "notificationCategory"
})
@XmlRootElement(name = "setNotificationCategoryForAddress")
public class SetNotificationCategoryForAddress {

    protected long addressId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected NotificationCategoryType notificationCategory;

    /**
     * Ruft den Wert der addressId-Eigenschaft ab.
     * 
     */
    public long getAddressId() {
        return addressId;
    }

    /**
     * Legt den Wert der addressId-Eigenschaft fest.
     * 
     */
    public void setAddressId(long value) {
        this.addressId = value;
    }

    /**
     * Ruft den Wert der notificationCategory-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link NotificationCategoryType }
     *     
     */
    public NotificationCategoryType getNotificationCategory() {
        return notificationCategory;
    }

    /**
     * Legt den Wert der notificationCategory-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link NotificationCategoryType }
     *     
     */
    public void setNotificationCategory(NotificationCategoryType value) {
        this.notificationCategory = value;
    }

}
