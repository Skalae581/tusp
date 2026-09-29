
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
 *         &lt;element name="eAddress" type="{http://www.his.de/ws/AddressService}EAddress"/&gt;
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
    "eAddress"
})
@XmlRootElement(name = "readEAddressResponse")
public class ReadEAddressResponse {

    @XmlElement(required = true)
    protected EAddress eAddress;

    /**
     * Ruft den Wert der eAddress-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EAddress }
     *     
     */
    public EAddress getEAddress() {
        return eAddress;
    }

    /**
     * Legt den Wert der eAddress-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EAddress }
     *     
     */
    public void setEAddress(EAddress value) {
        this.eAddress = value;
    }

}
