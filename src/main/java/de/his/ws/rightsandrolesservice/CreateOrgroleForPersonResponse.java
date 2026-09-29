
package de.his.ws.rightsandrolesservice;

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
 *         &lt;element name="orgroleId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "orgroleId"
})
@XmlRootElement(name = "createOrgroleForPersonResponse")
public class CreateOrgroleForPersonResponse {

    protected long orgroleId;

    /**
     * Ruft den Wert der orgroleId-Eigenschaft ab.
     * 
     */
    public long getOrgroleId() {
        return orgroleId;
    }

    /**
     * Legt den Wert der orgroleId-Eigenschaft fest.
     * 
     */
    public void setOrgroleId(long value) {
        this.orgroleId = value;
    }

}
