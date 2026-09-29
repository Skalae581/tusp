
package de.his.ws.personaddressservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für PersonAddressesByNotification complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PersonAddressesByNotification"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="notificationCategory" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="postaddresses" type="{http://www.his.de/ws/PersonAddressService}postaddressesType" minOccurs="0"/&gt;
 *         &lt;element name="emails" type="{http://www.his.de/ws/PersonAddressService}emailsType" minOccurs="0"/&gt;
 *         &lt;element name="phones" type="{http://www.his.de/ws/PersonAddressService}phonesType" minOccurs="0"/&gt;
 *         &lt;element name="messengers" type="{http://www.his.de/ws/PersonAddressService}messengersType" minOccurs="0"/&gt;
 *         &lt;element name="hyperlinks" type="{http://www.his.de/ws/PersonAddressService}hyperlinksType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonAddressesByNotification", propOrder = {
    "notificationCategory",
    "postaddresses",
    "emails",
    "phones",
    "messengers",
    "hyperlinks"
})
public class PersonAddressesByNotification {

    @XmlElement(required = true)
    protected String notificationCategory;
    protected PostaddressesType postaddresses;
    protected EmailsType emails;
    protected PhonesType phones;
    protected MessengersType messengers;
    protected HyperlinksType hyperlinks;

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
     * Ruft den Wert der postaddresses-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PostaddressesType }
     *     
     */
    public PostaddressesType getPostaddresses() {
        return postaddresses;
    }

    /**
     * Legt den Wert der postaddresses-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PostaddressesType }
     *     
     */
    public void setPostaddresses(PostaddressesType value) {
        this.postaddresses = value;
    }

    /**
     * Ruft den Wert der emails-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EmailsType }
     *     
     */
    public EmailsType getEmails() {
        return emails;
    }

    /**
     * Legt den Wert der emails-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EmailsType }
     *     
     */
    public void setEmails(EmailsType value) {
        this.emails = value;
    }

    /**
     * Ruft den Wert der phones-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PhonesType }
     *     
     */
    public PhonesType getPhones() {
        return phones;
    }

    /**
     * Legt den Wert der phones-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PhonesType }
     *     
     */
    public void setPhones(PhonesType value) {
        this.phones = value;
    }

    /**
     * Ruft den Wert der messengers-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link MessengersType }
     *     
     */
    public MessengersType getMessengers() {
        return messengers;
    }

    /**
     * Legt den Wert der messengers-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link MessengersType }
     *     
     */
    public void setMessengers(MessengersType value) {
        this.messengers = value;
    }

    /**
     * Ruft den Wert der hyperlinks-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link HyperlinksType }
     *     
     */
    public HyperlinksType getHyperlinks() {
        return hyperlinks;
    }

    /**
     * Legt den Wert der hyperlinks-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link HyperlinksType }
     *     
     */
    public void setHyperlinks(HyperlinksType value) {
        this.hyperlinks = value;
    }

}
