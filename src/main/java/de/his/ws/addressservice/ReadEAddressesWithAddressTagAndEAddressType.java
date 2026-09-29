
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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="objekttype" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="addressTagId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="eAddressTypeIds" type="{http://www.his.de/ws/AddressService}eAddressTypeIdsType"/&gt;
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
    "id",
    "objekttype",
    "addressTagId",
    "eAddressTypeIds"
})
@XmlRootElement(name = "readEAddressesWithAddressTagAndEAddressType")
public class ReadEAddressesWithAddressTagAndEAddressType {

    protected long id;
    @XmlElement(required = true)
    protected String objekttype;
    protected long addressTagId;
    @XmlElement(required = true)
    protected EAddressTypeIdsType eAddressTypeIds;

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der objekttype-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjekttype() {
        return objekttype;
    }

    /**
     * Legt den Wert der objekttype-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjekttype(String value) {
        this.objekttype = value;
    }

    /**
     * Ruft den Wert der addressTagId-Eigenschaft ab.
     * 
     */
    public long getAddressTagId() {
        return addressTagId;
    }

    /**
     * Legt den Wert der addressTagId-Eigenschaft fest.
     * 
     */
    public void setAddressTagId(long value) {
        this.addressTagId = value;
    }

    /**
     * Ruft den Wert der eAddressTypeIds-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EAddressTypeIdsType }
     *     
     */
    public EAddressTypeIdsType getEAddressTypeIds() {
        return eAddressTypeIds;
    }

    /**
     * Legt den Wert der eAddressTypeIds-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EAddressTypeIdsType }
     *     
     */
    public void setEAddressTypeIds(EAddressTypeIdsType value) {
        this.eAddressTypeIds = value;
    }

}
