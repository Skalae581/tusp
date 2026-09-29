
package de.his.ws.studentservice201812;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für Student complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Student"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="studentId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="registrationnumber" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="personId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="completeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="firstname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="allfirstnames" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dateofbirth" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="gender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="birthname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="artistname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nameprefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="namesuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="academicdegreesuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="academicdegree" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="birthcity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="countryLid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="nationality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nationalityLid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="secondNationalityId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="secondNationalityLid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="selfregistrationStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="connectedToSubject" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="eduType" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="enrollmentdate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="exchangeprogramId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="hasLoanRequest" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="homeCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="homeCountryLid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="homeDistrict" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="homeDistrictLid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="kollegsemester" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="leavesemester" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="orgunitLid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="practicalsemester" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="semesterCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="semesterCountryLid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="semesterDistrict" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="semesterDistrictLid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="universitysemester" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="libraryCardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="studystatus" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="termType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="createdAt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="updatedAt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="requestForDisenrollment" type="{http://www.his.de/ws/StudentService201812}RequestForDisenrollment" minOccurs="0"/&gt;
 *         &lt;element name="degreePrograms" type="{http://www.his.de/ws/StudentService201812}degreeProgramsType"/&gt;
 *         &lt;element name="accounts" type="{http://www.his.de/ws/StudentService201812}accountsType" minOccurs="0"/&gt;
 *         &lt;element name="postAddresses" type="{http://www.his.de/ws/StudentService201812}postAddressesType" minOccurs="0"/&gt;
 *         &lt;element name="emails" type="{http://www.his.de/ws/StudentService201812}emailsType" minOccurs="0"/&gt;
 *         &lt;element name="personattributes" type="{http://www.his.de/ws/StudentService201812}personattributesType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Student", propOrder = {
    "studentId",
    "registrationnumber",
    "personId",
    "completeName",
    "firstname",
    "surname",
    "allfirstnames",
    "dateofbirth",
    "gender",
    "birthname",
    "artistname",
    "nameprefix",
    "namesuffix",
    "academicdegreesuffix",
    "academicdegree",
    "title",
    "birthcity",
    "country",
    "countryLid",
    "nationality",
    "nationalityLid",
    "secondNationalityId",
    "secondNationalityLid",
    "selfregistrationStatus",
    "connectedToSubject",
    "eduType",
    "enrollmentdate",
    "exchangeprogramId",
    "hasLoanRequest",
    "homeCountry",
    "homeCountryLid",
    "homeDistrict",
    "homeDistrictLid",
    "kollegsemester",
    "leavesemester",
    "orgunitLid",
    "practicalsemester",
    "semesterCountry",
    "semesterCountryLid",
    "semesterDistrict",
    "semesterDistrictLid",
    "universitysemester",
    "libraryCardNumber",
    "studystatus",
    "termType",
    "year",
    "createdAt",
    "updatedAt",
    "requestForDisenrollment",
    "degreePrograms",
    "accounts",
    "postAddresses",
    "emails",
    "personattributes"
})
public class Student {

    protected long studentId;
    @XmlElement(required = true, nillable = true)
    protected BigInteger registrationnumber;
    protected long personId;
    protected String completeName;
    @XmlElement(nillable = true)
    protected String firstname;
    @XmlElement(nillable = true)
    protected String surname;
    @XmlElement(nillable = true)
    protected String allfirstnames;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateofbirth;
    protected String gender;
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
    protected String academicdegree;
    @XmlElement(nillable = true)
    protected String title;
    @XmlElement(nillable = true)
    protected String birthcity;
    @XmlElement(nillable = true)
    protected String country;
    @XmlElement(nillable = true)
    protected Long countryLid;
    @XmlElement(nillable = true)
    protected String nationality;
    @XmlElement(nillable = true)
    protected Long nationalityLid;
    @XmlElement(nillable = true)
    protected String secondNationalityId;
    @XmlElement(nillable = true)
    protected Long secondNationalityLid;
    @XmlElement(nillable = true)
    protected String selfregistrationStatus;
    @XmlElement(nillable = true)
    protected BigInteger connectedToSubject;
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
    protected String homeCountry;
    @XmlElement(nillable = true)
    protected Long homeCountryLid;
    @XmlElement(nillable = true)
    protected String homeDistrict;
    @XmlElement(nillable = true)
    protected Long homeDistrictLid;
    @XmlElement(nillable = true)
    protected BigDecimal kollegsemester;
    @XmlElement(nillable = true)
    protected BigDecimal leavesemester;
    @XmlElement(nillable = true)
    protected Long orgunitLid;
    @XmlElement(nillable = true)
    protected BigDecimal practicalsemester;
    @XmlElement(nillable = true)
    protected String semesterCountry;
    @XmlElement(nillable = true)
    protected Long semesterCountryLid;
    @XmlElement(nillable = true)
    protected String semesterDistrict;
    @XmlElement(nillable = true)
    protected Long semesterDistrictLid;
    @XmlElement(nillable = true)
    protected BigDecimal universitysemester;
    @XmlElement(nillable = true)
    protected String libraryCardNumber;
    @XmlElement(required = true, nillable = true)
    protected String studystatus;
    @XmlElement(required = true, nillable = true)
    protected String termType;
    @XmlElement(required = true, nillable = true)
    protected BigInteger year;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdAt;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updatedAt;
    @XmlElement(nillable = true)
    protected RequestForDisenrollment requestForDisenrollment;
    @XmlElement(required = true, nillable = true)
    protected DegreeProgramsType degreePrograms;
    @XmlElement(nillable = true)
    protected AccountsType accounts;
    @XmlElement(nillable = true)
    protected PostAddressesType postAddresses;
    @XmlElement(nillable = true)
    protected EmailsType emails;
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
     * Ruft den Wert der completeName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompleteName() {
        return completeName;
    }

    /**
     * Legt den Wert der completeName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompleteName(String value) {
        this.completeName = value;
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
     * Ruft den Wert der gender-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Legt den Wert der gender-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
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
     * Ruft den Wert der academicdegree-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcademicdegree() {
        return academicdegree;
    }

    /**
     * Legt den Wert der academicdegree-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcademicdegree(String value) {
        this.academicdegree = value;
    }

    /**
     * Ruft den Wert der title-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Legt den Wert der title-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
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
     * Ruft den Wert der country-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Legt den Wert der country-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Ruft den Wert der countryLid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCountryLid() {
        return countryLid;
    }

    /**
     * Legt den Wert der countryLid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCountryLid(Long value) {
        this.countryLid = value;
    }

    /**
     * Ruft den Wert der nationality-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Legt den Wert der nationality-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationality(String value) {
        this.nationality = value;
    }

    /**
     * Ruft den Wert der nationalityLid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNationalityLid() {
        return nationalityLid;
    }

    /**
     * Legt den Wert der nationalityLid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNationalityLid(Long value) {
        this.nationalityLid = value;
    }

    /**
     * Ruft den Wert der secondNationalityId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondNationalityId() {
        return secondNationalityId;
    }

    /**
     * Legt den Wert der secondNationalityId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondNationalityId(String value) {
        this.secondNationalityId = value;
    }

    /**
     * Ruft den Wert der secondNationalityLid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSecondNationalityLid() {
        return secondNationalityLid;
    }

    /**
     * Legt den Wert der secondNationalityLid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSecondNationalityLid(Long value) {
        this.secondNationalityLid = value;
    }

    /**
     * Ruft den Wert der selfregistrationStatus-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelfregistrationStatus() {
        return selfregistrationStatus;
    }

    /**
     * Legt den Wert der selfregistrationStatus-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelfregistrationStatus(String value) {
        this.selfregistrationStatus = value;
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
     * Ruft den Wert der homeCountry-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeCountry() {
        return homeCountry;
    }

    /**
     * Legt den Wert der homeCountry-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeCountry(String value) {
        this.homeCountry = value;
    }

    /**
     * Ruft den Wert der homeCountryLid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHomeCountryLid() {
        return homeCountryLid;
    }

    /**
     * Legt den Wert der homeCountryLid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHomeCountryLid(Long value) {
        this.homeCountryLid = value;
    }

    /**
     * Ruft den Wert der homeDistrict-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeDistrict() {
        return homeDistrict;
    }

    /**
     * Legt den Wert der homeDistrict-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeDistrict(String value) {
        this.homeDistrict = value;
    }

    /**
     * Ruft den Wert der homeDistrictLid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHomeDistrictLid() {
        return homeDistrictLid;
    }

    /**
     * Legt den Wert der homeDistrictLid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHomeDistrictLid(Long value) {
        this.homeDistrictLid = value;
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
     * Ruft den Wert der semesterCountry-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSemesterCountry() {
        return semesterCountry;
    }

    /**
     * Legt den Wert der semesterCountry-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSemesterCountry(String value) {
        this.semesterCountry = value;
    }

    /**
     * Ruft den Wert der semesterCountryLid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSemesterCountryLid() {
        return semesterCountryLid;
    }

    /**
     * Legt den Wert der semesterCountryLid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSemesterCountryLid(Long value) {
        this.semesterCountryLid = value;
    }

    /**
     * Ruft den Wert der semesterDistrict-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSemesterDistrict() {
        return semesterDistrict;
    }

    /**
     * Legt den Wert der semesterDistrict-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSemesterDistrict(String value) {
        this.semesterDistrict = value;
    }

    /**
     * Ruft den Wert der semesterDistrictLid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSemesterDistrictLid() {
        return semesterDistrictLid;
    }

    /**
     * Legt den Wert der semesterDistrictLid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSemesterDistrictLid(Long value) {
        this.semesterDistrictLid = value;
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
     * Ruft den Wert der libraryCardNumber-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLibraryCardNumber() {
        return libraryCardNumber;
    }

    /**
     * Legt den Wert der libraryCardNumber-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLibraryCardNumber(String value) {
        this.libraryCardNumber = value;
    }

    /**
     * Ruft den Wert der studystatus-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudystatus() {
        return studystatus;
    }

    /**
     * Legt den Wert der studystatus-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudystatus(String value) {
        this.studystatus = value;
    }

    /**
     * Ruft den Wert der termType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermType() {
        return termType;
    }

    /**
     * Legt den Wert der termType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermType(String value) {
        this.termType = value;
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
     * Ruft den Wert der degreePrograms-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DegreeProgramsType }
     *     
     */
    public DegreeProgramsType getDegreePrograms() {
        return degreePrograms;
    }

    /**
     * Legt den Wert der degreePrograms-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DegreeProgramsType }
     *     
     */
    public void setDegreePrograms(DegreeProgramsType value) {
        this.degreePrograms = value;
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
     * Ruft den Wert der postAddresses-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PostAddressesType }
     *     
     */
    public PostAddressesType getPostAddresses() {
        return postAddresses;
    }

    /**
     * Legt den Wert der postAddresses-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PostAddressesType }
     *     
     */
    public void setPostAddresses(PostAddressesType value) {
        this.postAddresses = value;
    }

    /**
     * Ruft den Wert der emails-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EmailsType }
     *     
     */
    public EmailsType getEmails() {
        return emails;
    }

    /**
     * Legt den Wert der emails-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EmailsType }
     *     
     */
    public void setEmails(EmailsType value) {
        this.emails = value;
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
