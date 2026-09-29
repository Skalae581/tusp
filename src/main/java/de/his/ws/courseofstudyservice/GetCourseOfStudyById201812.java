
package de.his.ws.courseofstudyservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="courseOfStudyId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "courseOfStudyId"
})
@XmlRootElement(name = "getCourseOfStudyById201812")
public class GetCourseOfStudyById201812 {

    protected long courseOfStudyId;

    /**
     * Ruft den Wert der courseOfStudyId-Eigenschaft ab.
     * 
     */
    public long getCourseOfStudyId() {
        return courseOfStudyId;
    }

    /**
     * Legt den Wert der courseOfStudyId-Eigenschaft fest.
     * 
     */
    public void setCourseOfStudyId(long value) {
        this.courseOfStudyId = value;
    }

}
