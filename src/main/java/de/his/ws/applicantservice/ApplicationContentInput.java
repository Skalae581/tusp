
package de.his.ws.applicantservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für ApplicationContentInput complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ApplicationContentInput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="contentInputType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="inputAssignmentId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="optional" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="remarkForStatus" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="applicationContentInputStatus" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="lockVersion" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="fieldInputDataSet" type="{http://www.his.de/ws/ApplicantService}fieldInputDataSetType"/&gt;
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
@XmlType(name = "ApplicationContentInput", propOrder = {
    "id",
    "contentInputType",
    "inputAssignmentId",
    "optional",
    "remarkForStatus",
    "applicationContentInputStatus",
    "lockVersion",
    "fieldInputDataSet",
    "errorEntries"
})
public class ApplicationContentInput {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long id;
    @XmlElement(required = true, nillable = true)
    protected String contentInputType;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long inputAssignmentId;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean optional;
    @XmlElement(required = true, nillable = true)
    protected String remarkForStatus;
    @XmlElement(required = true, nillable = true)
    protected String applicationContentInputStatus;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long lockVersion;
    @XmlElement(required = true, nillable = true)
    protected FieldInputDataSetType fieldInputDataSet;
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
     * Ruft den Wert der contentInputType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentInputType() {
        return contentInputType;
    }

    /**
     * Legt den Wert der contentInputType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentInputType(String value) {
        this.contentInputType = value;
    }

    /**
     * Ruft den Wert der inputAssignmentId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInputAssignmentId() {
        return inputAssignmentId;
    }

    /**
     * Legt den Wert der inputAssignmentId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInputAssignmentId(Long value) {
        this.inputAssignmentId = value;
    }

    /**
     * Ruft den Wert der optional-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOptional() {
        return optional;
    }

    /**
     * Legt den Wert der optional-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOptional(Boolean value) {
        this.optional = value;
    }

    /**
     * Ruft den Wert der remarkForStatus-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemarkForStatus() {
        return remarkForStatus;
    }

    /**
     * Legt den Wert der remarkForStatus-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemarkForStatus(String value) {
        this.remarkForStatus = value;
    }

    /**
     * Ruft den Wert der applicationContentInputStatus-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationContentInputStatus() {
        return applicationContentInputStatus;
    }

    /**
     * Legt den Wert der applicationContentInputStatus-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationContentInputStatus(String value) {
        this.applicationContentInputStatus = value;
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
     * Ruft den Wert der fieldInputDataSet-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link FieldInputDataSetType }
     *     
     */
    public FieldInputDataSetType getFieldInputDataSet() {
        return fieldInputDataSet;
    }

    /**
     * Legt den Wert der fieldInputDataSet-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldInputDataSetType }
     *     
     */
    public void setFieldInputDataSet(FieldInputDataSetType value) {
        this.fieldInputDataSet = value;
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
