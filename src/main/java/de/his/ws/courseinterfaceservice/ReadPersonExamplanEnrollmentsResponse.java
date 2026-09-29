
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
 *         &lt;element name="examplans" type="{http://www.his.de/ws/CourseInterfaceService}examplansType"/&gt;
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
    "examplans"
})
@XmlRootElement(name = "readPersonExamplanEnrollmentsResponse")
public class ReadPersonExamplanEnrollmentsResponse {

    @XmlElement(required = true)
    protected ExamplansType examplans;

    /**
     * Ruft den Wert der examplans-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ExamplansType }
     *     
     */
    public ExamplansType getExamplans() {
        return examplans;
    }

    /**
     * Legt den Wert der examplans-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ExamplansType }
     *     
     */
    public void setExamplans(ExamplansType value) {
        this.examplans = value;
    }

}
