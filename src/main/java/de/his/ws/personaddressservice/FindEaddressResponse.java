
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
 *         &lt;element name="eaddressInfos" type="{http://www.his.de/ws/PersonAddressService}eaddressInfosType"/&gt;
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
    "eaddressInfos"
})
@XmlRootElement(name = "findEaddressResponse")
public class FindEaddressResponse {

    @XmlElement(required = true)
    protected EaddressInfosType eaddressInfos;

    /**
     * Ruft den Wert der eaddressInfos-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EaddressInfosType }
     *     
     */
    public EaddressInfosType getEaddressInfos() {
        return eaddressInfos;
    }

    /**
     * Legt den Wert der eaddressInfos-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EaddressInfosType }
     *     
     */
    public void setEaddressInfos(EaddressInfosType value) {
        this.eaddressInfos = value;
    }

}
