
package de.his.ws.addressservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="eAddressTypeId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "eAddressTypeId"
})
@XmlRootElement(name = "readEAddressTypeIdsForEmailResponse")
public class ReadEAddressTypeIdsForEmailResponse {

    protected long eAddressTypeId;

    /**
     * Ruft den Wert der eAddressTypeId-Eigenschaft ab.
     * 
     */
    public long getEAddressTypeId() {
        return eAddressTypeId;
    }

    /**
     * Legt den Wert der eAddressTypeId-Eigenschaft fest.
     * 
     */
    public void setEAddressTypeId(long value) {
        this.eAddressTypeId = value;
    }

}
