
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
 *         &lt;element name="courseLinks" type="{http://www.his.de/ws/CourseInterfaceService}courseLinksType"/&gt;
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
    "courseLinks"
})
@XmlRootElement(name = "getLinksForCourseResponse")
public class GetLinksForCourseResponse {

    @XmlElement(required = true)
    protected CourseLinksType courseLinks;

    /**
     * Ruft den Wert der courseLinks-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CourseLinksType }
     *     
     */
    public CourseLinksType getCourseLinks() {
        return courseLinks;
    }

    /**
     * Legt den Wert der courseLinks-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CourseLinksType }
     *     
     */
    public void setCourseLinks(CourseLinksType value) {
        this.courseLinks = value;
    }

}
