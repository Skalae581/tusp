
package de.his.ws.studentservice;

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
 *         &lt;element name="student" type="{http://www.his.de/ws/StudentService}StudentExisting"/&gt;
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
    "student"
})
@XmlRootElement(name = "readStudentWithCoursesOfStudyByStudentId70Response")
public class ReadStudentWithCoursesOfStudyByStudentId70Response {

    @XmlElement(required = true, nillable = true)
    protected StudentExisting student;

    /**
     * Ruft den Wert der student-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link StudentExisting }
     *     
     */
    public StudentExisting getStudent() {
        return student;
    }

    /**
     * Legt den Wert der student-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link StudentExisting }
     *     
     */
    public void setStudent(StudentExisting value) {
        this.student = value;
    }

}
