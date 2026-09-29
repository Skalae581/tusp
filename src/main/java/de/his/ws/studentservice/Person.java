
package de.his.ws.studentservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
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
    "selfregistrationStatusId"
})
@XmlSeeAlso({
    Person70 .class
})
public class Person {

    protected long id;
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

}
