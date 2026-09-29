
package de.his.ws.applicantservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für FieldInput complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="FieldInput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="applicantVisibility" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="computed" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="uniquename" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="fieldId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="fieldDefaulttext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="fieldUniquename" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="fieldSortOrder" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="fieldOptional" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="lockVersion" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="errorEntries" type="{http://www.his.de/ws/ApplicantService}errorEntriesType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FieldInput", propOrder = {
    "id",
    "applicantVisibility",
    "computed",
    "value",
    "uniquename",
    "fieldId",
    "fieldDefaulttext",
    "fieldUniquename",
    "fieldSortOrder",
    "fieldOptional",
    "lockVersion",
    "errorEntries"
})
public class FieldInput {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long id;
    @XmlElement(required = true, nillable = true)
    protected String applicantVisibility;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean computed;
    @XmlElement(required = true, nillable = true)
    protected String value;
    @XmlElement(required = true, nillable = true)
    protected String uniquename;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long fieldId;
    @XmlElement(required = true, nillable = true)
    protected String fieldDefaulttext;
    @XmlElement(required = true, nillable = true)
    protected String fieldUniquename;
    @XmlElement(required = true, nillable = true)
    protected BigInteger fieldSortOrder;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean fieldOptional;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long lockVersion;
    @XmlElement(nillable = true)
    protected ErrorEntriesType errorEntries;

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
     * Ruft den Wert der applicantVisibility-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicantVisibility() {
        return applicantVisibility;
    }

    /**
     * Legt den Wert der applicantVisibility-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicantVisibility(String value) {
        this.applicantVisibility = value;
    }

    /**
     * Ruft den Wert der computed-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isComputed() {
        return computed;
    }

    /**
     * Legt den Wert der computed-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setComputed(Boolean value) {
        this.computed = value;
    }

    /**
     * Ruft den Wert der value-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Legt den Wert der value-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
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
     * Ruft den Wert der fieldId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFieldId() {
        return fieldId;
    }

    /**
     * Legt den Wert der fieldId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFieldId(Long value) {
        this.fieldId = value;
    }

    /**
     * Ruft den Wert der fieldDefaulttext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldDefaulttext() {
        return fieldDefaulttext;
    }

    /**
     * Legt den Wert der fieldDefaulttext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldDefaulttext(String value) {
        this.fieldDefaulttext = value;
    }

    /**
     * Ruft den Wert der fieldUniquename-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldUniquename() {
        return fieldUniquename;
    }

    /**
     * Legt den Wert der fieldUniquename-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldUniquename(String value) {
        this.fieldUniquename = value;
    }

    /**
     * Ruft den Wert der fieldSortOrder-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFieldSortOrder() {
        return fieldSortOrder;
    }

    /**
     * Legt den Wert der fieldSortOrder-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFieldSortOrder(BigInteger value) {
        this.fieldSortOrder = value;
    }

    /**
     * Ruft den Wert der fieldOptional-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFieldOptional() {
        return fieldOptional;
    }

    /**
     * Legt den Wert der fieldOptional-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFieldOptional(Boolean value) {
        this.fieldOptional = value;
    }

    /**
     * Ruft den Wert der lockVersion-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLockVersion() {
        return lockVersion;
    }

    /**
     * Legt den Wert der lockVersion-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLockVersion(Long value) {
        this.lockVersion = value;
    }

    /**
     * Ruft den Wert der errorEntries-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ErrorEntriesType }
     *     
     */
    public ErrorEntriesType getErrorEntries() {
        return errorEntries;
    }

    /**
     * Legt den Wert der errorEntries-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorEntriesType }
     *     
     */
    public void setErrorEntries(ErrorEntriesType value) {
        this.errorEntries = value;
    }

}
