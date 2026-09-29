
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
 *         &lt;element name="savedAccounts" type="{http://www.his.de/ws/AccountService}savedAccountsType"/&gt;
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
    "savedAccounts"
})
@XmlRootElement(name = "saveAccountResponse")
public class SaveAccountResponse {

    @XmlElement(required = true)
    protected SavedAccountsType savedAccounts;

    /**
     * Ruft den Wert der savedAccounts-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SavedAccountsType }
     *     
     */
    public SavedAccountsType getSavedAccounts() {
        return savedAccounts;
    }

    /**
     * Legt den Wert der savedAccounts-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SavedAccountsType }
     *     
     */
    public void setSavedAccounts(SavedAccountsType value) {
        this.savedAccounts = value;
    }

}
