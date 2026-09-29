
package de.his.ws.personservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A Person represents the identity of any natural person that is used throughout the system.
 * 
 * <p>Java-Klasse für Person complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Person"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="objGuid" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="firstname" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="allfirstnames" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dateofbirth" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="gender" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="birthname" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="artistname" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="nameprefix" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="namesuffix" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="academicdegreesuffix" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="academicdegreeId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="titleId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="birthcity" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="countryId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="createdAt" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="updatedAt" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="accounts" type="{http://www.his.de/ws/PersonService}accountsType"/&gt;
 *         &lt;element name="selfregistrationStatusId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="personinfo" type="{http://www.his.de/ws/PersonService}PersoninfoDto" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Person", propOrder = {
    "id",
    "objGuid",
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
    "academicdegreeId",
    "titleId",
    "birthcity",
    "countryId",
    "createdAt",
    "updatedAt",
    "accounts",
    "selfregistrationStatusId",
    "personinfo"
})
public class Person {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long id;
    @XmlElement(required = true, nillable = true)
    protected String objGuid;
    @XmlElement(required = true)
    protected String firstname;
    @XmlElement(required = true)
    protected String surname;
    @XmlElement(required = true, nillable = true)
    protected String allfirstnames;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateofbirth;
    protected long gender;
    @XmlElement(required = true, nillable = true)
    protected String birthname;
    @XmlElement(required = true, nillable = true)
    protected String artistname;
    @XmlElement(required = true, nillable = true)
    protected String nameprefix;
    @XmlElement(required = true, nillable = true)
    protected String namesuffix;
    @XmlElement(required = true, nillable = true)
    protected String academicdegreesuffix;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long academicdegreeId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long titleId;
    @XmlElement(required = true, nillable = true)
    protected String birthcity;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long countryId;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdAt;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updatedAt;
    @XmlElement(required = true)
    protected AccountsType accounts;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long selfregistrationStatusId;
    @XmlElement(nillable = true)
    protected PersoninfoDto personinfo;

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
     */
    public long getGender() {
        return gender;
    }

    /**
     * Legt den Wert der gender-Eigenschaft fest.
     * 
     */
    public void setGender(long value) {
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
     * Ruft den Wert der personinfo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PersoninfoDto }
     *     
     */
    public PersoninfoDto getPersoninfo() {
        return personinfo;
    }

    /**
     * Legt den Wert der personinfo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PersoninfoDto }
     *     
     */
    public void setPersoninfo(PersoninfoDto value) {
        this.personinfo = value;
    }

}
