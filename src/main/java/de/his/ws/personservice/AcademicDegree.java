
package de.his.ws.personservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Contains basic information for academic degrees.
 * 
 * <p>Java-Klasse für AcademicDegree complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AcademicDegree"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/PersonService}Value"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="positionOfTitle" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="defaulttextFemale" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="longtextFemale" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="shorttextFemale" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AcademicDegree", propOrder = {
    "positionOfTitle",
    "defaulttextFemale",
    "longtextFemale",
    "shorttextFemale"
})
public class AcademicDegree
    extends Value
{

    @XmlElement(required = true, nillable = true)
    protected BigInteger positionOfTitle;
    @XmlElement(required = true, nillable = true)
    protected String defaulttextFemale;
    @XmlElement(required = true, nillable = true)
    protected String longtextFemale;
    @XmlElement(required = true, nillable = true)
    protected String shorttextFemale;

    /**
     * Ruft den Wert der positionOfTitle-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPositionOfTitle() {
        return positionOfTitle;
    }

    /**
     * Legt den Wert der positionOfTitle-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPositionOfTitle(BigInteger value) {
        this.positionOfTitle = value;
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

}
