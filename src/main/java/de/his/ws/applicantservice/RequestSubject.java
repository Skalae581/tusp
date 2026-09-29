
package de.his.ws.applicantservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für RequestSubject complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="RequestSubject"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="courseOfStudyId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="courseOfStudy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="studySemesterRequested" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="studySemesterAdmitted" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="admissionStatusId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="reactionStatusId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="requestSubjectStatusId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="admissionDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="admissionMode" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="acceptanceDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="statusRemarkIds" type="{http://www.his.de/ws/ApplicantService}statusRemarkIdsType"/&gt;
 *         &lt;element name="statusRemarkText" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="scheduledDateOfEnrollment" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="dateOfExclusion" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="admissionTypeId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="selectionResult" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="quotaRankingInformation" type="{http://www.his.de/ws/ApplicantService}QuotaRankingInformation"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestSubject", propOrder = {
    "id",
    "courseOfStudyId",
    "courseOfStudy",
    "studySemesterRequested",
    "studySemesterAdmitted",
    "admissionStatusId",
    "reactionStatusId",
    "requestSubjectStatusId",
    "admissionDate",
    "admissionMode",
    "acceptanceDate",
    "statusRemarkIds",
    "statusRemarkText",
    "scheduledDateOfEnrollment",
    "dateOfExclusion",
    "remark",
    "admissionTypeId",
    "selectionResult",
    "quotaRankingInformation"
})
public class RequestSubject {

    protected long id;
    protected long courseOfStudyId;
    @XmlElement(required = true)
    protected String courseOfStudy;
    @XmlElement(required = true)
    protected BigInteger studySemesterRequested;
    @XmlElement(required = true)
    protected BigInteger studySemesterAdmitted;
    protected long admissionStatusId;
    protected long reactionStatusId;
    protected long requestSubjectStatusId;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar admissionDate;
    @XmlElement(required = true, nillable = true)
    protected BigInteger admissionMode;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar acceptanceDate;
    @XmlElement(required = true, nillable = true)
    protected StatusRemarkIdsType statusRemarkIds;
    @XmlElement(required = true, nillable = true)
    protected String statusRemarkText;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar scheduledDateOfEnrollment;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateOfExclusion;
    @XmlElement(required = true, nillable = true)
    protected String remark;
    protected long admissionTypeId;
    @XmlElement(required = true, nillable = true)
    protected String selectionResult;
    @XmlElement(required = true, nillable = true)
    protected QuotaRankingInformation quotaRankingInformation;

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
     * Ruft den Wert der courseOfStudy-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCourseOfStudy() {
        return courseOfStudy;
    }

    /**
     * Legt den Wert der courseOfStudy-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCourseOfStudy(String value) {
        this.courseOfStudy = value;
    }

    /**
     * Ruft den Wert der studySemesterRequested-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStudySemesterRequested() {
        return studySemesterRequested;
    }

    /**
     * Legt den Wert der studySemesterRequested-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStudySemesterRequested(BigInteger value) {
        this.studySemesterRequested = value;
    }

    /**
     * Ruft den Wert der studySemesterAdmitted-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStudySemesterAdmitted() {
        return studySemesterAdmitted;
    }

    /**
     * Legt den Wert der studySemesterAdmitted-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStudySemesterAdmitted(BigInteger value) {
        this.studySemesterAdmitted = value;
    }

    /**
     * Ruft den Wert der admissionStatusId-Eigenschaft ab.
     * 
     */
    public long getAdmissionStatusId() {
        return admissionStatusId;
    }

    /**
     * Legt den Wert der admissionStatusId-Eigenschaft fest.
     * 
     */
    public void setAdmissionStatusId(long value) {
        this.admissionStatusId = value;
    }

    /**
     * Ruft den Wert der reactionStatusId-Eigenschaft ab.
     * 
     */
    public long getReactionStatusId() {
        return reactionStatusId;
    }

    /**
     * Legt den Wert der reactionStatusId-Eigenschaft fest.
     * 
     */
    public void setReactionStatusId(long value) {
        this.reactionStatusId = value;
    }

    /**
     * Ruft den Wert der requestSubjectStatusId-Eigenschaft ab.
     * 
     */
    public long getRequestSubjectStatusId() {
        return requestSubjectStatusId;
    }

    /**
     * Legt den Wert der requestSubjectStatusId-Eigenschaft fest.
     * 
     */
    public void setRequestSubjectStatusId(long value) {
        this.requestSubjectStatusId = value;
    }

    /**
     * Ruft den Wert der admissionDate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAdmissionDate() {
        return admissionDate;
    }

    /**
     * Legt den Wert der admissionDate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAdmissionDate(XMLGregorianCalendar value) {
        this.admissionDate = value;
    }

    /**
     * Ruft den Wert der admissionMode-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAdmissionMode() {
        return admissionMode;
    }

    /**
     * Legt den Wert der admissionMode-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAdmissionMode(BigInteger value) {
        this.admissionMode = value;
    }

    /**
     * Ruft den Wert der acceptanceDate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAcceptanceDate() {
        return acceptanceDate;
    }

    /**
     * Legt den Wert der acceptanceDate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAcceptanceDate(XMLGregorianCalendar value) {
        this.acceptanceDate = value;
    }

    /**
     * Ruft den Wert der statusRemarkIds-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link StatusRemarkIdsType }
     *     
     */
    public StatusRemarkIdsType getStatusRemarkIds() {
        return statusRemarkIds;
    }

    /**
     * Legt den Wert der statusRemarkIds-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusRemarkIdsType }
     *     
     */
    public void setStatusRemarkIds(StatusRemarkIdsType value) {
        this.statusRemarkIds = value;
    }

    /**
     * Ruft den Wert der statusRemarkText-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusRemarkText() {
        return statusRemarkText;
    }

    /**
     * Legt den Wert der statusRemarkText-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusRemarkText(String value) {
        this.statusRemarkText = value;
    }

    /**
     * Ruft den Wert der scheduledDateOfEnrollment-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getScheduledDateOfEnrollment() {
        return scheduledDateOfEnrollment;
    }

    /**
     * Legt den Wert der scheduledDateOfEnrollment-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setScheduledDateOfEnrollment(XMLGregorianCalendar value) {
        this.scheduledDateOfEnrollment = value;
    }

    /**
     * Ruft den Wert der dateOfExclusion-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateOfExclusion() {
        return dateOfExclusion;
    }

    /**
     * Legt den Wert der dateOfExclusion-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateOfExclusion(XMLGregorianCalendar value) {
        this.dateOfExclusion = value;
    }

    /**
     * Ruft den Wert der remark-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Legt den Wert der remark-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * Ruft den Wert der admissionTypeId-Eigenschaft ab.
     * 
     */
    public long getAdmissionTypeId() {
        return admissionTypeId;
    }

    /**
     * Legt den Wert der admissionTypeId-Eigenschaft fest.
     * 
     */
    public void setAdmissionTypeId(long value) {
        this.admissionTypeId = value;
    }

    /**
     * Ruft den Wert der selectionResult-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectionResult() {
        return selectionResult;
    }

    /**
     * Legt den Wert der selectionResult-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectionResult(String value) {
        this.selectionResult = value;
    }

    /**
     * Ruft den Wert der quotaRankingInformation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link QuotaRankingInformation }
     *     
     */
    public QuotaRankingInformation getQuotaRankingInformation() {
        return quotaRankingInformation;
    }

    /**
     * Legt den Wert der quotaRankingInformation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link QuotaRankingInformation }
     *     
     */
    public void setQuotaRankingInformation(QuotaRankingInformation value) {
        this.quotaRankingInformation = value;
    }

}
