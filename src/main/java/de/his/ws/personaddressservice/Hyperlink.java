
package de.his.ws.personaddressservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für Hyperlink complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Hyperlink"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="hyperlinkValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="eaddresstype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="addresstag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Hyperlink", propOrder = {
    "hyperlinkValue",
    "eaddresstype",
    "addresstag"
})
public class Hyperlink {

    protected String hyperlinkValue;
    protected String eaddresstype;
    @XmlElement(nillable = true)
    protected String addresstag;

    /**
     * Ruft den Wert der hyperlinkValue-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHyperlinkValue() {
        return hyperlinkValue;
    }

    /**
     * Legt den Wert der hyperlinkValue-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHyperlinkValue(String value) {
        this.hyperlinkValue = value;
    }

    /**
     * Ruft den Wert der eaddresstype-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEaddresstype() {
        return eaddresstype;
    }

    /**
     * Legt den Wert der eaddresstype-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEaddresstype(String value) {
        this.eaddresstype = value;
    }

    /**
     * Ruft den Wert der addresstag-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddresstag() {
        return addresstag;
    }

    /**
     * Legt den Wert der addresstag-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddresstag(String value) {
        this.addresstag = value;
    }

}
