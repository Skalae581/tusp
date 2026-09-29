
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
 *         &lt;element name="floors" type="{http://www.his.de/ws/FacilityService}floorsType"/&gt;
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
    "floors"
})
@XmlRootElement(name = "readAllFloorsOfBuildingResponse")
public class ReadAllFloorsOfBuildingResponse {

    @XmlElement(required = true)
    protected FloorsType floors;

    /**
     * Ruft den Wert der floors-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link FloorsType }
     *     
     */
    public FloorsType getFloors() {
        return floors;
    }

    /**
     * Legt den Wert der floors-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link FloorsType }
     *     
     */
    public void setFloors(FloorsType value) {
        this.floors = value;
    }

}
