
package de.his.ws.facilityservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für Campus complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Campus"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/FacilityService}AbstractBuilding"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="buildingCount" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Campus", propOrder = {
    "buildingCount"
})
@XmlSeeAlso({
    Campus202106 .class
})
public class Campus
    extends AbstractBuilding
{

    @XmlElement(required = true, nillable = true)
    protected BigInteger buildingCount;

    /**
     * Ruft den Wert der buildingCount-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBuildingCount() {
        return buildingCount;
    }

    /**
     * Legt den Wert der buildingCount-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBuildingCount(BigInteger value) {
        this.buildingCount = value;
    }

}
