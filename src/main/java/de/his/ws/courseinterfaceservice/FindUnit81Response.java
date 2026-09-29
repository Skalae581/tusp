
package de.his.ws.courseinterfaceservice;

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
 *         &lt;element name="unitIds" type="{http://www.his.de/ws/CourseInterfaceService}unitIdsType"/&gt;
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
    "unitIds"
})
@XmlRootElement(name = "findUnit81Response")
public class FindUnit81Response {

    @XmlElement(required = true)
    protected UnitIdsType unitIds;

    /**
     * Ruft den Wert der unitIds-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link UnitIdsType }
     *     
     */
    public UnitIdsType getUnitIds() {
        return unitIds;
    }

    /**
     * Legt den Wert der unitIds-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitIdsType }
     *     
     */
    public void setUnitIds(UnitIdsType value) {
        this.unitIds = value;
    }

}
