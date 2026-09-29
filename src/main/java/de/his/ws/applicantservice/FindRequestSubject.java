
package de.his.ws.applicantservice;

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
 *         &lt;element name="degree" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cosUniquename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="studysemester" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="application_term_year" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="application_term_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="requestSubjectStatusHiskeyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="requestSubjectId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "degree",
    "cosUniquename",
    "subject",
    "studysemester",
    "applicationTermYear",
    "applicationTermType",
    "requestSubjectStatusHiskeyId",
    "requestSubjectId"
})
@XmlRootElement(name = "findRequestSubject")
public class FindRequestSubject {

    @XmlElement(nillable = true)
    protected String degree;
    @XmlElement(nillable = true)
    protected String cosUniquename;
    @XmlElement(nillable = true)
    protected String subject;
    @XmlElement(nillable = true)
    protected String studysemester;
    @XmlElement(name = "application_term_year", nillable = true)
    protected String applicationTermYear;
    @XmlElement(name = "application_term_type", nillable = true)
    protected String applicationTermType;
    @XmlElement(nillable = true)
    protected String requestSubjectStatusHiskeyId;
    @XmlElement(nillable = true)
    protected String requestSubjectId;

    /**
     * Ruft den Wert der degree-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDegree() {
        return degree;
    }

    /**
     * Legt den Wert der degree-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDegree(String value) {
        this.degree = value;
    }

    /**
     * Ruft den Wert der cosUniquename-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCosUniquename() {
        return cosUniquename;
    }

    /**
     * Legt den Wert der cosUniquename-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCosUniquename(String value) {
        this.cosUniquename = value;
    }

    /**
     * Ruft den Wert der subject-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Legt den Wert der subject-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Ruft den Wert der studysemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudysemester() {
        return studysemester;
    }

    /**
     * Legt den Wert der studysemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudysemester(String value) {
        this.studysemester = value;
    }

    /**
     * Ruft den Wert der applicationTermYear-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationTermYear() {
        return applicationTermYear;
    }

    /**
     * Legt den Wert der applicationTermYear-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationTermYear(String value) {
        this.applicationTermYear = value;
    }

    /**
     * Ruft den Wert der applicationTermType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationTermType() {
        return applicationTermType;
    }

    /**
     * Legt den Wert der applicationTermType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationTermType(String value) {
        this.applicationTermType = value;
    }

    /**
     * Ruft den Wert der requestSubjectStatusHiskeyId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestSubjectStatusHiskeyId() {
        return requestSubjectStatusHiskeyId;
    }

    /**
     * Legt den Wert der requestSubjectStatusHiskeyId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestSubjectStatusHiskeyId(String value) {
        this.requestSubjectStatusHiskeyId = value;
    }

    /**
     * Ruft den Wert der requestSubjectId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestSubjectId() {
        return requestSubjectId;
    }

    /**
     * Legt den Wert der requestSubjectId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestSubjectId(String value) {
        this.requestSubjectId = value;
    }

}
