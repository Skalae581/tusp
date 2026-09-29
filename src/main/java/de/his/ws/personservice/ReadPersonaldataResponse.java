
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
 *         &lt;element name="personaldata" type="{http://www.his.de/ws/PersonService}Personaldata"/&gt;
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
    "personaldata"
})
@XmlRootElement(name = "readPersonaldataResponse")
public class ReadPersonaldataResponse {

    @XmlElement(required = true, nillable = true)
    protected Personaldata personaldata;

    /**
     * Ruft den Wert der personaldata-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Personaldata }
     *     
     */
    public Personaldata getPersonaldata() {
        return personaldata;
    }

    /**
     * Legt den Wert der personaldata-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Personaldata }
     *     
     */
    public void setPersonaldata(Personaldata value) {
        this.personaldata = value;
    }

}
