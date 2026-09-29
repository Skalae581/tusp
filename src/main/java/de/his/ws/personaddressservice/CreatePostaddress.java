
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
 *         &lt;element name="personId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="notificationCategory" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="postaddress" type="{http://www.his.de/ws/PersonAddressService}Postaddress"/&gt;
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
    "personId",
    "notificationCategory",
    "postaddress"
})
@XmlRootElement(name = "createPostaddress")
public class CreatePostaddress {

    protected long personId;
    @XmlElement(required = true)
    protected String notificationCategory;
    @XmlElement(required = true)
    protected Postaddress postaddress;

    /**
     * Ruft den Wert der personId-Eigenschaft ab.
     * 
     */
    public long getPersonId() {
        return personId;
    }

    /**
     * Legt den Wert der personId-Eigenschaft fest.
     * 
     */
    public void setPersonId(long value) {
        this.personId = value;
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

    /**
     * Ruft den Wert der postaddress-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Postaddress }
     *     
     */
    public Postaddress getPostaddress() {
        return postaddress;
    }

    /**
     * Legt den Wert der postaddress-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Postaddress }
     *     
     */
    public void setPostaddress(Postaddress value) {
        this.postaddress = value;
    }

}
