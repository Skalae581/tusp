
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
 *         &lt;element name="studyBefore" type="{http://www.his.de/ws/StudentService}StudyBeforeDto"/&gt;
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
    "studyBefore"
})
@XmlRootElement(name = "readStudyBeforeResponse")
public class ReadStudyBeforeResponse {

    @XmlElement(required = true, nillable = true)
    protected StudyBeforeDto studyBefore;

    /**
     * Ruft den Wert der studyBefore-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link StudyBeforeDto }
     *     
     */
    public StudyBeforeDto getStudyBefore() {
        return studyBefore;
    }

    /**
     * Legt den Wert der studyBefore-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link StudyBeforeDto }
     *     
     */
    public void setStudyBefore(StudyBeforeDto value) {
        this.studyBefore = value;
    }

}
