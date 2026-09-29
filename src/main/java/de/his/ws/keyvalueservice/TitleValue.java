
package de.his.ws.keyvalueservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für TitleValue complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TitleValue"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/KeyvalueService}AbstractValueMaleFemale"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="lettersalutation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="lettersalutationFemale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TitleValue", propOrder = {
    "lettersalutation",
    "lettersalutationFemale"
})
public class TitleValue
    extends AbstractValueMaleFemale
{

    @XmlElement(nillable = true)
    protected String lettersalutation;
    @XmlElement(nillable = true)
    protected String lettersalutationFemale;

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

}
