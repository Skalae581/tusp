
package de.his.ws.courseinterfaceservice;

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
 *         &lt;element name="unitId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="unitDefaulttext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="unitElementnr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="editingStatusId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="courseEventtypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="courseTeachingLanguageId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="planelementDefaulttext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="parallelgroupId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="termYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="termTypeValueId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="planelementEditingStatusId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="individualDatesExecutionDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="individualDatesStarttime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="individualDatesEndtime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="roomId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orgunitLid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orgunitRelationTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="termYearForMapping" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="termTypeValueIdForMapping" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="elearningSystemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="courseMappingTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "unitId",
    "unitDefaulttext",
    "unitElementnr",
    "editingStatusId",
    "courseEventtypeId",
    "courseTeachingLanguageId",
    "planelementDefaulttext",
    "parallelgroupId",
    "termYear",
    "termTypeValueId",
    "planelementEditingStatusId",
    "individualDatesExecutionDate",
    "individualDatesStarttime",
    "individualDatesEndtime",
    "roomId",
    "orgunitLid",
    "orgunitRelationTypeId",
    "termYearForMapping",
    "termTypeValueIdForMapping",
    "elearningSystemId",
    "courseMappingTypeId"
})
@XmlRootElement(name = "findUnit81")
public class FindUnit81 {

    @XmlElement(nillable = true)
    protected String unitId;
    @XmlElement(nillable = true)
    protected String unitDefaulttext;
    @XmlElement(nillable = true)
    protected String unitElementnr;
    @XmlElement(nillable = true)
    protected String editingStatusId;
    @XmlElement(nillable = true)
    protected String courseEventtypeId;
    @XmlElement(nillable = true)
    protected String courseTeachingLanguageId;
    @XmlElement(nillable = true)
    protected String planelementDefaulttext;
    @XmlElement(nillable = true)
    protected String parallelgroupId;
    @XmlElement(nillable = true)
    protected String termYear;
    @XmlElement(nillable = true)
    protected String termTypeValueId;
    @XmlElement(nillable = true)
    protected String planelementEditingStatusId;
    @XmlElement(nillable = true)
    protected String individualDatesExecutionDate;
    @XmlElement(nillable = true)
    protected String individualDatesStarttime;
    @XmlElement(nillable = true)
    protected String individualDatesEndtime;
    @XmlElement(nillable = true)
    protected String roomId;
    @XmlElement(nillable = true)
    protected String orgunitLid;
    @XmlElement(nillable = true)
    protected String orgunitRelationTypeId;
    @XmlElement(nillable = true)
    protected String termYearForMapping;
    @XmlElement(nillable = true)
    protected String termTypeValueIdForMapping;
    @XmlElement(nillable = true)
    protected String elearningSystemId;
    @XmlElement(nillable = true)
    protected String courseMappingTypeId;

    /**
     * Ruft den Wert der unitId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * Legt den Wert der unitId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitId(String value) {
        this.unitId = value;
    }

    /**
     * Ruft den Wert der unitDefaulttext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitDefaulttext() {
        return unitDefaulttext;
    }

    /**
     * Legt den Wert der unitDefaulttext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitDefaulttext(String value) {
        this.unitDefaulttext = value;
    }

    /**
     * Ruft den Wert der unitElementnr-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitElementnr() {
        return unitElementnr;
    }

    /**
     * Legt den Wert der unitElementnr-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitElementnr(String value) {
        this.unitElementnr = value;
    }

    /**
     * Ruft den Wert der editingStatusId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEditingStatusId() {
        return editingStatusId;
    }

    /**
     * Legt den Wert der editingStatusId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEditingStatusId(String value) {
        this.editingStatusId = value;
    }

    /**
     * Ruft den Wert der courseEventtypeId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCourseEventtypeId() {
        return courseEventtypeId;
    }

    /**
     * Legt den Wert der courseEventtypeId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCourseEventtypeId(String value) {
        this.courseEventtypeId = value;
    }

    /**
     * Ruft den Wert der courseTeachingLanguageId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCourseTeachingLanguageId() {
        return courseTeachingLanguageId;
    }

    /**
     * Legt den Wert der courseTeachingLanguageId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCourseTeachingLanguageId(String value) {
        this.courseTeachingLanguageId = value;
    }

    /**
     * Ruft den Wert der planelementDefaulttext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanelementDefaulttext() {
        return planelementDefaulttext;
    }

    /**
     * Legt den Wert der planelementDefaulttext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanelementDefaulttext(String value) {
        this.planelementDefaulttext = value;
    }

    /**
     * Ruft den Wert der parallelgroupId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParallelgroupId() {
        return parallelgroupId;
    }

    /**
     * Legt den Wert der parallelgroupId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParallelgroupId(String value) {
        this.parallelgroupId = value;
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
     * Ruft den Wert der termTypeValueId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermTypeValueId() {
        return termTypeValueId;
    }

    /**
     * Legt den Wert der termTypeValueId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermTypeValueId(String value) {
        this.termTypeValueId = value;
    }

    /**
     * Ruft den Wert der planelementEditingStatusId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanelementEditingStatusId() {
        return planelementEditingStatusId;
    }

    /**
     * Legt den Wert der planelementEditingStatusId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanelementEditingStatusId(String value) {
        this.planelementEditingStatusId = value;
    }

    /**
     * Ruft den Wert der individualDatesExecutionDate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndividualDatesExecutionDate() {
        return individualDatesExecutionDate;
    }

    /**
     * Legt den Wert der individualDatesExecutionDate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndividualDatesExecutionDate(String value) {
        this.individualDatesExecutionDate = value;
    }

    /**
     * Ruft den Wert der individualDatesStarttime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndividualDatesStarttime() {
        return individualDatesStarttime;
    }

    /**
     * Legt den Wert der individualDatesStarttime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndividualDatesStarttime(String value) {
        this.individualDatesStarttime = value;
    }

    /**
     * Ruft den Wert der individualDatesEndtime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndividualDatesEndtime() {
        return individualDatesEndtime;
    }

    /**
     * Legt den Wert der individualDatesEndtime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndividualDatesEndtime(String value) {
        this.individualDatesEndtime = value;
    }

    /**
     * Ruft den Wert der roomId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomId() {
        return roomId;
    }

    /**
     * Legt den Wert der roomId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomId(String value) {
        this.roomId = value;
    }

    /**
     * Ruft den Wert der orgunitLid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgunitLid() {
        return orgunitLid;
    }

    /**
     * Legt den Wert der orgunitLid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgunitLid(String value) {
        this.orgunitLid = value;
    }

    /**
     * Ruft den Wert der orgunitRelationTypeId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgunitRelationTypeId() {
        return orgunitRelationTypeId;
    }

    /**
     * Legt den Wert der orgunitRelationTypeId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgunitRelationTypeId(String value) {
        this.orgunitRelationTypeId = value;
    }

    /**
     * Ruft den Wert der termYearForMapping-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermYearForMapping() {
        return termYearForMapping;
    }

    /**
     * Legt den Wert der termYearForMapping-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermYearForMapping(String value) {
        this.termYearForMapping = value;
    }

    /**
     * Ruft den Wert der termTypeValueIdForMapping-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermTypeValueIdForMapping() {
        return termTypeValueIdForMapping;
    }

    /**
     * Legt den Wert der termTypeValueIdForMapping-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermTypeValueIdForMapping(String value) {
        this.termTypeValueIdForMapping = value;
    }

    /**
     * Ruft den Wert der elearningSystemId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getElearningSystemId() {
        return elearningSystemId;
    }

    /**
     * Legt den Wert der elearningSystemId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setElearningSystemId(String value) {
        this.elearningSystemId = value;
    }

    /**
     * Ruft den Wert der courseMappingTypeId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCourseMappingTypeId() {
        return courseMappingTypeId;
    }

    /**
     * Legt den Wert der courseMappingTypeId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCourseMappingTypeId(String value) {
        this.courseMappingTypeId = value;
    }

}
