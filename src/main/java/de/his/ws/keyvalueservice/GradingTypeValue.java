
package de.his.ws.keyvalueservice;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für GradingTypeValue complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="GradingTypeValue"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/KeyvalueService}AbstractKeyValue"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="typeOfGrading" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="lowerLimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="upperLimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="creditsThreshold" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="regularExpression" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="roundingPosition" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GradingTypeValue", propOrder = {
    "typeOfGrading",
    "lowerLimit",
    "upperLimit",
    "creditsThreshold",
    "regularExpression",
    "roundingPosition"
})
public class GradingTypeValue
    extends AbstractKeyValue
{

    @XmlElement(nillable = true)
    protected BigInteger typeOfGrading;
    @XmlElement(nillable = true)
    protected BigDecimal lowerLimit;
    @XmlElement(nillable = true)
    protected BigDecimal upperLimit;
    @XmlElement(nillable = true)
    protected BigDecimal creditsThreshold;
    @XmlElement(nillable = true)
    protected String regularExpression;
    @XmlElement(nillable = true)
    protected BigInteger roundingPosition;

    /**
     * Ruft den Wert der typeOfGrading-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTypeOfGrading() {
        return typeOfGrading;
    }

    /**
     * Legt den Wert der typeOfGrading-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTypeOfGrading(BigInteger value) {
        this.typeOfGrading = value;
    }

    /**
     * Ruft den Wert der lowerLimit-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLowerLimit() {
        return lowerLimit;
    }

    /**
     * Legt den Wert der lowerLimit-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLowerLimit(BigDecimal value) {
        this.lowerLimit = value;
    }

    /**
     * Ruft den Wert der upperLimit-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUpperLimit() {
        return upperLimit;
    }

    /**
     * Legt den Wert der upperLimit-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUpperLimit(BigDecimal value) {
        this.upperLimit = value;
    }

    /**
     * Ruft den Wert der creditsThreshold-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCreditsThreshold() {
        return creditsThreshold;
    }

    /**
     * Legt den Wert der creditsThreshold-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCreditsThreshold(BigDecimal value) {
        this.creditsThreshold = value;
    }

    /**
     * Ruft den Wert der regularExpression-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegularExpression() {
        return regularExpression;
    }

    /**
     * Legt den Wert der regularExpression-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegularExpression(String value) {
        this.regularExpression = value;
    }

    /**
     * Ruft den Wert der roundingPosition-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRoundingPosition() {
        return roundingPosition;
    }

    /**
     * Legt den Wert der roundingPosition-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRoundingPosition(BigInteger value) {
        this.roundingPosition = value;
    }

}
