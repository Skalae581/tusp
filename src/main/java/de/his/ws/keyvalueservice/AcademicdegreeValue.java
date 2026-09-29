
package de.his.ws.keyvalueservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für AcademicdegreeValue complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AcademicdegreeValue"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/KeyvalueService}AbstractValueMaleFemale"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="positionOfTitle" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="lettersalutationFemale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="lettersalutation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AcademicdegreeValue", propOrder = {
    "positionOfTitle",
    "lettersalutationFemale",
    "lettersalutation"
})
public class AcademicdegreeValue
    extends AbstractValueMaleFemale
{

    @XmlElement(nillable = true)
    protected BigInteger positionOfTitle;
    @XmlElement(nillable = true)
    protected String lettersalutationFemale;
    @XmlElement(nillable = true)
    protected String lettersalutation;

    /**
     * Ruft den Wert der positionOfTitle-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPositionOfTitle() {
        return positionOfTitle;
    }

    /**
     * Legt den Wert der positionOfTitle-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPositionOfTitle(BigInteger value) {
        this.positionOfTitle = value;
    }

    /**
     * Ruft den Wert der lettersalutationFemale-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLettersalutationFemale() {
        return lettersalutationFemale;
    }

    /**
     * Legt den Wert der lettersalutationFemale-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLettersalutationFemale(String value) {
        this.lettersalutationFemale = value;
    }

    /**
     * Ruft den Wert der lettersalutation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLettersalutation() {
        return lettersalutation;
    }

    /**
     * Legt den Wert der lettersalutation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLettersalutation(String value) {
        this.lettersalutation = value;
    }

}
