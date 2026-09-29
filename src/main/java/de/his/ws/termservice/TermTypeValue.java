
package de.his.ws.termservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für termTypeValue complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="termTypeValue"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/TermService}ValueDtoBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="termCategory" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="termNumber" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "termTypeValue", propOrder = {
    "termCategory",
    "termNumber"
})
public class TermTypeValue
    extends ValueDtoBase
{

    @XmlElement(required = true, nillable = true)
    protected BigInteger termCategory;
    @XmlElement(required = true, nillable = true)
    protected BigInteger termNumber;

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
