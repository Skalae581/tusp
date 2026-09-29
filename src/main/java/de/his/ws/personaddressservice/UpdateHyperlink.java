
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
 *         &lt;element name="hyperlink" type="{http://www.his.de/ws/PersonAddressService}Hyperlink"/&gt;
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
    "hyperlink"
})
@XmlRootElement(name = "updateHyperlink")
public class UpdateHyperlink {

    protected long addressId;
    @XmlElement(required = true)
    protected Hyperlink hyperlink;

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
     * Ruft den Wert der hyperlink-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Hyperlink }
     *     
     */
    public Hyperlink getHyperlink() {
        return hyperlink;
    }

    /**
     * Legt den Wert der hyperlink-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Hyperlink }
     *     
     */
    public void setHyperlink(Hyperlink value) {
        this.hyperlink = value;
    }

}
