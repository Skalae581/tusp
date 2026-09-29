
package de.his.ws.personaddressservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für EaddressInfo complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="EaddressInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="addressId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="personId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="eaddressType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="eaddressValue" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EaddressInfo", propOrder = {
    "addressId",
    "personId",
    "eaddressType",
    "eaddressValue"
})
public class EaddressInfo {

    protected long addressId;
    protected long personId;
    @XmlElement(required = true)
    protected String eaddressType;
    @XmlElement(required = true, nillable = true)
    protected String eaddressValue;

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
     * Ruft den Wert der eaddressType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEaddressType() {
        return eaddressType;
    }

    /**
     * Legt den Wert der eaddressType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEaddressType(String value) {
        this.eaddressType = value;
    }

    /**
     * Ruft den Wert der eaddressValue-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEaddressValue() {
        return eaddressValue;
    }

    /**
     * Legt den Wert der eaddressValue-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEaddressValue(String value) {
        this.eaddressValue = value;
    }

}
