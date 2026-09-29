
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
 *         &lt;element name="personId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="personAttributeDto" type="{http://www.his.de/ws/PersonService}PersonAttributeDto"/&gt;
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
    "personId",
    "personAttributeDto"
})
@XmlRootElement(name = "updatePersonAttributeForPerson")
public class UpdatePersonAttributeForPerson {

    protected long personId;
    @XmlElement(required = true)
    protected PersonAttributeDto personAttributeDto;

    /**
     * Ruft den Wert der personId-Eigenschaft ab.
     * 
     */
    public long getPersonId() {
        return personId;
    }

    /**
     * Legt den Wert der personId-Eigenschaft fest.
     * 
     */
    public void setPersonId(long value) {
        this.personId = value;
    }

    /**
     * Ruft den Wert der personAttributeDto-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PersonAttributeDto }
     *     
     */
    public PersonAttributeDto getPersonAttributeDto() {
        return personAttributeDto;
    }

    /**
     * Legt den Wert der personAttributeDto-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonAttributeDto }
     *     
     */
    public void setPersonAttributeDto(PersonAttributeDto value) {
        this.personAttributeDto = value;
    }

}
