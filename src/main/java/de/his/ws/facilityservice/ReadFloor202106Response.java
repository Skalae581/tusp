
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
 *         &lt;element name="floor" type="{http://www.his.de/ws/FacilityService}Floor202106"/&gt;
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
    "floor"
})
@XmlRootElement(name = "readFloor202106Response")
public class ReadFloor202106Response {

    @XmlElement(required = true)
    protected Floor202106 floor;

    /**
     * Ruft den Wert der floor-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Floor202106 }
     *     
     */
    public Floor202106 getFloor() {
        return floor;
    }

    /**
     * Legt den Wert der floor-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Floor202106 }
     *     
     */
    public void setFloor(Floor202106 value) {
        this.floor = value;
    }

}
