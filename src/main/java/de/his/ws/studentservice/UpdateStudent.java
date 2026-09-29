
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
 *         &lt;element name="studentDto" type="{http://www.his.de/ws/StudentService}StudentExisting"/&gt;
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
    "studentDto"
})
@XmlRootElement(name = "updateStudent")
public class UpdateStudent {

    @XmlElement(required = true)
    protected StudentExisting studentDto;

    /**
     * Ruft den Wert der studentDto-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link StudentExisting }
     *     
     */
    public StudentExisting getStudentDto() {
        return studentDto;
    }

    /**
     * Legt den Wert der studentDto-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link StudentExisting }
     *     
     */
    public void setStudentDto(StudentExisting value) {
        this.studentDto = value;
    }

}
