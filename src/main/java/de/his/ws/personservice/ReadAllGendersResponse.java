
package de.his.ws.personservice;

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
 *         &lt;element name="genders" type="{http://www.his.de/ws/PersonService}gendersType"/&gt;
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
    "genders"
})
@XmlRootElement(name = "readAllGendersResponse")
public class ReadAllGendersResponse {

    @XmlElement(required = true)
    protected GendersType genders;

    /**
     * Ruft den Wert der genders-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link GendersType }
     *     
     */
    public GendersType getGenders() {
        return genders;
    }

    /**
     * Legt den Wert der genders-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link GendersType }
     *     
     */
    public void setGenders(GendersType value) {
        this.genders = value;
    }

}
