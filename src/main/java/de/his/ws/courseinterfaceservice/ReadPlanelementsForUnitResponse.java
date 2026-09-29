
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
 *         &lt;element name="planelements" type="{http://www.his.de/ws/CourseInterfaceService}planelementsType"/&gt;
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
    "planelements"
})
@XmlRootElement(name = "readPlanelementsForUnitResponse")
public class ReadPlanelementsForUnitResponse {

    @XmlElement(required = true)
    protected PlanelementsType planelements;

    /**
     * Ruft den Wert der planelements-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PlanelementsType }
     *     
     */
    public PlanelementsType getPlanelements() {
        return planelements;
    }

    /**
     * Legt den Wert der planelements-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PlanelementsType }
     *     
     */
    public void setPlanelements(PlanelementsType value) {
        this.planelements = value;
    }

}
