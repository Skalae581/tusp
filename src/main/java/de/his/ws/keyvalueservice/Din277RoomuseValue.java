
package de.his.ws.keyvalueservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für Din277RoomuseValue complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Din277RoomuseValue"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/KeyvalueService}AbstractKeyValue"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dinnumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isEventLocation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="isOfficeLocation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Din277RoomuseValue", propOrder = {
    "dinnumber",
    "isEventLocation",
    "isOfficeLocation"
})
public class Din277RoomuseValue
    extends AbstractKeyValue
{

    @XmlElement(nillable = true)
    protected String dinnumber;
    @XmlElement(nillable = true)
    protected Boolean isEventLocation;
    @XmlElement(nillable = true)
    protected Boolean isOfficeLocation;

    /**
     * Ruft den Wert der dinnumber-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDinnumber() {
        return dinnumber;
    }

    /**
     * Legt den Wert der dinnumber-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDinnumber(String value) {
        this.dinnumber = value;
    }

    /**
     * Ruft den Wert der isEventLocation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsEventLocation() {
        return isEventLocation;
    }

    /**
     * Legt den Wert der isEventLocation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsEventLocation(Boolean value) {
        this.isEventLocation = value;
    }

    /**
     * Ruft den Wert der isOfficeLocation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsOfficeLocation() {
        return isOfficeLocation;
    }

    /**
     * Legt den Wert der isOfficeLocation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsOfficeLocation(Boolean value) {
        this.isOfficeLocation = value;
    }

}
