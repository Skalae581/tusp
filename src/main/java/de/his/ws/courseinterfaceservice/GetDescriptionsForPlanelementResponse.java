
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
 *         &lt;element name="courseDescriptionPlanelement" type="{http://www.his.de/ws/CourseInterfaceService}CourseDescriptionPlanelement"/&gt;
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
    "courseDescriptionPlanelement"
})
@XmlRootElement(name = "getDescriptionsForPlanelementResponse")
public class GetDescriptionsForPlanelementResponse {

    @XmlElement(required = true)
    protected CourseDescriptionPlanelement courseDescriptionPlanelement;

    /**
     * Ruft den Wert der courseDescriptionPlanelement-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CourseDescriptionPlanelement }
     *     
     */
    public CourseDescriptionPlanelement getCourseDescriptionPlanelement() {
        return courseDescriptionPlanelement;
    }

    /**
     * Legt den Wert der courseDescriptionPlanelement-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CourseDescriptionPlanelement }
     *     
     */
    public void setCourseDescriptionPlanelement(CourseDescriptionPlanelement value) {
        this.courseDescriptionPlanelement = value;
    }

}
