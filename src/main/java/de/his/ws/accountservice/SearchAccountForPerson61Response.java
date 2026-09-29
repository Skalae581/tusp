
package de.his.ws.accountservice;

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
 *         &lt;element name="completeAccounts60" type="{http://www.his.de/ws/AccountService}completeAccounts60Type"/&gt;
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
    "completeAccounts60"
})
@XmlRootElement(name = "searchAccountForPerson61Response")
public class SearchAccountForPerson61Response {

    @XmlElement(required = true)
    protected CompleteAccounts60Type completeAccounts60;

    /**
     * Ruft den Wert der completeAccounts60-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CompleteAccounts60Type }
     *     
     */
    public CompleteAccounts60Type getCompleteAccounts60() {
        return completeAccounts60;
    }

    /**
     * Legt den Wert der completeAccounts60-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CompleteAccounts60Type }
     *     
     */
    public void setCompleteAccounts60(CompleteAccounts60Type value) {
        this.completeAccounts60 = value;
    }

}
