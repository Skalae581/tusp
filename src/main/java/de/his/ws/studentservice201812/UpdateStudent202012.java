
package de.his.ws.studentservice201812;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="studentId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="enrollmentDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="studentErrorStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="parentalLeaveFrom" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="parentalLeaveTo" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="officialStatisticsDataUpdatedAt" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="orgunit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="studyRelatedInternship" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="studyRelatedJob" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="exchangeprogram" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="connectedToSubject" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="universitysemester" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="leavesemester" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="practicalsemester" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="kollegsemester" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="healthInsuranceConfirmationStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="healthInsuranceCheckdate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="healthInsuranceMatching" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="healthInsuranceSmvparticipation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
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
    "studentId",
    "enrollmentDate",
    "studentErrorStatus",
    "parentalLeaveFrom",
    "parentalLeaveTo",
    "officialStatisticsDataUpdatedAt",
    "orgunit",
    "studyRelatedInternship",
    "studyRelatedJob",
    "exchangeprogram",
    "connectedToSubject",
    "universitysemester",
    "leavesemester",
    "practicalsemester",
    "kollegsemester",
    "healthInsuranceConfirmationStatus",
    "healthInsuranceCheckdate",
    "healthInsuranceMatching",
    "healthInsuranceSmvparticipation"
})
@XmlRootElement(name = "updateStudent202012")
public class UpdateStudent202012 {

    protected long studentId;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar enrollmentDate;
    protected String studentErrorStatus;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar parentalLeaveFrom;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar parentalLeaveTo;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar officialStatisticsDataUpdatedAt;
    protected String orgunit;
    protected BigInteger studyRelatedInternship;
    protected BigInteger studyRelatedJob;
    protected String exchangeprogram;
    protected BigInteger connectedToSubject;
    protected BigDecimal universitysemester;
    protected BigDecimal leavesemester;
    protected BigDecimal practicalsemester;
    protected BigDecimal kollegsemester;
    protected String healthInsuranceConfirmationStatus;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar healthInsuranceCheckdate;
    protected Boolean healthInsuranceMatching;
    protected Boolean healthInsuranceSmvparticipation;

    /**
     * Ruft den Wert der studentId-Eigenschaft ab.
     * 
     */
    public long getStudentId() {
        return studentId;
    }

    /**
     * Legt den Wert der studentId-Eigenschaft fest.
     * 
     */
    public void setStudentId(long value) {
        this.studentId = value;
    }

    /**
     * Ruft den Wert der enrollmentDate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEnrollmentDate() {
        return enrollmentDate;
    }

    /**
     * Legt den Wert der enrollmentDate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEnrollmentDate(XMLGregorianCalendar value) {
        this.enrollmentDate = value;
    }

    /**
     * Ruft den Wert der studentErrorStatus-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentErrorStatus() {
        return studentErrorStatus;
    }

    /**
     * Legt den Wert der studentErrorStatus-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentErrorStatus(String value) {
        this.studentErrorStatus = value;
    }

    /**
     * Ruft den Wert der parentalLeaveFrom-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getParentalLeaveFrom() {
        return parentalLeaveFrom;
    }

    /**
     * Legt den Wert der parentalLeaveFrom-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setParentalLeaveFrom(XMLGregorianCalendar value) {
        this.parentalLeaveFrom = value;
    }

    /**
     * Ruft den Wert der parentalLeaveTo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getParentalLeaveTo() {
        return parentalLeaveTo;
    }

    /**
     * Legt den Wert der parentalLeaveTo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setParentalLeaveTo(XMLGregorianCalendar value) {
        this.parentalLeaveTo = value;
    }

    /**
     * Ruft den Wert der officialStatisticsDataUpdatedAt-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOfficialStatisticsDataUpdatedAt() {
        return officialStatisticsDataUpdatedAt;
    }

    /**
     * Legt den Wert der officialStatisticsDataUpdatedAt-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOfficialStatisticsDataUpdatedAt(XMLGregorianCalendar value) {
        this.officialStatisticsDataUpdatedAt = value;
    }

    /**
     * Ruft den Wert der orgunit-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgunit() {
        return orgunit;
    }

    /**
     * Legt den Wert der orgunit-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgunit(String value) {
        this.orgunit = value;
    }

    /**
     * Ruft den Wert der studyRelatedInternship-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStudyRelatedInternship() {
        return studyRelatedInternship;
    }

    /**
     * Legt den Wert der studyRelatedInternship-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStudyRelatedInternship(BigInteger value) {
        this.studyRelatedInternship = value;
    }

    /**
     * Ruft den Wert der studyRelatedJob-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStudyRelatedJob() {
        return studyRelatedJob;
    }

    /**
     * Legt den Wert der studyRelatedJob-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStudyRelatedJob(BigInteger value) {
        this.studyRelatedJob = value;
    }

    /**
     * Ruft den Wert der exchangeprogram-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExchangeprogram() {
        return exchangeprogram;
    }

    /**
     * Legt den Wert der exchangeprogram-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExchangeprogram(String value) {
        this.exchangeprogram = value;
    }

    /**
     * Ruft den Wert der connectedToSubject-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getConnectedToSubject() {
        return connectedToSubject;
    }

    /**
     * Legt den Wert der connectedToSubject-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setConnectedToSubject(BigInteger value) {
        this.connectedToSubject = value;
    }

    /**
     * Ruft den Wert der universitysemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUniversitysemester() {
        return universitysemester;
    }

    /**
     * Legt den Wert der universitysemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUniversitysemester(BigDecimal value) {
        this.universitysemester = value;
    }

    /**
     * Ruft den Wert der leavesemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLeavesemester() {
        return leavesemester;
    }

    /**
     * Legt den Wert der leavesemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLeavesemester(BigDecimal value) {
        this.leavesemester = value;
    }

    /**
     * Ruft den Wert der practicalsemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPracticalsemester() {
        return practicalsemester;
    }

    /**
     * Legt den Wert der practicalsemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPracticalsemester(BigDecimal value) {
        this.practicalsemester = value;
    }

    /**
     * Ruft den Wert der kollegsemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getKollegsemester() {
        return kollegsemester;
    }

    /**
     * Legt den Wert der kollegsemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setKollegsemester(BigDecimal value) {
        this.kollegsemester = value;
    }

    /**
     * Ruft den Wert der healthInsuranceConfirmationStatus-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHealthInsuranceConfirmationStatus() {
        return healthInsuranceConfirmationStatus;
    }

    /**
     * Legt den Wert der healthInsuranceConfirmationStatus-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHealthInsuranceConfirmationStatus(String value) {
        this.healthInsuranceConfirmationStatus = value;
    }

    /**
     * Ruft den Wert der healthInsuranceCheckdate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getHealthInsuranceCheckdate() {
        return healthInsuranceCheckdate;
    }

    /**
     * Legt den Wert der healthInsuranceCheckdate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setHealthInsuranceCheckdate(XMLGregorianCalendar value) {
        this.healthInsuranceCheckdate = value;
    }

    /**
     * Ruft den Wert der healthInsuranceMatching-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHealthInsuranceMatching() {
        return healthInsuranceMatching;
    }

    /**
     * Legt den Wert der healthInsuranceMatching-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHealthInsuranceMatching(Boolean value) {
        this.healthInsuranceMatching = value;
    }

    /**
     * Ruft den Wert der healthInsuranceSmvparticipation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHealthInsuranceSmvparticipation() {
        return healthInsuranceSmvparticipation;
    }

    /**
     * Legt den Wert der healthInsuranceSmvparticipation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHealthInsuranceSmvparticipation(Boolean value) {
        this.healthInsuranceSmvparticipation = value;
    }

}
