
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
 *         &lt;element name="addressId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="notificationCategory" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
@XmlRootElement(name = "removeAddressFromNotification")
public class RemoveAddressFromNotification {

    protected long addressId;
    @XmlElement(required = true)
    protected String notificationCategory;

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
     *     {@link String }
     *     
     */
    public String getNotificationCategory() {
        return notificationCategory;
    }

    /**
     * Legt den Wert der notificationCategory-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificationCategory(String value) {
        this.notificationCategory = value;
    }

}
