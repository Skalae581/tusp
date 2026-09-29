
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
 *         &lt;element name="eAdresses" type="{http://www.his.de/ws/AddressService}eAdressesType"/&gt;
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
    "eAdresses"
})
@XmlRootElement(name = "readEAddressesWithAddressTagAndEAddressTypeResponse")
public class ReadEAddressesWithAddressTagAndEAddressTypeResponse {

    @XmlElement(required = true)
    protected EAdressesType eAdresses;

    /**
     * Ruft den Wert der eAdresses-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EAdressesType }
     *     
     */
    public EAdressesType getEAdresses() {
        return eAdresses;
    }

    /**
     * Legt den Wert der eAdresses-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EAdressesType }
     *     
     */
    public void setEAdresses(EAdressesType value) {
        this.eAdresses = value;
    }

}
