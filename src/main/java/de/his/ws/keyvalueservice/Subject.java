
package de.his.ws.keyvalueservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für Subject complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Subject"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/KeyvalueService}AbstractKeyValue"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="astatBund" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="astatLand" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Subject", propOrder = {
    "astatBund",
    "astatLand"
})
public class Subject
    extends AbstractKeyValue
{

    @XmlElement(nillable = true)
    protected String astatBund;
    @XmlElement(nillable = true)
    protected String astatLand;

    /**
     * Ruft den Wert der astatBund-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAstatBund() {
        return astatBund;
    }

    /**
     * Legt den Wert der astatBund-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAstatBund(String value) {
        this.astatBund = value;
    }

    /**
     * Ruft den Wert der astatLand-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAstatLand() {
        return astatLand;
    }

    /**
     * Legt den Wert der astatLand-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAstatLand(String value) {
        this.astatLand = value;
    }

}
