
package de.his.ws.periodservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für PeriodDto complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PeriodDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="defaultlanguage" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="defaulttext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="enddate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="longtext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="objectLocale" type="{http://www.w3.org/2001/XMLSchema}language"/&gt;
 *         &lt;element name="periodUsages" type="{http://www.his.de/ws/PeriodService}periodUsagesType"/&gt;
 *         &lt;element name="periodcontainers" type="{http://www.his.de/ws/PeriodService}periodcontainersType"/&gt;
 *         &lt;element name="shorttext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="startdate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="termSegment" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="termTypeValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="uniquename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeriodDto", propOrder = {
    "id",
    "defaultlanguage",
    "defaulttext",
    "enddate",
    "longtext",
    "objectLocale",
    "periodUsages",
    "periodcontainers",
    "shorttext",
    "startdate",
    "termSegment",
    "termTypeValueId",
    "year",
    "uniquename"
})
public class PeriodDto {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long id;
    protected long defaultlanguage;
    @XmlElement(required = true)
    protected String defaulttext;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar enddate;
    @XmlElement(required = true, nillable = true)
    protected String longtext;
    @XmlElement(required = true, nillable = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String objectLocale;
    @XmlElement(required = true, nillable = true)
    protected PeriodUsagesType periodUsages;
    @XmlElement(required = true, nillable = true)
    protected PeriodcontainersType periodcontainers;
    @XmlElement(required = true, nillable = true)
    protected String shorttext;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startdate;
    @XmlElement(required = true, nillable = true)
    protected BigInteger termSegment;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long termTypeValueId;
    @XmlElement(required = true, nillable = true)
    protected BigInteger year;
    protected String uniquename;

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
     * Ruft den Wert der defaultlanguage-Eigenschaft ab.
     * 
     */
    public long getDefaultlanguage() {
        return defaultlanguage;
    }

    /**
     * Legt den Wert der defaultlanguage-Eigenschaft fest.
     * 
     */
    public void setDefaultlanguage(long value) {
        this.defaultlanguage = value;
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
     * Ruft den Wert der enddate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEnddate() {
        return enddate;
    }

    /**
     * Legt den Wert der enddate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEnddate(XMLGregorianCalendar value) {
        this.enddate = value;
    }

    /**
     * Ruft den Wert der longtext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongtext() {
        return longtext;
    }

    /**
     * Legt den Wert der longtext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongtext(String value) {
        this.longtext = value;
    }

    /**
     * Ruft den Wert der objectLocale-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectLocale() {
        return objectLocale;
    }

    /**
     * Legt den Wert der objectLocale-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectLocale(String value) {
        this.objectLocale = value;
    }

    /**
     * Ruft den Wert der periodUsages-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PeriodUsagesType }
     *     
     */
    public PeriodUsagesType getPeriodUsages() {
        return periodUsages;
    }

    /**
     * Legt den Wert der periodUsages-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodUsagesType }
     *     
     */
    public void setPeriodUsages(PeriodUsagesType value) {
        this.periodUsages = value;
    }

    /**
     * Ruft den Wert der periodcontainers-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PeriodcontainersType }
     *     
     */
    public PeriodcontainersType getPeriodcontainers() {
        return periodcontainers;
    }

    /**
     * Legt den Wert der periodcontainers-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodcontainersType }
     *     
     */
    public void setPeriodcontainers(PeriodcontainersType value) {
        this.periodcontainers = value;
    }

    /**
     * Ruft den Wert der shorttext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShorttext() {
        return shorttext;
    }

    /**
     * Legt den Wert der shorttext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShorttext(String value) {
        this.shorttext = value;
    }

    /**
     * Ruft den Wert der startdate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartdate() {
        return startdate;
    }

    /**
     * Legt den Wert der startdate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartdate(XMLGregorianCalendar value) {
        this.startdate = value;
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
     * Ruft den Wert der termTypeValueId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTermTypeValueId() {
        return termTypeValueId;
    }

    /**
     * Legt den Wert der termTypeValueId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTermTypeValueId(Long value) {
        this.termTypeValueId = value;
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

}
