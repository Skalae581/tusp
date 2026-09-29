
package de.his.ws.orgunitservice;

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
 *         &lt;element name="savedOrgunit" type="{http://www.his.de/ws/OrgUnitService}OrgUnit"/&gt;
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
    "savedOrgunit"
})
@XmlRootElement(name = "saveNewVersionOfExistingOrgunitResponse")
public class SaveNewVersionOfExistingOrgunitResponse {

    @XmlElement(required = true)
    protected OrgUnit savedOrgunit;

    /**
     * Ruft den Wert der savedOrgunit-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link OrgUnit }
     *     
     */
    public OrgUnit getSavedOrgunit() {
        return savedOrgunit;
    }

    /**
     * Legt den Wert der savedOrgunit-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link OrgUnit }
     *     
     */
    public void setSavedOrgunit(OrgUnit value) {
        this.savedOrgunit = value;
    }

}
