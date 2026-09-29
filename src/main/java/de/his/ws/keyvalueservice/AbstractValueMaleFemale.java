
package de.his.ws.keyvalueservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für AbstractValueMaleFemale complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AbstractValueMaleFemale"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/KeyvalueService}AbstractKeyValue"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="shorttextFemale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="defaulttextFemale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="longtextFemale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractValueMaleFemale", propOrder = {
    "shorttextFemale",
    "defaulttextFemale",
    "longtextFemale"
})
@XmlSeeAlso({
    TitleValue.class,
    AcademicdegreeValue.class
})
public abstract class AbstractValueMaleFemale
    extends AbstractKeyValue
{

    @XmlElement(nillable = true)
    protected String shorttextFemale;
    @XmlElement(nillable = true)
    protected String defaulttextFemale;
    @XmlElement(nillable = true)
    protected String longtextFemale;

    /**
     * Ruft den Wert der shorttextFemale-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShorttextFemale() {
        return shorttextFemale;
    }

    /**
     * Legt den Wert der shorttextFemale-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShorttextFemale(String value) {
        this.shorttextFemale = value;
    }

    /**
     * Ruft den Wert der defaulttextFemale-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaulttextFemale() {
        return defaulttextFemale;
    }

    /**
     * Legt den Wert der defaulttextFemale-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaulttextFemale(String value) {
        this.defaulttextFemale = value;
    }

    /**
     * Ruft den Wert der longtextFemale-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongtextFemale() {
        return longtextFemale;
    }

    /**
     * Legt den Wert der longtextFemale-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongtextFemale(String value) {
        this.longtextFemale = value;
    }

}
