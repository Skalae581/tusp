
package de.his.ws.periodservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="termYear" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="termTypeId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="usageText" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="periodUsageHiskey" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "termYear",
    "termTypeId",
    "usageText",
    "periodUsageHiskey"
})
@XmlRootElement(name = "getPeriod")
public class GetPeriod {

    @XmlElement(required = true)
    protected BigInteger termYear;
    protected long termTypeId;
    @XmlElement(required = true)
    protected String usageText;
    protected long periodUsageHiskey;

    /**
     * Ruft den Wert der termYear-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTermYear() {
        return termYear;
    }

    /**
     * Legt den Wert der termYear-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTermYear(BigInteger value) {
        this.termYear = value;
    }

    /**
     * Ruft den Wert der termTypeId-Eigenschaft ab.
     * 
     */
    public long getTermTypeId() {
        return termTypeId;
    }

    /**
     * Legt den Wert der termTypeId-Eigenschaft fest.
     * 
     */
    public void setTermTypeId(long value) {
        this.termTypeId = value;
    }

    /**
     * Ruft den Wert der usageText-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsageText() {
        return usageText;
    }

    /**
     * Legt den Wert der usageText-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsageText(String value) {
        this.usageText = value;
    }

    /**
     * Ruft den Wert der periodUsageHiskey-Eigenschaft ab.
     * 
     */
    public long getPeriodUsageHiskey() {
        return periodUsageHiskey;
    }

    /**
     * Legt den Wert der periodUsageHiskey-Eigenschaft fest.
     * 
     */
    public void setPeriodUsageHiskey(long value) {
        this.periodUsageHiskey = value;
    }

}
