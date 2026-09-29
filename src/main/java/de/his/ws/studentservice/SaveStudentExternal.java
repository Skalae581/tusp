
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
 *         &lt;element name="studentexternal" type="{http://www.his.de/ws/StudentService}StudentexternalDto"/&gt;
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
    "studentexternal"
})
@XmlRootElement(name = "saveStudentExternal")
public class SaveStudentExternal {

    @XmlElement(required = true)
    protected StudentexternalDto studentexternal;

    /**
     * Ruft den Wert der studentexternal-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link StudentexternalDto }
     *     
     */
    public StudentexternalDto getStudentexternal() {
        return studentexternal;
    }

    /**
     * Legt den Wert der studentexternal-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link StudentexternalDto }
     *     
     */
    public void setStudentexternal(StudentexternalDto value) {
        this.studentexternal = value;
    }

}
