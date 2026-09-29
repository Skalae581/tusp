
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
 *         &lt;element name="postAddressOne" type="{http://www.his.de/ws/AddressService}PostAddressWithNillablePostcode"/&gt;
 *         &lt;element name="postAddressTwo" type="{http://www.his.de/ws/AddressService}PostAddressWithNillablePostcode"/&gt;
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
    "postAddressOne",
    "postAddressTwo"
})
@XmlRootElement(name = "comparePostAddressExcludeAddressTag")
public class ComparePostAddressExcludeAddressTag {

    @XmlElement(required = true)
    protected PostAddressWithNillablePostcode postAddressOne;
    @XmlElement(required = true)
    protected PostAddressWithNillablePostcode postAddressTwo;

    /**
     * Ruft den Wert der postAddressOne-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PostAddressWithNillablePostcode }
     *     
     */
    public PostAddressWithNillablePostcode getPostAddressOne() {
        return postAddressOne;
    }

    /**
     * Legt den Wert der postAddressOne-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PostAddressWithNillablePostcode }
     *     
     */
    public void setPostAddressOne(PostAddressWithNillablePostcode value) {
        this.postAddressOne = value;
    }

    /**
     * Ruft den Wert der postAddressTwo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PostAddressWithNillablePostcode }
     *     
     */
    public PostAddressWithNillablePostcode getPostAddressTwo() {
        return postAddressTwo;
    }

    /**
     * Legt den Wert der postAddressTwo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PostAddressWithNillablePostcode }
     *     
     */
    public void setPostAddressTwo(PostAddressWithNillablePostcode value) {
        this.postAddressTwo = value;
    }

}
