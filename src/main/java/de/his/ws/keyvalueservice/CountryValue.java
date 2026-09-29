
package de.his.ws.keyvalueservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für CountryValue complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="CountryValue"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/KeyvalueService}AbstractKeyValue"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="euMember" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="ewrMember" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="oecd" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="continent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="iso3166_1_alpha_2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="postalCountryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="citizenship" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CountryValue", propOrder = {
    "euMember",
    "ewrMember",
    "oecd",
    "continent",
    "iso31661Alpha2",
    "postalCountryName",
    "citizenship"
})
public class CountryValue
    extends AbstractKeyValue
{

    @XmlElement(nillable = true)
    protected BigInteger euMember;
    @XmlElement(nillable = true)
    protected BigInteger ewrMember;
    @XmlElement(nillable = true)
    protected BigInteger oecd;
    @XmlElement(nillable = true)
    protected String continent;
    @XmlElement(name = "iso3166_1_alpha_2", nillable = true)
    protected String iso31661Alpha2;
    @XmlElement(nillable = true)
    protected String postalCountryName;
    @XmlElement(nillable = true)
    protected String citizenship;

    /**
     * Ruft den Wert der euMember-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEuMember() {
        return euMember;
    }

    /**
     * Legt den Wert der euMember-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEuMember(BigInteger value) {
        this.euMember = value;
    }

    /**
     * Ruft den Wert der ewrMember-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEwrMember() {
        return ewrMember;
    }

    /**
     * Legt den Wert der ewrMember-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEwrMember(BigInteger value) {
        this.ewrMember = value;
    }

    /**
     * Ruft den Wert der oecd-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOecd() {
        return oecd;
    }

    /**
     * Legt den Wert der oecd-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOecd(BigInteger value) {
        this.oecd = value;
    }

    /**
     * Ruft den Wert der continent-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContinent() {
        return continent;
    }

    /**
     * Legt den Wert der continent-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContinent(String value) {
        this.continent = value;
    }

    /**
     * Ruft den Wert der iso31661Alpha2-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIso31661Alpha2() {
        return iso31661Alpha2;
    }

    /**
     * Legt den Wert der iso31661Alpha2-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIso31661Alpha2(String value) {
        this.iso31661Alpha2 = value;
    }

    /**
     * Ruft den Wert der postalCountryName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCountryName() {
        return postalCountryName;
    }

    /**
     * Legt den Wert der postalCountryName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCountryName(String value) {
        this.postalCountryName = value;
    }

    /**
     * Ruft den Wert der citizenship-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCitizenship() {
        return citizenship;
    }

    /**
     * Legt den Wert der citizenship-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCitizenship(String value) {
        this.citizenship = value;
    }

}
