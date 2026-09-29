
package de.his.ws.studentservice201812;

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
 *         &lt;element name="studentInfos" type="{http://www.his.de/ws/StudentService201812}studentInfosType"/&gt;
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
    "studentInfos"
})
@XmlRootElement(name = "findStudentResponse")
public class FindStudentResponse {

    @XmlElement(required = true)
    protected StudentInfosType studentInfos;

    /**
     * Ruft den Wert der studentInfos-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link StudentInfosType }
     *     
     */
    public StudentInfosType getStudentInfos() {
        return studentInfos;
    }

    /**
     * Legt den Wert der studentInfos-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link StudentInfosType }
     *     
     */
    public void setStudentInfos(StudentInfosType value) {
        this.studentInfos = value;
    }

}
