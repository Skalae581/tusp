
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
 *         &lt;element name="personAttributeDtos" type="{http://www.his.de/ws/PersonService}personAttributeDtosType"/&gt;
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
    "personAttributeDtos"
})
@XmlRootElement(name = "readAllPersonAttributesFromPersonResponse")
public class ReadAllPersonAttributesFromPersonResponse {

    @XmlElement(required = true)
    protected PersonAttributeDtosType personAttributeDtos;

    /**
     * Ruft den Wert der personAttributeDtos-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PersonAttributeDtosType }
     *     
     */
    public PersonAttributeDtosType getPersonAttributeDtos() {
        return personAttributeDtos;
    }

    /**
     * Legt den Wert der personAttributeDtos-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonAttributeDtosType }
     *     
     */
    public void setPersonAttributeDtos(PersonAttributeDtosType value) {
        this.personAttributeDtos = value;
    }

}
