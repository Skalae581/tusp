
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
 *         &lt;element name="postaddressInfos" type="{http://www.his.de/ws/PersonAddressService}postaddressInfosType"/&gt;
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
    "postaddressInfos"
})
@XmlRootElement(name = "findPostaddressResponse")
public class FindPostaddressResponse {

    @XmlElement(required = true)
    protected PostaddressInfosType postaddressInfos;

    /**
     * Ruft den Wert der postaddressInfos-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PostaddressInfosType }
     *     
     */
    public PostaddressInfosType getPostaddressInfos() {
        return postaddressInfos;
    }

    /**
     * Legt den Wert der postaddressInfos-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PostaddressInfosType }
     *     
     */
    public void setPostaddressInfos(PostaddressInfosType value) {
        this.postaddressInfos = value;
    }

}
