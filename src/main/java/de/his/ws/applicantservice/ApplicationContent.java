
package de.his.ws.applicantservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für ApplicationContent complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ApplicationContent"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="contentType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="defaulttext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="uniquename" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="applicantVisibility" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="applicationContentInputDataSet" type="{http://www.his.de/ws/ApplicantService}applicationContentInputDataSetType"/&gt;
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
@XmlType(name = "ApplicationContent", propOrder = {
    "id",
    "contentType",
    "defaulttext",
    "uniquename",
    "applicantVisibility",
    "applicationContentInputDataSet",
    "errorEntries"
})
public class ApplicationContent {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long id;
    @XmlElement(required = true, nillable = true)
    protected String contentType;
    @XmlElement(required = true, nillable = true)
    protected String defaulttext;
    @XmlElement(required = true, nillable = true)
    protected String uniquename;
    @XmlElement(required = true, nillable = true)
    protected String applicantVisibility;
    @XmlElement(required = true, nillable = true)
    protected ApplicationContentInputDataSetType applicationContentInputDataSet;
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
     * Ruft den Wert der contentType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Legt den Wert der contentType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentType(String value) {
        this.contentType = value;
    }

    /**
     * Ruft den Wert der defaulttext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaulttext() {
        return defaulttext;
    }

    /**
     * Legt den Wert der defaulttext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaulttext(String value) {
        this.defaulttext = value;
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
     * Ruft den Wert der applicationContentInputDataSet-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationContentInputDataSetType }
     *     
     */
    public ApplicationContentInputDataSetType getApplicationContentInputDataSet() {
        return applicationContentInputDataSet;
    }

    /**
     * Legt den Wert der applicationContentInputDataSet-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationContentInputDataSetType }
     *     
     */
    public void setApplicationContentInputDataSet(ApplicationContentInputDataSetType value) {
        this.applicationContentInputDataSet = value;
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
