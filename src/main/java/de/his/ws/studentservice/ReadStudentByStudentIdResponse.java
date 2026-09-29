
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
 *         &lt;element name="student70" type="{http://www.his.de/ws/StudentService}Student70"/&gt;
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
    "student70"
})
@XmlRootElement(name = "readStudentByStudentIdResponse")
public class ReadStudentByStudentIdResponse {

    @XmlElement(required = true)
    protected Student70 student70;

    /**
     * Ruft den Wert der student70-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Student70 }
     *     
     */
    public Student70 getStudent70() {
        return student70;
    }

    /**
     * Legt den Wert der student70-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Student70 }
     *     
     */
    public void setStudent70(Student70 value) {
        this.student70 = value;
    }

}
