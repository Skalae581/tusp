
package de.his.ws.applicationservice;

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
 *         &lt;element name="applicantId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="termYear" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="termCategory" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="termNumber" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
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
    "applicantId",
    "termYear",
    "termCategory",
    "termNumber"
})
@XmlRootElement(name = "getOrCreateApplicationForApplicant")
public class GetOrCreateApplicationForApplicant {

    protected long applicantId;
    @XmlElement(required = true)
    protected BigInteger termYear;
    @XmlElement(required = true)
    protected BigInteger termCategory;
    @XmlElement(required = true)
    protected BigInteger termNumber;

    /**
     * Ruft den Wert der applicantId-Eigenschaft ab.
     * 
     */
    public long getApplicantId() {
        return applicantId;
    }

    /**
     * Legt den Wert der applicantId-Eigenschaft fest.
     * 
     */
    public void setApplicantId(long value) {
        this.applicantId = value;
    }

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
     * Ruft den Wert der termCategory-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTermCategory() {
        return termCategory;
    }

    /**
     * Legt den Wert der termCategory-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTermCategory(BigInteger value) {
        this.termCategory = value;
    }

    /**
     * Ruft den Wert der termNumber-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTermNumber() {
        return termNumber;
    }

    /**
     * Legt den Wert der termNumber-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTermNumber(BigInteger value) {
        this.termNumber = value;
    }

}
