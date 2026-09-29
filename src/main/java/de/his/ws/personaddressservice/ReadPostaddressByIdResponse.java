
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
 *         &lt;element name="postaddress" type="{http://www.his.de/ws/PersonAddressService}PostaddressMeta"/&gt;
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
    "postaddress"
})
@XmlRootElement(name = "readPostaddressByIdResponse")
public class ReadPostaddressByIdResponse {

    @XmlElement(required = true, nillable = true)
    protected PostaddressMeta postaddress;

    /**
     * Ruft den Wert der postaddress-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PostaddressMeta }
     *     
     */
    public PostaddressMeta getPostaddress() {
        return postaddress;
    }

    /**
     * Legt den Wert der postaddress-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PostaddressMeta }
     *     
     */
    public void setPostaddress(PostaddressMeta value) {
        this.postaddress = value;
    }

}
