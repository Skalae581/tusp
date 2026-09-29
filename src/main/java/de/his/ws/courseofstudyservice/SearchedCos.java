
package de.his.ws.courseofstudyservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für SearchedCos complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="SearchedCos"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="courseofstudyId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="courseofstudyLid" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="uniquename" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
@XmlType(name = "SearchedCos", propOrder = {
    "courseofstudyId",
    "courseofstudyLid",
    "uniquename",
    "defaulttext"
})
public class SearchedCos {

    protected long courseofstudyId;
    protected long courseofstudyLid;
    @XmlElement(required = true, nillable = true)
    protected String uniquename;
    @XmlElement(required = true, nillable = true)
    protected String defaulttext;

    /**
     * Ruft den Wert der courseofstudyId-Eigenschaft ab.
     * 
     */
    public long getCourseofstudyId() {
        return courseofstudyId;
    }

    /**
     * Legt den Wert der courseofstudyId-Eigenschaft fest.
     * 
     */
    public void setCourseofstudyId(long value) {
        this.courseofstudyId = value;
    }

    /**
     * Ruft den Wert der courseofstudyLid-Eigenschaft ab.
     * 
     */
    public long getCourseofstudyLid() {
        return courseofstudyLid;
    }

    /**
     * Legt den Wert der courseofstudyLid-Eigenschaft fest.
     * 
     */
    public void setCourseofstudyLid(long value) {
        this.courseofstudyLid = value;
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
