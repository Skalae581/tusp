
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
 *         &lt;element name="courseDescription" type="{http://www.his.de/ws/CourseInterfaceService}CourseDescription"/&gt;
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
    "courseDescription"
})
@XmlRootElement(name = "getDescriptionsForUnitResponse")
public class GetDescriptionsForUnitResponse {

    @XmlElement(required = true)
    protected CourseDescription courseDescription;

    /**
     * Ruft den Wert der courseDescription-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CourseDescription }
     *     
     */
    public CourseDescription getCourseDescription() {
        return courseDescription;
    }

    /**
     * Legt den Wert der courseDescription-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CourseDescription }
     *     
     */
    public void setCourseDescription(CourseDescription value) {
        this.courseDescription = value;
    }

}
