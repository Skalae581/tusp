
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
 * <p>Java-Klasse für StudyBeforeDto complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="StudyBeforeDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/StudentService}GeneratedDtoBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="createdAt" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="discontinuationsemester" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="firstTermYear" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="firstStudyInGermanyTermYear" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="kollegsemester" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="leavesemester" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="ownuniversityTermYear" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="practicalsemester" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="universitysemester" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="updatedAt" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="firstTermTermTypeValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="ownuniversityTermTermTypeValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="firstStudyInGermanyTermTermTypeValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="universityValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="universityValueObjId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="countryId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StudyBeforeDto", propOrder = {
    "createdAt",
    "discontinuationsemester",
    "firstTermYear",
    "firstStudyInGermanyTermYear",
    "kollegsemester",
    "leavesemester",
    "ownuniversityTermYear",
    "practicalsemester",
    "universitysemester",
    "updatedAt",
    "firstTermTermTypeValueId",
    "ownuniversityTermTermTypeValueId",
    "firstStudyInGermanyTermTermTypeValueId",
    "universityValueId",
    "universityValueObjId",
    "countryId"
})
public class StudyBeforeDto
    extends GeneratedDtoBase
{

    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar createdAt;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal discontinuationsemester;
    @XmlElement(required = true, nillable = true)
    protected BigInteger firstTermYear;
    @XmlElement(required = true, nillable = true)
    protected BigInteger firstStudyInGermanyTermYear;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal kollegsemester;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal leavesemester;
    @XmlElement(required = true, nillable = true)
    protected BigInteger ownuniversityTermYear;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal practicalsemester;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal universitysemester;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar updatedAt;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long firstTermTermTypeValueId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long ownuniversityTermTermTypeValueId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long firstStudyInGermanyTermTermTypeValueId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long universityValueId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long universityValueObjId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long countryId;

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
     * Ruft den Wert der discontinuationsemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDiscontinuationsemester() {
        return discontinuationsemester;
    }

    /**
     * Legt den Wert der discontinuationsemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDiscontinuationsemester(BigDecimal value) {
        this.discontinuationsemester = value;
    }

    /**
     * Ruft den Wert der firstTermYear-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFirstTermYear() {
        return firstTermYear;
    }

    /**
     * Legt den Wert der firstTermYear-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFirstTermYear(BigInteger value) {
        this.firstTermYear = value;
    }

    /**
     * Ruft den Wert der firstStudyInGermanyTermYear-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFirstStudyInGermanyTermYear() {
        return firstStudyInGermanyTermYear;
    }

    /**
     * Legt den Wert der firstStudyInGermanyTermYear-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFirstStudyInGermanyTermYear(BigInteger value) {
        this.firstStudyInGermanyTermYear = value;
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
     * Ruft den Wert der ownuniversityTermYear-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOwnuniversityTermYear() {
        return ownuniversityTermYear;
    }

    /**
     * Legt den Wert der ownuniversityTermYear-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOwnuniversityTermYear(BigInteger value) {
        this.ownuniversityTermYear = value;
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
     * Ruft den Wert der firstTermTermTypeValueId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFirstTermTermTypeValueId() {
        return firstTermTermTypeValueId;
    }

    /**
     * Legt den Wert der firstTermTermTypeValueId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFirstTermTermTypeValueId(Long value) {
        this.firstTermTermTypeValueId = value;
    }

    /**
     * Ruft den Wert der ownuniversityTermTermTypeValueId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOwnuniversityTermTermTypeValueId() {
        return ownuniversityTermTermTypeValueId;
    }

    /**
     * Legt den Wert der ownuniversityTermTermTypeValueId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOwnuniversityTermTermTypeValueId(Long value) {
        this.ownuniversityTermTermTypeValueId = value;
    }

    /**
     * Ruft den Wert der firstStudyInGermanyTermTermTypeValueId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFirstStudyInGermanyTermTermTypeValueId() {
        return firstStudyInGermanyTermTermTypeValueId;
    }

    /**
     * Legt den Wert der firstStudyInGermanyTermTermTypeValueId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFirstStudyInGermanyTermTermTypeValueId(Long value) {
        this.firstStudyInGermanyTermTermTypeValueId = value;
    }

    /**
     * Ruft den Wert der universityValueId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUniversityValueId() {
        return universityValueId;
    }

    /**
     * Legt den Wert der universityValueId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUniversityValueId(Long value) {
        this.universityValueId = value;
    }

    /**
     * Ruft den Wert der universityValueObjId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUniversityValueObjId() {
        return universityValueObjId;
    }

    /**
     * Legt den Wert der universityValueObjId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUniversityValueObjId(Long value) {
        this.universityValueObjId = value;
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

}
