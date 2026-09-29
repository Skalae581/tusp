
package de.his.ws.addressservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für EAddress complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="EAddress"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/AddressService}Address"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="eaddresstypeId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="eaddress" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EAddress", propOrder = {
    "eaddresstypeId",
    "eaddress"
})
@XmlSeeAlso({
    Phone.class,
    Messenger.class,
    EMail.class,
    Hyperlink.class
})
public abstract class EAddress
    extends Address
{

    protected long eaddresstypeId;
    @XmlElement(required = true)
    protected String eaddress;

    /**
     * Ruft den Wert der eaddresstypeId-Eigenschaft ab.
     * 
     */
    public long getEaddresstypeId() {
        return eaddresstypeId;
    }

    /**
     * Legt den Wert der eaddresstypeId-Eigenschaft fest.
     * 
     */
    public void setEaddresstypeId(long value) {
        this.eaddresstypeId = value;
    }

    /**
     * Ruft den Wert der eaddress-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEaddress() {
        return eaddress;
    }

    /**
     * Legt den Wert der eaddress-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEaddress(String value) {
        this.eaddress = value;
    }

}
