
package de.his.ws.portalmessageservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für PortalMessageDto complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PortalMessageDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="publishedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="isNew" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="categoryId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="categoryUniquename" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="categoryDefaultname" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="icon" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="linkUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="linkTarget" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="textsByLanguage" type="{http://www.his.de/ws/PortalMessageService}textsByLanguageType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PortalMessageDto", propOrder = {
    "id",
    "publishedDate",
    "isNew",
    "categoryId",
    "categoryUniquename",
    "categoryDefaultname",
    "icon",
    "linkUrl",
    "linkTarget",
    "textsByLanguage"
})
public class PortalMessageDto {

    protected long id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar publishedDate;
    protected boolean isNew;
    protected long categoryId;
    @XmlElement(required = true)
    protected String categoryUniquename;
    @XmlElement(required = true)
    protected String categoryDefaultname;
    @XmlElement(required = true, nillable = true)
    protected String icon;
    @XmlElement(nillable = true)
    protected String linkUrl;
    @XmlElement(nillable = true)
    protected String linkTarget;
    @XmlElement(required = true)
    protected TextsByLanguageType textsByLanguage;

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der publishedDate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPublishedDate() {
        return publishedDate;
    }

    /**
     * Legt den Wert der publishedDate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPublishedDate(XMLGregorianCalendar value) {
        this.publishedDate = value;
    }

    /**
     * Ruft den Wert der isNew-Eigenschaft ab.
     * 
     */
    public boolean isIsNew() {
        return isNew;
    }

    /**
     * Legt den Wert der isNew-Eigenschaft fest.
     * 
     */
    public void setIsNew(boolean value) {
        this.isNew = value;
    }

    /**
     * Ruft den Wert der categoryId-Eigenschaft ab.
     * 
     */
    public long getCategoryId() {
        return categoryId;
    }

    /**
     * Legt den Wert der categoryId-Eigenschaft fest.
     * 
     */
    public void setCategoryId(long value) {
        this.categoryId = value;
    }

    /**
     * Ruft den Wert der categoryUniquename-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryUniquename() {
        return categoryUniquename;
    }

    /**
     * Legt den Wert der categoryUniquename-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryUniquename(String value) {
        this.categoryUniquename = value;
    }

    /**
     * Ruft den Wert der categoryDefaultname-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryDefaultname() {
        return categoryDefaultname;
    }

    /**
     * Legt den Wert der categoryDefaultname-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryDefaultname(String value) {
        this.categoryDefaultname = value;
    }

    /**
     * Ruft den Wert der icon-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Legt den Wert der icon-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIcon(String value) {
        this.icon = value;
    }

    /**
     * Ruft den Wert der linkUrl-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * Legt den Wert der linkUrl-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkUrl(String value) {
        this.linkUrl = value;
    }

    /**
     * Ruft den Wert der linkTarget-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkTarget() {
        return linkTarget;
    }

    /**
     * Legt den Wert der linkTarget-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkTarget(String value) {
        this.linkTarget = value;
    }

    /**
     * Ruft den Wert der textsByLanguage-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TextsByLanguageType }
     *     
     */
    public TextsByLanguageType getTextsByLanguage() {
        return textsByLanguage;
    }

    /**
     * Legt den Wert der textsByLanguage-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TextsByLanguageType }
     *     
     */
    public void setTextsByLanguage(TextsByLanguageType value) {
        this.textsByLanguage = value;
    }

}
