
package de.his.ws.facilityservice;

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
 *         &lt;element name="building" type="{http://www.his.de/ws/FacilityService}Building202006"/&gt;
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
    "building"
})
@XmlRootElement(name = "readBuilding202006Response")
public class ReadBuilding202006Response {

    @XmlElement(required = true, nillable = true)
    protected Building202006 building;

    /**
     * Ruft den Wert der building-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Building202006 }
     *     
     */
    public Building202006 getBuilding() {
        return building;
    }

    /**
     * Legt den Wert der building-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Building202006 }
     *     
     */
    public void setBuilding(Building202006 value) {
        this.building = value;
    }

}
