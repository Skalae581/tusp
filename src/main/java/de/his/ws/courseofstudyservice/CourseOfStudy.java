
package de.his.ws.courseofstudyservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für CourseOfStudy complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="CourseOfStudy"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="academicdegreeValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="admissionToStudyId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="astatDegree" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="astatSubject" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="combinationParameters" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="courseOfStudyTypeValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="courseSpecializationId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="courseSpecializationLid" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="defaultlanguage" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="defaulttext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="degreeId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="degreeLid" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="enrollmentId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="examinationversionId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="formOfStudiesId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="isAdmissionToStudy" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="isCourseOfStudyStart" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="lid" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="longtext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="majorFieldOfStudyId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="majorFieldOfStudyLid" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="objectLocale" type="{http://www.w3.org/2001/XMLSchema}language"/&gt;
 *         &lt;element name="orgunitId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="orgunitLid" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="partOfStudies" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="partTimePercentage" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="placeOfStudiesId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="regularNumberOfSemesters" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="shorttext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="subjectId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="subjectIndicatorId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="subjectLid" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="teachingunitOrgunitId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="teachingunitOrgunitLid" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="typeOfStudyId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="uniquename" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="validFrom" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="validFromTermTermTypeValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="validFromTermYear" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="validTo" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="validToTermTermTypeValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="validToTermYear" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CourseOfStudy", propOrder = {
    "id",
    "academicdegreeValueId",
    "admissionToStudyId",
    "astatDegree",
    "astatSubject",
    "combinationParameters",
    "courseOfStudyTypeValueId",
    "courseSpecializationId",
    "courseSpecializationLid",
    "defaultlanguage",
    "defaulttext",
    "degreeId",
    "degreeLid",
    "enrollmentId",
    "examinationversionId",
    "formOfStudiesId",
    "isAdmissionToStudy",
    "isCourseOfStudyStart",
    "lid",
    "longtext",
    "majorFieldOfStudyId",
    "majorFieldOfStudyLid",
    "objectLocale",
    "orgunitId",
    "orgunitLid",
    "partOfStudies",
    "partTimePercentage",
    "placeOfStudiesId",
    "regularNumberOfSemesters",
    "shorttext",
    "subjectId",
    "subjectIndicatorId",
    "subjectLid",
    "teachingunitOrgunitId",
    "teachingunitOrgunitLid",
    "typeOfStudyId",
    "uniquename",
    "validFrom",
    "validFromTermTermTypeValueId",
    "validFromTermYear",
    "validTo",
    "validToTermTermTypeValueId",
    "validToTermYear"
})
public class CourseOfStudy {

    protected long id;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long academicdegreeValueId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long admissionToStudyId;
    @XmlElement(required = true, nillable = true)
    protected String astatDegree;
    @XmlElement(required = true, nillable = true)
    protected String astatSubject;
    @XmlElement(required = true, nillable = true)
    protected String combinationParameters;
    protected long courseOfStudyTypeValueId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long courseSpecializationId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long courseSpecializationLid;
    protected long defaultlanguage;
    @XmlElement(required = true)
    protected String defaulttext;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long degreeId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long degreeLid;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long enrollmentId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long examinationversionId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long formOfStudiesId;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean isAdmissionToStudy;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean isCourseOfStudyStart;
    protected long lid;
    @XmlElement(required = true, nillable = true)
    protected String longtext;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long majorFieldOfStudyId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long majorFieldOfStudyLid;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String objectLocale;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long orgunitId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long orgunitLid;
    @XmlElement(required = true, nillable = true)
    protected BigInteger partOfStudies;
    @XmlElement(required = true, nillable = true)
    protected BigInteger partTimePercentage;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long placeOfStudiesId;
    @XmlElement(required = true, nillable = true)
    protected BigInteger regularNumberOfSemesters;
    @XmlElement(required = true, nillable = true)
    protected String shorttext;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long subjectId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long subjectIndicatorId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long subjectLid;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long teachingunitOrgunitId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long teachingunitOrgunitLid;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long typeOfStudyId;
    @XmlElement(required = true, nillable = true)
    protected String uniquename;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar validFrom;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long validFromTermTermTypeValueId;
    @XmlElement(required = true, nillable = true)
    protected BigInteger validFromTermYear;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar validTo;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long validToTermTermTypeValueId;
    @XmlElement(required = true, nillable = true)
    protected BigInteger validToTermYear;

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
     * Ruft den Wert der academicdegreeValueId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAcademicdegreeValueId() {
        return academicdegreeValueId;
    }

    /**
     * Legt den Wert der academicdegreeValueId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAcademicdegreeValueId(Long value) {
        this.academicdegreeValueId = value;
    }

    /**
     * Ruft den Wert der admissionToStudyId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAdmissionToStudyId() {
        return admissionToStudyId;
    }

    /**
     * Legt den Wert der admissionToStudyId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAdmissionToStudyId(Long value) {
        this.admissionToStudyId = value;
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
     * Ruft den Wert der courseOfStudyTypeValueId-Eigenschaft ab.
     * 
     */
    public long getCourseOfStudyTypeValueId() {
        return courseOfStudyTypeValueId;
    }

    /**
     * Legt den Wert der courseOfStudyTypeValueId-Eigenschaft fest.
     * 
     */
    public void setCourseOfStudyTypeValueId(long value) {
        this.courseOfStudyTypeValueId = value;
    }

    /**
     * Ruft den Wert der courseSpecializationId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCourseSpecializationId() {
        return courseSpecializationId;
    }

    /**
     * Legt den Wert der courseSpecializationId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCourseSpecializationId(Long value) {
        this.courseSpecializationId = value;
    }

    /**
     * Ruft den Wert der courseSpecializationLid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCourseSpecializationLid() {
        return courseSpecializationLid;
    }

    /**
     * Legt den Wert der courseSpecializationLid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCourseSpecializationLid(Long value) {
        this.courseSpecializationLid = value;
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
     * Ruft den Wert der degreeId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDegreeId() {
        return degreeId;
    }

    /**
     * Legt den Wert der degreeId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDegreeId(Long value) {
        this.degreeId = value;
    }

    /**
     * Ruft den Wert der degreeLid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDegreeLid() {
        return degreeLid;
    }

    /**
     * Legt den Wert der degreeLid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDegreeLid(Long value) {
        this.degreeLid = value;
    }

    /**
     * Ruft den Wert der enrollmentId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEnrollmentId() {
        return enrollmentId;
    }

    /**
     * Legt den Wert der enrollmentId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEnrollmentId(Long value) {
        this.enrollmentId = value;
    }

    /**
     * Ruft den Wert der examinationversionId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getExaminationversionId() {
        return examinationversionId;
    }

    /**
     * Legt den Wert der examinationversionId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setExaminationversionId(Long value) {
        this.examinationversionId = value;
    }

    /**
     * Ruft den Wert der formOfStudiesId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFormOfStudiesId() {
        return formOfStudiesId;
    }

    /**
     * Legt den Wert der formOfStudiesId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFormOfStudiesId(Long value) {
        this.formOfStudiesId = value;
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
     * Ruft den Wert der lid-Eigenschaft ab.
     * 
     */
    public long getLid() {
        return lid;
    }

    /**
     * Legt den Wert der lid-Eigenschaft fest.
     * 
     */
    public void setLid(long value) {
        this.lid = value;
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
     * Ruft den Wert der majorFieldOfStudyId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMajorFieldOfStudyId() {
        return majorFieldOfStudyId;
    }

    /**
     * Legt den Wert der majorFieldOfStudyId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMajorFieldOfStudyId(Long value) {
        this.majorFieldOfStudyId = value;
    }

    /**
     * Ruft den Wert der majorFieldOfStudyLid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMajorFieldOfStudyLid() {
        return majorFieldOfStudyLid;
    }

    /**
     * Legt den Wert der majorFieldOfStudyLid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMajorFieldOfStudyLid(Long value) {
        this.majorFieldOfStudyLid = value;
    }

    /**
     * Ruft den Wert der objectLocale-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectLocale() {
        return objectLocale;
    }

    /**
     * Legt den Wert der objectLocale-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectLocale(String value) {
        this.objectLocale = value;
    }

    /**
     * Ruft den Wert der orgunitId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOrgunitId() {
        return orgunitId;
    }

    /**
     * Legt den Wert der orgunitId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOrgunitId(Long value) {
        this.orgunitId = value;
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
     * Ruft den Wert der placeOfStudiesId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPlaceOfStudiesId() {
        return placeOfStudiesId;
    }

    /**
     * Legt den Wert der placeOfStudiesId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPlaceOfStudiesId(Long value) {
        this.placeOfStudiesId = value;
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
     * Ruft den Wert der subjectIndicatorId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSubjectIndicatorId() {
        return subjectIndicatorId;
    }

    /**
     * Legt den Wert der subjectIndicatorId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSubjectIndicatorId(Long value) {
        this.subjectIndicatorId = value;
    }

    /**
     * Ruft den Wert der subjectLid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSubjectLid() {
        return subjectLid;
    }

    /**
     * Legt den Wert der subjectLid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSubjectLid(Long value) {
        this.subjectLid = value;
    }

    /**
     * Ruft den Wert der teachingunitOrgunitId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTeachingunitOrgunitId() {
        return teachingunitOrgunitId;
    }

    /**
     * Legt den Wert der teachingunitOrgunitId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTeachingunitOrgunitId(Long value) {
        this.teachingunitOrgunitId = value;
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
     * Ruft den Wert der typeOfStudyId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTypeOfStudyId() {
        return typeOfStudyId;
    }

    /**
     * Legt den Wert der typeOfStudyId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTypeOfStudyId(Long value) {
        this.typeOfStudyId = value;
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
     * Ruft den Wert der validFromTermTermTypeValueId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValidFromTermTermTypeValueId() {
        return validFromTermTermTypeValueId;
    }

    /**
     * Legt den Wert der validFromTermTermTypeValueId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValidFromTermTermTypeValueId(Long value) {
        this.validFromTermTermTypeValueId = value;
    }

    /**
     * Ruft den Wert der validFromTermYear-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getValidFromTermYear() {
        return validFromTermYear;
    }

    /**
     * Legt den Wert der validFromTermYear-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setValidFromTermYear(BigInteger value) {
        this.validFromTermYear = value;
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

    /**
     * Ruft den Wert der validToTermTermTypeValueId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValidToTermTermTypeValueId() {
        return validToTermTermTypeValueId;
    }

    /**
     * Legt den Wert der validToTermTermTypeValueId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValidToTermTermTypeValueId(Long value) {
        this.validToTermTermTypeValueId = value;
    }

    /**
     * Ruft den Wert der validToTermYear-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getValidToTermYear() {
        return validToTermYear;
    }

    /**
     * Legt den Wert der validToTermYear-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setValidToTermYear(BigInteger value) {
        this.validToTermYear = value;
    }

}
