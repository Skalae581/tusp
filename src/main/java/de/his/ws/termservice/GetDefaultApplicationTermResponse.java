
package de.his.ws.termservice;

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
 *         &lt;element name="currentTerm" type="{http://www.his.de/ws/TermService}Term"/&gt;
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
    "currentTerm"
})
@XmlRootElement(name = "getDefaultApplicationTermResponse")
public class GetDefaultApplicationTermResponse {

    @XmlElement(required = true, nillable = true)
    protected Term currentTerm;

    /**
     * Ruft den Wert der currentTerm-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Term }
     *     
     */
    public Term getCurrentTerm() {
        return currentTerm;
    }

    /**
     * Legt den Wert der currentTerm-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Term }
     *     
     */
    public void setCurrentTerm(Term value) {
        this.currentTerm = value;
    }

}
