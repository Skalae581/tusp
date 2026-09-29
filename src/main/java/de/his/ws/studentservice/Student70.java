
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
 * <p>Java-Klasse für Student70 complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Student70"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="studentId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="registrationnumber" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="personId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="firstname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="allfirstnames" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dateofbirth" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="genderId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="birthname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="artistname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nameprefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="namesuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="academicdegreesuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="academicdegreeId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="titleId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="birthcity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="countryId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="nationalityId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="secondNationalityId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="selfregistrationStatusId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="connectedToSubject" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="degreePrograms70" type="{http://www.his.de/ws/StudentService}degreePrograms70Type"/&gt;
 *         &lt;element name="requestForDisenrollment" type="{http://www.his.de/ws/StudentService}RequestForDisenrollment" minOccurs="0"/&gt;
 *         &lt;element name="eduType" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="enrollmentdate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="exchangeprogramId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="hasLoanRequest" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="homeCountryId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="homeDistrictId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="homeDistrictObjId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="kollegsemester" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="leavesemester" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="orgunitLid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="practicalsemester" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="reasonOfDisenrollmentId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="semesterCountryId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="semesterDistrictId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="semesterDistrictObjId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="universitysemester" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="studystatusId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="termTypeValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="accounts" type="{http://www.his.de/ws/StudentService}accountsType" minOccurs="0"/&gt;
 *         &lt;element name="addresses" type="{http://www.his.de/ws/StudentService}addressesType" minOccurs="0"/&gt;
 *         &lt;element name="personattributes" type="{http://www.his.de/ws/StudentService}personattributesType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Student70", propOrder = {
    "studentId",
    "registrationnumber",
    "personId",
    "firstname",
    "surname",
    "allfirstnames",
    "dateofbirth",
    "genderId",
    "birthname",
    "artistname",
    "nameprefix",
    "namesuffix",
    "academicdegreesuffix",
    "academicdegreeId",
    "titleId",
    "birthcity",
    "countryId",
    "nationalityId",
    "secondNationalityId",
    "selfregistrationStatusId",
    "connectedToSubject",
    "degreePrograms70",
    "requestForDisenrollment",
    "eduType",
    "enrollmentdate",
    "exchangeprogramId",
    "hasLoanRequest",
    "homeCountryId",
    "homeDistrictId",
    "homeDistrictObjId",
    "kollegsemester",
    "leavesemester",
    "orgunitLid",
    "practicalsemester",
    "reasonOfDisenrollmentId",
    "semesterCountryId",
    "semesterDistrictId",
    "semesterDistrictObjId",
    "universitysemester",
    "studystatusId",
    "termTypeValueId",
    "year",
    "accounts",
    "addresses",
    "personattributes"
})
public class Student70 {

    protected long studentId;
    @XmlElement(required = true, nillable = true)
    protected BigInteger registrationnumber;
    protected long personId;
    @XmlElement(nillable = true)
    protected String firstname;
    @XmlElement(nillable = true)
    protected String surname;
    @XmlElement(nillable = true)
    protected String allfirstnames;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateofbirth;
    @XmlElement(nillable = true)
    protected Long genderId;
    @XmlElement(nillable = true)
    protected String birthname;
    @XmlElement(nillable = true)
    protected String artistname;
    @XmlElement(nillable = true)
    protected String nameprefix;
    @XmlElement(nillable = true)
    protected String namesuffix;
    @XmlElement(nillable = true)
    protected String academicdegreesuffix;
    @XmlElement(nillable = true)
    protected Long academicdegreeId;
    @XmlElement(nillable = true)
    protected Long titleId;
    @XmlElement(nillable = true)
    protected String birthcity;
    @XmlElement(nillable = true)
    protected Long countryId;
    @XmlElement(nillable = true)
    protected Long nationalityId;
    @XmlElement(nillable = true)
    protected Long secondNationalityId;
    @XmlElement(nillable = true)
    protected Long selfregistrationStatusId;
    @XmlElement(required = true, nillable = true)
    protected BigInteger connectedToSubject;
    @XmlElement(required = true, nillable = true)
    protected DegreePrograms70Type degreePrograms70;
    @XmlElement(nillable = true)
    protected RequestForDisenrollment requestForDisenrollment;
    @XmlElement(nillable = true)
    protected BigInteger eduType;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar enrollmentdate;
    @XmlElement(nillable = true)
    protected Long exchangeprogramId;
    @XmlElement(nillable = true)
    protected BigInteger hasLoanRequest;
    @XmlElement(nillable = true)
    protected Long homeCountryId;
    @XmlElement(nillable = true)
    protected Long homeDistrictId;
    @XmlElement(nillable = true)
    protected Long homeDistrictObjId;
    @XmlElement(nillable = true)
    protected BigDecimal kollegsemester;
    @XmlElement(nillable = true)
    protected BigDecimal leavesemester;
    @XmlElement(nillable = true)
    protected Long orgunitLid;
    @XmlElement(nillable = true)
    protected BigDecimal practicalsemester;
    @XmlElement(nillable = true)
    protected Long reasonOfDisenrollmentId;
    @XmlElement(nillable = true)
    protected Long semesterCountryId;
    @XmlElement(nillable = true)
    protected Long semesterDistrictId;
    @XmlElement(nillable = true)
    protected Long semesterDistrictObjId;
    @XmlElement(nillable = true)
    protected BigDecimal universitysemester;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long studystatusId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long termTypeValueId;
    @XmlElement(required = true, nillable = true)
    protected BigInteger year;
    @XmlElement(nillable = true)
    protected AccountsType accounts;
    @XmlElement(nillable = true)
    protected AddressesType addresses;
    @XmlElement(nillable = true)
    protected PersonattributesType personattributes;

    /**
     * Ruft den Wert der studentId-Eigenschaft ab.
     * 
     */
    public long getStudentId() {
        return studentId;
    }

    /**
     * Legt den Wert der studentId-Eigenschaft fest.
     * 
     */
    public void setStudentId(long value) {
        this.studentId = value;
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
     * Ruft den Wert der personId-Eigenschaft ab.
     * 
     */
    public long getPersonId() {
        return personId;
    }

    /**
     * Legt den Wert der personId-Eigenschaft fest.
     * 
     */
    public void setPersonId(long value) {
        this.personId = value;
    }

    /**
     * Ruft den Wert der firstname-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Legt den Wert der firstname-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstname(String value) {
        this.firstname = value;
    }

    /**
     * Ruft den Wert der surname-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Legt den Wert der surname-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurname(String value) {
        this.surname = value;
    }

    /**
     * Ruft den Wert der allfirstnames-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllfirstnames() {
        return allfirstnames;
    }

    /**
     * Legt den Wert der allfirstnames-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllfirstnames(String value) {
        this.allfirstnames = value;
    }

    /**
     * Ruft den Wert der dateofbirth-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateofbirth() {
        return dateofbirth;
    }

    /**
     * Legt den Wert der dateofbirth-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateofbirth(XMLGregorianCalendar value) {
        this.dateofbirth = value;
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
     * Ruft den Wert der birthname-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthname() {
        return birthname;
    }

    /**
     * Legt den Wert der birthname-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthname(String value) {
        this.birthname = value;
    }

    /**
     * Ruft den Wert der artistname-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtistname() {
        return artistname;
    }

    /**
     * Legt den Wert der artistname-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtistname(String value) {
        this.artistname = value;
    }

    /**
     * Ruft den Wert der nameprefix-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameprefix() {
        return nameprefix;
    }

    /**
     * Legt den Wert der nameprefix-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameprefix(String value) {
        this.nameprefix = value;
    }

    /**
     * Ruft den Wert der namesuffix-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNamesuffix() {
        return namesuffix;
    }

    /**
     * Legt den Wert der namesuffix-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNamesuffix(String value) {
        this.namesuffix = value;
    }

    /**
     * Ruft den Wert der academicdegreesuffix-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcademicdegreesuffix() {
        return academicdegreesuffix;
    }

    /**
     * Legt den Wert der academicdegreesuffix-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcademicdegreesuffix(String value) {
        this.academicdegreesuffix = value;
    }

    /**
     * Ruft den Wert der academicdegreeId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAcademicdegreeId() {
        return academicdegreeId;
    }

    /**
     * Legt den Wert der academicdegreeId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAcademicdegreeId(Long value) {
        this.academicdegreeId = value;
    }

    /**
     * Ruft den Wert der titleId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTitleId() {
        return titleId;
    }

    /**
     * Legt den Wert der titleId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTitleId(Long value) {
        this.titleId = value;
    }

    /**
     * Ruft den Wert der birthcity-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthcity() {
        return birthcity;
    }

    /**
     * Legt den Wert der birthcity-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthcity(String value) {
        this.birthcity = value;
    }

    /**
     * Ruft den Wert der countryId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCountryId() {
        return countryId;
    }

    /**
     * Legt den Wert der countryId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCountryId(Long value) {
        this.countryId = value;
    }

    /**
     * Ruft den Wert der nationalityId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNationalityId() {
        return nationalityId;
    }

    /**
     * Legt den Wert der nationalityId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNationalityId(Long value) {
        this.nationalityId = value;
    }

    /**
     * Ruft den Wert der secondNationalityId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSecondNationalityId() {
        return secondNationalityId;
    }

    /**
     * Legt den Wert der secondNationalityId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSecondNationalityId(Long value) {
        this.secondNationalityId = value;
    }

    /**
     * Ruft den Wert der selfregistrationStatusId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSelfregistrationStatusId() {
        return selfregistrationStatusId;
    }

    /**
     * Legt den Wert der selfregistrationStatusId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSelfregistrationStatusId(Long value) {
        this.selfregistrationStatusId = value;
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
     * Ruft den Wert der reasonOfDisenrollmentId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getReasonOfDisenrollmentId() {
        return reasonOfDisenrollmentId;
    }

    /**
     * Legt den Wert der reasonOfDisenrollmentId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setReasonOfDisenrollmentId(Long value) {
        this.reasonOfDisenrollmentId = value;
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
     * Ruft den Wert der accounts-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AccountsType }
     *     
     */
    public AccountsType getAccounts() {
        return accounts;
    }

    /**
     * Legt den Wert der accounts-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountsType }
     *     
     */
    public void setAccounts(AccountsType value) {
        this.accounts = value;
    }

    /**
     * Ruft den Wert der addresses-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AddressesType }
     *     
     */
    public AddressesType getAddresses() {
        return addresses;
    }

    /**
     * Legt den Wert der addresses-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressesType }
     *     
     */
    public void setAddresses(AddressesType value) {
        this.addresses = value;
    }

    /**
     * Ruft den Wert der personattributes-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PersonattributesType }
     *     
     */
    public PersonattributesType getPersonattributes() {
        return personattributes;
    }

    /**
     * Legt den Wert der personattributes-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonattributesType }
     *     
     */
    public void setPersonattributes(PersonattributesType value) {
        this.personattributes = value;
    }

}
