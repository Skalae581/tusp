
package de.his.ws.orgunitservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für Orgunit61 complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Orgunit61"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="lid" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="uniquename" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="astat" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="shorttext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="defaulttext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="longtext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="parentId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="parentLid" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="typeId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="validFrom" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="validTo" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="sortorder" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="languageId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="children61" type="{http://www.his.de/ws/OrgUnitService}children61Type" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Orgunit61", propOrder = {
    "id",
    "lid",
    "uniquename",
    "astat",
    "shorttext",
    "defaulttext",
    "longtext",
    "parentId",
    "parentLid",
    "typeId",
    "validFrom",
    "validTo",
    "sortorder",
    "languageId",
    "children61"
})
public class Orgunit61 {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long id;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long lid;
    @XmlElement(required = true)
    protected String uniquename;
    @XmlElement(required = true, nillable = true)
    protected String astat;
    @XmlElement(required = true, nillable = true)
    protected String shorttext;
    @XmlElement(required = true)
    protected String defaulttext;
    @XmlElement(required = true, nillable = true)
    protected String longtext;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long parentId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long parentLid;
    @XmlElement(nillable = true)
    protected Long typeId;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar validFrom;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar validTo;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long sortorder;
    protected long languageId;
    @XmlElement(nillable = true)
    protected Children61Type children61;

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
     * Ruft den Wert der lid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLid() {
        return lid;
    }

    /**
     * Legt den Wert der lid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLid(Long value) {
        this.lid = value;
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
     * Ruft den Wert der astat-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAstat() {
        return astat;
    }

    /**
     * Legt den Wert der astat-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAstat(String value) {
        this.astat = value;
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
     * Ruft den Wert der parentId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * Legt den Wert der parentId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setParentId(Long value) {
        this.parentId = value;
    }

    /**
     * Ruft den Wert der parentLid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getParentLid() {
        return parentLid;
    }

    /**
     * Legt den Wert der parentLid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setParentLid(Long value) {
        this.parentLid = value;
    }

    /**
     * Ruft den Wert der typeId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTypeId() {
        return typeId;
    }

    /**
     * Legt den Wert der typeId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTypeId(Long value) {
        this.typeId = value;
    }

    /**
     * Ruft den Wert der validFrom-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValidFrom() {
        return validFrom;
    }

    /**
     * Legt den Wert der validFrom-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValidFrom(XMLGregorianCalendar value) {
        this.validFrom = value;
    }

    /**
     * Ruft den Wert der validTo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValidTo() {
        return validTo;
    }

    /**
     * Legt den Wert der validTo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValidTo(XMLGregorianCalendar value) {
        this.validTo = value;
    }

    /**
     * Ruft den Wert der sortorder-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSortorder() {
        return sortorder;
    }

    /**
     * Legt den Wert der sortorder-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSortorder(Long value) {
        this.sortorder = value;
    }

    /**
     * Ruft den Wert der languageId-Eigenschaft ab.
     * 
     */
    public long getLanguageId() {
        return languageId;
    }

    /**
     * Legt den Wert der languageId-Eigenschaft fest.
     * 
     */
    public void setLanguageId(long value) {
        this.languageId = value;
    }

    /**
     * Ruft den Wert der children61-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Children61Type }
     *     
     */
    public Children61Type getChildren61() {
        return children61;
    }

    /**
     * Legt den Wert der children61-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Children61Type }
     *     
     */
    public void setChildren61(Children61Type value) {
        this.children61 = value;
    }

}
