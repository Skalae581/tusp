
package de.his.ws.personservice;

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
 *         &lt;element name="personAttributeId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "personAttributeId"
})
@XmlRootElement(name = "updatePersonAttribute202006Response")
public class UpdatePersonAttribute202006Response {

    protected long personAttributeId;

    /**
     * Ruft den Wert der personAttributeId-Eigenschaft ab.
     * 
     */
    public long getPersonAttributeId() {
        return personAttributeId;
    }

    /**
     * Legt den Wert der personAttributeId-Eigenschaft fest.
     * 
     */
    public void setPersonAttributeId(long value) {
        this.personAttributeId = value;
    }

}
