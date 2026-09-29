
package de.his.ws.personservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Contains basic information for titles.
 * 
 * <p>Java-Klasse für Title complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Title"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/PersonService}Value"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="defaulttextFemale" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Title", propOrder = {
    "defaulttextFemale"
})
public class Title
    extends Value
{

    @XmlElement(required = true, nillable = true)
    protected String defaulttextFemale;

    /**
     * Ruft den Wert der defaulttextFemale-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaulttextFemale() {
        return defaulttextFemale;
    }

    /**
     * Legt den Wert der defaulttextFemale-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaulttextFemale(String value) {
        this.defaulttextFemale = value;
    }

}
