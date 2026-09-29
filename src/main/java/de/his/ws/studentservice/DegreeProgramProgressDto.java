
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
 * <p>Java-Klasse für DegreeProgramProgressDto complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="DegreeProgramProgressDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="courseOfStudyId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="degreeProgramId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="studentstatusId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="studyStatusId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="studynumber" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="studysemester" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="subjectnumber" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="periodId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="period" type="{http://www.his.de/ws/StudentService}PeriodDto"/&gt;
 *         &lt;element name="finished" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="reasonOfFinishingId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DegreeProgramProgressDto", propOrder = {
    "courseOfStudyId",
    "degreeProgramId",
    "studentstatusId",
    "studyStatusId",
    "studynumber",
    "studysemester",
    "subjectnumber",
    "periodId",
    "period",
    "finished",
    "reasonOfFinishingId"
})
public class DegreeProgramProgressDto {

    protected long courseOfStudyId;
    protected long degreeProgramId;
    protected long studentstatusId;
    protected long studyStatusId;
    @XmlElement(required = true)
    protected BigInteger studynumber;
    @XmlElement(required = true)
    protected BigDecimal studysemester;
    @XmlElement(required = true)
    protected BigInteger subjectnumber;
    protected long periodId;
    @XmlElement(required = true)
    protected PeriodDto period;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar finished;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long reasonOfFinishingId;

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
     * Ruft den Wert der degreeProgramId-Eigenschaft ab.
     * 
     */
    public long getDegreeProgramId() {
        return degreeProgramId;
    }

    /**
     * Legt den Wert der degreeProgramId-Eigenschaft fest.
     * 
     */
    public void setDegreeProgramId(long value) {
        this.degreeProgramId = value;
    }

    /**
     * Ruft den Wert der studentstatusId-Eigenschaft ab.
     * 
     */
    public long getStudentstatusId() {
        return studentstatusId;
    }

    /**
     * Legt den Wert der studentstatusId-Eigenschaft fest.
     * 
     */
    public void setStudentstatusId(long value) {
        this.studentstatusId = value;
    }

    /**
     * Ruft den Wert der studyStatusId-Eigenschaft ab.
     * 
     */
    public long getStudyStatusId() {
        return studyStatusId;
    }

    /**
     * Legt den Wert der studyStatusId-Eigenschaft fest.
     * 
     */
    public void setStudyStatusId(long value) {
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
     */
    public long getPeriodId() {
        return periodId;
    }

    /**
     * Legt den Wert der periodId-Eigenschaft fest.
     * 
     */
    public void setPeriodId(long value) {
        this.periodId = value;
    }

    /**
     * Ruft den Wert der period-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PeriodDto }
     *     
     */
    public PeriodDto getPeriod() {
        return period;
    }

    /**
     * Legt den Wert der period-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodDto }
     *     
     */
    public void setPeriod(PeriodDto value) {
        this.period = value;
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

}
