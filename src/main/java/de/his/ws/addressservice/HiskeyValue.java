
package de.his.ws.addressservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für HiskeyValue complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="HiskeyValue"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/AddressService}Value"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="hiskeyId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HiskeyValue", propOrder = {
    "hiskeyId"
})
@XmlSeeAlso({
    NotificationCategorie.class
})
public class HiskeyValue
    extends Value
{

    protected long hiskeyId;

    /**
     * Ruft den Wert der hiskeyId-Eigenschaft ab.
     * 
     */
    public long getHiskeyId() {
        return hiskeyId;
    }

    /**
     * Legt den Wert der hiskeyId-Eigenschaft fest.
     * 
     */
    public void setHiskeyId(long value) {
        this.hiskeyId = value;
    }

}
