
package de.his.ws.addressservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für PostAddressWithNotification complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PostAddressWithNotification"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/AddressService}PostAddressWithNillablePostcode"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="notificationCategories" type="{http://www.his.de/ws/AddressService}notificationCategoriesType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PostAddressWithNotification", propOrder = {
    "notificationCategories"
})
public class PostAddressWithNotification
    extends PostAddressWithNillablePostcode
{

    @XmlElement(required = true, nillable = true)
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
