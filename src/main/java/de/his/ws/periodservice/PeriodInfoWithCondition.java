
package de.his.ws.periodservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für PeriodInfoWithCondition complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PeriodInfoWithCondition"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="periodId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="defaultext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="shorttext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="longtext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="termTypeId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="termYear" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="fromStudySemester" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="toStudySemester" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="periodUsageId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="periodUsageValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="periodUsageValueDefaulttext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="defaultPeriod" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="conditionDefaulttext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="conditionUniquename" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="conditionDescription" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="courseOfStudyId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="courseOfStudyDefaulttext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="courseOfStudyUniquename" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeriodInfoWithCondition", propOrder = {
    "periodId",
    "defaultext",
    "shorttext",
    "longtext",
    "termTypeId",
    "termYear",
    "startDate",
    "endDate",
    "fromStudySemester",
    "toStudySemester",
    "periodUsageId",
    "periodUsageValueId",
    "periodUsageValueDefaulttext",
    "defaultPeriod",
    "conditionDefaulttext",
    "conditionUniquename",
    "conditionDescription",
    "courseOfStudyId",
    "courseOfStudyDefaulttext",
    "courseOfStudyUniquename"
})
public class PeriodInfoWithCondition {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long periodId;
    @XmlElement(required = true, nillable = true)
    protected String defaultext;
    @XmlElement(required = true, nillable = true)
    protected String shorttext;
    @XmlElement(required = true, nillable = true)
    protected String longtext;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long termTypeId;
    @XmlElement(required = true, nillable = true)
    protected BigInteger termYear;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    @XmlElement(required = true, nillable = true)
    protected BigInteger fromStudySemester;
    @XmlElement(required = true, nillable = true)
    protected BigInteger toStudySemester;
    protected long periodUsageId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long periodUsageValueId;
    @XmlElement(required = true, nillable = true)
    protected String periodUsageValueDefaulttext;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean defaultPeriod;
    @XmlElement(required = true, nillable = true)
    protected String conditionDefaulttext;
    @XmlElement(required = true, nillable = true)
    protected String conditionUniquename;
    @XmlElement(required = true, nillable = true)
    protected String conditionDescription;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long courseOfStudyId;
    @XmlElement(required = true, nillable = true)
    protected String courseOfStudyDefaulttext;
    @XmlElement(required = true, nillable = true)
    protected String courseOfStudyUniquename;

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
     * Ruft den Wert der defaultext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultext() {
        return defaultext;
    }

    /**
     * Legt den Wert der defaultext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultext(String value) {
        this.defaultext = value;
    }

    /**
     * Ruft den Wert der shorttext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShorttext() {
        return shorttext;
    }

    /**
     * Legt den Wert der shorttext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShorttext(String value) {
        this.shorttext = value;
    }

    /**
     * Ruft den Wert der longtext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongtext() {
        return longtext;
    }

    /**
     * Legt den Wert der longtext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongtext(String value) {
        this.longtext = value;
    }

    /**
     * Ruft den Wert der termTypeId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTermTypeId() {
        return termTypeId;
    }

    /**
     * Legt den Wert der termTypeId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTermTypeId(Long value) {
        this.termTypeId = value;
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
     * Ruft den Wert der startDate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Legt den Wert der startDate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Ruft den Wert der endDate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Legt den Wert der endDate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * Ruft den Wert der fromStudySemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFromStudySemester() {
        return fromStudySemester;
    }

    /**
     * Legt den Wert der fromStudySemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFromStudySemester(BigInteger value) {
        this.fromStudySemester = value;
    }

    /**
     * Ruft den Wert der toStudySemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getToStudySemester() {
        return toStudySemester;
    }

    /**
     * Legt den Wert der toStudySemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setToStudySemester(BigInteger value) {
        this.toStudySemester = value;
    }

    /**
     * Ruft den Wert der periodUsageId-Eigenschaft ab.
     * 
     */
    public long getPeriodUsageId() {
        return periodUsageId;
    }

    /**
     * Legt den Wert der periodUsageId-Eigenschaft fest.
     * 
     */
    public void setPeriodUsageId(long value) {
        this.periodUsageId = value;
    }

    /**
     * Ruft den Wert der periodUsageValueId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPeriodUsageValueId() {
        return periodUsageValueId;
    }

    /**
     * Legt den Wert der periodUsageValueId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPeriodUsageValueId(Long value) {
        this.periodUsageValueId = value;
    }

    /**
     * Ruft den Wert der periodUsageValueDefaulttext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodUsageValueDefaulttext() {
        return periodUsageValueDefaulttext;
    }

    /**
     * Legt den Wert der periodUsageValueDefaulttext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodUsageValueDefaulttext(String value) {
        this.periodUsageValueDefaulttext = value;
    }

    /**
     * Ruft den Wert der defaultPeriod-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDefaultPeriod() {
        return defaultPeriod;
    }

    /**
     * Legt den Wert der defaultPeriod-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDefaultPeriod(Boolean value) {
        this.defaultPeriod = value;
    }

    /**
     * Ruft den Wert der conditionDefaulttext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConditionDefaulttext() {
        return conditionDefaulttext;
    }

    /**
     * Legt den Wert der conditionDefaulttext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConditionDefaulttext(String value) {
        this.conditionDefaulttext = value;
    }

    /**
     * Ruft den Wert der conditionUniquename-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConditionUniquename() {
        return conditionUniquename;
    }

    /**
     * Legt den Wert der conditionUniquename-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConditionUniquename(String value) {
        this.conditionUniquename = value;
    }

    /**
     * Ruft den Wert der conditionDescription-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConditionDescription() {
        return conditionDescription;
    }

    /**
     * Legt den Wert der conditionDescription-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConditionDescription(String value) {
        this.conditionDescription = value;
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
     * Ruft den Wert der courseOfStudyDefaulttext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCourseOfStudyDefaulttext() {
        return courseOfStudyDefaulttext;
    }

    /**
     * Legt den Wert der courseOfStudyDefaulttext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCourseOfStudyDefaulttext(String value) {
        this.courseOfStudyDefaulttext = value;
    }

    /**
     * Ruft den Wert der courseOfStudyUniquename-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCourseOfStudyUniquename() {
        return courseOfStudyUniquename;
    }

    /**
     * Legt den Wert der courseOfStudyUniquename-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCourseOfStudyUniquename(String value) {
        this.courseOfStudyUniquename = value;
    }

}
