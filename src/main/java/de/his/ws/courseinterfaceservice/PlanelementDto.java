
package de.his.ws.courseinterfaceservice;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * planelement-object with all specific information
 * 
 * <p>Java-Klasse für PlanelementDto complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PlanelementDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="objGuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="attendeeMaximum" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="attendeeMinimum" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="cancelled" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="defaultlanguage" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="defaulttext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="genderId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="hoursPerWeek" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="longtext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="parallelgroupId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="shorttext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="unitId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="termTypeValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="academicYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="compulsoryRequirement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="credits" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="examinationAchievement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="externOrganizer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="grading" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="targetGroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="teachingLanguageId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="teachingMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="workload" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlanelementDto", propOrder = {
    "id",
    "objGuid",
    "attendeeMaximum",
    "attendeeMinimum",
    "cancelled",
    "defaultlanguage",
    "defaulttext",
    "genderId",
    "hoursPerWeek",
    "longtext",
    "parallelgroupId",
    "shorttext",
    "unitId",
    "termTypeValueId",
    "year",
    "academicYear",
    "compulsoryRequirement",
    "credits",
    "examinationAchievement",
    "externOrganizer",
    "grading",
    "targetGroup",
    "teachingLanguageId",
    "teachingMethod",
    "workload"
})
public class PlanelementDto {

    @XmlElement(nillable = true)
    protected Long id;
    @XmlElement(nillable = true)
    protected String objGuid;
    @XmlElement(nillable = true)
    protected BigInteger attendeeMaximum;
    @XmlElement(nillable = true)
    protected BigInteger attendeeMinimum;
    @XmlElement(nillable = true)
    protected Long cancelled;
    protected long defaultlanguage;
    @XmlElement(required = true)
    protected String defaulttext;
    @XmlElement(nillable = true)
    protected Long genderId;
    @XmlElement(nillable = true)
    protected BigDecimal hoursPerWeek;
    @XmlElement(nillable = true)
    protected String longtext;
    @XmlElement(nillable = true)
    protected Long parallelgroupId;
    @XmlElement(nillable = true)
    protected String shorttext;
    protected long unitId;
    protected long termTypeValueId;
    @XmlElement(required = true)
    protected BigInteger year;
    @XmlElement(nillable = true)
    protected String academicYear;
    @XmlElement(nillable = true)
    protected String compulsoryRequirement;
    @XmlElement(nillable = true)
    protected String credits;
    @XmlElement(nillable = true)
    protected String examinationAchievement;
    @XmlElement(nillable = true)
    protected String externOrganizer;
    @XmlElement(nillable = true)
    protected String grading;
    @XmlElement(nillable = true)
    protected String targetGroup;
    @XmlElement(nillable = true)
    protected Long teachingLanguageId;
    @XmlElement(nillable = true)
    protected String teachingMethod;
    @XmlElement(nillable = true)
    protected String workload;

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
     * Ruft den Wert der attendeeMaximum-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAttendeeMaximum() {
        return attendeeMaximum;
    }

    /**
     * Legt den Wert der attendeeMaximum-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAttendeeMaximum(BigInteger value) {
        this.attendeeMaximum = value;
    }

    /**
     * Ruft den Wert der attendeeMinimum-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAttendeeMinimum() {
        return attendeeMinimum;
    }

    /**
     * Legt den Wert der attendeeMinimum-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAttendeeMinimum(BigInteger value) {
        this.attendeeMinimum = value;
    }

    /**
     * Ruft den Wert der cancelled-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCancelled() {
        return cancelled;
    }

    /**
     * Legt den Wert der cancelled-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCancelled(Long value) {
        this.cancelled = value;
    }

    /**
     * Ruft den Wert der defaultlanguage-Eigenschaft ab.
     * 
     */
    public long getDefaultlanguage() {
        return defaultlanguage;
    }

    /**
     * Legt den Wert der defaultlanguage-Eigenschaft fest.
     * 
     */
    public void setDefaultlanguage(long value) {
        this.defaultlanguage = value;
    }

    /**
     * Ruft den Wert der defaulttext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaulttext() {
        return defaulttext;
    }

    /**
     * Legt den Wert der defaulttext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaulttext(String value) {
        this.defaulttext = value;
    }

    /**
     * Ruft den Wert der genderId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getGenderId() {
        return genderId;
    }

    /**
     * Legt den Wert der genderId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setGenderId(Long value) {
        this.genderId = value;
    }

    /**
     * Ruft den Wert der hoursPerWeek-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getHoursPerWeek() {
        return hoursPerWeek;
    }

    /**
     * Legt den Wert der hoursPerWeek-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setHoursPerWeek(BigDecimal value) {
        this.hoursPerWeek = value;
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
     * Ruft den Wert der parallelgroupId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getParallelgroupId() {
        return parallelgroupId;
    }

    /**
     * Legt den Wert der parallelgroupId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setParallelgroupId(Long value) {
        this.parallelgroupId = value;
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
     * Ruft den Wert der unitId-Eigenschaft ab.
     * 
     */
    public long getUnitId() {
        return unitId;
    }

    /**
     * Legt den Wert der unitId-Eigenschaft fest.
     * 
     */
    public void setUnitId(long value) {
        this.unitId = value;
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
     * Ruft den Wert der academicYear-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcademicYear() {
        return academicYear;
    }

    /**
     * Legt den Wert der academicYear-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcademicYear(String value) {
        this.academicYear = value;
    }

    /**
     * Ruft den Wert der compulsoryRequirement-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompulsoryRequirement() {
        return compulsoryRequirement;
    }

    /**
     * Legt den Wert der compulsoryRequirement-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompulsoryRequirement(String value) {
        this.compulsoryRequirement = value;
    }

    /**
     * Ruft den Wert der credits-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCredits() {
        return credits;
    }

    /**
     * Legt den Wert der credits-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCredits(String value) {
        this.credits = value;
    }

    /**
     * Ruft den Wert der examinationAchievement-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExaminationAchievement() {
        return examinationAchievement;
    }

    /**
     * Legt den Wert der examinationAchievement-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExaminationAchievement(String value) {
        this.examinationAchievement = value;
    }

    /**
     * Ruft den Wert der externOrganizer-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternOrganizer() {
        return externOrganizer;
    }

    /**
     * Legt den Wert der externOrganizer-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternOrganizer(String value) {
        this.externOrganizer = value;
    }

    /**
     * Ruft den Wert der grading-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrading() {
        return grading;
    }

    /**
     * Legt den Wert der grading-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrading(String value) {
        this.grading = value;
    }

    /**
     * Ruft den Wert der targetGroup-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetGroup() {
        return targetGroup;
    }

    /**
     * Legt den Wert der targetGroup-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetGroup(String value) {
        this.targetGroup = value;
    }

    /**
     * Ruft den Wert der teachingLanguageId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTeachingLanguageId() {
        return teachingLanguageId;
    }

    /**
     * Legt den Wert der teachingLanguageId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTeachingLanguageId(Long value) {
        this.teachingLanguageId = value;
    }

    /**
     * Ruft den Wert der teachingMethod-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeachingMethod() {
        return teachingMethod;
    }

    /**
     * Legt den Wert der teachingMethod-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeachingMethod(String value) {
        this.teachingMethod = value;
    }

    /**
     * Ruft den Wert der workload-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkload() {
        return workload;
    }

    /**
     * Legt den Wert der workload-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkload(String value) {
        this.workload = value;
    }

}
