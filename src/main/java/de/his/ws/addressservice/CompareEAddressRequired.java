
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
 *         &lt;element name="eAddressOne" type="{http://www.his.de/ws/AddressService}EAddress"/&gt;
 *         &lt;element name="eAddressTwo" type="{http://www.his.de/ws/AddressService}EAddress"/&gt;
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
    "eAddressOne",
    "eAddressTwo"
})
@XmlRootElement(name = "compareEAddressRequired")
public class CompareEAddressRequired {

    @XmlElement(required = true)
    protected EAddress eAddressOne;
    @XmlElement(required = true)
    protected EAddress eAddressTwo;

    /**
     * Ruft den Wert der eAddressOne-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EAddress }
     *     
     */
    public EAddress getEAddressOne() {
        return eAddressOne;
    }

    /**
     * Legt den Wert der eAddressOne-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EAddress }
     *     
     */
    public void setEAddressOne(EAddress value) {
        this.eAddressOne = value;
    }

    /**
     * Ruft den Wert der eAddressTwo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EAddress }
     *     
     */
    public EAddress getEAddressTwo() {
        return eAddressTwo;
    }

    /**
     * Legt den Wert der eAddressTwo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EAddress }
     *     
     */
    public void setEAddressTwo(EAddress value) {
        this.eAddressTwo = value;
    }

}
