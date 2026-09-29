
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
 *         &lt;element name="personAttributeTypes" type="{http://www.his.de/ws/PersonService}personAttributeTypesType"/&gt;
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
    "personAttributeTypes"
})
@XmlRootElement(name = "getAllPersonAttributeTypesResponse")
public class GetAllPersonAttributeTypesResponse {

    @XmlElement(required = true)
    protected PersonAttributeTypesType personAttributeTypes;

    /**
     * Ruft den Wert der personAttributeTypes-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PersonAttributeTypesType }
     *     
     */
    public PersonAttributeTypesType getPersonAttributeTypes() {
        return personAttributeTypes;
    }

    /**
     * Legt den Wert der personAttributeTypes-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonAttributeTypesType }
     *     
     */
    public void setPersonAttributeTypes(PersonAttributeTypesType value) {
        this.personAttributeTypes = value;
    }

}
