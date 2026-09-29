
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
 *         &lt;element name="buildings" type="{http://www.his.de/ws/FacilityService}buildingsType"/&gt;
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
    "buildings"
})
@XmlRootElement(name = "readAllBuildingsOfCampusResponse")
public class ReadAllBuildingsOfCampusResponse {

    @XmlElement(required = true)
    protected BuildingsType buildings;

    /**
     * Ruft den Wert der buildings-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BuildingsType }
     *     
     */
    public BuildingsType getBuildings() {
        return buildings;
    }

    /**
     * Legt den Wert der buildings-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BuildingsType }
     *     
     */
    public void setBuildings(BuildingsType value) {
        this.buildings = value;
    }

}
