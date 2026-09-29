
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
 * <p>Java-Klasse für EntranceQualification201712 complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="EntranceQualification201712"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="objGuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dateOfWork" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="termSegment" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="countryId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="countryLid" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="districtId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="districtLid" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="entranceQualificationTypeId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="totalQueueTime" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="eduType" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="externalKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="externalsystemId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="foreignGrade" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="germanGrade" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntranceQualification201712", propOrder = {
    "id",
    "objGuid",
    "dateOfWork",
    "termSegment",
    "year",
    "countryId",
    "countryLid",
    "districtId",
    "districtLid",
    "entranceQualificationTypeId",
    "totalQueueTime",
    "eduType",
    "externalKey",
    "externalsystemId",
    "foreignGrade",
    "germanGrade"
})
public class EntranceQualification201712 {

    protected Long id;
    @XmlElement(nillable = true)
    protected String objGuid;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateOfWork;
    @XmlElement(required = true, nillable = true)
    protected BigInteger termSegment;
    @XmlElement(required = true, nillable = true)
    protected BigInteger year;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long countryId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long countryLid;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long districtId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long districtLid;
    @XmlElement(nillable = true)
    protected Long entranceQualificationTypeId;
    @XmlElement(nillable = true)
    protected BigInteger totalQueueTime;
    @XmlElement(nillable = true)
    protected Long eduType;
    @XmlElement(nillable = true)
    protected String externalKey;
    @XmlElement(nillable = true)
    protected Long externalsystemId;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal foreignGrade;
    @XmlElement(nillable = true)
    protected BigDecimal germanGrade;

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
     * Ruft den Wert der dateOfWork-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateOfWork() {
        return dateOfWork;
    }

    /**
     * Legt den Wert der dateOfWork-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateOfWork(XMLGregorianCalendar value) {
        this.dateOfWork = value;
    }

    /**
     * Ruft den Wert der termSegment-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTermSegment() {
        return termSegment;
    }

    /**
     * Legt den Wert der termSegment-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTermSegment(BigInteger value) {
        this.termSegment = value;
    }

    /**
     * Ruft den Wert der year-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getYear() {
        return year;
    }

    /**
     * Legt den Wert der year-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setYear(BigInteger value) {
        this.year = value;
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
     * Ruft den Wert der countryLid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCountryLid() {
        return countryLid;
    }

    /**
     * Legt den Wert der countryLid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCountryLid(Long value) {
        this.countryLid = value;
    }

    /**
     * Ruft den Wert der districtId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDistrictId() {
        return districtId;
    }

    /**
     * Legt den Wert der districtId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDistrictId(Long value) {
        this.districtId = value;
    }

    /**
     * Ruft den Wert der districtLid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDistrictLid() {
        return districtLid;
    }

    /**
     * Legt den Wert der districtLid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDistrictLid(Long value) {
        this.districtLid = value;
    }

    /**
     * Ruft den Wert der entranceQualificationTypeId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEntranceQualificationTypeId() {
        return entranceQualificationTypeId;
    }

    /**
     * Legt den Wert der entranceQualificationTypeId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEntranceQualificationTypeId(Long value) {
        this.entranceQualificationTypeId = value;
    }

    /**
     * Ruft den Wert der totalQueueTime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalQueueTime() {
        return totalQueueTime;
    }

    /**
     * Legt den Wert der totalQueueTime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalQueueTime(BigInteger value) {
        this.totalQueueTime = value;
    }

    /**
     * Ruft den Wert der eduType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEduType() {
        return eduType;
    }

    /**
     * Legt den Wert der eduType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEduType(Long value) {
        this.eduType = value;
    }

    /**
     * Ruft den Wert der externalKey-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalKey() {
        return externalKey;
    }

    /**
     * Legt den Wert der externalKey-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalKey(String value) {
        this.externalKey = value;
    }

    /**
     * Ruft den Wert der externalsystemId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getExternalsystemId() {
        return externalsystemId;
    }

    /**
     * Legt den Wert der externalsystemId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setExternalsystemId(Long value) {
        this.externalsystemId = value;
    }

    /**
     * Ruft den Wert der foreignGrade-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getForeignGrade() {
        return foreignGrade;
    }

    /**
     * Legt den Wert der foreignGrade-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setForeignGrade(BigDecimal value) {
        this.foreignGrade = value;
    }

    /**
     * Ruft den Wert der germanGrade-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGermanGrade() {
        return germanGrade;
    }

    /**
     * Legt den Wert der germanGrade-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGermanGrade(BigDecimal value) {
        this.germanGrade = value;
    }

}
