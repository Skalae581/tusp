
package de.his.ws.personaddressservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für HyperlinkMeta complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="HyperlinkMeta"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/PersonAddressService}AddressMeta"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="hyperlink" type="{http://www.his.de/ws/PersonAddressService}Hyperlink"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HyperlinkMeta", propOrder = {
    "hyperlink"
})
public class HyperlinkMeta
    extends AddressMeta
{

    @XmlElement(required = true)
    protected Hyperlink hyperlink;

    /**
     * Ruft den Wert der hyperlink-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Hyperlink }
     *     
     */
    public Hyperlink getHyperlink() {
        return hyperlink;
    }

    /**
     * Legt den Wert der hyperlink-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Hyperlink }
     *     
     */
    public void setHyperlink(Hyperlink value) {
        this.hyperlink = value;
    }

}
