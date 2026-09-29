
package de.his.ws.applicantservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für QuotaRankingInformation complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="QuotaRankingInformation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="quota" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="processStep" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="rankingNumber" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QuotaRankingInformation", propOrder = {
    "quota",
    "processStep",
    "rankingNumber"
})
public class QuotaRankingInformation {

    @XmlElement(required = true, nillable = true)
    protected String quota;
    @XmlElement(required = true, nillable = true)
    protected String processStep;
    @XmlElement(required = true, nillable = true)
    protected BigInteger rankingNumber;

    /**
     * Ruft den Wert der quota-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuota() {
        return quota;
    }

    /**
     * Legt den Wert der quota-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuota(String value) {
        this.quota = value;
    }

    /**
     * Ruft den Wert der processStep-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessStep() {
        return processStep;
    }

    /**
     * Legt den Wert der processStep-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessStep(String value) {
        this.processStep = value;
    }

    /**
     * Ruft den Wert der rankingNumber-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRankingNumber() {
        return rankingNumber;
    }

    /**
     * Legt den Wert der rankingNumber-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRankingNumber(BigInteger value) {
        this.rankingNumber = value;
    }

}
