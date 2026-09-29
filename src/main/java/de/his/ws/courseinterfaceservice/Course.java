
package de.his.ws.courseinterfaceservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * course-object with all semester independent information
 * 
 * <p>Java-Klasse für Course complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Course"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="objGuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="academicYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="classAttendance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="compulsoryRequirement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="credits" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="directive" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="eventtypeId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="examinationAchievement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="externOrganizer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="frequencyOfOffervalueId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="grading" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="independentStudy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="intendedSemester" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="scheduledGroupSize" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="targetGroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="teachingKLanguageId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
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
@XmlType(name = "Course", propOrder = {
    "id",
    "objGuid",
    "academicYear",
    "classAttendance",
    "compulsoryRequirement",
    "credits",
    "directive",
    "eventtypeId",
    "examinationAchievement",
    "externOrganizer",
    "frequencyOfOffervalueId",
    "grading",
    "independentStudy",
    "intendedSemester",
    "scheduledGroupSize",
    "targetGroup",
    "teachingKLanguageId",
    "teachingMethod",
    "workload"
})
public class Course {

    @XmlElement(nillable = true)
    protected Long id;
    @XmlElement(nillable = true)
    protected String objGuid;
    @XmlElement(nillable = true)
    protected String academicYear;
    @XmlElement(nillable = true)
    protected String classAttendance;
    @XmlElement(nillable = true)
    protected String compulsoryRequirement;
    @XmlElement(nillable = true)
    protected String credits;
    @XmlElement(nillable = true)
    protected String directive;
    protected long eventtypeId;
    @XmlElement(nillable = true)
    protected String examinationAchievement;
    @XmlElement(nillable = true)
    protected String externOrganizer;
    @XmlElement(nillable = true)
    protected Long frequencyOfOffervalueId;
    @XmlElement(nillable = true)
    protected String grading;
    @XmlElement(nillable = true)
    protected String independentStudy;
    @XmlElement(nillable = true)
    protected String intendedSemester;
    @XmlElement(nillable = true)
    protected BigInteger scheduledGroupSize;
    @XmlElement(nillable = true)
    protected String targetGroup;
    @XmlElement(nillable = true)
    protected Long teachingKLanguageId;
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
     * Ruft den Wert der classAttendance-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassAttendance() {
        return classAttendance;
    }

    /**
     * Legt den Wert der classAttendance-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassAttendance(String value) {
        this.classAttendance = value;
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
     * Ruft den Wert der directive-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirective() {
        return directive;
    }

    /**
     * Legt den Wert der directive-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirective(String value) {
        this.directive = value;
    }

    /**
     * Ruft den Wert der eventtypeId-Eigenschaft ab.
     * 
     */
    public long getEventtypeId() {
        return eventtypeId;
    }

    /**
     * Legt den Wert der eventtypeId-Eigenschaft fest.
     * 
     */
    public void setEventtypeId(long value) {
        this.eventtypeId = value;
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
     * Ruft den Wert der frequencyOfOffervalueId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFrequencyOfOffervalueId() {
        return frequencyOfOffervalueId;
    }

    /**
     * Legt den Wert der frequencyOfOffervalueId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFrequencyOfOffervalueId(Long value) {
        this.frequencyOfOffervalueId = value;
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
     * Ruft den Wert der independentStudy-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndependentStudy() {
        return independentStudy;
    }

    /**
     * Legt den Wert der independentStudy-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndependentStudy(String value) {
        this.independentStudy = value;
    }

    /**
     * Ruft den Wert der intendedSemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntendedSemester() {
        return intendedSemester;
    }

    /**
     * Legt den Wert der intendedSemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntendedSemester(String value) {
        this.intendedSemester = value;
    }

    /**
     * Ruft den Wert der scheduledGroupSize-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getScheduledGroupSize() {
        return scheduledGroupSize;
    }

    /**
     * Legt den Wert der scheduledGroupSize-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setScheduledGroupSize(BigInteger value) {
        this.scheduledGroupSize = value;
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
     * Ruft den Wert der teachingKLanguageId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTeachingKLanguageId() {
        return teachingKLanguageId;
    }

    /**
     * Legt den Wert der teachingKLanguageId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTeachingKLanguageId(Long value) {
        this.teachingKLanguageId = value;
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
