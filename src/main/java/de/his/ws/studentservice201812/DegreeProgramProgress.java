
package de.his.ws.studentservice201812;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für DegreeProgramProgress complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="DegreeProgramProgress"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="objGuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="subjectnumber" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="studysemester" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="startdate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="enddate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="partTimePercentage" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="studentstatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="studystatus" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="courseOfStudyId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="studynumber" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="partTimeReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="reasonOfLeaveId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="reregistrationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="periodId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="termType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="clinicalSemester" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="curriculumSemester" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="noExaminationAsOf" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="examinationEntitlement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="finished" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="reasonOfFinishing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isSetCurriculumSemester" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="createdAt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="updatedAt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DegreeProgramProgress", propOrder = {
    "id",
    "objGuid",
    "subjectnumber",
    "studysemester",
    "startdate",
    "enddate",
    "partTimePercentage",
    "studentstatus",
    "studystatus",
    "courseOfStudyId",
    "studynumber",
    "partTimeReason",
    "reasonOfLeaveId",
    "reregistrationDate",
    "periodId",
    "termType",
    "year",
    "clinicalSemester",
    "curriculumSemester",
    "noExaminationAsOf",
    "examinationEntitlement",
    "finished",
    "reasonOfFinishing",
    "isSetCurriculumSemester",
    "createdAt",
    "updatedAt"
})
public class DegreeProgramProgress {

    protected Long id;
    @XmlElement(nillable = true)
    protected String objGuid;
    protected BigInteger subjectnumber;
    protected BigDecimal studysemester;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startdate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar enddate;
    @XmlElement(nillable = true)
    protected BigInteger partTimePercentage;
    @XmlElement(nillable = true)
    protected String studentstatus;
    @XmlElement(required = true, nillable = true)
    protected String studystatus;
    protected Long courseOfStudyId;
    protected BigInteger studynumber;
    @XmlElement(nillable = true)
    protected String partTimeReason;
    @XmlElement(nillable = true)
    protected String reasonOfLeaveId;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar reregistrationDate;
    protected Long periodId;
    @XmlElement(required = true, nillable = true)
    protected String termType;
    @XmlElement(required = true, nillable = true)
    protected BigInteger year;
    @XmlElement(nillable = true)
    protected BigDecimal clinicalSemester;
    @XmlElement(nillable = true)
    protected BigDecimal curriculumSemester;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar noExaminationAsOf;
    @XmlElement(nillable = true)
    protected String examinationEntitlement;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar finished;
    @XmlElement(nillable = true)
    protected String reasonOfFinishing;
    @XmlElement(nillable = true)
    protected Boolean isSetCurriculumSemester;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdAt;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updatedAt;

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der objGuid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjGuid() {
        return objGuid;
    }

    /**
     * Legt den Wert der objGuid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjGuid(String value) {
        this.objGuid = value;
    }

    /**
     * Ruft den Wert der subjectnumber-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSubjectnumber() {
        return subjectnumber;
    }

    /**
     * Legt den Wert der subjectnumber-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSubjectnumber(BigInteger value) {
        this.subjectnumber = value;
    }

    /**
     * Ruft den Wert der studysemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStudysemester() {
        return studysemester;
    }

    /**
     * Legt den Wert der studysemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStudysemester(BigDecimal value) {
        this.studysemester = value;
    }

    /**
     * Ruft den Wert der startdate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartdate() {
        return startdate;
    }

    /**
     * Legt den Wert der startdate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartdate(XMLGregorianCalendar value) {
        this.startdate = value;
    }

    /**
     * Ruft den Wert der enddate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEnddate() {
        return enddate;
    }

    /**
     * Legt den Wert der enddate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEnddate(XMLGregorianCalendar value) {
        this.enddate = value;
    }

    /**
     * Ruft den Wert der partTimePercentage-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPartTimePercentage() {
        return partTimePercentage;
    }

    /**
     * Legt den Wert der partTimePercentage-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPartTimePercentage(BigInteger value) {
        this.partTimePercentage = value;
    }

    /**
     * Ruft den Wert der studentstatus-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentstatus() {
        return studentstatus;
    }

    /**
     * Legt den Wert der studentstatus-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentstatus(String value) {
        this.studentstatus = value;
    }

    /**
     * Ruft den Wert der studystatus-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudystatus() {
        return studystatus;
    }

    /**
     * Legt den Wert der studystatus-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudystatus(String value) {
        this.studystatus = value;
    }

    /**
     * Ruft den Wert der courseOfStudyId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCourseOfStudyId() {
        return courseOfStudyId;
    }

    /**
     * Legt den Wert der courseOfStudyId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCourseOfStudyId(Long value) {
        this.courseOfStudyId = value;
    }

    /**
     * Ruft den Wert der studynumber-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStudynumber() {
        return studynumber;
    }

    /**
     * Legt den Wert der studynumber-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStudynumber(BigInteger value) {
        this.studynumber = value;
    }

    /**
     * Ruft den Wert der partTimeReason-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartTimeReason() {
        return partTimeReason;
    }

    /**
     * Legt den Wert der partTimeReason-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartTimeReason(String value) {
        this.partTimeReason = value;
    }

    /**
     * Ruft den Wert der reasonOfLeaveId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReasonOfLeaveId() {
        return reasonOfLeaveId;
    }

    /**
     * Legt den Wert der reasonOfLeaveId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReasonOfLeaveId(String value) {
        this.reasonOfLeaveId = value;
    }

    /**
     * Ruft den Wert der reregistrationDate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReregistrationDate() {
        return reregistrationDate;
    }

    /**
     * Legt den Wert der reregistrationDate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReregistrationDate(XMLGregorianCalendar value) {
        this.reregistrationDate = value;
    }

    /**
     * Ruft den Wert der periodId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPeriodId() {
        return periodId;
    }

    /**
     * Legt den Wert der periodId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPeriodId(Long value) {
        this.periodId = value;
    }

    /**
     * Ruft den Wert der termType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermType() {
        return termType;
    }

    /**
     * Legt den Wert der termType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermType(String value) {
        this.termType = value;
    }

    /**
     * Ruft den Wert der year-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getYear() {
        return year;
    }

    /**
     * Legt den Wert der year-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setYear(BigInteger value) {
        this.year = value;
    }

    /**
     * Ruft den Wert der clinicalSemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getClinicalSemester() {
        return clinicalSemester;
    }

    /**
     * Legt den Wert der clinicalSemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setClinicalSemester(BigDecimal value) {
        this.clinicalSemester = value;
    }

    /**
     * Ruft den Wert der curriculumSemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCurriculumSemester() {
        return curriculumSemester;
    }

    /**
     * Legt den Wert der curriculumSemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCurriculumSemester(BigDecimal value) {
        this.curriculumSemester = value;
    }

    /**
     * Ruft den Wert der noExaminationAsOf-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNoExaminationAsOf() {
        return noExaminationAsOf;
    }

    /**
     * Legt den Wert der noExaminationAsOf-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNoExaminationAsOf(XMLGregorianCalendar value) {
        this.noExaminationAsOf = value;
    }

    /**
     * Ruft den Wert der examinationEntitlement-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExaminationEntitlement() {
        return examinationEntitlement;
    }

    /**
     * Legt den Wert der examinationEntitlement-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExaminationEntitlement(String value) {
        this.examinationEntitlement = value;
    }

    /**
     * Ruft den Wert der finished-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFinished() {
        return finished;
    }

    /**
     * Legt den Wert der finished-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFinished(XMLGregorianCalendar value) {
        this.finished = value;
    }

    /**
     * Ruft den Wert der reasonOfFinishing-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReasonOfFinishing() {
        return reasonOfFinishing;
    }

    /**
     * Legt den Wert der reasonOfFinishing-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReasonOfFinishing(String value) {
        this.reasonOfFinishing = value;
    }

    /**
     * Ruft den Wert der isSetCurriculumSemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsSetCurriculumSemester() {
        return isSetCurriculumSemester;
    }

    /**
     * Legt den Wert der isSetCurriculumSemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSetCurriculumSemester(Boolean value) {
        this.isSetCurriculumSemester = value;
    }

    /**
     * Ruft den Wert der createdAt-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedAt() {
        return createdAt;
    }

    /**
     * Legt den Wert der createdAt-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedAt(XMLGregorianCalendar value) {
        this.createdAt = value;
    }

    /**
     * Ruft den Wert der updatedAt-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Legt den Wert der updatedAt-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdatedAt(XMLGregorianCalendar value) {
        this.updatedAt = value;
    }

}
