
package de.his.ws.studentservice;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für DegreeProgramProgressDto70 complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="DegreeProgramProgressDto70"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="courseOfStudyId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="degreeProgramId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="studentstatusId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="studyStatusId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="studynumber" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="studysemester" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="subjectnumber" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="periodId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="finished" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="reasonOfFinishingId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="clinicalSemester" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DegreeProgramProgressDto70", propOrder = {
    "courseOfStudyId",
    "degreeProgramId",
    "studentstatusId",
    "studyStatusId",
    "studynumber",
    "studysemester",
    "subjectnumber",
    "periodId",
    "finished",
    "reasonOfFinishingId",
    "clinicalSemester"
})
public class DegreeProgramProgressDto70 {

    protected Long courseOfStudyId;
    protected Long degreeProgramId;
    protected Long studentstatusId;
    protected Long studyStatusId;
    protected BigInteger studynumber;
    protected BigDecimal studysemester;
    protected BigInteger subjectnumber;
    protected Long periodId;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar finished;
    @XmlElement(nillable = true)
    protected Long reasonOfFinishingId;
    @XmlElement(nillable = true)
    protected BigDecimal clinicalSemester;

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
     * Ruft den Wert der degreeProgramId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDegreeProgramId() {
        return degreeProgramId;
    }

    /**
     * Legt den Wert der degreeProgramId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDegreeProgramId(Long value) {
        this.degreeProgramId = value;
    }

    /**
     * Ruft den Wert der studentstatusId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStudentstatusId() {
        return studentstatusId;
    }

    /**
     * Legt den Wert der studentstatusId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStudentstatusId(Long value) {
        this.studentstatusId = value;
    }

    /**
     * Ruft den Wert der studyStatusId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStudyStatusId() {
        return studyStatusId;
    }

    /**
     * Legt den Wert der studyStatusId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStudyStatusId(Long value) {
        this.studyStatusId = value;
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
     * Ruft den Wert der reasonOfFinishingId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getReasonOfFinishingId() {
        return reasonOfFinishingId;
    }

    /**
     * Legt den Wert der reasonOfFinishingId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setReasonOfFinishingId(Long value) {
        this.reasonOfFinishingId = value;
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

}
