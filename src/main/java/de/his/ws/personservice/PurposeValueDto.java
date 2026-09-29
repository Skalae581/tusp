
package de.his.ws.personservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für PurposeValueDto complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PurposeValueDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="defaultlanguage" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="defaulttext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="hiskeyId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="longtext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="objecttype" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="shorttext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="sortorder" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="uniquename" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PurposeValueDto", propOrder = {
    "id",
    "defaultlanguage",
    "defaulttext",
    "hiskeyId",
    "longtext",
    "objecttype",
    "shorttext",
    "sortorder",
    "uniquename"
})
public class PurposeValueDto {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long id;
    protected long defaultlanguage;
    @XmlElement(required = true)
    protected String defaulttext;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long hiskeyId;
    @XmlElement(required = true, nillable = true)
    protected String longtext;
    @XmlElement(required = true, nillable = true)
    protected String objecttype;
    @XmlElement(required = true, nillable = true)
    protected String shorttext;
    @XmlElement(required = true, nillable = true)
    protected BigInteger sortorder;
    @XmlElement(required = true, nillable = true)
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
     * Ruft den Wert der hiskeyId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHiskeyId() {
        return hiskeyId;
    }

    /**
     * Legt den Wert der hiskeyId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHiskeyId(Long value) {
        this.hiskeyId = value;
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
     * Ruft den Wert der objecttype-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjecttype() {
        return objecttype;
    }

    /**
     * Legt den Wert der objecttype-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjecttype(String value) {
        this.objecttype = value;
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
     * Ruft den Wert der sortorder-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSortorder() {
        return sortorder;
    }

    /**
     * Legt den Wert der sortorder-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSortorder(BigInteger value) {
        this.sortorder = value;
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
