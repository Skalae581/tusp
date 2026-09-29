
package de.his.ws.orgunitservice;

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
 *         &lt;element name="orgunits" type="{http://www.his.de/ws/OrgUnitService}orgunitsType"/&gt;
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
    "orgunits"
})
@XmlRootElement(name = "findAllVersionsByLidResponse")
public class FindAllVersionsByLidResponse {

    @XmlElement(required = true)
    protected OrgunitsType orgunits;

    /**
     * Ruft den Wert der orgunits-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link OrgunitsType }
     *     
     */
    public OrgunitsType getOrgunits() {
        return orgunits;
    }

    /**
     * Legt den Wert der orgunits-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link OrgunitsType }
     *     
     */
    public void setOrgunits(OrgunitsType value) {
        this.orgunits = value;
    }

}
