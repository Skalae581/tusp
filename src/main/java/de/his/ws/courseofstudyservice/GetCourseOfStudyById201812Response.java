
package de.his.ws.courseofstudyservice;

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
 *         &lt;element name="courseOfStudy" type="{http://www.his.de/ws/CourseOfStudyService}CourseOfStudy201812"/&gt;
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
    "courseOfStudy"
})
@XmlRootElement(name = "getCourseOfStudyById201812Response")
public class GetCourseOfStudyById201812Response {

    @XmlElement(required = true, nillable = true)
    protected CourseOfStudy201812 courseOfStudy;

    /**
     * Ruft den Wert der courseOfStudy-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CourseOfStudy201812 }
     *     
     */
    public CourseOfStudy201812 getCourseOfStudy() {
        return courseOfStudy;
    }

    /**
     * Legt den Wert der courseOfStudy-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CourseOfStudy201812 }
     *     
     */
    public void setCourseOfStudy(CourseOfStudy201812 value) {
        this.courseOfStudy = value;
    }

}
