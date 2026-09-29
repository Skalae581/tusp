
package de.his.ws.termservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für Term complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Term"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/TermService}ValueDtoBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="termType" type="{http://www.his.de/ws/TermService}termTypeValue"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Term", propOrder = {
    "id",
    "year",
    "termType"
})
public class Term
    extends ValueDtoBase
{

    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected BigInteger year;
    @XmlElement(required = true)
    protected TermTypeValue termType;

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der year-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getYear() {
        return year;
    }

    /**
     * Legt den Wert der year-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setYear(BigInteger value) {
        this.year = value;
    }

    /**
     * Ruft den Wert der termType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TermTypeValue }
     *     
     */
    public TermTypeValue getTermType() {
        return termType;
    }

    /**
     * Legt den Wert der termType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TermTypeValue }
     *     
     */
    public void setTermType(TermTypeValue value) {
        this.termType = value;
    }

}
