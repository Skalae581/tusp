
package de.his.ws.personservice;

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
 *         &lt;element name="personAttributes202006" type="{http://www.his.de/ws/PersonService}personAttributes202006Type"/&gt;
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
    "personAttributes202006"
})
@XmlRootElement(name = "readPersonAttributes202006Response")
public class ReadPersonAttributes202006Response {

    @XmlElement(required = true)
    protected PersonAttributes202006Type personAttributes202006;

    /**
     * Ruft den Wert der personAttributes202006-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PersonAttributes202006Type }
     *     
     */
    public PersonAttributes202006Type getPersonAttributes202006() {
        return personAttributes202006;
    }

    /**
     * Legt den Wert der personAttributes202006-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonAttributes202006Type }
     *     
     */
    public void setPersonAttributes202006(PersonAttributes202006Type value) {
        this.personAttributes202006 = value;
    }

}
