
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
 *         &lt;element name="studentExternals" type="{http://www.his.de/ws/StudentService}studentExternalsType"/&gt;
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
    "studentExternals"
})
@XmlRootElement(name = "searchStudentExternalByPersonIdResponse")
public class SearchStudentExternalByPersonIdResponse {

    @XmlElement(required = true, nillable = true)
    protected StudentExternalsType studentExternals;

    /**
     * Ruft den Wert der studentExternals-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link StudentExternalsType }
     *     
     */
    public StudentExternalsType getStudentExternals() {
        return studentExternals;
    }

    /**
     * Legt den Wert der studentExternals-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link StudentExternalsType }
     *     
     */
    public void setStudentExternals(StudentExternalsType value) {
        this.studentExternals = value;
    }

}
