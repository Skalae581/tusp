
package de.his.ws.keyvalueservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für ValueClassDescription complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ValueClassDescription"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="valueClass" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="valueClassQualified" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="tablename" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="package" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="defaulttext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValueClassDescription", propOrder = {
    "valueClass",
    "valueClassQualified",
    "tablename",
    "_package",
    "defaulttext"
})
public class ValueClassDescription {

    @XmlElement(required = true)
    protected String valueClass;
    @XmlElement(required = true)
    protected String valueClassQualified;
    @XmlElement(required = true, nillable = true)
    protected String tablename;
    @XmlElement(name = "package", required = true)
    protected String _package;
    @XmlElement(required = true)
    protected String defaulttext;

    /**
     * Ruft den Wert der valueClass-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValueClass() {
        return valueClass;
    }

    /**
     * Legt den Wert der valueClass-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValueClass(String value) {
        this.valueClass = value;
    }

    /**
     * Ruft den Wert der valueClassQualified-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValueClassQualified() {
        return valueClassQualified;
    }

    /**
     * Legt den Wert der valueClassQualified-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValueClassQualified(String value) {
        this.valueClassQualified = value;
    }

    /**
     * Ruft den Wert der tablename-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTablename() {
        return tablename;
    }

    /**
     * Legt den Wert der tablename-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTablename(String value) {
        this.tablename = value;
    }

    /**
     * Ruft den Wert der package-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackage() {
        return _package;
    }

    /**
     * Legt den Wert der package-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackage(String value) {
        this._package = value;
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

}
