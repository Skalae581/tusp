
package de.his.ws.applicantservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für Applicant complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Applicant"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="applicantNumber" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="registrationNumber" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="personId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="studentId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="hasDoneService" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="serviceBegin" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="serviceEnd" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="applicantPerson" type="{http://www.his.de/ws/ApplicantService}ApplicantPerson"/&gt;
 *         &lt;element name="applicationDataSet" type="{http://www.his.de/ws/ApplicantService}applicationDataSetType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Applicant", propOrder = {
    "id",
    "applicantNumber",
    "registrationNumber",
    "personId",
    "studentId",
    "hasDoneService",
    "serviceBegin",
    "serviceEnd",
    "applicantPerson",
    "applicationDataSet"
})
public class Applicant {

    protected long id;
    protected long applicantNumber;
    @XmlElement(required = true, nillable = true)
    protected BigInteger registrationNumber;
    protected long personId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long studentId;
    @XmlElement(required = true)
    protected BigInteger hasDoneService;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar serviceBegin;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar serviceEnd;
    @XmlElement(required = true)
    protected ApplicantPerson applicantPerson;
    @XmlElement(required = true, nillable = true)
    protected ApplicationDataSetType applicationDataSet;

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der applicantNumber-Eigenschaft ab.
     * 
     */
    public long getApplicantNumber() {
        return applicantNumber;
    }

    /**
     * Legt den Wert der applicantNumber-Eigenschaft fest.
     * 
     */
    public void setApplicantNumber(long value) {
        this.applicantNumber = value;
    }

    /**
     * Ruft den Wert der registrationNumber-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Legt den Wert der registrationNumber-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRegistrationNumber(BigInteger value) {
        this.registrationNumber = value;
    }

    /**
     * Ruft den Wert der personId-Eigenschaft ab.
     * 
     */
    public long getPersonId() {
        return personId;
    }

    /**
     * Legt den Wert der personId-Eigenschaft fest.
     * 
     */
    public void setPersonId(long value) {
        this.personId = value;
    }

    /**
     * Ruft den Wert der studentId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStudentId() {
        return studentId;
    }

    /**
     * Legt den Wert der studentId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStudentId(Long value) {
        this.studentId = value;
    }

    /**
     * Ruft den Wert der hasDoneService-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getHasDoneService() {
        return hasDoneService;
    }

    /**
     * Legt den Wert der hasDoneService-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setHasDoneService(BigInteger value) {
        this.hasDoneService = value;
    }

    /**
     * Ruft den Wert der serviceBegin-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getServiceBegin() {
        return serviceBegin;
    }

    /**
     * Legt den Wert der serviceBegin-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setServiceBegin(XMLGregorianCalendar value) {
        this.serviceBegin = value;
    }

    /**
     * Ruft den Wert der serviceEnd-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getServiceEnd() {
        return serviceEnd;
    }

    /**
     * Legt den Wert der serviceEnd-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setServiceEnd(XMLGregorianCalendar value) {
        this.serviceEnd = value;
    }

    /**
     * Ruft den Wert der applicantPerson-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ApplicantPerson }
     *     
     */
    public ApplicantPerson getApplicantPerson() {
        return applicantPerson;
    }

    /**
     * Legt den Wert der applicantPerson-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicantPerson }
     *     
     */
    public void setApplicantPerson(ApplicantPerson value) {
        this.applicantPerson = value;
    }

    /**
     * Ruft den Wert der applicationDataSet-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationDataSetType }
     *     
     */
    public ApplicationDataSetType getApplicationDataSet() {
        return applicationDataSet;
    }

    /**
     * Legt den Wert der applicationDataSet-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationDataSetType }
     *     
     */
    public void setApplicationDataSet(ApplicationDataSetType value) {
        this.applicationDataSet = value;
    }

}
