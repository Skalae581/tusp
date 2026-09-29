
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
 *         &lt;element name="placeholders" type="{http://www.his.de/ws/PersonService}placeholdersType"/&gt;
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
    "placeholders"
})
@XmlRootElement(name = "getAllPersonPlaceholdersResponse")
public class GetAllPersonPlaceholdersResponse {

    @XmlElement(required = true)
    protected PlaceholdersType placeholders;

    /**
     * Ruft den Wert der placeholders-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PlaceholdersType }
     *     
     */
    public PlaceholdersType getPlaceholders() {
        return placeholders;
    }

    /**
     * Legt den Wert der placeholders-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PlaceholdersType }
     *     
     */
    public void setPlaceholders(PlaceholdersType value) {
        this.placeholders = value;
    }

}
