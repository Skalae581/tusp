
package de.his.ws.personaddressservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für PostaddressMeta complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PostaddressMeta"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/PersonAddressService}AddressMeta"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="postaddress" type="{http://www.his.de/ws/PersonAddressService}Postaddress"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PostaddressMeta", propOrder = {
    "postaddress"
})
public class PostaddressMeta
    extends AddressMeta
{

    @XmlElement(required = true)
    protected Postaddress postaddress;

    /**
     * Ruft den Wert der postaddress-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Postaddress }
     *     
     */
    public Postaddress getPostaddress() {
        return postaddress;
    }

    /**
     * Legt den Wert der postaddress-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Postaddress }
     *     
     */
    public void setPostaddress(Postaddress value) {
        this.postaddress = value;
    }

}
