
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
 *         &lt;element name="eAdressIds" type="{http://www.his.de/ws/AddressService}eAdressIdsType"/&gt;
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
    "eAdressIds"
})
@XmlRootElement(name = "saveEAddressesResponse")
public class SaveEAddressesResponse {

    @XmlElement(required = true)
    protected EAdressIdsType eAdressIds;

    /**
     * Ruft den Wert der eAdressIds-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EAdressIdsType }
     *     
     */
    public EAdressIdsType getEAdressIds() {
        return eAdressIds;
    }

    /**
     * Legt den Wert der eAdressIds-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EAdressIdsType }
     *     
     */
    public void setEAdressIds(EAdressIdsType value) {
        this.eAdressIds = value;
    }

}
