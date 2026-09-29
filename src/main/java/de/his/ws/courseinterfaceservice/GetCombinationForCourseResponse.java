
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
 *         &lt;element name="courseCombinations" type="{http://www.his.de/ws/CourseInterfaceService}courseCombinationsType"/&gt;
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
    "courseCombinations"
})
@XmlRootElement(name = "getCombinationForCourseResponse")
public class GetCombinationForCourseResponse {

    @XmlElement(required = true)
    protected CourseCombinationsType courseCombinations;

    /**
     * Ruft den Wert der courseCombinations-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CourseCombinationsType }
     *     
     */
    public CourseCombinationsType getCourseCombinations() {
        return courseCombinations;
    }

    /**
     * Legt den Wert der courseCombinations-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CourseCombinationsType }
     *     
     */
    public void setCourseCombinations(CourseCombinationsType value) {
        this.courseCombinations = value;
    }

}
