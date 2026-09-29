
package de.his.ws.personaddressservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für AddressConfiguration complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AddressConfiguration"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="notificationCategory" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="addressType" type="{http://www.his.de/ws/PersonAddressService}addressTypeType"/&gt;
 *         &lt;element name="upperLimit" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="required" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="assignable" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressConfiguration", propOrder = {
    "notificationCategory",
    "addressType",
    "upperLimit",
    "required",
    "assignable"
})
public class AddressConfiguration {

    @XmlElement(required = true)
    protected String notificationCategory;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected AddressTypeType addressType;
    @XmlElement(required = true)
    protected BigInteger upperLimit;
    protected boolean required;
    protected boolean assignable;

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
     * Ruft den Wert der addressType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AddressTypeType }
     *     
     */
    public AddressTypeType getAddressType() {
        return addressType;
    }

    /**
     * Legt den Wert der addressType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressTypeType }
     *     
     */
    public void setAddressType(AddressTypeType value) {
        this.addressType = value;
    }

    /**
     * Ruft den Wert der upperLimit-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getUpperLimit() {
        return upperLimit;
    }

    /**
     * Legt den Wert der upperLimit-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setUpperLimit(BigInteger value) {
        this.upperLimit = value;
    }

    /**
     * Ruft den Wert der required-Eigenschaft ab.
     * 
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * Legt den Wert der required-Eigenschaft fest.
     * 
     */
    public void setRequired(boolean value) {
        this.required = value;
    }

    /**
     * Ruft den Wert der assignable-Eigenschaft ab.
     * 
     */
    public boolean isAssignable() {
        return assignable;
    }

    /**
     * Legt den Wert der assignable-Eigenschaft fest.
     * 
     */
    public void setAssignable(boolean value) {
        this.assignable = value;
    }

}
