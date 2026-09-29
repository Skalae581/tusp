
package de.his.ws.addressservice;

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
 *         &lt;element name="postAdressesWithNotification" type="{http://www.his.de/ws/AddressService}postAdressesWithNotificationType"/&gt;
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
    "postAdressesWithNotification"
})
@XmlRootElement(name = "readPostAddressesWithNotificationResponse")
public class ReadPostAddressesWithNotificationResponse {

    @XmlElement(required = true)
    protected PostAdressesWithNotificationType postAdressesWithNotification;

    /**
     * Ruft den Wert der postAdressesWithNotification-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PostAdressesWithNotificationType }
     *     
     */
    public PostAdressesWithNotificationType getPostAdressesWithNotification() {
        return postAdressesWithNotification;
    }

    /**
     * Legt den Wert der postAdressesWithNotification-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PostAdressesWithNotificationType }
     *     
     */
    public void setPostAdressesWithNotification(PostAdressesWithNotificationType value) {
        this.postAdressesWithNotification = value;
    }

}
