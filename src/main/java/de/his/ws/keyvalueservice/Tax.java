
package de.his.ws.keyvalueservice;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für Tax complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Tax"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/KeyvalueService}AbstractKeyValue"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="rate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="taxType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fractionOfRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="creditUstva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="debitUstva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rateUpdatedAt" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="taxKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tax", propOrder = {
    "rate",
    "taxType",
    "fractionOfRate",
    "creditUstva",
    "debitUstva",
    "rateUpdatedAt",
    "taxKey"
})
public class Tax
    extends AbstractKeyValue
{

    @XmlElement(nillable = true)
    protected BigDecimal rate;
    @XmlElement(nillable = true)
    protected String taxType;
    @XmlElement(nillable = true)
    protected BigDecimal fractionOfRate;
    @XmlElement(nillable = true)
    protected String creditUstva;
    @XmlElement(nillable = true)
    protected String debitUstva;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar rateUpdatedAt;
    @XmlElement(nillable = true)
    protected String taxKey;

    /**
     * Ruft den Wert der rate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * Legt den Wert der rate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRate(BigDecimal value) {
        this.rate = value;
    }

    /**
     * Ruft den Wert der taxType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxType() {
        return taxType;
    }

    /**
     * Legt den Wert der taxType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxType(String value) {
        this.taxType = value;
    }

    /**
     * Ruft den Wert der fractionOfRate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFractionOfRate() {
        return fractionOfRate;
    }

    /**
     * Legt den Wert der fractionOfRate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFractionOfRate(BigDecimal value) {
        this.fractionOfRate = value;
    }

    /**
     * Ruft den Wert der creditUstva-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditUstva() {
        return creditUstva;
    }

    /**
     * Legt den Wert der creditUstva-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditUstva(String value) {
        this.creditUstva = value;
    }

    /**
     * Ruft den Wert der debitUstva-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebitUstva() {
        return debitUstva;
    }

    /**
     * Legt den Wert der debitUstva-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebitUstva(String value) {
        this.debitUstva = value;
    }

    /**
     * Ruft den Wert der rateUpdatedAt-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRateUpdatedAt() {
        return rateUpdatedAt;
    }

    /**
     * Legt den Wert der rateUpdatedAt-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRateUpdatedAt(XMLGregorianCalendar value) {
        this.rateUpdatedAt = value;
    }

    /**
     * Ruft den Wert der taxKey-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxKey() {
        return taxKey;
    }

    /**
     * Legt den Wert der taxKey-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxKey(String value) {
        this.taxKey = value;
    }

}
