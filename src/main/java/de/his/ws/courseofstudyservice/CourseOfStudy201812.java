
package de.his.ws.courseofstudyservice;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für CourseOfStudy201812 complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="CourseOfStudy201812"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="lid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="objGuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="uniquename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="degree" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="majorFieldOfStudy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="courseSpecialization" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="subjectIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="examinationversion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="placeOfStudies" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="typeOfStudy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="enrollment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="formOfStudies" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="shorttext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="defaulttext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="longtext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="examinationOfficeNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="regularNumberOfSemesters" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="toleranceSemester" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="partOfStudies" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="isAdmissionToStudy" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="isCourseOfStudyStart" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="orgunitLid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="teachingunitOrgunitLid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="courseOfStudyTypeValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="academicdegreeValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="partTimePercentage" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="combinationParameters" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="astatDegree" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="astatSubject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="lockCurricula" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="lockCurriculaNotificationStu" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="lockCurriculaNotificationExa" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="fieldOfStudy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="typeOfDoctorate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="courseFee" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="furtherEducationAccountId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="dosvKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="validFrom" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="validTo" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CourseOfStudy201812", propOrder = {
    "id",
    "lid",
    "objGuid",
    "uniquename",
    "degree",
    "subject",
    "majorFieldOfStudy",
    "courseSpecialization",
    "subjectIndicator",
    "examinationversion",
    "placeOfStudies",
    "typeOfStudy",
    "enrollment",
    "formOfStudies",
    "shorttext",
    "defaulttext",
    "longtext",
    "examinationOfficeNo",
    "regularNumberOfSemesters",
    "toleranceSemester",
    "partOfStudies",
    "isAdmissionToStudy",
    "isCourseOfStudyStart",
    "orgunitLid",
    "teachingunitOrgunitLid",
    "courseOfStudyTypeValue",
    "academicdegreeValue",
    "partTimePercentage",
    "combinationParameters",
    "astatDegree",
    "astatSubject",
    "lockCurricula",
    "lockCurriculaNotificationStu",
    "lockCurriculaNotificationExa",
    "fieldOfStudy",
    "typeOfDoctorate",
    "courseFee",
    "furtherEducationAccountId",
    "dosvKey",
    "validFrom",
    "validTo"
})
public class CourseOfStudy201812 {

    protected Long id;
    @XmlElement(nillable = true)
    protected Long lid;
    @XmlElement(nillable = true)
    protected String objGuid;
    @XmlElement(nillable = true)
    protected String uniquename;
    @XmlElement(nillable = true)
    protected String degree;
    @XmlElement(nillable = true)
    protected String subject;
    @XmlElement(nillable = true)
    protected String majorFieldOfStudy;
    @XmlElement(nillable = true)
    protected String courseSpecialization;
    @XmlElement(nillable = true)
    protected String subjectIndicator;
    @XmlElement(nillable = true)
    protected String examinationversion;
    @XmlElement(nillable = true)
    protected String placeOfStudies;
    @XmlElement(nillable = true)
    protected String typeOfStudy;
    @XmlElement(nillable = true)
    protected String enrollment;
    @XmlElement(nillable = true)
    protected String formOfStudies;
    @XmlElement(nillable = true)
    protected String shorttext;
    protected String defaulttext;
    @XmlElement(nillable = true)
    protected String longtext;
    @XmlElement(nillable = true)
    protected String examinationOfficeNo;
    @XmlElement(nillable = true)
    protected BigInteger regularNumberOfSemesters;
    @XmlElement(nillable = true)
    protected BigInteger toleranceSemester;
    protected BigInteger partOfStudies;
    @XmlElement(nillable = true)
    protected Boolean isAdmissionToStudy;
    @XmlElement(nillable = true)
    protected Boolean isCourseOfStudyStart;
    @XmlElement(nillable = true)
    protected Long orgunitLid;
    @XmlElement(nillable = true)
    protected Long teachingunitOrgunitLid;
    protected String courseOfStudyTypeValue;
    @XmlElement(nillable = true)
    protected String academicdegreeValue;
    @XmlElement(nillable = true)
    protected BigInteger partTimePercentage;
    @XmlElement(nillable = true)
    protected String combinationParameters;
    @XmlElement(nillable = true)
    protected String astatDegree;
    @XmlElement(nillable = true)
    protected String astatSubject;
    @XmlElement(nillable = true)
    protected Boolean lockCurricula;
    @XmlElement(nillable = true)
    protected Boolean lockCurriculaNotificationStu;
    @XmlElement(nillable = true)
    protected Boolean lockCurriculaNotificationExa;
    @XmlElement(nillable = true)
    protected String fieldOfStudy;
    @XmlElement(nillable = true)
    protected String typeOfDoctorate;
    @XmlElement(nillable = true)
    protected BigDecimal courseFee;
    @XmlElement(nillable = true)
    protected Long furtherEducationAccountId;
    @XmlElement(nillable = true)
    protected String dosvKey;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar validFrom;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar validTo;

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
     * Ruft den Wert der lid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLid() {
        return lid;
    }

    /**
     * Legt den Wert der lid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLid(Long value) {
        this.lid = value;
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
     * Ruft den Wert der uniquename-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUniquename() {
        return uniquename;
    }

    /**
     * Legt den Wert der uniquename-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUniquename(String value) {
        this.uniquename = value;
    }

    /**
     * Ruft den Wert der degree-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDegree() {
        return degree;
    }

    /**
     * Legt den Wert der degree-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDegree(String value) {
        this.degree = value;
    }

    /**
     * Ruft den Wert der subject-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Legt den Wert der subject-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Ruft den Wert der majorFieldOfStudy-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMajorFieldOfStudy() {
        return majorFieldOfStudy;
    }

    /**
     * Legt den Wert der majorFieldOfStudy-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMajorFieldOfStudy(String value) {
        this.majorFieldOfStudy = value;
    }

    /**
     * Ruft den Wert der courseSpecialization-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCourseSpecialization() {
        return courseSpecialization;
    }

    /**
     * Legt den Wert der courseSpecialization-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCourseSpecialization(String value) {
        this.courseSpecialization = value;
    }

    /**
     * Ruft den Wert der subjectIndicator-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubjectIndicator() {
        return subjectIndicator;
    }

    /**
     * Legt den Wert der subjectIndicator-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubjectIndicator(String value) {
        this.subjectIndicator = value;
    }

    /**
     * Ruft den Wert der examinationversion-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExaminationversion() {
        return examinationversion;
    }

    /**
     * Legt den Wert der examinationversion-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExaminationversion(String value) {
        this.examinationversion = value;
    }

    /**
     * Ruft den Wert der placeOfStudies-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlaceOfStudies() {
        return placeOfStudies;
    }

    /**
     * Legt den Wert der placeOfStudies-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlaceOfStudies(String value) {
        this.placeOfStudies = value;
    }

    /**
     * Ruft den Wert der typeOfStudy-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeOfStudy() {
        return typeOfStudy;
    }

    /**
     * Legt den Wert der typeOfStudy-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeOfStudy(String value) {
        this.typeOfStudy = value;
    }

    /**
     * Ruft den Wert der enrollment-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnrollment() {
        return enrollment;
    }

    /**
     * Legt den Wert der enrollment-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnrollment(String value) {
        this.enrollment = value;
    }

    /**
     * Ruft den Wert der formOfStudies-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormOfStudies() {
        return formOfStudies;
    }

    /**
     * Legt den Wert der formOfStudies-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormOfStudies(String value) {
        this.formOfStudies = value;
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
     * Ruft den Wert der examinationOfficeNo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExaminationOfficeNo() {
        return examinationOfficeNo;
    }

    /**
     * Legt den Wert der examinationOfficeNo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExaminationOfficeNo(String value) {
        this.examinationOfficeNo = value;
    }

    /**
     * Ruft den Wert der regularNumberOfSemesters-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRegularNumberOfSemesters() {
        return regularNumberOfSemesters;
    }

    /**
     * Legt den Wert der regularNumberOfSemesters-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRegularNumberOfSemesters(BigInteger value) {
        this.regularNumberOfSemesters = value;
    }

    /**
     * Ruft den Wert der toleranceSemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getToleranceSemester() {
        return toleranceSemester;
    }

    /**
     * Legt den Wert der toleranceSemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setToleranceSemester(BigInteger value) {
        this.toleranceSemester = value;
    }

    /**
     * Ruft den Wert der partOfStudies-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPartOfStudies() {
        return partOfStudies;
    }

    /**
     * Legt den Wert der partOfStudies-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPartOfStudies(BigInteger value) {
        this.partOfStudies = value;
    }

    /**
     * Ruft den Wert der isAdmissionToStudy-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsAdmissionToStudy() {
        return isAdmissionToStudy;
    }

    /**
     * Legt den Wert der isAdmissionToStudy-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsAdmissionToStudy(Boolean value) {
        this.isAdmissionToStudy = value;
    }

    /**
     * Ruft den Wert der isCourseOfStudyStart-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsCourseOfStudyStart() {
        return isCourseOfStudyStart;
    }

    /**
     * Legt den Wert der isCourseOfStudyStart-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsCourseOfStudyStart(Boolean value) {
        this.isCourseOfStudyStart = value;
    }

    /**
     * Ruft den Wert der orgunitLid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOrgunitLid() {
        return orgunitLid;
    }

    /**
     * Legt den Wert der orgunitLid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOrgunitLid(Long value) {
        this.orgunitLid = value;
    }

    /**
     * Ruft den Wert der teachingunitOrgunitLid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTeachingunitOrgunitLid() {
        return teachingunitOrgunitLid;
    }

    /**
     * Legt den Wert der teachingunitOrgunitLid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTeachingunitOrgunitLid(Long value) {
        this.teachingunitOrgunitLid = value;
    }

    /**
     * Ruft den Wert der courseOfStudyTypeValue-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCourseOfStudyTypeValue() {
        return courseOfStudyTypeValue;
    }

    /**
     * Legt den Wert der courseOfStudyTypeValue-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCourseOfStudyTypeValue(String value) {
        this.courseOfStudyTypeValue = value;
    }

    /**
     * Ruft den Wert der academicdegreeValue-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcademicdegreeValue() {
        return academicdegreeValue;
    }

    /**
     * Legt den Wert der academicdegreeValue-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcademicdegreeValue(String value) {
        this.academicdegreeValue = value;
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
     * Ruft den Wert der combinationParameters-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCombinationParameters() {
        return combinationParameters;
    }

    /**
     * Legt den Wert der combinationParameters-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCombinationParameters(String value) {
        this.combinationParameters = value;
    }

    /**
     * Ruft den Wert der astatDegree-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAstatDegree() {
        return astatDegree;
    }

    /**
     * Legt den Wert der astatDegree-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAstatDegree(String value) {
        this.astatDegree = value;
    }

    /**
     * Ruft den Wert der astatSubject-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAstatSubject() {
        return astatSubject;
    }

    /**
     * Legt den Wert der astatSubject-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAstatSubject(String value) {
        this.astatSubject = value;
    }

    /**
     * Ruft den Wert der lockCurricula-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLockCurricula() {
        return lockCurricula;
    }

    /**
     * Legt den Wert der lockCurricula-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLockCurricula(Boolean value) {
        this.lockCurricula = value;
    }

    /**
     * Ruft den Wert der lockCurriculaNotificationStu-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLockCurriculaNotificationStu() {
        return lockCurriculaNotificationStu;
    }

    /**
     * Legt den Wert der lockCurriculaNotificationStu-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLockCurriculaNotificationStu(Boolean value) {
        this.lockCurriculaNotificationStu = value;
    }

    /**
     * Ruft den Wert der lockCurriculaNotificationExa-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLockCurriculaNotificationExa() {
        return lockCurriculaNotificationExa;
    }

    /**
     * Legt den Wert der lockCurriculaNotificationExa-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLockCurriculaNotificationExa(Boolean value) {
        this.lockCurriculaNotificationExa = value;
    }

    /**
     * Ruft den Wert der fieldOfStudy-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    /**
     * Legt den Wert der fieldOfStudy-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldOfStudy(String value) {
        this.fieldOfStudy = value;
    }

    /**
     * Ruft den Wert der typeOfDoctorate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeOfDoctorate() {
        return typeOfDoctorate;
    }

    /**
     * Legt den Wert der typeOfDoctorate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeOfDoctorate(String value) {
        this.typeOfDoctorate = value;
    }

    /**
     * Ruft den Wert der courseFee-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCourseFee() {
        return courseFee;
    }

    /**
     * Legt den Wert der courseFee-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCourseFee(BigDecimal value) {
        this.courseFee = value;
    }

    /**
     * Ruft den Wert der furtherEducationAccountId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFurtherEducationAccountId() {
        return furtherEducationAccountId;
    }

    /**
     * Legt den Wert der furtherEducationAccountId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFurtherEducationAccountId(Long value) {
        this.furtherEducationAccountId = value;
    }

    /**
     * Ruft den Wert der dosvKey-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDosvKey() {
        return dosvKey;
    }

    /**
     * Legt den Wert der dosvKey-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDosvKey(String value) {
        this.dosvKey = value;
    }

    /**
     * Ruft den Wert der validFrom-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValidFrom() {
        return validFrom;
    }

    /**
     * Legt den Wert der validFrom-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValidFrom(XMLGregorianCalendar value) {
        this.validFrom = value;
    }

    /**
     * Ruft den Wert der validTo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValidTo() {
        return validTo;
    }

    /**
     * Legt den Wert der validTo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValidTo(XMLGregorianCalendar value) {
        this.validTo = value;
    }

}
