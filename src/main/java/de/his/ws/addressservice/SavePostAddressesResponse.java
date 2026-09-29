
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
 *         &lt;element name="postAddressIds" type="{http://www.his.de/ws/AddressService}postAddressIdsType"/&gt;
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
    "postAddressIds"
})
@XmlRootElement(name = "savePostAddressesResponse")
public class SavePostAddressesResponse {

    @XmlElement(required = true)
    protected PostAddressIdsType postAddressIds;

    /**
     * Ruft den Wert der postAddressIds-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PostAddressIdsType }
     *     
     */
    public PostAddressIdsType getPostAddressIds() {
        return postAddressIds;
    }

    /**
     * Legt den Wert der postAddressIds-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PostAddressIdsType }
     *     
     */
    public void setPostAddressIds(PostAddressIdsType value) {
        this.postAddressIds = value;
    }

}
