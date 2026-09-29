
package de.his.ws.studentservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="personId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="studentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="firstname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="registrationnumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="studystatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="disenrollmentDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="reasonOfDisenrollmentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orgroleId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orgroleValidFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orgroleValidTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="personattributeTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="personattributeValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="degreeProgramId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dppId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dppTermTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dppTermYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dppStudystatusId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dppCourseOfStudyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="updatedAt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "personId",
    "studentId",
    "surname",
    "firstname",
    "registrationnumber",
    "studystatus",
    "disenrollmentDate",
    "reasonOfDisenrollmentId",
    "orgroleId",
    "orgroleValidFrom",
    "orgroleValidTo",
    "personattributeTypeId",
    "personattributeValue",
    "degreeProgramId",
    "dppId",
    "dppTermTypeId",
    "dppTermYear",
    "dppStudystatusId",
    "dppCourseOfStudyId",
    "updatedAt"
})
@XmlRootElement(name = "findStudent61")
public class FindStudent61 {

    @XmlElement(nillable = true)
    protected String personId;
    @XmlElement(nillable = true)
    protected String studentId;
    @XmlElement(nillable = true)
    protected String surname;
    @XmlElement(nillable = true)
    protected String firstname;
    @XmlElement(nillable = true)
    protected String registrationnumber;
    @XmlElement(nillable = true)
    protected String studystatus;
    @XmlElement(nillable = true)
    protected String disenrollmentDate;
    @XmlElement(nillable = true)
    protected String reasonOfDisenrollmentId;
    @XmlElement(nillable = true)
    protected String orgroleId;
    @XmlElement(nillable = true)
    protected String orgroleValidFrom;
    @XmlElement(nillable = true)
    protected String orgroleValidTo;
    @XmlElement(nillable = true)
    protected String personattributeTypeId;
    @XmlElement(nillable = true)
    protected String personattributeValue;
    @XmlElement(nillable = true)
    protected String degreeProgramId;
    @XmlElement(nillable = true)
    protected String dppId;
    @XmlElement(nillable = true)
    protected String dppTermTypeId;
    @XmlElement(nillable = true)
    protected String dppTermYear;
    @XmlElement(nillable = true)
    protected String dppStudystatusId;
    @XmlElement(nillable = true)
    protected String dppCourseOfStudyId;
    @XmlElement(nillable = true)
    protected String updatedAt;

    /**
     * Ruft den Wert der personId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonId() {
        return personId;
    }

    /**
     * Legt den Wert der personId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonId(String value) {
        this.personId = value;
    }

    /**
     * Ruft den Wert der studentId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Legt den Wert der studentId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentId(String value) {
        this.studentId = value;
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
     * Ruft den Wert der registrationnumber-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationnumber() {
        return registrationnumber;
    }

    /**
     * Legt den Wert der registrationnumber-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationnumber(String value) {
        this.registrationnumber = value;
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
     * Ruft den Wert der disenrollmentDate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisenrollmentDate() {
        return disenrollmentDate;
    }

    /**
     * Legt den Wert der disenrollmentDate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisenrollmentDate(String value) {
        this.disenrollmentDate = value;
    }

    /**
     * Ruft den Wert der reasonOfDisenrollmentId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReasonOfDisenrollmentId() {
        return reasonOfDisenrollmentId;
    }

    /**
     * Legt den Wert der reasonOfDisenrollmentId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReasonOfDisenrollmentId(String value) {
        this.reasonOfDisenrollmentId = value;
    }

    /**
     * Ruft den Wert der orgroleId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgroleId() {
        return orgroleId;
    }

    /**
     * Legt den Wert der orgroleId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgroleId(String value) {
        this.orgroleId = value;
    }

    /**
     * Ruft den Wert der orgroleValidFrom-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgroleValidFrom() {
        return orgroleValidFrom;
    }

    /**
     * Legt den Wert der orgroleValidFrom-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgroleValidFrom(String value) {
        this.orgroleValidFrom = value;
    }

    /**
     * Ruft den Wert der orgroleValidTo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgroleValidTo() {
        return orgroleValidTo;
    }

    /**
     * Legt den Wert der orgroleValidTo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgroleValidTo(String value) {
        this.orgroleValidTo = value;
    }

    /**
     * Ruft den Wert der personattributeTypeId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonattributeTypeId() {
        return personattributeTypeId;
    }

    /**
     * Legt den Wert der personattributeTypeId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonattributeTypeId(String value) {
        this.personattributeTypeId = value;
    }

    /**
     * Ruft den Wert der personattributeValue-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonattributeValue() {
        return personattributeValue;
    }

    /**
     * Legt den Wert der personattributeValue-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonattributeValue(String value) {
        this.personattributeValue = value;
    }

    /**
     * Ruft den Wert der degreeProgramId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDegreeProgramId() {
        return degreeProgramId;
    }

    /**
     * Legt den Wert der degreeProgramId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDegreeProgramId(String value) {
        this.degreeProgramId = value;
    }

    /**
     * Ruft den Wert der dppId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDppId() {
        return dppId;
    }

    /**
     * Legt den Wert der dppId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDppId(String value) {
        this.dppId = value;
    }

    /**
     * Ruft den Wert der dppTermTypeId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDppTermTypeId() {
        return dppTermTypeId;
    }

    /**
     * Legt den Wert der dppTermTypeId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDppTermTypeId(String value) {
        this.dppTermTypeId = value;
    }

    /**
     * Ruft den Wert der dppTermYear-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDppTermYear() {
        return dppTermYear;
    }

    /**
     * Legt den Wert der dppTermYear-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDppTermYear(String value) {
        this.dppTermYear = value;
    }

    /**
     * Ruft den Wert der dppStudystatusId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDppStudystatusId() {
        return dppStudystatusId;
    }

    /**
     * Legt den Wert der dppStudystatusId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDppStudystatusId(String value) {
        this.dppStudystatusId = value;
    }

    /**
     * Ruft den Wert der dppCourseOfStudyId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDppCourseOfStudyId() {
        return dppCourseOfStudyId;
    }

    /**
     * Legt den Wert der dppCourseOfStudyId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDppCourseOfStudyId(String value) {
        this.dppCourseOfStudyId = value;
    }

    /**
     * Ruft den Wert der updatedAt-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Legt den Wert der updatedAt-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdatedAt(String value) {
        this.updatedAt = value;
    }

}
