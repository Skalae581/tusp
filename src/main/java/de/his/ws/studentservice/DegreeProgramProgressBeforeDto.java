
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
 * <p>Java-Klasse für DegreeProgramProgressBeforeDto complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="DegreeProgramProgressBeforeDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/StudentService}GeneratedDtoBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="createdAt" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="numberOfStudysemester" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="studynumber" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="subjectnumber" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="updatedAt" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="degreeProgramBeforeId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="enrollmentValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="studystatusValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="subjectId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="subjectObjId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="termTypeValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="typeOfStudyValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DegreeProgramProgressBeforeDto", propOrder = {
    "createdAt",
    "numberOfStudysemester",
    "studynumber",
    "subjectnumber",
    "updatedAt",
    "year",
    "degreeProgramBeforeId",
    "enrollmentValueId",
    "studystatusValueId",
    "subjectId",
    "subjectObjId",
    "termTypeValueId",
    "typeOfStudyValueId"
})
public class DegreeProgramProgressBeforeDto
    extends GeneratedDtoBase
{

    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar createdAt;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal numberOfStudysemester;
    @XmlElement(required = true, nillable = true)
    protected BigInteger studynumber;
    @XmlElement(required = true, nillable = true)
    protected BigInteger subjectnumber;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar updatedAt;
    @XmlElement(required = true, nillable = true)
    protected BigInteger year;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long degreeProgramBeforeId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long enrollmentValueId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long studystatusValueId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long subjectId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long subjectObjId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long termTypeValueId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long typeOfStudyValueId;

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
     * Ruft den Wert der numberOfStudysemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNumberOfStudysemester() {
        return numberOfStudysemester;
    }

    /**
     * Legt den Wert der numberOfStudysemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNumberOfStudysemester(BigDecimal value) {
        this.numberOfStudysemester = value;
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
     * Ruft den Wert der degreeProgramBeforeId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDegreeProgramBeforeId() {
        return degreeProgramBeforeId;
    }

    /**
     * Legt den Wert der degreeProgramBeforeId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDegreeProgramBeforeId(Long value) {
        this.degreeProgramBeforeId = value;
    }

    /**
     * Ruft den Wert der enrollmentValueId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEnrollmentValueId() {
        return enrollmentValueId;
    }

    /**
     * Legt den Wert der enrollmentValueId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEnrollmentValueId(Long value) {
        this.enrollmentValueId = value;
    }

    /**
     * Ruft den Wert der studystatusValueId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStudystatusValueId() {
        return studystatusValueId;
    }

    /**
     * Legt den Wert der studystatusValueId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStudystatusValueId(Long value) {
        this.studystatusValueId = value;
    }

    /**
     * Ruft den Wert der subjectId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSubjectId() {
        return subjectId;
    }

    /**
     * Legt den Wert der subjectId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSubjectId(Long value) {
        this.subjectId = value;
    }

    /**
     * Ruft den Wert der subjectObjId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSubjectObjId() {
        return subjectObjId;
    }

    /**
     * Legt den Wert der subjectObjId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSubjectObjId(Long value) {
        this.subjectObjId = value;
    }

    /**
     * Ruft den Wert der termTypeValueId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTermTypeValueId() {
        return termTypeValueId;
    }

    /**
     * Legt den Wert der termTypeValueId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTermTypeValueId(Long value) {
        this.termTypeValueId = value;
    }

    /**
     * Ruft den Wert der typeOfStudyValueId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTypeOfStudyValueId() {
        return typeOfStudyValueId;
    }

    /**
     * Legt den Wert der typeOfStudyValueId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTypeOfStudyValueId(Long value) {
        this.typeOfStudyValueId = value;
    }

}
