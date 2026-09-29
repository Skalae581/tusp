
package de.his.ws.applicationservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für RequestParam complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="RequestParam"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="applicantId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="termYear" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="termTypeValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="requestSubjects" type="{http://www.his.de/ws/ApplicationService}requestSubjectsType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestParam", propOrder = {
    "applicantId",
    "termYear",
    "termTypeValueId",
    "requestSubjects"
})
public class RequestParam {

    protected long applicantId;
    @XmlElement(required = true)
    protected BigInteger termYear;
    protected long termTypeValueId;
    @XmlElement(required = true)
    protected RequestSubjectsType requestSubjects;

    /**
     * Ruft den Wert der applicantId-Eigenschaft ab.
     * 
     */
    public long getApplicantId() {
        return applicantId;
    }

    /**
     * Legt den Wert der applicantId-Eigenschaft fest.
     * 
     */
    public void setApplicantId(long value) {
        this.applicantId = value;
    }

    /**
     * Ruft den Wert der termYear-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTermYear() {
        return termYear;
    }

    /**
     * Legt den Wert der termYear-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTermYear(BigInteger value) {
        this.termYear = value;
    }

    /**
     * Ruft den Wert der termTypeValueId-Eigenschaft ab.
     * 
     */
    public long getTermTypeValueId() {
        return termTypeValueId;
    }

    /**
     * Legt den Wert der termTypeValueId-Eigenschaft fest.
     * 
     */
    public void setTermTypeValueId(long value) {
        this.termTypeValueId = value;
    }

    /**
     * Ruft den Wert der requestSubjects-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RequestSubjectsType }
     *     
     */
    public RequestSubjectsType getRequestSubjects() {
        return requestSubjects;
    }

    /**
     * Legt den Wert der requestSubjects-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestSubjectsType }
     *     
     */
    public void setRequestSubjects(RequestSubjectsType value) {
        this.requestSubjects = value;
    }

}
