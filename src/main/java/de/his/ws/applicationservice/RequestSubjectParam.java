
package de.his.ws.applicationservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für RequestSubjectParam complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="RequestSubjectParam"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="courseOfStudyId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="studysemesterRequested" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestSubjectParam", propOrder = {
    "courseOfStudyId",
    "studysemesterRequested"
})
public class RequestSubjectParam {

    protected long courseOfStudyId;
    @XmlElement(required = true)
    protected BigInteger studysemesterRequested;

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

    /**
     * Ruft den Wert der studysemesterRequested-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStudysemesterRequested() {
        return studysemesterRequested;
    }

    /**
     * Legt den Wert der studysemesterRequested-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStudysemesterRequested(BigInteger value) {
        this.studysemesterRequested = value;
    }

}
