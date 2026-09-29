
package de.his.ws.studentservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Additional personal data
 * 
 * <p>Java-Klasse für PersoninfoDto complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PersoninfoDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="createdAt" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="familyStatusId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="hasDoneService" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="healthInsuranceId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="healthInsuranceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="healthInsuranceText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="insurantStatusId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="nationalityId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="religionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="secondNationalityId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="updatedAt" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="visumnumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersoninfoDto", propOrder = {
    "id",
    "createdAt",
    "familyStatusId",
    "hasDoneService",
    "healthInsuranceId",
    "healthInsuranceNumber",
    "healthInsuranceText",
    "insurantStatusId",
    "nationalityId",
    "religionId",
    "secondNationalityId",
    "updatedAt",
    "visumnumber"
})
public class PersoninfoDto {

    @XmlElement(nillable = true)
    protected Long id;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar createdAt;
    @XmlElement(nillable = true)
    protected Long familyStatusId;
    @XmlElement(nillable = true)
    protected Boolean hasDoneService;
    @XmlElement(nillable = true)
    protected Long healthInsuranceId;
    @XmlElement(nillable = true)
    protected String healthInsuranceNumber;
    @XmlElement(nillable = true)
    protected String healthInsuranceText;
    @XmlElement(nillable = true)
    protected Long insurantStatusId;
    @XmlElement(nillable = true)
    protected Long nationalityId;
    @XmlElement(nillable = true)
    protected Long religionId;
    @XmlElement(nillable = true)
    protected Long secondNationalityId;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar updatedAt;
    @XmlElement(nillable = true)
    protected String visumnumber;

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
     * Ruft den Wert der familyStatusId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFamilyStatusId() {
        return familyStatusId;
    }

    /**
     * Legt den Wert der familyStatusId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFamilyStatusId(Long value) {
        this.familyStatusId = value;
    }

    /**
     * Ruft den Wert der hasDoneService-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasDoneService() {
        return hasDoneService;
    }

    /**
     * Legt den Wert der hasDoneService-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasDoneService(Boolean value) {
        this.hasDoneService = value;
    }

    /**
     * Ruft den Wert der healthInsuranceId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHealthInsuranceId() {
        return healthInsuranceId;
    }

    /**
     * Legt den Wert der healthInsuranceId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHealthInsuranceId(Long value) {
        this.healthInsuranceId = value;
    }

    /**
     * Ruft den Wert der healthInsuranceNumber-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    /**
     * Legt den Wert der healthInsuranceNumber-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHealthInsuranceNumber(String value) {
        this.healthInsuranceNumber = value;
    }

    /**
     * Ruft den Wert der healthInsuranceText-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHealthInsuranceText() {
        return healthInsuranceText;
    }

    /**
     * Legt den Wert der healthInsuranceText-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHealthInsuranceText(String value) {
        this.healthInsuranceText = value;
    }

    /**
     * Ruft den Wert der insurantStatusId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInsurantStatusId() {
        return insurantStatusId;
    }

    /**
     * Legt den Wert der insurantStatusId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInsurantStatusId(Long value) {
        this.insurantStatusId = value;
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
     * Ruft den Wert der religionId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getReligionId() {
        return religionId;
    }

    /**
     * Legt den Wert der religionId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setReligionId(Long value) {
        this.religionId = value;
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
     * Ruft den Wert der visumnumber-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisumnumber() {
        return visumnumber;
    }

    /**
     * Legt den Wert der visumnumber-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisumnumber(String value) {
        this.visumnumber = value;
    }

}
