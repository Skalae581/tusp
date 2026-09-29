
package de.his.ws.periodservice;

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
 *         &lt;element name="defaulttext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="termTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="termYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fromStudySemester" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="toStudySemester" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="periodUsageValueId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="defaultPeriod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="periodContainerDefaulttext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="courseOfStudyIds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "defaulttext",
    "termTypeId",
    "termYear",
    "startDate",
    "endDate",
    "fromStudySemester",
    "toStudySemester",
    "periodUsageValueId",
    "defaultPeriod",
    "periodContainerDefaulttext",
    "courseOfStudyIds"
})
@XmlRootElement(name = "findPeriodAndConditionInfo")
public class FindPeriodAndConditionInfo {

    @XmlElement(nillable = true)
    protected String defaulttext;
    @XmlElement(nillable = true)
    protected String termTypeId;
    @XmlElement(nillable = true)
    protected String termYear;
    @XmlElement(nillable = true)
    protected String startDate;
    @XmlElement(nillable = true)
    protected String endDate;
    @XmlElement(nillable = true)
    protected String fromStudySemester;
    @XmlElement(nillable = true)
    protected String toStudySemester;
    @XmlElement(nillable = true)
    protected String periodUsageValueId;
    @XmlElement(nillable = true)
    protected String defaultPeriod;
    @XmlElement(nillable = true)
    protected String periodContainerDefaulttext;
    @XmlElement(nillable = true)
    protected String courseOfStudyIds;

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
     * Ruft den Wert der termTypeId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermTypeId() {
        return termTypeId;
    }

    /**
     * Legt den Wert der termTypeId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermTypeId(String value) {
        this.termTypeId = value;
    }

    /**
     * Ruft den Wert der termYear-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermYear() {
        return termYear;
    }

    /**
     * Legt den Wert der termYear-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermYear(String value) {
        this.termYear = value;
    }

    /**
     * Ruft den Wert der startDate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Legt den Wert der startDate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDate(String value) {
        this.startDate = value;
    }

    /**
     * Ruft den Wert der endDate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Legt den Wert der endDate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDate(String value) {
        this.endDate = value;
    }

    /**
     * Ruft den Wert der fromStudySemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromStudySemester() {
        return fromStudySemester;
    }

    /**
     * Legt den Wert der fromStudySemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromStudySemester(String value) {
        this.fromStudySemester = value;
    }

    /**
     * Ruft den Wert der toStudySemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToStudySemester() {
        return toStudySemester;
    }

    /**
     * Legt den Wert der toStudySemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToStudySemester(String value) {
        this.toStudySemester = value;
    }

    /**
     * Ruft den Wert der periodUsageValueId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodUsageValueId() {
        return periodUsageValueId;
    }

    /**
     * Legt den Wert der periodUsageValueId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodUsageValueId(String value) {
        this.periodUsageValueId = value;
    }

    /**
     * Ruft den Wert der defaultPeriod-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultPeriod() {
        return defaultPeriod;
    }

    /**
     * Legt den Wert der defaultPeriod-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultPeriod(String value) {
        this.defaultPeriod = value;
    }

    /**
     * Ruft den Wert der periodContainerDefaulttext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodContainerDefaulttext() {
        return periodContainerDefaulttext;
    }

    /**
     * Legt den Wert der periodContainerDefaulttext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodContainerDefaulttext(String value) {
        this.periodContainerDefaulttext = value;
    }

    /**
     * Ruft den Wert der courseOfStudyIds-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCourseOfStudyIds() {
        return courseOfStudyIds;
    }

    /**
     * Legt den Wert der courseOfStudyIds-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCourseOfStudyIds(String value) {
        this.courseOfStudyIds = value;
    }

}
