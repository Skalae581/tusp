
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
 * <p>Java-Klasse für StudentExisting complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="StudentExisting"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/StudentService}DtoBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="person" type="{http://www.his.de/ws/StudentService}Person70"/&gt;
 *         &lt;element name="connectedToSubject" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="degreePrograms70" type="{http://www.his.de/ws/StudentService}degreePrograms70Type"/&gt;
 *         &lt;element name="eduType" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="enrollmentdate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="exchangeprogramId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="hasLoanRequest" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="homeCountryId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="homeDistrictId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="homeDistrictObjId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="kollegsemester" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="leavesemester" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="orgunitId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="orgunitLid" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="practicalsemester" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="requestForDisenrollment" type="{http://www.his.de/ws/StudentService}RequestForDisenrollment"/&gt;
 *         &lt;element name="registrationnumber" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="semesterCountryId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="semesterDistrictId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="semesterDistrictObjId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="studystatusId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="termTypeValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="universitysemester" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="studentFunctionlocks" type="{http://www.his.de/ws/StudentService}studentFunctionlocksType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StudentExisting", propOrder = {
    "person",
    "connectedToSubject",
    "degreePrograms70",
    "eduType",
    "enrollmentdate",
    "exchangeprogramId",
    "hasLoanRequest",
    "homeCountryId",
    "homeDistrictId",
    "homeDistrictObjId",
    "kollegsemester",
    "leavesemester",
    "orgunitId",
    "orgunitLid",
    "practicalsemester",
    "requestForDisenrollment",
    "registrationnumber",
    "semesterCountryId",
    "semesterDistrictId",
    "semesterDistrictObjId",
    "studystatusId",
    "termTypeValueId",
    "universitysemester",
    "year",
    "studentFunctionlocks"
})
public class StudentExisting
    extends DtoBase
{

    @XmlElement(required = true, nillable = true)
    protected Person70 person;
    @XmlElement(required = true, nillable = true)
    protected BigInteger connectedToSubject;
    @XmlElement(required = true, nillable = true)
    protected DegreePrograms70Type degreePrograms70;
    @XmlElement(required = true, nillable = true)
    protected BigInteger eduType;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar enrollmentdate;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long exchangeprogramId;
    @XmlElement(required = true, nillable = true)
    protected BigInteger hasLoanRequest;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long homeCountryId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long homeDistrictId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long homeDistrictObjId;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal kollegsemester;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal leavesemester;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long orgunitId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long orgunitLid;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal practicalsemester;
    @XmlElement(required = true, nillable = true)
    protected RequestForDisenrollment requestForDisenrollment;
    @XmlElement(required = true, nillable = true)
    protected BigInteger registrationnumber;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long semesterCountryId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long semesterDistrictId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long semesterDistrictObjId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long studystatusId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long termTypeValueId;
    @XmlElement(required = true)
    protected BigDecimal universitysemester;
    @XmlElement(required = true, nillable = true)
    protected BigInteger year;
    @XmlElement(required = true, nillable = true)
    protected StudentFunctionlocksType studentFunctionlocks;

    /**
     * Ruft den Wert der person-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Person70 }
     *     
     */
    public Person70 getPerson() {
        return person;
    }

    /**
     * Legt den Wert der person-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Person70 }
     *     
     */
    public void setPerson(Person70 value) {
        this.person = value;
    }

    /**
     * Ruft den Wert der connectedToSubject-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getConnectedToSubject() {
        return connectedToSubject;
    }

    /**
     * Legt den Wert der connectedToSubject-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setConnectedToSubject(BigInteger value) {
        this.connectedToSubject = value;
    }

    /**
     * Ruft den Wert der degreePrograms70-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DegreePrograms70Type }
     *     
     */
    public DegreePrograms70Type getDegreePrograms70() {
        return degreePrograms70;
    }

    /**
     * Legt den Wert der degreePrograms70-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DegreePrograms70Type }
     *     
     */
    public void setDegreePrograms70(DegreePrograms70Type value) {
        this.degreePrograms70 = value;
    }

    /**
     * Ruft den Wert der eduType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEduType() {
        return eduType;
    }

    /**
     * Legt den Wert der eduType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEduType(BigInteger value) {
        this.eduType = value;
    }

    /**
     * Ruft den Wert der enrollmentdate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEnrollmentdate() {
        return enrollmentdate;
    }

    /**
     * Legt den Wert der enrollmentdate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEnrollmentdate(XMLGregorianCalendar value) {
        this.enrollmentdate = value;
    }

    /**
     * Ruft den Wert der exchangeprogramId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getExchangeprogramId() {
        return exchangeprogramId;
    }

    /**
     * Legt den Wert der exchangeprogramId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setExchangeprogramId(Long value) {
        this.exchangeprogramId = value;
    }

    /**
     * Ruft den Wert der hasLoanRequest-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getHasLoanRequest() {
        return hasLoanRequest;
    }

    /**
     * Legt den Wert der hasLoanRequest-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setHasLoanRequest(BigInteger value) {
        this.hasLoanRequest = value;
    }

    /**
     * Ruft den Wert der homeCountryId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHomeCountryId() {
        return homeCountryId;
    }

    /**
     * Legt den Wert der homeCountryId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHomeCountryId(Long value) {
        this.homeCountryId = value;
    }

    /**
     * Ruft den Wert der homeDistrictId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHomeDistrictId() {
        return homeDistrictId;
    }

    /**
     * Legt den Wert der homeDistrictId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHomeDistrictId(Long value) {
        this.homeDistrictId = value;
    }

    /**
     * Ruft den Wert der homeDistrictObjId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHomeDistrictObjId() {
        return homeDistrictObjId;
    }

    /**
     * Legt den Wert der homeDistrictObjId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHomeDistrictObjId(Long value) {
        this.homeDistrictObjId = value;
    }

    /**
     * Ruft den Wert der kollegsemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getKollegsemester() {
        return kollegsemester;
    }

    /**
     * Legt den Wert der kollegsemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setKollegsemester(BigDecimal value) {
        this.kollegsemester = value;
    }

    /**
     * Ruft den Wert der leavesemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLeavesemester() {
        return leavesemester;
    }

    /**
     * Legt den Wert der leavesemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLeavesemester(BigDecimal value) {
        this.leavesemester = value;
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
     * Ruft den Wert der practicalsemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPracticalsemester() {
        return practicalsemester;
    }

    /**
     * Legt den Wert der practicalsemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPracticalsemester(BigDecimal value) {
        this.practicalsemester = value;
    }

    /**
     * Ruft den Wert der requestForDisenrollment-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RequestForDisenrollment }
     *     
     */
    public RequestForDisenrollment getRequestForDisenrollment() {
        return requestForDisenrollment;
    }

    /**
     * Legt den Wert der requestForDisenrollment-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestForDisenrollment }
     *     
     */
    public void setRequestForDisenrollment(RequestForDisenrollment value) {
        this.requestForDisenrollment = value;
    }

    /**
     * Ruft den Wert der registrationnumber-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRegistrationnumber() {
        return registrationnumber;
    }

    /**
     * Legt den Wert der registrationnumber-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRegistrationnumber(BigInteger value) {
        this.registrationnumber = value;
    }

    /**
     * Ruft den Wert der semesterCountryId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSemesterCountryId() {
        return semesterCountryId;
    }

    /**
     * Legt den Wert der semesterCountryId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSemesterCountryId(Long value) {
        this.semesterCountryId = value;
    }

    /**
     * Ruft den Wert der semesterDistrictId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSemesterDistrictId() {
        return semesterDistrictId;
    }

    /**
     * Legt den Wert der semesterDistrictId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSemesterDistrictId(Long value) {
        this.semesterDistrictId = value;
    }

    /**
     * Ruft den Wert der semesterDistrictObjId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSemesterDistrictObjId() {
        return semesterDistrictObjId;
    }

    /**
     * Legt den Wert der semesterDistrictObjId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSemesterDistrictObjId(Long value) {
        this.semesterDistrictObjId = value;
    }

    /**
     * Ruft den Wert der studystatusId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStudystatusId() {
        return studystatusId;
    }

    /**
     * Legt den Wert der studystatusId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStudystatusId(Long value) {
        this.studystatusId = value;
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
     * Ruft den Wert der universitysemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUniversitysemester() {
        return universitysemester;
    }

    /**
     * Legt den Wert der universitysemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUniversitysemester(BigDecimal value) {
        this.universitysemester = value;
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
     * Ruft den Wert der studentFunctionlocks-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link StudentFunctionlocksType }
     *     
     */
    public StudentFunctionlocksType getStudentFunctionlocks() {
        return studentFunctionlocks;
    }

    /**
     * Legt den Wert der studentFunctionlocks-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link StudentFunctionlocksType }
     *     
     */
    public void setStudentFunctionlocks(StudentFunctionlocksType value) {
        this.studentFunctionlocks = value;
    }

}
